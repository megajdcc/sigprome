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
package Vista;
import Controlador.ControladorConsulta;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import static javax.swing.BorderFactory.createBevelBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import org.edisoncor.gui.panel.*;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author Jnatn'h
 */
public class Consulta extends java.awt.Dialog {
    private JDialog finalizar;
    public JButton botones;
    ControladorConsulta controlador;
    //getter y setters
    public void setController(ControladorConsulta controlador){
        this.controlador = controlador;
    }
    public ControladorConsulta getController(){
        return controlador;
    } 
    public void setOyente(){
        consultar.addActionListener(getController());
        observaciontriaje.addActionListener(getController());
        asignarpatologia.addActionListener(getController());
        historiamedica.addActionListener(getController());
        Procesar.addActionListener(getController());
        Salir.addActionListener(getController());
        this.anadir.addActionListener(getController());
        this.quitar.addActionListener(getController());
        seleccionada.addMouseListener(getController());
        seleccionada.addListSelectionListener(getController());
        patologias.addMouseListener(getController());
        Patologias.addMouseListener(getController());
      

    }
    public void setoyentesinternos(){
        grabarrecipe.addActionListener(getController());

    }
    public void setOyentesinternosreposo(){
       grabarreposo.addActionListener(getController());
    }
    public void setProcesar(JButton procesar){
        this.Procesar = procesar;
    }
    public JButton getProcesar(){
        return this.Procesar;
    }
    public void setSalir(JButton salir){
        this.Salir = salir;
    }
    public JButton getSalir(){
        return this.Salir;
    }
    
    // paciente...
    public void setConsultar(JButton consultar){
        this.consultar = consultar;
    }
    public JButton getConsultar(){
        return consultar;
    }
    public void setCedula(JFormattedTextField cedula){
        this.cedula = cedula;
    }
    public JFormattedTextField getCedula(){
        return this.cedula;
    }
    public void setNombre(JTextField nombre){
        this.Nombre = nombre;
    }

    public JTextField getNombre(){
        return this.Nombre;
    }
    public void setApellido(JTextField apellido){
        this.Apellido = apellido;
    }
    public JTextField getApellido(){
        return this.Apellido;
    }
    public void setTipopaciente(JTextField tipopaciente){
        this.Tipopaciente = tipopaciente;
    }
    public JTextField getTipopaciente(){
        return Tipopaciente;
    }
    // triaje 
    public void setPeso(JLabel peso){
        this.peso = peso;
    }
    public JLabel getPeso(){
        return this.peso;
    }
    public void setTalla(JLabel talla){
        this.talla = talla;
    }
    public JLabel getTalla(){
        return this.talla;
    }
    public void setTemperatura(JLabel temperatura){
        this.temperatura = temperatura;
    }
    public JLabel getTemperatura(){
        return this.temperatura;
    }
    public void setPresion(JLabel presion){
        this.tension = presion;
    }
    public JLabel getPresion(){
        return this.tension;
    }
    public void setGlicemia(JLabel glicemia){
        this.glicemia = glicemia;
    }
    public JLabel getGlicemia(){
        return this.glicemia;
    }
    public void setObservaciontriaje(JButton obser){
        this.observaciontriaje = obser;
    }
    public JButton getObservaciontriaje(){
        return this.observaciontriaje;
    }
    public void setPanelTriaje(JPanel triaje){
        this.triaje = triaje;
    }
    public JPanel getPanelTriaje(){
        return this.triaje;
    }
    //consulta
    public void setSintoma(JTextArea sintoma){
        this.sintoma = sintoma;
    }
    public JTextArea getSintoma(){
        return this.sintoma;
    }
    public void setDiagnostico(JTextArea diagnostico){
        this.diagnostico = diagnostico;
    }
    public JTextArea getDiagnostico(){
        return this.diagnostico;
    }
   
    public JButton getAsignarpatologia() {
    return asignarpatologia;
    }

    public void setAsignarpatologia(JButton asignarpatologia) {
    this.asignarpatologia = asignarpatologia;
    }
    
