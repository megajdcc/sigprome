/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class ModeloMunicipio {
    private int id, relacion;
    private String nombre;
          
    private ModeloConexion conexion;

    public ModeloMunicipio() {
    }

    public ModeloMunicipio(int id) {
        this.id = id;
    }

    public ModeloMunicipio(int id, int relacion, String nombre) {
        this.id = id;
        this.relacion = relacion;
        this.nombre = nombre;
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpcion() {
        return nombre;
    }

    public void setOpcion(String nombre) {
        this.nombre = nombre;
    }

    public int getRelacion() {
        return relacion;
    }

    public void setRelacion(int relacion) {
        this.relacion = relacion;
    }
    
    
    
	public boolean GuardarMunicipio(){
	   boolean sw=false;
           conexion = new ModeloConexion();
           String sentencia="";
           System.out.println("Insertar: "+sentencia);
	   int m = conexion.ejecutarActualizacion(sentencia);
           if (m!=0)
		   sw = true;
	   conexion.cerrar();
	   return sw;
	}
	public boolean ModificarMunicipio(){
	   boolean sw=false;
	   conexion = new ModeloConexion();
           String sentencia="";
           System.out.println("Modificar: "+sentencia);
	   int m = conexion.ejecutarActualizacion(sentencia);
	   if (m!=0)
		   sw = true;
	   conexion.cerrar();
	   return sw;
	}
	
	public boolean EliminarMunicipio(int cod){
	   boolean sw=false;
	   conexion = new ModeloConexion();
           String sentencia="";
	    int m= conexion.ejecutarActualizacion(sentencia);
            if (m!=0)
		   sw = true;
	   conexion.cerrar();
	   return sw;
	}
	public boolean ConsultarMunicipioPorCodigo(int cod) throws SQLException{
		   boolean sw; 
		   conexion = new ModeloConexion();
                   String sentencia ="SELECT * FROM municipio WHERE id="+cod+";";
		   ResultSet resul = conexion.ejecutarConsulta(sentencia);
		   if (resul != null)
		   {
			   	resul.next();
		                this.setId(resul.getInt(1));
                                this.setOpcion(resul.getString(2));
                                this.setRelacion(resul.getInt(3));
		   		sw = true;
		   }
		   else
		   {
			   sw = false;  
		   }	 
		   conexion.cerrar();
		   return sw;
		}
        
        
        public boolean ConsultarMunicipioPorNombre(String nombre) throws SQLException{
		   boolean sw; 
		   conexion = new ModeloConexion();
                   String sentencia ="SELECT * FROM municipio WHERE nombre='"+nombre+"';";
		   ResultSet resul = conexion.ejecutarConsulta(sentencia);
		   if (resul != null)
		   {
			   	resul.next();
		                this.setId(resul.getInt("id"));
                                this.setOpcion(resul.getString("nombre"));
                                this.setRelacion(resul.getInt("relacion"));
		   		sw = true;
		   }
		   else
		   {
			   sw = false;  
		   }	 
		   conexion.cerrar();
		   return sw;
		}
        
        
      
   public boolean cargarDatosMunicipio(DefaultComboBoxModel comboModel, String nombre, String municipio){
             boolean sw=false; 
             int idEstado=0;
	     conexion = new ModeloConexion();
                   String sentencia1 ="SELECT * FROM estado WHERE nombre='"+nombre+"';";
		   ResultSet resul1 = conexion.ejecutarConsulta(sentencia1);
                   if (resul1 != null)
		   {
                 try {
                     resul1.next();
                     idEstado = resul1.getInt("id");
                     sw = true;
                 } catch (SQLException ex) {
                     Logger.getLogger(ModeloMunicipio.class.getName()).log(Level.SEVERE, null, ex);
                 }
		   }
             String sentencia = "SELECT * FROM municipio Where idestado ="+idEstado+";";
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){
			   
        try {
            System.out.println(municipio);
            comboModel.addElement(municipio);
            while (resul.next()) {
                sw=true;
                comboModel.addElement(resul.getObject("nombre"));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
             conexion.cerrar();
	     return sw;
        }//fin de cargarDatos   
    public boolean cargarDatosMunicipio(DefaultComboBoxModel comboModel, String nombre){
             boolean sw=false; 
             int idEstado=0;
	     conexion = new ModeloConexion();
                   String sentencia1 ="SELECT * FROM estado WHERE nombre='"+nombre+"';";
		   ResultSet resul1 = conexion.ejecutarConsulta(sentencia1);
                   if (resul1 != null)
		   {
                 try {
                     resul1.next();
                     idEstado = resul1.getInt("id");
                     sw = true;
                 } catch (SQLException ex) {
                     Logger.getLogger(ModeloMunicipio.class.getName()).log(Level.SEVERE, null, ex);
                 }
		   }
             String sentencia = "SELECT * FROM municipio Where idestado ="+idEstado+";";
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){
			   
        try {
            while (resul.next()) {
                sw=true;
                comboModel.addElement(resul.getObject("nombre"));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
             conexion.cerrar();
	     return sw;
        }//fin de cargarDatos
}
