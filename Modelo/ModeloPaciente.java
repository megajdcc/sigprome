/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import Vista.Principal;
/**
 *
 * @author Jnatn'h
 */
public class ModeloPaciente extends ModeloPersona implements Familiar{
//  Atributos...
    private long cedulaafiliado;
    private String nombreafiliado;
    private String apellidoafiliado;
    private int expediente;
    private int id;
    private boolean beneficiario;
    private String campo_adicional;
    private String condicionempleado;
    private String tipoempleado;
    private String parentesco;
    private Principal principal;
//  Constructores de la clase...
    public ModeloPaciente(){
     conexion = ModeloConexion.conexion;
    }
    public ModeloPaciente(long cedula, String nombre, String apellido, String sexo, Date fechanacimiento, long telefono, String direccion, int id_tipopersona, int id_parroquia){
          conexion = ModeloConexion.conexion;
        this.setCedula(cedula);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setSexo(sexo);
        this.setFechanacimiento(fechanacimiento);
        this.setTelefono(telefono);
        this.setDireccion(direccion);
        this.setIdtipopersona(id_tipopersona);
        this.setIdparroquia(id_parroquia);
         
    }
    public ModeloPaciente(long cedula,String campo_adicional, boolean beneficiario){
          conexion = ModeloConexion.conexion;
      this.setCedulaPersona(cedula);
        this.campo_adicional = campo_adicional;
        this.beneficiario = beneficiario;
       
    }
    public ModeloPaciente(long cedula,int expediente, String campo_adicional, boolean beneficiario){
         conexion = ModeloConexion.conexion;  
      this.setCedulaPersona(cedula);
        this.expediente = expediente;
        this.campo_adicional = campo_adicional;
        
    }
    public ModeloPaciente(long cedula, boolean beneficiario){
        conexion = ModeloConexion.conexion;
         this.setCedulaPersona(cedula);
         
    }
    public ModeloPaciente(Long cedula){
         conexion = ModeloConexion.conexion;
        this.cedula = cedula;
        
    }
//  Getter y Setter...
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public int getExpediente(){
        return expediente;
    }
    public void setExpediente(int expediente){
        this.expediente = expediente;
    }
    public boolean getBeneficiario(){
        return beneficiario;
    }
    public void setBeneficiario(boolean beneficiario){
        this.beneficiario = beneficiario;
    }
    public String getCampoadicional(){
        return campo_adicional; 
    }
    public void setCampoAdicional(String campoadicional){
        this.campo_adicional = campoadicional;
    }
    public long getCedulaPersona(){
       return cedula;
   }
    public void setCedulaPersona(long cedula){
       this.cedula = cedula;
   }
    public String getCondicionempleado(){
       return condicionempleado;
   }
    public void setCondicionempleado(String condicionempleado){
       this.condicionempleado = condicionempleado;
   }
    public String getTipoempleado(){
       return tipoempleado;
   }
    public void setTipoempleado(String tipoempleado){
       this.tipoempleado = tipoempleado;
   }
    public long getCedulaafiliado(){
       return cedulaafiliado;
   }
    public void setCedulaafiliado(long cedulaafiliado){
       this.cedulaafiliado = cedulaafiliado;
   }
    public String getNombreafiliado(){
      return nombreafiliado;
   }
    public void setNombreafiliado(String nombreafiliado){
       this.nombreafiliado = nombreafiliado;
   }
    public String getApellidoafiliado(){
       return apellidoafiliado;
   }
    public void setApellidoafiliado(String apellidoafiliado){
       this.apellidoafiliado = apellidoafiliado;
   }
    public void setParentesco(String parentesco){
        this.parentesco = parentesco;
    }
    public String getParentesco(){
        return this.parentesco;
    }  
//  Metodos 
    public void  Listartipopersona(){        
        String sentenciaSQL = "select * from listartipopersona";
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
        if(resultadoConsulta == null) ;
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][1];
                i = 0;
                resultadoConsulta.beforeFirst();
                 DefaultComboBoxModel model = new DefaultComboBoxModel();
                model.addElement("Tipo paciente");
                while(resultadoConsulta.next()){
                    model.addElement(resultadoConsulta.getObject("tipopersona"));
                }
            } catch (SQLException ex) {
            }     
}
    public boolean registrarpaciente() throws SQLException{
        boolean statusIncluir = false;
        String sentenciaSql = "insert into paciente(cedula,expediente,campo_adicional,beneficiario,sincronizado) values("+cedula+","+expediente+",'"+campo_adicional+"',"+beneficiario+",false)";

        int resultadoIncluir = conexion.ejecutarActualizacion(sentenciaSql);
        if(resultadoIncluir != 0){
          statusIncluir = true;
        }  
        return statusIncluir;
    }//fin de incluir
    public String[][] listarafiliados(){
        boolean statusConsulta = false;
        String sentenciaSQL = "select * from listarpacientes";
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
            while(resultadoConsulta.next())
            {
               datos[i][0] = resultadoConsulta.getString("cedula");
               datos[i][1] = resultadoConsulta.getString("nombre");
               datos[i][2] = resultadoConsulta.getString("apellido");
               datos[i][3] = resultadoConsulta.getString("tipopersona");
                i++;
            }
            return datos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
                return null;
        }
    }
    public String[][] ListarPacientes(){
          boolean statusConsulta = false;
            String sentenciaSQL = "select * from listarpacientes";
           
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null){
               String Error = "error en la consulta";
               System.out.println(Error);
               return null;
            }
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][4];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next())
                {
                   datos[i][0] = resultadoConsulta.getString("cedula");
                   datos[i][1] = resultadoConsulta.getString("nombre");
                   datos[i][2] = resultadoConsulta.getString("apellido");
                   datos[i][3] = resultadoConsulta.getString("tipopersona");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
                    return null;
            }
    }
    public String[][] ListarPacientesAdministrativos(){
            boolean statusConsulta = false;
            String sentenciaSQL = "select * from listarpacientesadministrativos";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null)
            {
               String Error = "error en la consulta";
               return null;
            }
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][4];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next())
                {
                   datos[i][0] = resultadoConsulta.getString("cedula");
                   datos[i][1] = resultadoConsulta.getString("nombre");
                   datos[i][2] = resultadoConsulta.getString("apellido");
                   datos[i][3] = resultadoConsulta.getString("tipopersona");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                    return null;
            }
    }
    public String[][] ListarPacientesEspera(){
            String sentenciaSQL = "select * from enesperatriaje";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null){
               return null;
            }
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][5];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next())
                {
                   datos[i][0] = resultadoConsulta.getString("cedula");
                   datos[i][1] = resultadoConsulta.getString("nombre");
                   datos[i][2] = resultadoConsulta.getString("apellido");
                   datos[i][3] = resultadoConsulta.getString("tipopersona");
                   datos[i][4] = resultadoConsulta.getString("tiposervicio");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                ex.printStackTrace();
                    return null;
            }
    }
    public String[][] ListarPacienteEsperaConsulta(){
        String query = "select * from personaparaconsulta";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if(consulta == null){
            String Error = "error en la consulta";
            System.out.println(Error);
            return null;
        }
             try {
                int i = 0;
                while(consulta.next()) i++;
                String[][] datos = new String[i][5];
                i = 0;
                consulta.beforeFirst();
                while(consulta.next())
                {
                   datos[i][0] = consulta.getString("cedula");
                   datos[i][1] = consulta.getString("nombre");
                   datos[i][2] = consulta.getString("apellido");
                   datos[i][3] = consulta.getString("tipopersona");
                   datos[i][4] = consulta.getString("tiposervicio");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
    }
    public String[][] ListarAfiliado(){
        String query = "select * from listarafiliados";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if(consulta == null){
            String Error = "error en la consulta";
            System.out.println(Error);
            return null;
        }
             try {
                int i = 0;
                while(consulta.next()) i++;
                String[][] datos = new String[i][4];
                i = 0;
                consulta.beforeFirst();
                while(consulta.next()){
                   datos[i][0] = consulta.getString("cedula");
                   datos[i][1] = consulta.getString("nombre");
                   datos[i][2] = consulta.getString("apellido");
                   datos[i][3] = consulta.getString("tipopersona");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
    }
    public boolean consultarafiliado(long cedula){
            boolean statusConsulta=false;  
            String sentenciaSQL = "select p.cedula, p.nombre, p.apellido, tp.nombre as tipoempleado, emp.condicionempleado from persona as p \n" +
                                  "join empleado as emp on (p.cedula = emp.cedulapersona) join tipoempleado as tp on(emp.id_tipoempleado = tp.id)\n" +
                                  "where p.cedula = "+cedula+"";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    this.setCedulaafiliado(resultadoConsulta.getLong("cedula"));
                    this.setNombreafiliado(resultadoConsulta.getString("nombre"));
                    this.setApellidoafiliado(resultadoConsulta.getString("apellido"));
                    this.setTipoempleado(resultadoConsulta.getString("tipoempleado"));
                    this.setCondicionempleado(resultadoConsulta.getString("condicionempleado"));
                    
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipopaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            return statusConsulta;
    }
    public boolean consultarpaciente(){
        boolean statusConsulta=false;      
        String sentenciaSQL = "select id_tipopersona from persona where cedula  = "+cedula+"";
        ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            try {
                if(resultadoConsulta != null){
                    resultadoConsulta.next();
                    this.id_tipopersona  = (resultadoConsulta.getInt("id_tipopersona"));
  
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipopaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            return statusConsulta;
   }
    public boolean capturardatospaciente(){
        boolean encontrado = false;
        String query = "SELECT p.cedula,p.nombre,p.apellido, p.sexo, p.fecha_nacimiento, p.direccion,  p.telefono, tp.nombre as tipopersona, p.id_parroquia,\n" +
                    "parr.nombre as nombreparroquia,p.id_tipopersona, pac.campo_adicional, pac.expediente\n" +
                    "from persona as p \n" +
                    "join parroquia as parr on p.id_parroquia = parr.id\n" +
                    "join tipopersona as tp on p.id_tipopersona = tp.id\n" +
                    "join paciente as pac on p.cedula = pac.cedula  where p.cedula = "+this.cedula+"";
       ResultSet consulta = conexion.ejecutarConsulta(query);
        try {
            if(consulta != null){
                consulta.next();
                //Persona
                this.setCedula(consulta.getLong("cedula"));
                this.setNombre(consulta.getString("nombre"));
                this.setApellido(consulta.getString("apellido"));
                this.setSexo(consulta.getString("sexo"));
                this.setFechanacimiento(consulta.getDate("fecha_nacimiento"));
                this.setDireccion(consulta.getString("direccion"));
                this.setTelefono(consulta.getLong("telefono"));
                this.setIdtipopersona(consulta.getInt("id_tipopersona"));
                this.setIdparroquia(consulta.getInt("id_parroquia"));
                this.setCampoAdicional(consulta.getString("campo_adicional"));
                super.setTipopersona(consulta.getString("tipopersona"));
                if (consulta.getString("tipopersona").equalsIgnoreCase("empleado")) {
                    this.capturarempleado(this.getCedula());
                }else if(consulta.getString("tipopersona").equalsIgnoreCase("estudiante")){
                    this.setBeneficiario(true);
                    this.setExpediente(consulta.getInt("expediente"));
                }else if(consulta.getString("tipopersona").equalsIgnoreCase("familiar")){
                    this.consultarfamiliar(getCedula());
                }
                encontrado = true;
            }
        } catch (SQLException e) {
             Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, e.getMessage());
        }
     
        return encontrado;
    }
    @Override
    public boolean registrar() {
        boolean registro = false;
        String query = "INSERT into persona( cedula, nombre, apellido, sexo, fecha_nacimiento, direccion,"
                + "id_tipopersona, id_parroquia, telefono,actualizado) VALUES("+this.cedula+",'"+this.nombre+"','"+this.apellido+"', "
                + "'"+this.sexo+"','"+this.fechanacimiento+"','"+this.direccion+"',"+this.id_tipopersona+","+this.id_parroquia+","+this.telefono+",false)";
        int ingreso = conexion.ejecutarActualizacion(query);
        if (ingreso != 0) {
            registro = true;
        }
        return registro;
    }
    @Override
    public boolean modificar() {
          boolean modificar = false; 
          String query = "UPDATE paciente set campo_adicional='"+this.campo_adicional+"', beneficiario ="+this.beneficiario+", sincronizado = false"
                  + " where cedula = "+this.cedula+"";
          int modificar1 = conexion.ejecutarActualizacion(query);
          if (modificar1 != 0){
            modificar = true;
        }
        return modificar;
    }
    @Override
    public boolean registrarfamiliar(String parentesco, long cedulaafiliado) {
    	System.out.println("cedula del afiliado: "+ cedulaafiliado);
    	boolean registro = false;
    	Connection conec = conexion.getConec();
    	
    	
    	String registrarpersona = "INSERT into persona(cedula,nombre,apellido,sexo,fecha_nacimiento,direccion,id_tipopersona,id_parroquia,telefono,actualizado) values(?,?,?,?,?,?,?,?,?,?)"; 
    	String registrarpaciente = "insert into paciente(campo_adicional,cedula,beneficiario) values(?,?,?)";
    	String registrarfamiliar = "insert into familiar(parentesco,cedula,cedulaafiliado) values(?,?,?)";
    	PreparedStatement persona = null, paciente= null, familiar = null;
    	try {
			conec.setAutoCommit(false);
			java.sql.Date date = new java.sql.Date(fechanacimiento.getTime()); 
					
			persona = conec.prepareStatement(registrarpersona);
			persona.setLong(1, this.cedula);
			persona.setString(2, this.nombre);
			persona.setString(3, apellido);
			persona.setString(4, sexo);
			persona.setDate(5, date);
			persona.setString(6, direccion);
			persona.setInt(7, id_tipopersona);
			persona.setInt(8, id_parroquia);
			persona.setLong(9, telefono);
			persona.setBoolean(10, false);
			persona.executeUpdate();
			
			paciente = conec.prepareStatement(registrarpaciente);
			paciente.setString(1, campo_adicional);
			paciente.setLong(2, cedula);
			paciente.setBoolean(3, beneficiario);
			
			paciente.executeUpdate();

			familiar = conec.prepareStatement(registrarfamiliar);
			familiar.setString(1,parentesco);
			familiar.setLong(2,cedulaafiliado);
			familiar.setLong(3, cedula);
			
			familiar.executeUpdate();
			
			conec.commit();
			registro = true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(principal, "No se pudo registrar al familiar: "+ e.getMessage(),
					"Error de registro", JOptionPane.WARNING_MESSAGE);
			try {
				conec.rollback();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
    	return registro;
    }
    @Override
    public boolean modificarfamiliar(String parentesco, long cedulaafiliado, long cedula) {
        
       boolean modificacionfa = false;
       this.parentesco = parentesco;
       this.cedulaafiliado = cedulaafiliado;
       super.cedula = cedula;
       String query = "UPDATE familiar SET cedulaafiliado = "+this.cedulaafiliado+", parentesco = '"+this.parentesco+"' "
               + "where cedula = "+super.cedula+"";
       int modificacion = conexion.ejecutarActualizacion(query);
        if (modificacion != 0) {
            modificacionfa = true;
        }
        return modificacionfa;
    }
    public boolean modificarestudiante(){
        boolean modificar = false;
        String query = "UPDATE paciente set campo_adicional='"+this.campo_adicional+"', beneficiario ="+this.beneficiario+","
                  + "expediente = "+this.expediente+" where cedula = "+this.cedula+"";
        int modificar1 = conexion.ejecutarActualizacion(query);
          if (modificar1 != 0){
            modificar = true;
        }
        return modificar;
    }
    @Override
    public void consultarfamiliar(long cedula) {
      String query = "select * from familiar where cedula = "+cedula+"";
      ResultSet consulta = conexion.ejecutarConsulta(query);
      if(consulta != null){
          try {
            consulta.next();
            this.setParentesco(consulta.getString("parentesco"));
            this.setCedulaafiliado(consulta.getLong("cedulaafiliado"));
          } catch (SQLException ex) {
              Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
          }
         
      }
    }
    public void capturarempleado(long cedulapersona) {
        
        String query = "select emp.condicionempleado, emp.id_tipoempleado, tp.nombre as tipoempleado from empleado as emp\n" +
                        "join tipoempleado as tp on emp.id_tipoempleado = tp.id\n" +
                        "where emp.cedulapersona  = "+cedulapersona+"";
        ResultSet consulta = conexion.ejecutarConsulta(query);
        if (consulta != null) {
            try {
                consulta.next();
                this.setCondicionempleado(consulta.getString("condicionempleado"));
                this.setTipoempleado(consulta.getString("tipoempleado"));
            } catch (SQLException ex) {
                Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public void capturardatosafiliado(long cedulaafiliado){
       String query = "SELECT p.nombre, p.apellido from persona as p where cedula = "+cedulaafiliado+"";
       ResultSet consulta = conexion.ejecutarConsulta(query);
        if (consulta != null) {
            try {
                consulta.next();
                this.setNombreafiliado(consulta.getString("nombre"));
                this.setApellidoafiliado(consulta.getString("apellido"));
                this.capturarempleado(cedulaafiliado);
            } catch (SQLException ex) {
                Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public int capturarid() throws SQLException{
        String query = "SELECT id FROM paciente where cedula = "+this.getCedula()+"";
        ResultSet consult = conexion.ejecutarConsulta(query);
        if(consult != null){
            consult.next();
            this.setId(consult.getInt("id"));
        }
        return getId();
    }
    public String CapturarTipoPaciente(long cedula){
      String tipoPaciente = null; 
      String query = "select * from Capturartipopersona("+cedula+")";
      ResultSet Consult = conexion.ejecutarConsulta(query);
      if(Consult != null){
        try {
          Consult.next();
          tipoPaciente = Consult.getString("tipopersona");
        } catch (SQLException ex) {
          Logger.getLogger(ModeloPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return tipoPaciente;
    }
    
    
    
    public String[][] ListarPacienteFevida(){
            
            String sentenciaSQL = "select * from listarpacientesfevida";
           
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
                while(resultadoConsulta.next())
                {
                   datos[i][0] = resultadoConsulta.getString("cedula");
                   datos[i][1] = resultadoConsulta.getString("nombre");
                   datos[i][2] = resultadoConsulta.getString("apellido");
                   datos[i][3] = resultadoConsulta.getString("tipopersona");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
//                System.out.println(ex.getMessage());
                    return null;
            }
    }
    
    
    
    public ArrayList capturardatos(Long cedulapaciente){
            
            ArrayList datos = new ArrayList();

            Connection conec = conexion.getConec();
            
            String sql = "select * from datospacientefevida(?)";
             
            PreparedStatement consulta = null;
            
        try {
            consulta = conec.prepareStatement(sql);
            consulta.setLong(1,cedulapaciente);
            ResultSet result = consulta.executeQuery();
            
            while(result.next()){
                datos.add(result.getInt("edad"));
                datos.add(result.getString("nombre"));
                datos.add(result.getLong("cedula"));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            if(consulta != null){
                try {
                    consulta.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
            }
        }        
        return datos;
    }
}

    