    // doctor.. .
    public void setDoctor(JLabel nombr){
        this.doctor = nombr;
    }
    public JLabel getDoctor(){
        return this.doctor;
    }
    // leyenda
    public void setLeyenda(JLabel leyenda){
        this.Leyenda = leyenda;
    }
    public JLabel getLeyenda(){
        return Leyenda;
    }
  public JList<String> getPatologias() {
    return Patologias;
  }

  public JPanel getPanelpatologias() {
    return patologias;
  }

  public void setPanelpatologias(JPanel panelpatologias) {
    this.patologias = panelpatologias;
  }

  public void setPatologias(JList<String> Patologias) {
    this.Patologias = Patologias;
  }

  public JButton getAnadir() {
    return anadir;
  }

  public void setAnadir(JButton anadir) {
    this.anadir = anadir;
  }

  public JPanel getConsulta() {
    return consulta;
  }

  public void setConsulta(JPanel consulta) {
    this.consulta = consulta;
  }

  public void setPatologias(JPanel patologias) {
    this.patologias = patologias;
  }

  public JButton getQuitar() {
    return quitar;
  }

  public void setQuitar(JButton quitar) {
    this.quitar = quitar;
  }

  public JList<String> getSeleccionada() {
    return seleccionada;
  }

  public void setSeleccionada(JList<String> seleccionada) {
    this.seleccionada = seleccionada;
  }

  public JPanel getTriaje() {
    return triaje;
  }

  public void setTriaje(JPanel triaje) {
    this.triaje = triaje;
  }

  public JTabbedPane getMovimiento() {
    return movimiento;
  }

  public void setMovimiento(JTabbedPane movimiento) {
    this.movimiento = movimiento;
  }
    
