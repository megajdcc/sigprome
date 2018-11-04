/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPaciente;
import Modelo.Reports;
import java.awt.event.ActionListener;
import Vista.*;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jnatn'h
 */
public class ControllerFevida implements ActionListener, MouseListener, KeyListener{
    
    
    private Fevida vista;
    private Reports reporte;
    private CatPaciente cat;
    private Principal principal;
    private ModeloPaciente modelo;
    private DefaultTableModel dm;
    
    
    private Long cedula = Long.MIN_VALUE;
    private int edad = 0;
    private String nombre = " "; 
    
    public ControllerFevida(Fevida view){
        modelo = new ModeloPaciente();
        reporte = new Reports();
        cat = new CatPaciente(principal,true);
        cat.getNuevopaciente().setVisible(false);
        cat.getRefres().setVisible(false);
        cat.getSalircat().addActionListener((ActionEvent ae) -> { cat.setVisible(false); });
        cat.setController(this);
        listarpaciente();
        this.vista = view;
    }

    
    private void listarpaciente(){
        String[][] informacion =  modelo.ListarPacienteFevida();
        cat.getCatpaciente().setModel(new DefaultTableModel(informacion,
        new String [] {"Cédula", "Nombre", "Apellido", "Tipo de Paciente"}) {
        boolean[] canEdit = new boolean [] {
            false,false,false,false
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
        cat.getCatpaciente().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
    private void capturardatos(Long cedula){
        this.cedula = cedula;
        ArrayList datos = modelo.capturardatos(cedula);
        if(!datos.isEmpty()){
            edad    = (int)datos.get(0);           
            nombre  = (String)datos.get(1);
            vista.getNamepaciente().setText(nombre);
           
            cat.setVisible(false);
     
        }
       
        
    }
    
    /**
     * @param consulta
     * @param JTableBuscar 
     */
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
      Object proviene = ae.getSource();
      
        if(proviene.equals(vista.getBuscarpaciente())){
            cat.setVisible(true);
        }else if(proviene.equals(vista.getProcesar())){
            validar();
        }
    
}
    
    private void validar(){
        if(vista.getNamepaciente().getText().trim().isEmpty()){
            String leyenda = "Porfavor Seleccione a un paciente";
            vista.getLeyenda().setText(leyenda);
        }else if(vista.getCondicion().getText().trim().isEmpty() || vista.getCondicion().getText().contains("  ")){
            String leyenda = "El campo condición salud (no contenga varios espacios en blanco o este vacio)";
            vista.getLeyenda().setText(leyenda);
        }else{
            procesar();
        }
        
    }
    
    private void procesar(){
        
        Object[] opciones = {"Imprimir","Mostrar"};
        int opcion = JOptionPane.showOptionDialog(principal, "Seleccione entre mostrar o imprimir reporte.!", "Reporte de fe de vida", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,opciones,opciones[0]);
        if(opcion == 0){
            reporte.fedevida(cedula, vista.getCondicion().getText(), true);
            String leyenda = "Se ha impreso Correctamente la constancia de fe de vida";
            vista.getLeyenda().setText(leyenda);
            limpiar();
        }else{
            reporte.fedevida(cedula, vista.getCondicion().getText(), false);
            String leyenda = "Se ha Procesado exitosamente el requerimiento";
            vista.getLeyenda().setText(leyenda);
            limpiar();
        }
        
    }
    
    private void limpiar(){
        cedula = null;
        nombre = "";
        edad = 0;
        
        vista.getNamepaciente().setText("");
        vista.getCondicion().setText("");
    }
    @Override
    public void mouseClicked(MouseEvent me) {
      Component origen = me.getComponent();
        if(origen.equals(cat.getCatpaciente())){
          if (me.getClickCount() == 2) {
            try{
                int fila = cat.getCatpaciente().getSelectedRow();
                int fila1 = cat.getCatpaciente().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getCatpaciente().getModel();
                long cedulapaciente = Long.parseLong((String) modelotabla.getValueAt(fila1, 0));
                capturardatos(cedulapaciente);
            }catch(HeadlessException ex){
                System.out.println("Error: "+ex);
            }
            }  
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
      
    }

    @Override
    public void mouseReleased(MouseEvent me) {
      
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
      
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       Component origen = ke.getComponent();
        if(origen.equals(cat.getTextbusqueda())){
             String busqueda = cat.getTextbusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getCatpaciente());
        }
    }

    public Long getCedula() {
        return cedula;
    }

    public int getEdad() {
        return edad;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    
    
    
}
