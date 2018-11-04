
package Modelo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ModeloEspecialidad {
    String nombre;
    int id;
    
    ModeloConexion conexion;
    
    public JasperReport reporte;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
     public ModeloEspecialidad(){
         conexion = ModeloConexion.conexion;
     }
   public ModeloEspecialidad(String nombre){
       conexion = ModeloConexion.conexion;
       this.nombre = nombre;
   }
    public ModeloEspecialidad(String nombre, int id) {
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
    
    public boolean ConsultarEspecialidad(String nombre){
            boolean statusConsulta=false;
           
            
            String sentenciaSQL = "SELECT * FROM especialidad WHERE nombre='"+nombre+"';";
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
             conexion.cerrar();
            return statusConsulta;
            
        }//fin de consultar
    
    public boolean incluirModelo(){
        boolean statusIncluir;
        
  
       // System.out.println(this.nombre);
        String sentenciaSql = "INSERT INTO especialidad(nombre) VALUES('"+nombre+"');";
    
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
           
            String sentenciaSQL = "select verificarespecialidad('"+nombre+"');";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    existe = resultadoConsulta.getBoolean("verificarespecialidad");
                }else{
                    existe = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloEspecialidad.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            return existe;
        
    }
     public boolean modificarModelo(String nombre, int idespecialidad){
        boolean statusModificar;
     
        //System.out.println(nombre);
        String sentenciaSql = "UPDATE especialidad SET nombre='"+nombre+"' WHERE id='"+idespecialidad+"';";
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
 
        
        String sentenciaSql = "DELETE FROM especialidad WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        return statusEliminar;
    }//fin de eliminar
    
      public String[][] FiltroEspecialidad(String nombre){
       
            boolean statusConsulta=false;
            
           
            
            String sentenciaSQL = "SELECT nombre FROM especialidad WHERE nombre LIKE '%"+nombre+"%'";
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
      
    //Filtrar busquedas en el catalogo de especialidad

         public String[][] FiltrarEspecialidad(String nombre){
           boolean statusConsulta=false; 
           
            String sentenciaSQL = "SELECT nombre FROM especialidad WHERE nombre LIKE '%"+nombre+"%'";
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
    public String[][] ListarEspecialidad(){
            boolean statusConsulta = false;
           
            String sentenciaSQL = "SELECT nombre FROM especialidad";
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
    public boolean cargarDatosEspecialidad(DefaultComboBoxModel comboModel){
    boolean sw=false; 
	    
             String sentencia = "select * from especialidad";
             ResultSet resul = conexion.ejecutarConsulta(sentencia);
        if (resul != null){		   
        try {
           comboModel.addElement("Seleccione la especialidad");       
            while (resul.next()) {
                sw=true;
                comboModel.addElement(resul.getObject("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
	    return sw;
        }//fin de cargarDatos
    public String capturarnombre(int idespecialidad){
        this.id = idespecialidad;
        
       String nombreesp = "";
       
       String sentencia = "select nombre from especialidad where id = "+this.id+"";
       ResultSet result = conexion.ejecutarConsulta(sentencia);
        if (result != null) {
            try {
                result.next();
                nombreesp = result.getString("nombre");
            } catch (SQLException e) {
                System.out.println("ERROR: "+ e.getMessage());
            }
        }
        return nombreesp;
    }
    /**
     * 
     * @param nombreespecialidad
     * @return integer
     */
    public int capturarid(String nombreespecialidad){
        
        
        String sentencia = "Select id from especialidad where nombre = '"+nombreespecialidad+"'";
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
