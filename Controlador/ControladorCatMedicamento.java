/*
 * The MIT License
 *
 * Copyright 2017 Jnatn'h.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Controlador;
import Modelo.ModeloMedicamento;
import Vista.CatMedicamento;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import Vista.Medicamento;
import Vista.Principal;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Jnatn'h
 */
public class ControladorCatMedicamento implements ActionListener, KeyListener, MouseListener{
    DefaultTableModel dm;
    CatMedicamento cat; 
    ModeloMedicamento modelo;
    Medicamento vista;
    Principal principal;
    // constructor 
    public ControladorCatMedicamento(CatMedicamento cat){
        this.cat = cat;
        modelo = new ModeloMedicamento();
        this.listarMedicamento();
        
    }
    
    @SuppressWarnings("serial")
	private void listarMedicamento(){
        String[][] informacion =  modelo.consultarListaMedicamento();
        cat.getMedicamento().setModel(
        	new DefaultTableModel(informacion,
        	new String [] {"Medicamento","Pricipio Activo","Cantidad"}) {
        	boolean[] canEdit = new boolean [] {false,false,false};
        	@Override
        	public boolean isCellEditable(int rowIndex, int columnIndex) {
        	return canEdit [columnIndex];
        	}
        	});
    cat.getMedicamento().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre){

           modelo.setNombre(nombre);
           boolean encontrado = false;
        try {
            encontrado = modelo.consultarMedicamento();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCatMedicamento.class.getName()).log(Level.SEVERE, null, ex);
        }
           if (encontrado){
                vista = new Medicamento(principal, true);
                String medicamento = modelo.getNombre();
                String detalle = modelo.getDetalle();
                int cantidad = modelo.getCantidad();
                Date fechavencimiento = modelo.getFechavencimiento();
                String tipoproducto = modelo.getTipoproducto();
                String presentacion = modelo.getPresentacion();
                String principio = modelo.getPrincipioactivo();
                String potencia = modelo.getPotencia();
                
//                System.out.println("medicamento: "+medicamento);
//                System.out.println("Detalle: "+detalle);
//                System.out.println("Cantidad: "+cantidad);
//                System.out.println("fecha; "+fechavencimiento);
//                System.out.println("Tipo producto; "+tipoproducto);
//                System.out.println("Presentacion; "+presentacion);
//                System.out.println("Principio activo; "+principio);
//                System.out.println("Potencia; "+potencia);
                
                vista.getMedicamento().setText(medicamento);
                vista.getDetalle().setText(detalle);
                vista.getCantidad().setValue(cantidad);
                vista.getFechavencimiento().setDate(fechavencimiento);
                vista.getTipoproducto().setSelectedItem(tipoproducto);
                vista.getPresentacion().setSelectedItem(presentacion);
                vista.getPotencia().setSelectedItem(potencia);
                vista.getPrincipioActivo().setSelectedItem(principio);
                
                //Acción de los botones y cajas de texto
                vista.getRegistrar().setEnabled(false);
                vista.getModificar().setEnabled(true);
                
                
                cat.dispose();
                vista.setVisible(true);   
            }else{
                vista.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
}
    @Override
    public void actionPerformed(ActionEvent e) {
     Object origen = e.getSource();
     if(origen.equals(cat.getSalir())){
         cat.dispose();
     }else if(origen.equals(cat.getRegistrar())){
         vista = new Medicamento(principal, true);
         vista.getRegistrar().setEnabled(true);
         vista.getModificar().setEnabled(false);
         vista.getimprimir().setEnabled(false);
         cat.dispose();
         vista.setVisible(true);
     }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
     
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(cat.getBusqueda())){
            String busqueda = cat.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getMedicamento());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            try{
                int fila = cat.getMedicamento().getSelectedRow();
                int fila1 = cat.getMedicamento().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getMedicamento().getModel();
                String captura = (String)modelotabla.getValueAt(fila1, 0);
                Capturardatos(captura);
            }catch(Exception ex){
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