    /**
     * Creates new form Consulta
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Consulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setModalityType(Dialog.ModalityType.MODELESS);
        initComponents();
        setController(new ControladorConsulta(this));
        this.setOyente();
        this.setResizable(false);
        getController().asignardoctor();
        setTitle("Consulta");
         DefaultListModel model = new DefaultListModel();
         this.seleccionada.setModel(model);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel5 = new org.edisoncor.gui.panel.Panel();
        Procesar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        cedul = new javax.swing.JLabel();
        nomb = new javax.swing.JLabel();
        apell = new javax.swing.JLabel();
        tipopac = new javax.swing.JLabel();
        cedula = new javax.swing.JFormattedTextField();
        Nombre = new javax.swing.JTextField();
        Apellido = new javax.swing.JTextField();
        Tipopaciente = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Leyenda = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        historiamedica = new javax.swing.JButton();
        panel2 = new org.edisoncor.gui.panel.Panel();
        movimiento = new javax.swing.JTabbedPane();
        triaje = new javax.swing.JPanel();
        peso = new javax.swing.JLabel();
        talla = new javax.swing.JLabel();
        temperatura = new javax.swing.JLabel();
        tension = new javax.swing.JLabel();
        glicemia = new javax.swing.JLabel();
        observaciontriaje = new javax.swing.JButton();
        consulta = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        sintoma = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        diagnostico = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        asignarpatologia = new javax.swing.JButton();
        patologias = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Patologias = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        seleccionada = new javax.swing.JList<>();
        anadir = new javax.swing.JButton();
        quitar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        doctor = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        panel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        panel5.setMaximumSize(new java.awt.Dimension(771, 457));
        panel5.setPreferredSize(new java.awt.Dimension(771, 452));

        Procesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
        Procesar.setText("Procesar");
        Procesar.setToolTipText("");
        Procesar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Procesar.setEnabled(false);
        Procesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcesarActionPerformed(evt);
            }
        });

        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cedul.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        cedul.setForeground(new java.awt.Color(255, 255, 255));
        cedul.setText("Cédula:");

        nomb.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        nomb.setForeground(new java.awt.Color(255, 255, 255));
        nomb.setText("Nombre:");

        apell.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        apell.setForeground(new java.awt.Color(255, 255, 255));
        apell.setText("Apellido:");

        tipopac.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        tipopac.setForeground(new java.awt.Color(255, 255, 255));
        tipopac.setText("Tipo de paciente:");

        cedula.setForeground(new java.awt.Color(0, 0, 0));
        try {
            cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("V-##.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cedula.setEnabled(false);

        Nombre.setForeground(new java.awt.Color(0, 0, 0));
        Nombre.setEnabled(false);

        Apellido.setForeground(new java.awt.Color(0, 0, 0));
        Apellido.setEnabled(false);

        Tipopaciente.setForeground(new java.awt.Color(0, 0, 0));
        Tipopaciente.setEnabled(false);

        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/buscar.png"))); // NOI18N
        consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("HelveticaLT", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Paciente");
        jLabel11.setToolTipText("");

        historiamedica.setText("Historia Médica");
        historiamedica.setEnabled(false);

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-consulta.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(771, 75));
        panel2.setMinimumSize(new java.awt.Dimension(771, 75));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 771, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        triaje.setEnabled(false);

        peso.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        peso.setForeground(new java.awt.Color(0, 0, 0));
        peso.setText("Peso:");
        peso.setToolTipText("");

        talla.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        talla.setForeground(new java.awt.Color(0, 0, 0));
        talla.setText("Talla:");
        talla.setToolTipText("");

        temperatura.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        temperatura.setForeground(new java.awt.Color(0, 0, 0));
        temperatura.setText("Temperatura:");
        temperatura.setToolTipText("");

        tension.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tension.setForeground(new java.awt.Color(0, 0, 0));
        tension.setText("Tensión:");
        tension.setToolTipText("");

        glicemia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        glicemia.setForeground(new java.awt.Color(0, 0, 0));
        glicemia.setText("Glicemia:");
        glicemia.setToolTipText("");

        observaciontriaje.setText("Observación");
        observaciontriaje.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        observaciontriaje.setEnabled(false);

        javax.swing.GroupLayout triajeLayout = new javax.swing.GroupLayout(triaje);
        triaje.setLayout(triajeLayout);
        triajeLayout.setHorizontalGroup(
            triajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(triajeLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(triajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(observaciontriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(triajeLayout.createSequentialGroup()
                        .addGroup(triajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tension, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(peso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(triajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(talla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(glicemia, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        triajeLayout.setVerticalGroup(
            triajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(triajeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(triajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(peso)
                    .addComponent(talla)
                    .addComponent(temperatura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(triajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tension)
                    .addComponent(glicemia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(observaciontriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        movimiento.addTab("Triage", triaje);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Síntoma:");

        sintoma.setColumns(20);
        sintoma.setLineWrap(true);
        sintoma.setRows(2);
        sintoma.setEnabled(false);
        sintoma.setMaximumSize(new java.awt.Dimension(220, 80));
        sintoma.setName(""); // NOI18N
        sintoma.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                sintomaFocusGained(evt);
            }
        });
        jScrollPane5.setViewportView(sintoma);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Diagnóstico:");

        diagnostico.setColumns(20);
        diagnostico.setLineWrap(true);
        diagnostico.setRows(5);
        diagnostico.setEnabled(false);
        diagnostico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                diagnosticoFocusGained(evt);
            }
        });
        jScrollPane6.setViewportView(diagnostico);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Patología:");

        asignarpatologia.setText("Asignar Patologías");
        asignarpatologia.setToolTipText("Asignación de patologias");
        asignarpatologia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        asignarpatologia.setEnabled(false);

        javax.swing.GroupLayout consultaLayout = new javax.swing.GroupLayout(consulta);
        consulta.setLayout(consultaLayout);
        consultaLayout.setHorizontalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(asignarpatologia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        consultaLayout.setVerticalGroup(
            consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(consultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(asignarpatologia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        movimiento.addTab("Consulta", consulta);

        patologias.setEnabled(false);

        Patologias.setToolTipText("Seleccione una o mas patologías");
        Patologias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Patologias.setEnabled(false);
        jScrollPane2.setViewportView(Patologias);

        seleccionada.setForeground(new java.awt.Color(0, 0, 0));
        seleccionada.setToolTipText("");
        seleccionada.setEnabled(false);
        jScrollPane7.setViewportView(seleccionada);

        anadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/der.png"))); // NOI18N
        anadir.setToolTipText("");
        anadir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        anadir.setEnabled(false);
        anadir.setIconTextGap(10);

        quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/izq.png"))); // NOI18N
        quitar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        quitar.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Patologías:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Patologías Asignadas");
        jLabel6.setToolTipText("");

        javax.swing.GroupLayout patologiasLayout = new javax.swing.GroupLayout(patologias);
        patologias.setLayout(patologiasLayout);
        patologiasLayout.setHorizontalGroup(
            patologiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patologiasLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(patologiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patologiasLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(patologiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(anadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quitar, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(patologiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        patologiasLayout.setVerticalGroup(
            patologiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patologiasLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(patologiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(patologiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addGroup(patologiasLayout.createSequentialGroup()
                        .addComponent(anadir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        movimiento.addTab("Patologías", patologias);

        doctor.setFont(new java.awt.Font("HelveticaLT", 1, 18)); // NOI18N
        doctor.setForeground(new java.awt.Color(255, 255, 255));
        doctor.setText("Doctor/a");
        doctor.setToolTipText("");

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(261, 261, 261)
                                .addComponent(doctor))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(historiamedica)
                                .addGap(387, 387, 387)
                                .addComponent(Procesar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Salir))
                            .addComponent(movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel5Layout.createSequentialGroup()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(cedul)
                                .addGap(74, 74, 74)
                                .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(tipopac))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addComponent(nomb)
                                .addGap(69, 69, 69)
                                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(apell)))
                        .addGap(6, 6, 6)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Tipopaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(Apellido)))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(doctor)))
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(cedul))
                            .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(tipopac))
                            .addComponent(Tipopaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(nomb))
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(apell)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(movimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Procesar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historiamedica, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(panel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void ProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcesarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProcesarActionPerformed

    private void sintomaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_sintomaFocusGained
        String leyenda = "Describir el síntoma presentado por el paciente...";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_sintomaFocusGained

    private void diagnosticoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_diagnosticoFocusGained
        String leyenda = "Especifíque el diagnóstico encontrado...";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_diagnosticoFocusGained
    public void finalizar(){
      finalizar = new AsignacionesFinales(this,true); 
      finalizar.toFront();
      finalizar.setVisible(true);

    }
    public void Ordenes(){
      Ordens = new Ordenes(this.getAsignaciones(),true); 
      Ordens.toFront();
      Ordens.setVisible(true);

    }
    public JDialog getAsignaciones(){
      return finalizar;
    }
public void recipe(){
  rec = new Vrecipe(this.getAsignaciones(),true);
  setoyentesinternos();
  rec.setVisible(true);
}
public void reposo(){
  repos = new Vreposo(this.getAsignaciones(),true);
  setOyentesinternosreposo();
  repos.setVisible(true);
}
public void salirrecipe(){
  rec.dispose();
}
public void cerrarreposo(){
  repos.dispose();
}
  public JButton getGrabarrecipe() {
    return grabarrecipe;
  }

  public void setGrabarrecipe(JButton grabarrecipe) {
    this.grabarrecipe = grabarrecipe;
  }

  public JTextArea getRecipe1() {
    return recipe1;
  }

  public void setRecipe1(JTextArea recipe1) {
    this.recipe1 = recipe1;
  }

  public JTextArea getIndicaciones() {
    return indicaciones;
  }

  public void setIndicaciones(JTextArea indicaciones) {
    this.indicaciones = indicaciones;
  }

  public JDateChooser getVencrecipe() {
    return vencrecipe;
  }

  public void setVencrecipe(JDateChooser vencrecipe) {
    this.vencrecipe = vencrecipe;
  }

  public JDateChooser getVencindicaciones() {
    return vencindicaciones;
  }

  public void setVencindicaciones(JDateChooser vencindicaciones) {
    this.vencindicaciones = vencindicaciones;
  }

  public JButton getRecip() {
    return recip;
  }

  public void setRecip(JButton recip) {
    this.recip = recip;
  }

  public JButton getGrabarreposo() {
    return grabarreposo;
  }

  public void setGrabarreposo(JButton grabarreposo) {
    this.grabarreposo = grabarreposo;
  }

  public JTextArea getReposo1() {
    return reposo1;
  }

  public void setReposo1(JTextArea reposo1) {
    this.reposo1 = reposo1;
  }

  public JSlider getDiasreposo() {
    return diasreposo;
  }

  public void setDiasreposo(JSlider diasreposo) {
    this.diasreposo = diasreposo;
  }
//******************************************************************************
    // CLASE INTERNA DE ASIGNACIONES FINALES 
    private class AsignacionesFinales extends JDialog{
      
      public AsignacionesFinales(Dialog Parent, boolean modal){
        super(Parent,modal);
        setSize(300,320);
        setResizable(false);
        setModal(true);
        this.setModalityType(Dialog.ModalityType.MODELESS);
        setLocationRelativeTo(null);
        setTitle("Asignaciones de consultas");
        // Creamos la lamina interna.
        Contenedor();
        botones();
        footer();
        botonesfooter();
        add(contenedor);
      }
      private void Contenedor(){
        contenedor = new JPanel(new BorderLayout());
        this.Header(300,50);
        contenedor.add(laminaheader,BorderLayout.NORTH);
      }
      private void Header(int anchura, int altura){
        laminaheader = new JPanel();
        laminaheader.setMinimumSize(new Dimension(anchura,altura));
      }
      private void botones(){
        laminabotones = new JPanel(new GridLayout(3,1));
        
//        ArrayList listbtn = new ArrayList(3);
//        
//        JButton[] bt = new JButton[3];
//       
////        JButton[] opc = new Array(recip,reposo,ordenes);
        crearbotones(getController());
      }
      private void crearbotones(ActionListener Oyente){
        recip = new JButton("RECIPE");
        reposo = new JButton("REPOSO");
        ordenes = new JButton("ORDENES");
        recip.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reposo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ordenes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recip.addActionListener(Oyente);
        reposo.addActionListener(Oyente);
        ordenes.addActionListener(Oyente);
       
        laminabotones.add(recip);
        laminabotones.add(reposo);
        laminabotones.add(ordenes);
        
        contenedor.add(laminabotones,BorderLayout.CENTER);
      }
      private void footer(){
        laminafooter = new JPanel();
        laminafooter.setLayout(new FlowLayout(FlowLayout.RIGHT));

      }
      private void botonesfooter(){
  
        aterminar = new JButton("Finalizar");
        aterminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        aterminar.setSize(new Dimension(120,35));
        aterminar.addActionListener(getController());
        laminafooter.add(aterminar);
        contenedor.add(laminafooter,BorderLayout.SOUTH);
      }
      private JPanel contenedor,laminaheader,laminabotones,laminafooter;
      
    }   
/******************************************************************************/
//   CLASE INTERNA RECIPE 
/******************************************************************************/  
 private class Vrecipe extends JDialog implements ActionListener{
   
