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
import Modelo.ModeloUsuario;
import Vista.Principal;
import Vista.Triaje;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Vista.catpacientetriaje;
import javax.swing.table.DefaultTableModel;
import Modelo.ModeloPaciente;
import Modelo.ModeloServicio;
import Modelo.ModeloTriaje;
import java.awt.HeadlessException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jnatn'h
 */

public class ControladorTriaje implements ActionListener, KeyListener, MouseListener{
    
    DefaultTableModel dm;
    Vista.Triaje triaje;
    ModeloTriaje modelo;
    catpacientetriaje enespera;
    ModeloUsuario modelousuario;
    Principal principal;
    ModeloPaciente modelopacientes;
    ModeloServicio modeloservicio;
    long cedulapaciente;
    public ControladorTriaje(Triaje triaje) {
        this.triaje = triaje;
        modelopacientes = new ModeloPaciente();
        modeloservicio = new ModeloServicio();
        modelo = new ModeloTriaje();
        try {
            this.asignarusuario();
            enespera = new catpacientetriaje(principal, true);
         
        } catch (SQLException ex) {
            Logger.getLogger(ControladorTriaje.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(triaje.getSalir())){
            triaje.dispose();
        }else if(e.getSource().equals(triaje.getConsultar())){
            this.PacienteEspera();
            enespera.setController(this);
            enespera.setVisible(true);
        }else if(e.getSource().equals(triaje.getProcesar())){
            boolean registrar = this.registrar();
            if(registrar){
                this.limpiar();
                String leyenda = "Triage Procesado exitosamente";
                triaje.getLeyenda().setText(leyenda);
            }
        }else if(e.getSource().equals(enespera.getRefres())){
            this.PacienteEspera();
        }
        
        if(e.getSource().equals(enespera.getSalir())){
            enespera.dispose();
        }   
    }
    public boolean registrar(){
        boolean registro = false;
        int idservicios = modeloservicio.capturarid(this.cedulapaciente);
        String peso = triaje.getPeso().getText();
        String talla = triaje.getTalla().getText();
        String temperatura = triaje.getTemperatura().getText();
        String tension = triaje.getTension().getText();
        String glicemia = triaje.getGlicemia().getText();
        String observacion = triaje.getObservacion().getText();
   
        modelo.asignarentradas(peso,talla,temperatura,tension,glicemia,observacion,idservicios);
        registro = modelo.registrar();
        return registro;
    }
//    private long capturacedula(){
//        char cedul = triaje.getCedula().getText().charAt(2);
//        char cedul1 = triaje.getCedula().getText().charAt(3);
//        char cedul2 = triaje.getCedula().getText().charAt(5);
//        char cedul3 = triaje.getCedula().getText().charAt(6);
//        char cedul4 = triaje.getCedula().getText().charAt(7);
//        char cedul5 = triaje.getCedula().getText().charAt(9);
//        char cedul6 = triaje.getCedula().getText().charAt(10);
//        char cedul7 = triaje.getCedula().getText().charAt(11);
//        char data[] = {cedul,cedul1,cedul2,cedul3,cedul4,cedul5,cedul6,cedul7};
//        String cedul8 = String.copyValueOf(data);
//        long cedula = Long.parseLong(cedul8);
//        return cedula;
//    }
    public void limpiar(){
        triaje.getPeso().setEnabled(false);
        triaje.getTalla().setEnabled(false);
        triaje.getTemperatura().setEnabled(false);
        triaje.getTension().setEnabled(false);
        triaje.getGlicemia().setEnabled(false);
        triaje.getObservacion().setEnabled(false);      
        triaje.getProcesar().setEnabled(false);
        
        triaje.getPeso().setText("");
        triaje.getTemperatura().setText("");
        triaje.getTalla().setText("");
        triaje.getGlicemia().setText("");
        triaje.getTension().setText("");
        triaje.getCedula().setText("");
        triaje.getNombre().setText("");
        triaje.getApellido().setText("");
        triaje.getObservacion().setText("");
        triaje.getTipopaciente().setText("");
    } 
    public void seleccionpaciente(long captura){
        modelopacientes = new ModeloPaciente(captura);
        boolean capturado = modelopacientes.capturardatospaciente();
        if(capturado){
            enespera.dispose();
            triaje.getNombre().setText(modelopacientes.getNombre());
            triaje.getApellido().setText(modelopacientes.getApellido());
            triaje.getTipopaciente().setText(modelopacientes.getTipopersona());
            long cedula9 = modelopacientes.getCedula();
          this.cedulapaciente = cedula9;
            String cedu = String.valueOf(cedula9);
            if (cedu.length() < 8) {
                String num = "0";
                String cedula99 = num+cedu;
                triaje.getCedula().setText(cedula99);
            }else{
                triaje.getCedula().setText(cedu); 
            }   
            this.habilitarmovimiento();
            triaje.getPeso().requestFocus();
        }
    }
    private void  seleccionpacienteemergencia(String captura){
      
    }
    public void habilitarmovimiento(){
        triaje.getPeso().setEnabled(true);
        triaje.getTalla().setEnabled(true);
        triaje.getTemperatura().setEnabled(true);
        triaje.getTension().setEnabled(true);
        triaje.getGlicemia().setEnabled(true);
        triaje.getObservacion().setEnabled(true);
        
        triaje.getProcesar().setEnabled(true);
    }
    public void asignarusuario() throws SQLException{
        int idusuario = Principal.getIdUsuario();
        modelousuario = new ModeloUsuario(idusuario);
        boolean captura = modelousuario.capturardatos();
        if (captura){
            String cedula = String.valueOf(modelousuario.getCedula());
            String nombre = modelousuario.getNombre();
            String apellido = modelousuario.getApellido();
            String especialidad = modelousuario.getNombespecialidad();
            triaje.getEnfermera().setText(especialidad+": " +nombre+ " "+apellido);
        }else{
             triaje.dispose();
        }
    }
    public void PacienteEspera(){
        String[][] informacion  = modelopacientes.ListarPacientesEspera();  
        enespera.getCatpaciente().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Cédula","Nombre","Apellido","Tipo Paciente","Proviene"}) {
    boolean[] canEdit = new boolean [] {
        false,false,false,false,false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    enespera.getCatpaciente().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    @Override
    public void keyPressed(KeyEvent e) {
     
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(enespera.getBusqueda())){
            String busqueda = enespera.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,enespera.getCatpaciente());
//            if(enespera.getFiltro().isSelected(enespera.getEmergencia().getModel())){
//               enespera.getEmergencia().setSelected(false);
//            }else if(enespera.getFiltro().isSelected(enespera.getCita().getModel())){
//               enespera.getCita().setSelected(false);
//            }
           enespera.getFiltro().clearSelection();
           
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      Object radioselec  = e.getSource();
        if(radioselec.equals(enespera.getCatpaciente())){
          
                if (e.getClickCount() == 2) {
              try{
                  int fila = enespera.getCatpaciente().getSelectedRow();
                  int fila1 = enespera.getCatpaciente().convertRowIndexToModel(fila);
                  DefaultTableModel modelotabla=(DefaultTableModel) enespera.getCatpaciente().getModel();
                  String captura = (String)modelotabla.getValueAt(fila1, 0);
                   Long capt = Long.parseLong(captura);
                  seleccionpaciente(capt);
              }catch(HeadlessException ex){
                   JOptionPane.showMessageDialog(principal, "ERROR: " + ex.getMessage());
              }
                }
       }else if(captura().equalsIgnoreCase("Emergencia")){
                String cap = captura().toUpperCase();
                 this.ListadoBusqueda(cap, enespera.getCatpaciente());
          }else if(captura().equalsIgnoreCase("Cita")){
              String cap = captura().toUpperCase();
              this.ListadoBusqueda(cap, enespera.getCatpaciente());
          }
  }
    private String captura(){
      String cap = null;
         
          for (Enumeration<AbstractButton > buttons = enespera.getFiltro().getElements(); buttons.hasMoreElements();)
            {
                   AbstractButton button = buttons.nextElement();
                  if(button.isSelected())
                    {
                       cap = button.getText();

                   }
             } 
          return cap;
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
