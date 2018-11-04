/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.Paciente;
import Vista.CatPaciente;
import Vista.Principal;
import Modelo.ModeloEmpleado;
import Modelo.ModeloEstado;
import Modelo.ModeloMunicipio;
import Modelo.ModeloPaciente;
import Modelo.ModeloParroquia;
import Modelo.ModeloTipoEmpleado;
import Modelo.ModeloTipoPersona;
import Modelo.Reports;
import static java.awt.Color.green;
import static java.awt.Color.orange;
import static java.awt.Color.red;
import java.awt.Component;
import java.awt.Cursor;


//librerias...
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import java.awt.HeadlessException;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 * @author Jnatn'h
 */
public class ControladorPaciente implements ActionListener, KeyListener, MouseListener{
  
/*************************************************/
/*              CAMPOS DE CLASE                  */
/*************************************************/
    private long cedul;
    private DefaultTableModel dm;
    private ModeloPaciente modelo;
    private ModeloEmpleado modeloempleado;
    private ModeloTipoPersona modelotipopersona;
    private CatPaciente catalogo,catalogo1;
    private boolean validafechanac = false;
    private Principal principal;
    public Paciente vista;
    private Reports report;
    private boolean desdeservicio = false;
    private ModeloEstado estado;
    private ModeloParroquia parroquia;
/*************************************************/
/*              CONSTRUCTORES                    */
/*************************************************/
    public ControladorPaciente(CatPaciente catalogo){
        this.catalogo = catalogo;
        this.catalogo1 = catalogo;
        modelo = new ModeloPaciente(); // Objeto de paciente con herencia de persona 
        modeloempleado = new ModeloEmpleado(); // Objeto de empleado con herencia de persona 
        modelotipopersona = new ModeloTipoPersona(); // objeto de tipopersona 
        vista = new Paciente(principal,true); // Instanciamos la clase Paciente... 
        // Funcionalidades de los objetos con herenciade dialogos .. 
//        this.listaEmpleado();
        this.listarafiliado();
        this.listarpacientes();
        report = new Reports();
    }

    public ControladorPaciente(Paciente vista, boolean desdeservicio){
        this.desdeservicio = desdeservicio;
        this.vista = vista;
        report = new Reports();
    }
    
    
/*************************************************/
/*             METODOS DE CLASE                  */
/*************************************************/
    
