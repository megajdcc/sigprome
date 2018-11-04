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
import Modelo.ModeloPactivo;
import Modelo.ModeloPotencia;
import Modelo.ModeloPresentacion;
import Modelo.ModeloTipoProducto;
import Vista.CatMedicamento;
import Vista.Medicamento;
import Vista.Principal;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
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
public class ControladorMedicamento implements ActionListener, KeyListener, MouseListener {
    DefaultTableModel dm;
    DefaultComboBoxModel cbtipoproducto,cbpresentacion,cbprincipioactivo,cbpotencia;
    Medicamento vista;
    ModeloMedicamento modelo;
    CatMedicamento cat;
    Principal principal;
    public ControladorMedicamento(Medicamento vista){
        this.vista = vista;
        modelo = new ModeloMedicamento();
         cbtipoproducto = new DefaultComboBoxModel();
         cbpresentacion = new DefaultComboBoxModel();
         cbprincipioactivo = new DefaultComboBoxModel();
         cbpotencia = new DefaultComboBoxModel();
         this.ListarTipoProducto(cbtipoproducto);
         this.ListarPresentacion(cbpresentacion);
         this.ListarPrincipioActivo(cbprincipioactivo);
         this.ListarPotencia(cbpotencia);
         vista.getTipoproducto().setModel(cbtipoproducto);
         vista.getPresentacion().setModel(cbpresentacion);
         vista.getPrincipioActivo().setModel(cbprincipioactivo);
         vista.getPotencia().setModel(cbpotencia);
    }
    public ControladorMedicamento(Medicamento vista, boolean opct){
        this.vista = vista;
        modelo = new ModeloMedicamento();
         cbtipoproducto = new DefaultComboBoxModel();
         cbpresentacion = new DefaultComboBoxModel();
         cbprincipioactivo = new DefaultComboBoxModel();
         cbpotencia = new DefaultComboBoxModel();
         this.ListarTipoProducto(cbtipoproducto);
         this.ListarPresentacion(cbpresentacion);
         this.ListarPrincipioActivo(cbprincipioactivo);
         this.ListarPotencia(cbpotencia);
         vista.getTipoproducto().setModel(cbtipoproducto);
         vista.getPresentacion().setModel(cbpresentacion);
         vista.getPrincipioActivo().setModel(cbprincipioactivo);
         vista.getPotencia().setModel(cbpotencia);
    }
    private void ListarTipoProducto(DefaultComboBoxModel tipoproducto){
        boolean resultado = modelo.cargarTipoproducto(tipoproducto);
    }
    private void ListarPresentacion(DefaultComboBoxModel presentacion){
         boolean resultado = modelo.cargarPresentacion(presentacion);
    }
    private void ListarPrincipioActivo(DefaultComboBoxModel principio){
        boolean resultado = modelo.cargarPrincipioActivo(principio);
    }
    private void ListarPotencia(DefaultComboBoxModel potencia){
        boolean resultado = modelo.cargarPotencia(potencia);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();
        if(origen.equals(vista.getBuscar())){
            cat = new CatMedicamento(principal, true, 1);
            cat.setController(this);
            this.listarMedicamento();
            cat.getRegistrar().setEnabled(false);
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
        }else if(origen.equals(vista.getSalir())){
            cat = new CatMedicamento(principal, true);
            vista.dispose();
            cat.setVisible(true);
        }
    }
    private boolean validar(){
        boolean validado = false;
        Date fech = new Date();
        Date fechavenc = vista.getFechavencimiento().getDate();
        DateFormat fech1 = new SimpleDateFormat("dd/MM/yyyy");
        
        String fecha1 = fech1.format(fech);

//        String fecha2 = fech1.format(fechavenc);
        if(vista.getMedicamento().getText().length() < 3 || vista.getMedicamento().getText().isEmpty()){
            String leyenda = "El nombre del medicamento no puede ser menor a 3 caracteres, o no tener nombre.";
            vista.getLeyenda().setText(leyenda);
            vista.getMedicamento().setCaretColor(Color.red);
        }else if(vista.getMedicamento().getText().contains("  ")){
            String leyenda = "El nombre del medicamento no puede tener varios espacios en blanco.";
            vista.getLeyenda().setText(leyenda);
            vista.getMedicamento().setCaretColor(Color.red);
        }else if(vista.getDetalle().getText().contains("  ")){
            String leyenda = "El detalle del medicamento no puede tener varios espacios en blanco.";
            vista.getLeyenda().setText(leyenda);
            vista.getDetalle().setCaretColor(Color.red);
        }else if(fechavenc == null){
            String leyenda = "Debe seleccionar la fecha de vencimiento del medicamento";
            vista.getLeyenda().setText(leyenda);
        }else if(fechavenc.before(fech)){
            String leyenda = "El medicamento no puede estar vencido.";
            vista.getLeyenda().setText(leyenda);
        }else if(vista.getCantidad().getValue().toString().contains("-")){
            String leyenda = "no puede ingresar productos que sean menores a 0";
            vista.getLeyenda().setText(leyenda);
        }else if(vista.getTipoproducto().getSelectedItem().equals("Seleccione")){
            String leyenda = "Debe seleccionar un tipo de producto";
            vista.getLeyenda().setText(leyenda);
        }else if(vista.getPresentacion().getSelectedItem().equals("Seleccione")){
            String leyenda = "Debe seleccionar una presentación";
            vista.getLeyenda().setText(leyenda);
        }else if(vista.getPrincipioActivo().getSelectedItem().equals("Seleccione")){
            String leyenda = "Debe seleccionar un principio activo";
            vista.getLeyenda().setText(leyenda);
        }else if(vista.getPotencia().getSelectedItem().equals("Seleccione")){
            String leyenda = "Debe seleccionar una potencia";
            vista.getLeyenda().setText(leyenda);
        }else{
            validado = true;
        }
        return validado;
    }
    private void registrar(){
        modelo.setNombre(vista.getMedicamento().getText());
        modelo.setDetalle(vista.getDetalle().getText());
        modelo.setFechavencimiento(vista.getFechavencimiento().getDate());
        modelo.setCantidad((Integer) vista.getCantidad().getValue());
        ModeloTipoProducto tp = new ModeloTipoProducto();
        tp.setNombre(vista.getTipoproducto().getSelectedItem().toString());
        int idtipoproducto = tp.capturarId();
        modelo.setIdtipoproducto(idtipoproducto);
        ModeloPresentacion pre = new ModeloPresentacion();
        pre.setNombre(vista.getPresentacion().getSelectedItem().toString());
        int idpresentacion = pre.capturarId();
        modelo.setIdpresentacion(idpresentacion);
        ModeloPactivo pa = new ModeloPactivo();
        pa.setNombre(vista.getPrincipioActivo().getSelectedItem().toString());
        int idprincipio = pa.capturarId();
        modelo.setIdprincipioactivo(idprincipio);
        ModeloPotencia pote = new ModeloPotencia();
        pote.setNombre(vista.getPotencia().getSelectedItem().toString());
        int idpotencia = pote.capturarId();
        modelo.setIidpotencia(idpotencia);
       boolean registro = modelo.registrar();
       if(registro){    
           String leyenda = "El Medicamento, "+vista.getMedicamento().getText()+", Ha sido registrado exitosamente";
           vista.getLeyenda().setText(leyenda);
           this.limpiar();
       }
       
    }
    private void limpiar(){
        vista.getMedicamento().setText("");
        vista.getDetalle().setText("");
        vista.getFechavencimiento().setDate(null);
        vista.getCantidad().setValue(0);
        vista.getTipoproducto().setSelectedItem("Seleccione");
        vista.getPresentacion().setSelectedItem("Seleccione");
        vista.getPotencia().setSelectedItem("Seleccione");
        vista.getPrincipioActivo().setSelectedItem("Seleccione");
        
        vista.getRegistrar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getimprimir().setEnabled(true);
    }
    private void modificar(){
        modelo.setNombre(vista.getMedicamento().getText());
        modelo.setDetalle(vista.getDetalle().getText());
        modelo.setFechavencimiento(vista.getFechavencimiento().getDate());
        modelo.setCantidad((Integer) vista.getCantidad().getValue());
        ModeloTipoProducto tp = new ModeloTipoProducto();
        tp.setNombre(vista.getTipoproducto().getSelectedItem().toString());
        int idtipoproducto = tp.capturarId();
        modelo.setIdtipoproducto(idtipoproducto);
        ModeloPresentacion pre = new ModeloPresentacion();
        pre.setNombre(vista.getPresentacion().getSelectedItem().toString());
        int idpresentacion = pre.capturarId();
        modelo.setIdpresentacion(idpresentacion);
        ModeloPactivo pa = new ModeloPactivo();
        pa.setNombre(vista.getPrincipioActivo().getSelectedItem().toString());
        int idprincipio = pa.capturarId();
        modelo.setIdprincipioactivo(idprincipio);
        ModeloPotencia pote = new ModeloPotencia();
        pote.setNombre(vista.getPotencia().getSelectedItem().toString());
        int idpotencia = pote.capturarId();
        modelo.setIidpotencia(idpotencia);
        
        boolean modificar = modelo.modificar();
         if(modificar){    
           String leyenda = "El Medicamento, "+vista.getMedicamento().getText()+", Ha sido modificado exitosamente";
           vista.getLeyenda().setText(leyenda);
           this.limpiar();
       }
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
                String medicamento = modelo.getNombre();
                String detalle = modelo.getDetalle();
                int cantidad = modelo.getCantidad();
                Date fechavencimiento = modelo.getFechavencimiento();
                String tipoproducto = modelo.getTipoproducto();
                String presentacion = modelo.getPresentacion();
                String principio = modelo.getPrincipioactivo();
                String potencia = modelo.getPotencia();
                
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
                   
            }else{
                vista.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
}
     private void listarMedicamento(){
        String[][] informacion =  modelo.consultarListaMedicamento();
        cat.getMedicamento().setModel(new javax.swing.table.DefaultTableModel(
    informacion,
    new String [] {"Medicamento","Pricipio Activo"}) {
    boolean[] canEdit = new boolean [] {
        false,false
    };
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
    }
});
    cat.getMedicamento().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        Object origen = e.getSource();
        if(origen.equals(cat.getMedicamento())){
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