   //Constructor de la clase interna
  Vrecipe(JDialog padre, boolean modal){
    setTitle("Preescripción de recipe");
    setMinimumSize(new Dimension(780,382));
    setPreferredSize(new Dimension(780,382));
    setMaximumSize(new Dimension(780,382));
    setResizable(false);
    setModal(modal);
    setLocationRelativeTo(null);
//    this.getContentPane().setLayout(new GridLayout(4,1));
    laminaprincipal();
    add(lamina);
    header();
    movimiento();
    botones();
  }
  
  /*Metodos o funciones*/
  private void  laminaprincipal(){
    lamina = new Panel();
    lamina.setBorder(createBevelBorder(BevelBorder.RAISED));
    lamina.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png")));
    lamina.setMaximumSize(new Dimension(780, 382));
    lamina.setPreferredSize(new Dimension(780, 382));
    lamina.setLayout(null);
  }
  private void header(){
    header = new Panel();
    header.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/header-consulta.png")));
    header.setBounds(0, 0,772, 75);
    lamina.add(header);
  }
  private void movimiento(){    
    movimiento = new JTabbedPane();
    movimiento.setBounds(20, 95, 740, 189);
    lamina.add(movimiento);
    recipe();
    indicaciones();
    
  }
  private void recipe(){
    recipe = new JPanel();
    movimiento.addTab("Recipe", recipe);
    Scroll = new JScrollPane();
    recipe1 = new JTextArea();
    recipe.setLayout(null);
    recipe1.setColumns(20);
    recipe1.setLineWrap(true);
    recipe1.setRows(4);
    recipe1.setToolTipText("Ingrese la preescripción del recipe");
    recipe1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Preescripción de recipe", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
    recipe1.setMargin(new java.awt.Insets(1, 2, 2, 1));
    Scroll.setViewportView(recipe1);
    
    labvencimiento = new JLabel("Vencimiento:");
    labvencimiento.setFont(new java.awt.Font("Dialog", 1, 13));
    labvencimiento.setForeground(Color.BLACK);
    labvencimiento.setBounds(10,10,84, 29);
    recipe.add(labvencimiento);
    
    vencrecipe = new JDateChooser();
    vencrecipe.setMinSelectableDate(new Date());
    vencrecipe.setDateFormatString("dd/MM/yyyy");
    
    vencrecipe.setBounds(97, 10, 200, 29);
    recipe.add(vencrecipe);
    
    Scroll.setBounds(10, 49, 700, 100);
    recipe.add(Scroll);
  }
  private void indicaciones(){
    laminaindicaciones = new JPanel();
    movimiento.addTab("Indicaciones", laminaindicaciones);
    Scroll1 = new JScrollPane();
    Scroll1.setViewportBorder(null);
    indicaciones = new JTextArea();
    laminaindicaciones.setLayout(null);
    indicaciones.setColumns(20);
    indicaciones.setLineWrap(true);
    indicaciones.setRows(4);
    indicaciones.setToolTipText("Ingrese las Indicaciones para este recipe");
    indicaciones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Indicación", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
    indicaciones.setMargin(new java.awt.Insets(1, 2, 2, 1));
    Scroll1.setViewportView(indicaciones);
    
    labvencimiento = new JLabel("Vencimiento:");
    labvencimiento.setFont(new java.awt.Font("Dialog", 1, 13));
    labvencimiento.setForeground(Color.BLACK);
    labvencimiento.setBounds(10,10,84, 18);
    laminaindicaciones.add(labvencimiento);
    
    vencindicaciones = new JDateChooser();
    vencindicaciones.setMinSelectableDate(new Date());
    vencindicaciones.setDateFormatString("dd/MM/yyyy");
    
    vencindicaciones.setBounds(97, 10, 200, 29);
    laminaindicaciones.add(vencindicaciones);
    
    Scroll1.setBounds(10, 49, 700, 100);
    laminaindicaciones.add(Scroll1);
  }
  private void botones(){
    footer = new JPanel();
    footer.setBackground(new Color(0.90f,0.89f,0.89f,0.0f));
    footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
    grabarrecipe = new JButton("Grabar");
    volverrecipe = new JButton("Volver");
    grabarrecipe.setPreferredSize(new Dimension(150,30));
    volverrecipe.setPreferredSize(new Dimension(150,30));
    grabarrecipe.setCursor(new Cursor(Cursor.HAND_CURSOR));
    volverrecipe.setCursor(new Cursor(Cursor.HAND_CURSOR));
    volverrecipe.addActionListener(this);
    grabarrecipe.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png")));
    volverrecipe.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png")));
    grabarrecipe.setIconTextGap(4);
    volverrecipe.setIconTextGap(4);
    grabarrecipe.setBounds(425, 0, 150, 30);
    grabarrecipe.setBounds(580, 0, 150, 30);
   footer.add(grabarrecipe);
   footer.add(volverrecipe);
   footer.setBounds(20, this.getHeight()-80, 740, 40);
   lamina.add(footer);
  }
  public JButton getGrabarrecipe(){
    return grabarrecipe;
  }
  /*Campos */ 
  private Panel lamina,header;
  private JTabbedPane movimiento;
  private JPanel recipe,laminaindicaciones,footer;
  private JScrollPane Scroll,Scroll1;