   /**
    * Metodo que me enlista en la vista catalogo de paciente, los datos correspondiente a todos los pacientes registrado en el sistema.
    */
    public void listarpacientes(){
        String[][] informacion =  modelo.ListarPacientes();
        catalogo.getCatpaciente().setModel(new DefaultTableModel(informacion,
        new String [] {"Cédula", "Nombre", "Apellido", "Tipo de Paciente"}) {

			private static final long serialVersionUID = 1L;
		boolean[] canEdit = new boolean [] {
            false,false,false,false
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
        catalogo.getCatpaciente().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    /**
     * @param consulta
     * @param JTableBuscar 
     */
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
   
    @Override
    public void actionPerformed(ActionEvent evento) {
        Object origenEvento = evento.getSource();/// un objeto que recibe el objet que genera el evento
        // Las acciones generadas desde la vista de paciente
        if(origenEvento.equals(vista.getTipopaciente())){
            String captura;
            captura = (String) vista.getTipopaciente().getSelectedItem();
            if (null != captura) switch (captura) {
                case "EMPLEADO":
                    this.validafechanac = this.validarfechanac();
                    habilitarempleado();
                    vista.getjTabbedPaciente().setSelectedIndex(1);
                    break;
                case "ESTUDIANTE":
                    this.validafechanac = this.validarfechanac();
                    habilitarestudiante();
                    vista.getjTabbedPaciente().setSelectedIndex(3);
                    break;
                case "COMUNIDAD":
                    this.validafechanac = this.validarfechanac();
                    habilitarcomunida();
                    break;
                case "FAMILIAR":
                    this.validafechanac = this.validarfechanac();
                    habilitarfamiliar();
                    vista.getjTabbedPaciente().setSelectedIndex(0);
                    break;
                case "SELECCIONE":
                    break;
                default:
                    this.validafechanac = this.validarfechanac();
                    habilitarcomunida();
                    break;
            }
        }else if(origenEvento.equals(catalogo.getNuevopaciente())){
            catalogo.dispose();
            vista = new Paciente(principal,true);
            vista.setController(this);
            vista.setVisible(true);
        }else if (origenEvento.equals(vista.getEstado())){	
            DefaultComboBoxModel<String> model  = new DefaultComboBoxModel<String>();
            ListarMunicipio(model);
            vista.getMunicipio().setEnabled(true);
            vista.getMunicipio().setModel(model);    
        }else if (origenEvento.equals(vista.getMunicipio())){	
            DefaultComboBoxModel model  = new DefaultComboBoxModel();
            ListarParroquia(model);
            vista.getParroquia().setEnabled(true);
            vista.getParroquia().setModel(model);    
        }else if(evento.getSource() == vista.getRegistrar()){
            boolean validar = this.validar();
            if (validar) {                
                if (vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("EMPLEADO")) {
                    try {
                         ingresarempleado();
                    } catch (SQLException e) {
                        System.out.println("ERROR: "+e.getMessage());
                    }
                  
                }else if(vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("FAMILIAR")){
                    try {
                        ingresarfamiliar(); 
                    } catch (SQLException e) {
                        System.out.println("ERROR: "+e.getMessage());
                    }
                   
                }else if(vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("ESTUDiante")){
                    try {
                        ingresarestudiante();
                    } catch (SQLException e) {
                        System.out.println("ERROR: "+e.getMessage());
                    }
                    
                }else if(vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("comunidad")){
                    try {
                         ingresarcomunidad();
                    } catch (SQLException e) {
                        System.out.println("ERROR: "+e.getMessage());
                    }
                }else{
                    try{
                        ingresarcomunidad();
                    }catch(SQLException ex2){
                        System.out.println("ERROR: "+ex2.getMessage());
                    }
                }
            }         
         }else if(evento.getSource() == vista.getSalir()){
                vista.dispose();
                catalogo = new CatPaciente(principal,true);
                catalogo.setController(this);
                this.listarpacientes();
                catalogo.setVisible(true); 
                Principal.getController().setPaciente(catalogo);
         }else if(evento.getSource().equals(vista.getBtonafiliado())){
             catalogo1 = new CatPaciente(principal,true,true);
             catalogo1.setController(this);
             modelo = new ModeloPaciente();
             this.listaEmpleado();
             catalogo1.setVisible(true);
             catalogo1.getNuevopaciente().setVisible(false);
             catalogo1.getNuevopaciente().setEnabled(false);
//                if (vista.getCedulaafiliado().getText().length() < 7 || vista.getCedulaafiliado().getText().isEmpty()) {
//                    String leyenda = "Es necesario que ingreses el número de cedula del afiliado...";
//                    vista.getLeyenda().setText(leyenda);
//                    vista.getLeyenda().setForeground(orange);
//                }else{
//                    buscarafiliado();
//                }
         }else if(evento.getSource().equals(vista.getModificar())){
             boolean validar = this.validar();
             if (validar) {
                if (vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("EMPLEADO")) {
                    try {
                         modificarempleado();
                    } catch (SQLException e) {
                        System.out.println("ERROR: "+e.getMessage());
                    }
                }else if(vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("FAMILIAR")){
                    try {
                        modificarfamiliar();
                    } catch (SQLException e) {
                        System.out.println("ERROR: "+e.getMessage());
                    }
                }else if(vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("ESTUDiante")){
                    try {
                       modificarestudiante();
                    } catch (SQLException e) {
                        System.out.println("ERROR: "+e.getMessage());
                    }
                }else if(vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("comunidad")){
                    try {
                        modificarcomunidad();
                    } catch (SQLException e) {
                        System.out.println("ERROR: "+e.getMessage());
                    }
                }
            }
        }else if(evento.getSource().equals(vista.getBtonimprimir())){
            String[] opcion = {"Impresion","Mostrar"};
          int opc = JOptionPane.showOptionDialog(principal,"Elija una opción", "Impresion o mostrar",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opcion,opcion[1]);
          if(opc == 0){
              report.paciente(vista.getCedula(),true);
          }else{
              report.paciente(vista.getCedula(),false);
          }  
          vista.setCursor(new Cursor(Cursor.WAIT_CURSOR));
         }
        
        // Invocados desde otros Objevista.getCedula(), this);tos... 
        try {
            if (this.desdeservicio && origenEvento.equals(vista.getSalir())){
               vista.dispose();
            }else if(evento.getSource().equals(catalogo1.getSalircat())){
               catalogo1.dispose();
            } 
        } catch (NullPointerException exc) {
            
            System.out.println(exc.getMessage());
        }
       
      }
    
    private boolean validarfechanac(){
        boolean captura = false;
        captura = modelotipopersona.capturarvalicionfechanac(vista.getTipopaciente().getSelectedItem().toString());
        return captura;
    }
    
    public void listaEmpleado(){
        String[][] informacion = modelo.ListarAfiliado();
        catalogo1.getCatpaciente().setModel(new javax.swing.table.DefaultTableModel(
            informacion,
            new String [] {"Céula","Nombre","Apellido","Tipo Paciente"}) {
            boolean[] canEdit = new boolean [] {
                false,false,false,false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
});
        catalogo1.getCatpaciente().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    public void listartipopaciente(DefaultComboBoxModel modeloCombo){
       boolean resultado= false;
        resultado= modelotipopersona.Listartipopersona(modeloCombo);
      }
    
    public void Listarestado(DefaultComboBoxModel<String> listar){
        boolean resultado = false;
        ModeloEstado modeloEstado = new ModeloEstado();
        resultado= modeloEstado.cargarDatosEstado(listar);
        
        vista.getMunicipio().setEnabled(false);
        vista.getMunicipio().setSelectedItem("Seleccione");
        vista.getParroquia().setEnabled(false);
      //  vista.getParroquia().setSelectedIndex(0);
    }
    
    public void ListarMunicipio(DefaultComboBoxModel<String> modeloCombo){
        boolean resultado= false;
        ModeloMunicipio modeloMunicipio = new ModeloMunicipio();
        String nombreEstadoSeleccionado= vista.getEstado().getSelectedItem().toString();
        resultado= modeloMunicipio.cargarDatosMunicipio(modeloCombo,nombreEstadoSeleccionado);
        
        vista.getMunicipio().setEnabled(true);
        vista.getParroquia().setEnabled(false);
//        vista.getParroquia().setSelectedIndex(0);
    }
    
    public void ListarParroquia(DefaultComboBoxModel modeloCombo){
        boolean resultado= false;
        vista.getParroquia().setEnabled(true);
        ModeloParroquia modeloParroquia = new ModeloParroquia();
        String nombreMunicipioSeleccionado= vista.getMunicipio().getSelectedItem().toString();
//        System.out.println("Nombre: "+nombreMunicipioSeleccionado);
        resultado= modeloParroquia.cargarDatosParroquia(modeloCombo,nombreMunicipioSeleccionado);
    }
    
    public void Listartipoempleado(DefaultComboBoxModel modeloCombo){
        boolean resultado= false;
        ModeloTipoEmpleado modelotipoempleado = new ModeloTipoEmpleado();
        resultado= modelotipoempleado.Listartipoempleado(modeloCombo);
     }
    
    public void habilitarcampos(){
         vista.getEstado().setEnabled(true);
     }
    
    public void habilitarempleado(){
         vista.getCedul().setEnabled(true);
         vista.getNombre().setEnabled(true);
         vista.getApellido().setEnabled(true);
         vista.getM().setEnabled(true);
         vista.getF().setEnabled(true);
         vista.getFechanacimiento().setEnabled(true);
         vista.getTelefon().setEnabled(true);
         vista.getEstado().setEnabled(true);
         vista.getDireccion().setEnabled(true);
         vista.getCondicionempleado().setEnabled(true);
         vista.getTpoempleado().setEnabled(true);
         vista.getCampoadicional().setEnabled(true);
         vista.getExpediente().setEnabled(false);
         vista.getBtonafiliado().setEnabled(false);
         vista.getParentesco().setEnabled(false);
         vista.getCedulaafiliado1().setEnabled(false);
     }
    
    public void habilitarestudiante(){
         vista.getCedul().setEnabled(true);
         vista.getNombre().setEnabled(true);
         vista.getApellido().setEnabled(true);
         vista.getM().setEnabled(true);
         vista.getF().setEnabled(true);
         vista.getFechanacimiento().setEnabled(true);
         vista.getTelefon().setEnabled(true);
         vista.getEstado().setEnabled(true);
         vista.getDireccion().setEnabled(true);
         vista.getCampoadicional().setEnabled(true);
         vista.getExpediente().setEnabled(true);
         vista.getBtonafiliado().setEnabled(false);
         vista.getParentesco().setEnabled(false);
         vista.getCondicionempleado().setEnabled(false);
         vista.getTpoempleado().setEnabled(false);
         vista.getCedulaafiliado1().setEnabled(false);
     }
    
    public void habilitarfamiliar(){
         vista.getCedul().setEnabled(true);
         vista.getNombre().setEnabled(true);
         vista.getApellido().setEnabled(true);
         vista.getM().setEnabled(true);
         vista.getF().setEnabled(true);
         vista.getFechanacimiento().setEnabled(true);
         vista.getTelefon().setEnabled(true);
         vista.getEstado().setEnabled(true);
         vista.getDireccion().setEnabled(true);
         vista.getCampoadicional().setEnabled(true);
         vista.getBtonafiliado().setEnabled(true);
         vista.getParentesco().setEnabled(true);
         vista.getExpediente().setEnabled(false);
         vista.getCondicionempleado().setEnabled(false);
         vista.getTpoempleado().setEnabled(false);
         vista.getCedulaafiliado1().setEnabled(true);
         
     }
    
    public void habilitarcomunida(){
         vista.getCedul().setEnabled(true);
         vista.getNombre().setEnabled(true);
         vista.getApellido().setEnabled(true);
         vista.getM().setEnabled(true);
         vista.getF().setEnabled(true);
         vista.getFechanacimiento().setEnabled(true);
         vista.getTelefon().setEnabled(true);
         vista.getEstado().setEnabled(true);
         vista.getDireccion().setEnabled(true);
         vista.getCampoadicional().setEnabled(true);
         vista.getBtonafiliado().setEnabled(false);
         vista.getParentesco().setEnabled(false);
         vista.getExpediente().setEnabled(false);
         vista.getTpoempleado().setEnabled(false);
         vista.getCondicionempleado().setEnabled(false);
         vista.getCedulaafiliado1().setEnabled(false);
     }
    
    public void ingresarempleado() throws SQLException{
        String tipopaciente = vista.getTipopaciente().getSelectedItem().toString();
        ModeloTipoPersona tipopersona  = new ModeloTipoPersona();
        int idtipopersona = 0;
        try {
            idtipopersona = tipopersona.capturarid(tipopaciente);
            System.out.println("Tipo de persona: "+idtipopersona);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         String parroquia = vista.getParroquia().getSelectedItem().toString();
         ModeloParroquia parroqui = new ModeloParroquia();
         int idparroquia = 0;
        try {
            idparroquia = parroqui.capturaridparroquia(parroquia);
            System.out.println("Parroquia: "+idtipopersona);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         String tipoempleado = vista.getTpoempleado().getSelectedItem().toString();
         ModeloTipoEmpleado tipoemplead = new ModeloTipoEmpleado();
         int idtipoempleado = 0;
         try {
            idtipoempleado = tipoemplead.capturaridtipoempleado(tipoempleado);
            System.out.println("Tipo empleado: "+idtipopersona);
        } catch (SQLException e) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, e);
        }
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        long cedula = vista.getCedula();
        System.out.println("La cédula a ingresar: "+cedula);
        String sex = capturarsexo();   
         Date fechacapturada =  vista.getFechanacimiento().getDate();
      
        long telefono;
        telefono = vista.getTelefono();
        String direccion = vista.getDireccion().getText();
        String condempleado = vista.getCondicionempleado().getText();
            String campoadicional;
            campoadicional = vista.getCampoadicional().getText();

       
        modelo = new ModeloPaciente(cedula, nombre, apellido, sex, fechacapturada,telefono, direccion, idtipopersona, idparroquia);
        boolean registrar = modelo.registrar();
        
        if (registrar)
        {
            modeloempleado = new ModeloEmpleado(condempleado,idtipoempleado,cedula);        
            boolean registrarempleado = modeloempleado.incluirModelo();
            if(registrarempleado)
            { 
                boolean beneficiario = true;
                modelo  = new ModeloPaciente(cedula,campoadicional, beneficiario);
                boolean registranenpaciente = modelo.registrarpaciente();
                if(registranenpaciente)
                {
                    String leyenda = "El empleado "+nombre+" "+apellido+" ha sido registrado exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(green);
                    limpiar();
                }else
                {
                    JOptionPane.showMessageDialog(new JFrame(),"No se registro en paciente","Incluir",JOptionPane.ERROR_MESSAGE);
                }
                
            }else
            {
                JOptionPane.showMessageDialog(new JFrame(),"no incluido en empleado","Incluir",JOptionPane.ERROR_MESSAGE);
            }
        }else
        {
            JOptionPane.showMessageDialog(new JFrame(),"no incluido en persona","Incluir",JOptionPane.ERROR_MESSAGE);
        }
        
     }
    
    public void ingresarestudiante() throws SQLException{
        String tipopaciente = vista.getTipopaciente().getSelectedItem().toString();
        ModeloTipoPersona tipopersona  = new ModeloTipoPersona();
        int idtipopersona = 0;
        try {
            idtipopersona = tipopersona.capturarid(tipopaciente);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         String parroquia = vista.getParroquia().getSelectedItem().toString();
         ModeloParroquia parroqui = new ModeloParroquia();
         int idparroquia = 0;
        try {
            idparroquia = parroqui.capturaridparroquia(parroquia);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        long cedula = vista.getCedula();
        String sex = capturarsexo();   
        Date fechacapturada =  vista.getFechanacimiento().getDate();
      
        long telefono;
        telefono = vista.getTelefono();

       
        String direccion = vista.getDireccion().getText();
        String campoadicional = vista.getCampoadicional().getText();
        int expediente = Integer.parseInt(vista.getExpediente().getText());
        modelo = new ModeloPaciente(cedula, nombre, apellido, sex, fechacapturada,telefono, direccion, idtipopersona, idparroquia);
        
        boolean ingresoestudiante = modelo.registrar();
        
        if(ingresoestudiante){
            boolean beneficiario = true;
            modelo  = new ModeloPaciente(cedula,expediente,campoadicional,beneficiario);
            boolean registranenpaciente = modelo.registrarpaciente();
                if(registranenpaciente)
                {
                    String leyenda = "El estudiante "+nombre+" "+apellido+" ha sido registrado exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(green);
                    limpiar();
                }else{
                    String leyenda = "¡OH!  ha ocurrido un error en el registro de paciente, comuníquese con el administrador"; 
                    JOptionPane.showMessageDialog(principal, leyenda);
                }
        }else{
            String leyenda = "El paciente " +nombre+ " " +apellido+ ", ya ha sido registrado anteriormente";
            vista.getLeyenda().setText(leyenda);
        }
     }
    
    public void ingresarcomunidad() throws SQLException{
        String tipopaciente = vista.getTipopaciente().getSelectedItem().toString();
        ModeloTipoPersona tipopersona  = new ModeloTipoPersona();
        int idtipopersona = 0;
        try {
            idtipopersona = tipopersona.capturarid(tipopaciente);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String parroquia = vista.getParroquia().getSelectedItem().toString();
        ModeloParroquia parroqui = new ModeloParroquia();
        int idparroquia = 0;
        try {
            idparroquia = parroqui.capturaridparroquia(parroquia);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        long cedula = vista.getCedula();
        String sex = capturarsexo();   
        Date fechacapturada =  vista.getFechanacimiento().getDate();
      
        long telefono;
        telefono = vista.getTelefono();
        String direccion = vista.getDireccion().getText();
        String campoadicional = vista.getCampoadicional().getText();
       
        modelo = new ModeloPaciente(cedula, nombre, apellido, sex, fechacapturada,telefono, direccion, idtipopersona, idparroquia);
        
        boolean ingresocomunidad = modelo.registrar();
        if(ingresocomunidad)
        {
            boolean beneficiario = false;
            modelo  = new ModeloPaciente(cedula,campoadicional,beneficiario);
            boolean registranenpaciente = modelo.registrarpaciente();
                if(registranenpaciente)
                {
                    String leyenda = "La persona, "+nombre+" "+apellido+" ha sido registrado exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(green);
                    limpiar();
                }else
                {
                    String leyenda = "La persona "+nombre+" "+apellido+" no ha sido registrado exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(red);
                }
        }
     }
    
    public void ingresarfamiliar() throws SQLException{
        String tipopaciente = vista.getTipopaciente().getSelectedItem().toString();
        ModeloTipoPersona tipopersona  = new ModeloTipoPersona();
        int idtipopersona = 0;
        try {
            idtipopersona = tipopersona.capturarid(tipopaciente);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        String parroquia = vista.getParroquia().getSelectedItem().toString();
        ModeloParroquia parroqui = new ModeloParroquia();
        int idparroquia = 0;
        try {
            idparroquia = parroqui.capturaridparroquia(parroquia);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        long cedula = vista.getCedula();
        String sex = capturarsexo();   
        Date fechacapturada = vista.getFechanacimiento().getDate();
      
        long telefono;
        telefono = vista.getTelefono();
        String direccion = vista.getDireccion().getText();
        String campoadicional = vista.getCampoadicional().getText();
       
        Long cedulaafiliado  = vista.getCedulaafiliado();
        String parentensco  = vista.getParentesco().getSelectedItem().toString();
        modelo = new ModeloPaciente(cedula, nombre, apellido, sex, fechacapturada,telefono, direccion, idtipopersona, idparroquia);
        
        modelo.setCampoAdicional(campoadicional);
        modelo.setBeneficiario(true);
      
        
        boolean registro = modelo.registrarfamiliar(vista.getParentesco().getSelectedItem().toString(), cedulaafiliado);
        
        if(registro) {
        	String Leyenda = "Se ha registrado exitosamente al familiar";
        	vista.getLeyenda().setText(Leyenda);
        	this.limpiar();
        }else {
        	String Leyenda = "No tuvo exito el registro del familiar, por favor verifique";
        	vista.getLeyenda().setText(Leyenda);
        }
     }
    
    public void modificarempleado()throws SQLException{
        String tipopaciente = vista.getTipopaciente().getSelectedItem().toString();
        ModeloTipoPersona tipopersona  = new ModeloTipoPersona();
        int idtipopersona = 0;
        try {
            idtipopersona = tipopersona.capturarid(tipopaciente);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         String parroquia = vista.getParroquia().getSelectedItem().toString();
         ModeloParroquia parroqui = new ModeloParroquia();
         int idparroquia = 0;
        try {
            idparroquia = parroqui.capturaridparroquia(parroquia);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         String tipoempleado = vista.getTpoempleado().getSelectedItem().toString();
         ModeloTipoEmpleado tipoemplead = new ModeloTipoEmpleado();
         int idtipoempleado = 0;
         try {
            idtipoempleado = tipoemplead.capturaridtipoempleado(tipoempleado);
            System.out.println("Tipo empleado: "+idtipoempleado);
        } catch (SQLException e) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, e);
        }
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        long cedula = vista.getCedula();
        System.out.println("La cédula a ingresar: "+cedula);
        String sex = capturarsexo();   
        Date fechacapturada = vista.getFechanacimiento().getDate();
      
        long telefono;
        telefono = vista.getTelefono();
        String direccion = vista.getDireccion().getText();
        String condempleado = vista.getCondicionempleado().getText();
            String campoadicional;
            campoadicional = vista.getCampoadicional().getText();

       
        modelo = new ModeloPaciente(cedula, nombre, apellido, sex,fechacapturada,telefono, direccion, idtipopersona, idparroquia);
        boolean modificar = modelo.modificarModelo();
        
        if (modificar)
        {
            modeloempleado = new ModeloEmpleado(condempleado,idtipoempleado,cedula);        
            boolean modificarempleado = modeloempleado.modificarempleado();
            if(modificarempleado){ 
                boolean beneficiario = true;
                modelo  = new ModeloPaciente(cedula,campoadicional, beneficiario);
                boolean modificarpaciente = modelo.modificar();
                if(modificarpaciente){
                    String leyenda = "El empleado "+nombre+" "+apellido+" ha sido Modificado exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(green);
                    limpiar();
                }else
                {
                    JOptionPane.showMessageDialog(new JFrame(),"No se modificó en paciente","Incluir",JOptionPane.ERROR_MESSAGE);
                }
                
            }else
            {
                JOptionPane.showMessageDialog(new JFrame(),"No modificado en empleado","Incluir",JOptionPane.ERROR_MESSAGE);
            }
        }else
        {
            JOptionPane.showMessageDialog(new JFrame(),"No modificado en persona","Incluir",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void modificarfamiliar() throws SQLException{
        String tipopaciente = vista.getTipopaciente().getSelectedItem().toString();
        ModeloTipoPersona tipopersona  = new ModeloTipoPersona();
        int idtipopersona = 0;
        try {
            idtipopersona = tipopersona.capturarid(tipopaciente);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        String parroquia = vista.getParroquia().getSelectedItem().toString();
        ModeloParroquia parroqui = new ModeloParroquia();
        int idparroquia = 0;
        try {
            idparroquia = parroqui.capturaridparroquia(parroquia);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        long cedula = vista.getCedula();
        String sex = capturarsexo();   
        Date fechacapturada =  vista.getFechanacimiento().getDate();
      
        long telefono;
        telefono = vista.getTelefono();
        String direccion = vista.getDireccion().getText();
        String campoadicional = vista.getCampoadicional().getText();
       
        Long cedulaafiliado  = vista.getCedulaafiliado();
        String parentensco  = vista.getParentesco().getSelectedItem().toString();
        modelo = new ModeloPaciente(cedula, nombre, apellido, sex, fechacapturada,telefono, direccion, idtipopersona, idparroquia);
        
        boolean modificar = modelo.modificarModelo();
        if(modificar)
        {
            boolean beneficiario = true;
            modelo  = new ModeloPaciente(cedula,campoadicional,beneficiario);
            boolean registranenpaciente = modelo.modificar();
                if(registranenpaciente){
                    boolean registrarenfamilia = modelo.modificarfamiliar(parentensco, cedulaafiliado, cedula);
                    if (registrarenfamilia) {
                        String leyenda = "La persona, "+nombre+" "+apellido+" ha sido modificada exitosamente";
                        vista.getLeyenda().setText(leyenda);
                        vista.getLeyenda().setForeground(green);
                        limpiar();
                    }else{
                        String leyenda = "La persona "+nombre+" "+apellido+" no ha sido modificada exitosamente con el familiar";
                        vista.getLeyenda().setText(leyenda);
                        vista.getLeyenda().setForeground(red);
                    }
                }else
                {
                    String leyenda = "La persona "+nombre+" "+apellido+" no ha sido modificada exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(red);
                }
        }
    }
    
    public void modificarestudiante()throws SQLException{
         String tipopaciente = vista.getTipopaciente().getSelectedItem().toString();
        ModeloTipoPersona tipopersona  = new ModeloTipoPersona();
        int idtipopersona = 0;
        try {
            idtipopersona = tipopersona.capturarid(tipopaciente);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         String parroquia = vista.getParroquia().getSelectedItem().toString();
         ModeloParroquia parroqui = new ModeloParroquia();
         int idparroquia = 0;
        try {
            idparroquia = parroqui.capturaridparroquia(parroquia);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        long cedula = vista.getCedula();
        String sex = capturarsexo();   
        Date fechacapturada =  vista.getFechanacimiento().getDate();
      
        long telefono;
        telefono = vista.getTelefono();
        String direccion = vista.getDireccion().getText();
        String campoadicional = vista.getCampoadicional().getText();
        int expediente = Integer.parseInt(vista.getExpediente().getText());
        modelo = new ModeloPaciente(cedula, nombre, apellido, sex, fechacapturada,telefono, direccion, idtipopersona, idparroquia);    
        boolean ingresoestudiante = modelo.modificarModelo();
        if(ingresoestudiante){
            boolean beneficiario = true;
            modelo  = new ModeloPaciente(cedula,expediente,campoadicional,beneficiario);
            boolean registranenpaciente = modelo.modificarestudiante();
                if(registranenpaciente)
                {
                    String leyenda = "El estudiante "+nombre+" "+apellido+" ha sido modificad@ exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(green);
                    limpiar();
                }else
                {
                    String leyenda = "El estudiante "+nombre+" "+apellido+" no ha sido modificad@ exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(red);
                }
        }
    }
    
    public void modificarcomunidad()throws SQLException{
         String tipopaciente = vista.getTipopaciente().getSelectedItem().toString();
        ModeloTipoPersona tipopersona  = new ModeloTipoPersona();
        int idtipopersona = 0;
        try {
            idtipopersona = tipopersona.capturarid(tipopaciente);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        String parroquia = vista.getParroquia().getSelectedItem().toString();
        ModeloParroquia parroqui = new ModeloParroquia();
        int idparroquia = 0;
        try {
            idparroquia = parroqui.capturaridparroquia(parroquia);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String nombre = vista.getNombre().getText();
        String apellido = vista.getApellido().getText();
        long cedula = vista.getCedula();
        String sex = capturarsexo();   
        Date fechacapturada =  vista.getFechanacimiento().getDate();
      
        long telefono;
        telefono = vista.getTelefono();

       
        String direccion = vista.getDireccion().getText();
        String campoadicional = vista.getCampoadicional().getText();
       
        modelo = new ModeloPaciente(cedula, nombre, apellido, sex, fechacapturada,telefono, direccion, idtipopersona, idparroquia);
        
        boolean ingresocomunidad = modelo.modificarModelo();
        if(ingresocomunidad)
        {
            boolean beneficiario = false;
            modelo  = new ModeloPaciente(cedula,campoadicional,beneficiario);
            boolean registranenpaciente = modelo.modificar();
                if(registranenpaciente)
                {
                    String leyenda = "La persona, "+nombre+" "+apellido+" ha sido modificad@ exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(green);
                    limpiar();
                }else
                {
                    String leyenda = "La persona "+nombre+" "+apellido+" no ha sido modificad@ exitosamente";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(red);
                }
        }
    }
    
    public String capturarsexo(){
         String sexo = null;
         
          for (Enumeration<AbstractButton > buttons = vista.getSexo().getElements(); buttons.hasMoreElements();)
            {
                   AbstractButton button = buttons.nextElement();
                  if(button.isSelected())
                    {
                       sexo = button.getText();

                   }
             } 
          return sexo;
     }
    
    public void limpiar(){
        vista.getTipopaciente().setSelectedItem("Seleccione");
        vista.getCedul().setText("");
        vista.getNombre().setText("");
        vista.getApellido().setText("");
        vista.getM().setSelected(false);
        vista.getF().setSelected(false);
        vista.getFechanacimiento().setDate(null);
        vista.getTelefon().setText("");
        vista.getEstado().setSelectedIndex(0);
        vista.getMunicipio().setSelectedIndex(0);
        vista.getParroquia().setSelectedIndex(0);
        vista.getDireccion().setText("");
        vista.getCondicionempleado().setText("");
        vista.getTpoempleado().setSelectedIndex(0);
        vista.getCampoadicional().setText("");
        vista.getExpediente().setText("");
        vista.getParentesco().setSelectedItem("Seleccione");
        vista.getCedulaafiliado1().setText("");
        vista.getNombAfiliado().setText("");
        vista.getApeAfiliado().setText("");
        vista.getTpoempaf().setText("");
        vista.getCondicionfamiliar().setText("");
        
        vista.getCedul().setEnabled(false);
        vista.getNombre().setEnabled(false);
        vista.getApellido().setEnabled(false);
        vista.getM().setEnabled(false);
        vista.getF().setEnabled(false);
        vista.getFechanacimiento().setEnabled(false);
        vista.getTelefon().setEnabled(false);
        vista.getEstado().setEnabled(false);
        vista.getMunicipio().setEnabled(false);
        vista.getParroquia().setEnabled(false);
        vista.getDireccion().setEnabled(false);
        vista.getCondicionempleado().setEnabled(false);
        vista.getTpoempleado().setEnabled(false);
        vista.getCampoadicional().setEnabled(false);
        vista.getExpediente().setEnabled(false);
        vista.getBtonafiliado().setEnabled(false);
        vista.getParentesco().setEnabled(false);
        vista.getCedulaafiliado1().setEnabled(false);
         
     }
    
    public void Capturarafiliado(String captura){
         this.cedul = Long.parseLong(captura);

           modelo = new ModeloPaciente();
           boolean encontrado = modelo.consultarafiliado(cedul);
           if (encontrado){
               try {
             //  this.afiliados.dispose();
               this.llenarcampoafiliado();
               } catch (Exception e) {
                   JOptionPane.showMessageDialog(principal,"ERROR: "+e.getMessage());
               }
            }else{
//                .getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
                
     
//            int id = (int) cat.getCatpatologia().getValueAt(fila, 0);
            
//            Object[] datos = control.consultarporID(id);
//            jcombonacion.setSelectedItem(datos[0].toString());
//           txtcedula.setText(datos[1].toString());
//            txtnombre.setText(datos[2].toString());
//            txtapellido.setText(datos[3].toString());
//            jcombosexo.setSelectedItem(datos[4].toString());
//           txtdireccion.setText(datos[5].toString());
//            jdatefecha.setDate((Date) datos[6]);
    }
    
    private void llenarcampoafiliado(){
        this.habilitarfamiliar();
        String cedulaafiliado = Long.toString(modelo.getCedulaafiliado());
        this.vista.getCedulaafiliado1().setText(cedulaafiliado);
        this.vista.getNombAfiliado().setText(modelo.getNombreafiliado());
        this.vista.getApeAfiliado().setText(modelo.getApellidoafiliado());
        this.vista.getCondicionfamiliar().setText(modelo.getCondicionempleado());
        this.vista.getTpoempaf().setText(modelo.getTipoempleado());       
    }
    
    private boolean validarfechanacimiento(){
        boolean siesmayor = false;
            Calendar fechanac = vista.getFechanacimiento().getCalendar();
            int edad = this.calcularedad(fechanac);
            System.out.println(edad);
            if(edad >= 18){
                siesmayor = true;
            }
        return siesmayor;
    }
    
    private int calcularedad(Calendar fechanac){
        Calendar today = Calendar.getInstance();
        int ano = today.get(Calendar.YEAR) - fechanac.get(Calendar.YEAR);
        int mes = today.get(Calendar.MONTH) - fechanac.get(Calendar.MONTH);
        int dia = today.get(Calendar.DAY_OF_MONTH) - fechanac.get(Calendar.DAY_OF_MONTH);
        
        if(mes < 0 || (mes == 0 && dia < 0)){
            ano -= 1;
        }
        return ano;
    }
    
    private boolean validar(){
        boolean validar = false;
        Date fech = new Date();

        Date fechanac = vista.getFechanacimiento().getDate();
        DateFormat fech1 = new SimpleDateFormat("dd/MM/yyyy");
        String fecha1 = fech1.format(fech);
        String fecha2 = fech1.format(fechanac);
        String sexo = this.capturarsexo();
        try {
        if (vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("Seleccione")) {
            String leyenda = "Es necesario seleccionar el tipo de persona";
            vista.getLeyenda().setText(leyenda);
            vista.getLeyenda().setForeground(orange);
            
        }else if (vista.getCedula() < 8) {
            String leyenda = "Es necesario ingresar una cédula valida";
            vista.getLeyenda().setText(leyenda);
            vista.getLeyenda().setForeground(orange);
        }
        else if(vista.getCedul().getText().contains(" ")){
            String leyenda = "No puede regitrar espacios en una cédula";
            vista.getLeyenda().setText(leyenda);
            vista.getLeyenda().setForeground(orange);
        }
        else if(vista.getNombre().getText().isEmpty())
        {
            this.espacioblancoNombre();
        }
        else if(vista.getNombre().getText().length() < 3) {
           this.menorletrasNombre();
        }
        else if(vista.getNombre().getText().contains("  "))
        {
            this.parespaciosNombre();
        }
        
        else if (vista.getApellido().getText().isEmpty())
        {
            this.espacioblancoApellido();
        }
        else if (vista.getApellido().getText().length() <3) {
            this.menorletrasApellido();
        }
        else if (vista.getApellido().getText().contains("  "))
        {
            this.parespaciosApellido();
        }
        else if (sexo == null){
            String leyenda = "Es necesario seleccionar un sexo";
            vista.getLeyenda().setText(leyenda);
        }else if(fechanac.after(fech) || fecha1.equals(fecha2)){
            String leyenda = "Es necesario Ingresar la fecha de nacimiento y que la misma sea menor a la actual...";
            vista.getLeyenda().setText(leyenda);
        }else if(this.validafechanac && (this.validarfechanacimiento() == false)){
                String leyenda = "El paciente debe ser mayor de edad";
                vista.getLeyenda().setText(leyenda);
        }else if(vista.getTelefono() < 11){
            String leyenda = "Ingrese un número de teléfono...";
            vista.getLeyenda().setText(leyenda);
        }else if (vista.getParroquia().getSelectedItem().toString() == null){
            String leyenda = "Es necesario seleccionar la parroquia";
            vista.getLeyenda().setText(leyenda);
        }else if(vista.getDireccion().getText().length() < 10 || vista.getDireccion().getText().isEmpty()){
            String leyenda = "Es necesario ingresar la dirección";
            vista.getLeyenda().setText(leyenda);
            vista.getLeyenda().setForeground(orange);
        }
        else if (vista.getDireccion().getText().contains("  "))
        {  String leyenda = "No puede escribir varios espacios en blanco en dirección";
            vista.getLeyenda().setText(leyenda);
            vista.getLeyenda().setForeground(orange);
        }
        else if(vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("EMPLEADO")){
            if (vista.getCondicionempleado().getText().length() < 3 || vista.getCondicionempleado().getText().isEmpty()) {
                String leyenda = "Es necesario ingresar la condición del empleado...";
                vista.getLeyenda().setText(leyenda);    
            }else if (vista.getTpoempleado().getSelectedItem().toString().equalsIgnoreCase("Seleccione")){
                String leyenda = "Es necesario seleccionar el tipo de empleado...";
                vista.getLeyenda().setText(leyenda);
            }
            validar = true;
        }else if (vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("ESTUDIANTE")){
            if (vista.getExpediente().getText().length() < 4 || vista.getExpediente().getText().isEmpty()) {
                String leyenda = "Es necesario ingresar el número de expediente...";
                vista.getLeyenda().setText(leyenda);
            }
            validar = true;
        }else if(vista.getTipopaciente().getSelectedItem().toString().equalsIgnoreCase("FAMILIAR")){
            if(vista.getCedulaafiliado() < 8 ){
                String leyenda = "Es necesario ingresar al familiar afiliado...";
                vista.getLeyenda().setText(leyenda);
            }else if(vista.getNombAfiliado().getText().isEmpty()){
                String leyenda = "Es necesario que este el afiliado asignado al registro...";
                vista.getLeyenda().setText(leyenda);
            }else if(vista.getParentesco().getSelectedItem().toString().equalsIgnoreCase("Seleccione")){
                String leyenda = "Es necesario seleccionar el parentesco";
                vista.getLeyenda().setText(leyenda);
            }
            validar = true;
        }else{
            validar = true;
        }
        } catch (Exception e3) {
            JOptionPane.showMessageDialog(principal, "Hey! hacen faltan campos importantes evita estos cuadros de diálogos");
            System.out.println("ERROR: " + e3.getMessage());
        }
      
    return validar;
    }
    
    private void buscarafiliado(){
        
        Long cedula =  vista.getCedulaafiliado();
        modelo = new ModeloPaciente();
        boolean encontrado = modelo.consultarafiliado(cedula);
        if (encontrado) {
            vista.getNombAfiliado().setText(modelo.getNombreafiliado());
            vista.getApeAfiliado().setText(modelo.getApellidoafiliado());
            vista.getTpoempaf().setText(modelo.getTipoempleado());
            vista.getCondicionfamiliar().setText(modelo.getCondicionempleado());
            vista.getNombAfiliado().setEnabled(false);
            vista.getApeAfiliado().setEnabled(false);
            vista.getTpoempaf().setEnabled(false);
            vista.getCondicionfamiliar().setEnabled(false);
        }else{
            String Leyenda  = "No fue encontrado el afiliado, por favor intente con otro número de cédula";
            JOptionPane.showMessageDialog(principal, Leyenda);
        }
    }   
    
    public void Capturardatos(int opction , long cedulaa){
        if(opction == 1 ){
            modelo.setCedula(cedulaa);
            boolean encontrado = modelo.capturardatospaciente();
            if (encontrado){
            	vista.dispose();
            	vista  = new Paciente(principal,false);
                vista.setController(this);
                String tipopersona = modelo.getTipopersona();
                vista.getTipopaciente().setSelectedItem(tipopersona);
                vista.getTipopaciente().setEnabled(false);
                String dni = Long.toString(modelo.getCedula());
                
              if (dni.length() < 8) {
                  String num = "0";
                  String cedula99 = num+dni;
                  vista.getCedul().setText(cedula99);
              }else{
                  vista.getCedul().setText(dni); 
              }
                vista.getCedul().setEnabled(false);
                vista.getNombre().setText(modelo.getNombre());
                vista.getApellido().setText(modelo.getApellido());
                vista.getFechanacimiento().setDate(modelo.getFechanacimiento());
                vista.getTelefon().setText(Long.toString(modelo.getTelefono()));
                vista.getDireccion().setText(modelo.getDireccion());
               
                
                String sexo = modelo.getSexo();
                if (sexo.equals("M")) {
                   vista.getM().setSelected(true);
               }else{
                    vista.getF().setSelected(true);
                }
                int idparroquia = modelo.getIdparroquia();
                parroquia   = new ModeloParroquia();
                estado      = new ModeloEstado();
                
                String nombestado = estado.capturarestado(idparroquia);
                String municipio = parroquia.capturarmunicipio(idparroquia);
                String nombmunicipio = municipio; 
                DefaultComboBoxModel<String> listaestado = new DefaultComboBoxModel<String>();
                vista.getEstado().setModel(listaestado);
                boolean resultado = false;
                vista.getEstado().addItem(nombestado);
                vista.getMunicipio().setSelectedItem(nombmunicipio);
                resultado = estado.cargarDatosEstado(listaestado);
               
//                usuario.getGrabar().setEnabled(true);
//                usuario.getEliminar().setEnabled(true);
                if(modelo.getTipopersona().equalsIgnoreCase("estudiante")){
                    String expediente = Integer.toString(modelo.getExpediente());
                    vista.getExpediente().setText(expediente);
                }else if(modelo.getTipopersona().equalsIgnoreCase("familiar")){
                    
                    ModeloPaciente modelo1 = new ModeloPaciente();
                    String cedulaafiliado = Long.toString(modelo.getCedulaafiliado());
                    vista.getCedulaafiliado1().setText(cedulaafiliado);
                    modelo1.capturardatosafiliado(modelo.getCedulaafiliado());
                    vista.getParentesco().setSelectedItem(modelo.getParentesco());
                    vista.getNombAfiliado().setText(modelo1.getNombreafiliado());
                    vista.getApeAfiliado().setText(modelo1.getApellidoafiliado());
                    vista.getTpoempaf().setText(modelo1.getTipoempleado());
                    vista.getCondicionfamiliar().setText(modelo1.getCondicionempleado());
                }else if(modelo.getTipopersona().equalsIgnoreCase("empleado")){
                    vista.getCondicionempleado().setText(modelo.getCondicionempleado());
                    vista.getTpoempleado().setSelectedItem(modelo.getTipoempleado());
                }
                    vista.getRegistrar().setEnabled(false);
                    catalogo.setVisible(false);
                     
                    vista.setVisible(true);
                   
                    vista.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }else{
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
        }else if(opction == 2){
            modelo.setCedula(cedulaa);
            boolean encontrado = modelo.capturardatospaciente();
            if (encontrado){
                catalogo1.dispose();
                long ced = modelo.getCedula();
                String dni = null;
                
                dni = Long.toString(ced);
                if(dni.length() < 8){
                    dni = "0"+dni;
                }
                vista.getCedulaafiliado1().setText(dni);
                vista.getNombAfiliado().setText(modelo.getNombre());
                vista.getApeAfiliado().setText(modelo.getApellido());
                vista.getTpoempaf().setText(modelo.getTipoempleado());
                vista.getCondicionfamiliar().setText(modelo.getCondicionempleado());
                vista.getParentesco().requestFocus();
            }else{
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            } 
        }
           
    }
    
    public void listarafiliado(){
        modelo = new ModeloPaciente();
        String[][] informacion =  modelo.listarafiliados();
        DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cédula","Nombre","Apellido","Tipo Empleado"});
    //    afiliados.getAfiliado().setModel(model);
    }  
    
    public void parespaciosNombre(){
     String leyenda = "¡No puede registrar varios espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
    
    public void menorletrasNombre(){
     String leyenda = "Asegúrese de que los campo Nombre no sean menor a 4 letras!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
    
    public void espacioblancoNombre(){
     String leyenda = "¡No puede registrar espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }  
    
    public void parespaciosApellido(){
     String leyenda = "¡No puede registras varios espacios en blancos en Apellido!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
    
    public void menorletrasApellido(){
     String leyenda = "Asegúrese de que los campo Apellido no sean menor a 2 letras!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
    
    public void espacioblancoApellido(){
     String leyenda = "¡No puede registras espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }  
    
    public void parespaciosDireccion(){
     String leyenda = "¡No puede registrar varios espacios en blancos en dirección!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
    
    @Override
    public void keyTyped(KeyEvent e) {
       Object origen = e.getSource();
       if(origen.equals(catalogo1.getTextbusqueda())){
            String caracter  = catalogo1.getTextbusqueda().getText();
            char c = e.getKeyChar();
            if(caracter.length() > 30){
            e.consume();
            }
       }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
            try {
                Component origen = e.getComponent();
                if(origen.equals(vista.getCedulaafiliado())){
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (vista.getCedulaafiliado() < 8) {
                            String leyenda = "Es necesario que ingreses el número de cédula del afiliado...";
                            vista.getLeyenda().setText(leyenda);
                        }else{
                            buscarafiliado();
                        } 
                }
            }
            } catch (NumberFormatException ex1) {
                System.out.println(ex1.getMessage());
            }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        Component origen = e.getComponent();
        if(origen.equals(catalogo.getTextbusqueda())){
             String busqueda = catalogo.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,catalogo.getCatpaciente());
        }else if(origen.equals(catalogo1.getTextbusqueda())){
            String busqueda = catalogo1.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,catalogo1.getCatpaciente());
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        Component origen = e.getComponent();
        if(origen.equals(catalogo.getCatpaciente())){
          if (e.getClickCount() == 2) {
            try{
                int fila = catalogo.getCatpaciente().getSelectedRow();
                int fila1 = catalogo.getCatpaciente().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) catalogo.getCatpaciente().getModel();
                long captura = Long.parseLong((String) modelotabla.getValueAt(fila1, 0));
                Capturardatos(1, captura);
            }catch(HeadlessException ex){
                System.out.println("Error: "+ex);
            }
            }  
        }else if(origen.equals(catalogo1.getCatpaciente())){
             if (e.getClickCount() == 2) {
            try{
                int fila = catalogo1.getCatpaciente().getSelectedRow();
                int fila1 = catalogo1.getCatpaciente().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) catalogo1.getCatpaciente().getModel();
                long captura = Long.parseLong((String) modelotabla.getValueAt(fila1, 0));
                Capturardatos(2, captura);
            }catch(HeadlessException ex){
                System.out.println("Error: "+ex);
            }
            }  
        }
}
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
       
    }    
}