/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPotencia;
import Vista.CatPotencia;
import Vista.Potencia;
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
public class ControladorCatPotencia implements ActionListener, KeyListener,MouseListener{
    
    DefaultTableModel dm;
    String nombre;
    CatPotencia potencia;
    ModeloPotencia modelo;
    Potencia vista;
    Principal principal;
    
    public ControladorCatPotencia(CatPotencia potencia){
        this.potencia = potencia;
        this.ListarPotencia();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == potencia.getRegistrar()) 
        {
            potencia.setVisible(false);
            vista= new Potencia(principal, true);
            vista.getModificar().setEnabled(false);
            vista.getEliminar().setEnabled(false);
            vista.setVisible(true);
        }else if(e.getSource() == potencia.getSalir())
        {
            potencia.dispose();   
        }
    }
    public void ListarPotencia(){
        modelo = new ModeloPotencia();
        String[][] informacion =  modelo.ListarPotencia();
        potencia.getPotencia().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Potencia"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    potencia.getPotencia().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
           modelo = new ModeloPotencia();
           boolean encontrado = modelo.ConsultarPotencia(nombre);
           if (encontrado){
                vista = new Potencia(principal, true);
                vista.getTextpotencia().setText(modelo.getNombre());
                int idpotencia = modelo.getId();                
                vista.Idpotencia(idpotencia);
                
              
                //Acción de los botones y cajas de texto
                vista.getRegistrar().setEnabled(false);
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
                potencia.setVisible(false);
                vista.setVisible(true);
                
              
            }
            else
            {
//                Potencia.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
           
       }
    @Override
    public void keyTyped(KeyEvent e) {
        char b = e.getKeyChar();

        if(potencia.getBusqueda().getText().length()>50){
            e.consume();
        }
    }
    private void validarcampo(KeyEvent e){
        String caracter  = potencia.getBusqueda().getText();
     char b = e.getKeyChar();
        if(!caracter.isEmpty())
        caracter=caracter.substring(0,caracter.length()-1);
        caracter+=String.valueOf(b).toUpperCase();
        potencia.getBusqueda().setText(caracter);
}
    @Override
    public void keyPressed(KeyEvent e) {
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
       Object origen = e.getSource();
        if(origen.equals(potencia.getBusqueda())){
            String busqueda = potencia.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,potencia.getPotencia());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
     if (e.getClickCount() == 2) {
            try{
                int fila = potencia.getPotencia().getSelectedRow();
                int fila1 = potencia.getPotencia().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) potencia.getPotencia().getModel();
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
