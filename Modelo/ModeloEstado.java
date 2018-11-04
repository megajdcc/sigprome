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
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jnatn'h
 */
public class ModeloEstado {
    private int id;
    private String nombre;
    
    ModeloConexion conexion;
    
    public JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
     public ModeloEstado() {
         conexion = ModeloConexion.conexion;
    }

    public ModeloEstado(int id) {
          conexion = ModeloConexion.conexion;
        this.id = id;
    }

    public ModeloEstado(int id, String nombre) {
          conexion = ModeloConexion.conexion;
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
	public boolean GuardarEstado(){
	   boolean sw=false;
          
           String sentencia="";
           System.out.println("Insertar: "+sentencia);
	   int m = conexion.ejecutarActualizacion(sentencia);
           if (m!=0)
		   sw = true;
	   
	   return sw;
	}
	public boolean ModificarEstado(){
	   boolean sw=false;
	   
           String sentencia="";
           System.out.println("Modificar: "+sentencia);
	   int m = conexion.ejecutarActualizacion(sentencia);
	   if (m!=0)
		   sw = true;
	
	   return sw;
	}
	
	public boolean EliminarEstado(int cod){
	   boolean sw=false;
	   
           String sentencia="";
	    int m= conexion.ejecutarActualizacion(sentencia);
            if (m!=0)
		   sw = true;
	  
	   return sw;
	}
//	public boolean ConsultarEstadoPorCodigo(int cod) throws SQLException{
//		   boolean sw; 
//		   conexion = new ModeloConexion();
//                   String sentencia ="SELECT * FROM estado WHERE id="+cod+";";
//		   ResultSet resul = conexion.ejecutarConsulta(sentencia);
//		   if (resul != null)
//		   {
//			   	resul.next();
//		                this.setId(resul.getInt(1));
//                                this.setOpcion(resul.getString(2));
//		   		sw = true;
//		   }
//		   else
//		   {
//			   sw = false;  
//		   }	 
//		   conexion.cerrar();
//		   return sw;
//		}
        
        
//        public boolean ConsultarEstadoPorNombre(String nombre) throws SQLException{
//		   boolean sw; 
//		   conexion = new ModeloConexion();
//                   String sentencia ="SELECT * FROM estado WHERE nombre='"+nombre+"';";
//                   System.out.println("sentencia");
//		   ResultSet resul = conexion.ejecutarConsulta(sentencia);
//		   if (resul != null)
//		   {
//			   	resul.next();
//		                this.setId(resul.getInt(1));
//                                this.setOpcion(resul.getString(2));
//		   		sw = true;
//		   }
//		   else
//		   {
//			   sw = false;  
//		   }	 
//		   conexion.cerrar();
//		   return sw;
//		}
        
         public boolean cargarDatosEstado(DefaultComboBoxModel comboModel){
             boolean sw=false; 
	  
             String sentencia = "select * from listarestado";
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){		   
        try {
//            comboModel.addElement("Seleccione el Estado");                  
            while (resul.next()) {
                sw=true;
                comboModel.addElement(resul.getObject("nombre"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
	     return sw;
        }//fin de cargarDatos
    


    private void setOpcion(int string) {
       
    }
    public String capturarestado(int parroquia)
    {
       String estado = "";
       
       String sentencia = "select est.nombre from estado as est join municipio as mun on (est.id = mun.idestado) join parroquia as parr \n" +
                        "on(mun.id = parr.idmunicipio) where parr.id = '"+parroquia+"'";
       ResultSet result = conexion.ejecutarConsulta(sentencia);
        if (result != null) {
            try {
                result.next();
                estado = result.getString("nombre");
            } catch (SQLException e) {
                System.out.println("ERROR: "+ e.getMessage());
            }
        }
        return estado;
    }
    public int capturarid(String nombreestado){
        
        
        String sentencia = "Select id from estado where nombre = '"+nombreestado+"'";
        ResultSet result = conexion.ejecutarConsulta(sentencia);
        if (result != null) {
            try {
                result.next();
                this.id = result.getInt("id");
                
            } catch (SQLException e) {
                  System.out.println("ERROR: "+ e.getMessage());
            }
        }
        return this.getId();
    }
}
