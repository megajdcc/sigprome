package Modelo;

import Vista.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ModeloUsuario extends ModeloPersona {
    
   
    // constructor con todos los parametros de la clase para registrar... 
    public ModeloUsuario(long cedula,String nombre,String apellido,String sexo,
            Date fechanacimiento,long telefono,int parroquia, String direccion,String nombreusuario,String contrasena,
            int especialidad,int tipousuario){
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.telefono = telefono;
        this.fechanacimiento = fechanacimiento;
        this.id_parroquia = parroquia;
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.nombreusuario = nombreusuario;
        this.tipousuario = tipousuario;
        this.especialidad = especialidad;
        conexion = new ModeloConexion();
    }
   
    //constructor para validar usuario; 
    public ModeloUsuario(String nombreusuario, String contrasena){
        this.nombreusuario = nombreusuario;
        this.contrasena = contrasena;
        conexion = new ModeloConexion();
    } 
//constructor con un solo parametro para capturar el nombre de usuario y el tipo...
    public ModeloUsuario(int id){
       this.setId_usuario(id);
        conexion = new ModeloConexion();
    }
    
    public ModeloUsuario(){
      conexion = new ModeloConexion();
    };
    public ModeloUsuario(String nombre){
        this.nombre = nombre;
        conexion = new ModeloConexion();
    }
    public ModeloUsuario(long cedula){
        this.cedula = cedula;
          conexion = new ModeloConexion();
    }
//    Getter y Setter...
    public int getId_usuario(){
        return id;
    }
    public void setId_usuario(int id){
        this.id = id;
    }
    public String getNombreUsuario(){
        return nombreusuario;
    }
    public void setNombreUsuario(String nombreusuario){
        this.nombreusuario = nombreusuario;
    }
    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }
    public String getContrasena(){
        return this.contrasena;
    }
    public String getNombtipousuario(){
        return this.nombtipousuario;
    }
    public void setNombtipousuario(String tipousuario){
        this.nombtipousuario = tipousuario;
    }
    public int getTipousuario(){
        return tipousuario;
    }
    public void setTipousuario(int tipousuario){
        this.tipousuario = tipousuario;
    }
    public int getEspecialidad(){
        return especialidad;
    }
    public void setEspecialidad(int Especialidad){
        this.especialidad  = Especialidad;
    }
    public void setNombespecialidad(String especialidad){
        this.nombreespecialidad = especialidad;
    }
    public String getNombespecialidad(){
        return this.nombreespecialidad;
    }

  public long getRif() {
    return rif;
  }

  public void setRif(long rif) {
    this.rif = rif;
  }

  public long getMpps() {
    return mpps;
  }

  public void setMpps(long mpps) {
    this.mpps = mpps;
  }
    @Override
  public ModeloConexion getConexion(){
    return conexion;
  }  
    // Metodo para registrar usuario... 
    @Override
    public boolean registrar(){

        boolean registro = false;
        String funcion = "INSERT INTO persona (cedula, nombre, apellido, sexo, fecha_nacimiento, id_parroquia,direccion, telefono)"
                + "values("+this.cedula+",'"+this.nombre+"','"+this.apellido+"','"+this.sexo+"','"+this.fechanacimiento+"',"
                + ""+this.id_parroquia+",'"+this.direccion+"',"+this.telefono+")";
        int sentencia = conexion.ejecutarActualizacion(funcion);
        
        if(sentencia != 0)
        {
          String query = "INSERT INTO usuario (nombreusuario, contrasena, idespecialidad, idtipousuario, cedula_persona, mpps, rif) "
                  + "values('"+this.nombreusuario+"','"+this.contrasena+"',"+this.especialidad+","+this.tipousuario+","+this.cedula+","+getMpps()+","+getRif()+")";
         int result = conexion.ejecutarActualizacion(query);
            if (result > 0) {
                registro = true;
            }
        }
        return registro;
    }
    @Override
    public boolean modificar(){
        boolean modificar = false;
        String function = "UPDATE persona SET nombre = '"+this.nombre+"',apellido = '"+this.apellido+"',sexo = '"+this.sexo+"',"
                + "fecha_nacimiento = '"+this.fechanacimiento+"',direccion = '"+this.direccion+"', id_parroquia = "+this.id_parroquia+","
                + "telefono = "+this.telefono+" where cedula = "+this.cedula+"";
        int sentencia  = conexion.ejecutarActualizacion(function);
        if (sentencia != 0) {
            String query = "UPDATE usuario SET nombreusuario = '"+this.nombreusuario+"', contrasena = '"+this.contrasena+"',"
                    + "idespecialidad = "+this.especialidad+", idtipousuario = "+this.tipousuario+" where cedula_persona = "+this.cedula+"";
            int result = conexion.ejecutarActualizacion(query);
            if (result != 0) {
                modificar = true;
            }
        }
        return modificar;
    }
    public boolean capturarusers(){
        boolean existe = false;
            String sentenciaSQL = "SELECT * FROM usuario where id= '"+id+"'";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
             try {
              
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    
                    this.setNombreUsuario(resultadoConsulta.getString("nombreusuario"));
                    this.setTipousuario(resultadoConsulta.getInt("idtipousuario"));
                    existe=true;
                }else{
                     System.out.println("Error");
                    existe = false;
                }
            } catch (SQLException ex) {
                
                Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            return existe;
    }
    public boolean validarusuario(){
        boolean verificado = false;
        String verificar = "SELECT verificarusuario('"+this.nombreusuario+"', '"+this.contrasena+"')";
        ResultSet verificacion = conexion.ejecutarConsulta(verificar);
         try {
                if(verificacion.next())
                {
                    if (verificacion.getBoolean("verificarusuario"))
                    {
                        verificado = true;
                    }else
                    {
                        verificado = false;
                    }
                }
            } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, ex.getMessage());
                
                Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
    return verificado;
}
    public int capturarid() throws SQLException{
        this.id = 0;
        String consultaid = "SELECT id FROM usuario where nombreusuario = '"+this.nombreusuario+"'";
        ResultSet consulta = conexion.ejecutarConsulta(consultaid);
        if (consulta.next()) 
        {
            int idusers  = consulta.getInt("id");
            if (idusers != 0) 
            {
                id = idusers;
            }
        }
        return id;
    }
    public String[][] ListarUsuario(){
            boolean statusConsulta = false;
            String sentenciaSQL = "select p.nombre,p.apellido,u.nombreusuario,tp.nombre as tipousuario FROM usuario u \n" +
                                    "join persona p ON u.cedula_persona = p.cedula \n" +
                                    "join tipousuario tp ON u.idtipousuario = tp.id";
            ResultSet resultadoConsulta = conexion.ejecutarConsulta(sentenciaSQL);
            if(resultadoConsulta == null)
            {
               return null;
            }
            int i = 0;
            try {
                while(resultadoConsulta.next()) i++;
                String[][] datos = new String[i][4];
                i = 0;
                resultadoConsulta.beforeFirst();
                while(resultadoConsulta.next()){
                   datos[i][0] = resultadoConsulta.getString("nombre");
                    datos[i][1] = resultadoConsulta.getString("apellido");
                     datos[i][2] = resultadoConsulta.getString("nombreusuario");
                      datos[i][3] = resultadoConsulta.getString("tipousuario");
                    i++;
                }
                return datos;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                    return null;
            }
}
    public String[][] Filtrarusuario(String busqueda){
        String sentenciaSQL = "SELECT p.nombre, p.apellido, u.nombreusuario, tp.nombre as tipousuario "
                + "FROM usuario u  JOIN persona p ON u.cedula_persona = p.cedula"
                + "JOIN tipousuario tp ON u.idtipousuario = tp.id" 
                + "WHERE p.nombre LIKE '%"+busqueda+"%' ||  p.apellido LIKE '%"+busqueda+"%'"
                + "||  u.nombreusuario LIKE '%"+busqueda+"%' || tp.tipousuario LIKE '%"+busqueda+"%'";
        ResultSet resultadoConsulta =conexion.ejecutarConsulta(sentenciaSQL);      
        if(resultadoConsulta==null) return null;
        try{
            int i=0;
            while(resultadoConsulta.next())
                i++;
            String[][] Data = new String[i][4];
            resultadoConsulta.beforeFirst();
            i=0;
            while(resultadoConsulta.next()){ 
                Data[i][0]=resultadoConsulta.getString("nombre");  
                Data[i][1]=resultadoConsulta.getString("apellido");  
                Data[i][2]=resultadoConsulta.getString("nombreusuario");  
                Data[i][3]=resultadoConsulta.getString("tipousuario");  
            }
            return Data;
        } catch (SQLException ex) {
            return null;
        }
    }
    public boolean capturarusuario(int id){
        boolean captura = false;
        String consultar = "SELECT us.nombreusuario as usuario, tp.nombre as tipousuario from usuario as us join "
                + "tipousuario as tp on(us.idtipousuario = tp.id) where us.id = '"+id+"'";
        ResultSet consulta = conexion.ejecutarConsulta(consultar);
        try {
            if (consulta!=null)
            {
                consulta.next();
                this.nombreusuario = consulta.getString("usuario");
                this.setNombtipousuario(consulta.getString("tipousuario"));
                captura = true;
            }else
            {
                captura = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return captura;
    }
    public boolean ConsultarUsuario(){
        boolean statusConsulta=false;    
        String sentenciaSQL = "SELECT p.cedula,p.nombre,p.apellido, p.sexo, p.fecha_nacimiento, p.direccion, p.id_tipopersona, p.id_parroquia, p.telefono,\n" +
"	parr.nombre as nombreparroquia, u.nombreusuario as usuario, u.contrasena, u.id, u.idespecialidad,\n" +
"	u.idtipousuario, esp.nombre as nombre_especialidad, tpus.nombre as tipousuario, \n" +
"	u.rif, u.mpps from persona as p \n" +
"	join usuario as u on(p.cedula = u.cedula_persona)\n" +
"	join parroquia as parr ON(p.id_parroquia = parr.id)\n" +
"	join tipousuario as tpus ON(u.idtipousuario = tpus.id)\n" +
"	join especialidad as esp ON(u.idespecialidad = esp.id) WHERE p.nombre = '"+this.nombre+"';";
            ResultSet resultadoConsulta =conexion.ejecutarConsulta(sentenciaSQL);
             try {
                if(resultadoConsulta!=null){
                    resultadoConsulta.next();
                    //Persona
                    this.setCedula(resultadoConsulta.getLong("cedula"));
                    this.setNombre(resultadoConsulta.getString("nombre"));
                    this.setApellido(resultadoConsulta.getString("apellido"));
                    this.setSexo(resultadoConsulta.getString("sexo"));
                    this.setFechanacimiento(resultadoConsulta.getDate("fecha_nacimiento"));
                    this.setDireccion(resultadoConsulta.getString("direccion"));
                    this.setTelefono(resultadoConsulta.getLong("telefono"));
                    this.setIdtipopersona(resultadoConsulta.getInt("id_tipopersona"));
                    this.setIdparroquia(resultadoConsulta.getInt("id_parroquia"));
//                  Usuario                    
                    this.setNombreUsuario(resultadoConsulta.getString("usuario"));
                    this.setContrasena(resultadoConsulta.getString("contrasena"));
                    this.setEspecialidad(resultadoConsulta.getInt("idespecialidad"));
                    this.setTipousuario(resultadoConsulta.getInt("idtipousuario"));
                    this.setRif(resultadoConsulta.getLong("rif"));
                    this.setMpps(resultadoConsulta.getLong("mpps"));
                    statusConsulta=true;
                }else{
                    statusConsulta=false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModeloTipopaciente.class.getName()).log(Level.SEVERE, null, ex);
            }
            return statusConsulta;
    }
    public boolean verificarusuario(){
        boolean existe = false;
        String verificar = "select verificarexistencia("+this.getCedula()+")";
        ResultSet verificacion = conexion.ejecutarConsulta(verificar);
         try {
                if(verificacion.next())
                {
                    if (verificacion.getBoolean("verificarexistencia"))
                    {
                        existe = true;
                    }else
                    {
                        existe = false;
                    }
                }
            } catch (SQLException ex) {
                
                Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
    return existe;
    }
    public boolean eliminarusuario(){
        boolean eliminacion = false;
        String sentencia = "DELETE FROM usuario where cedula_persona = "+this.cedula+"";
        int resultado = conexion.ejecutarActualizacion(sentencia);       
        if (resultado != 0) {
            String query = "DELETE FROM persona where cedula = "+this.cedula+"";
            int result = conexion.ejecutarActualizacion(query);
            if (result != 0) {
                eliminacion = true;
            }
        }
        return eliminacion;
    }
    public boolean capturardatos() throws SQLException{
        boolean encontrado = false;
        String query = "SELECT p.nombre, p.apellido, p.cedula, esp.nombre as especialidad "
                + "FROM persona as p "
                + "join usuario as u ON(p.cedula = u.cedula_persona)"
                + "join especialidad as esp ON(u.idespecialidad = esp.id)"
                + "where u.id = "+this.getId_usuario()+"";
        ResultSet encontrar = conexion.ejecutarConsulta(query);
        if(encontrar != null){
            encontrado = true;
            encontrar.next();
            this.setCedula(encontrar.getLong("cedula"));
            this.setNombre(encontrar.getString("nombre"));
            this.setApellido(encontrar.getString("apellido"));
            this.setNombespecialidad(encontrar.getString("especialidad"));
        }
        return encontrado;
    }
    public String[] datos(){
      this.setId_usuario(Principal.getIdUsuario());
      String query = "select * from datosdoctor("+this.getId_usuario()+")";
      ResultSet consul = conexion.ejecutarConsulta(query);
      String datos[] = new String[4]; 
      if(consul != null){
        try {
          consul.next();
          datos[0] = consul.getString("nombrecompleto");
          datos[1] = Long.toString(consul.getLong("cedula"));
          datos[2] = Integer.toString(consul.getInt("mpps"));
          datos[3] = Long.toString(consul.getLong("rif"));
        } catch (SQLException ex) {
          Logger.getLogger(ModeloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return datos;
    }
//    CAMPOS
    private int id;
    private String contrasena;
    private String nombreusuario;
    private int tipousuario;
    private int especialidad;
    private String nombtipousuario;
    private String nombreespecialidad;
    private long rif;
    private long mpps;
//    public int capturaridtipopaciente
}