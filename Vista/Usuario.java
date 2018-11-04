/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Long.parseLong;
import java.util.*;

import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author Jnatn'h
 */
public class Usuario extends java.awt.Dialog {
    
    private ControladorUsuario controlador;
    private int id;
    private void setController(ControladorUsuario cont){
      controlador = cont;
      this.Oyente();
    }
    private ControladorUsuario getController(){
      return controlador;
    }
    private void Oyente(){
      getGrabar().addActionListener(getController());
      getEliminar().addActionListener(getController());
      getSalir().addActionListener(getController());
      getImprimir().addActionListener(getController());
      
      getEspecialidad().addActionListener(getController());
      getTipousuario().addActionListener(getController());
     
      getEstado().addActionListener(getController());
      getMunicipio().addActionListener(getController());
      getParroquia().addActionListener(getController());
      
      getMpps().addKeyListener(getController());
    }
      public int getIdusuario(){
        return id;
    }
    public void setIdusuario(int id){
        this.id = id;
    }
    
    public JFormattedTextField getCedula(){
    return cedula;
    }
    public void setCedula(JFormattedTextField cedula){
        this.cedula = cedula;
    }
    public JTextField getNombre(){
        return nombre;
    }
    public void setNombre(JTextField nombre){
        this.nombre = nombre;
    }
    public JTextField getApellido(){
        return apellido;
    }
    public void setApellido(JTextField apellido){
        this.apellido = apellido;
    }
    public ButtonGroup getSexo(){
        return sexo;
    }
    public void setSexo(ButtonGroup sexo){
        this.sexo =  sexo;
    }
    public JDateChooser getFechanac(){
        return fechanac;
    }
    public void setFechanac(JDateChooser fechanac){
        this.fechanac = fechanac;
    }
    public JFormattedTextField getTelefono(){
        return telefono;
    }
    public void setTelefono(JFormattedTextField telefono){
        this.telefono = telefono;
    }
    public JComboBox getEstado(){
        return estado;
    }
    public void setEstado(JComboBox estado){
        this.estado = estado;
    }
    public JComboBox getMunicipio(){
        return municipio;
    }
    public void setMunicipio(JComboBox municipio){
        this.municipio = municipio;
    }
    public JComboBox getParroquia(){
        return parroquia;
    }
    public void setParroquia(JComboBox parroquia){
        this.parroquia = parroquia;
    }
    public JTextArea getDireccion(){
        return direccion;
    }
    public void setDireccion(JTextArea direccion){
        this.direccion = direccion;
    } 
//    Metodos de los atributos propios de usuario en la vista... 
    public JTextField getUsuario(){
        return usuario;
    }
    public void setUsuario(JTextField usuario){
        this.usuario = usuario;
    }
    public JPasswordField getContrasena(){
        return contrasena;
    } 
    public void setContrasena(JPasswordField contrasena){
        this.contrasena = contrasena;
    }
    public JComboBox getEspecialidad(){
        return especialidad;
    }
    public void setEspecialidad(JComboBox especialidad){
        this.especialidad = especialidad;
    }
    public JComboBox getTipousuario(){
        return tipousuario;
    }
    public void setTipousuario(JComboBox tipousuario){
        this.tipousuario = tipousuario;
    }
    //fin de los metodos de acceso a los atributos... 
    
    public JButton getImprimir(){
        return imprimir;
    }
    public void setImprimir(JButton imprimir){
        this.imprimir = imprimir;
    }
    public JButton getGrabar(){
        return this.grabar;
    }
    public void setGrabar(JButton grabar){
        this.grabar = grabar;
    }
    public JButton getEliminar(){
        return eliminar;
    }
    public void setEliminar(JButton Eliminar){
        this.eliminar = Eliminar;
    }
    public JButton getSalir(){
        return salir;
    }
    public void setSalir(JButton salir){
        this.salir = salir;
    }
    public JLabel getLeyenda(){
        return leyenda;
    }
    public void setLeyenda(JLabel leyenda){
        this.leyenda = leyenda;
    }
    public JRadioButton getM(){
        return this.M;
    }
    public JRadioButton getF(){
        return this.F;
    }
    public void setM(JRadioButton masculino){
        this.M = masculino;
    }
    public  void setF(JRadioButton femenino){
        this.F = femenino;
    }

  public JTextField getMpps() {
    return mpps;
  }

  public void setMpps(JTextField mpps) {
    this.mpps = mpps;
  }

  public JFormattedTextField getRif() {
    return rif;
  }

  public void setRif(JFormattedTextField rif) {
    this.rif = rif;
  }

  public JTabbedPane getUserstab() {
    return userstab;
  }

  public void setUserstab(JTabbedPane userstab) {
    this.userstab = userstab;
  }
    
