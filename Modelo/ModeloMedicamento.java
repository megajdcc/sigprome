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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

import org.postgresql.util.PSQLException;

/**
 *
 * @author Jnatn'h
 */
public class ModeloMedicamento extends ModeloProducto {

    
    // propiedades 
    static private int id;
    private Date fechavencimiento;
    private int idprincipio_activo;
    private int idpotencia;
    private int idpresentacion;
    private int idproducto;
    
    
    //variables relacional 
    private String potencia;
    private String presentacion;
    private String principioactivo;
    // constructor
    public ModeloMedicamento(){
          conexion = ModeloConexion.conexion;
    }
    // getters y setters.
    @Override
    public void setId(int id){
        ModeloMedicamento.id = id;
    }

    static public int getId(){
        return ModeloMedicamento.id;
    }
    public void setFechavencimiento(Date fecha){
     this.fechavencimiento = fecha;   
    }
    public Date getFechavencimiento(){
        return this.fechavencimiento;
    }
    public void setIdprincipioactivo(int id){
        this.idprincipio_activo = id;
    }
    public int getIdprincipioactivo(){
        return this.idprincipio_activo;
    }
    public void setIidpotencia(int potencia){
        this.idpotencia = potencia;
    }
    public int getIdpotencia(){
        return this.idpotencia;
    }
    public void setIdpresentacion(int id){
        this.idpresentacion = id;
    }
    public int getIdpresentacion(){
        return this.idpresentacion;
    }
    
    public String getPotencia(){
        return this.potencia;
    }
    public String getPrincipioactivo(){
        return this.principioactivo;
    }
    public String getPresentacion(){
        return this.presentacion;
    }
    // metodos

    @Override
    public boolean registrar() {
        boolean registr = false;
        String query = "INSERT INTO producto (nombre, detalle, cantidad, id_tipoproducto) values"
                + "('"+getNombre()+"','"+getDetalle()+"',"+getCantidad()+","+getIdtipoproducto()+")";
        int registro = conexion.ejecutarActualizacion(query);
        if(registro != 0){
            super.setId(this.capturaridproducto(super.getNombre()));
            String query1 = "INSERT into medicamento (fechavencimiento, id_presentacion, id_principioactivo, id_potencia, id_producto)"
                    + "values ('"+getFechavencimiento()+"',"+getIdpresentacion()+","+getIdprincipioactivo()+","+getIdpotencia()+","
                    + ""+ModeloProducto.getId()+")";
            int consul = conexion.ejecutarActualizacion(query1);
            if(consul != 0){
                registr = true;
            }
        }
        return registr;
    }
    @Override
    public boolean modificar() {
        boolean modificacion = false;
        String query = "UPDATE producto set nombre = '"+getNombre()+"',detalle = '"+getDetalle()+"',cantidad = "+getCantidad()+","
                + "id_tipoproducto = "+getIdtipoproducto()+" where id = "+ModeloProducto.getId()+"";
        int update = conexion.ejecutarActualizacion(query);
        if(update != 0){
            String query1 = "UPDATE medicamento set fechavencimiento = '"+this.getFechavencimiento()+"', id_presentacion = "+this.getIdpresentacion()+","
                    + "id_principioactivo = "+this.getIdprincipioactivo()+", id_potencia = "+this.getIdpotencia()+","
                    + "id_producto = "+ModeloProducto.getId()+" where id = "+ModeloMedicamento.getId()+"";
            int consul1 = conexion.ejecutarActualizacion(query1);
            if(consul1 != 0){
                modificacion = true;
            }
        }
        return modificacion;
    }
    @Override
    public boolean eliminar() {
         boolean delet = false;
        String query = "DELETE FROM producto where id = "+ModeloProducto.getId()+"";
        int delete = conexion.ejecutarActualizacion(query);
        if(delete != 0){
            String query1 = "DELETE FROM medicamento where id = "+ModeloMedicamento.getId()+"";
            int deletee = conexion.ejecutarActualizacion(query1);
            if(deletee != 0){
                delet = true;
            }
        }
        return delet;
    }
    
    private int capturaridproducto(String nombre){
        int idproduc = 0 ;
        String nomb = nombre.toUpperCase();
        String queryy = "SELECT id from producto where nombre = '"+nomb+"'";
        ResultSet consulta = conexion.ejecutarConsulta(queryy);
        if(consulta != null){
            try {
                consulta.next();
                idproduc = consulta.getInt("id");
            } catch (SQLException ex) {
                Logger.getLogger(ModeloMedicamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idproduc;
    }
    
    public String[][] consultarListaMedicamento(){
    		String[][] datos =null ;
    		Connection conec = conexion.getConec();
            String sentenciaSQL = "select * from listarmedicamentos";
            
            PreparedStatement medicamentos = null;
            
            try {
				medicamentos = conec.prepareStatement(sentenciaSQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				ResultSet result = medicamentos.executeQuery();
			    
				int i = 0;
				
				while(result.next()) i++;
                datos = new String[i][3];
                i = 0;
                
                result.beforeFirst();
                while(result.next()){
                   datos[i][0] = result.getString("medicamento");
                   datos[i][1] = result.getString("principio");
                   datos[i][2] = result.getString("cantidad");
                    i++;
                }
			}catch (PSQLException ex) {
				ex.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
            return datos;
    }
    public boolean consultarMedicamento() throws SQLException{
        boolean encontrado = false;
        String query = "select * from capturarmedicamento('"+super.getNombre()+"')";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if(consulta != null){
            consulta.next();
            super.setId(consulta.getInt("idproducto"));
            super.setNombre(consulta.getString("medicamento"));
            super.setCantidad(consulta.getInt("cantidad"));
            super.setDetalle(consulta.getString("detalle"));
            super.setIdtipoproducto(consulta.getInt("idtipoprodcuto"));
            super.tipoproducto = consulta.getString("tipoproducto");
            
            this.setId(consulta.getInt("idmedicamento"));
            this.setFechavencimiento(consulta.getDate("fechavencimiento"));
            this.setIdpresentacion(consulta.getInt("idpresentacion"));
            this.setIdprincipioactivo(consulta.getInt("idprincipioactivo"));
            this.setIidpotencia(consulta.getInt("idpotencia"));
            
            this.potencia = consulta.getString("potencia");
            this.presentacion = consulta.getString("presentacion");
            this.principioactivo = consulta.getString("principio");
            encontrado = true;
        }
        return encontrado;
    }
    public boolean cargarPresentacion(DefaultComboBoxModel presentacion){
        boolean sw = false; 
            String sentencia = "select nombre from presentacion";
            ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){		   
        try {
            presentacion.addElement("Seleccione");                  
            while (resul.next()) {
                sw=true;
                presentacion.addElement(resul.getObject("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
	     return sw;
    }
    public boolean cargarPrincipioActivo(DefaultComboBoxModel principio){
        boolean sw = false; 
            String sentencia = "select nombre from principioactivo";
            ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){		   
        try {
            principio.addElement("Seleccione");                  
            while (resul.next()) {
                sw=true;
                principio.addElement(resul.getObject("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
	     return sw;
    }
    public boolean cargarPotencia(DefaultComboBoxModel potencia){
        boolean sw = false; 
            String sentencia = "select nombre from potencia";
            ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){		   
        try {
            potencia.addElement("Seleccione");                  
            while (resul.next()) {
                sw=true;
                potencia.addElement(resul.getObject("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
	return sw;
    }
}
