/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTipoorden;
import Vista.Principal;
import Vista.TipoOrden;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Vista.cattiporden;
import java.awt.HeadlessException;
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
public class ControladorCattipoorden implements KeyListener, ActionListener, MouseListener{   
    DefaultTableModel dm;
    ModeloTipoorden modelo;
    cattiporden cat;
    TipoOrden Tipoorden;
    Principal principal;
    private String nombre;
    public ControladorCattipoorden(cattiporden cat){
        this.cat = cat;
    }
    public ControladorCattipoorden(TipoOrden Tipoorden){
        this.Tipoorden = Tipoorden;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == cat.getNuevatipoorden()) 
        {
            cat.setVisible(false);
            TipoOrden tipoorden = new TipoOrden(principal, true);
            tipoorden.getModificar().setEnabled(false);
            tipoorden.getEliminar().setEnabled(false);
            tipoorden.setVisible(true);
        }else if(e.getSource() == cat.getSalircat())
        {
            cat.setVisible(false);
            cat.dispose();
        }
    }  
    public void ListaTipoorden (){
        modelo = new ModeloTipoorden();
        String[][] informacion =  modelo.consultarListaTipoorden();
         cat.getCattipoorden().setModel(new javax.swing.table.DefaultTableModel(
        informacion,
        new String [] {"Tipos de Órdenes"}) {
        boolean[] canEdit = new boolean [] {
            false
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
});
    cat.getCattipoorden().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));  
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre){
           this.nombre = nombre;
           modelo = new ModeloTipoorden();
           boolean encontrado = modelo.consultarModelo(nombre);
           if (encontrado){
                Tipoorden = new TipoOrden(principal, true);
                Tipoorden.getTexttipoorden().setText(modelo.getNombre());
                int idtipoorden = modelo.getId();
                Tipoorden.Idtipoorden(idtipoorden);
                //Acción de los botones y cajas de texto
                Tipoorden.getRegistrar().setEnabled(false);
                Tipoorden.getModificar().setEnabled(true);
                Tipoorden.getEliminar().setEnabled(true);
                cat.setVisible(false);
                Tipoorden.setVisible(true);
            }else{
                Tipoorden.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
       }
    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(cat.getTextbusqueda())){
            String busqueda = cat.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getCattipoorden());
        }
    }
     @Override
    public void keyTyped(KeyEvent e) {
        char b = e.getKeyChar();
        if(cat.getTextbusqueda().getText().length()>50){
            e.consume();
        }
    }
    @Override
    public void keyPressed(KeyEvent e){
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
            try{
                int fila = cat.getCattipoorden().getSelectedRow();
                int fila1 = cat.getCattipoorden().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCattipoorden().getModel();
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
