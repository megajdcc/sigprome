/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jnatn'h
 */
public abstract class ModeloPersona {
    protected long cedula;
    protected String nombre;
    protected String apellido;
    protected String sexo;
    protected Date fechanacimiento;
    protected long telefono;
    protected String direccion;
    protected int id_tipopersona;
    protected int id_parroquia;
    public String busqueda;
    protected String tipopersona;
    
    protected ModeloConexion conexion;
    private Connection conn, connp;
    private ResultSet rs;
    private JasperViewer jasperViewer;
    
//    Constructores de paciente...
    public long getCedula()
    {
        return cedula;
    }
    public void setCedula(long cedula)
    {
        this.cedula = cedula;
    }
    public String getNombre()
    {
        return nombre;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    public String getApellido()
    {
        return apellido;
    }
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }
    public String getSexo()
    {
        return sexo;
    }
    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }
    public Date getFechanacimiento()
    {
        return fechanacimiento;
    }
    public void setFechanacimiento(Date fechanacimiento2)
    {
        this.fechanacimiento = fechanacimiento2;
    }
    public long getTelefono()
    {
        return telefono;
    }
    public void setTelefono(long telefono)
    {
        this.telefono = telefono;
    }
    public String getDireccion()
    {
        return direccion;
    }
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }
    public int getIdtipopersona()
    {
        return id_tipopersona;
    }
    public void setIdtipopersona(int id_tipopersona)
    {
        this.id_tipopersona = id_tipopersona;
  
    }
    public int getIdparroquia()
    {
        return id_parroquia;
    }
    public void setIdparroquia(int id_parroquia)
    {
        this.id_parroquia = id_parroquia;
    }
    public void setTipopersona(String tipopersona){
        this.tipopersona = tipopersona;
    }
    public String getTipopersona(){
        return tipopersona;
    }

  public ModeloConexion getConexion() {
    return conexion;
  }

  public void setConexion(ModeloConexion conexion) {
    this.conexion = conexion;
  }
    
    //Definimos los metodos abstractos para que todo el que herede la clase lo implemente necesariamente...
    
//    public abstract boolean consultar();
    public abstract boolean registrar();
    public abstract boolean modificar();
    
    public boolean verificar(int cedula) {
        boolean existe = false;
          conexion = ModeloConexion.conexion;
        String sentenciaSQL = "SELECT * FROM verificarpersona('"+cedula+"')";
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            try {
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();         
                    existe = true;
                }else{
                    existe = false;
                }
            }catch (SQLException ex) {
                Logger.getLogger(ModeloPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return existe;
    }
    public boolean modificarModelo(){
        boolean statusModificar = false;
         conexion = ModeloConexion.conexion;
        //System.out.println(nombre);
        
        String sentenciaSql = "UPDATE persona set nombre = '"+this.nombre+"',apellido = '"+this.apellido+"',"
                + "sexo = '"+this.sexo+"', fecha_nacimiento = '"+this.fechanacimiento+"', telefono = "+this.telefono+","
                + "direccion = '"+this.direccion+"', id_tipopersona = "+this.id_tipopersona+", "
                + "id_parroquia = "+this.id_parroquia+",actualizado = false where cedula = "+this.cedula+"";
             
        int modificacion = conexion.ejecutarActualizacion(sentenciaSql);
        if (modificacion != 0) {
            statusModificar = true;
        }
        
        return statusModificar;
    }//fin de modificar
    public String[][] Filtrarpersona(String busqueda){
       
            boolean statusConsulta=false;
            
             conexion = ModeloConexion.conexion;
            String sentenciaSQL = "SELECT * FROM filtrar_persona('"+busqueda+"')";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null) return null;
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][3];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                    datos[i][0]=resultadoConsulta.getString("cedula");
                    datos[i][1]=resultadoConsulta.getString("nombre");      
                    datos[i][2]=resultadoConsulta.getString("tipopersona");      
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                    return null;
            } 
        }
    public String[][] ListarPersona(){
            boolean statusConsulta = false;
            
            String sentenciaSQL = "select * from catpersona";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null){
               return null;
            }
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][4];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                   datos[i][0] = resultadoConsulta.getString("cedula");
                   datos[i][1] = resultadoConsulta.getString("nombre");
                   datos[i][2] = resultadoConsulta.getString("apellido");
                   datos[i][3] = resultadoConsulta.getString("tipo_persona");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                ex.printStackTrace();
                    return null;
            }
        }
    
}
