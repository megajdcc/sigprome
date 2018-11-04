/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
/**
 *
 * @author Jhonatan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Vista.Principal;
import javax.swing.JOptionPane;

public class ModeloConexion {
    private String clave;
    private String usuario;
    private String driver;
    private String driverUrl;
    private int idtemporal;
    private boolean cerrar;
    public static ModeloConexion  conexion = new ModeloConexion();
    private Connection con = null;
    Principal principal;

    //Constructores de la Clase de la Conexión.... 
    public ModeloConexion(){
        this.Conf_database();
        this.conectar();
        
    };
    public ModeloConexion(int idtemporal){
        this.idtemporal = idtemporal;
        this.crearidtemporal();
        try {
           this.usuariosesion();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Conf_database();
        this.conectar();
    }
//  Methodos Setter y Getters ... 
    public static ModeloConexion getConexion(){
        return ModeloConexion.conexion;
    }
    public void setIdtemporal(int idtemporal){
        this.idtemporal = idtemporal;
    }
    public int getIdtemporal(){
        return this.idtemporal;
    }
    public Connection getConec(){
        return con;
    }
    private void Conf_database() {
        this.driver = "org.postgresql.Driver";
        this.driverUrl = "jdbc:postgresql://localhost:5432/sigprome";
        this.usuario = "postgres";
        this.clave = "paterblat";
    }
    //Function Conectar que nos devuelve un Boolean... 
    private Connection conectar(){
        try {
           Class.forName(this.driver);// Inicializamos la clase org.postgresql.Driver invoncandola...   
           con = DriverManager.getConnection( driverUrl, usuario, clave);
        } catch (ClassNotFoundException e) {
            // error especifico con respecto al driver JDBC... 
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error:"+e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(ModeloConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    } 
    private void crearidtemporal(){
        Statement sen;
        try {
         String sentenciaSql = "SELECT id_temporal('"+this.getIdtemporal()+"')";
         this.ejecutarConsulta(sentenciaSql);
        } catch (Exception e) {
            System.out.println("ERROR: no se pudo crear el id temporal en la base de dato...");
        } 
    }
    public int ejecutarActualizacion(String sql) {
        Statement sen;
        int a;
        try {
//             la function id temporal se utiliza para capturar el id del usuario y poder 
//             registrarlo en la base de dato...
//             Referenciamos  la clase del controlador principal...s
            String sentenciaSql = "SELECT id_temporal('"+Principal.getIdUsuario()+"')";
            this.usuariosesion();
            this.ejecutarConsulta(sentenciaSql);
            sen = con.createStatement();//creamos la declareacion del objeto para enviar la sentencia sql a la BD...
            a = sen.executeUpdate(sql);
            return a;
        } catch (SQLException e) {
            System.out.println("Error en Ejecutar actualización: "+e.getMessage());
            return 0;
        }	
    }
    public ResultSet ejecutarConsulta(String sql) {
        Statement sen;
        ResultSet rs;
        try {
            sen = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = sen.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
                return rs;
            } else {
                return null;
            }
        } catch (SQLException e) {
            //System.out.println("Error: "+e.getMessage());
             //JOptionPane.showMessageDialog(new JFrame(),e.getMessage(),JOptionPane.INFORMATION_MESSAGE);        
            return null;
        }
    }
    private void usuariosesion() throws SQLException{
        Statement sen;
        ResultSet rs;
        try
        {
            String sentenciaSQL = "SELECT table_temporal("+Principal.getIdUsuario()+")";
            sen = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = sen.executeQuery(sentenciaSQL);
            if (rs.next()) {
                rs.beforeFirst();
            }else{
                System.out.println("Error! Problema creando la tabla temporal...");
            }
        } catch(SQLException ex)
        {
           // System.out.println("Error: "+ex.getMessage());
        }
    }
    
    public boolean verificar(String sql) {
        Statement sen;
        boolean existe;
        ResultSet rs;
        try {
            sen = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = sen.executeQuery(sql);
            if (rs.next()) {
                rs.beforeFirst();
                existe = true;
            } else {
                existe = false;
            }
        return existe;
        } catch (SQLException e) {
            System.out.println("Error:"+e.getMessage());
             return false;
        }
    }
    
    /**
     * Metodo encargado de cerrar cualquier conexion existente ... 
     */
    public  void cerrar() {
      if (ModeloConexion.conexion != null) {
			try {
				con.close();
			} catch (SQLException e) {
                            e.printStackTrace();
			}
		}
    }
    
    private Connection getCon() {
        return con;
    }
    
    private void setConnectString(String driverUrl) {
        this.driverUrl = driverUrl;
    }
    
}
