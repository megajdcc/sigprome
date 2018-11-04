/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.ControladorServicio;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Jnatn'h
 */
public class ModelEstadosProces {
  
  private int id; 
  private String nombre;
  private int condicion;
  private ModeloConexion conexion;
  public ModelEstadosProces(){
    conexion = ModeloConexion.conexion;
    
  }
//  metodos del crud 
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
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCondicion() {
    return condicion;
  }

  public void setCondicion(int condicion) {
    this.condicion = condicion;
  }


  

    public String[][] ListarOpciones(){
            boolean statusConsulta = false;
            String sentenciaSQL = "SELECT nombre FROM estadoproceso";
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
            String sentenciaSQL = "select verificarestadosproceso('"+nombre+"');";
            System.out.println(sentenciaSQL);
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    existe = resultadoConsulta.getBoolean("verificarestadosproceso");
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
        String sentenciaSql = "INSERT INTO estadoproceso(nombre,condicion,sincronizado) VALUES('"+nombre+"', "+getCondicion()+",false);";
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        if(ejecutado!=0){
            statusIncluir = true;
        }
//        conexion.cerrar();
        return statusIncluir;
    }//fin de incluir
    public boolean modificarModelo(String nombre, int condicion, int id){
        boolean statusModificar;
        setCondicion(condicion);
        String sentenciaSql = "UPDATE estadoproceso SET nombre='"+nombre+"', condicion = "+getCondicion()+", sincronizado = false WHERE id='"+id+"';";
        int ejecutado;
        ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        
        if(ejecutado!=0){
            statusModificar= true;
        }else{
            statusModificar = false;
        }
        return statusModificar;
    }//fin de modificar
    public boolean eliminarModelo(int id){
        setId(id);
        boolean statusEliminar;
        String sentenciaSql = "DELETE FROM estadoproceso WHERE id='"+getId()+"';";
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
            String sentenciaSQL = "SELECT * FROM estadoproceso WHERE nombre='"+nombre+"';";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try { 
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setNombre(resultadoConsulta.getString("nombre"));
                    this.setCondicion(resultadoConsulta.getInt("condicion"));
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
  
  public boolean Capturarestado(int idopciones, int idpaciente){
    boolean encontrado = false;
    String query = "select * from capturarestadoproceso("+idopciones+","+idpaciente+")";
    System.out.println(query);
    ResultSet consul = conexion.ejecutarConsulta(query);
    if(consul != null){
      try {
        consul.next();
        this.setNombre(consul.getString("nombreestado"));
        this.setId(consul.getInt("idestado"));
        encontrado = true;
      } catch (SQLException ex) {
        Logger.getLogger(ModelEstadosProces.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }
    return encontrado;
  }
  public void ListarStatus(JComboBox lista, String primero, ControladorServicio serv){
    DefaultComboBoxModel dm  = new DefaultComboBoxModel();
    String query = "SELECT nombre from estadoproceso";
    ResultSet result = conexion.ejecutarConsulta(query);
    if(result != null){
      try {
        while(result.next()){
          dm.addElement(result.getString("nombre"));
        }
        dm.setSelectedItem(primero);
        
        lista.setModel(dm);
      } catch (SQLException ex) {
        Logger.getLogger(ModelEstadosProces.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  public void capturaridseleccion(String nombre){
    setNombre(nombre);
    String query = "SELECT id from estadoproceso where nombre = '"+getNombre()+"'";
    ResultSet result  = conexion.ejecutarConsulta(query);
    if(result != null){
      try {
        result.next();
        setId(result.getInt("id"));
      } catch (SQLException ex) {
        Logger.getLogger(ModelEstadosProces.class.getName()).log(Level.SEVERE, null, ex);
      }  
    }
  }
  
}
