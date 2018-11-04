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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jnatn'h
 */
public class ModeloParroquia {
    private int id, relacion;
    private String nombre;
          
    private ModeloConexion conexion;
    public ModeloParroquia() {
          conexion = ModeloConexion.conexion;
    }
    public ModeloParroquia(int id) {
          conexion = ModeloConexion.conexion;
        this.id = id;
    }
    public ModeloParroquia(int id, int relacion, String nombre) {
          conexion = ModeloConexion.conexion;
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
    public boolean GuardarParroquia(){
	   boolean sw=false;
         
           String sentencia="";
           System.out.println("Insertar: "+sentencia);
	   int m = conexion.ejecutarActualizacion(sentencia);
           if (m!=0)
		   sw = true;
	 
	   return sw;
	}
    public boolean ModificarParroquia(){
	   boolean sw=false;

           String sentencia="";
           System.out.println("Modificar: "+sentencia);
	   int m = conexion.ejecutarActualizacion(sentencia);
	   if (m!=0)
		   sw = true;
	
	   return sw;
	}
    public boolean EliminarParroquia(int cod){
	   boolean sw=false;
	
           String sentencia="";
	    int m= conexion.ejecutarActualizacion(sentencia);
            if (m!=0)
		   sw = true;
	 
	   return sw;
	}
    public boolean ConsultarParroquiaPorCodigo(int cod) throws SQLException{
		   boolean sw; 
		
                   String sentencia ="SELECT * FROM parroquia WHERE id="+cod+";";
                   System.out.println("sentencia");
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
		
		   return sw;
		} 
    public boolean ConsultarParroquiaPorNombre(String nombre) throws SQLException{
		   boolean sw; 
		
                   String sentencia ="SELECT * FROM parroquia WHERE nombre='"+nombre+"';";
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
		   
		   return sw;
		}     
    public boolean cargarDatosParroquia(DefaultComboBoxModel comboModel, String nombre){
             boolean sw=false; 
             int idMunicipio=0;
	     
                   String sentencia1 ="SELECT * FROM municipio WHERE nombre='"+nombre+"';";
		   ResultSet resul1 = conexion.ejecutarConsulta(sentencia1);
                   if (resul1 != null)
                       
		   {
                 try {
                     resul1.next();
                     idMunicipio= resul1.getInt("id");
                     sw = true;
                 } catch (SQLException ex) {
                     Logger.getLogger(ModeloMunicipio.class.getName()).log(Level.SEVERE, null, ex);
                 }
		   }
             String sentencia = "SELECT * FROM parroquia Where idmunicipio ="+idMunicipio+" ;";
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
          
	     return sw;
        }//fin de cargarDatos
    public boolean listarparroquia(DefaultComboBoxModel comboModel, String nombre){
             boolean sw=false; 
             int idMunicipio=0;
	     
                   String sentencia1 ="SELECT * FROM municipio WHERE nombre='"+nombre+"';";
		   ResultSet resul1 = conexion.ejecutarConsulta(sentencia1);
                   if (resul1 != null)
                       
		   {
                 try {
                     resul1.next();
                     idMunicipio= resul1.getInt("id");
                     sw = true;
                 } catch (SQLException ex) {
                     Logger.getLogger(ModeloMunicipio.class.getName()).log(Level.SEVERE, null, ex);
                 }
		   }
             String sentencia = "SELECT * FROM parroquia Where idmunicipio ="+idMunicipio+" ;";
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){
			   
        try {
            comboModel.addElement(nombre);
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
    public int capturaridparroquia(String nombre) throws SQLException{
     
      String sentenciasql = "select id from parroquia where nombre = '"+nombre+"'";
      ResultSet resul = conexion.ejecutarConsulta(sentenciasql);
      if (resul.next()){
			   
        try {

              id =  resul.getInt("id");
              return id;
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ModeloParroquia.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
	     return 0 ;
  } 
    public String capturarnombre(int idparroquia) throws SQLException{
        String dato = null;
        this.setId(idparroquia);
        
        String query = "SELECT nombre from parroquia where id = "+this.id+";";
        ResultSet result  = conexion.ejecutarConsulta(query);
        if (result.next()) {
            dato = result.getString("nombre");
        }else{
             
             JOptionPane.showMessageDialog(new JFrame(),"Registro no encontrado","Parroquia",JOptionPane.INFORMATION_MESSAGE);
        }
        return dato;
    }
    public boolean listarporparroquia(DefaultComboBoxModel listarestado,DefaultComboBoxModel listarmunicipio,DefaultComboBoxModel listarparroquia, int idparroquia) throws SQLException{
         boolean sw=false; 
         String parroquia = "";
         
         String sentenciasql = "SELECT * from parroquia where id = '"+idparroquia+"'";
         ResultSet resultado = conexion.ejecutarConsulta(sentenciasql);
         
         
         if (resultado != null) {
             
             resultado.next();
             int idmunicipio = resultado.getInt("idmunicipio");
             parroquia  = resultado.getString("nombre");
//             listarmunicipioporparroquia(listarestado, listarmunicipio, idmunicipio);
            
            String sentenciasq = "SELECT * FROM parroquia where idmunicipio = '"+idmunicipio+"'";
            ResultSet result = conexion.ejecutarConsulta(sentenciasq);
             
            if (result != null){
            try {
//            listarparroquia.setSelectedItem(parroquia);
            listarparroquia.addElement(parroquia);     
            while (result.next()) {
                sw=true;
                listarparroquia.addElement(result.getObject("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en el ModeloPArroquia-listarporparroquia:" +ex.getMessage());
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
        }
         return sw;
    }
    public boolean listarmunicipioporparroquia(DefaultComboBoxModel listarestado, DefaultComboBoxModel listarmunicipio, int idmunicipio) throws SQLException
    {
        boolean sw=false; 
        int idestado = 0;
        String municipio = "";
       
         String sentenciasql = "SELECT * from municipio where id = '"+idmunicipio+"'";
         ResultSet result = conexion.ejecutarConsulta(sentenciasql);
        if (result.next()) {
            sw = true;
             try {
                 idestado = result.getInt("idestado");
                 municipio = result.getString("nombre");
             } catch (SQLException ex) {
                 Logger.getLogger(ModeloParroquia.class.getName()).log(Level.SEVERE, null, ex);
             }
            try {
                listarestadoporparroquia(listarestado, idestado);
            } catch (SQLException ex) {
                Logger.getLogger(ModeloParroquia.class.getName()).log(Level.SEVERE, null, ex);
            }
            String query = "SELECT * from municipio where idestado = '"+idestado+"'";
            ResultSet resultado = conexion.ejecutarConsulta(query);
            if (resultado.next()) {
                sw = true;
                try {
                    listarmunicipio.setSelectedItem(municipio);
                    while (result.next()) {
                        sw=true;
                        listarmunicipio.addElement(result.getObject("nombre"));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return sw;
    }
    public boolean listarestadoporparroquia(DefaultComboBoxModel listarestado, int idestado) throws SQLException
    {
        boolean sw = false;
        String estado = "";
        
        String sentenciasql = "SELECT * from estado where id = '"+idestado+"'";
        ResultSet resultado = conexion.ejecutarConsulta(sentenciasql);
        if (resultado.next()){
            sw = true;
            estado = resultado.getString("nombre"); 
            String query = "SELECT * from estado";
            ResultSet result = conexion.ejecutarConsulta(query);
            if (result.next()) {
                 try {
                    listarestado.addElement(estado);     
                    while (result.next()){
                        listarestado.addElement(result.getObject("nombre"));
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return sw;
    }
    public String capturarmunicipio(int idparroquia){
        String municipio = "";
       
        String sentencia = "select municipio.nombre from municipio join parroquia on(municipio.id = parroquia.idmunicipio) where parroquia.id = '"+idparroquia+"'";
        ResultSet result = conexion.ejecutarConsulta(sentencia);
        if (result != null) {  
            try {
                result.next();
                municipio = result.getString("nombre");
            } catch (SQLException ex) {
                Logger.getLogger(ModeloParroquia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return municipio;
    }
    public int capturarid(String nombreparroquia){
        
       
        String sentencia = "Select id from parroquia where nombre = '"+nombreparroquia+"'";
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
