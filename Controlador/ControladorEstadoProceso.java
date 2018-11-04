/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.EstadoProceso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
import Modelo.ModelEstadosProces;
import Vista.EstadoProcesos;
import Vista.Principal;
import java.awt.Color;
import static java.awt.Color.orange;
import java.awt.HeadlessException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jnatn'h
 */
public class ControladorEstadoProceso implements ActionListener, KeyListener, MouseListener{
  private int idestado;
  private Principal principal;
  private DefaultTableModel dm; 
  private EstadoProceso cat;
  private ModelEstadosProces model;
  private EstadoProcesos vista;
  public ControladorEstadoProceso(EstadoProceso cat){
    this.cat = cat;
    model = new ModelEstadosProces(); 
    this.listarEstados();
  }
  public ControladorEstadoProceso(EstadoProcesos vista){
    this.vista = vista;
    model = new ModelEstadosProces(); 
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
  Object obj  = e.getSource();
    if(obj.equals(cat.getNuevo())){
        cat.dispose();
        this.vista = new EstadoProcesos(principal, true);
        vista.setControlador(this);
        vista.setVisible(true);
    }else if(obj.equals(cat.getSalircat())){
      cat.dispose();
    }else if(obj.equals(vista.getGrabar())){
       if (vista.getEstado().getText().isEmpty()) {
               this.espacioblanco();
            }else if(vista.getEstado().getText().contains("  ")){
                this.parespacios();
            }else
            if(vista.getEstado().getText().length() < 4){
                this.menorletras();
            }else if(capturarcondicion() == null){
              String leyenda = "Es necesario que seleccione una condioion para el nuevo estado";
              vista.getLeyenda().setText(leyenda);
            }else{codigoBotonregistrar();
                    }
    }else if(obj.equals(vista.getModificar())){
           if (vista.getEstado().getText().isEmpty()) {
               this.espacioblanco();
             }
            else if(vista.getEstado().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            if(vista.getEstado().getText().length() < 4){
                this.menorletras();
            }
            else{
                
                codigoBotonModificar();
             }
    }else if(obj.equals(vista.getEliminar())){
       String nomb = vista.getEstado().getText();
            boolean eliminado = false;
            int respuesta = JOptionPane.showConfirmDialog(principal, "¿Desea Eliminar el estado "+nomb+" ?", "Estados de Procesos", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE); 
            if (respuesta == 0) {
             eliminado=model.eliminarModelo(this.idestado);
            }
       
        if (eliminado){
            String Leyenda = "Estado "+nomb+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            codigoBotonLimpiar();
        }else{
            String Leyenda = "El Estado "+nomb+"  no fue eliminado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }  
       
    }else if(obj.equals(vista.getSalir())){
      vista.dispose();
      cat = new EstadoProceso(principal, true);
      cat.setVisible(true);
    }
  }
  
/** 
 * Metodos Propios 
 * 
 */
  public void codigoBotonregistrar(){
        String nomb = vista.getEstado().getText();
        String condicion = capturarcondicion();
        boolean existe = model.verificar(nomb);
            if (existe == true) {
                    String leyenda = "No se pudo registrar el estado de proceso porque ya existe";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(Color.YELLOW);
                }else{
                    model.setNombre(nomb);
                    if(condicion.equalsIgnoreCase("En curso"))  {
                        model.setCondicion(1);
                      }else{
                      model.setCondicion(2);
                    }
                    boolean incluido=model.incluirModelo();
                    if (incluido){
                        String Leyenda = "Estado "+nomb+" Registrado";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                    }else{
                    String leyenda = "No se pudo registrar el Estado de proceso";
                    vista.getLeyenda().setText(leyenda);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
   public void codigoBotonModificar(){
       String nomb = vista.getEstado().getText();  
        model.setNombre(nomb);
        String condicion = capturarcondicion();
        int cond = 0 ;
        if(condicion.equalsIgnoreCase("En Curso")){
          cond = 1;
        }else{
          cond = 2;
        }
        
        boolean modificado=model.modificarModelo(nomb,cond, this.idestado);
        if (modificado){
            String Leyenda = "Estado "+nomb+" modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
        }else{
            String Leyenda = "Estado "+nomb+" no modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }
        codigoBotonLimpiar();
        }//fin de codigoModificar
  private void listarEstados(){
    String[][] informacion = model.ListarOpciones();
        cat.getEstados().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String[] {"Estados de Procesos"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getEstados().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
  }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
     public String capturarcondicion(){
         String condicion = null;
         
          for (Enumeration<AbstractButton > buttons = vista.getCondiciones().getElements(); buttons.hasMoreElements();)
            {
                   AbstractButton button = buttons.nextElement();
                  if(button.isSelected())
                    {
                        condicion = button.getText();

                   }
             } 
          return condicion;
     }
  public void Capturardatos(String nombre){
           boolean encontrado = model.consultarModelo(nombre);
           if (encontrado){
                vista = new EstadoProcesos(principal, true);
                vista.setControlador(this);
                vista.getEstado().setText(model.getNombre());
                if(model.getCondicion() == 1){
                  vista.getEncurso().setSelected(true);
                }else{
                  vista.getFinalizado().setSelected(true);
                }
                this.idestado = model.getId();
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
  public void espacioblanco(){
     String leyenda = "¡No puede registrar espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
 public void codigoBotonLimpiar(){
        vista.getEstado().setText("");
        vista.getEncurso().setSelected(false);
        vista.getFinalizado().setSelected(false);
        vista.getCondiciones().clearSelection();
        vista.getGrabar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false);        
    }//fin de codigoLimpiar
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
            ListadoBusqueda(busqueda,cat.getEstados());
        }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
            try{
                int fila = cat.getEstados().getSelectedRow();
                int fila1 = cat.getEstados().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getEstados().getModel();
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
  
}
