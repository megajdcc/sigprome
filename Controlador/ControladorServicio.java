/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModelEstadosProces;
import Modelo.ModeloCita;
import Modelo.ModeloPaciente;
import Vista.Servicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import Modelo.ModeloTiposervicios;
import java.util.Date;
import Modelo.ModeloServicio;
import Modelo.OpcAdminis;
import Vista.Paciente;
import javax.swing.table.DefaultTableModel;
import Vista.Principal;
import Vista.ProcesoEstado;
import Vista.catpacientes;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import Vista.calendariocita;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author Jnatn'h
 */
public class ControladorServicio implements ActionListener, KeyListener,MouseListener, ListSelectionListener,ItemListener{
    
    DefaultTableModel dm;
    Servicio vista;
    catpacientes cat;
    ModeloServicio modelo;
    ModeloCita cita;
    private ModeloPaciente modelopacientes;
    private ProcesoEstado estatusopcion;
    private OpcAdminis opcionesadministrativas;
    private Principal principal;
    ModeloTiposervicios modelotiposervicio;
    calendariocita calendario;
    private ModelEstadosProces estadoproceso;
    boolean administ = false;
    public boolean cit  = false;
    boolean emerg = false;
    private int idcita;
    private String statu;
    
    /**
      * Constructor de la clase ControladorServicio 
      * @param vista
      * @param principal
     */
    public ControladorServicio(Servicio vista, java.awt.Frame principal){
        this.vista = vista;
        this.principal = (Principal) principal;
        modelotiposervicio = new ModeloTiposervicios();
        modelo = new ModeloServicio();
        cita = new ModeloCita();
        opcionesadministrativas = new OpcAdminis();
        modelopacientes = new ModeloPaciente();
        estadoproceso = new ModelEstadosProces();
         calendario = new calendariocita(principal, true);
        this.listarcitashoy();
        this.listaropcionesadministrativas();
        this.listaropcionesadministrativaspendientes();
        cat = new catpacientes(principal, true);
        cat.setController(this);
    }
    @SuppressWarnings("serial")
	private void listaropcionesadministrativaspendientes(){
     String[][] informacion  = modelo.Listaropcionesadministrativaspendientes();
        vista.getSolicitudes().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Cédula","Nombre","Tipo Paciente","Opción administrativa", "Estado del proceso"}) {
    boolean[] canEdit = new boolean [] {
        false,false,false,false,false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    vista.getSolicitudes().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void Listartiposervicio(DefaultComboBoxModel<String> modeloCombo){
        modelotiposervicio.Listartiposervicio(modeloCombo);
      }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();/// un objeto que recibe el objet que genera el evento

         if(event.equals(vista.getTiposervicio())){
              String captura;
            captura = (String) vista.getTiposervicio().getSelectedItem();
            if (null != captura) switch (captura) {
                case "CITA":
                    habilitarcita();
                    break;
                case "EMERGENCIA":
                    habilitar();
                    break;
                case "ADMINISTRATIVO":
                    habilitaradministrativo();
                    break;
                case "SELECCIONE":
                    desabilitar();
                    break;
                default:
                 habilitar();
                    break;
            }
         }else if(event.equals(vista.getRegistrar())){
            String captura;
            captura = (String) vista.getTiposervicio().getSelectedItem();
            if(captura.equalsIgnoreCase("Administrativo")){
                boolean validado = false;
                validado = this.validar(1);
                if(validado == false){
                    JOptionPane.showMessageDialog(principal, "Es necesario que seleccione al menos una opción administrativa");
                }else{
                   this.administ = true;
                   this.emerg = false;
                   this.cit = false;
                  try {
                    this.registrar();
                  } catch (SQLException ex) {
                    Logger.getLogger(ControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
                  }
                }
            }else if(captura.equalsIgnoreCase("emergencia")){
                boolean validado = false;
                validado = this.validar(2);
                if(validado == false){
                   JOptionPane.showMessageDialog(principal, "Es necesario que los campos requeridos estén seleccionados.");
                }else{
                    this.administ = false;
                    this.cit = false;
                    this.emerg = true;
                    try {
                        this.registrar();
                    } catch (SQLException e4) {
                          Logger.getLogger(ControladorServicio.class.getName()).log(Level.SEVERE, null, e4);
                    }
                }
            }else if(cit){
                boolean validado = validarprocesocita();
                 if(validado){
                     registrarprocesocita();
                 }
            }
         }else if(event.equals(vista.getSalir())){
            this.limpiar();
            vista.dispose();
         }else if(event.equals(vista.getNuevopaciente())){
             try {
                 Paciente paciente = new Paciente(principal, true, 1);
                 paciente.setVisible(true);
             } catch (Exception e2) {
                 JOptionPane.showMessageDialog(principal, "Error: "+e2.getMessage());
             }
         }else if(event.equals(vista.getConsultar())){
             try {
             if(vista.getTiposervicio().getSelectedItem().toString().equalsIgnoreCase("Administrativo")){
               this.ListarPacientesAdministrativos();
               cat.setVisible(true);
             }else{
                 this.ListarPacientes();
                 cat.setVisible(true);
             }
           
             } catch (Exception e3) {
                 JOptionPane.showMessageDialog(principal, "Error: "+e3.getMessage());
             } 
         }else if(event.equals(vista.getNuevacita())){
            
             calendario.setController(this);
             calendario.setVisible(true);
         }else if(event.equals(calendario.getSalircalendario())){
             calendario.dispose();
         }else if(e.getSource() == calendario.getAsignarCita()){
             
             boolean validado = this.validarfecha();
             if(validado){
                 Calendar fech = calendario.getCalendariocita().getSelectedDate();
                 Date fecha = fech.getTime();
                 Locale localidad = new Locale("es");
                 DateFormat da = new SimpleDateFormat("yyyy-MM-dd", localidad);
                 String fechaasignad = null;
                 fechaasignad = da.format(fecha);
                 System.out.println(fechaasignad);
                 boolean fechadisponible = this.verificarcita(fechaasignad);
                 if(fechadisponible){
                        String si = "SI";
                        String no = "NO";
                        Object[] options ={si,no}; 
                        String disponible = "Excelente la fecha "+fechaasignad+" está disponible. ¿Desea asignarla?";
                        int respuesta = JOptionPane.showOptionDialog(principal, disponible, "Patologia", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null, options); 
                        if (respuesta == 0) {
                            registrarcita(fechaasignad);
                        }
                 }else{
                     String disculpe = "Disculpe esta fecha está llena";
                     JOptionPane.showMessageDialog(principal, disculpe, "Fecha elegida llena", JOptionPane.WARNING_MESSAGE);
                 }
             } 
         }else if(event.equals(vista.getProcesarcita())){
              String si = "SI";
                        String no = "NO";
                        Object[] options ={si,no}; 
                        String disponible = "Excelente, el paciente viene por consulta médica?";
                        int respuesta = JOptionPane.showOptionDialog(principal, disponible, "Procesar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,null,null, options); 
                        if (respuesta == 0) {
                            procesarcitaconsulta();
                        }else{
                            procesarcitaadministrativo();
                        }
         }else if(event.equals(vista.getAnadir())){
          moverSeleccion(vista.getOpciones(), vista.getSeleccionada());
         }else if(event.equals(vista.getQuitar())){
          moverSeleccion(vista.getSeleccionada(), vista.getOpciones());
         }else if(event.equals(estatusopcion.getGrabar())){
           this.estadoproceso.capturaridseleccion(estatusopcion.getEstadoopcion().getSelectedItem().toString());
           boolean modificacion = modelo.modificaropcionservicio(this.opcionesadministrativas.getId(),estadoproceso.getId());
           if(modificacion){
             this.estatusopcion.dispose();
             String leyenda = "Se ha guardado el cambio exitosamente";
             vista.getLeyenda().setText(leyenda);
             listaropcionesadministrativaspendientes();
             vista.getBuscarestado().setText(modelopacientes.getNombre());
             this.ListadoBusqueda(modelopacientes.getNombre(), vista.getSolicitudes());
//             this.ListarPacientesAdministrativos();
//             this.limpiar();
           }
         }else if(event.equals(cat.getSalircat())){
             cat.dispose();
         }
         
         
         
    }
     private void moverSeleccion(JList origen, JList destino){

        //Se obitnen los modelos de "origen" y "destino" para poder utilizar el metodo 'addElement' y 'removeRange'
        DefaultListModel listaorigen = (DefaultListModel) origen.getModel();
        DefaultListModel listadestino = (DefaultListModel) destino.getModel();
        //Se almacenan los identificadores de las tablas seleccionadas
        int[] indice = origen.getSelectedIndices();
     
        //Se almacena los nombres de las tablas seleccionadas de la "Lista Roja"
      List selec = origen.getSelectedValuesList();
       byte i = 0;
       
       for(Object selecc: selec) {
         listadestino.addElement(selecc);
         i++;
       }
            if( indice.length > 0)
               //Se eliminan las tablas de la "Lista Roja" que ya han sido insertadas en la "Lista Verde"
               listaorigen.removeRange(indice[0], indice[i - 1]);
            else
               //Al no seleccionar un elemento de Origen y dar click en el boton ( < - > )
               JOptionPane.showMessageDialog(principal, "Seleccione un elemento de la lista", "ERROR !!!", JOptionPane.ERROR_MESSAGE);
    }
  
    private void procesarcitaadministrativo(){
         boolean validar = this.validarprocesocita();
          if(validar){
            long cedula = this.capturacedula();
            modelopacientes.setCedula(cedula);
            int idpaciente = 0;
            try {
                idpaciente = modelopacientes.capturarid();
            } catch (SQLException ex2) {
                System.out.println(ex2.getMessage());
            }
            int idcitaa = modelo.capturaridcita(idpaciente);
            boolean citaprocesada = modelo.procesarcitaconsulta(idcitaa);
            if(citaprocesada){
                String leyenda = "Cita terminada y asignada a la adminsitración";
                this.listarcitashoy();
                vista.getLeyenda().setText(leyenda);
                vista.getTiposervicio().setSelectedItem("ADMINISTRATIVO");
                vista.getProcesarcita().setEnabled(false);
                vista.geteliminarcita().setEnabled(false);
                vista.getNuevacita().setEnabled(false);
                vista.getOpciones().setEnabled(true);
                vista.getSeleccionada().setEnabled(true);
            }
                    
        }
    }
    private void procesarcitaconsulta(){
        boolean validar = this.validarprocesocita();
        if(validar){
            long cedula = this.capturacedula();
            modelopacientes.setCedula(cedula);
            int idpaciente = 0;
            try {
                idpaciente = modelopacientes.capturarid();
            } catch (SQLException ex2) {
                System.out.println(ex2.getMessage());
            }
            int idcitaa = modelo.capturaridcita(idpaciente);
            boolean citaprocesada = modelo.procesarcita(idcitaa);
            if(citaprocesada){
                String leyenda = "Cita terminada y asignada a consulta";
                vista.getTiposervicio().setSelectedItem("SELECCIONE");
                this.listarcitashoy();
                vista.getLeyenda().setText(leyenda);
                this.limpiar();
            }         
        }
    }
    private void registrarprocesocita(){
        ModeloTiposervicios tpservi = new ModeloTiposervicios();
        ModeloPaciente pa = new ModeloPaciente();
        int idtiposervicio = 0;
        try {
            idtiposervicio = tpservi.capturarid(vista.getTiposervicio().getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(ControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        pa.setCedula(this.capturacedula());
        int idpaciente = 0;
        try {
             idpaciente = pa.capturarid();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean registro =  modelo.registrarcita(this.idcita, idtiposervicio,idpaciente);
        if(registro){
            cit = false;
            String leyenda = "Cita Registrada con exito";
            this.listarcitashoy();
            vista.getTiposervicio().setSelectedItem("SELECCIONE");
            vista.getLeyenda().setText(leyenda);
            this.limpiar();
        }
    }
    private boolean  validarprocesocita(){
        boolean validado = false;
        if(vista.getNombre().getText().isEmpty()){
            validado = false;
        } else {
            validado = true;
        }
        return validado;
    }
    private void registrarcita(String fecha){
        cita.setFecha(fecha);
        boolean registro  = cita.nuevacita();
        if(registro){
            calendario.dispose();
            String leyenda = "Cita asignada";
            this.idcita = cita.getId();
            cit = true;
            JOptionPane.showMessageDialog(principal, leyenda);
            vista.getSalir().setEnabled(false);
            vista.getNuevacita().setEnabled(false);
            vista.getProcesarcita().setEnabled(false);
            vista.geteliminarcita().setEnabled(false);
        }
    }
    private boolean validarfecha(){
        boolean validado = false;
        Date fechaactuall = new Date();
        Calendar fech = calendario.getCalendariocita().getSelectedDate();
        Date fecha = fech.getTime();
        if(fecha.before(fechaactuall) || fecha.equals(fechaactuall)){
            String atencion = "Disculpe no puede asignar una cita en una fecha anterior o del mismo dia verifique";
            JOptionPane.showMessageDialog(principal, atencion,"Fecha no valida", JOptionPane.WARNING_MESSAGE);
        }else{
            validado = true;
        }
        return validado;
    }
    private boolean verificarcita(String fecha){
        boolean disponibilidad = false;
        cita.setFecha(fecha);
        disponibilidad = cita.verificarfecha();
        return disponibilidad;
    }
    public boolean validar(int tiposervicio){
        boolean validado = false;
        switch (tiposervicio) {
            case 1:
                boolean Administrativo = this.capturaradministrativo();
                if (this.capturacedula() < 8) {
                    validado = false;
                    JOptionPane.showMessageDialog(principal, "Disculpe es necesario que este el paciente seleccionado...");
                }else if(Administrativo){
                    validado = true;
                }else{
                    validado = false;
                }   break;
            case 2:
                if (vista.getCedula().getText().length() < 11){
                    validado = false;
                    JOptionPane.showMessageDialog(principal, "Disculpe es necesario que este el paciente seleccionado...");
                }else{
                    validado = true;
                }
                break;
            default:
                break;
        }
       
        
        return validado;
    }
    private void viewseleccionopcion(long cedula,String opcion){
    	
      opcionesadministrativas.setNombre(opcion);
      modelopacientes.setCedula(cedula);
      int idpaciente = 0;
      try {
        idpaciente = modelopacientes.capturarid();
      } catch (SQLException ex) {
        Logger.getLogger(ControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
      }
//      boolean capturado = estadoproceso.Capturarestado(opcionesadministrativas.getId(), idpaciente);
//     
//      if(capturado){
       boolean si = modelo.capturaridservicios(opcionesadministrativas.getId(),idpaciente);
        if(si){
     
       Runnable opciones = new OpcionesAdministrativas(this);
       Thread t = new Thread(opciones);
       t.setPriority(Thread.MAX_PRIORITY);
       t.start();
       
       vista.getBuscarestado().setText("");
        
      }else{
        String leyenda = "Estimado usuario hubo un error en el sistema por favor comuniquelo al administrador";
        JOptionPane.showMessageDialog(principal, leyenda);
      }
        
        
    }
    public void seleccionpaciente(long cedula){
        modelopacientes.setCedula(cedula);
        boolean capturado = modelopacientes.capturardatospaciente();
        if(capturado){
            cat.dispose();
            vista.getNombre().setText(modelopacientes.getNombre());
            vista.getApellido().setText(modelopacientes.getApellido());
            vista.getTipopaciente().setText(modelopacientes.getTipopersona());
            
            long cedula9 = modelopacientes.getCedula();
            
            String cedu = String.valueOf(cedula9);
            if (cedu.length() < 8) {
                String num = "0";
                String cedula99 = num+cedu;
                vista.getCedula().setText(cedula99);
            }else{
                vista.getCedula().setText(cedu); 
            }
            if(vista.getTiposervicio().getSelectedItem().toString().equals("ADMINISTRATIVO") || vista.getTiposervicio().getSelectedItem().toString().equals("CITA")){
                 this.verificarestado();
            }
           
        }
    }
    private void listarestatus(){
      JComboBox estatus = estatusopcion.getEstadoopcion();
      this.estadoproceso.ListarStatus(estatus,statu,this);
    }
    public void ListarPacientes(){
        String[][] informacion  = modelopacientes.ListarPacientes();
        DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cédula","Nombre","Apellido","Tipo Paciente"});
        cat.getCatpaciente().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Cédula","Nombre","Apellido","Tipo Paciente"}) {
    boolean[] canEdit = new boolean [] {
        false,false,false,false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getCatpaciente().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void ListarPacientesAdministrativos(){
        String[][] informacion  = modelopacientes.ListarPacientesAdministrativos();
        DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Cédula","Nombre","Apellido","Tipo Paciente"});
        cat.getCatpaciente().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Cédula","Nombre","Apellido","Tipo Paciente"}) {
    boolean[] canEdit = new boolean [] {
        false,false,false,false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getCatpaciente().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void listarcitashoy(){
        String[][] citasparahoy =  modelo.consultar();
        vista.getCitashoy().setModel(new javax.swing.table.DefaultTableModel(
    citasparahoy,
    new String [] {"Cédula","Nombre","Apellido","Tipo de Paciente","Fecha de Asignación"}) {
    boolean[] canEdit = new boolean [] {
        false,false,false,false,false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    vista.getCitashoy().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void habilitarcita(){
        vista.getMovimiento().setSelectedIndex(0);
        vista.getMovimiento().setEnabled(true);
        vista.getRegistrar().setEnabled(true); 
        vista.getConsultar().setEnabled(true);
        vista.getNuevopaciente().setEnabled(true);
        vista.getCedula().setEnabled(false);
        
        vista.getNuevacita().setEnabled(true);
        vista.getProcesarcita().setEnabled(false);
        vista.geteliminarcita().setEnabled(false);
        vista.getCitashoy().setEnabled(true);
        vista.getNuevacita().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vista.getProcesarcita().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vista.geteliminarcita().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//        vista.getReembolso().setEnabled(false);
//        vista.getEstudiosespeciales().setEnabled(false);
//        vista.getValidarreposo().setEnabled(false);
}
    public void habilitaradministrativo(){
     vista.getConsultar().setEnabled(true);
     vista.getRegistrar().setEnabled(true);
     vista.getNuevopaciente().setEnabled(true);
     vista.getCedula().setEnabled(false);
     vista.getMovimiento().setSelectedIndex(1);
     vista.getMovimiento().setEnabled(true);
     
//     vista.getReembolso().setEnabled(true);
//     vista.getEstudiosespeciales().setEnabled(true);
//     vista.getValidarreposo().setEnabled(true);
     vista.getNuevacita().setEnabled(false);
     vista.getProcesarcita().setEnabled(false);
     vista.geteliminarcita().setEnabled(false);
     vista.getCitashoy().setEnabled(false);
     
    }
    public void habilitarestado(){
    vista.getEstadoadministrativo().setVisible(true);
    vista.getEstadoadministrativo().setEnabled(true);
//    vista.getProcesado().setEnabled(true);
//    vista.getEspera().setEnabled(true);
//    vista.getFinalizado().setEnabled(true);
    }
    public void habilitar(){
     vista.getConsultar().setEnabled(true);
     vista.getRegistrar().setEnabled(true);
     vista.getNuevopaciente().setEnabled(true);
     vista.getCedula().setEnabled(false);
     
     vista.getNuevacita().setEnabled(false);
     vista.getProcesarcita().setEnabled(false);
     vista.geteliminarcita().setEnabled(false);
     vista.getCitashoy().setEnabled(false);
     
//     vista.getReembolso().setEnabled(false);
//     vista.getEstudiosespeciales().setEnabled(false);
//     vista.getValidarreposo().setEnabled(false);
     vista.getMovimiento().setEnabled(false);
     
}
    public void  desabilitar(){
     vista.getConsultar().setEnabled(false);
     vista.getRegistrar().setEnabled(false);
     vista.getNuevopaciente().setEnabled(false);
     vista.getCedula().setEnabled(false);
     vista.getNuevacita().setEnabled(false);
     vista.getProcesarcita().setEnabled(false);
     vista.geteliminarcita().setEnabled(false);
     vista.getCitashoy().setEnabled(false);
//     vista.getReembolso().setEnabled(false);
//     vista.getEstudiosespeciales().setEnabled(false);
//     vista.getValidarreposo().setEnabled(false);
     vista.getMovimiento().setEnabled(false);
    }
    public void desabilitaradministrativo(){
//     vista.getReembolso().setEnabled(false);
//     vista.getEstudiosespeciales().setEnabled(false);
//     vista.getValidarreposo().setEnabled(false);
    }
    private long capturacedula(){
        long cedula = 0;
        char cedul = vista.getCedula().getText().charAt(2);
        char cedul1 = vista.getCedula().getText().charAt(3);
        char cedul2 = vista.getCedula().getText().charAt(5);
        char cedul3 = vista.getCedula().getText().charAt(6);
        char cedul4 = vista.getCedula().getText().charAt(7);
        char cedul5 = vista.getCedula().getText().charAt(9);
        char cedul6 = vista.getCedula().getText().charAt(10);
        char cedul7 = vista.getCedula().getText().charAt(11);
        char data[] = {cedul,cedul1,cedul2,cedul3,cedul4,cedul5,cedul6,cedul7};
        String cedul8 = String.copyValueOf(data);
        if(cedul8.trim().isEmpty()){
          cedula = 0 ;
        }else{
          cedula = Long.parseLong(cedul8);
        }
        return cedula;
    }
    private boolean capturaradministrativo(){
      boolean val = false;
       DefaultListModel milista = (DefaultListModel) vista.getSeleccionada().getModel();
       if(milista.isEmpty()){
         val = false;
       }else{
         val = true;
       }
       return val;
    }
      private String[] capturaradministrativos(){
         DefaultListModel milista = (DefaultListModel) vista.getSeleccionada().getModel();
        String [] milist = new String[milista.getSize()];
       int cantidad = milista.getSize();
       byte i = 0;
       for(; i < cantidad; i++){
         milist[i] = milista.get(i).toString();
       }
       return milist;
    }
    private String capturarestado(){
         String administrativo = null;
          for (Enumeration<AbstractButton > buttons = vista.getEstadadministrativo().getElements(); buttons.hasMoreElements();)
            {
                   AbstractButton button = buttons.nextElement();
                  if(button.isSelected())
                    {
                       administrativo = button.getText();
                   }
             } 
          return administrativo;
    }
    public void limpiar(){
        vista.getCedula().setText("");
        vista.getNombre().setText("");
        vista.getApellido().setText("");
        vista.getBuscarestado().setText(null);
        vista.getTipopaciente().setText("");
        vista.getSeleccionada().setModel(new DefaultListModel());
        vista.getSeleccionada().setEnabled(false);
        vista.getQuitar().setEnabled(false);
        vista.getAnadir().setEnabled(false);
        this.listaropcionesadministrativas();
        vista.getOpciones().setEnabled(false);
        vista.getTiposervicio().setSelectedItem("SELECCIONE");
        this.listaropcionesadministrativaspendientes();
        vista.getSalir().setEnabled(true);
    }
    public void registrar() throws SQLException{
        if (administ) {
            String tiposervicio = vista.getTiposervicio().getSelectedItem().toString();
            modelotiposervicio = new ModeloTiposervicios();
            int tiposerv = modelotiposervicio.capturarid(tiposervicio);
            long cedula = this.capturacedula();
            modelopacientes = new ModeloPaciente(cedula);
            int idpaciente = modelopacientes.capturarid();
            int idusuario = Principal.getIdUsuario();
            String [] captura = this.capturaradministrativos();
            boolean registro = modelo.registraradministrativo(tiposerv, idusuario, idpaciente, captura);
            if(registro){
              String Leyenda = "Se ha procesado exitosamente su solicitud";
              vista.getLeyenda().setText(Leyenda);
              this.listaropcionesadministrativas();
              this.listaropcionesadministrativaspendientes();
              this.limpiar();
            }
        }else if(cit){
            
            
        }else if(emerg){
            String tiposervicio = vista.getTiposervicio().getSelectedItem().toString();
            modelotiposervicio = new ModeloTiposervicios();
            int tiposerv = modelotiposervicio.capturarid(tiposervicio);
            long cedula = this.capturacedula();
            modelopacientes = new ModeloPaciente(cedula);
            int idpaciente = modelopacientes.capturarid();
            int idusuario = Principal.getIdUsuario();
            
            boolean registrar = modelo.registraremergencia(tiposerv, idpaciente, idusuario);
            if(registrar){
                this.limpiar();
                String leyenda = "Emergencia registrada";
                vista.getLeyenda().setText(leyenda);
                
            }else{
                JOptionPane.showMessageDialog(principal, "Hubo un error verifique");
            }
            
        }else{
            JOptionPane.showMessageDialog(principal, "Error...");
        }
    }
    public void modificar() throws SQLException{
        if(administ){
            String tiposervicio = vista.getTiposervicio().getSelectedItem().toString();
            modelotiposervicio = new ModeloTiposervicios();
            int tiposerv = modelotiposervicio.capturarid(tiposervicio);
            long cedula = this.capturacedula();
            modelopacientes = new ModeloPaciente(cedula);
            int idpaciente = modelopacientes.capturarid();
            int idusuario = Principal.getIdUsuario();
            String capt = this.capturarestado();
            int estado = 0; 
            modelopacientes = new ModeloPaciente(this.capturacedula());
            int idpac = modelopacientes.capturarid();
            if(capt.equalsIgnoreCase("En espera")){
                estado = 2;
            }else if (capt.equalsIgnoreCase("Finalizado")){
                estado = 3;
            }else {
                estado = 1;
            }
            System.out.println(estado);
          boolean modificado = false;
         
        }
    }
    public void buscarpaciente(){
      long cedula = this.capturacedula();
//    JOptionPane.showMessageDialog(principal, cedula);
    ModeloPaciente modelopac = new ModeloPaciente(cedula);
    boolean existe = modelopac.capturardatospaciente();
        if (existe) {
            vista.getNombre().setText(modelopac.getNombre());
            vista.getApellido().setText(modelopac.getApellido());
            vista.getTipopaciente().setText(modelopac.getTipopersona());
            this.verificarestado();
        }else{
            JOptionPane.showMessageDialog(principal, "El Paciente con la cédula: "+cedula+ "no existe, Verifique...");
        }
    }
    private void verificarestado(){
        int opcion1 = 0 ;

        opcion1 = this.verificarestados();
           if(opcion1 == 2){
                 String leyenda = "Esta persona posee una cita para hoy";
                JOptionPane.showMessageDialog(principal, leyenda, "Posee Cita hoy", JOptionPane.INFORMATION_MESSAGE);
                vista.getNuevacita().setEnabled(false);
                vista.getProcesarcita().setEnabled(true);
                vista.geteliminarcita().setEnabled(true);
            }
    }
    /**
     * 
     * @return  
     */
    private int verificarestados(){
        int opcion1= 0;
        boolean posee = false;
        long cedula = this.capturacedula();
        modelopacientes = new ModeloPaciente(cedula);
        int idpaciente = 0;
        boolean poseecita = false;
        try {
            idpaciente = modelopacientes.capturarid();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            posee = modelo.capturarestado(idpaciente,this.modelopacientes);
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        modelo.setPaciente(idpaciente);
        poseecita = modelo.verificarcita();
        if(posee && vista.getTiposervicio().getSelectedItem().equals("ADMINISTRATIVO")){
            opcion1 = 1;
            int deseo  = JOptionPane.showConfirmDialog(principal, "Este paciente posee "+modelo.getCantidadpendientes()+" solicitudes administrativas pendientes. \n Desea asignar otra solicitud?", "Paciente con solicitudes administrativas pendientes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(deseo == 0){
              this.listaropcionesadministrativasrestante();
              String Leyenda = "Selecciones las opciones administrativas que solicite el paciente";
              vista.getLeyenda().setText(Leyenda);
              vista.getOpciones().setEnabled(true);
              vista.getSeleccionada().setEnabled(true);
              vista.getMovimiento().setSelectedIndex(1);
            }else{
              vista.getPaneladministrativo().setEnabled(false);
              this.habilitarestado();
              this.desabilitaradministrativo();
              String leyenda = "Busque el o los estados administrativos correspondientes al paciente";
              vista.getMovimiento().addTab(leyenda,vista.getEstadoadministrativo());
              vista.getMovimiento().setSelectedIndex(2);
              vista.getOpciones().setEnabled(false);
              vista.getSeleccionada().setEnabled(false);
              vista.getBuscarestado().setText(modelopacientes.getNombre());
              this.ListadoBusqueda(modelopacientes.getNombre(), vista.getSolicitudes());
            }
          
        }else if(posee == false && vista.getTiposervicio().getSelectedItem().equals("ADMINISTRATIVO")){
              vista.getOpciones().setEnabled(true);
              vista.getSeleccionada().setEnabled(true);
        }else if(poseecita) {
            opcion1 = 2;
        }
        return opcion1;
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    private void listaropcionesadministrativas(){
      JList lista  =vista.getOpciones();
      modelo.listaropcionesadministrativas(lista);
    }
    private void listaropcionesadministrativasrestante(){
      JList lista = vista.getOpciones();
      modelo.listaropcionesadministrativasrestante(lista);
    }
    @Override
    public void keyTyped(KeyEvent e) {
       
    }
    @Override
    public void keyPressed(KeyEvent e) {
     if(e.getSource() == vista.getCedula()){
          if (e.getKeyCode()==KeyEvent.VK_ENTER || e.getKeyChar() == KeyEvent.VK_TAB){
           if (vista.getCedula().getText().contains(" ")){
                    String leyenda1 = "Es necesario que ingreses un número de cédula valido";
                    String leyenda = "Recuerda que el campo debe tener los 8 digitos de lo contrario no sera valida";
                    vista.getLeyenda().setText(leyenda);
                    JOptionPane.showMessageDialog(principal, leyenda1);
                  
                }else{
                    buscarpaciente();
                } 
        }
     }   
    }
    @Override
    public void keyReleased(KeyEvent e) {
         Object origen = e.getSource();
        if(origen.equals(vista.getBuscarestado())){
            String busqueda = vista.getBuscarestado().getText().toUpperCase();
            ListadoBusqueda(busqueda,vista.getSolicitudes());
        }else if(origen.equals(cat.getTextbusqueda())){
            String busqueda = cat.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getCatpaciente());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
      Component evento = e.getComponent();
      if(evento.equals(vista.getOpciones())){
        vista.getAnadir().setEnabled(true);
        vista.getQuitar().setEnabled(false);
      }else if(evento.equals(vista.getSeleccionada())){
        vista.getQuitar().setEnabled(true);
        vista.getAnadir().setEnabled(false);
      }else if(evento.equals(vista.getSolicitudes())){
        if(e.getClickCount() == 2){
          try{
            int fila = vista.getSolicitudes().getSelectedRow();
            int fila1 = vista.getSolicitudes().convertRowIndexToModel(fila);
            DefaultTableModel modelotabla = (DefaultTableModel) vista.getSolicitudes().getModel();
            String cedul  = (String) modelotabla.getValueAt(fila1, 0);
            String opcion  = (String) modelotabla.getValueAt(fila1, 3);
            statu = (String) modelotabla.getValueAt(fila1, 4);
            
            long cedula = Long.parseLong(cedul);
            if(cedula == this.capturacedula()){
               this.viewseleccionopcion(cedula,opcion);
            }else{
              String leyenda = "Estimado debes seleccionar primero el paciente correspondiente al servicio";
              JOptionPane.showMessageDialog(principal, leyenda,"Avertencia",JOptionPane.WARNING_MESSAGE);
            }
           
          }catch(HeadlessException ex){
             System.out.println("Error: "+ex);
          }
        }
      }else
      if(evento.equals(cat.getCatpaciente())){
         if (e.getClickCount() == 2) {
            try{
                int fila = cat.getCatpaciente().getSelectedRow();
                int fila1 = cat.getCatpaciente().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCatpaciente().getModel();
                String cedula = (String) modelotabla.getValueAt(fila1, 0);
                long captura = Long.parseLong(cedula);
                this.seleccionpaciente(captura);
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

  @Override
  public void valueChanged(ListSelectionEvent e) {
    
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
      if(!estatusopcion.getEstadoopcion().getSelectedItem().equals(statu)){
        estatusopcion.getGrabar().setEnabled(true);
      }else{
        estatusopcion.getGrabar().setEnabled(false);
      }
    }
  
  
  
  /*************************************/
  /* 			Clases internas		   */
  /*************************************/
  class OpcionesAdministrativas implements Runnable{

	  	ControladorServicio controller;
	  	
	  	public OpcionesAdministrativas(ControladorServicio control) {
	  		this.controller = control;
	  	}
	  	
		@Override
		public void run() {
			estatusopcion = new ProcesoEstado(principal, true);
	        estatusopcion.setController(controller);
	        estatusopcion.getNombr().setText(vista.getNombre().getText() + " " + vista.getApellido().getText());
	        estatusopcion.getCedula().setText(vista.getCedula().getText());
	        estatusopcion.getTipopaciente().setText(vista.getTipopaciente().getText());
	        estatusopcion.getOpcionadmin().setText(opcionesadministrativas.getNombre());
	        listarestatus();
	        estatusopcion.setVisible(true);
			
		}
  	
  }
}
