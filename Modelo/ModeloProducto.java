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
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Jnatn'h
 */
abstract public class ModeloProducto {
    
    // class extern
    ModeloConexion conexion;
    // propiedades ... 
    static private int id;
    protected String nombre;
    protected String detalle;
    protected int cantidad;
    protected int idtipoproducto;
    //propiedad relacional
    protected String tipoproducto;
    
    //Constructor 
    public ModeloProducto(){
         conexion = ModeloConexion.conexion;
    }
    public ModeloProducto(int id){
        setId(id);
        conexion = ModeloConexion.conexion;
    }
    public ModeloProducto(String product){
        setNombre(product);
        conexion = ModeloConexion.conexion;
    }
            
    // getters y setters 
    public void setId(int id){
        ModeloProducto.id = id;
    }
    static public int getId(){
        return ModeloProducto.id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setDetalle(String detail){
        this.detalle = detail;
    }
    public String getDetalle(){
        return this.detalle;
    }
    public void setCantidad(int cant){
        this.cantidad = cant;
    }
    public int getCantidad(){
        return this.cantidad;
    }
    public void setIdtipoproducto(int tipoproduct){
        this.idtipoproducto = tipoproduct;
    }
    public int getIdtipoproducto(){
        return this.idtipoproducto;
    }
    public String getTipoproducto(){
        return this.tipoproducto;
    }
    //metodos ... 
    abstract public boolean registrar();
//        boolean registr = false;
//        String query = "INSERT INTO producto (nombre, detalle, cantidad, idtipoproducto) values"
//                + "('"+getNombre()+"','"+getDetalle()+"',"+getCantidad()+","+getIdtipoproducto()+")";
//        int registro = conexion.ejecutarActualizacion(query);
//        if(registro != 0){
//            registr = true;
//        }
//        return registr;
   abstract public boolean modificar();
//        boolean modif = false;
//        String query = "UPDATE producto set nombre = '"+getNombre()+"',detalle = '"+getDetalle()+"',cantidad = "+getCantidad()+","
//                + "idtipoproducto = "+getIdtipoproducto()+" where id = "+getId()+"";
//        int update = conexion.ejecutarActualizacion(query);
//        if(update != 0){
//            modif = true;
//        }
//        return modif;
    abstract public boolean eliminar();
//        boolean delet = false;
//        String query = "DELETE FROM producto where id = "+getId()+"";
//        int delete = conexion.ejecutarActualizacion(query);
//        if(delete != 0){
//            delet = true;
//        }
//        return delet;
    public boolean cargarTipoproducto(DefaultComboBoxModel tipoproducto){
        boolean sw = false; 
            String sentencia = "select nombre from tipoproducto";
            ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){		   
        try {
            tipoproducto.addElement("Seleccione");                  
            while (resul.next()) {
                sw=true;
                tipoproducto.addElement(resul.getObject("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
	     return sw;
    }
}
