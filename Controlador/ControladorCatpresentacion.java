/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.ModeloPresentacion;
import Vista.CatPresentacion;
import Vista.Principal;
import Vista.Presentacion;
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
public class ControladorCatpresentacion implements KeyListener, ActionListener, MouseListener{
    DefaultTableModel dm;
    ModeloPresentacion modelo;
    CatPresentacion cat;
    Presentacion presentacion;
    Principal principal;
    private String nombre;
    
    public ControladorCatpresentacion(CatPresentacion cat){
        this.cat = cat;
        this.ListaPresentacion();
    }
    public ControladorCatpresentacion(Presentacion presentacion){
        this.presentacion = presentacion;
        this.ListaPresentacion();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == cat.getNuevapresentacion()) 
        {
            cat.setVisible(false);
            Presentacion presentacio = new Presentacion(principal, true);
            presentacio.getModificar().setEnabled(false);
            presentacio.getEliminar().setEnabled(false);
            presentacio.setVisible(true);
        }else if(e.getSource() == cat.getSalircat())
        {
            cat.setVisible(false);
            cat.dispose();
        }
    }
    public void ListaPresentacion (){
        modelo = new ModeloPresentacion();
        String[][] informacion =  modelo.consultarListaPresentacion();
         cat.getCatpresentacion().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Presentación"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getCatpresentacion().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
           modelo = new ModeloPresentacion();
           boolean encontrado = modelo.consultarModelo(nombre);
           if (encontrado){
                presentacion = new Presentacion(principal, true);
                //System.out.println(patologia.getNombre());
                presentacion.getTextpresentacion().setText(modelo.getNombre());
               // vista.getIdPatologia().setIdPatologia(modelo.getId());
                int idpresentacion = modelo.getId();
                //System.out.println(idpatologia);
               // String Idpatologia = Integer.toString(idpatologia);
                
                presentacion.Idpresentacion(idpresentacion);
                
              
                //Acción de los botones y cajas de texto
                presentacion.getRegistrar().setEnabled(false);
                presentacion.getModificar().setEnabled(true);
                presentacion.getEliminar().setEnabled(true);
                cat.setVisible(false);
                presentacion.setVisible(true);
                
              
            }
            else
            {
                presentacion.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
           
       }
    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(cat.getTextbusqueda())){
            String busqueda = cat.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getCatpresentacion());
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
                int fila = cat.getCatpresentacion().getSelectedRow();
                int fila1 = cat.getCatpresentacion().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCatpresentacion().getModel();
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
