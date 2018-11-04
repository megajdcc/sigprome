/*
 * The MIT License
 *
 * Copyright 2017 Jnatn'h.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
public class ModeloCita {
    
    ModeloConexion conexion;
    private int id;
    private String fecha;
    private boolean cancelada;
    private boolean procesada;
    
    // constructor 
    public ModeloCita(String fecha){
        setFecha(fecha);
        conexion = ModeloConexion.conexion;
    }
    public ModeloCita(int id){
        setId(id);
        conexion = ModeloConexion.conexion;
    }
    public ModeloCita(){
        conexion = ModeloConexion.conexion;
    }
   
    //getters y setters 
    public void setConexion(ModeloConexion conec){
        this.conexion = conec;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    public String getFecha(){
        return this.fecha;
    }
    public void setCancelar(boolean cancelar){
        this.cancelada = cancelar;
    }
    public boolean getCancelar(){
        return this.cancelada;
    }
    public void setProcesar(boolean procesar){
        this.procesada = procesar;
    }
    public boolean getProcesar(){
        return this.procesada;
    }
    
    //Metodos 
    public boolean verificarfecha(){
        boolean verificar = false;
        String query = "select consultarcita('"+getFecha()+"')";
        System.out.println(query);
        ResultSet existencia  = conexion.ejecutarConsulta(query);
        if(existencia != null){
            try {
                existencia.next();
                verificar = existencia.getBoolean("consultarcita");
            } catch (SQLException ex) {
                Logger.getLogger(ModeloCita.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return verificar;
    }
    public boolean nuevacita(){
        boolean asignada = false;
        setCancelar(false);
        setProcesar(false);
        String query = "INSERT INTO cita(fecha,cancelada,procesada,sincronizado)"
                + "values('"+getFecha()+"','"+getCancelar()+"','"+getProcesar()+"',false)";
        int regis = conexion.ejecutarActualizacion(query);
        if(regis !=0){
              String query1 = "SELECT max(id) as id from cita where fecha = '"+getFecha()+"'";
              ResultSet consul = conexion.ejecutarConsulta(query1);
              if(consul != null){
                  try {
                      consul.next();
                      setId(consul.getInt("id"));
                  } catch (SQLException ex) {
                      Logger.getLogger(ModeloCita.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  asignada = true;
              }   
        }
        return asignada;
    }
}
