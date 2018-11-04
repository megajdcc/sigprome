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

import java.sql.Time;
import java.util.Date;

import Vista.Principal;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author Jnatn'h
 */
public class ModeloServicio {
    private Date fecha;
    private Time hora;
    private int id;
    private int id_cita;
    private int tiposervicio;
    private int usuario;
    private int paciente;
    
    // cmapos de administrativo... 
   private int cantidadpendientes = 0 ;
    
    ModeloConexion conexion;
    
    // metodos getter y setter 
    
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    public Date getFecha(){
        return fecha;
    }
    public void sethora(Time hora){
        this.hora = hora;
    }
    public Time getHora(){
        return this.hora;
    }
    public void setId(int id){
        this.id = id;
    }
    private int getId(){
        return this.id;
    }
    public void setIdcita(int cita){
        this.id_cita = cita;
    }
    public int getIdcita(){
        return this.id_cita;
    }
    public void setTiposervicio(int tiposervicio){
        this.tiposervicio = tiposervicio;
    }
    public int getTiposervicio(){
        return tiposervicio;
    }
    public void setUsuario(int usuario){
        this.usuario = usuario;
    }
    public void setPaciente(int paciente){
        this.paciente = paciente;
    } 

  public int getCantidadpendientes() {
    return cantidadpendientes;
  }

  public void setCantidadpendientes(int cantidadpendientes) {
    this.cantidadpendientes = cantidadpendientes;
  }
    
