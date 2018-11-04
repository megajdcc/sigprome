/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.Opcadministrativas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Vista.Principal;
import Modelo.OpcAdminis;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Vista.OpcionesAdministrativas;
import java.awt.Color;
import static java.awt.Color.orange;
import javax.swing.JFrame;
/**
 *
 * @author Jnatn'h
 */
public class ControladorOpcAdministrativas implements ActionListener, KeyListener, MouseListener {
  
  private DefaultTableModel dm;
  private OpcionesAdministrativas vista;
  private OpcAdminis Opciones; 
  private Opcadministrativas cat;
  private Principal principal;
  private String nombre;
  private int id = 0;
  //constructores 
  public ControladorOpcAdministrativas(Opcadministrativas vista){
    this.cat = vista;
    Opciones = new OpcAdminis(); 
    this.ListarOpciones();
  }
  public ControladorOpcAdministrativas(OpcionesAdministrativas vista){
    this.vista = vista;
    Opciones = new OpcAdminis();
  }
  
  
  @Override
  public void actionPerformed(ActionEvent e) {
   Object obj  = e.getSource();
    if(obj.equals(cat.getRegistrar())){
        cat.dispose();
        this.vista = new OpcionesAdministrativas(principal, true);
        vista.setController(this);
        vista.setVisible(true);
    }else if(obj.equals(cat.getSalircat())){
      cat.dispose();
    }else if(obj.equals(vista.getGrabar())){
       if (vista.getOpcion().getText().isEmpty()) {
               this.espacioblanco();
            }else if(vista.getOpcion().getText().contains("  ")){
                this.parespacios();
            }else
            if(vista.getOpcion().getText().length() < 4){
                this.menorletras();
            }else{codigoBotonregistrar();}
    }else if(obj.equals(vista.getModificar())){
           if (vista.getOpcion().getText().isEmpty()) {
               this.espacioblanco();
             }
            else if(vista.getOpcion().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            if(vista.getOpcion().getText().length() < 4){
                this.menorletras();
            }
            else{
                
                codigoBotonModificar();
             }
    }else if(obj.equals(vista.getEliminar())){
       String nomb = vista.getOpcion().getText();
        boolean eliminado=Opciones.eliminarModelo(nomb);
        if (eliminado){
            this.nombre = nomb;
            String Leyenda = "Opción "+nomb+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
           
        }else{
            String Leyenda = "La Opción "+nomb+"  no fue eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }  
        codigoBotonLimpiar();
    }else if(obj.equals(vista.getSalir())){
      vista.dispose();
      cat = new Opcadministrativas(principal, true);
      cat.setVisible(true);
    }
    
    
  }
  public void codigoBotonregistrar(){
        String nomb = vista.getOpcion().getText();
        boolean existe = Opciones.verificar(nomb);
            if (existe == true) {
                    String leyenda = "No se pudo registrar la opción administrativa porque ya existe";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(Color.YELLOW);
                }else{
                    Opciones.setNombre(nomb);
                    boolean incluido=Opciones.incluirModelo();
                    if (incluido){
                        this.nombre = nomb;
                        String Leyenda = "Opción "+nomb+" Registrado";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                    }else{
                    String leyenda = "No se pudo registrar la opción administrativa";
                    vista.getLeyenda().setText(leyenda);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
  public void codigoBotonModificar(){
       String nomb = vista.getOpcion().getText();  
        Opciones.setNombre(nomb);
        boolean modificado=Opciones.modificarModelo(nomb, this.id);
        if (modificado){
            this.nombre = nomb;
            String Leyenda = "Opción "+nomb+" modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
        }else{
            String Leyenda = "Opción "+nomb+" no modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }
        codigoBotonLimpiar();
        }//fin de codigoModificar

  private void ListarOpciones(){
        String[][] informacion = Opciones.ListarOpciones();
        cat.getOpcadmins().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String[] {"Opciones Administrativas"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getOpcadmins().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
  public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
  public void Capturardatos(String nombre){
           this.nombre = nombre;
           boolean encontrado = Opciones.consultarModelo(nombre);
           if (encontrado){
                vista = new OpcionesAdministrativas(principal, true);
                vista.setController(this);
                vista.getOpcion().setText(Opciones.getNombre());
                this.id = Opciones.getId();
                //Acción de los botones y cajas de texto
                vista.getGrabar().setEnabled(false);
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
                cat.setVisible(false);
                vista.setVisible(true);    
            }else{
                vista.getGrabar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
       }
 public void espacioblanco(){
     String leyenda = "¡No puede registrar espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
 public void parespacios(){
     String leyenda = "¡No puede registrar varios espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
 public void menorletras(){
     String leyenda = "Asegúrese de que los campo Nombre no sean menor a 4 letras!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
 public void codigoBotonLimpiar(){
        vista.getOpcion().setText("");        
        vista.getGrabar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false);        
    }//fin de codigoLimpiar
  @Override
  public void mouseClicked(MouseEvent e) {
     if (e.getClickCount() == 2) {
            try{
                int fila = cat.getOpcadmins().getSelectedRow();
                int fila1 = cat.getOpcadmins().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getOpcadmins().getModel();
                String captura = (String)modelotabla.getValueAt(fila1, 0);
                Capturardatos(captura);
            }catch(HeadlessException ex){
                System.out.println("Error: "+ex);
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
  public void keyTyped(KeyEvent e) {
      char b = e.getKeyChar();
        if(cat.getBusqueda().getText().length()>50){
            e.consume();
        }
  }

  @Override
  public void keyPressed(KeyEvent e) {
   
  }

  @Override
  public void keyReleased(KeyEvent e) {
     Object origen = e.getSource();
        if(origen.equals(cat.getBusqueda())){
            String busqueda = cat.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getOpcadmins());
        }
  }
  
}
