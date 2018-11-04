/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Jnatn'h
 */
public class ModeloEmpleado {
    String condicionempleado;
    int tipoempleado;
    private long cedulapersona;
    int id;
    
    private Connection conn, connp;
    private ResultSet rs;
    
    
    ModeloConexion conexion;
    public ModeloEmpleado()
    {
        conexion = ModeloConexion.conexion;
    }
     public ModeloEmpleado(String condicionempleado,int tipoempleado,long cedulapersona)
    {
        conexion = ModeloConexion.conexion;
        this.condicionempleado = condicionempleado;
        this.tipoempleado = tipoempleado;
        this.cedulapersona = cedulapersona;
    }
     public String getCondicionempleado()
     {
         return condicionempleado;
         
     }
     public void setCondicionempleado(String condicionempleado)
     {
         this.condicionempleado = condicionempleado;
     }
     public int getTipoempleado()
     {
         return tipoempleado;
     }
    public void setTipoempleado(int tipoempleado)
    {
        this.tipoempleado = tipoempleado;
    }
    public long getCedulapersona()
    {
        return cedulapersona;
    }
    public void setCedulapersona(long cedulapersona)
    {
        this.cedulapersona = cedulapersona;
    }
    public boolean incluirModelo() throws SQLException{
        boolean statusIncluir = false;
       

        String sentenciaSql = "insert into empleado(condicionempleado, cedulapersona,id_tipoempleado)"
                + "values('"+condicionempleado+"',"+cedulapersona+","+tipoempleado+")";
    
        int ejecutado = conexion.ejecutarActualizacion(sentenciaSql);
        if(ejecutado != 0)
        {
           statusIncluir = true;
        }
        
      
        return statusIncluir;
    }//fin de incluir
    public boolean modificarempleado(){
        boolean statusmodificar = false;
       
        
        String query = "UPDATE empleado set condicionempleado = '"+this.condicionempleado+"', "
                + "id_tipoempleado = "+this.tipoempleado+""
        + "where cedulapersona = "+this.cedulapersona+"";
      
        int ejecutado = conexion.ejecutarActualizacion(query);
        if(ejecutado != 0){
            statusmodificar = true;
        }
        return statusmodificar;
    }

}