    // constructor... 
    public ModeloServicio(){
         conexion = ModeloConexion.conexion;
//        this.eliminarserviciospasados();
    }
    // por emergecia y administrativo... 
    public ModeloServicio(Date fecha, Time hora,int paciente, int usuario, int tiposervicio){
        this.setFecha(fecha);
        this.sethora(hora);
        this.setPaciente(paciente);
        this.setUsuario(usuario);
        this.setTiposervicio(tiposervicio);
         conexion = ModeloConexion.conexion;
    }
    // por cita
    public ModeloServicio(Date fecha, Time hora,int paciente, int usuario, int tiposervicio, int id_cita){
        this.setFecha(fecha);
        this.sethora(hora);
        this.setPaciente(paciente);
        this.setUsuario(usuario);
        this.setTiposervicio(tiposervicio);
        this.setIdcita(id_cita);
         conexion = ModeloConexion.conexion;
    }
    public void asignar(Date fecha, Time hora,int paciente, int usuario, int tiposervicio){
        this.setFecha(fecha);
        this.sethora(hora);
        this.setPaciente(paciente);
        this.setUsuario(usuario);
        this.setTiposervicio(tiposervicio);
    }
    public void asignar(Date fecha, Time hora,int paciente, int usuario, int tiposervicio, int id_cita){
        this.setFecha(fecha);
        this.sethora(hora);
        this.setPaciente(paciente);
        this.setUsuario(usuario);
        this.setTiposervicio(tiposervicio);
        this.setIdcita(id_cita);
    }
    public String[][] consultar(){
        // aca capturamos la citas que son para hoy ... 
            Locale localidad = new Locale("es");
            Date fechahoy = new Date();
            DateFormat da = new SimpleDateFormat("yyyy-MM-dd", localidad);
            String fech = da.format(fechahoy);
        try {
            this.limpiarcitas(fech);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
            String sentenciaSQL = "select * from listarcitashoy('"+fech+"')";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null){
               return null;
            }
            int i = 0;
            String[][] datos = null;
            try {
                while(resultadoConsulta.next()) i++;
                datos = new String[i][5];
                i = 0;
                resultadoConsulta.beforeFirst();
                    DateFormat daa = new SimpleDateFormat("EEE, d MMM yyyy", localidad);
                    String fechaasignad = null;
                    
                while(resultadoConsulta.next()){
                   datos[i][0] = resultadoConsulta.getString("cedula");
                   datos[i][1] = resultadoConsulta.getString("nombre");
                   datos[i][2] = resultadoConsulta.getString("apellido");
                   datos[i][3] = resultadoConsulta.getString("tipopersona");
                   fechaasignad = daa.format(resultadoConsulta.getDate("fecha"));
                   datos[i][4] = fechaasignad;
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                    
            }
            
            return datos;
    }
    private void eliminarserviciospasados(){
      String query  = "delete from servicio as ser where ser.paratriage = true AND ser.procesado = false AND ser.fecha <  (( SELECT to_date(( SELECT to_char('now'::text::date::timestamp with time zone, 'YYYY-MM-DD'::text) AS to_char), 'YYYY-MM-DD'::text) AS to_date))";
      int eliminado = conexion.ejecutarActualizacion(query);
      if(eliminado != 0){
        // aca va el codigo que me permitira eliminar los registros en la tabla cita que no se encuentren en la tabla serivicio... 
//        String query1 = "delete from cita where id = " 
      }
    }
    private void limpiarcitas(String fecha) throws SQLException{
        String query = "select count(cita.id) as cuenta from cita where cita.procesada = "+false+" AND cita.fecha < '"+fecha+"'";
        ResultSet cons = conexion.ejecutarConsulta(query);
        if(cons != null){
            cons.next();
            int cuenta = 0;
            cuenta = cons.getInt("cuenta");
            for (int i = 0; i <= cuenta; i++) {
                String quer = "select max(id) as cuant from cita where cita.procesada = '"+false+"' AND cita.fecha < '"+fecha+"'";
                ResultSet consult = conexion.ejecutarConsulta(quer);
                int cuant = 0;
                if(consult != null){
                    consult.next();
                    cuant = consult.getInt("cuant");
                }
                String query1  = "delete from servicio where id_cita = "+cuant+"";
                int dl = conexion.ejecutarActualizacion(query1);
                String query2  = "delete from cita where id = "+cuant+"";
                int dle = conexion.ejecutarActualizacion(query2);
            }
            if(cuenta > 0){
                String leyend = "Se ha eliminado "+cuenta+" registro de cita que nunca fue o fuerón procesadas";
                JOptionPane.showMessageDialog(new Principal(), leyend, "Limpieza de citas no procesadas", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    public boolean registrar(){    
        boolean registro = false;
        String query = "INSERT into servicio (fecha, hora, id_cita, id_usuario, id_paciente,sincronizado)"
                + "values('"+this.getFecha()+"', '"+this.getHora()+"', "+this.id_cita+", "+this.usuario+", "+this.paciente+",false)";
        int regis = conexion.ejecutarActualizacion(query);
        if (regis != 0) {
            registro = true;
        }
        return registro;
    }
    public boolean modificar(){
        boolean modificar = false;
        String query = "UPDATE servicio set fecha = '"+this.getFecha()+"', hora = '"+this.getHora()+"', "
                + "id_cita = "+this.id_cita+", id_usuario = "+this.usuario+", id_paciente = "+this.paciente+", sincronizado = false"
                + "where id  = "+this.getId()+"";
        int modifi = conexion.ejecutarActualizacion(query);
        if(modifi != 0){
            modificar = true;
        }
        return modificar;
    }
    // Metodos de registro para las posibles acciones administrativas... 
    public boolean registraradministrativo(int tiposerv,int idusuario, int idpaciente,String[] milista){
        boolean registro = false;
        this.setTiposervicio(tiposerv);
        this.setPaciente(idpaciente);
//        Calendar calendario = new GregorianCalendar();
        Date date = new Date();
        DateFormat fech = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hor = new SimpleDateFormat("HH:mm:ss");
        Time horaa = Time.valueOf(hor.format(date));
        this.sethora(horaa);
            Date fechaa = date;
            this.setFecha(fechaa);
        String query = "insert into servicio(fecha, hora, id_tipo_servicio,id_usuario,id_paciente)"
                + "values('"+fech.format(date)+"','"+this.getHora()+"',"+this.getTiposervicio()+","+idusuario+","+this.paciente+")";
        int registracion = conexion.ejecutarActualizacion(query);
        
        if(registracion != 0){
            int idservicio = 0;
            String query1 = "SELECT max(id) as id from servicio";
            ResultSet consult = conexion.ejecutarConsulta(query1);
          try {
            consult.next();
            idservicio = consult.getInt("id");
          } catch (SQLException ex) {
            Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE, null, ex);
          }
        
            int i = 0 ;
            int [] idopciones = new int[milista.length];
            
            for(String milist:milista){
              String query2 = "select id from opcionesadministrativas where nombre = '"+milist+"'";
             ResultSet capturaid = conexion.ejecutarConsulta(query2);
             
              try {
                capturaid.next();
                idopciones[i] = capturaid.getInt("id");
              } catch (SQLException ex) {
                Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE, null, ex);
              }
              i++;
            }
            int idestado = 0;
            String query3 = "select min(id) as idestado from estadoproceso where condicion = 1";
            ResultSet cons = conexion.ejecutarConsulta(query3);
          try {
            cons.next();
            idestado = cons.getInt("idestado");
          } catch (SQLException ex) {
            Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE, null, ex);
          }
          int re = 0 ;
          for(String milist:milista){
            String registrofinal = "INSERT INTO opcionservicio(idopciones,idservicios,idestadoproceso)"
                    + "values("+idopciones[re]+","+idservicio+","+idestado+")";
            int regist = conexion.ejecutarActualizacion(registrofinal);
            if(regist != 0){
              registro = true;
            }
            re++;
          }
        }
        return registro;
    }

    
//   Capturar estado si existe que el paciente tengas solicitudes administrativas activas... 
    public boolean capturarestado(int idpaciente, ModeloPaciente persona) throws SQLException{
        boolean posee = false;
        this.setId(idpaciente);
        String query = " select * from capturarestadopaciente("+getId()+");";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if(consulta != null ){
            consulta.next();
             this.setCantidadpendientes(consulta.getInt("pendientes"));
            if(getCantidadpendientes() > 0){
              posee = true;
              String query1 = "select (p.nombre || ' ' ||p.apellido) as nombrecompleto from persona as p \n" +
"	join paciente as pac on(p.cedula = pac.cedula)\n" +
"	where pac.id = "+idpaciente+"";
              ResultSet consul = conexion.ejecutarConsulta(query1);
              consul.next();
              persona.setNombre(consul.getString("nombrecompleto"));
            }
        }
        return posee;
    }
    public boolean actualizarreembolso(int estado){
      boolean modificar = false;
      String query  = "update servicio set reembolso = "+estado+" "
              + " where id_paciente  = 18 and reembolso != 0 or reembolso != null and procesado  = false";

      int modificacion = conexion.ejecutarActualizacion(query);
      if(modificacion !=0 ) {
        modificar = true;
      }
      return modificar;
    }
     public boolean actualizarestudios(int estado){
      boolean modificar = false;
      String query  = "update servicio set estudiosespeciales = "+estado+" "
              + " where id_paciente  = 18 and estudiosespeciales != 0 or estudiosespeciales != null and procesado  = false";
      int modificacion = conexion.ejecutarActualizacion(query);
      if(modificacion !=0 ) {
        modificar = true;
      }
      return modificar;
    }
      public boolean actualizarreposo(int estado){
      boolean modificar = false;
      String query  = "update servicio set validarreposo = "+estado+" "
              + " where id_paciente  = 18 and validarreposo != 0 or validarreposo != null and procesado  = false";
      int modificacion = conexion.ejecutarActualizacion(query);
      if(modificacion !=0 ) {
        modificar = true;
      }
      return modificar;
    }
    public boolean modificaradministrativo(int estado, int idpac){
        boolean modificado = false;
        Date date = new Date();
        DateFormat fech = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hor = new SimpleDateFormat("HH:mm:ss");
        Time horaa = Time.valueOf(hor.format(date));
        this.sethora(horaa);
        Date fechaa = date;
        this.setFecha(fechaa);
        String query = "UPDATE servicio SET fecha = '"+this.getFecha()+"', hora = '"+this.getHora()+"', ";
        return modificado;
    }
    public boolean registraremergencia(int tiposerv, int idpaciente, int idusuario){
        boolean registrado = false;
        this.setTiposervicio(tiposerv);
        this.setPaciente(idpaciente);
        Date date = new Date();
        DateFormat fech = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hor = new SimpleDateFormat("HH:mm:ss");
        Time horaa = Time.valueOf(hor.format(date));
        this.sethora(horaa);
        Date fechaa = date;
        this.setFecha(fechaa);
        String query = "INSERT INTO servicio(fecha, hora, id_tipo_servicio, id_usuario, id_paciente,procesado,paratriage) "
                + "values ('"+this.getFecha()+"','"+this.getHora()+"',"+this.getTiposervicio()+","+idusuario+","+this.paciente+","
                + ""+false+","+true+")";
        int registro = conexion.ejecutarActualizacion(query);
        if(registro != 0){
            registrado = true;
        }
        return registrado;
    }
    public int capturarid(long cedula){
         String query = "select * from capturaridservicio("+cedula+")";
       ResultSet captura = conexion.ejecutarConsulta(query);
       if(captura != null ){
           try {
               captura.next();
               this.setId(captura.getInt("servicio"));
           } catch (SQLException ex) {
               Logger.getLogger(ModeloTiposervicios.class.getName()).log(Level.SEVERE, null, ex);
           }
           
       }
       return this.getId();
    }
    public void cambiarestadoprocesotriaje(boolean registro, int idservicio){
        this.setId(idservicio);
        String query = "UPDATE servicio set procesado = "+registro+" where id = "+this.getId()+"";
       
        int actuali = conexion.ejecutarActualizacion(query);
    }
    public boolean registrarcita(int idcita, int idtiposervicio, int idpaciente){
        boolean procesado = false;
        boolean registro = false;
        this.setIdcita(idcita);
        this.setTiposervicio(idtiposervicio);
        this.setPaciente(idpaciente);
        Date date = new Date();
        DateFormat fech = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hor = new SimpleDateFormat("HH:mm:ss");
        Time horaa = Time.valueOf(hor.format(date));
        this.sethora(horaa);
        Date fechaa = date;
        this.setFecha(fechaa);
        String query = "insert into servicio (id_cita,id_tipo_servicio,id_usuario,id_paciente,procesado,fecha,hora,paratriage)"
                + " values("+getIdcita()+","+getTiposervicio()+","+Principal.getIdUsuario()+","+this.paciente+",'"+procesado+"',"
                + "'"+this.getFecha()+"','"+this.getHora()+"',"+false+")";
        int registroo = conexion.ejecutarActualizacion(query);
        if(registroo !=0){
            registro = true;
        }
        return registro;
    }
    public boolean verificarcita(){
        boolean existe = false;
        Date fechahoy = new Date();
        Locale localidad = new Locale("es");
//        DateFormat da = new SimpleDateFormat("EEE, d MMM yyyy", localidad);
        DateFormat da = new SimpleDateFormat("yyyy-MM-dd", localidad);
        String fechaasignad = null;
        fechaasignad = da.format(fechahoy);
        String query = "select * from verificarcitas('"+fechaasignad+"',"+this.paciente+")";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if(consulta != null){
            try {
                consulta.next();
                existe  = consulta.getBoolean("verificarcitas");
            } catch (SQLException ex) {
                Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return existe;
    }
    public boolean procesarcita(int idcita){
        boolean procesada  = false;
        this.id_cita = idcita;
        String query = "UPDATE cita set procesada = '"+true+"' where id = "+this.id_cita+"";
        System.out.println(query);
        int modificada = conexion.ejecutarActualizacion(query);
        if(modificada !=0){
            String query1 = "UPDATE servicio set procesado = '"+false+"', citaporconsulta = '"+true+"', paratriage = "+true+" where id_cita = "+this.id_cita+"";   
            System.out.println(query);
            int consul = conexion.ejecutarActualizacion(query1);
            if(consul != 0){
                procesada = true;
            }
        }
        return procesada;
    }
    public boolean procesarcitaconsulta(int idcita){
        boolean procesada  = false;
        this.id_cita = idcita;
        String query = "UPDATE cita set procesada = '"+true+"' where id = "+this.id_cita+"";
        int modificada = conexion.ejecutarActualizacion(query);
        if(modificada !=0){
            String query1 = "UPDATE servicio set procesado = '"+true+"', citaporconsulta = '"+false+"' where id_cita = "+this.id_cita+"";   
            int consul = conexion.ejecutarActualizacion(query1);
            if(consul != 0){
                procesada = true;
            }
        }
        return procesada;
    }
    public int capturaridcita(int idpaciente){
        boolean consulta = false;
        int idcita = 0;
        this.paciente = idpaciente;
        String query = "SELECT cit.id from servicio as ser \n" +
                        "join cita as cit on(ser.id_cita = cit.id)  \n" +
                        "where id_paciente ="+this.paciente+" AND cit.cancelada = '"+false+"' AND cit.procesada = '"+false+"'";
        ResultSet consultaa = conexion.ejecutarConsulta(query);
        if(consultaa != null){
            try {
                    consultaa.next();
                    idcita = consultaa.getInt("id");
            } catch (Exception ex2) {
                System.out.println(ex2.getMessage());
            }
           
        }
        return idcita;
    }
    public void listaropcionesadministrativas(JList lista){
      
      String sentenciasql = "SELECT * from opcionesadministrativas";
      ResultSet consulta = conexion.ejecutarConsulta(sentenciasql);
      if(consulta != null){
        try {
          int i = 0 ;
          DefaultListModel milista = new DefaultListModel();
          
          while(consulta.next()){
               
               milista.addElement(consulta.getString("nombre"));
          }
          lista.setModel(milista);
        } catch (SQLException ex) {
          Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    public void listaropcionesadministrativasrestante(JList lista){
      String sentenciasql = "select * from capturaropcionesadministrativasrestantes("+getId()+")";
      ResultSet consulta = conexion.ejecutarConsulta(sentenciasql);
      if(consulta != null){
        try {
          int i = 0 ;
          DefaultListModel milista = new DefaultListModel();
          
          while(consulta.next()){
               
               milista.addElement(consulta.getString("nombre"));
          }
          lista.setModel(milista);
        } catch (SQLException ex) {
          Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    public String[][] Listaropcionesadministrativaspendientes(){
            String sentenciaSQL = "select * from opcionesadministrativaspendientes";
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
                String[][] datos = new String[i][5];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                   datos[i][0] = resultadoConsulta.getString("cedula");
                   datos[i][1] = resultadoConsulta.getString("nombrecompleto");
                   datos[i][2] = resultadoConsulta.getString("tipopersona");
                   datos[i][3] = resultadoConsulta.getString("opcionadministrativa");
                   datos[i][4] = resultadoConsulta.getString("estadoproceso");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                    return null;
            }
    }
    public int capturarestadoproceso(int idopciones, int idpaciente){
      int idestadoproceso= 0 ;
          String query = "SELECT id from estadoproceso where  ";
      
      return idestadoproceso;
    }
    public boolean capturaridservicios(int idopc, int idpac){
      boolean capturado = false;
      String query = "SELECT * FROM  capturaridserviciosolicitudes("+idopc+","+idpac+")";
      ResultSet result = conexion.ejecutarConsulta(query);
      if(result != null){
        try {
          result.next();
          setId(result.getInt("idservicio"));
          capturado = true;
        } catch (SQLException ex) {
          Logger.getLogger(ModeloServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return capturado;
    }
    public boolean modificaropcionservicio(int opciones,int idestado){
      boolean modificado = false;
      String query = "UPDATE opcionservicio set idestadoproceso = "+idestado+" where idopciones = "+opciones+" and idservicios = "+getId()+"";
      int result = conexion.ejecutarActualizacion(query);
      if(result != 0){
        modificado = true;
      }
      return modificado;
    }
}
