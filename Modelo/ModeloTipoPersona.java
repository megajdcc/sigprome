/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import Vista.Principal;
/**
 *
 * @author Jnatn'h
 */
public class ModeloTipoPersona {
    private String nombre;
    private boolean validarfechanac = false;
    int id;
    
    ModeloConexion conexion;
    Principal p;
    
    public  JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
    public ModeloTipoPersona(){  conexion = ModeloConexion.conexion;}
    public ModeloTipoPersona(String nombre,boolean validarfechanac){
          conexion = ModeloConexion.conexion;
       this.setNombre(nombre);
       this.validarfechanac = validarfechanac;
    }
    public ModeloTipoPersona(String nombre, int id, boolean validarfechanac) {
          conexion = ModeloConexion.conexion;
        this.setNombre(nombre);
        this.setId(id);
        this.validarfechanac = validarfechanac;
    }
//    Getter y Setter... 
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
//    Methodos u acciones necesarias... 
    public boolean consultarModelo(String nombre){   
        boolean statusConsulta=false;    
        
            String sentenciaSQL = "SELECT * FROM tipopersona WHERE nombre='"+nombre+"';";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setNombre(resultadoConsulta.getString("nombre"));
                    this.setId(resultadoConsulta.getInt("id"));
                    
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipopaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
          
            return statusConsulta;
            
        }//fin de consultar
    public boolean incluirModelo(){
        boolean statusIncluir;
    
        String sentenciaSql = "INSERT INTO tipopersona(nombre,validafechanac,actualizado) VALUES('"+this.nombre+"', '"+this.validarfechanac+"',false)";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir = true;
        }else{
            statusIncluir = false;
        }
        
      
        return statusIncluir;
    }//fin de incluir
    public boolean verificar(String nombre){
        boolean existe = false;
           
            String sentenciaSQL = "SELECT * FROM tipopersona WHERE nombre='"+nombre+"';";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                   existe = true;
                }else{
                    existe = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipoPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            return existe;
    }
    public boolean modificarModelo(String nombre, int idtipopersona,boolean validafechanac){
        boolean statusModificar;
        this.validarfechanac = validafechanac;
        
        String sentenciaSql = "UPDATE tipopersona SET nombre='"+nombre+"', validafechanac = '"+this.validarfechanac+"', actualizado = false WHERE id='"+idtipopersona+"';";
        int ejecutado;
        ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
      
        return statusModificar;
    }//fin de modificar
    public boolean eliminarModelo(String nombre){
        boolean statusEliminar;
    
        
        
        String sentenciaSql = "DELETE FROM tipopersona WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        
     
        return statusEliminar;
    }//fin de eliminar
    public String[][] FiltroTipopersona(String nombre){
            boolean statusConsulta=false;
           
            String sentenciaSQL = "SELECT nombre FROM tipopersona WHERE nombre LIKE '%"+nombre+"%'";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null) return null;
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][1];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    datos[i][0]=resultadoConsulta.getString("nombre");                   
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
    //Filtrar busquedas en el catalogo de patologia... 
    public String[][] filtrartipopersona(String busqueda){
       
        String sentenciaSQL = "SELECT nombre FROM tipopersona WHERE nombre LIKE '%"+busqueda+"%';";
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
        
        if(resultadoConsulta==null) return null;
        try{
            int i=0;
            while(resultadoConsulta.next())
                i++;
            String[][] Data = new String[i][1];
            resultadoConsulta.beforeFirst();
            i=0;
            while(resultadoConsulta.next()){ 
                Data[i][0]=resultadoConsulta.getString("nombre");  
            }
            return Data;
        } catch (SQLException ex) {
            return null;
        }
    }
    public String[][] consultarListaTipopersona(){
            boolean statusConsulta = false;
            
            String sentenciaSQL = "SELECT nombre FROM tipopersona";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null)
            {
                String Error = "error en la consulta";

               return null;
            }
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][1];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                   datos[i][0] = resultadoConsulta.getString("nombre");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                    return null;
            }
        }
    public boolean Listartipopersona(DefaultComboBoxModel comboModel){
             boolean sw=false; 
	     
             String sentencia = "SELECT * FROM tipopersona;";
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){
			   
        try {
            comboModel.addElement("Seleccione");
            while (resul.next()) {
                sw=true;
                comboModel.addElement(resul.getString("nombre")); 
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ModeloTipoPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
	     return sw;
        }//fin de cargarDatos
    public int capturarid(String nombre) throws SQLException{
     
      String sentenciasql = "select id from tipopersona where nombre = '"+nombre+"'"; 
      ResultSet resul = conexion.ejecutarConsulta(sentenciasql);
      if (resul.next()){   
        try {
              id =  resul.getInt("id");
              return id;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Error al capturar el id de tipopersona","ModeloTipoPersona",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ModeloTipoPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
           
	     return id ;
  }
    public boolean capturarvalicionfechanac(String dato){
      
        boolean resultado = false;
        String query = "select validafechanac as validacion from tipopersona where nombre = '"+dato+"'";
        ResultSet captura = conexion.ejecutarConsulta(query);
        if(captura != null){
            try {
                captura.next();
                resultado = captura.getBoolean("validacion");
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipoPersona.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        return resultado;
    }
}
