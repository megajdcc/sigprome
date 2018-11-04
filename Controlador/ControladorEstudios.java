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

import Modelo.ModeloEstudio;
import Vista.CatEstudios;
import Vista.EstudiosEspeciales;
import Vista.Principal;
import static java.awt.Color.orange;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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
public class ControladorEstudios implements ActionListener, KeyListener, MouseListener {
    private int idestudios;
    private DefaultTableModel dm;
    private CatEstudios cat;
    private ModeloEstudio modelo;
    private EstudiosEspeciales vista;
    private Principal  principal;
    public ControladorEstudios(CatEstudios catalogo){
        this.cat = catalogo;
        modelo = new ModeloEstudio();
         this.listarEstudios();
    }
    public ControladorEstudios(EstudiosEspeciales vista){
        this.vista = vista;
        modelo = new ModeloEstudio();
        this.listarEstudios();
    }
    public void listarEstudios(){
        String[][] informacion =  modelo.listarEstudios();
        cat.getEstudios().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Estudios Especialaes"}) {
    boolean[] canEdit = new boolean [] {
        false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getEstudios().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    public void Capturardatos(String nombre) throws SQLException{
           modelo.setNombre(nombre);
           
           boolean encontrado = modelo.consultarEstudio();
           if (encontrado){
                this.idestudios = modelo.getId(); 
                vista = new EstudiosEspeciales(principal, true);
                vista.setController(this);
                vista.getEstudios().setText(modelo.getNombre());
                              
                //Acción de los botones y cajas de texto
                vista.getRegistrar().setEnabled(false);
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
                cat.dispose();
                vista.setVisible(true);   
            }else{
                vista.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
}
        //VALIDAR CARACTERES AL SER PEGADO
    public boolean validarcaracteres(){
         boolean algo = false;
         String nombres = vista.getEstudios().getText(); 
         
         char nom[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','w','x','y','z'};
         if(!nombres.equals(String.valueOf(nom))){
             algo = true;
             String leyenda = "¡No puede registras caraceteres en Nombre!";
                vista.getLeyenda().setText(leyenda);
                vista.getLeyenda().setForeground(orange);
         }
         return algo;
     }
    public void parespacios(){
     String leyenda = "¡No puede registras varios espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
    }
    public void menorletras(){
     String leyenda = "Asegurese de que los campo Nombre no sean menor a 4 letras!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);}
    public void espacioblanco(){
     String leyenda = "¡No puede registras espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);}
    private void limpiar(){
        vista.getEstudios().setText("");
        this.deshabilitar();
    }
    private void deshabilitar(){
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false);
    }
    private void registrar(){
        modelo.setNombre(vista.getEstudios().getText());
        boolean registro = modelo.registrar();
        if(registro){
            String nombre = vista.getEstudios().getText();
            String leyenda = "El Estudio, "+nombre+", ha sido registrado exitosamente";
            vista.getLeyenda().setText(leyenda);
             this.limpiar();
        }else{
            JOptionPane.showMessageDialog(principal, "No se pudo registrar el estudio. Verifique o ponganse en contacto con el administrador");
        }
    }
    private void modificar(){
        modelo.setNombre(vista.getEstudios().getText());
        modelo.setId(this.idestudios);
        boolean modificar = modelo.modificar();
        if(modificar){    
            String nombre = vista.getEstudios().getText();
            String leyenda = "El Estudio, "+nombre+", ha sido modificado exitosamente";
            vista.getLeyenda().setText(leyenda);
            this.limpiar();
        }else{
            JOptionPane.showMessageDialog(principal, "No se pudo modificar el estudio. Verifique o póngase en contacto con el administrador");
        }
    }
    private void eliminar(){
        modelo.setId(this.idestudios);
        boolean eliminar = modelo.eliminar();
        if(eliminar){
            String nombre = vista.getEstudios().getText();
            String leyenda = "El Estudio, "+nombre+", ha sido eliminado exitosamente";
            vista.getLeyenda().setText(leyenda);
            this.limpiar();
        }else{
            JOptionPane.showMessageDialog(principal, "No se pudo eliminar el estudio. Verifique o póngase en contacto con el administrador");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();
        if(origen.equals(cat.getSalircat())){
            cat.dispose();
        }else if(origen.equals(cat.getNuevo())){
            cat.dispose();
            vista = new EstudiosEspeciales(principal, true);
            vista.setController(this);
            this.deshabilitar();
            vista.setVisible(true);
        }else if(origen.equals(vista.getSalir())){
            vista.dispose();
            cat = new CatEstudios(principal,true);
            cat.setVisible(true);
        }else if(origen.equals(vista.getRegistrar())){
            boolean validado = this.validar();
            if(validado){
                this.registrar();
            }
        }else if(origen.equals(vista.getModificar())){
             boolean validado = this.validar();
             if(validado){
                 this.modificar();
             }
        }else if(origen.equals(vista.getEliminar())){
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar el Estudio especial?", "Estudios Especiales", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null, options); 
            if (respuesta == 0) {
                eliminar();
            }
        }
    }
    private boolean validar(){
            boolean validado = false;
            if (vista.getEstudios().getText().isEmpty()){
                this.espacioblanco();
             }else if(vista.getEstudios().getText().contains("  ")){
                 this.parespacios();
             }else if(vista.getEstudios().getText().length() < 4){
                this.menorletras();
             }else{
                 validado = true;
             }
            return validado;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        Component origen = e.getComponent();
        char b = e.getKeyChar();
        if(origen.equals(cat.getBusqueda())){
           if(cat.getBusqueda().getText().length()>100){
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(cat.getBusqueda())){
            String busqueda = cat.getBusqueda().getText().toUpperCase();
            ListadoBusqueda(busqueda,cat.getEstudios());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            try{
                int fila = cat.getEstudios().getSelectedRow();
                int fila1 = cat.getEstudios().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getEstudios().getModel();
                String captura = (String)modelotabla.getValueAt(fila1, 0);
                Capturardatos(captura);
            }catch(SQLException ex){
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
