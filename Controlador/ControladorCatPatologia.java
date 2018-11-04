/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPatologia;
import Modelo.Reports;
import Vista.catapatologia;
import Vista.Patologias;
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

public class ControladorCatPatologia implements KeyListener, ActionListener, MouseListener{
    DefaultTableModel dm;
    ModeloPatologia patologia;
    private Reports reporte;
    public catapatologia cat;
//    Patologias pat;
    Principal principal;
    private String nombre;
    private Patologias pa;
    
    public ControladorCatPatologia(catapatologia cat){
        this.cat = cat;
        patologia = new ModeloPatologia();
        pa = new Patologias(principal, true);
        pa.setBack(cat);
        this.ListaPatologia();
    }
    public ControladorCatPatologia(Patologias pat){
        this.pa = pat;
        patologia = new ModeloPatologia();
        pa = new Patologias(principal, true);
        pa.setBack(cat);
        this.ListaPatologia();
    }

    @Override
    public void actionPerformed(ActionEvent evento)
    {
        if (evento.getSource().equals(cat.getNuevapat())) {
            cat.setVisible(false);
            pa.setVisible(true);
            pa.getModificar().setEnabled(false);
            pa.getEliminar().setEnabled(false);
            pa.setEnabled(true);      
        }else if(evento.getSource() == cat.getSalircat()){
            //patologia.CerrarConexion();
            cat.setVisible(false);
            cat.dispose();
           
        }else if(evento.getSource().equals(cat.getImprimir())){
            
            Runnable rep = new report();
            Thread t = new Thread(rep);
            t.start();
        }
    }
    
    public void ListaPatologia (){
        
        String[][] informacion =  patologia.consultarListaPatologia();
        cat.getCatpatologia().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Patologías"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getCatpatologia().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre){
           this.nombre = nombre;
           boolean encontrado = patologia.consultarModelo(nombre);
           if (encontrado){
                pa.getTextpatologia().setText(patologia.getNombre());
                pa.getdescripcion().setText(patologia.getDescripcion());
                int idpatologia = patologia.getId();                
                pa.IdPatologia(idpatologia);
                //Acción de los botones y cajas de texto
                pa.getRegistrar().setEnabled(false);
                pa.getModificar().setEnabled(true);
                pa.getEliminar().setEnabled(true);
                cat.setVisible(false);
                pa.setVisible(true);   
            }else{
                pa.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
}
    @Override
    public void keyReleased(KeyEvent e){
        Object origen = e.getSource();
        if(origen.equals(cat.getTextbusqueda())){
            String busqueda = cat.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getCatpatologia());
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
    public void mouseClicked(MouseEvent e){
      if (e.getClickCount() == 2) {
            try{
                int fila = cat.getCatpatologia().getSelectedRow();
                int fila1 = cat.getCatpatologia().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCatpatologia().getModel();
                String captura = (String)modelotabla.getValueAt(fila1, 0);
                Capturardatos(captura);
            }catch(HeadlessException ex){
                System.out.println("Error: "+e);
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
    
    
    class report implements Runnable{

        @Override
        public void run(){
          reporte = new Reports();
          reporte.listpatologia();
        }
    }
    
}
