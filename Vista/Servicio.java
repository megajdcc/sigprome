/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorServicio;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jnatn'h
 */
public class Servicio extends Dialog {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControladorServicio controlador;
    private int idcita;
    public void IdCita(int IdCita){
         this.idcita = IdCita;
         this.setIdCita(idcita);
     }
    private void setController(ControladorServicio controlador){
        this.controlador = controlador;
        asignarListener();
        DefaultComboBoxModel<String> listiposervicio = new DefaultComboBoxModel<String>();
        controlador.Listartiposervicio(listiposervicio);
        getTiposervicio().setModel(listiposervicio);
    }
    public ControladorServicio getController(){
        return this.controlador;
    }
    public void asignarListener(){
        procesarcita.addActionListener(controlador);
        nuevacita.addActionListener(controlador);
        eliminarcita.addActionListener(controlador);
        citashoy.addMouseListener(controlador);
        citashoy.addKeyListener(controlador);
        nuevopaciente.addActionListener(controlador);
       // cedula.addKeyListener(controlador);
        consultar.addActionListener(controlador);
        tiposervicio.addActionListener(controlador);
        Registrar.addActionListener(controlador);
        Salir.addActionListener(controlador);
        //        procesar.addActionListener(controlador);
        getAnadir().addActionListener(controlador);
        Opciones.addMouseListener(controlador);
        getBuscarestado().addKeyListener(controlador);
        getSolicitudes().addMouseListener(controlador);
        getQuitar().addActionListener(controlador);
        seleccionada.addMouseListener(controlador);
        seleccionada.addListSelectionListener(controlador);
    }
    public int getIdcita(){
         return idcita;
     }
    public void setIdCita(int idcita){
         this.idcita = idcita;
     }
    public JComboBox<String> getTiposervicio(){
        return tiposervicio;
    }
    public void setTiposervicio(JComboBox<String> tiposervicio){
        this.tiposervicio = tiposervicio;
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
    public JTextField getTipopaciente(){
        return tipopaciente;
    }
    public void setTipopaciente(JTextField tipopaciente){
        this.tipopaciente = tipopaciente;
    }
    public JFormattedTextField getCedula(){
        return cedula;
    }
    public void setCedula(JFormattedTextField cedula){
        this.cedula = cedula;
    }
    public JButton getConsultar(){
        return consultar;
    }
    public void setConsultar(JButton consultar){
        this.consultar = consultar;
    }
    public JButton getProcesarcita(){
        return procesarcita;
    }
    public void setProcesarcita(JButton procesarcita){
        this.procesarcita = procesarcita;
    }
    public JButton getSalir(){
        return Salir;
    }
    public void setSalir(JButton salir){
        this.Salir = salir;
    }
    public JLabel getLeyenda(){
        return Leyenda;
    }
    public void setLeyenda(JLabel Leyenda){
        this.Leyenda = Leyenda;
    }

  public JList<String> getSeleccionada() {
    return seleccionada;
  }

 

  public void setSeleccionada(JList<String> seleccionada) {
    this.seleccionada = seleccionada;
  }
 
  public JButton getQuitar() {
    return quitar;
  }

  public void setQuitar(JButton quitar) {
    this.quitar = quitar;
  }
    
//    public void setProcesado(JRadioButton proce){
//        this.procesado = proce;
//    }
//    public JRadioButton getProcesado(){
//        return this.procesado;
//    }
//    public void setEspera(JRadioButton esper){
//        this.espera = esper;
//    }
//    public JRadioButton getEspera(){
//        return this.espera;
//    }
//    public void setFinalizado(JRadioButton finalizado){
//        this.finalizado = finalizado;
//    }
//    public JRadioButton getFinalizado(){
//        return this.finalizado;
//    }
    
//    public JDateChooser getFechacita()
//    {
//        return fechacita;
//    }
//    public void setFechacita(JDateChooser fechacita)
//    {
//        this.fechacita = fechacita;
//    }
    public JButton getRegistrar(){
        return Registrar;
    }
    public void setRegistrar(JButton registrar){
        this.Registrar = registrar;
    }
    public void setCitashoy(JTable citas){
        citashoy = citas;
    }
    public JTable getCitashoy(){
        return this.citashoy;
    }
    public void setNuevacita(JButton nuevacita){
        this.nuevacita = nuevacita;
    }
    public JButton getNuevacita(){
        return this.nuevacita;
    }
    public void seteliminarcita(JButton eliminarcita){
        this.eliminarcita= eliminarcita;
    }
    public JButton geteliminarcita(){
        return this.eliminarcita;
    }
    public void setNuevopaciente(JButton nuevo){
        this.nuevopaciente= nuevo;
    }
    public JButton getNuevopaciente(){
        return this.nuevopaciente;
    }
    public JTabbedPane getMovimiento(){
        return movimiento;
    }
    public ButtonGroup getAdministrativo(){
        return administrativo;
    }
//    public JRadioButton getReembolso(){
//        return reembolso;
//    }
//    public JRadioButton getValidarreposo(){
//        return validarreposo;
//    }
//    public JRadioButton getEstudiosespeciales(){
//        return estudiosespeciales;
//    }
    public void setPaneladministrativo(JPanel admins2){
        this.adminis = admins2;
    }
    public JPanel getPaneladministrativo(){
        return this.adminis;
    }
    public void setEstadosadministrativos(ButtonGroup grupo){
        this.estadosadministrativos = grupo;
    }
    public ButtonGroup getEstadadministrativo(){
        return this.estadosadministrativos;
    }

  public JList<String> getOpciones() {
    return Opciones;
  }

  public JPanel getEstadoadministrativo() {
	  return status;
  }

  public void setOpciones(JList<String> Opciones) {
    this.Opciones = Opciones;
  }

  public JButton getAnadir() {
    return anadir;
  }

  public void setAnadir(JButton anadir) {
    this.anadir = anadir;
  }

  public JTextField getBuscarestado() {
    return buscarestado;
  }

  public void setBuscarestado(JTextField buscarestado) {
    this.buscarestado = buscarestado;
  }

  public JTable getSolicitudes() {
    return solicitudes;
  }

  public void setSolicitudes(JTable solicitudes) {
    this.solicitudes = solicitudes;
  }
    
    /**
     * Creates new form Servicio
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Servicio(java.awt.Frame parent, boolean modal) {
    	
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setTitle("Servicio");
        setController(new ControladorServicio(this, parent));
        setLocationRelativeTo(null);
        DefaultListModel<String> lista = new DefaultListModel<String>();
        this.seleccionada.setModel(lista);
        
    }


    private void viewheader() {
    	header.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/header-servicio.png")));
        header.setPreferredSize(new Dimension(857, 75));
        header.setMaximumSize(header.getPreferredSize());
        header.setMinimumSize(header.getPreferredSize());
        panelprincipal.add(header,BorderLayout.NORTH);
    }
    
    @SuppressWarnings("serial")
	private void viewcontent() {
    	JPanel conten1 = new JPanel();
    	conten1.setOpaque(false);
    	
    	//Contenedor norte ... 
    	JPanel nort = new JPanel();
    	nort.setOpaque(false);
    	nort.setPreferredSize(new Dimension(830,100));
    	nort.setMaximumSize(nort.getPreferredSize());
    	nort.setMinimumSize(nort.getPreferredSize());
    	
    	JPanel nort1 = new JPanel();
    	JPanel nort2 = new JPanel();
    	
    	nort1.setOpaque(false);
    	nort2.setOpaque(false);
    	
    	nort.setLayout(new GridLayout(1,2));
    	
    	nort.add(nort1);
    	nort.add(nort2);
    	
    	nort1.setLayout(new FlowLayout(FlowLayout.TRAILING));
    	
    	//nort1.setLayout(new GridLayout(3,2));
    	
    	tiposervicios = new JLabel();
    	tiposervicios.setFont(new java.awt.Font("Times New Roman", 1, 18));
        tiposervicios.setForeground(new Color(255, 255, 255));
        tiposervicios.setText("Tipo de servicio:");
        tiposervicios.setPreferredSize(new Dimension(140,20));
        nort1.add(tiposervicios);
        
        tiposervicio = new JComboBox<String>();
        tiposervicio.setForeground(new Color(0, 0, 0));
        tiposervicio.setPreferredSize(new Dimension(200,20));
        
        
        tiposervicio.setModel(new DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        tiposervicio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tiposervicioFocusGained(evt);
            }
        });
        
        nort1.add(tiposervicio);
        
        lcedula = new JLabel();
        lcedula.setFont(new java.awt.Font("Times New Roman", 1, 18));
        lcedula.setForeground(new java.awt.Color(255, 255, 255));
        lcedula.setText("Cédula:");
        lcedula.setPreferredSize(new Dimension(140,20));
        
        nort1.add(lcedula);
        
        cedula = new JFormattedTextField();
        cedula.setPreferredSize(new Dimension(140,20));
        try {
            cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("V-##.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cedula.setEnabled(false);
        
        nort1.add(cedula);
        
        consultar = new JButton();
        consultar.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/buscar.png")));
        consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        consultar.setEnabled(false);
        consultar.setPreferredSize(new Dimension(20,20));
        
        nort1.add(consultar);
        
        nuevopaciente = new JButton();
        nuevopaciente.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/registrar.png")));
        nuevopaciente.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        nuevopaciente.setEnabled(false);
        nuevopaciente.setPreferredSize(new Dimension(20,20));
        
        nort1.add(nuevopaciente);
        
        ltipopaciente = new JLabel();
        ltipopaciente.setFont(new java.awt.Font("Times New Roman", 1, 18));
        ltipopaciente.setForeground(new java.awt.Color(255, 255, 255));
        ltipopaciente.setText("Tipo de paciente:");
        ltipopaciente.setPreferredSize(new Dimension(140,20));
        
        nort1.add(ltipopaciente);
        
        tipopaciente = new JTextField();
        tipopaciente.setEnabled(false);
        tipopaciente.setPreferredSize(new Dimension(200,20));
        
        nort1.add(tipopaciente);
        
        // panel izq nort2 
        
        lnombre = new JLabel();
        lnombre.setFont(new Font("Times New Roman", 1, 18));
        lnombre.setForeground(new Color(255, 255, 255));
        lnombre.setText("Nombre:");
        
        lnombre.setPreferredSize(new Dimension(120,20));
        nort2.add(lnombre);
        
        nombre = new JTextField();
        nombre.setEnabled(false);
        nombre.setPreferredSize(new Dimension(200,20));
        
        nort2.add(nombre);
        
        lapellido = new javax.swing.JLabel();
        lapellido.setFont(new java.awt.Font("Times New Roman", 1, 18));
        lapellido.setForeground(new java.awt.Color(255, 255, 255));
        lapellido.setText("Apellido:");
        lapellido.setPreferredSize(new Dimension(120,20));
        
        nort2.add(lapellido);
        
        
        apellido = new JTextField();
        apellido.setEnabled(false);
        apellido.setPreferredSize(new Dimension(200,20));
        
        nort2.add(apellido);
        
        
        JPanel sout = new JPanel();
        sout.setOpaque(false);
    	sout.setPreferredSize(new Dimension(830,200));
    	sout.setMinimumSize(sout.getPreferredSize());
    	sout.setMaximumSize(sout.getPreferredSize());
    	// parte sur donde esta la operaciones del proceso.. 
    	
    	movimiento = new JTabbedPane();
    	movimiento.setPreferredSize(new Dimension(sout.getPreferredSize().width,sout.getPreferredSize().height-10));
    	movimiento.setOpaque(false);
    	
    	
    	//tablas de citas pendiente... 
    	nuevacita = new javax.swing.JButton();
        procesarcita = new javax.swing.JButton();
        eliminarcita = new javax.swing.JButton();
    	citashoy = new JTable();
    	citashoy.setModel(new DefaultTableModel(
              new Object [][] {
                  {null, null, null, null, null},
              },
              new String [] {
                  "Cédula", "Nombre", "Apellido", "Tipo de paciente", "Fecha asignada"
              }
          ) {
              boolean[] canEdit = new boolean [] {
                  false, false, false, false, false
              };
  
              public boolean isCellEditable(int rowIndex, int columnIndex) {
                  return canEdit [columnIndex];
              }
          });
        citashoy.setEnabled(false);
        jScrollPane1.setViewportView(citashoy);
    	jScrollPane1.setPreferredSize(new Dimension(sout.getPreferredSize().width,120));;
        
        panelcitas = new JPanel();
        panelcitas.setLayout(new BorderLayout(0,0));
        
        panelcitas.add(jScrollPane1,BorderLayout.NORTH);
        
        JPanel footerpanelcitas = new JPanel();
        footerpanelcitas.setOpaque(false);
        JPanel izq = new JPanel();
        izq.setLayout(new FlowLayout(FlowLayout.LEFT));
        izq.setPreferredSize(new Dimension(400,40));

        izq.setOpaque(false);
        
        JPanel der = new JPanel();
        der.setLayout(new FlowLayout(FlowLayout.RIGHT));
        der.setPreferredSize(new Dimension(400,40));
        der.setOpaque(false);
        
        
        nuevacita.setForeground(new java.awt.Color(0, 0, 0));
	    nuevacita.setText("Nueva cita");
	    nuevacita.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	    nuevacita.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    nuevacita.setEnabled(false);
	    nuevacita.setPreferredSize(new Dimension(200,30));
	    
	    izq.add(nuevacita);
	    
	    procesarcita.setForeground(new java.awt.Color(0, 0, 0));
	    procesarcita.setText("Procesar");
	    procesarcita.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	    procesarcita.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    procesarcita.setEnabled(false);
	    
	    procesarcita.setPreferredSize(new Dimension(150,30));
	    
	    der.add(procesarcita);
	    
	    eliminarcita.setForeground(new java.awt.Color(0, 0, 0));
	    eliminarcita.setText("Eliminar");
	    eliminarcita.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	    eliminarcita.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    eliminarcita.setEnabled(false);
	    eliminarcita.setPreferredSize(new Dimension(150,30));
        
	    der.add(eliminarcita);
	    
	    footerpanelcitas.add(izq);
	    footerpanelcitas.add(der);
	    
        panelcitas.add(footerpanelcitas,BorderLayout.SOUTH);
        
        
   
        movimiento.addTab("Cita", panelcitas);
        
        
        // panel administrativo 
        
        adminis = new JPanel();
        adminis.setOpaque(false);
        
        adminis.setPreferredSize(new Dimension(movimiento.getPreferredSize().width-10, movimiento.getPreferredSize().height-40));
        adminis.setLayout(new BorderLayout(0,0));
        
        JPanel listizq = new JPanel();
        listizq.setOpaque(false);
        
        listizq.setPreferredSize(new Dimension(adminis.getPreferredSize().width/3,adminis.getPreferredSize().height));
        
        listizq.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel lopciones = new JLabel("Opciones: ");
	    lopciones.setFont(new Font("Dialog", 1, 14));
	    lopciones.setForeground(new Color(0, 0, 0));
	    lopciones.setPreferredSize(new Dimension(listizq.getPreferredSize().width,20));
	    
	    listizq.add(lopciones);
	    
	    
        Opciones = new JList<>();
	    Opciones.setToolTipText("Seleccione uno o mas opciones");
	    Opciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	    Opciones.setEnabled(false);
	    
	    jScrollPane2 = new JScrollPane();
	    jScrollPane2.setViewportView(Opciones);
        jScrollPane2.setPreferredSize(new Dimension(listizq.getPreferredSize().width-30,listizq.getPreferredSize().height-40));
	    
        listizq.add(jScrollPane2);
	  
        adminis.add(listizq,BorderLayout.WEST);
        
        JPanel botonera = new JPanel();
        botonera.setOpaque(false);
       
        FlowLayout lay = new FlowLayout(FlowLayout.CENTER);
        lay.setAlignOnBaseline(false);
        lay.setVgap(30);

        botonera.setLayout(lay);
       
        botonera.setPreferredSize(new Dimension(adminis.getPreferredSize().width/3 - 60,listizq.getPreferredSize().height-60));
        JPanel botones = new JPanel();
        botones.setOpaque(false);
        botones.setPreferredSize(new Dimension(adminis.getPreferredSize().width/3 - 60,listizq.getPreferredSize().height-60));
        botones.setLayout(new GridLayout(2,1));
        
        
        
        anadir = new JButton();
        quitar = new JButton();
        estadosadministrativos = new ButtonGroup();
        estadosadministrativos.add(anadir);
        estadosadministrativos.add(quitar);
        
       
	      anadir.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/der.png")));
	      anadir.setToolTipText("");
	      anadir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	      anadir.setEnabled(false);
	      anadir.setIconTextGap(10);
	      anadir.setPreferredSize(new Dimension(120,30)); 
	    
	      botones.add(anadir);
	    
	      quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/izq.png"))); 
	      quitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
	      quitar.setEnabled(false);
	      quitar.setIconTextGap(10);
	      quitar.setPreferredSize(new Dimension(120,30)); 
		    
		  botones.add(quitar);
		  botonera.add(botones);
		  adminis.add(botonera,BorderLayout.CENTER);
		  
		  
		JPanel listder = new JPanel();
		listder.setOpaque(false);
		listder.setPreferredSize(new Dimension(adminis.getPreferredSize().width/3,adminis.getPreferredSize().height-10));
	    listder.setLayout(new FlowLayout(FlowLayout.CENTER));
		
	    JLabel lseleccion = new JLabel("Opciones Añadidas");
	    lseleccion.setFont(new java.awt.Font("Dialog", 1, 14));
	    lseleccion.setForeground(new java.awt.Color(0, 0, 0));
	    lseleccion.setToolTipText("");
	    lseleccion.setPreferredSize(new Dimension(listizq.getPreferredSize().width,20));
	    
	    listder.add(lseleccion);
	    
	    seleccionada = new javax.swing.JList<>();
	    seleccionada.setForeground(new java.awt.Color(0, 0, 0));
	    seleccionada.setToolTipText("");
	    seleccionada.setEnabled(false);
	    
	    jScrollPane5 = new javax.swing.JScrollPane();
	    jScrollPane5.setViewportView(seleccionada);
	    jScrollPane5.setPreferredSize(new Dimension(listder.getPreferredSize().width-30,listder.getPreferredSize().height-30));
	    
	    listder.add(jScrollPane5);
	    adminis.add(listder,BorderLayout.EAST);
	    
	    movimiento.addTab("Opciones Administrativas", adminis);
	    
	    // panel de estatus de Opciones Administrativas .. 
	    
	    status = new JPanel();
        status.setOpaque(false);
      
        JPanel nor1 = new JPanel();
        nor1.setOpaque(false);
        nor1.setPreferredSize(new Dimension(status.getPreferredSize().width-20,50));
        nor1.setMinimumSize(nor1.getPreferredSize());
      //  nor1.setOpaque(false);
        
        
        status.setPreferredSize(new Dimension(movimiento.getPreferredSize().width-10, movimiento.getPreferredSize().height-40));
        status.setLayout(new BorderLayout(0,0));
	    
        JLabel lopcion = new JLabel("Paciente: ");
        
        lopcion.setFont(new Font("Dialog", 1, 14)); 
        lopcion.setForeground(new Color(0, 0, 0));
        lopcion.setPreferredSize(new Dimension(80,30));
        nor1.setBackground(Color.black);
        nor1.add(lopcion);
        
	    buscarestado = new JTextField();
	    buscarestado.setPreferredSize(new Dimension(600,30));
	    buscarestado.setForeground(new java.awt.Color(0, 0, 0));
	    buscarestado.addKeyListener(new java.awt.event.KeyAdapter() {
          public void keyTyped(java.awt.event.KeyEvent evt) {
              buscarestadoKeyTyped(evt);
          }
      });
	    nor1.add(buscarestado);
	    
	    JPanel tabla = new JPanel();
	    
	    tabla.setOpaque(false);
	    tabla.setPreferredSize(new Dimension(800,130));
	    tabla.setMaximumSize(tabla.getPreferredSize());
	    jScrollPane3 = new JScrollPane();
	    jScrollPane3.setPreferredSize(new Dimension(tabla.getPreferredSize().width,120));
        solicitudes = new JTable();
        
        solicitudes.setForeground(new java.awt.Color(0, 0, 0));
        solicitudes.setModel(new javax.swing.table.DefaultTableModel(
          new Object [][] {
              {null, null, null, null, null},
              {null, null, null, null, null},
              {null, null, null, null, null},
              {null, null, null, null, null}
          },
          new String [] {
              "Cédula", "Nombre", "Tipo de paciente", "Opción Administrativa", "Estado de solicitud"
          }
      ) {
          boolean[] canEdit = new boolean [] {
              false, false, false, false, false
          };

          public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit [columnIndex];
          }
        });
        
        jScrollPane3.setViewportView(solicitudes);
        tabla.add(jScrollPane3);
        status.add(nor1,BorderLayout.NORTH);
        status.add(tabla,BorderLayout.CENTER);
        movimiento.addTab("Estado de solicitudes administrativas", status);
        movimiento.getAccessibleContext().setAccessibleName("movimiento");
        
	    sout.add(movimiento);
        conten1.add(nort);
    	conten1.add(sout);
    	
    	panelprincipal.add(conten1,BorderLayout.CENTER);
    }
    
    private void viewfooter() {
    	JPanel foot = new JPanel();
    	foot.setOpaque(false);
    	foot.setPreferredSize(new Dimension(200, 80));
    	foot.setLayout(new BorderLayout(0,0));
    	JPanel footnor = new JPanel();
    	footnor.setOpaque(false);
    	footnor.setPreferredSize(new Dimension(foot.getPreferredSize().width,40));
    	
    	footnor.setLayout(new BorderLayout(0,0));
    	
    	JPanel footizq = new JPanel();
    	footizq.setOpaque(false);
    	footnor.add(footizq,BorderLayout.WEST);
    	
    	JPanel footder = new JPanel();
    	footder.setOpaque(false);
    	footder.setLayout(new FlowLayout(FlowLayout.RIGHT));
    	footnor.add(footder,BorderLayout.EAST);
    	
    	
    	Registrar = new JButton();
        Salir = new JButton();  
    	Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/procesos.png")));
    	Registrar.setText("Procesar");
    	Registrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    	Registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    	Registrar.setEnabled(false);
    	Registrar.setPreferredSize(new Dimension(150,30));
    	
    	Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png")));
    	Salir.setText("Salir");
    	Salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    	Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    	Salir.setPreferredSize(new Dimension(150,30));
    	footder.add(Registrar);
    	footder.add(Salir);
    	
    	foot.add(footnor,BorderLayout.NORTH);
    	
    	Leyenda = new JLabel(); 
    	Leyenda.setFont(new java.awt.Font("Dialog", 1, 14));
    	Leyenda.setForeground(new java.awt.Color(255, 255, 255));
    	foot.add(Leyenda,BorderLayout.SOUTH);
    	
    	panelprincipal.add(foot,BorderLayout.SOUTH);
    }
    
    @SuppressWarnings("serial")
	private void initComponents() {

        new com.toedter.calendar.JDayChooser();
        administrativo = new javax.swing.ButtonGroup();    
        panelprincipal = new org.edisoncor.gui.panel.Panel();
        header = new org.edisoncor.gui.panel.Panel();
        jScrollPane1 = new javax.swing.JScrollPane();  
        setPreferredSize(new Dimension(857, 520));
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
        
//        setLayout(new BorderLayout(0,0));
        
        panelprincipal.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png")));
        panelprincipal.setPreferredSize(new java.awt.Dimension(857, 500));
        panelprincipal.setMinimumSize(panelprincipal.getPreferredSize());
        panelprincipal.setMaximumSize(panelprincipal.getPreferredSize());
        panelprincipal.setLayout(new BorderLayout(0,0));
        add(panelprincipal);
        viewheader();
        viewcontent();
        viewfooter();
        
       
        
    }

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {
      if(getController().cit){
        String alert = "No puede cerrar servicio si no procesa la cita asignada al paciente\n "+this.getNombre().getText()+
                " "+this.getApellido().getText();
        JOptionPane.showMessageDialog(null,alert,"No se puede cerrar ventana", JOptionPane.INFORMATION_MESSAGE);
      }else{
        getController().limpiar();
        setVisible(false);
        dispose();
      }
    }

    private void tiposervicioFocusGained(java.awt.event.FocusEvent evt) {
        String leyenda = "Seleccione el tipo de servicio a implementar";
        this.getLeyenda().setText(leyenda);
    }
    
    private void validar (KeyEvent e){
        char b = e.getKeyChar();
         if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE){
            e.consume();
         }
    }
 
    private void buscarestadoKeyTyped(java.awt.event.KeyEvent evt) {
    this.validar(evt);
  }
  	
  
  	/**
  	 * Campos de clases referente a los componentes ... 
  	 */
    public JLabel Leyenda,tiposervicios,lcedula,ltipopaciente,lnombre,lapellido,leyenda;
    private JList<String> Opciones, seleccionada;
    private JButton Registrar,Salir,anadir,consultar,eliminarcita,nuevacita,nuevopaciente, procesarcita,quitar;
    private ButtonGroup administrativo,estadosadministrativos;
    private JTextField apellido,buscarestado,nombre, tipopaciente;
    private JFormattedTextField cedula;
    private JTable citashoy,solicitudes;
    private JPanel panelcitas,status,adminis;
    private JScrollPane jScrollPane1,jScrollPane2,jScrollPane3,jScrollPane5;
    private JTabbedPane movimiento;
    private org.edisoncor.gui.panel.Panel panelprincipal, header;
    private JComboBox<String> tiposervicio;
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
          Servicio ini  = new Servicio(null,true);
          ini.setVisible(true);
        });
    }

}
