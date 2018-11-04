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

/**
 *
 * @author Jnatn'h
 */
public class OpcAdminis {
  
  private int id = 0;
  private String nombre = null;
  private ModeloConexion conexion;
  
  //constructores
  public OpcAdminis(){
    conexion = ModeloConexion.conexion;
  }
  
  //Metodos
  protected boolean Registrar(String nombre){
    boolean regis = false;
    this.nombre = nombre;
    String query = "INSERT INTO opcionesadministrativas(nombre) values ("+this.nombre+")";
    int registro = conexion.ejecutarActualizacion(query);
    if(registro != 0 ){
      regis = true;
    }
    return regis;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
    this.capturarid();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  public void Consultar(){
    
  }
  private void capturarid(){
    String query = "SELECT id from opcionesadministrativas where nombre = '"+getNombre()+"'";
    ResultSet result = conexion.ejecutarConsulta(query);
    if(result !=null){
      try {
        result.next();
        setId(result.getInt("id"));
      } catch (SQLException ex) {
        Logger.getLogger(OpcAdminis.class.getName()).log(Level.SEVERE, null, ex);
      }
    }else{
      System.out.println("Error capturando el id de opcionesadminsitrativas");
    }
  }
    public String[][] ListarOpciones(){
    
            String sentenciaSQL = "SELECT nombre FROM opcionesadministrativas";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null){
              System.out.println("Error en la consulta");
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
    public boolean verificar(String nombre){
            boolean existe = false;
            String sentenciaSQL = "select verificaropcionadministrativa('"+nombre+"');";
           
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    existe = resultadoConsulta.getBoolean("verificaropcionadministrativa");
                }else{
                    existe = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloExamen.class.getName()).log(Level.SEVERE, null, ex);
            }
//            conexion.cerrar();
            return existe;
    }
    public boolean incluirModelo(){
        boolean statusIncluir = false;
        String sentenciaSql = "INSERT INTO opcionesadministrativas(nombre,sincronizado) VALUES('"+nombre+"',false);";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        if(ejecutado!=0){
            statusIncluir = true;
        }
//        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    public boolean modificarModelo(String nombre, int id){
        boolean statusModificar;
        String sentenciaSql = "UPDATE opcionesadministrativas SET nombre='"+nombre+"', sincronizado = false WHERE id='"+id+"';";
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
        String sentenciaSql = "DELETE FROM opcionesadministrativas WHERE nombre='"+nombre+"';";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusEliminar= true;
        }else{
            statusEliminar = false;
        }
        return statusEliminar;
    }//fin de eliminar
    public boolean consultarModelo(String nombre){
            boolean statusConsulta=false;            
            String sentenciaSQL = "SELECT * FROM opcionesadministrativas WHERE nombre='"+nombre+"';";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
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
//             conexion.cerrar();
            return statusConsulta;
        }//fin de consultar
  
  
  
}
