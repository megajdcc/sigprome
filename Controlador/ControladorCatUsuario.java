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

import Modelo.ModeloEspecialidad;
import Modelo.ModeloEstado;
import Modelo.ModeloParroquia;
import Vista.CatUsuario;
import Modelo.ModeloUsuario;
import Vista.Usuario;
import Vista.Principal;
import Modelo.ModeloTipousuario;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static jdk.nashorn.internal.objects.NativeString.toUpperCase;
/**
 *
 * @author Jnatn'h
 */
public class ControladorCatUsuario implements ActionListener,KeyListener, MouseListener{
    DefaultTableModel dm;
    CatUsuario cat;
    Usuario usuario;
    ModeloUsuario modelo;
    ModeloEstado estado;
    ModeloParroquia parroquia;
    ModeloEspecialidad especialidad;
    Principal principal;
    ModeloTipousuario tipousuario;
    private String nombre;
    public ControladorCatUsuario(CatUsuario cat){
        this.cat  = cat;
        this.listarusuarios();
    }
     public ControladorCatUsuario(Usuario usuario){
        this.usuario  = usuario;
        this.listarusuarios();
    }

    public void listarusuarios(){
        modelo = new ModeloUsuario();
        String[][] informacion =  modelo.ListarUsuario();
        cat.getUsuarios().setModel(new javax.swing.table.DefaultTableModel(
        informacion,
        new String [] {"Nombre", "Apellido", "Usuario", "Tipo de Usuario"}) {
        boolean[] canEdit = new boolean [] {
            false,false,false,false
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
        cat.getUsuarios().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        if (e.getSource() == cat.getNuevo()){
            cat.setVisible(false);
            usuario = new Usuario(principal, true);
            usuario.getEliminar().setEnabled(false);
            usuario.setVisible(true);
        }
        if (e.getSource() == cat.getSalircat()){
            cat.dispose();
        }
        } catch (Exception e2) {
            System.out.println("ERROR EN CONTROLADOR CATUSUARIO: "+e2.getMessage());
        }
    }
// Metodos... 
    public void Capturardatos(String nombre){
           this.nombre = toUpperCase(nombre);
           modelo = new ModeloUsuario(nombre);
           boolean encontrado = modelo.ConsultarUsuario();
           if (encontrado){
                usuario = new Usuario(principal, false, true);
                String cedula = Long.toString(modelo.getCedula());
                if (cedula.length() < 8) {
                  String num = "0";
                  String cedula99 = num+cedula;
                  usuario.getCedula().setText(cedula99);
              }else{
                  usuario.getCedula().setText(cedula); 
              }
                usuario.getCedula().setEnabled(false);
                usuario.getNombre().setText(modelo.getNombre());
                usuario.getApellido().setText(modelo.getApellido());
                usuario.getFechanac().setDate(modelo.getFechanacimiento());
                usuario.getTelefono().setText(Long.toString(modelo.getTelefono()));
                usuario.getDireccion().setText(modelo.getDireccion());
                usuario.getUsuario().setText(modelo.getNombreUsuario());
                usuario.getContrasena().setText(modelo.getContrasena());
                
                String sexo = modelo.getSexo();
                if (sexo.equals("M")) {
                   usuario.getM().setSelected(true);
               }else{
                    usuario.getF().setSelected(true);
                }
                int idparroquia = modelo.getIdparroquia();
                parroquia   = new ModeloParroquia();
                estado      = new ModeloEstado();
                
                String nombestado = estado.capturarestado(idparroquia);
                String municipio = parroquia.capturarmunicipio(idparroquia);
                String nombmunicipio = municipio;
//                System.out.println(this.nombmunicipio);
                DefaultComboBoxModel listaestado = new DefaultComboBoxModel();
                usuario.getEstado().setModel(listaestado);
                boolean resultado = false;
                usuario.getEstado().addItem(nombestado);
                usuario.getMunicipio().setSelectedItem(nombmunicipio);
                resultado = estado.cargarDatosEstado(listaestado);
                
                int idespecialidad = modelo.getEspecialidad();
                especialidad = new ModeloEspecialidad();
                String nombespecialidad = especialidad.capturarnombre(idespecialidad);
                
                DefaultComboBoxModel listaespecialidad = new DefaultComboBoxModel();
                usuario.getEspecialidad().setModel(listaespecialidad);
                boolean resultadoesp = false;
                usuario.getEspecialidad().addItem(nombespecialidad);
                resultadoesp = especialidad.cargarDatosEspecialidad(listaespecialidad);
                
                int idtipousuario = modelo.getTipousuario();
                tipousuario = new ModeloTipousuario();
                tipousuario.setId(idtipousuario);
                String nombtipousuario = tipousuario.capturarnombre();
                if(nombtipousuario.contains("DOC")){
                  usuario.getRif().setEnabled(true);
                  usuario.getMpps().setEnabled(true);
                  usuario.getMpps().setText(Long.toString(modelo.getMpps()));
                  long ri = modelo.getRif();
                  String rif = Long.toString(ri);
                   if (rif.length() < 9) {
                  String num = "0";
                  String cedula99 = num+rif;
                  usuario.getRif().setText(cedula99);
                  }else{
                      usuario.getRif().setText(rif); 
                  }
                }
                DefaultComboBoxModel listatipousuario = new DefaultComboBoxModel();
                usuario.getTipousuario().setModel(listatipousuario);
                usuario.getTipousuario().addItem(nombtipousuario);
                boolean resulttp = tipousuario.cargarNombretipousuario(listatipousuario);
                //Acción de los botones y cajas de texto
                usuario.getGrabar().setEnabled(true);
                usuario.getEliminar().setEnabled(true);
                cat.setVisible(false);
                usuario.setVisible(true);              
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            } 
       }
    public void ListadoBusqueda(String consulta, JTable JTableBuscar){
        dm = (DefaultTableModel) JTableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr  = new TableRowSorter<>(dm);
        JTableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    @Override
    public void keyTyped(KeyEvent e) {
        char b = e.getKeyChar();
        if(cat.getBusqueda().getText().length()>50){
            e.consume();
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
            ListadoBusqueda(busqueda,cat.getUsuarios());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
     if (e.getClickCount() == 2) {
            try{
                int fila = cat.getUsuarios().getSelectedRow();
                int fila1 = cat.getUsuarios().convertRowIndexToModel(fila);
                DefaultTableModel modelotabla=(DefaultTableModel) cat.getUsuarios().getModel();
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
