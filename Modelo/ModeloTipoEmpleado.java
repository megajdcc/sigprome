/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Vista.CatTipoempleado;
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
public class ModeloTipoEmpleado {
    String nombre;
    int id;
    
    ModeloConexion conexion;
    
    public JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
     public ModeloTipoEmpleado(){
           conexion = ModeloConexion.conexion;
     }
   public ModeloTipoEmpleado(String nombre){
         conexion = ModeloConexion.conexion;
       this.nombre = nombre;
   };
    public ModeloTipoEmpleado(String nombre, int id) {
          conexion = ModeloConexion.conexion;
        this.nombre = nombre;
        this.id = id;
    }
   public ModeloTipoEmpleado(CatTipoempleado cat)
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
            String sentenciaSQL = "SELECT * FROM tipoempleado WHERE nombre='"+nombre+"';";
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
        String sentenciaSql = "INSERT INTO tipoempleado(nombre) VALUES('"+nombre+"');";
    
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
            String sentenciaSQL = "SELECT * FROM tipoempleado WHERE nombre='"+nombre+"';";
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
     public boolean modificarModelo(String nombre, int idtipoempleado){
        boolean statusModificar;
        
        //System.out.println(nombre);
        String sentenciaSql = "UPDATE tipoempleado SET nombre='"+nombre+"' WHERE id='"+idtipoempleado+"';";
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
        String sentenciaSql = "DELETE FROM tipoempleado WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        
        return statusEliminar;
    }//fin de eliminar
    
      public String[][] FiltroTipoempleado(String nombre){
       
            boolean statusConsulta=false;

            String sentenciaSQL = "SELECT nombre FROM tipoempleado WHERE nombre LIKE '%"+nombre+"%'";
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
       public String[][] filtrartipoempleado(String busqueda){
        String sentenciaSQL = "SELECT nombre FROM tipoempleado WHERE nombre LIKE '%"+busqueda+"%';";
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
   
 public String[][] consultarListaTipoempleado(){
       
            boolean statusConsulta = false;
            
            
            String sentenciaSQL = "SELECT nombre FROM tipoempleado";
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
  public boolean Listartipoempleado(DefaultComboBoxModel comboModel){
             boolean sw=false; 
	     
             String sentencia = "SELECT * FROM tipoempleado;";
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
  public int capturaridtipoempleado(String nombre) throws SQLException
  {
      
      String sentenciasql = "select id from tipoempleado where nombre = '"+nombre+"'";
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
  
}
