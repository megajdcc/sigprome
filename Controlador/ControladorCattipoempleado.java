/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloTipoEmpleado;
import Vista.Principal;
import Vista.TipoEmpleado;
import Vista.CatTipoempleado;
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
public class ControladorCattipoempleado implements KeyListener, ActionListener, MouseListener{
    
    DefaultTableModel dm;
    ModeloTipoEmpleado modelo;
    CatTipoempleado cat;
    TipoEmpleado Tipoempleado;
    Principal principal;
    private String nombre;
    
    public ControladorCattipoempleado(CatTipoempleado cat){
        this.cat = cat;
        this.ListaTipoempleado();
    }
    public ControladorCattipoempleado(TipoEmpleado Tipoempleado){
        this.Tipoempleado = Tipoempleado;
    }
     @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == cat.getNuevoTipoempleado()) 
        {
            cat.setVisible(false);
            TipoEmpleado tipoempleado = new TipoEmpleado(principal, true);
            tipoempleado.getModificar().setEnabled(false);
            tipoempleado.getEliminar().setEnabled(false);
            tipoempleado.setVisible(true);
        }else if(e.getSource() == cat.getSalircat())
        {
            cat.setVisible(false);
            cat.dispose();
        }
    }
    public void ListaTipoempleado (){
        modelo = new ModeloTipoEmpleado();
        String[][] informacion =  modelo.consultarListaTipoempleado();
        cat.getCattipoempleado().setModel(new javax.swing.table.DefaultTableModel(
        informacion,
        new String [] {"Tipos de Empleados"}) {
        boolean[] canEdit = new boolean [] {
            false
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
});
    cat.getCattipoempleado().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre){
           this.nombre = nombre;
           modelo = new ModeloTipoEmpleado();
           boolean encontrado = modelo.consultarModelo(nombre);
           if (encontrado){
                Tipoempleado = new TipoEmpleado(principal, true);
                Tipoempleado.getTexttipoempleado().setText(modelo.getNombre());
                int idtipoempleado = modelo.getId();
                Tipoempleado.Idtipoempleado(idtipoempleado);
                //Acción de los botones y cajas de texto
                Tipoempleado.getRegistrar().setEnabled(false);
                Tipoempleado.getModificar().setEnabled(true);
                Tipoempleado.getEliminar().setEnabled(true);
                cat.setVisible(false);
                Tipoempleado.setVisible(true);
            }else{
                Tipoempleado.getRegistrar().setEnabled(true);
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
            ListadoBusqueda(busqueda,cat.getCattipoempleado());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getClickCount() == 2) {
            try{
                int fila = cat.getCattipoempleado().getSelectedRow();
                int fila1 = cat.getCattipoempleado().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCattipoempleado().getModel();
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