  private JLabel labvencimiento;

  private JButton volverrecipe;

    @Override
    public void actionPerformed(ActionEvent e) {
    Object evento = e.getSource();
    if(evento.equals(volverrecipe)){
      this.dispose();
    }
    }
 }    
 /******************************************************************************/
//   CLASE INTERNA REPOSO
/******************************************************************************/  
 private class Vreposo extends JDialog implements ActionListener, ChangeListener{
   
   //Constructor de la clase interna
  Vreposo(JDialog padre, boolean modal){
    setTitle("Preescripción del reposo");
    setMinimumSize(new Dimension(780,382));
    setPreferredSize(new Dimension(780,382));
    setMaximumSize(new Dimension(780,382));
    setResizable(false);
    setModal(modal);
    setLocationRelativeTo(null);
//    this.getContentPane().setLayout(new GridLayout(4,1));
    laminaprincipal();
    add(lamina);
    header();
    movimiento();
    botones();
  }
  
  /*Metodos o funciones*/
  private void  laminaprincipal(){
    lamina = new Panel();
    lamina.setBorder(createBevelBorder(BevelBorder.RAISED));
    lamina.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png")));
    lamina.setMaximumSize(new Dimension(780, 382));
    lamina.setPreferredSize(new Dimension(780, 382));
    lamina.setLayout(null);
  }
  private void header(){
    header = new Panel();
    header.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/header-consulta.png")));
    header.setBounds(0, 0,772, 75);
    lamina.add(header);
  }
  private void movimiento(){    
    movimiento = new JTabbedPane();
    movimiento.setBounds(20, 95, 740, 195);
    lamina.add(movimiento);
    reposo();
  }
  private void reposo(){
    reposo = new JPanel();
    movimiento.addTab("Reposo",reposo);
    Scroll = new JScrollPane();
    reposo1 = new JTextArea();
    reposo.setLayout(null);
    reposo1.setColumns(20);
    reposo1.setLineWrap(true);
    reposo1.setRows(4);
    reposo1.setToolTipText("Ingrese la preescripción del reposo");
    reposo1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Motivo del reposo", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));
    reposo1.setMargin(new java.awt.Insets(1, 2, 2, 1));
    Scroll.setViewportView(reposo1);
    
    labvencimiento = new JLabel("Dias de reposo:");
    labvencimiento.setFont(new java.awt.Font("Dialog", 1, 13));
    labvencimiento.setForeground(Color.BLACK);
    labvencimiento.setBounds(10,10,105, 50);
    reposo.add(labvencimiento);
    dia = new JLabel("dias");
    dia.setFont(new java.awt.Font("Dialog", 1, 13));
    dia.setForeground(Color.BLACK);
    dia.setBounds(435,10,200, 50);
    reposo.add(dia);
    diasreposo = new JSlider(1,21,1);
    diasreposo.addChangeListener(this);
    diasreposo.setMajorTickSpacing(1);
    diasreposo.setPaintTicks(true);
    diasreposo.setFont(new Font("Serif",Font.ITALIC,10));
    diasreposo.setPaintLabels(true);
    diasreposo.setSnapToTicks(true);
    diasreposo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    diasreposo.setBounds(115, 10, 300, 50);
    reposo.add(diasreposo);
   
    Scroll.setBounds(10, 65, 700, 70);
    reposo.add(Scroll);
  }
 
