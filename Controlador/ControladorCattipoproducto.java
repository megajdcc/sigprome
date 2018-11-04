/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTipoProducto;
import Vista.Principal;
import Vista.TipoProducto;
import Vista.CatTipoproducto;
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
public class ControladorCattipoproducto implements KeyListener, ActionListener, MouseListener{
    DefaultTableModel dm;
    ModeloTipoProducto modelo;
    CatTipoproducto cat;
    TipoProducto Tipoproducto;
    Principal principal;
    private String nombre;
    
    public ControladorCattipoproducto(CatTipoproducto cat){
        this.cat = cat;
        this.ListaTipoproducto();
    }
    public ControladorCattipoproducto(TipoProducto Tipoproducto){
        this.Tipoproducto = Tipoproducto;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cat.getNuevotipoproducto()) 
        {
            cat.setVisible(false);
            TipoProducto tipoproducto = new TipoProducto(principal, true);
            tipoproducto.getModificar().setEnabled(false);
            tipoproducto.getEliminar().setEnabled(false);
            tipoproducto.setVisible(true);
        }else if(e.getSource() == cat.getSalircat())
        {
            cat.setVisible(false);
            cat.dispose();
        }
    }
    public void ListaTipoproducto (){
        modelo = new ModeloTipoProducto();
        String[][] informacion =  modelo.consultarListaTipoproducto();
        DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Tipos de Productos"});
       cat.getCattipoproducto().setModel(new javax.swing.table.DefaultTableModel(
        informacion,
        new String [] {"Tipos de Productos"}) {
        boolean[] canEdit = new boolean [] {
            false
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
        cat.getCattipoproducto().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));   
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre){
           this.nombre = nombre;
           modelo = new ModeloTipoProducto();
           boolean encontrado = modelo.consultarModelo(nombre);
           if (encontrado){
                Tipoproducto = new TipoProducto(principal, true);
                Tipoproducto.getTexttipoproducto().setText(modelo.getNombre());
                int idtipoproducto = modelo.getId();
                Tipoproducto.Idtipoproducto(idtipoproducto);
                //Acción de los botones y cajas de texto
                Tipoproducto.getRegistrar().setEnabled(false);
                Tipoproducto.getModificar().setEnabled(true);
                Tipoproducto.getEliminar().setEnabled(true);
                cat.setVisible(false);
                Tipoproducto.setVisible(true); 
            }
            else
            {
                Tipoproducto.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
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
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       Object origen = e.getSource();
        if(origen.equals(cat.getTextbusqueda())){
            String busqueda = cat.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getCattipoproducto());
        }
    }

   

    @Override
    public void mouseClicked(MouseEvent e) {
     if (e.getClickCount() == 2) {
            try{
                int fila = cat.getCattipoproducto().getSelectedRow();
                int fila1 = cat.getCattipoproducto().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCattipoproducto().getModel();
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
