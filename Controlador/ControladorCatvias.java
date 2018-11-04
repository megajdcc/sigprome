/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.CatVias;
import Modelo.Modelovias;
import Vista.Vias;
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
public class ControladorCatvias implements KeyListener, ActionListener, MouseListener{
    DefaultTableModel dm;
    CatVias cat;
    Modelovias modelo;
    Vias vias;
    Principal principal;
    
    private String nombre;
    
    public ControladorCatvias(CatVias cat)
    {
        this.cat = cat;
    }
    public ControladorCatvias(Vias vias)
    {
        this.vias = vias;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
       if (e.getSource() == cat.getNuevavia()) 
        {
            cat.setVisible(false);
            
            Vias via = new Vias(principal, true);
            
            via.getModificar().setEnabled(false);
            via.getEliminar().setEnabled(false);
            via.setVisible(true);
        }else if(e.getSource() == cat.getSalircat())
        {
            cat.setVisible(false);
            cat.dispose();
        }
    }
    public void Listavias (){
        modelo = new Modelovias();
        String[][] informacion =  modelo.consultarListaVias();
        cat.getCatvias().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Vías"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getCatvias().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
    }
    
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre)
       {
           this.nombre = nombre;
//           System.out.println(nombre);
           modelo = new Modelovias();
           boolean encontrado = modelo.consultarModelo(nombre);
           if (encontrado){
                vias = new Vias(principal, true);
                vias.getTextvias().setText(modelo.getNombre());
                int idvias = modelo.getId();
                
                vias.Idvias(idvias);
             
                //Acción de los botones y cajas de texto
                vias.getRegistrar().setEnabled(false);
                vias.getModificar().setEnabled(true);
                vias.getEliminar().setEnabled(true);
                cat.setVisible(false);
                vias.setVisible(true);
                
              
            }
            else
            {
                vias.getRegistrar().setEnabled(true);
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
            ListadoBusqueda(busqueda,cat.getCatvias());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 2) {
            try{
                int fila = cat.getCatvias().getSelectedRow();
                int fila1 = cat.getCatvias().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCatvias().getModel();
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