  private void botones(){
    footer = new JPanel();
    footer.setBackground(new Color(0.90f,0.89f,0.89f,0.0f));
    footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
    grabarreposo = new JButton("Grabar");
    volverreposo = new JButton("Volver");
    grabarreposo.setPreferredSize(new Dimension(150,30));
    volverreposo.setPreferredSize(new Dimension(150,30));
    grabarreposo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    volverreposo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    volverreposo.addActionListener(this);
    grabarreposo.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png")));
    volverreposo.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png")));
    grabarreposo.setIconTextGap(4);
    volverreposo.setIconTextGap(4);
    grabarreposo.setBounds(425, 0, 150, 30);
    grabarreposo.setBounds(580, 0, 150, 30);
   footer.add(grabarreposo);
   footer.add(volverreposo);
   footer.setBounds(20, 304, 740, 40);
   lamina.add(footer);
  }
  public JButton getGrabarreposo(){
    return grabarreposo;
  }
  /*Campos */ 
  private Panel lamina,header;
  private JTabbedPane movimiento;
  private JPanel reposo,footer;
  private JScrollPane Scroll;

  private JLabel labvencimiento, dia;

  private JButton volverreposo;

    @Override
    public void actionPerformed(ActionEvent e) {
    Object evento = e.getSource();
    if(evento.equals(volverreposo)){
      this.dispose();
    }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
      int dias = getDiasreposo().getValue();
     dia.setText(dias+" Dias de reposo");
    }

  
 }  
  /******************************************************************************/
//   CLASE INTERNA ORDENES
/******************************************************************************/  
 private class Ordenes extends JDialog{
   Ordenes(JDialog padre, boolean modal){
    setTitle("Ordenes de consulta");
    setMinimumSize(new Dimension(780,382));
    setPreferredSize(new Dimension(780,382));
    setMaximumSize(new Dimension(780,382));
    setResizable(false);
    setModal(modal);
    setLocationRelativeTo(null);
//    this.getContentPane().setLayout(new GridLayout(4,1));
    laminaprincipal();
    add(lamina);
    header();
    movimiento();
    footer();
  }
   private void laminaprincipal(){
    lamina = new Panel();
    lamina.setBorder(createBevelBorder(BevelBorder.RAISED));
    lamina.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png")));
    lamina.setMaximumSize(new Dimension(780, 382));
    lamina.setPreferredSize(new Dimension(780, 382));
    lamina.setLayout(new BorderLayout());
   }

