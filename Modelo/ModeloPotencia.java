/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.cattiporden;
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
public class ModeloPotencia {
    String nombre;
    int id;
    
    ModeloConexion conexion;
    
    public JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
     public ModeloPotencia(){
           conexion = ModeloConexion.conexion;
     }
   public ModeloPotencia(String nombre){
         conexion = ModeloConexion.conexion;
       this.nombre = nombre;
   }
    public ModeloPotencia(String nombre, int id) {
          conexion = ModeloConexion.conexion;
        this.nombre = nombre;
        this.id = id;
    }
   public ModeloPotencia(cattiporden cat)
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
    
    public boolean ConsultarPotencia(String nombre){
       
            boolean statusConsulta=false;
            
            String sentenciaSQL = "SELECT * FROM potencia WHERE nombre='"+nombre+"';";
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
        String sentenciaSql = "INSERT INTO potencia(nombre) VALUES('"+nombre+"');";
    
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
            
            String sentenciaSQL = "select verificarpotencia('"+nombre+"');";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    existe = resultadoConsulta.getBoolean("verificarpotencia");
                }else{
                    existe = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloPotencia.class.getName()).log(Level.SEVERE, null, ex);
            }
     
            return existe;
        
    }
     public boolean modificarModelo(String nombre, int idpotencia){
        boolean statusModificar;
      
        //System.out.println(nombre);
        String sentenciaSql = "UPDATE potencia SET nombre='"+nombre+"' WHERE id='"+idpotencia+"';";
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
        
        String sentenciaSql = "DELETE FROM potencia WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }

        return statusEliminar;
    }//fin de eliminar
    
    public String[][] FiltroPotencia(String nombre){       
            boolean statusConsulta=false;
            
            String sentenciaSQL = "SELECT nombre FROM potencia WHERE nombre LIKE '%"+nombre+"%'";
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
      
    //Filtrar busquedas en el catalogo de potencia

         public String[][] FiltrarPotencia(String nombre){
           boolean statusConsulta=false;
            
            String sentenciaSQL = "SELECT nombre FROM potencia WHERE nombre LIKE '%"+nombre+"%'";
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
 public String[][] ListarPotencia(){
            boolean statusConsulta = false;
              
            String sentenciaSQL = "SELECT nombre FROM potencia";
            //System.out.println("SQL.-"+sentenciaSQL);
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
    public int capturarId(){
        int idd = 0 ;

        String query = "Select id from potencia where nombre = '"+getNombre()+"'";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if(consulta != null){
            try {
                consulta.next();
                idd = consulta.getInt("id");
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipoProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idd;
    }
}
