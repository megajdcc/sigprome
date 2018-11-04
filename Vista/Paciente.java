/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.ControladorPaciente;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Frame;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
/**
 *
 * @author Jnatn'h
 */
public class Paciente extends JDialog {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ControladorPaciente controlador;
   
    
    public void setController(ControladorPaciente controlador){
        this.controlador = controlador;
        DefaultComboBoxModel<String> listaestado = new DefaultComboBoxModel<String>();
        DefaultComboBoxModel<String> listipopaciente = new DefaultComboBoxModel<String>();
        DefaultComboBoxModel<String> listatpoempleado = new DefaultComboBoxModel<String>();
        controlador.Listarestado(listaestado);
        est.setModel(listaestado);
        controlador.listartipopaciente(listipopaciente);
        tipopaciente.setModel(listipopaciente);
        controlador.Listartipoempleado(listatpoempleado);
        this.tipoempleado.setModel(listatpoempleado);
        asignarListener();
    }

    private void asignarListener(){
        tipopaciente.addActionListener(controlador);
        bafiliado.addActionListener(controlador);
        registrar.addActionListener(controlador);
        modificar.addActionListener(controlador);
        salir.addActionListener(controlador);
        imprimir.addActionListener(controlador);
        //parentesco.addActionListener(controlador);
        est.addActionListener(controlador);
        mun.addActionListener(controlador);
        parr.addActionListener(controlador);
        cedulaafiliado.addKeyListener(controlador);

        //tipoempleado.addActionListener(controlador);
    }
    public ControladorPaciente getControlador(){
        return controlador;
    }
    public long getCedula(){
        Long cedul = this.capturacedula();
        return cedul;
    }
    public JFormattedTextField getCedul(){
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
        this.sexo = sexo;
    }
    public JDateChooser getFechanacimiento(){
        return fechanacimiento;
    }
    public JFormattedTextField getTelefon(){
        return telefono;
    }
    public void setTelefono(JFormattedTextField telefono){
        this.telefono = telefono;
    }
    public Long getTelefono(){
        Long telefon = this.capturatelefono();
        return telefon;
    }
    public JComboBox<String> getEstado(){
        return est;
    }
    public void setEstado(JComboBox<String> estado){
        this.est = estado;
    }
    public JComboBox<String> getMunicipio(){
        return mun;
    }
    public void setMunicipio(JComboBox<String> municipio){
        this.mun = municipio;
    }
    public JComboBox<String> getParroquia(){
        return parr;
    }
    public void setParroquia(JComboBox<String> parroquia){
        this.parr = parroquia;
    }
    public JTextArea getDireccion(){
        return direcc;
    }
    public void setDireccion(JTextArea direccion){
        this.direcc = direccion;
    }
    public long getCedulaafiliado(){
        Long cedul1 = this.capturacedulaafiliado();
        return cedul1;
    }
    public JFormattedTextField getCedulaafiliado1(){
        return this.cedulaafiliado;
    }
    public void setCedulaafiliado(JFormattedTextField cedulaafiliado){
        this.cedulaafiliado = cedulaafiliado;
    }
    public JTextField getNombAfiliado(){
        return nombafiliado;
    }
    public void setNombAfiliado(JTextField nombafiliado){
        this.nombafiliado = nombafiliado;
    }
    public JTextField getApeAfiliado(){
        return apeafiliado;
    }
    public void setApeAfiliado(JTextField apeafiliado){
        this.apeafiliado = apeafiliado;
    }
    public JTextField getTpoempaf(){
        return tpoempaf;
    }
    public void setTpoempaf(JTextField tpoempaf){
        this.tpoempaf = tpoempaf;
    }
    public JTextField getCondicionfamiliar(){
        return condicionfamiliar;
    }
    public void setCondicionfamiliar(JTextField condicionfamiliar){
        this.condicionfamiliar = condicionfamiliar;
    }
    public JComboBox<String> getParentesco(){
        return parentesco;
    }
    public void setParentesco(JComboBox<String> parentesco){
        this.parentesco = parentesco;
    }
    public JTextField getCondicionempleado(){
        return condempleado;
    }
    public void setCondicionempleado(JTextField condicionempleado){
        this.condempleado = condicionempleado;
    }
    public JComboBox<String> getTpoempleado(){
        return tipoempleado;
    }
    public void setTpoempleado(JComboBox<String> tipoempleado){
        this.tipoempleado = tipoempleado;
    }
    public JTextArea getCampoadicional(){
        return campoadicional;
    }
    public void setCampoadicional(JTextArea campoadicional){
        this.campoadicional = campoadicional;
    }
    public JButton getBtonafiliado(){
        return bafiliado;
    }
    public void setBtonafiliado(JButton botonafiliado){
        this.bafiliado = botonafiliado;
    }
    public void setBtonimprimir(JButton imp){
        this.imprimir = imp;
    }
    public JButton getBtonimprimir(){
        return this.imprimir;
    }
    public JButton getRegistrar(){
        return registrar;
    }
    public void setRegistrar(JButton registrar){
        this.registrar = registrar;
    }
    public JButton getModificar(){
        return modificar;
    }
    public void setModificar(JButton modificar){
        this.modificar = modificar;
    }
    public JButton getSalir(){
        return salir;
    }
    public void setSalir(JButton salir){
        this.salir = salir;
    }
    public JComboBox<String> getTipopaciente(){
        return tipopaciente;
    }
    public void setTipopaciente(JComboBox<String> tipopaciente){
        this.tipopaciente = tipopaciente;
    }
    public JRadioButton getM(){
        return m;
    }
    public JRadioButton getF(){
        return f;
    }
    public JTextField getExpediente(){
        return expediente;
    }
    public void setExpediente(JTextField expediente){
        this.expediente = expediente;
    }
    public JLabel getLeyenda(){
        return leyenda;
    }
    public void setLeyenda(JLabel leyenda){
        this.leyenda = leyenda;
    }
    public JTabbedPane getjTabbedPaciente(){
        return jTabbedPaciente;
    }
    public void setjTabbedPaciente(JTabbedPane tabbedpaciente){
        this.jTabbedPaciente = tabbedpaciente;
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
    private long capturacedulaafiliado(){
        char cedul  =   cedulaafiliado.getText().charAt(2);
        char cedul1 =   cedulaafiliado.getText().charAt(3);
        char cedul2 =   cedulaafiliado.getText().charAt(5);
        char cedul3 =   cedulaafiliado.getText().charAt(6);
        char cedul4 =   cedulaafiliado.getText().charAt(7);
        char cedul5 =   cedulaafiliado.getText().charAt(9);
        char cedul6 =   cedulaafiliado.getText().charAt(10);
        char cedul7 =   cedulaafiliado.getText().charAt(11);
        char data[] = {cedul,cedul1,cedul2,cedul3,cedul4,cedul5,cedul6,cedul7};
        String cedul8 = String.copyValueOf(data);
        long cedulaa = Long.parseLong(cedul8);
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
    
    /**
     * Creates new form Paciente
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Paciente(Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        centraVentana();
       
    }
    /**
     * Instancias de ver paciente
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     * @param ver, un boolean para indicar que se quiere ver el paciente desde otra instancia ...
     */
    public Paciente(Frame parent,boolean modal, boolean ver){
        super(parent, modal);
        setModal(false);
        initComponents();
        centraVentana();
        this.setResizable(false);
        this.setTitle("Paciente");
       
        getFechanacimiento().setMaxSelectableDate(new Date());
    }
    /**
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     * @param desdeservicio,un entero que se utiliza para indicar que se desea levantar instancia desde servicios  
     */
    public Paciente(Frame parent, boolean modal, int desdeservicio){
        super(parent, modal);
        initComponents();
        this.setResizable(true);
        centraVentana();
        
          getFechanacimiento().setMaxSelectableDate(new Date());
    }
    private void centraVentana() {
    //Obtiene el tamaño de la pantalla
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Obtiene el tamaño de la ventana de la aplicación
    Dimension frameSize = getSize();
    // Se asegura que el tamaño de la ventana de la aplicación
    // no exceda el tamaño de la pantalla
    if (frameSize.height > screenSize.height) {
    frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
    frameSize.width = screenSize.width;
    }
    // Centra la ventana de la aplicación sobre la pantalla
    setLocation((screenSize.width - frameSize.width) / 2,
    (screenSize.height - frameSize.height) / 2);
} 
    private void validarcampo(KeyEvent e){
     char b = e.getKeyChar();
         if(!Character.isLetter(b)  &&  b != KeyEvent.VK_SPACE){
            e.consume();
         }
    }
    private void validarnumeros(KeyEvent evt){
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
        cat = new org.edisoncor.gui.panel.Panel();
        salir = new javax.swing.JButton();
        panel7 = new org.edisoncor.gui.panel.Panel();
        jTabbedPaciente = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        bafiliado = new javax.swing.JButton();
        nombafiliado = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        apeafiliado = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        parentesco = new javax.swing.JComboBox<>();
        condicionfamiliar = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        tpoempaf = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        cedulaafiliado = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        condempleado = new javax.swing.JTextField();
        tipoempleado = new javax.swing.JComboBox<>();
        jLabel79 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        campoadicional = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        expediente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tipopaciente = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        m = new javax.swing.JRadioButton();
        f = new javax.swing.JRadioButton();
        jLabel66 = new javax.swing.JLabel();
        fechanacimiento = new com.toedter.calendar.JDateChooser();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        mun = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        parr = new javax.swing.JComboBox<>();
        jLabel70 = new javax.swing.JLabel();
        est = new javax.swing.JComboBox<>();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        direcc = new javax.swing.JTextArea();
        jLabel72 = new javax.swing.JLabel();
        modificar = new javax.swing.JButton();
        registrar = new javax.swing.JButton();
        imprimir = new javax.swing.JButton();
        leyenda = new javax.swing.JLabel();
        cedula = new javax.swing.JFormattedTextField();
        telefono = new javax.swing.JFormattedTextField();

        //controlador= new ControladorPaciente(this);
        //sexo.addActionListener(controlador);

        setMaximumSize(new java.awt.Dimension(781, 580));
        setMinimumSize(new java.awt.Dimension(781, 580));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        cat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        cat.setMaximumSize(new java.awt.Dimension(781, 580));
        cat.setPreferredSize(new java.awt.Dimension(781, 580));

        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        salir.setText("Salir");
        salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        panel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/headerpaciente.png"))); // NOI18N
        panel7.setMaximumSize(new java.awt.Dimension(781, 81));
        panel7.setMinimumSize(new java.awt.Dimension(781, 81));
        panel7.setPreferredSize(new java.awt.Dimension(781, 81));

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 781, Short.MAX_VALUE)
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        jTabbedPaciente.setForeground(new java.awt.Color(0, 0, 0));
        jTabbedPaciente.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPaciente.setToolTipText("");

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Parentesco:");
        jLabel1.setToolTipText("");

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Ced Afiliado:");

        bafiliado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/buscar.png"))); // NOI18N
        bafiliado.setToolTipText("Buscar afiliado");
        bafiliado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        bafiliado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bafiliado.setEnabled(false);
        bafiliado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bafiliadoFocusGained(evt);
            }
        });

        nombafiliado.setEnabled(false);
        nombafiliado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombafiliadoKeyTyped(evt);
            }
        });

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Nombre:");

        apeafiliado.setEnabled(false);
        apeafiliado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apeafiliadoKeyTyped(evt);
            }
        });

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Apellido:");

        parentesco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Conyugue", "Hijo", "Hija", "Padre", "Madre" }));
        parentesco.setEnabled(false);

        condicionfamiliar.setEnabled(false);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Condición empleado:");

        tpoempaf.setEnabled(false);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Tipo empleado:");

        try {
            cedulaafiliado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("V-##.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cedulaafiliado.setEnabled(false);
        cedulaafiliado.setPreferredSize(new java.awt.Dimension(14, 24));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(apeafiliado, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(nombafiliado)
                    .addComponent(cedulaafiliado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(parentesco, 0, 199, Short.MAX_VALUE)
                    .addComponent(tpoempaf)
                    .addComponent(condicionfamiliar)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel73)
                            .addComponent(cedulaafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(parentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(nombafiliado)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tpoempaf)
                    .addComponent(jLabel77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apeafiliado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(condicionfamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPaciente.addTab("Familiar", jPanel1);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Cond empleado:");

        condempleado.setEnabled(false);
        condempleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                condempleadoFocusGained(evt);
            }
        });

        tipoempleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));
        tipoempleado.setEnabled(false);
        tipoempleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tipoempleadoFocusGained(evt);
            }
        });

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Tipo empleado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel78)
                    .addComponent(jLabel79))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tipoempleado, 0, 236, Short.MAX_VALUE)
                    .addComponent(condempleado))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(condempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tipoempleado)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPaciente.addTab("Empleado", jPanel2);

        campoadicional.setColumns(20);
        campoadicional.setRows(5);
        campoadicional.setWrapStyleWord(true);
        campoadicional.setEnabled(false);
        campoadicional.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoadicionalFocusGained(evt);
            }
        });
        jScrollPane5.setViewportView(campoadicional);
        //controlador= new ControladorPaciente(this);
        //campoadicional.addActionListener(controlador);

        jTabbedPaciente.addTab("Observación", jScrollPane5);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Expediente:");

        expediente.setEnabled(false);
        expediente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                expedienteFocusGained(evt);
            }
        });
        expediente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                expedienteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(expediente, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(331, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(expediente)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jTabbedPaciente.addTab("Estudiante", jPanel3);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo paciente:");

        tipopaciente.setToolTipText("Seleccione el tipo de paciente... ");
        tipopaciente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tipopacienteFocusGained(evt);
            }
        });
        tipopaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tipopacienteMousePressed(evt);
            }
        });
        tipopaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipopacienteActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cédula:");
        jLabel6.setToolTipText("");

        nombre.setEnabled(false);
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

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nombre:");

        apellido.setEnabled(false);
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

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Apellido:");

        sexo.add(m);
        m.setForeground(new java.awt.Color(255, 255, 255));
        m.setText("M");
        m.setEnabled(false);
        m.setOpaque(false);
        m.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mFocusGained(evt);
            }
        });

        sexo.add(f);
        f.setForeground(new java.awt.Color(255, 255, 255));
        f.setText("F");
        f.setEnabled(false);
        f.setOpaque(false);
        f.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fFocusGained(evt);
            }
        });

        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Sexo:");

        fechanacimiento.setEnabled(false);
        fechanacimiento.setMaxSelectableDate(new java.util.Date(253370782876000L));
        fechanacimiento.setMinSelectableDate(new java.util.Date(-62135751524000L));
        fechanacimiento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fechanacimientoFocusGained(evt);
            }
        });

        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Fecha Nacimiento:");

        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Teléfono:");

        mun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        mun.setEnabled(false);
        mun.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                munFocusGained(evt);
            }
        });

        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Estado:");

        parr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        parr.setEnabled(false);

        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Municipio:");

        est.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        est.setEnabled(false);
        est.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                estFocusGained(evt);
            }
        });
        est.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                estKeyPressed(evt);
            }
        });

        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Parroquia:");

        direcc.setColumns(20);
        direcc.setLineWrap(true);
        direcc.setRows(5);
        direcc.setWrapStyleWord(true);
        direcc.setAutoscrolls(false);
        direcc.setEnabled(false);
        direcc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                direccFocusGained(evt);
            }
        });
        jScrollPane4.setViewportView(direcc);
        //controlador= new ControladorPaciente(this);
        //direcc.addActionListener(controlador);

        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setText("Dirección:");

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/modificar.png"))); // NOI18N
        modificar.setText("Modificar");
        modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
        registrar.setText("Registrar");
        registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/imprimir.png"))); // NOI18N
        imprimir.setToolTipText("Imprimir");
        imprimir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        leyenda.setForeground(new java.awt.Color(255, 255, 255));

        try {
            cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("V-##.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cedula.setEnabled(false);
        cedula.setMaximumSize(new java.awt.Dimension(14, 24));
        cedula.setMinimumSize(new java.awt.Dimension(14, 24));
        cedula.setPreferredSize(new java.awt.Dimension(14, 24));

        try {
            telefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("0###-###-##-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefono.setEnabled(false);
        telefono.setPreferredSize(new java.awt.Dimension(98, 26));

        javax.swing.GroupLayout catLayout = new javax.swing.GroupLayout(cat);
        cat.setLayout(catLayout);
        catLayout.setHorizontalGroup(
            catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(catLayout.createSequentialGroup()
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(catLayout.createSequentialGroup()
                                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre)
                                    .addComponent(apellido)
                                    .addComponent(tipopaciente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(catLayout.createSequentialGroup()
                                        .addComponent(m)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(f)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(cedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(catLayout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechanacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(est, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mun, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(parr, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(telefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(catLayout.createSequentialGroup()
                            .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(registrar)
                            .addGap(18, 18, 18)
                            .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTabbedPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90))
            .addGroup(catLayout.createSequentialGroup()
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, catLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        catLayout.setVerticalGroup(
            catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catLayout.createSequentialGroup()
                .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipopaciente)
                    .addComponent(jLabel68)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(catLayout.createSequentialGroup()
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(est, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(parr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(catLayout.createSequentialGroup()
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(m, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(f))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel67)
                            .addComponent(fechanacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registrar))
                .addGap(11, 11, 11)
                .addComponent(leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaciente.getAccessibleContext().setAccessibleName("Paciente");
        //controlador= new ControladorPaciente(this);
        //nombre.addActionListener(controlador);
        //controlador= new ControladorPaciente(this);
        //apellido.addActionListener(controlador);

        getContentPane().add(cat, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void estKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_estKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_estKeyPressed

    private void tipopacienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipopacienteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipopacienteMousePressed

    private void tipopacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipopacienteActionPerformed
           
    }//GEN-LAST:event_tipopacienteActionPerformed

    private void tipopacienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipopacienteFocusGained
    String leyend = "Seleccione un tipo de paciente y procesa a llenar los campos...";
    this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_tipopacienteFocusGained

    private void nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreFocusGained
        String leyend = "Ingrese su nombre";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_nombreFocusGained

    private void apellidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apellidoFocusGained
        String leyend = "Ingrese su apellido";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_apellidoFocusGained

    private void mFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mFocusGained
        String leyend = "Ingrese su Sexo";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_mFocusGained

    private void fFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fFocusGained
        String leyend = "Ingrese su Sexo";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_fFocusGained

    private void estFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_estFocusGained
         String leyend = "Seleccione su Estado";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_estFocusGained

    private void munFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_munFocusGained
         String leyend = "Seleccione su Municipio";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_munFocusGained

    private void direccFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_direccFocusGained
        String leyend = "Ingrese su dirección";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_direccFocusGained

    private void bafiliadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bafiliadoFocusGained
         String leyend = "Buscar en el catálogo el paciente afiliado";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_bafiliadoFocusGained

    private void condempleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_condempleadoFocusGained
         String leyend = "Condición del empleado dentro de la institución académica";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_condempleadoFocusGained

    private void tipoempleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipoempleadoFocusGained
         String leyend = "Seleccione tipo de empleado";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_tipoempleadoFocusGained

    private void campoadicionalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campoadicionalFocusGained
        String leyend = "Si necesita agregar información relevante sobre el paciente...";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_campoadicionalFocusGained

    private void expedienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_expedienteFocusGained
        String leyend = "Agregar Expediente del estudiante...";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_expedienteFocusGained

    private void fechanacimientoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fechanacimientoFocusGained
        String leyend = "Seleccione su fecha de nacimiento";
        this.getLeyenda().setText(leyend);
    }//GEN-LAST:event_fechanacimientoFocusGained

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        // TODO add your handling code here:
        this.validarcampo(evt);
        if(this.getNombre().getText().length() >= 30)
        {
          evt.consume();
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyTyped
        // TODO add your handling code here:
        this.validarcampo(evt);
        if(this.getApellido().getText().length() >= 30)
        {
          evt.consume();
        }
    }//GEN-LAST:event_apellidoKeyTyped

    private void nombafiliadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombafiliadoKeyTyped
        // TODO add your handling code here:
        this.validarcampo(evt);
        if(this.getApellido().getText().length() >= 30)
        {
          evt.consume();
        }
    }//GEN-LAST:event_nombafiliadoKeyTyped

    private void apeafiliadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apeafiliadoKeyTyped
        // TODO add your handling code here:
        this.validarcampo(evt);
        if(this.getApellido().getText().length() >= 30)
        {
          evt.consume();
        }
    }//GEN-LAST:event_apeafiliadoKeyTyped

    private void expedienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_expedienteKeyTyped
        // TODO add your handling code here:
        this.validarnumeros(evt);
        if(this.getExpediente().getText().length()>4){
          evt.consume();
        }
    }//GEN-LAST:event_expedienteKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apeafiliado;
    private javax.swing.JTextField apellido;
    private javax.swing.JButton bafiliado;
    private javax.swing.JTextArea campoadicional;
    private org.edisoncor.gui.panel.Panel cat;
    private javax.swing.JFormattedTextField cedula;
    private javax.swing.JFormattedTextField cedulaafiliado;
    private javax.swing.JTextField condempleado;
    private javax.swing.JTextField condicionfamiliar;
    private javax.swing.JTextArea direcc;
    private javax.swing.JComboBox<String> est;
    private javax.swing.JTextField expediente;
    private javax.swing.JRadioButton f;
    private com.toedter.calendar.JDateChooser fechanacimiento;
    private javax.swing.JButton imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPaciente;
    private javax.swing.JLabel leyenda;
    private javax.swing.JRadioButton m;
    private javax.swing.JButton modificar;
    private javax.swing.JComboBox<String> mun;
    private javax.swing.JTextField nombafiliado;
    private javax.swing.JTextField nombre;
    private org.edisoncor.gui.panel.Panel panel7;
    private javax.swing.JComboBox<String> parentesco;
    private javax.swing.JComboBox<String> parr;
    private javax.swing.JButton registrar;
    private javax.swing.JButton salir;
    private javax.swing.ButtonGroup sexo;
    private javax.swing.JFormattedTextField telefono;
    private javax.swing.JComboBox<String> tipoempleado;
    private javax.swing.JComboBox<String> tipopaciente;
    private javax.swing.JTextField tpoempaf;
    // End of variables declaration//GEN-END:variables
}