    private void header(){
      header = new Panel();
      header.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/header-consulta.png")));
      header.setPreferredSize(new Dimension(772,75));
      header.setMaximumSize(new Dimension(772,75));
      header.setOpaque(false);
      lamina.add(header,BorderLayout.NORTH);
    }
    private void movimiento(){
      center = new Panel();
      center.setOpaque(true);
      
      movimiento = new JTabbedPane();
      movimiento.setBounds(20, 95, 740, 195);
      center.add(movimiento);
      
      lamina.add(center,BorderLayout.CENTER);
    
    }
    private void footer(){
      footer = new Panel();
      footer.setOpaque(false);
      footer.setPreferredSize(new Dimension(772,40));
      footer.setMaximumSize(new Dimension(772,40));
      lamina.add(footer,BorderLayout.SOUTH);
    }
   //Campos de classe..
   Panel lamina, header,footer,center;
   
 }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    public javax.swing.JLabel Leyenda;
    private javax.swing.JTextField Nombre;
    private javax.swing.JList<String> Patologias;
    private javax.swing.JButton Procesar;
    private javax.swing.JButton Salir;
    private javax.swing.JTextField Tipopaciente;
    private javax.swing.JButton anadir;
    private javax.swing.JLabel apell;
    private javax.swing.JButton asignarpatologia;
    private javax.swing.JLabel cedul;
    private javax.swing.JFormattedTextField cedula;
    private javax.swing.JPanel consulta;
    private javax.swing.JButton consultar;
    private javax.swing.JTextArea diagnostico;
    private javax.swing.JLabel doctor;
    private javax.swing.JLabel glicemia;
    private javax.swing.JButton historiamedica;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane movimiento;
    private javax.swing.JLabel nomb;
    private javax.swing.JButton observaciontriaje;
    private org.edisoncor.gui.panel.Panel panel2;
    private org.edisoncor.gui.panel.Panel panel5;
    private javax.swing.JPanel patologias;
    private javax.swing.JLabel peso;
    private javax.swing.JButton quitar;
    private javax.swing.JList<String> seleccionada;
    private javax.swing.JTextArea sintoma;
    private javax.swing.JLabel talla;
    private javax.swing.JLabel temperatura;
    private javax.swing.JLabel tension;
    private javax.swing.JLabel tipopac;
    private javax.swing.JPanel triaje;
    // End of variables declaration//GEN-END:variables
    public JButton asalir,aterminar,reposo,ordenes,grabarrecipe,grabarreposo;
    private JButton recip;
    private JTextArea recipe1,indicaciones, reposo1;
    private JDateChooser vencrecipe,vencindicaciones;
    private JSlider diasreposo;
    private JDialog rec,repos,Ordens;
}
