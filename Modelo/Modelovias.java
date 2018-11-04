/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.CatVias;
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
public class Modelovias 
{
    String nombre;
    int id;
    
    ModeloConexion conexion;
    
    public JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
     public Modelovias(){
           conexion = ModeloConexion.conexion;
     }
   public Modelovias(String nombre){
         conexion = ModeloConexion.conexion;
       this.nombre = nombre;
   };
    public Modelovias(String nombre, int id) {
          conexion = ModeloConexion.conexion;
        this.nombre = nombre;
        this.id = id;
    }
   public Modelovias(CatVias cat)
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
           
            
            String sentenciaSQL = "SELECT * FROM via WHERE nombre='"+nombre+"';";
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
        String sentenciaSql = "INSERT INTO via(nombre) VALUES('"+nombre+"');";
    
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
            String sentenciaSQL = "SELECT * FROM via WHERE nombre='"+nombre+"';";
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
     public boolean modificarModelo(String nombre, int idvias){
        boolean statusModificar;
        //System.out.println(nombre);
        String sentenciaSql = "UPDATE via SET nombre='"+nombre+"' WHERE id='"+idvias+"';";
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

        String sentenciaSql = "DELETE FROM via WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }

        return statusEliminar;
    }//fin de eliminar
    
      public String[][] FiltroVias(String nombre){
       
            boolean statusConsulta=false;
            
            
            String sentenciaSQL = "SELECT nombre FROM via WHERE nombre LIKE '%"+nombre+"%'";
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
       public String[][] filtrarVias(String busqueda){

        String sentenciaSQL = "SELECT nombre FROM via WHERE nombre LIKE '%"+busqueda+"%';";
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
   
 public String[][] consultarListaVias(){
       
            boolean statusConsulta = false;
   
            String sentenciaSQL = "SELECT nombre FROM via";
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
//public boolean CargarCatPatologia(JTable jTablecatpatologia ) throws SQLException
//{
//    boolean existe = false;
//    ResultSetMetaData rsm;
//    String ConsultaSql = "SELECT nombre FROM patologia";
//    ResultSet resultado  = conexion.ejecutarConsulta(ConsultaSql);
//    if(resultado == null) return null;
//    int i = 0; 
//    try{
//        while()
//    }
//    return existe;
//}
}
