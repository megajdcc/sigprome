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

import Vista.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Jnatn'h
 */
public class ModeloConsulta {
    ModeloConexion conexion;
    
    // atributos de clases ... 
    
    private int id;
    private Date fecha;
    private Time hora;
    private String sintoma;
    private String diagnostico;
    private int id_triaje;
    private int patologia;
    private int idusuario  = Principal.getIdUsuario();
    //constructores... 
    public ModeloConsulta(){
       conexion = ModeloConexion.conexion;
    }
    
    // getters y setters. 
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
       return this.id;
    }
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    public Date getFecha(){
        return fecha;
    }
    public void setHora(Time hora){
        this.hora = hora;
    }
    public Date getHora(){
        return hora;
    }
    public void setSintoma(String nombre){
        this.sintoma = nombre;
    }
    public String getSintoma(){
        return sintoma;
    }
    public void setDiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }
    public String getDiagnostico(){
        return this.diagnostico;
    }
    public void setIdtriaje(int idtriaje){
        this.id_triaje = idtriaje;
    }
    public int getIdtriaje(){
        return id_triaje;
    }
    public void setPatologia(int patologia){
        this.patologia = patologia;
    }
    public int getPatologia(){
        return patologia;
    }
    
    public int caputrarultimoid(){
        int idcon = 0;
        String query = "SELECT max(id)as ultimoid from consulta";
        ResultSet cons = conexion.ejecutarConsulta(query);
        if(cons != null){
          
            try {
                cons.next();
                idcon = cons.getInt("ultimoid");
                
            } catch (SQLException ex) {
                Logger.getLogger(ModeloConsulta.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        return idcon;
    }
    
    // metodos de movimientos... 
    public boolean registrar(){
        boolean registro = false;
        Date date = new Date();
        DateFormat fech = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hor = new SimpleDateFormat("HH:mm:ss");
        Time horaa = Time.valueOf(hor.format(date));
        this.setHora(horaa);
            Date fechaa = date;
            this.setFecha(fechaa);
        String query = "insert into consulta (fecha, hora, sintoma, diagnostico, id_triaje, id_usuario,sincronizado)"
                + "values('"+getFecha()+"','"+getHora()+"','"+getSintoma()+"','"+getDiagnostico()+"'"
                + ","+getIdtriaje()+","+this.idusuario+",false)";
        int regist = conexion.ejecutarActualizacion(query);
        if(regist != 0){
            setId(this.caputrarultimoid());
            registro = true;
            ModeloTriaje triaje = new ModeloTriaje(getIdtriaje(), true);
        }
        return registro;  
    }

    public boolean insertarrecipe(String recipe,String indicacion,Date vencrecipe, Date vencindicacion){
      Recipe reci = new Recipe(recipe,indicacion,vencrecipe,vencindicacion);
      boolean registro = reci.registrarrecipe();
      return registro;
    }
    public boolean insertarreposo(String motivo, int diasreposo){
      Reposo repos = new Reposo(motivo,diasreposo);
      boolean registro = repos.registrarreposo();
      return registro;
    }
    private class Recipe implements Indicaciones{
      //CAMPOS
      private String recipe,indicacion;
      private Date vencrecipe, vencindicaciones;
      //CONSTRUCTOR 
      Recipe(String recipe,String indicacion,Date vencrecipe, Date vencindicacion){
        this.recipe = recipe;
        this.indicacion = indicacion;
        this.vencrecipe = vencrecipe;
        this.vencindicaciones = vencindicacion;
      }  
    boolean registrarrecipe(){
       boolean registrado = false;
       String query = "insert into recipe(recipe,vencimiento,id_consulta,sincronizado) values('"+recipe+"','"+vencrecipe+"',"+getId()+",false)";
       int registro = conexion.ejecutarActualizacion(query);
       if(registro != 0){
           String query1 = "SELECT max(id)as id from recipe";
           ResultSet consult = conexion.ejecutarConsulta(query1);
           int idrecipe = 0;
           if(consult != null){
             try {
               consult.next();
               idrecipe = consult.getInt("id");
             } catch (SQLException ex) {
               Logger.getLogger(ModeloConsulta.class.getName()).log(Level.SEVERE, null, ex);
             }
           }
         registrado = registrarindicacion(idrecipe);
     }
     return registrado;
     }
    
    @Override
    public boolean registrarindicacion(int idrecipe) {
        boolean registro = false;
        String query = "insert into indicaciones(indicacion,vencimiento,id_recipe,sincronizado) values('"+indicacion+"','"+vencindicaciones+"',"+idrecipe+",false)";
        int regis = conexion.ejecutarActualizacion(query);
        if(regis!= 0){
          registro = true;
        }
        return registro;
    }

    @Override
    public void consultarindicacion(int idrecipe) {
     
    }
    }
private class Reposo{
      //CAMPOS
      private String motivo;
      private int diasreposo;
      //CONSTRUCTOR 
      Reposo(String motivo, int diasreposo){
        this.motivo = motivo;
        this.diasreposo = diasreposo;
      }  
    boolean registrarreposo(){
       boolean registrado = false;
       String query = "insert into reposo(motivo,dias,id_consulta,sincronizado) values('"+motivo+"','"+diasreposo+"',"+getId()+",false)";
       int registro = conexion.ejecutarActualizacion(query);
       if(registro != 0){
          registrado = true;
     }
     return registrado;
     }
    }
}

