/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.Principal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import Vista.catapatologia;
import java.awt.Cursor;
import javax.swing.DefaultListModel;
import javax.swing.JList;
/**
 *
 * @author Jnatn'h
 */
public class ModeloPatologia{
    
    
    String nombre;
    String descripcion;
    int id;
    ModeloConexion conexion;
    
    public JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
     public ModeloPatologia(){
          conexion = ModeloConexion.conexion;
     }

   public ModeloPatologia(String nombre, String descripcion){
        conexion = ModeloConexion.conexion;
     this.nombre = nombre;
       this.descripcion = descripcion;
   };
    public ModeloPatologia(String nombre, int id) {
       conexion = ModeloConexion.conexion;
      this.nombre = nombre;
        this.id = id;
    }
    public ModeloPatologia(String nombre){
       conexion = ModeloConexion.conexion;
      this.nombre = nombre;
    }
   public ModeloPatologia(catapatologia cat){
         conexion = ModeloConexion.conexion;
   }
   public String getNombre() {
        return nombre;
    }
   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion()
    {
        return descripcion;
    }
    public void setDescripcion(String Descripcion)
    {
        this.descripcion = Descripcion;
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
            String sentenciaSQL = "SELECT * FROM patologia WHERE nombre='"+nombre+"';";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setNombre(resultadoConsulta.getString("nombre"));
                    this.setDescripcion(resultadoConsulta.getString("descripcion"));
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
        boolean statusIncluir = false;
            String sentenciaSql = "INSERT INTO patologia(nombre, descripcion) VALUES('"+this.nombre+"','"+this.descripcion+"')";
            
            int registro = conexion.ejecutarActualizacion(sentenciaSql);
            if (registro !=0 ) {
                statusIncluir = true;
            }else{
                statusIncluir = false;
            } 
        return statusIncluir;
    }//fin de incluir
    public boolean verificar(String nombre){
            boolean existe = false;
            String sentenciaSQL = "select * from verificarpatologia('"+nombre+"');";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    existe = resultadoConsulta.getBoolean("verificarpatologia");
                }else{
                    existe = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipopaciente.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return existe;
    }
    public boolean modificarModelo(int idpatologia){
        boolean statusModificar;
        String sentenciaSql = "UPDATE patologia SET nombre='"+nombre+"', descripcion='"+descripcion+"' WHERE id='"+idpatologia+"';";
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
        String sentenciaSql = "DELETE FROM patologia WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }        
        return statusEliminar;
    }//fin de eliminar
    public String[][] FiltroPatologia(String nombre){
            boolean statusConsulta=false;
            String sentenciaSQL = "SELECT nombre FROM patologia WHERE nombre LIKE '%"+nombre+"%'";
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
    public String[][] filtrarpatologia(String busqueda){
        String sentenciaSQL = "SELECT nombre FROM patologia WHERE nombre LIKE '%"+busqueda+"%';";
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
    public String[][] consultarListaPatologia(){
            boolean statusConsulta = false;
            String sentenciaSQL = "SELECT nombre FROM patologia";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null){
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

    /**
     * Se recibe por paramatro un objecto del JList a la cual se va asignar una lista de tipo patologias
     * @param lista 
     */
    public void listarpatologiasasignar(JList lista){
      String query = "SELECT nombre from patologia";
      ResultSet consul  = conexion.ejecutarConsulta(query);
      if(consul != null){
        try {
         int i = 0 ;
          DefaultListModel milista = new DefaultListModel();
          
          while(consul.next()){              
               milista.addElement(consul.getString("nombre"));
          }
          lista.setCursor(new Cursor(Cursor.HAND_CURSOR));
          lista.setModel(milista);
        } catch (SQLException ex) {
          Logger.getLogger(ModeloPatologia.class.getName()).log(Level.SEVERE, null, ex);
        }
         
      }
    }
    public boolean capturaridpatologia(String nombre,int idconsulta){
      boolean registro = false;
      String query = "SELECT id from patologia where nombre = '"+nombre+"'";
      ResultSet consul = conexion.ejecutarConsulta(query);
      if(consul != null){
        try {
          consul.next();
          id = consul.getInt("id");
          String query1 = "INSERT INTO assocpatologia(id_patologia,id_consulta) values("+id+","+idconsulta+")";
          int regis = conexion.ejecutarActualizacion(query1);
          if(regis !=0){
            registro = true;
         }
        } catch (SQLException ex) {
          Logger.getLogger(ModeloPatologia.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return registro;
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