    /**
     * Creates new form Usuario
     * @param parent
     * @param modal
     * @param ver
     */
    public Usuario(java.awt.Frame parent, boolean modal, boolean ver){
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Usuario");
        setController(new ControladorUsuario(this));
    }
     /**
     * Creates new form Usuario
     * @param parent
     * @param modal
     */
    public Usuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Usuario");
        setController(new ControladorUsuario(this));
        this.desabilitaroptionpegar(this.getCedula());
        this.desabilitaroptionpegar(this.getNombre());
        this.desabilitaroptionpegar(this.getApellido());
        this.desabilitaroptionpegar(this.getTelefono());
        
    }
    //Metodos implementados para capturar los datos numericos tanto de la cedula como del telefono...
    private long capturacedula(){
        char cedul  =   cedula.getText().charAt(2);
        char cedul1 =   cedula.getText().charAt(3);
        char cedul2 =   cedula.getText().charAt(5);
        char cedul3 =   cedula.getText().charAt(6);
        char cedul4 =   cedula.getText().charAt(7);
        char cedul5 =   cedula.getText().charAt(9);
        char cedul6 =   cedula.getText().charAt(10);
        char cedul7 =   cedula.getText().charAt(11);
        char data[] = {cedul,cedul1,cedul2,cedul3,cedul4,cedul5,cedul6,cedul7};
        String cedul8 = String.copyValueOf(data);
        long cedulaa = Long.parseLong(cedul8);
        return cedulaa;
    }
    private long capturarrif(){
        char cedul  =   rif.getText().charAt(2);
        char cedul1 =   rif.getText().charAt(3);
        char cedul2 =   rif.getText().charAt(5);
        char cedul3 =   rif.getText().charAt(6);
        char cedul4 =   rif.getText().charAt(7);
        char cedul5 =   rif.getText().charAt(9);
        char cedul6 =   rif.getText().charAt(10);
        char cedul7 =   rif.getText().charAt(11);
        char cedul8 =   rif.getText().charAt(13);
        char data[] = {cedul,cedul1,cedul2,cedul3,cedul4,cedul5,cedul6,cedul7,cedul8};
        String cedul9 = String.copyValueOf(data);
        long cedulaa = Long.parseLong(cedul9);
        return cedulaa;
    }
    private long capturatelefono(){
        char cedul  =   telefono.getText().charAt(0);
        char cedul1 =   telefono.getText().charAt(1);
        char cedul2 =   telefono.getText().charAt(2);
        char cedul3 =   telefono.getText().charAt(3);
        char cedul4 =   telefono.getText().charAt(5);
        char cedul5 =   telefono.getText().charAt(6);
        char cedul6 =   telefono.getText().charAt(7);
        char cedul7 =   telefono.getText().charAt(9);
        char cedul8 =   telefono.getText().charAt(10);
        char cedul9 =   telefono.getText().charAt(12);
        char cedul10 =   telefono.getText().charAt(13);
        char data[] = {cedul,cedul1,cedul2,cedul3,cedul4,cedul5,cedul6,cedul7,cedul8,cedul9,cedul10};
        String cedul11 = String.copyValueOf(data);
        long telefon = Long.parseLong(cedul11);
        return telefon;
    }
 public void desabilitaroptionpegar(JTextField text){
        InputMap map = text.getInputMap();
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
}
        
 
    private void validarcampo(KeyEvent e){
     char b = e.getKeyChar();
         if(!Character.isLetter(b)  &&  b != KeyEvent.VK_SPACE){
            e.consume();
         }

    }
    private void validarnumeros(KeyEvent evt)
    {
        char b = evt.getKeyChar();
        if(Character.isLetter(b)){          
                Toolkit.getDefaultToolkit().beep();
                //JOptionPane.showMessageDialog(null, "Solo se permiten números", "Mensaje de información", JOptionPane.PLAIN_MESSAGE);
                    evt.consume();             
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    sexo = new javax.swing.ButtonGroup();
    panel1 = new org.edisoncor.gui.panel.Panel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    nombre = new javax.swing.JTextField();
    asd = new javax.swing.JLabel();
    apellido = new javax.swing.JTextField();
    asd1 = new javax.swing.JLabel();
    M = new javax.swing.JRadioButton();
    F = new javax.swing.JRadioButton();
    asd2 = new javax.swing.JLabel();
    fechanac = new com.toedter.calendar.JDateChooser();
    asd3 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    estado = new javax.swing.JComboBox<>();
    jLabel4 = new javax.swing.JLabel();
    municipio = new javax.swing.JComboBox<>();
    jLabel5 = new javax.swing.JLabel();
    parroquia = new javax.swing.JComboBox<>();
    jLabel6 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    direccion = new javax.swing.JTextArea();
    userstab = new javax.swing.JTabbedPane();
    jPanel2 = new javax.swing.JPanel();
    asd4 = new javax.swing.JLabel();
    usuario = new javax.swing.JTextField();
    asd5 = new javax.swing.JLabel();
    contrasena = new javax.swing.JPasswordField();
    asd6 = new javax.swing.JLabel();
    especialidad = new javax.swing.JComboBox<>();
    asd7 = new javax.swing.JLabel();
    tipousuario = new javax.swing.JComboBox<>();
    doctor = new javax.swing.JPanel();
    asd8 = new javax.swing.JLabel();
    asd9 = new javax.swing.JLabel();
    rif = new javax.swing.JFormattedTextField();
    mpps = new javax.swing.JTextField();
    imprimir = new javax.swing.JButton();
    grabar = new javax.swing.JButton();
    eliminar = new javax.swing.JButton();
    salir = new javax.swing.JButton();
    leyenda = new javax.swing.JLabel();
    panel2 = new org.edisoncor.gui.panel.Panel();
    cedula = new javax.swing.JFormattedTextField();
    telefono = new javax.swing.JFormattedTextField();

    setMinimumSize(new java.awt.Dimension(737, 504));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });

    panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
    panel1.setMaximumSize(new java.awt.Dimension(737, 504));

    jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Cédula:");

    jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Nombre:");

    nombre.setBackground(new java.awt.Color(255, 255, 255));
    nombre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
    nombre.setForeground(new java.awt.Color(0, 0, 0));
    nombre.setMaximumSize(new java.awt.Dimension(14, 25));
    nombre.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        nombreFocusGained(evt);
      }
    });
    nombre.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        nombreKeyTyped(evt);
      }
    });

    asd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd.setForeground(new java.awt.Color(255, 255, 255));
    asd.setText("Apellido:");

    apellido.setBackground(new java.awt.Color(255, 255, 255));
    apellido.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
    apellido.setForeground(new java.awt.Color(0, 0, 0));
    apellido.setMaximumSize(new java.awt.Dimension(14, 25));
    apellido.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        apellidoFocusGained(evt);
      }
    });
    apellido.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        apellidoKeyTyped(evt);
      }
    });

    asd1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd1.setForeground(new java.awt.Color(255, 255, 255));
    asd1.setText("Sexo:");

    sexo.add(M);
    M.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    M.setForeground(new java.awt.Color(255, 255, 255));
    M.setText("M");
    M.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        MFocusGained(evt);
      }
    });

    sexo.add(F);
    F.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    F.setForeground(new java.awt.Color(255, 255, 255));
    F.setText("F");
    F.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        FFocusGained(evt);
      }
    });

    asd2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd2.setForeground(new java.awt.Color(255, 255, 255));
    asd2.setText("Fecha Nac:");

    fechanac.setBackground(new java.awt.Color(255, 255, 255));
    fechanac.setForeground(new java.awt.Color(0, 0, 0));
    fechanac.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    fechanac.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        fechanacFocusGained(evt);
      }
    });

    asd3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd3.setForeground(new java.awt.Color(255, 255, 255));
    asd3.setText("Teléfono:");

    jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("Estado:");

    estado.setBackground(new java.awt.Color(255, 255, 255));
    estado.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    estado.setForeground(new java.awt.Color(0, 0, 0));
    estado.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        estadoFocusGained(evt);
      }
    });

    jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setText("Municipio:");

    municipio.setBackground(new java.awt.Color(255, 255, 255));
    municipio.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    municipio.setForeground(new java.awt.Color(0, 0, 0));
    municipio.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        municipioFocusGained(evt);
      }
    });

    jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(255, 255, 255));
    jLabel5.setText("Parroquia:");

    parroquia.setBackground(new java.awt.Color(255, 255, 255));
    parroquia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    parroquia.setForeground(new java.awt.Color(0, 0, 0));
    parroquia.setMaximumSize(new java.awt.Dimension(32767, 223));
    parroquia.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        parroquiaFocusGained(evt);
      }
    });

    jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
    jLabel6.setText("Dirección:");

    direccion.setBackground(new java.awt.Color(255, 255, 255));
    direccion.setColumns(20);
    direccion.setForeground(new java.awt.Color(0, 0, 0));
    direccion.setRows(1);
    direccion.setWrapStyleWord(true);
    direccion.setMargin(new java.awt.Insets(0, 1, 0, 1));
    direccion.setMaximumSize(new java.awt.Dimension(220, 280));
    direccion.setName(""); // NOI18N
    direccion.setPreferredSize(new java.awt.Dimension(220, 80));
    direccion.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        direccionFocusGained(evt);
      }
    });
    jScrollPane1.setViewportView(direccion);

    userstab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

    asd4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd4.setForeground(new java.awt.Color(0, 0, 0));
    asd4.setText("Usuario:");

    usuario.setBackground(new java.awt.Color(255, 255, 255));
    usuario.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
    usuario.setForeground(new java.awt.Color(0, 0, 0));
    usuario.setMaximumSize(new java.awt.Dimension(14, 25));
    usuario.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        usuarioFocusGained(evt);
      }
    });

    asd5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd5.setForeground(new java.awt.Color(0, 0, 0));
    asd5.setText("Contraseña:");

    contrasena.setBackground(new java.awt.Color(255, 255, 255));
    contrasena.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
    contrasena.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        contrasenaFocusGained(evt);
      }
    });

    asd6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd6.setForeground(new java.awt.Color(0, 0, 0));
    asd6.setText("Especialidad:");

    especialidad.setBackground(new java.awt.Color(255, 255, 255));
    especialidad.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    especialidad.setForeground(new java.awt.Color(0, 0, 0));
    especialidad.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        especialidadFocusGained(evt);
      }
    });

    asd7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd7.setForeground(new java.awt.Color(0, 0, 0));
    asd7.setText("Tipo usuario:");

    tipousuario.setBackground(new java.awt.Color(255, 255, 255));
    tipousuario.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    tipousuario.setForeground(new java.awt.Color(0, 0, 0));
    tipousuario.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        tipousuarioFocusGained(evt);
      }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addComponent(asd4)
            .addGap(33, 33, 33))
          .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(asd5)
            .addGap(9, 9, 9)))
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(contrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
          .addComponent(usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(18, 18, 18)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(asd6)
          .addComponent(asd7))
        .addGap(18, 18, 18)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(tipousuario, 0, 241, Short.MAX_VALUE)
          .addComponent(especialidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(usuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(asd6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(especialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(asd4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(asd5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(contrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(asd7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(tipousuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(26, 26, 26))
    );

    userstab.addTab("Usuario", new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/user.png")), jPanel2); // NOI18N

    asd8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd8.setForeground(new java.awt.Color(0, 0, 0));
    asd8.setText("RIf:");

    asd9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    asd9.setForeground(new java.awt.Color(0, 0, 0));
    asd9.setText("M.P.P.S:");

    try {
      rif.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("V-##.###.###-#")));
    } catch (java.text.ParseException ex) {
      ex.printStackTrace();
    }
    rif.setEnabled(false);

    mpps.setToolTipText("");
    mpps.setEnabled(false);

    javax.swing.GroupLayout doctorLayout = new javax.swing.GroupLayout(doctor);
    doctor.setLayout(doctorLayout);
    doctorLayout.setHorizontalGroup(
      doctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(doctorLayout.createSequentialGroup()
        .addGap(17, 17, 17)
        .addGroup(doctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(doctorLayout.createSequentialGroup()
            .addComponent(asd9)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(mpps))
          .addGroup(doctorLayout.createSequentialGroup()
            .addComponent(asd8)
            .addGap(37, 37, 37)
            .addComponent(rif, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(384, Short.MAX_VALUE))
    );
    doctorLayout.setVerticalGroup(
      doctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(doctorLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(doctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(asd8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(rif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(doctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(asd9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(mpps, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(25, 25, 25))
    );

    asd9.getAccessibleContext().setAccessibleName("doctor");
    asd9.getAccessibleContext().setAccessibleDescription("");

    userstab.addTab("Doctor/a", doctor);

    imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/imprimir.png"))); // NOI18N
    imprimir.setText("Imprimir");
    imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    imprimir.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        imprimirFocusGained(evt);
      }
    });

    grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
    grabar.setText("Grabar");
    grabar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    grabar.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        grabarFocusGained(evt);
      }
    });

    eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/eliminar.png"))); // NOI18N
    eliminar.setText("Eliminar");
    eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    eliminar.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        eliminarFocusGained(evt);
      }
    });

    salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
    salir.setText("Salir");
    salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    leyenda.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    leyenda.setForeground(new java.awt.Color(255, 255, 255));

    panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-usuario.png"))); // NOI18N
    panel2.setMaximumSize(new java.awt.Dimension(737, 75));
    panel2.setMinimumSize(new java.awt.Dimension(737, 75));
    panel2.setPreferredSize(new java.awt.Dimension(737, 75));

    javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
    panel2.setLayout(panel2Layout);
    panel2Layout.setHorizontalGroup(
      panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    panel2Layout.setVerticalGroup(
      panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 75, Short.MAX_VALUE)
    );

    try {
      cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("V-##.###.###")));
    } catch (java.text.ParseException ex) {
      ex.printStackTrace();
    }
    cedula.setMaximumSize(new java.awt.Dimension(14, 24));
    cedula.setMinimumSize(new java.awt.Dimension(14, 24));
    cedula.setPreferredSize(new java.awt.Dimension(14, 24));

    try {
      telefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("0###-###-##-##")));
    } catch (java.text.ParseException ex) {
      ex.printStackTrace();
    }
    telefono.setPreferredSize(new java.awt.Dimension(98, 26));

    javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
    panel1.setLayout(panel1Layout);
    panel1Layout.setHorizontalGroup(
      panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(panel1Layout.createSequentialGroup()
        .addGap(35, 35, 35)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(panel1Layout.createSequentialGroup()
            .addComponent(imprimir)
            .addGap(110, 110, 110)
            .addComponent(grabar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(eliminar)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(userstab)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel2)
                  .addComponent(jLabel1))
                .addGap(24, 24, 24)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(cedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
              .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(asd)
                  .addComponent(asd1))
                .addGap(22, 22, 22)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(panel1Layout.createSequentialGroup()
                    .addGap(67, 67, 67)
                    .addComponent(F)
                    .addGap(0, 0, Short.MAX_VALUE))
                  .addComponent(apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
              .addGroup(panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(asd2)
                  .addComponent(asd3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(panel1Layout.createSequentialGroup()
                    .addComponent(M, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                  .addComponent(fechanac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(telefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel6)
                  .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(parroquia, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel3)
                  .addComponent(jLabel4))
                .addGap(25, 25, 25)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(municipio, 0, 223, Short.MAX_VALUE)
                  .addComponent(estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        .addContainerGap(21, Short.MAX_VALUE))
      .addGroup(panel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    panel1Layout.setVerticalGroup(
      panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(municipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(asd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(parroquia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
          .addGroup(panel1Layout.createSequentialGroup()
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(asd1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(M)
              .addComponent(F)
              .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(7, 7, 7)
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(asd2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(fechanac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(asd3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(userstab, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(grabar)
          .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    //Date fechaActual = new Date();
    //SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
    //String fechaSistema=formateador.format(fechaActual);
    //fechanac.setMaxSelectableDate(fechaActual);

    add(panel1, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        // TODO add your handling code here:
        this.validarcampo(evt);
        if (this.getNombre().getText().length() >= 30)
        {
            evt.consume();
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyTyped
        // TODO add your handling code here:
        this.validarcampo(evt);
        if (this.getApellido().getText().length() >= 30)
        {
            evt.consume();
        }
    }//GEN-LAST:event_apellidoKeyTyped

    private void nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreFocusGained
        String leyend = "Ingrese el nombre del usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_nombreFocusGained

    private void apellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apellidoFocusGained
         String leyend = "Ingrese el apellido del usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_apellidoFocusGained

    private void MFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MFocusGained
        String leyend = "Masculino";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_MFocusGained

    private void FFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FFocusGained
        String leyend = "Femenino";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_FFocusGained

    private void fechanacFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fechanacFocusGained
        String leyend = "Fecha de nacimiento";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_fechanacFocusGained

    private void estadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_estadoFocusGained
        String leyend = "Seleccione el estado del usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_estadoFocusGained

    private void municipioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_municipioFocusGained
        String leyend = "Seleccione el municipio del usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_municipioFocusGained

    private void parroquiaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_parroquiaFocusGained
        String leyend = "Seleccione la parroquia del usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_parroquiaFocusGained

    private void direccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_direccionFocusGained
         String leyend = "Ingrese la dirección residencial.";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_direccionFocusGained

    private void usuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuarioFocusGained
        String leyend = "Ingrese nombre de usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_usuarioFocusGained

    private void especialidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_especialidadFocusGained
        String leyend = "Seleccione la especialidad del usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_especialidadFocusGained

    private void contrasenaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contrasenaFocusGained
        String leyend = "Contraseña, recuerde registrar una contrasena segura y recordable";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_contrasenaFocusGained

    private void tipousuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipousuarioFocusGained
        String leyend = "Seleccione el tipo de usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_tipousuarioFocusGained

    private void imprimirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_imprimirFocusGained
        String leyend = "Imprimir los datos de este usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_imprimirFocusGained

    private void grabarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grabarFocusGained
       String leyend = "Guarde los datos ingresado";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_grabarFocusGained

    private void eliminarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eliminarFocusGained
         String leyend = "Eliminación total del usuario";
        getLeyenda().setText(leyend);
    }//GEN-LAST:event_eliminarFocusGained

    
    
    
    
    
    
/*******************************************************************************/
// CLASE INTERNA CONTROLADORUSUARIO     
private  class ControladorUsuario implements ActionListener,KeyListener, MouseListener{
    private int id;
    private String nombre;
    private String nombmunicipio;
    private DefaultComboBoxModel listaestado,listaespecialidad,listatipousuario; 
    Usuario usuario;
    ModeloUsuario modelo;
    Principal principal;
    ModeloParroquia parroquia;
    ModeloEstado estado;
    ModeloEspecialidad especialidad;
    ModeloTipousuario tipousuario;
    CatUsuario cat;
    // Controlador que es llamado desde la usuario usuario...
    public ControladorUsuario(Usuario users){
        this.usuario = users;
        listaestado = new DefaultComboBoxModel();
        listaespecialidad = new DefaultComboBoxModel();
        listatipousuario = new DefaultComboBoxModel();
        Listartipousuario(listatipousuario);
        Listarespecialidad(listaespecialidad);
       Listarestado(listaestado);
    }
    // controlador que es llamado desde el catalogo de usuarios....
    
    public int getIdUsuario()
    {
        return id;
    }
    public void setIdusuario(int idusuario)
    {
        this.id = idusuario;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
    try {
        if (e.getSource().equals(usuario.getSalir())) {
                usuario.dispose();
                cat = new CatUsuario(principal, true);
                cat.setVisible(true);
            }
        if (e.getSource().equals(usuario.getEstado())){	
            DefaultComboBoxModel model  = new DefaultComboBoxModel();
            ListarMunicipio(model);
            usuario.getMunicipio().setEnabled(true);
            usuario.getMunicipio().setModel(model);    
        }else if (e.getSource().equals(usuario.getMunicipio())){	
            DefaultComboBoxModel model  = new DefaultComboBoxModel();
            ListarParroquia(model);
            usuario.getParroquia().setEnabled(true);
            usuario.getParroquia().setModel(model);
        }     
        if (e.getSource().equals(usuario.getImprimir())){
            // verificar que los campos no esten vacios antes de mostrarlo en hoja pdf para imprimir...   
        }
        if (e.getSource().equals(usuario.getGrabar())){
            // Verificar que los campos no esten vacios antes de mandarlo a grabar o modificar....
              long cedul = usuario.capturacedula();
              String ced = String.valueOf(cedul);
                if(ced.length() < 7 ){
                        String leyenda = "Es necesario ingresar un número de cédula.";
                        usuario.getLeyenda().setText(leyenda);
                }else if (usuario.getNombre().getText().isEmpty()) {
                    this.espacioblancoNombre();
    //                vista.getTextpatologia().requestFocus(); 
                 }else if(usuario.getNombre().getText().contains("  ")){
                  this.parespaciosNombre();
                } else if(usuario.getNombre().getText().length() < 4){
                    this.menorletrasNombre(); 
                }
                
                if (usuario.getApellido().getText().isEmpty()) {
                this.espacioblancoApellido();
//                vista.getTextpatologia().requestFocus(); 
                 }
               else if(usuario.getApellido().getText().contains("  "))
                {
                  this.parespaciosApellido();
                }
                else
            
                if(usuario.getApellido().getText().length() < 4){
                    this.menorletrasApellido(); 
                }
                if(usuario.getDireccion().getText().contains("  "))
                    this.parespaciosDireccion();
                
                if (usuario.getUsuario().getText().isEmpty()){
                    this.espacioblancoUsuario();
                }
                else if (usuario.getUsuario().getText().contains("  "))
                {
                    this.parespaciosUsuario();
                }
                else if (usuario.getUsuario().getText().length() <4)
                {
                    this.menorletrasUsuario();
                }
                
                //FIN DE VALIDAR CAMPOS EN BLANCO
                   else{
                        modelo = new ModeloUsuario();
                        long cedula = usuario.capturacedula();
                        modelo.setCedula(cedula);
                        boolean existe = modelo.verificarusuario();
                        if (existe) {
                           boolean validacion = validarusuario();
                           
                            if (validacion) {
                                usuario.getContrasena().setText(JOptionPane.showInputDialog(principal, "Por favor ingrese la contraseña del usuario que va a modificar!"));
                                modificarusuario();
                            }
                        }else{
                            boolean validacion = validarusuario();
                            if (validacion) {
                                registrarusuario();
                            }
                        }
                    }                  
        }else if(e.getSource().equals(usuario.getEliminar())){
            
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, " Desea Eliminar al Usuario?", "Usuario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {
                eliminarusuario();
            }
        }else if(e.getSource().equals(usuario.getTipousuario())){
          if(usuario.getTipousuario().getSelectedItem().toString().contains("DOC")){
            usuario.getRif().setEnabled(true);
            usuario.getMpps().setEnabled(true);
            usuario.getUserstab().setSelectedIndex(1);
          }
        }  
        } catch (Exception e2) {   
            System.out.println("ERROR EN CONTROLADOR USUARIO:"+ e2.getMessage());
        }
    }
    public void modificarusuario(){
        long cedula = Long.parseLong(usuario.getCedula().getText());
        String nombrep = usuario.getNombre().getText();
        String apellido = usuario.getApellido().getText();
        String sexo = capturarsexo();
        Date fechanacimiento =  usuario.getFechanac().getDate();
        long telefono = Long.parseLong(usuario.getTelefono().getText());
        
        String nombparroquia = usuario.getParroquia().getSelectedItem().toString();
        parroquia = new ModeloParroquia();
        int idparroquia = parroquia.capturarid(nombparroquia);


        String direccion  = usuario.getDireccion().getText();
        String nombusuario = usuario.getUsuario().getText();
        String contrasena = usuario.getContrasena().getText();
        contrasena  = DigestUtils.sha1Hex(contrasena);
        String nombespecialidad = usuario.getEspecialidad().getSelectedItem().toString();
        especialidad = new ModeloEspecialidad();
        int idespecialidad = especialidad.capturarid(nombespecialidad);
        String nombtipousuario = usuario.getTipousuario().getSelectedItem().toString();
          if(nombtipousuario.contains("DOC")){
          modelo.setRif(usuario.capturarrif());
          modelo.setMpps(parseLong(usuario.getMpps().toString()));
        }
        tipousuario = new ModeloTipousuario();
        int idtipousuario = tipousuario.capturarid(nombtipousuario);
        
        modelo = new ModeloUsuario(cedula,nombrep,apellido,sexo,fechanacimiento,telefono,
                idparroquia,direccion,nombusuario,contrasena,idespecialidad,idtipousuario);
        boolean modificacion = modelo.modificar();
        if (modificacion) {
            String leyenda = "Excelente Ya has modificado al usuario "+ nombusuario;
            usuario.getLeyenda().setText(leyenda);
        }
    }
    public void registrarusuario(){
        long cedula = usuario.capturacedula();
        String nombrep = usuario.getNombre().getText();
        String apellido = usuario.getApellido().getText();
        String sexo = capturarsexo();
        Date fechanacimiento = usuario.getFechanac().getDate();
        long telefono = usuario.capturatelefono();
        
        String nombparroquia = usuario.getParroquia().getSelectedItem().toString();
        parroquia = new ModeloParroquia();
        int idparroquia = parroquia.capturarid(nombparroquia);
        String direccion  = usuario.getDireccion().getText();
        String nombusuario = usuario.getUsuario().getText();
        String contrasena = usuario.getContrasena().getText();
        contrasena  = DigestUtils.sha1Hex(contrasena);
        String nombespecialidad = usuario.getEspecialidad().getSelectedItem().toString();
        especialidad = new ModeloEspecialidad();
        int idespecialidad = especialidad.capturarid(nombespecialidad);
        String nombtipousuario = usuario.getTipousuario().getSelectedItem().toString();
        tipousuario = new ModeloTipousuario();
        int idtipousuario = tipousuario.capturarid(nombtipousuario);
        
        
        modelo = new ModeloUsuario(cedula,nombrep,apellido,sexo,fechanacimiento,telefono,
                idparroquia,direccion,nombusuario,contrasena,idespecialidad,idtipousuario);
        boolean registrar= false;
         if(nombtipousuario.contains("DOC")){
           long mpps = parseLong(usuario.getMpps().getText());
          modelo.setRif(usuario.capturarrif());
          modelo.setMpps(mpps);
          registrar = modelo.registrar();
        }else{
           registrar = modelo.registrar();
         }
          if (registrar) {
            String leyenda = "Se ha registrado exitosamente... "+ nombusuario;
            usuario.getLeyenda().setText(leyenda);
            this.limpiar();
        }
        
    }
    public void eliminarusuario(){
        long cedula = Long.parseLong(usuario.getCedula().getText());
        modelo = new ModeloUsuario(cedula);
        boolean eliminacion = modelo.eliminarusuario();
        if (eliminacion) {
            String leyenda = "Se ha eliminado exitosamente al usuario";
            usuario.getLeyenda().setText(leyenda);
            this.limpiar();
        }
    } 
    public boolean validarusuario(){
         boolean validado = false;
         Date fech = new Date();
        long  tele = usuario.capturatelefono();
        String tel = Long.toString(tele);
         Date fechanac = usuario.getFechanac().getDate();
          try{
              String sexo = this.capturarsexo();
            if(usuario.getNombre().getText().length()< 3 || usuario.getNombre().getText().trim().isEmpty()){
                String leyenda = "Es necesario ingresar el nombre";
                usuario.getLeyenda().setText(leyenda);
            }else if(usuario.getApellido().getText().length() < 3 || usuario.getApellido().getText().trim().isEmpty()){
                String leyenda = "Es necesario ingresar el apellido";
                usuario.getLeyenda().setText(leyenda);
            }else if(sexo == null){
                String leyenda = "Es necesario seleccionar un sexo";
                usuario.getLeyenda().setText(leyenda);
            }else if(fechanac.after(fech)){
                String leyenda = "Es necesario Ingresar la fecha de nacimiento y que la misma sea menor a la actual...";
                usuario.getLeyenda().setText(leyenda);
            }else if(tel.length() < 10 ){
                String leyenda = "Ingrese un número de teléfono...";
                usuario.getLeyenda().setText(leyenda);
            }else if(usuario.getParroquia().getSelectedItem().toString() == null){
                String leyenda = "Es necesario seleccionar la parroquia";
                usuario.getLeyenda().setText(leyenda);
            }else if(usuario.getDireccion().getText().length() < 10 || usuario.getDireccion().getText().isEmpty()){
                String leyenda = "Es necesario ingresar la dirección";
                usuario.getLeyenda().setText(leyenda);
            }else if(usuario.getUsuario().getText().length() < 4 || usuario.getUsuario().getText().trim().isEmpty()){
                String leyenda = "Es necesario ingresar un usuario";
                usuario.getLeyenda().setText(leyenda);
            }else if(usuario.getContrasena().getPassword().length < 5 || usuario.getContrasena().getPassword().toString().isEmpty()){
                String leyenda = "Es necesario ingresar la contraseña";
                usuario.getLeyenda().setText(leyenda);
            }else if(usuario.getEspecialidad().getSelectedItem().toString().equalsIgnoreCase("Seleccione la especialidad") || usuario.getEspecialidad().getSelectedItem().toString().trim().isEmpty()){
                String leyenda = "Es necesario ingresar una especialidad, sea generica si no poseeo o la correspondiente...";
                usuario.getLeyenda().setText(leyenda);
            }else if(usuario.getTipousuario().getSelectedItem().toString().equalsIgnoreCase("Seleccione el Tipo de usuario") || usuario.getTipousuario().getSelectedItem().toString().isEmpty()){
                String leyenda = "Es necesario ingresar el tipo de usuario...";
                usuario.getLeyenda().setText(leyenda);
            }else{
            validado = true;
            }
        } catch (Exception e3) {
           
            JOptionPane.showMessageDialog(principal, "Hey! hacen faltan campos importantes evita estos cuadros de diálogos");
            System.out.println("ERROR: " + e3.getMessage());
        }
        
    return validado;
    }
    public void Listarestado(DefaultComboBoxModel listar){
        boolean resultado = false;
        ModeloEstado modeloEstado = new ModeloEstado();
        resultado= modeloEstado.cargarDatosEstado(listar);
        usuario.getEstado().setModel(listar);
        usuario.getMunicipio().setEnabled(false);
        usuario.getParroquia().setEnabled(false);
    }
    public void ListarMunicipio(DefaultComboBoxModel modeloCombo){
        boolean resultado= false;
        ModeloMunicipio modeloMunicipio = new ModeloMunicipio();
        String nombreEstadoSeleccionado = usuario.getEstado().getSelectedItem().toString();
        if (this.nombmunicipio == null) {
             resultado= modeloMunicipio.cargarDatosMunicipio(modeloCombo,nombreEstadoSeleccionado);
        }else{
            System.out.println(nombmunicipio);
             resultado= modeloMunicipio.cargarDatosMunicipio(modeloCombo,nombreEstadoSeleccionado, this.nombmunicipio);
        } 
        usuario.getMunicipio().setEnabled(true);
        usuario.getParroquia().setEnabled(false);
    }
    public void ListarParroquia(DefaultComboBoxModel modeloCombo){
        boolean resultado= false;
        usuario.getParroquia().setEnabled(true);
        ModeloParroquia modeloParroquia = new ModeloParroquia();
        String nombreMunicipioSeleccionado= usuario.getMunicipio().getSelectedItem().toString();
        resultado= modeloParroquia.cargarDatosParroquia(modeloCombo,nombreMunicipioSeleccionado);
    }    
    public void Listarespecialidad(DefaultComboBoxModel listar){
        boolean resultado = false;
        ModeloEspecialidad mesp = new ModeloEspecialidad();
        resultado = mesp.cargarDatosEspecialidad(listar);
        usuario.getEspecialidad().setModel(listar);
    }
    public void Listartipousuario(DefaultComboBoxModel listar){
        boolean resultado = false;
        ModeloTipousuario tipousuari = new ModeloTipousuario();
        resultado = tipousuari.cargarNombretipousuario(listar);
        usuario.getTipousuario().setModel(listar);
    }   
    public String capturarsexo(){
         String sexo = null;  
          for (Enumeration<AbstractButton > buttons = usuario.getSexo().getElements(); buttons.hasMoreElements();)
            {
                   AbstractButton button = buttons.nextElement();
                  if(button.isSelected())
                    {
                       sexo = button.getText();
                   }
             } 
          return sexo;
     }
    public void limpiar(){
        usuario.getCedula().setEnabled(true);
        usuario.getCedula().setText("");
        usuario.getNombre().setText("");
        usuario.getApellido().setText("");
        usuario.getSexo().clearSelection();
        usuario.getFechanac().getCalendar().clear();
        usuario.getTelefono().setText("");
        usuario.getEstado().insertItemAt("Seleccione el estado", 0);
        usuario.getEstado().setSelectedItem("Seleccione el estado");
        usuario.getDireccion().setText("");
        usuario.getUsuario().setText("");
        usuario.getContrasena().setText("");
        usuario.getEspecialidad().setSelectedItem("Seleccione la especialidad");
        usuario.getTipousuario().setSelectedItem("Seleccione el Tipo de usuario"); 
        usuario.getRif().setText("");
        usuario.getMpps().setText("");
        usuario.getUserstab().setSelectedIndex(0);
        usuario.getRif().setEnabled(false);
        usuario.getMpps().setEnabled(false);
    }
    
    public void parespaciosNombre()
 {
     String leyenda = "¡No puede registrar varios espacios en blancos en Nombre!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }
 
 public void menorletrasNombre()
 {
     String leyenda = "Asegúrese de que los campo Nombre no sean menor a 4 letras!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }
 public void espacioblancoNombre()
 {
     String leyenda = "¡No puede registrar espacios en blancos en Nombre!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }  
 
  public void parespaciosApellido()
 {
     String leyenda = "¡No puede registrar varios espacios en blancos en Apellido!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }
 
 public void menorletrasApellido()
 {
     String leyenda = "Asegúrese de que los campo apellido no sean menor a 2 letras!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }
 public void espacioblancoApellido()
 {
     String leyenda = "¡No puede registrar espacios en blancos en Nombre!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }  
    
 public void parespaciosDireccion()
 {
     String leyenda = "¡No puede registrar varios espacios en blancos en Dirección!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }
  public void parespaciosUsuario()
 {
     String leyenda = "¡No puede registrar varios espacios en blancos en Usuario!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }
 
 public void menorletrasUsuario()
 {
     String leyenda = "Asegúrese de que los campo Usuario no sean menor a 4 letras!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }
 public void espacioblancoUsuario()
 {
     String leyenda = "¡No puede registrar espacios en blancos en Usuario!";
     usuario.getLeyenda().setText(leyenda);
     usuario.getLeyenda().setForeground(Color.orange);
 }
    @Override
    public void keyTyped(KeyEvent e) {
      Component origen =  e.getComponent();
      if(origen.equals(usuario.getMpps())){
         char b = e.getKeyChar();
         if(Character.isLetter(b) &&  b != KeyEvent.VK_SPACE){
            e.consume();
         }
      }
     
    }
    @Override
    public void keyPressed(KeyEvent e) {
       Component origen =  e.getComponent();
      if(origen.equals(usuario.getMpps())){
         if(usuario.getMpps().toString().length()< 10){
            e.consume();
         }
      }
    }
    @Override
    public void keyReleased(KeyEvent e) {
       
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {     
    }
    @Override
    public void mouseReleased(MouseEvent e) {       
    }
    @Override
    public void mouseEntered(MouseEvent e) {      
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JRadioButton F;
  private javax.swing.JRadioButton M;
  private javax.swing.JTextField apellido;
  private javax.swing.JLabel asd;
  private javax.swing.JLabel asd1;
  private javax.swing.JLabel asd2;
  private javax.swing.JLabel asd3;
  private javax.swing.JLabel asd4;
  private javax.swing.JLabel asd5;
  private javax.swing.JLabel asd6;
  private javax.swing.JLabel asd7;
  private javax.swing.JLabel asd8;
  private javax.swing.JLabel asd9;
  private javax.swing.JFormattedTextField cedula;
  private javax.swing.JPasswordField contrasena;
  private javax.swing.JTextArea direccion;
  private javax.swing.JPanel doctor;
  private javax.swing.JButton eliminar;
  private javax.swing.JComboBox<String> especialidad;
  private javax.swing.JComboBox<String> estado;
  private com.toedter.calendar.JDateChooser fechanac;
  private javax.swing.JButton grabar;
  private javax.swing.JButton imprimir;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel leyenda;
  private javax.swing.JTextField mpps;
  private javax.swing.JComboBox<String> municipio;
  private javax.swing.JTextField nombre;
  private org.edisoncor.gui.panel.Panel panel1;
  private org.edisoncor.gui.panel.Panel panel2;
  private javax.swing.JComboBox<String> parroquia;
  private javax.swing.JFormattedTextField rif;
  private javax.swing.JButton salir;
  private javax.swing.ButtonGroup sexo;
  private javax.swing.JFormattedTextField telefono;
  private javax.swing.JComboBox<String> tipousuario;
  private javax.swing.JTabbedPane userstab;
  private javax.swing.JTextField usuario;
  // End of variables declaration//GEN-END:variables

}
