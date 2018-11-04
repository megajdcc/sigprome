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
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Jnatn'h
 */
public class ModeloTipopaciente {
    
    String nombre;
    int id;
    
    ModeloConexion conexion;
    
    public JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
    public ModeloTipopaciente(){
           conexion = ModeloConexion.conexion;
     }
   
    public ModeloTipopaciente(String nombre) {
          conexion = ModeloConexion.conexion;
        this.nombre = nombre;
    }
   public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public boolean consultarModelo(String nombre){
       
            boolean statusConsulta=false;    
            String sentenciaSQL = "SELECT * FROM tipopersona WHERE nombre='"+nombre+"';";
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
        String sentenciaSql = "INSERT INTO tipopersona(nombre) VALUES('"+nombre+"');";
    
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusIncluir = true;
        }else{
            statusIncluir = false;
        }
        
        return statusIncluir;
    }//fin de incluir
      
      public boolean modificarModelo(String nombre){
        boolean statusModificar;
        //System.out.println(nombre);
        String sentenciaSql = "UPDATE tipopersona SET nombre='"+nombre+"' WHERE nombre='"+nombre+"';";
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

 
      
      
}
