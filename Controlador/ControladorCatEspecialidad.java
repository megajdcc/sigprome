/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloEspecialidad;
import Vista.CatEspecialidad;
import Vista.Especialidad;
import Vista.Principal;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jnatn'h
 */
public class ControladorCatEspecialidad implements ActionListener, KeyListener,MouseListener{
    DefaultTableModel dm;
    String nombre;
    CatEspecialidad especialidad;
    ModeloEspecialidad modelo;
    Especialidad vista;
    Principal principal;
    
    public ControladorCatEspecialidad(CatEspecialidad especialidad){
        this.especialidad = especialidad;
        this.ListarEspecialidad();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == especialidad.getRegistrar()) 
        {
            especialidad.setVisible(false);
            vista= new Especialidad(principal, true);
            vista.getModificar().setEnabled(false);
            vista.getEliminar().setEnabled(false);
            vista.setVisible(true);
        }else if(e.getSource() == especialidad.getSalir())
        {
            especialidad.dispose();   
        }
    }
    public void ListarEspecialidad(){
        modelo = new ModeloEspecialidad();
        String[][] informacion =  modelo.ListarEspecialidad();
        DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Especialidad"});
        especialidad.getEspecialidad().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Especialidad"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    especialidad.getEspecialidad().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre){
           this.nombre = nombre;
//           System.out.println(nombre);
           modelo = new ModeloEspecialidad();
           boolean encontrado = modelo.ConsultarEspecialidad(nombre);
           if (encontrado){
                vista = new Especialidad(principal, true);
                vista.getTextespecialidad().setText(modelo.getNombre());
                int idespecialidad = modelo.getId();                
                vista.Idespecialidad(idespecialidad);
                
              
                //Acción de los botones y cajas de texto
                vista.getRegistrar().setEnabled(false);
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
                especialidad.setVisible(false);
                vista.setVisible(true);
                
              
            }
            else
            {
//                Especialidad.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
           
       }
    @Override
    public void keyTyped(KeyEvent e) {
        char b = e.getKeyChar();

        if(especialidad.getBusqueda().getText().length()>50){
            e.consume();
        }
    }
    private void validarcampo(KeyEvent e){
        String caracter  = especialidad.getBusqueda().getText();
     char b = e.getKeyChar();
        if(!caracter.isEmpty())
        caracter=caracter.substring(0,caracter.length()-1);
        caracter+=String.valueOf(b).toUpperCase();
        especialidad.getBusqueda().setText(caracter);
}
    @Override
    public void keyPressed(KeyEvent e) {
      
    }
    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(especialidad.getBusqueda())){
            String busqueda = especialidad.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,especialidad.getEspecialidad());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getClickCount() == 2) {
            try{
                int fila = especialidad.getEspecialidad().getSelectedRow();
                int fila1 = especialidad.getEspecialidad().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) especialidad.getEspecialidad().getModel();
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
