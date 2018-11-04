/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloExamen;
import Vista.CatExamen;
import Vista.Examen;
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
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jnatn'h
 */
public class ControladorCatExamen implements ActionListener, KeyListener,MouseListener{
    DefaultTableModel dm;
    String nombre;
    CatExamen examen;
    ModeloExamen modelo;
    Examen vista;
    Principal principal;
    
    public ControladorCatExamen(CatExamen examen){
        this.examen = examen;
        this.ListarExamen();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == examen.getRegistrar()) {
            examen.setVisible(false);
            vista = new Examen(principal, true);
            vista.getModificar().setEnabled(false);
            vista.getEliminar().setEnabled(false);
            vista.setVisible(true);
        }else if(e.getSource() == examen.getSalir())
        {
            examen.dispose();   
        }
    }
    public void ListarExamen(){
        modelo = new ModeloExamen();
        String[][] informacion =  modelo.ListarExamen();
        examen.getExamen().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Examen"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    examen.getExamen().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
           modelo = new ModeloExamen();
           boolean encontrado = modelo.ConsultarExamen(nombre);
           if (encontrado){
                vista = new Examen(principal, true);
                vista.getTextexamen().setText(modelo.getNombre());
                int idexamen = modelo.getId();                
                vista.Idexamen(idexamen);
                
              
                //Acción de los botones y cajas de texto
                vista.getRegistrar().setEnabled(false);
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
                examen.setVisible(false);
                vista.setVisible(true);
                
              
            }
            else
            {
//                Examen.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
           
       }
    @Override
    public void keyTyped(KeyEvent e) {
        char b = e.getKeyChar();

        if(examen.getBusqueda().getText().length()>50){
            e.consume();
        }
    }
private void validarcampo(KeyEvent e){
        String caracter  = examen.getBusqueda().getText();
     char b = e.getKeyChar();
        if(!caracter.isEmpty())
        caracter=caracter.substring(0,caracter.length()-1);
        caracter+=String.valueOf(b).toUpperCase();
        examen.getBusqueda().setText(caracter);
}
    @Override
    public void keyPressed(KeyEvent e) {
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(examen.getBusqueda())){
            String busqueda = examen.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,examen.getExamen());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            try{
                int fila = examen.getExamen().getSelectedRow();
                int fila1 = examen.getExamen().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) examen.getExamen().getModel();
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
