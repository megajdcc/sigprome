/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTipousuario;
import Vista.CatTipousuario;
import Vista.Tipousuario;
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
public class ControladorCatTipousuario implements ActionListener, KeyListener,MouseListener{
    DefaultTableModel dm;
    String nombre;
    CatTipousuario tipousuario;
    ModeloTipousuario modelo;
    Tipousuario vista;
    Principal principal;
    
    public ControladorCatTipousuario(CatTipousuario tipousuario){
        this.tipousuario = tipousuario;
        this.ListarTipousuario();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == tipousuario.getRegistrar()) 
        {
            tipousuario.setVisible(false);
            vista= new Tipousuario(principal, true);
            vista.getModificar().setEnabled(false);
            vista.getEliminar().setEnabled(false);
            vista.setVisible(true);
        }else if(e.getSource() == tipousuario.getSalir())
        {
            tipousuario.dispose();   
        }
    }
    public void ListarTipousuario()
    {
        modelo = new ModeloTipousuario();
        String[][] informacion =  modelo.ListarTipousuario();
        DefaultTableModel model = new DefaultTableModel(informacion, new String[]{"Tipo de usuario"});
         tipousuario.getTipousuario().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Tipo de usuario"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    tipousuario.getTipousuario().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
           modelo = new ModeloTipousuario();
           boolean encontrado = modelo.ConsultarTipousuario(nombre);
           if (encontrado){
                vista = new Tipousuario(principal, true);
                vista.getTexttipousuario().setText(modelo.getNombre());
                int idtipousuario = modelo.getId();                
                vista.Idtipousuario(idtipousuario);
                
              
                //Acción de los botones y cajas de texto
                vista.getRegistrar().setEnabled(false);
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
                tipousuario.setVisible(false);
                vista.setVisible(true);
                
              
            }
            else
            {
//                Tipousuario.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
           
       }
    @Override
    public void keyTyped(KeyEvent e) {
        char b = e.getKeyChar();

        if(tipousuario.getBusqueda().getText().length()>50){
            e.consume();
        }
    }
private void validarcampo(KeyEvent e)
    {
        String caracter  = tipousuario.getBusqueda().getText();
     char b = e.getKeyChar();
        if(!caracter.isEmpty())
        caracter=caracter.substring(0,caracter.length()-1);
        caracter+=String.valueOf(b).toUpperCase();
        tipousuario.getBusqueda().setText(caracter);
}
    @Override
    public void keyPressed(KeyEvent e) {
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(tipousuario.getBusqueda())){
            String busqueda = tipousuario.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,tipousuario.getTipousuario());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getClickCount() == 2) {
            try{
                int fila = tipousuario.getTipousuario().getSelectedRow();
                int fila1 = tipousuario.getTipousuario().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) tipousuario.getTipousuario().getModel();
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
