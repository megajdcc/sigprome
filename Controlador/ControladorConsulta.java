/*
 * The MIT License
 *
 * Copyright 2017 Jnatn'h.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Controlador;

import Modelo.ModeloConsulta;
import Modelo.ModeloPaciente;
import Modelo.ModeloPatologia;
import Modelo.ModeloTriaje;
import Modelo.ModeloUsuario;
import Modelo.Reports;
import Vista.CatPaciente;
import Vista.Consulta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
import Vista.Principal;
import Vista.Recipe;
import Vista.Reposo;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jnatn'h
 */
public class ControladorConsulta implements ActionListener, KeyListener, MouseListener, ListSelectionListener{
    // REFERNCIANDO A OBJECTO NECESARIOS 
    private DefaultTableModel dm;
    //REFERENCIANDO A MODELOS 
    private ModeloConsulta modelo; // modelo consulta
    
    private ModeloPaciente modelpaciente; // modelo Paciente
    private ModeloPatologia patologia; // modelo Patologia
    private ModeloTriaje triage; // modelo triage 
    private Reports reporte; // model de reporte 
    private ModeloUsuario usuario; 
    // REFERNCIANDO A LAS VISTA ASOCIADAS     
    private Principal principal; // view principal 
    private Consulta consulta; // view consulta
    private CatPaciente consult; // view catalogo de paciente
    private Recipe Reci; // view de recipe 
    private Reposo Rep; // view de reposo
    private JDialog term; // view de terminacion de consulta.
    // CAMPOS PROPIOS DE CLASES 
    private long cedulapaciente;
    private String observaciontriage; 
    private int idpatologia,idtriage,idconsulta;
    private boolean imprimirrecipe, imprimirreposo;
    
// CONSTRUCTOR DE LA CLASE 
    /**
     * El cosntructor recibe por parametro un Object JDialog en si es la vista de consulta... 
     * @param consulta 
     */
    public ControladorConsulta(Consulta consulta){
        this.consulta = consulta;
        modelo = new ModeloConsulta();
        usuario = new ModeloUsuario();
        patologia = new ModeloPatologia();
        reporte = new Reports();
        this.listarpatologias();
    }
    public Consulta getConsulta(){
      return consulta;
    }
    // METODOS DE LA INTERFACE ACTIONLISTENER 
    @Override
    public void actionPerformed(ActionEvent e) {
      
        Object origenevento = e.getSource();
        if(origenevento.equals(consulta.getSalir())){
            consulta.dispose();
        }else if(origenevento.equals(consulta.getConsultar())){
            consult = new CatPaciente(principal, true,1);
            consult.setController(this);
            this.ListarPaciente();
            consult.getNuevopaciente().setVisible(false);
            consult.setVisible(true);
        }else if(origenevento.equals(consult.getSalircat())){
            consult.dispose();
        }else if (origenevento.equals(consulta.getObservaciontriaje())){
            JOptionPane.showMessageDialog(principal, this.observaciontriage);
        }else if(origenevento.equals(consulta.getAnadir())){
          moverSeleccion(consulta.getPatologias(), consulta.getSeleccionada());
         }else if(origenevento.equals(consulta.getQuitar())){
          moverSeleccion(consulta.getSeleccionada(), consulta.getPatologias());
         }else if(origenevento.equals(consulta.getAsignarpatologia())){
          consulta.getMovimiento().setSelectedIndex(2);
          consulta.getPanelpatologias().setEnabled(true);
          consulta.getPatologias().setEnabled(true);
          consulta.getSeleccionada().setEnabled(true);
        }else if(origenevento.equals(consulta.getProcesar())){
                
            boolean validado = this.validar();
                if(validado){
                    String pregunta  ="Confirma procesar la consulta? \n Recuerde que una ves confirmado el proceso no puede volver atras...";
                    int valor = JOptionPane.showConfirmDialog(principal, pregunta,"Confirmar proceso",JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if(valor == 0){
                       this.registrar();
                    }          
                }else{
                    String leyenda = "Disculpe no se pudo registrar verifique los campos";
                    consulta.getLeyenda().setText(leyenda);
                }
        }else if(origenevento.equals(consulta.getRecip())){
            consulta.recipe();
        }else if(origenevento.equals(consulta.grabarrecipe)){
          boolean validarrecipe = false;
          validarrecipe = validarrecipe();
          if(validarrecipe){
           registrarrecipe(); 
          }
        }else if(origenevento.equals(consulta.reposo)){
          consulta.reposo();
        }else if(origenevento.equals(consulta.getGrabarreposo())){
           boolean validarreposo = false;
          validarreposo = validarreposo();
          if(validarreposo){
           registrarreposo(); 
          }
        }else if(origenevento.equals(consulta.ordenes)){
          consulta.Ordenes();
        }else if(origenevento.equals(consulta.aterminar)){
          if(imprimirrecipe && imprimirreposo){
             reporte.recipeReports(idconsulta, true,this);
             reporte.reposoReports(idconsulta,true,this);
             imprimirrecipe = false;
             imprimirreposo = false;
             consulta.getAsignaciones().dispose();
          }else if(imprimirrecipe && imprimirreposo == false){
             reporte.recipeReports(idconsulta, true,this);
              imprimirrecipe = false;
              imprimirreposo = false;
              consulta.getAsignaciones().dispose();
          }else if(imprimirreposo && imprimirrecipe == false){
             reporte.reposoReports(idconsulta,true,this);
             imprimirrecipe = false;
             imprimirreposo = false;
             consulta.getAsignaciones().dispose();
          }else{
            consulta.getAsignaciones().dispose();
          }
        }else if(origenevento.equals(consult.getRefres())){
            this.ListarPaciente();
        }   
    }
    private void registrarrecipe(){
        String recipe = consulta.getRecipe1().getText();
        String indicacion = consulta.getIndicaciones().getText();
        Date vencrecipe = consulta.getVencrecipe().getDate();
        Date vencindicacion = consulta.getVencindicaciones().getDate();
        boolean registro = modelo.insertarrecipe(recipe,indicacion,vencrecipe,vencindicacion);
        if(registro){
          Icon icono = new ImageIcon(getClass().getResource("/Vista/imagen/reportes.png"));
          String[] opciones ={"Imprimir","Mostrar","Despues"};
          String pregunta = "Desea imprimir o mostrar de una vez el recipe o desea hacerlo al finalizar ?";
          int opcion = JOptionPane.showOptionDialog(principal, pregunta, "Recipe", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,icono,opciones,opciones[0]);
            consulta.salirrecipe();
            consulta.getRecip().setBackground(Color.RED);
            consulta.getRecip().setForeground(Color.WHITE);
            consulta.getRecip().setEnabled(false);
          switch (opcion) {
            case 0:
              reporte.recipeReports(idconsulta, true,this);
              break;
            case 1:
              consulta.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
              reporte.recipeReports(idconsulta, false,this);
              break;
            default:
              imprimirrecipe = true;
              break;
          }
        }else{
          String mesage = "Ocurrio un error comuniquelo al administrador del sistema";
          JOptionPane.showMessageDialog(principal, mesage);
        }
    }
    private void registrarreposo(){
        String motivo = consulta.getReposo1().getText();
        int diasreposo = consulta.getDiasreposo().getValue();
      
        boolean registro = modelo.insertarreposo(motivo,diasreposo);
        if(registro){
          Icon icono = new ImageIcon(getClass().getResource("/Vista/imagen/reportes.png"));
          String[] opciones ={"Imprimir","Mostrar","Despues"};
          String pregunta = "Desea imprimir o mostrar de una vez el reposo o desea imprimirlo al finalizar ?";
          int opcion = JOptionPane.showOptionDialog(principal, pregunta, "Reposo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,icono,opciones,opciones[0]);
            consulta.cerrarreposo();
            consulta.reposo.setBackground(Color.RED);
            consulta.reposo.setForeground(Color.WHITE);
            consulta.reposo.setEnabled(false);
          if(opcion == 0){
            reporte.reposoReports(idconsulta, true,this);
          }else if(opcion == 1){
            consulta.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
            reporte.reposoReports(idconsulta, false,this);
          }else{
            imprimirreposo = true;
          }
        }else{
          String mesage = "Ocurrio un error comuniquelo al administrador del sistema";
          JOptionPane.showMessageDialog(principal, mesage);
        }
    }
    /**
     * Este metodo se encarga de validar cada uno de los campos de la vista recipe, 
     * para asegurarse de que ningun campo este vacio.
     * @return Devuelve un booleano para indicar que la validacion es falsa o verdaderas
     */
    private boolean validarrecipe(){
      boolean validado = false;
        String recipe = consulta.getRecipe1().getText().trim();
        String indicaciones = consulta.getIndicaciones().getText().trim();
        Date vencimientorecipe = consulta.getVencrecipe().getDate();
        Date vencimientoindicaciones = consulta.getVencindicaciones().getDate();          
         if(recipe.isEmpty()){
          String text = "Debe de escribir o preescribir el recipe el mismo se encuentra vacio";
          consulta.getRecipe1().setFocusable(true);
          JOptionPane.showMessageDialog(principal, text);
        }else if(indicaciones.isEmpty()){
            String text = "Debe de escribir o preescribir la indicación el mismo se encuentra vacio";
              consulta.getIndicaciones().setFocusable(true);
            JOptionPane.showMessageDialog(principal, text);
        }else if(vencimientorecipe == null){
           String text = "Debe de indicar la fecha de vencimiento del recipe";
             consulta.getVencrecipe().setFocusable(true);
            JOptionPane.showMessageDialog(principal, text);
        }else if(vencimientoindicaciones == null){
            String text = "Debe de indicar la fecha de vencimiento de la indicación";
            consulta.getVencindicaciones().setFocusable(true);
            JOptionPane.showMessageDialog(principal, text);
        }else{
           validado = true;
        }
      return validado;
    }
     private boolean validarreposo(){
      boolean validado = false;
        String motivo = consulta.getReposo1().getText().trim();
        int diasreposo = consulta.getDiasreposo().getValue();
         
         if(motivo.isEmpty()){
          String text = "Debe de escribir el motivo del reposo, pues el mismo se encuentra vacio";
          consulta.getReposo1().setFocusable(true);
          JOptionPane.showMessageDialog(principal, text);
        }else if(diasreposo == 0){
           String text = "Debe de indicar el número de dias de reposo";
             consulta.getDiasreposo().setFocusable(true);
            JOptionPane.showMessageDialog(principal, text);
        }else{
           validado = true;
        }
      return validado;
    }
    /**
     * este metodo recibo por parametro dos objecto de tipo Jlist, la cual uno es donde se enlistan todas las patologias
     * y el otro es a donde van a ir aquellas selecciones que se necesiten anadir a la consulta en cuestion ... 
     * @param origen
     * @param destino 
     */
    private void moverSeleccion(JList origen, JList destino){

        //Se obitnen los modelos de "origen" y "destino" para poder utilizar el metodo 'addElement' y 'removeRange

        DefaultListModel listaorigen = (DefaultListModel) origen.getModel();
        DefaultListModel listadestino = (DefaultListModel) destino.getModel();
        //Se almacenan los identificadores de las tablas seleccionadas
        int[] indice = origen.getSelectedIndices();
     
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
    int contador  = 0;
    ListModel cont = consulta.getSeleccionada().getModel();
    contador = cont.getSize();
    if(contador != 0){
        Color micolor = new Color(247,0,24,80);
        consulta.getAsignarpatologia().setBackground(micolor);
        consulta.getAsignarpatologia().setForeground(Color.white);
       consulta.getAsignarpatologia().setText( contador + " Patologia seleccionada");
    }else{
      consulta.getAsignarpatologia().setText("Asignar patologia");
       consulta.getAsignarpatologia().setForeground(Color.black);
       consulta.getAsignarpatologia().setBackground(Color.white);
    //   consulta.getAsignarpatologia().setBorder(Border.BORDER_STYLE_DASHED);
    }
   
    }

    /**
     * Este metodo no retorna nada, su function es solo listar las patologias en un objecto del tipo JList de la viesta
     * consulta, utilizando el objeto modelo patologia .... 
     */
     private void listarpatologias(){
      JList lista  = consulta.getPatologias();
      patologia.listarpatologiasasignar(lista);
    }
     /**
      * Este metodo devuelve un entero, debido a que su function es capturar el id de la ultima consulta hecha... 
      * @return 
      */
    public int capturaridconsulta(){
        int idconsu= 0;
        idconsu = modelo.caputrarultimoid();
        return idconsu;
    }
    /**
     * Este metodo devuelve un booleano para indicar de que la validacion de los campos requeridos estan validados.
     * @return boolean  
     */
    public boolean validar(){
        boolean validado = false;
            if(consulta.getSintoma().getText().isEmpty() || consulta.getSintoma().getText().length() < 5){
                String leyenda = "Debe de rellenar el campo síntoma y la misma no puede ser menor a 5 letras";
                consulta.getLeyenda().setText(leyenda);
            }else if(consulta.getDiagnostico().getText().isEmpty() || consulta.getDiagnostico().getText().length() < 5){
                String leyenda = "Debe de rellenar el campo diagnóstico y la misma no puede ser menor a 5 letras";
                consulta.getLeyenda().setText(leyenda);
            }else if(this.validarpatologias() == false){
                String leyenda = "Debe elegir la patología asociada al paciente";
                consulta.getLeyenda().setText(leyenda);
            }else {
                validado = true;
            }
        return validado;
    }
    /**
     * Este metodo devuelve un boolean, debido a que valida si se ha seleccionado o no una patologia para la consulta.
     * @return boolean  
     */
     private boolean validarpatologias(){
      boolean val = false;
       DefaultListModel milista = (DefaultListModel) consulta.getSeleccionada().getModel();
       if(milista.isEmpty()){
         val = false;
       }else{
         val = true;
       }
       return val;
    }
    /**
     * No retorna nada, su fucntion es registrar la consulta, claro esta ya cuando lo campos esten validados..
     */
    public void registrar(){
        modelo.setSintoma(consulta.getSintoma().getText());
        modelo.setDiagnostico(consulta.getDiagnostico().getText());
        modelo.setIdtriaje(idtriage);
        boolean registro = modelo.registrar();
        if(registro){
           idconsulta = modelo.getId();
           ListModel listpat  = consulta.getSeleccionada().getModel();
           int taman = listpat.getSize();
           boolean registrado = false;
           for(int i = 0 ; i < taman; i++){
             registrado = patologia.capturaridpatologia(listpat.getElementAt(i).toString(),idconsulta);
           }
        
        if(registrado){
             consulta.finalizar();
             this.limpiar();
             String leyenda = "Consulta procesada";
             consulta.getLeyenda().setText(leyenda);
           }
       }
    }
    /**
     * 
     * La fucntion de este metodo es de limpiar los campos despues de un registro ... 
     */
    public void limpiar(){
        consulta.getCedula().setText("");
        consulta.getNombre().setText("");
        consulta.getApellido().setText("");
        consulta.getTipopaciente().setText("");
        consulta.getTalla().setText("Talla: ");
        consulta.getTemperatura().setText("Temperatura: ");
        consulta.getPeso().setText("Peso: ");
        consulta.getPresion().setText("Tensión: ");
        consulta.getGlicemia().setText("Glicemia: ");
        consulta.getObservaciontriaje().setEnabled(false);
        consulta.getSintoma().setText("");
        consulta.getDiagnostico().setText("");
        consulta.getAsignarpatologia().setEnabled(false);
        consulta.getSintoma().setEnabled(false);
        consulta.getDiagnostico().setEnabled(false);
        consulta.getAsignarpatologia().setEnabled(false);
        consulta.getProcesar().setEnabled(false);
        this.listarpatologias();
        consulta.getAsignarpatologia().setText("Asignar Patologias");
        consulta.getSeleccionada().setModel(new DefaultListModel());
        consulta.getSeleccionada().setEnabled(false);
        consulta.getPatologias().setEnabled(false);
    }
    /**
     * Este metodo no devuelve nada pero recibe por parametro un Long, que no es mas que la cedula del
     * paciente capturado en el catalogo... 
     * @param captura 
     */
    public void seleccionpaciente(long captura){
        modelpaciente = new ModeloPaciente(captura);
        boolean capturado = modelpaciente.capturardatospaciente();
        if(capturado){
            consult.dispose();
            consulta.getNombre().setText(modelpaciente.getNombre());
            consulta.getApellido().setText(modelpaciente.getApellido());
            consulta.getTipopaciente().setText(modelpaciente.getTipopersona());
            long cedula9 = modelpaciente.getCedula();
          
            String cedu = String.valueOf(cedula9);
            if (cedu.length() < 8) {
                String num = "0";
                String cedula99 = num+cedu;
                consulta.getCedula().setText(cedula99);
            }else{
                consulta.getCedula().setText(cedu); 
            }
            this.establecertriage(cedula9);
            this.habilitarmovimiento();
            consult.dispose();
        }
    }
    /**
     * Este metodo no devuelve nada pero recibe por parametro un Long.
     * Su function es establecer los datos del triaje, despue que se capture el paciente en el catalogo de espera... 
     * @param cedula 
     */
    private void establecertriage(long cedula){
        triage  = new ModeloTriaje(); 
        boolean capturartriage = triage.capturartriage(cedula); 
        if(capturartriage){
            consulta.getTalla().setText("Talla: "+ triage.getTalla());
            consulta.getTemperatura().setText("Temperatura:" + triage.getTemperatura());
            consulta.getPresion().setText("Tension: "+ triage.getTension());
            consulta.getPeso().setText("Peso: "+ triage.getPeso());
            consulta.getGlicemia().setText("Glicemia: "+ triage.getGlicemia());
            this.idtriage = triage.getId();
            this.observaciontriage = triage.getObservacion();
            if(triage.getObservacion().isEmpty()){
                consulta.getObservaciontriaje().setEnabled(false);
            }else{
                consulta.getObservaciontriaje().setEnabled(false);
            }
        }else{
            String consul = "Verifique la cédula del paciente.";
            JOptionPane.showMessageDialog(principal,consul);
        }
    }
    /**
     * No devuelve nada, su unica function es habilitar Jpanel de movimiento del objecto consulta para su 
     * respectivo uso ... 
     */
    public void habilitarmovimiento(){
        consulta.getSintoma().setEnabled(true);
        consulta.getDiagnostico().setEnabled(true);
        consulta.getAsignarpatologia().setEnabled(true);
        consulta.getObservaciontriaje().setEnabled(true);
        consulta.getProcesar().setEnabled(true);
    }
    /**
     * No devuelve nada, su unica fucntion es capturar los datos del doctor y estabecerlo en la vista consulta. 
     */
    public void asignardoctor(){
        int idusuario = Principal.getIdUsuario();
        ModeloUsuario usuario = new ModeloUsuario(idusuario);
        try {
            boolean captura  = usuario.capturardatos();
            if(captura){
                String nombredoctor = usuario.getNombre();
                String Apellido = usuario.getApellido();
                String especialidad = usuario.getNombespecialidad();
            consulta.getDoctor().setText(especialidad + ": " + nombredoctor + " " + Apellido);
            }else{
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
//    public void Capturardatoscatpatol(String nombre){
//
//        ModeloPatologia pat  = new ModeloPatologia();
//        boolean encontrado = pat.consultarModelo(nombre);
//           if (encontrado){
//                consulta.getPatologia().setText(pat.getNombre());
//                this.idpatologia = pat.getId();
//                catpatol.dispose();
//            }else{
//                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
//            }
//    }
    /**
     * No devuelve nada, su unica function es listar los paciente en el catalogo de paciente...  
     */
    public void ListarPaciente(){
        ModeloPaciente pacientemodel = new ModeloPaciente();
        String[][] datos  = pacientemodel.ListarPacienteEsperaConsulta();
        DefaultTableModel model = new DefaultTableModel(datos, new String[]{"Cédula","Nombre","Apellido","Tipo Paciente","Proviene"});
        consult.getCatpaciente().setModel(new javax.swing.table.DefaultTableModel(
            datos,
            new String [] {"Cédula","Nombre","Apellido","Tipo Paciente","Proviene"}) {
            boolean[] canEdit = new boolean [] {
                false,false,false,false,false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
});
    consult.getCatpaciente().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource().equals(consult.getTextbusqueda())){
            this.validarcampo(e);
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
       
    }
    /**
     * Este metodo no retorna ningun dato, sin embargo recibe por parametro dos objecto uno de tipo String
     * y el otro de tipo JTable, el string es lo que el usuario tipeo para buscar en el catalogo.
     * y el Jtable es la tabla en donde se necesitan que se establesca lo que se busco ... 
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
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(consult.getTextbusqueda())){
            String busqueda = consult.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,consult.getCatpaciente());
        }
    }
    private void validarcampo(KeyEvent event){
        String caracter  = consult.getTextbusqueda().getText();
        char c = event.getKeyChar();
//         if(Character.isLetter(c)){
//            event.consume();
//    }
    if(caracter.length()> 9)
        {
            event.consume();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Object evento = e.getSource();
        if(evento.equals(consulta.getPatologias())){
        consulta.getAnadir().setEnabled(true);
        consulta.getQuitar().setEnabled(false);
      }else if(evento.equals(consulta.getSeleccionada())){
        consulta.getQuitar().setEnabled(true);
        consulta.getAnadir().setEnabled(false); 
      }else if(evento.equals(consult.getCatpaciente())){
        if (e.getClickCount() == 2) {
          consult.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            try{
                int fila = consult.getCatpaciente().getSelectedRow();
                int fila1 = consult.getCatpaciente().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) consult.getCatpaciente().getModel();
                long captura = Long.parseLong((String) modelotabla.getValueAt(fila1, 0));
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
  public String[] capturardatospararecipe(){
      String[] datos = usuario.datos();
      return datos;
  }
//  Ventana expediction ... 
  private void expedicion(){



//        contenedor.add(botones,BorderLayout.CENTER);
//       salir.setIcon(new ImageIcon("/Vista/imagen/salir.png"));
//       
//        footer.add(Terminar);
//        footer.add(salir);
//       
//        contenedor.add(footer,BorderLayout.SOUTH);
//        term.add(contenedor);
//        term.setVisible(true);
  }
}
