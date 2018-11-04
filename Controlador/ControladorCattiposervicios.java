/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloTiposervicios;
import Vista.Principal;
import Vista.TipoServicios;
import Vista.CatTiposervicios;
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
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jnatn'h
 */
public class ControladorCattiposervicios implements KeyListener, ActionListener, MouseListener{
    DefaultTableModel dm;
    ModeloTiposervicios modelo;
    CatTiposervicios cat;
    TipoServicios Tiposervicios;
    Principal principal;
    
    private String nombre;
    
    public ControladorCattiposervicios(CatTiposervicios cat)
    {
        this.cat = cat;
    }
    public ControladorCattiposervicios(TipoServicios Tiposervicios)
    {
        this.Tiposervicios = Tiposervicios;
    }

 @Override
    public void actionPerformed(ActionEvent e) 
    {
         if (e.getSource() == cat.getNuevotiposervicio()) 
        {
            cat.setVisible(false);
            TipoServicios tiposervicios = new TipoServicios(principal, true);
            tiposervicios.getModificar().setEnabled(false);
            tiposervicios.getEliminar().setEnabled(false);
            tiposervicios.setVisible(true);
        }else if(e.getSource() == cat.getSalircat())
        {
            cat.setVisible(false);
            cat.dispose();
        }
    }
    public void ListaTiposervicios (){
        modelo = new ModeloTiposervicios();
        String[][] informacion =  modelo.consultarListaTiposervicios();
        cat.getCattiposervicios().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Tipos de Servicios"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getCattiposervicios().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
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
           modelo = new ModeloTiposervicios();
           boolean encontrado = modelo.consultarModelo(nombre);
           if (encontrado){
                Tiposervicios = new TipoServicios(principal, true);
                //System.out.println(patologia.getNombre());
                Tiposervicios.getTexttiposervicios().setText(modelo.getNombre());
               // vista.getIdPatologia().setIdPatologia(modelo.getId());
                int idtiposervicios = modelo.getId();
                //System.out.println(idpatologia);
               // String Idpatologia = Integer.toString(idpatologia);
                
                Tiposervicios.Idtiposervicios(idtiposervicios);
                
              
                //Acción de los botones y cajas de texto
                Tiposervicios.getRegistrar().setEnabled(false);
                Tiposervicios.getModificar().setEnabled(true);
                Tiposervicios.getEliminar().setEnabled(true);
                cat.setVisible(false);
                Tiposervicios.setVisible(true);
                
              
            }
            else
            {
                Tiposervicios.getRegistrar().setEnabled(true);
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
            ListadoBusqueda(busqueda,cat.getCattiposervicios());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
     if (e.getClickCount() == 2) {
            try{
                int fila = cat.getCattiposervicios().getSelectedRow();
                int fila1 = cat.getCattiposervicios().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCattiposervicios().getModel();
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
    

    
}
