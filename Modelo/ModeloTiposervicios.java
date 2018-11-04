/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.CatTiposervicios;
import Vista.Servicio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import net.sf.jasperreports.engine.JasperReport;
/**
 *
 * @author Jnatn'h
 */
public class ModeloTiposervicios {
    Servicio vista;
    String nombre;
    int id;
    
    ModeloConexion conexion;
    
    public JasperReport reporte;

    
     public ModeloTiposervicios(){
           conexion = ModeloConexion.conexion;
     }
   public ModeloTiposervicios(String nombre){
         conexion = ModeloConexion.conexion;
       this.nombre = nombre;
   };
    public ModeloTiposervicios(String nombre, int id) {
          conexion = ModeloConexion.conexion;
        this.nombre = nombre;
        this.id = id;
    }
   public ModeloTiposervicios(CatTiposervicios cat)
   {
         conexion = ModeloConexion.conexion;
   }
   public String getNombre() {
        return nombre;
    }
   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean consultarModelo(String nombre){
       
            boolean statusConsulta=false;
            
            
            String sentenciaSQL = "SELECT * FROM tiposervicio WHERE nombre='"+nombre+"';";
            //System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
//            System.out.println(resultadoConsulta);
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
       // System.out.println(this.nombre);
        String sentenciaSql = "INSERT INTO tiposervicio(nombre,sincronizado) VALUES('"+nombre+"',false);";
    
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir = true;
        }else{
            statusIncluir = false;
        }
        return statusIncluir;
    }//fin de incluir
    public boolean verificar(String nombre)
    {
        boolean existe = false;

            String sentenciaSQL = "SELECT * FROM tiposervicio WHERE nombre='"+nombre+"';";
            //System.out.println("SQL.-"+sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
//            System.out.println(resultadoConsulta);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();

                    
                    existe = true;
                }else{
                    existe = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipopaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            return existe;
        
    }
     public boolean modificarModelo(String nombre, int idtipoorden){
        boolean statusModificar;
        //System.out.println(nombre);
        String sentenciaSql = "UPDATE tiposervicio SET nombre='"+nombre+"',sincronizado = false WHERE id='"+idtipoorden+"';";
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

        String sentenciaSql = "DELETE FROM tiposervicio WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        
        return statusEliminar;
    }//fin de eliminar
    
      public String[][] FiltroTiposervicios(String nombre){
       

            
            String sentenciaSQL = "SELECT nombre FROM tiposervicio WHERE nombre LIKE '%"+nombre+"%'";
            //System.out.println("SQL.-"+sentenciaSQL);
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
       public String[][] filtrartiposervicios(String busqueda){
        
        String sentenciaSQL = "SELECT nombre FROM tiposervicio WHERE nombre LIKE '%"+busqueda+"%';";
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
   
 public String[][] consultarListaTiposervicios(){
            
            String sentenciaSQL = "SELECT nombre FROM tiposervicio";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null)
            {
                String Error = "error en la consulta";
               System.out.println(Error);
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
   @SuppressWarnings("unchecked")
public boolean Listartiposervicio(@SuppressWarnings("rawtypes") DefaultComboBoxModel comboModel){
             boolean sw=false; 
             String sentencia = "SELECT * FROM tiposervicio";
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){			   
        try {
            comboModel.addElement("SELECCIONE");
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
   public int capturarid(String nombrer) throws SQLException{
       
       String query = "SELECT id FROM tiposervicio where nombre = '"+nombrer+"'";
       ResultSet consult = conexion.ejecutarConsulta(query);
       if (consult != null) {
           consult.next();
          this.setId(consult.getInt("id"));
       }
       return this.getId();
       }
   public int capturarid(Long cedula){
      
       
       String query = "select * from capturaridtiposervicio("+cedula+")";
       ResultSet captura = conexion.ejecutarConsulta(query);
       if(captura != null ){
           try {
               captura.next();
               this.setId(captura.getInt("tiposervicio"));
           } catch (SQLException ex) {
               Logger.getLogger(ModeloTiposervicios.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
       return this.getId();
   }
}
