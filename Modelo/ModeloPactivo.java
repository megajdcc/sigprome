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
public class ModeloPactivo {
    String nombre;
    int id;
    
    ModeloConexion conexion;
    
    public JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
     public ModeloPactivo(){
           conexion = ModeloConexion.conexion;
     }
   public ModeloPactivo(String nombre){
         conexion = ModeloConexion.conexion;
       this.nombre = nombre;
   }
    public ModeloPactivo(String nombre, int id) {
          conexion = ModeloConexion.conexion;
        this.nombre = nombre;
        this.id = id;
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
    
    public boolean ConsultarPactivo(String nombre){
            boolean statusConsulta=false;
            
            String sentenciaSQL = "SELECT * FROM principioactivo WHERE nombre='"+nombre+"';";
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
                Logger.getLogger(ModeloPactivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            return statusConsulta;
            
        }//fin de consultar
    
    public boolean incluirModelo(){
        boolean statusIncluir;
        String sentenciaSql = "INSERT INTO principioactivo(nombre) VALUES('"+nombre+"');";
    
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
            
            String sentenciaSQL = "select verificarpactivo('"+nombre+"');";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    existe = resultadoConsulta.getBoolean("verificarpactivo");
                }else{
                    existe = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloPotencia.class.getName()).log(Level.SEVERE, null, ex);
            }
            return existe;
        
    }
     public boolean modificarModelo(String nombre, int idpactivo){
        boolean statusModificar;
  
        //System.out.println(nombre);
        String sentenciaSql = "UPDATE principioactivo SET nombre='"+nombre+"' WHERE id='"+idpactivo+"';";
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
        
        String sentenciaSql = "DELETE FROM principioactivo WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        return statusEliminar;
    }//fin de eliminar
    
    //Filtrar busquedas en el catalogo de potencia

         public String[][] FiltrarPactivo(String nombre){
           boolean statusConsulta=false;
            
            String sentenciaSQL = "SELECT nombre FROM principioactivo WHERE nombre LIKE '%"+nombre+"%'";
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
 public String[][] ListarPactivo(){
            boolean statusConsulta = false;
            String sentenciaSQL = "SELECT nombre FROM principioactivo";
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
        String query = "Select id from principioactivo where nombre = '"+getNombre()+"'";
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
