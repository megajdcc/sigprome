/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.Catprincipioactivo;
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
import javax.swing.table.DefaultTableModel;
import Modelo.ModeloPactivo;
import Vista.Pactivo;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jnatn'h
 */
public class ControladorCatprincipioactivo implements ActionListener, KeyListener,MouseListener{
    DefaultTableModel dm;
    String nombre;
    Catprincipioactivo pactivo;
    ModeloPactivo modelo;
    Pactivo vista;
    Principal principal;
    
    public ControladorCatprincipioactivo(Catprincipioactivo pactivo){
        this.pactivo = pactivo;
        this.ListarPactivo();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == pactivo.getRegistrar()) 
        {
            pactivo.setVisible(false);
            vista= new Pactivo(principal, true);
            vista.getModificar().setEnabled(false);
            vista.getEliminar().setEnabled(false);
            vista.setVisible(true);
        }else if(e.getSource() == pactivo.getSalir())
        {
            pactivo.dispose();   
        }
    }
    public void ListarPactivo(){
        modelo = new ModeloPactivo();
        String[][] informacion =  modelo.ListarPactivo();
        pactivo.getPactivo().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Principio activo"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    pactivo.getPactivo().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre){
           this.nombre = nombre;
           modelo = new ModeloPactivo();
           boolean encontrado = modelo.ConsultarPactivo(nombre);
           if (encontrado){
                vista = new Pactivo(principal, true);
                vista.getTextpactivo().setText(modelo.getNombre());
                int idpactivo = modelo.getId();                
                vista.Idpactivo(idpactivo);
                
              
                //Acción de los botones y cajas de texto
                vista.getRegistrar().setEnabled(false);
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
                pactivo.setVisible(false);
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

        if(pactivo.getBusqueda().getText().length()>50){
            e.consume();
        }
    }
private void validarcampo(KeyEvent e){
        String caracter  = pactivo.getBusqueda().getText();
     char b = e.getKeyChar();
        if(!caracter.isEmpty())
        caracter=caracter.substring(0,caracter.length()-1);
        caracter+=String.valueOf(b).toUpperCase();
        pactivo.getBusqueda().setText(caracter);
}
    @Override
    public void keyPressed(KeyEvent e) {
      
    }
    @Override
    public void keyReleased(KeyEvent e) {
       Object origen = e.getSource();
        if(origen.equals(pactivo.getBusqueda())){
            String busqueda = pactivo.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,pactivo.getPactivo());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getClickCount() == 2) {
            try{
                int fila = pactivo.getPactivo().getSelectedRow();
                int fila1 = pactivo.getPactivo().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) pactivo.getPactivo().getModel();
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
