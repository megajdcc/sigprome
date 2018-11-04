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
import Modelo.ModeloBitacora;
import Modelo.ModeloUsuario;
import Vista.Bitacora;
import Vista.Principal;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Jnatn'h
 */
public class ControllerBitacora implements ActionListener,KeyListener,MouseListener, FocusListener {
    DefaultTableModel dm;
    Bitacora bitacora; 
    ModeloBitacora modelo;
    Principal principal;
    public ControllerBitacora(Bitacora bita){
        this.bitacora = bita;
        modelo = new ModeloBitacora();
        this.ListarBitacora();
        this.ListarUsuarios();
        this.ListarOperacion();
    }
    public void ListarBitacora(){
        String[][] datos = modelo.ListarBitacora();
        if(datos != null){
           // DefaultTableModel model = new DefaultTableModel(datos, new String[]{"Operación", "Tabla", "Usuario", "Tipo de usuario", "Fecha", "Hora"});
            bitacora.getBitacora().setModel(new javax.swing.table.DefaultTableModel(
    datos,
    new String [] {
        "Operación", "Tabla", "Usuario", "Tipo de usuario", "Fecha", "Hora"
    }
) {
    boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    bitacora.getBitacora().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    }
    public void ListarOperacion(){
        DefaultComboBoxModel listaroperacion = new DefaultComboBoxModel();
        boolean lista = modelo.listaroperacion(listaroperacion);
        if(lista){
            bitacora.getOperacion().setModel(listaroperacion);
        }
    }
    public void ListarUsuarios(){
       
        DefaultComboBoxModel listausuario = new DefaultComboBoxModel();
        
        boolean lista = modelo.listarusuario(listausuario);
        if (lista){
           bitacora.getUsuarios().setModel(listausuario);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       Object origen = e.getSource();
       if(origen.equals(bitacora.getSalir())){
           bitacora.dispose();
       }else if (origen.equals(bitacora.getAceptar())){
           this.capturardatos();
       }else if(origen.equals(bitacora.getUsuarios())){
            String busqueda = bitacora.getUsuarios().getSelectedItem().toString();
            this.ListadoBusqueda(busqueda,bitacora.getBitacora());
//          bitacora.getOperacion().setSelectedItem("Seleccione");
       }else if(origen.equals(bitacora.getOperacion())){
            String busqueda = bitacora.getOperacion().getSelectedItem().toString();
//            bitacora.getUsuarios().setSelectedItem("Seleccione");
            this.ListadoBusqueda(busqueda,bitacora.getBitacora());
       }
    }
    public void Capturardatos(String capt, String capt1, String capt2, String capt3 , String capt4, String capt5){
        boolean encontrado = false;
        try {
            encontrado = modelo.capturarDatos(capt,capt1, capt2, capt3 , capt4, capt5);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerBitacora.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(encontrado){
            String operacion = modelo.getOperacion();
            String tabla = modelo.getTabla();
            String usuario = modelo.getUsuario();
            String tipousuario = modelo.getTipousuario();
            String fecha = modelo.getFecha();
            String hora = modelo.getHora();
            String nuevovalor = modelo.getNuevovalor();
            String viejovalor = modelo.getViejovalor();
            String leyenda = "El usuario: "+usuario + "\nTipo de usuario: "+tipousuario+ "\n"+
                    "Operación: "+operacion+"\n"+
                    "Tabla: "+tabla+" \n" + "Nuevo Valor: " + nuevovalor + "\n"
                    + "Viejo Valor: "+viejovalor+ "\n"
                    + "Fecha: "+fecha+"\n"
                    +"Hora: "+hora+"\n";
            JOptionPane.showMessageDialog(principal, leyenda, "Operación de "+operacion, 0);
                    
        }
    }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
        int rows = tr.getModelRowCount();
     //s   tr.convertRowIndexToView(rows);
    }
    @Override
    public void keyTyped(KeyEvent e) {
       if(bitacora.getBusqueda().getText().length()>30){
            e.consume();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
       
    }
    @Override
    public void keyReleased(KeyEvent e) {
        Object origen = e.getSource();
        if(origen.equals(bitacora.getBusqueda())){
            String busqueda = bitacora.getBusqueda().getText();
            ListadoBusqueda(busqueda,bitacora.getBitacora());
        }
    }
    private void capturardatos(){
                int fila = bitacora.getBitacora().getSelectedRow();
                
                int fila1 = bitacora.getBitacora().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) bitacora.getBitacora().getModel();
                
                String capt,capt1, capt2, capt3 , capt4, capt5; 
                capt  = (String)modelotabla.getValueAt(fila1, 0);
                capt1 = (String)modelotabla.getValueAt(fila1, 1);
                capt2 = (String)modelotabla.getValueAt(fila1, 2);
                capt3 = (String)modelotabla.getValueAt(fila1, 3);
                capt4 = (String)modelotabla.getValueAt(fila1, 4);
                capt5 = (String)modelotabla.getValueAt(fila1, 5);
                Capturardatos(capt, capt1, capt2, capt3 , capt4, capt5);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
      Object origen = e.getSource();
      if (origen.equals(bitacora.getBitacora())){
          if (e.getClickCount() == 2) {
            try{
               this.capturardatos();
            }catch(HeadlessException ex){
                System.out.println("Error: " + e);
            }
    }else if(e.getClickCount() == 1){
      bitacora.getAceptar().setEnabled(true);
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
    @Override
    public void focusGained(FocusEvent e) {
           Component origen = e.getComponent();
         if(origen.equals(bitacora.getOperacion())){
             String leyend = "Filtre la tabla por el tipo de operación";
             bitacora.getLeyenda().setText(leyend);
         }else if(origen.equals(bitacora.getBitacora())){
             String leyend = "Los datos registrados por acciones de usuarios en el sistema";
              bitacora.getLeyenda().setText(leyend);
         }else if(origen.equals(bitacora.getImpresion())){
             String leyend = "Imprima los datos filtrados o totales de la bitácora";
            bitacora.getLeyenda().setText(leyend);
         }else if(origen.equals(bitacora.getSalir())){
              String leyend = "Salir de la ventana bitácora";
                bitacora.getLeyenda().setText(leyend);
         }else if (origen.equals(bitacora.getLimpiar())){
              String leyend = "Guarde el registro de la bitácora y limpiela";
             bitacora.getLeyenda().setText(leyend);
         }else if(origen.equals(bitacora.getAceptar())){
              String leyend = "Acepte para ver los datos completos del registro seleccionado";
             bitacora.getLeyenda().setText(leyend);
         }else if(origen.equals(bitacora.getBusqueda())){
             String leyend = "Escriba todo lo que desee y vea si se encuentra en la tabla...";
             bitacora.getLeyenda().setText(leyend);
         }else if (origen.equals(bitacora.getUsuarios())){
             String leyend = "Filtre la búsqueda por usuario";
             bitacora.getLeyenda().setText(leyend);
         }else{
             String leyend = "La bitácora es el armario de acciones de los usuarios en el sistema";
             bitacora.getLeyenda().setText(leyend);
         }
    }
    @Override
    public void focusLost(FocusEvent e) {
       
    }
}
