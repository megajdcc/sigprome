/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTipoPersona;
import Vista.CatTipopersona;
import Vista.TipoPersona;
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
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;

/**
 *
 * @author Jnatn'h
 */
public class ControladorCattipopersona implements KeyListener, ActionListener, MouseListener{
    DefaultTableModel dm;
    ModeloTipoPersona modelo;
    CatTipopersona cat;
    TipoPersona Tipopersona;
    Principal principal;
    private String nombre;
    
     public ControladorCattipopersona(CatTipopersona cat)
    {
        this.cat = cat;
    }
    public ControladorCattipopersona(TipoPersona Tipopersona)
    {
        this.Tipopersona = Tipopersona;
    }
    @Override
     public void actionPerformed(ActionEvent e) {
          if (e.getSource() == cat.getNuevatipopersona()) 
        {
            cat.setVisible(false);
            TipoPersona tipopersona = new TipoPersona(principal, true);
            tipopersona.getModificar().setEnabled(false);
            tipopersona.getEliminar().setEnabled(false);
            tipopersona.setVisible(true);
        }else if(e.getSource() == cat.getSalircat())
        {
            cat.setVisible(false);
            cat.dispose();
        }
     }

   
    public void ListaTipopersona (){
        modelo = new ModeloTipoPersona();
        String[][] informacion =  modelo.consultarListaTipopersona();
        DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Tipos de Paciente"});
       cat.getCattipopersona().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Tipos de Paciente"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getCattipopersona().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre){
           this.nombre = nombre;
           modelo = new ModeloTipoPersona();
           boolean encontrado = modelo.consultarModelo(nombre);
           if (encontrado){
                Tipopersona = new TipoPersona(principal, true);
                Tipopersona.getTexttipopersona().setText(modelo.getNombre());
                int idtipopersona = modelo.getId();
                Tipopersona.Idtipopersona(idtipopersona);
                //Acción de los botones y cajas de texto
                Tipopersona.getRegistrar().setEnabled(false);
                Tipopersona.getModificar().setEnabled(true);
                Tipopersona.getEliminar().setEnabled(true);
                cat.setVisible(false);
                Tipopersona.setVisible(true);
            }else{
                Tipopersona.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            } 
       }
    @Override
    public void keyPressed(KeyEvent e) {
        
    }
     @Override
    public void keyTyped(KeyEvent e) {
        char b = e.getKeyChar();

        if(cat.getTextbusqueda().getText().length()>50){
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       Object origen = e.getSource();
        if(origen.equals(cat.getTextbusqueda())){
            String busqueda = cat.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getCattipopersona());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
     if (e.getClickCount() == 2) {
            try{
                int fila = cat.getCattipopersona().getSelectedRow();
                int fila1 = cat.getCattipopersona().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCattipopersona().getModel();
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
