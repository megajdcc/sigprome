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
import Controlador.ControladorTriaje;
import Vista.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Jnatn'h
 */
public class ModeloTriaje {
    ControladorTriaje controlador;
    ModeloConexion conexion;
    private int id;
    private String peso;
    private String talla;
    private String temperatura;
    private String tension;
    private String glicemia;
    private String observacion;
    private int idusuario = Principal.getIdUsuario();
    private int idservicio;
    public boolean consultado;
    // constructorres ..
    public ModeloTriaje(){
          conexion = ModeloConexion.conexion;
    }
    public ModeloTriaje(ControladorTriaje controlador){
        this.controlador = controlador;
          conexion = ModeloConexion.conexion;
    }
    // constructor para modificar statu de consulta en triaje.... 
    public ModeloTriaje(int id, boolean consultado){
          conexion = ModeloConexion.conexion;
        this.setId(id);
        this.setConsultado(consultado);
        this.modificar();
        
    }
    //getters y setters 
    public void asignarentradas(String peso, String talla, String temperatura, String tension, String glicemia, String observacion, int idservicio){
        this.setTalla(talla);
        this.setTemperatura(temperatura);
        this.setTension(tension);
        this.setglicemia(glicemia);
        this.setPeso(peso);
        this.setObservacion(observacion);
        this.setIdservicio(idservicio);
    }
    public void setConsultado(boolean consultado){
        this.consultado = consultado;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setIdservicio(int idservicio){
        this.idservicio = idservicio;
    }
    public int getIdservicio(){
        return this.idservicio;
    }
    public void setPeso(String peso){
        this.peso = peso;
    }
    public String getPeso(){
        return this.peso;
    }
    public void setTalla(String Talla){
        this.talla = Talla;
    }
    public String getTalla(){
        return this.talla;
    }
    public void setTemperatura(String temperatura){
        this.temperatura = temperatura;
    }
    public String getTemperatura(){
        return this.temperatura;
    }
    public void setTension(String tension){
        this.tension = tension;
    }
    public String getTension(){
        return this.tension;
    }
    public void setglicemia(String glicemia){
        this.glicemia = glicemia;
    }
    public String getGlicemia(){
        return this.glicemia;
    }
    public void setObservacion(String observacion){
        this.observacion = observacion;
    }
    public String getObservacion(){
        return this.observacion;
    }
    
    //metodos del crud...
    public boolean registrar(){
        boolean registro = false;
        this.setConsultado(false);
        String query = "INSERT INTO triaje(peso,talla,temperatura,presion_arterial,glicemia,nota,id_servicio,id_usuario, consultado,procesado,sincronizado)"+
"                   VALUES ('"+getPeso()+"', '"+getTalla()+"', '"+getTemperatura()+"', '"+getTension()+"', '"+getGlicemia()+"',"
                + "'"+getObservacion()+"',"+getIdservicio()+","+Principal.getIdUsuario()+", "+this.consultado+", "+true+", false)";
        int ingreso = conexion.ejecutarActualizacion(query);
        
        if(ingreso != 0){
            registro = true;
            ModeloServicio servicio = new ModeloServicio();
            servicio.cambiarestadoprocesotriaje(registro,getIdservicio());
        }
      
        return registro;
    }
    private void modificar(){
        String query = "UPDATE triaje SET consultado = '"+this.consultado+"' where id = "+getId()+";";
       
        int modificacion = conexion.ejecutarActualizacion(query);
    }
    public boolean capturartriage(long cedula){
        boolean capturado = false; 
        String query = "select * from capturartriage("+cedula+")";
        ResultSet captura = conexion.ejecutarConsulta(query);
        if(captura == null){
            String consul  = "Verifique que la cedula indicada verdaderamente corresponda con un paciente que este esperando por consulta";
           
        }else{
            capturado = true;
            try {
                captura.next();
                this.setTemperatura(captura.getString("temperatura"));
                this.setTalla(captura.getString("talla"));
                this.setPeso(captura.getString("peso"));
                this.setglicemia(captura.getString("glicemia"));
                this.setTension(captura.getString("tension"));
                this.setObservacion(captura.getString("nota"));  
                this.setId(captura.getInt("idtriage"));
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTriaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        return capturado;
    }
    
}
