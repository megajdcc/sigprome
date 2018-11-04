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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
/**
 *
 * @author Jnatn'h
 */
public class ModeloBitacora {
    private ModeloConexion conexion;
    private String operacion;
    private String tabla;
    private String nuevovalor;
    private String viejovalor;
    private Date fecha;
    private String hora;
    private String usuario;
    private String tipousuario;
     
    // getters y setters 
    public String getOperacion(){
        return this.operacion;
    }
    public String getTabla(){
        return this.tabla;
    }
    public String getNuevovalor(){
        return this.nuevovalor;
    }
    public String getViejovalor(){
        return this.viejovalor;
    }
    public String getFecha(){
        String fech = String.valueOf(this.fecha);
        return fech;
    }
    public String getHora(){
        return this.hora;
    }
    public String getUsuario(){
        return this.usuario;
    }
    public String getTipousuario(){
        return this.tipousuario;
    }
   // Constructor 
    public ModeloBitacora(){
        conexion = new ModeloConexion();
    }
    
    // metodos
    public String[][] ListarBitacora(){
        String[][] datos = null;
        String query = "select * from listabitacora";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if(consulta != null ){
              int i = 0;
            try {
                while(consulta.next()) i++;
                datos = new String[i][6];
                i = 0;
                consulta.beforeFirst();
                while(consulta.next()){
                    datos[i][0]=consulta.getString("operacion");
                    datos[i][1]=consulta.getString("tabla");  
                    datos[i][2]=consulta.getString("usuario"); 
                    datos[i][3]=consulta.getString("tipousuario");
                    datos[i][4]=consulta.getString("fecha"); 
                    datos[i][5]=consulta.getString("hora");  
                    i++;
                }
                
            } catch (SQLException ex) {
                return null;
            } 
        }
        return datos;
    }
    public boolean capturarDatos(String capt, String capt1, String capt2, String capt3 , String capt4, String capt5) throws SQLException{
        ResultSet rs;
        boolean encontrado = false;
        String query = "SELECT * from bitacora where operacion = '"+capt+"' and tabla = '"+capt1+"' and usuario = '"+capt2+"'"
                +" and tipousuario = '"+capt3+"' and fecha = '"+capt4+"' and hora = '"+capt5+"'";
        rs = conexion.ejecutarConsulta(query);
         if(rs != null){
             rs.next();
             encontrado = true;
             this.fecha = rs.getDate("fecha");
             this.hora  = rs.getString("hora");
             this.tabla = rs.getString("tabla");
             this.nuevovalor = rs.getString("nuevo_valor");
             this.viejovalor = rs.getString("viejo_valor");
             this.usuario = rs.getString("usuario");
             this.tipousuario = rs.getString("tipousuario");
             this.operacion = rs.getString("operacion");
         }
        return encontrado;
    }
    public boolean listarusuario(DefaultComboBoxModel model){
        boolean listado = false;
        String query = "select * from listarusuario";
        ResultSet resul = conexion.ejecutarConsulta(query);
        if (resul != null){		   
        try {
            model.addElement("Seleccione");
            while (resul.next()) {
                listado=true;
                model.addElement(resul.getObject("usuario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
	return listado;
    }
    public boolean listaroperacion(DefaultComboBoxModel model){
        boolean listado = false;
        String query = "select * from listaroperacion";
        ResultSet resul = conexion.ejecutarConsulta(query);
        if (resul != null){		   
        try {
            model.addElement("Seleccione");
            while (resul.next()) {
                listado=true;
                model.addElement(resul.getObject("operacion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
	return listado;
    }
}
