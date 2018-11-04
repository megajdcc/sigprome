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

/**
 *
 * @author Jnatn'h
 */
public class ModeloEstudio {
    
    // Propiedades... 
    private int id;
    private String nombre;
    
    private ModeloConexion conexion;
    //getters y setters 
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }
    
    //constructor..
    public ModeloEstudio(){
         conexion = ModeloConexion.conexion;
    }
    /*
    *@param int id del estudio
    */
    public ModeloEstudio(int id){
        setId(id);
         conexion = ModeloConexion.conexion;
    }
    /*
    *@param String nombre del estudio.
    */
    public ModeloEstudio(String nombre){
        setNombre(nombre);
         conexion = ModeloConexion.conexion;
    }
    
    
    //Metodos o funciones
    
    public String[][] listarEstudios(){
        boolean statusConsulta = false;   
        String sentenciaSQL = "select * from listarestudiosespeciales";
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
    public boolean consultarEstudio() throws SQLException{
        boolean encontrado= false;
        String query = "select * from estudiosespeciales where nombre = '"+getNombre()+"'";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if(consulta != null){
            consulta.next();
            setNombre(consulta.getString("nombre"));
            setId(consulta.getInt("id"));
            encontrado = true;
        }
        return encontrado;
    }
    public boolean registrar(){
        boolean registrado = false;
        String query = "INSERT into estudiosespeciales(nombre) values('"+getNombre()+"')";
        int registro = conexion.ejecutarActualizacion(query);
        if(registro != 0){
            registrado = true;
        }
        return registrado;
    }
    public boolean modificar(){
        boolean modificado = false;
        String query = "UPDATE estudiosespeciales set nombre = '"+getNombre()+"' where id = "+getId()+"";
        int registro = conexion.ejecutarActualizacion(query);
        if(registro != 0){
            modificado = true;
        }
        return modificado;
    }
    public boolean eliminar(){
        boolean eliminacion = false;
        String query = "DELETE from estudiosespeciales where id = "+getId()+"";
        int delete = conexion.ejecutarActualizacion(query);
        if(delete != 0){
            eliminacion = true;
        }
        return eliminacion;
    }
    
}
