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

import Controlador.ControladorMorbilidad;
import datechooser.beans.DateChooserCombo;
import java.awt.Dialog;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;


//Getters y setters
    
    
/**
 *
 * @author Jnatn'h
 */
public class Morbilidad extends java.awt.Dialog {

    ControladorMorbilidad controller;
    
    public void setController(ControladorMorbilidad cont){
        this.controller = cont;
        this.asignarListener();
    }
    public ControladorMorbilidad getController(){
        return this.controller;
    }
    private void asignarListener(){
        this.seleccion.addActionListener(controller);
        this.Calendario.addActionListener(controller);
        this.Emitir.addActionListener(controller);        
        this.Salir.addActionListener(controller);
        this.fechafin.addSelectionChangedListener(controller);
    }

    public JButton getEmitir() {
        return Emitir;
    }

    public void setEmitir(JButton Emitir) {
        this.Emitir = Emitir;
    }

    public JComboBox<String> getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(JComboBox<String> seleccion) {
        this.seleccion = seleccion;
    }
                
    public JButton getCalendario() {
        return Calendario;
    }

    public void setCalendario(JButton Calendario) {
        this.Calendario = Calendario;
    }

    public JRadioButton getComunidad() {
        return Comunidad;
    }

    public void setComunidad(JRadioButton Comunidad) {
        this.Comunidad = Comunidad;
    }

    public JRadioButton getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(JRadioButton Empleado) {
        this.Empleado = Empleado;
    }

  public DateChooserCombo getFechafin() {
    return fechafin;
  }

  public void setFechafin(DateChooserCombo fechafin) {
    this.fechafin = fechafin;
  }

  public DateChooserCombo getFechamin() {
    return fechamin;
  }

  public void setFechamin(DateChooserCombo fechamin) {
    this.fechamin = fechamin;
  }
  
    public JLabel getLeyenda() {
        return Leyenda;
    }

    public void setLeyenda(JLabel Leyenda) {
        this.Leyenda = Leyenda;
    }

    public JPanel getPacvistos() {
        return Pacvistos;
    }

    public void setPacvistos(JPanel Pacvistos) {
        this.Pacvistos = Pacvistos;
    }


    public JButton getSalir() {
        return Salir;
    }

    public void setSalir(JButton Salir) {
        this.Salir = Salir;
    }

    public JRadioButton getTodas() {
        return Todas;
    }

    public void setTodas(JRadioButton Todas) {
        this.Todas = Todas;
    }

    public JRadioButton getCita() {
        return cita;
    }

    public void setCita(JRadioButton cita) {
        this.cita = cita;
    }

    public JRadioButton getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(JRadioButton emergencia) {
        this.emergencia = emergencia;
    }

    public JRadioButton getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(JRadioButton estudiante) {
        this.estudiante = estudiante;
    }

    public ButtonGroup getFecha() {
        return fecha;
    }

    public void setFecha(ButtonGroup fecha) {
        this.fecha = fecha;
    }

    public JTabbedPane getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(JTabbedPane movimiento) {
        this.movimiento = movimiento;
    }

    public JPanel getPri_patologia() {
        return pri_patologia;
    }

    public void setPri_patologia(JPanel pri_patologia) {
        this.pri_patologia = pri_patologia;
    }

    public ButtonGroup getTipodeservicio() {
        return tipodeservicio;
    }

    public void setTipodeservicio(ButtonGroup tipodeservicio) {
        this.tipodeservicio = tipodeservicio;
    }

    public ButtonGroup getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(ButtonGroup tipopersona) {
        this.tipopersona = tipopersona;
    }

    public JRadioButton getTodos() {
        return todos;
    }

    public void setTodos(JRadioButton todos) {
        this.todos = todos;
    }

    
    /**
     * Creates new form Morbilidad
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Morbilidad(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setModalityType(Dialog.ModalityType.MODELESS);
        setResizable(false);
        setTitle("Reporte de Morbilidad");
        setLocationRelativeTo(null);
        setController(new ControladorMorbilidad(this));
        Locale miregion = new Locale("es","VE");
        TimeZone tz = TimeZone.getTimeZone("América / Caracas");
        Calendar fechamax = new GregorianCalendar(tz,miregion) ;
        fechamax.setTime(new Date());
        Calendar fechamin = new GregorianCalendar(tz,miregion);
        this.fechamin.setMaxDate(fechamax);
        this.fechafin.setMinDate(fechamin);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    tipopersona = new javax.swing.ButtonGroup();
    tipodeservicio = new javax.swing.ButtonGroup();
    fecha = new javax.swing.ButtonGroup();
    panel5 = new org.edisoncor.gui.panel.Panel();
    header = new org.edisoncor.gui.panel.Panel();
    footer = new javax.swing.JPanel();
    Leyenda = new javax.swing.JLabel();
    headfooter = new javax.swing.JPanel();
    Emitir = new javax.swing.JButton();
    Salir = new javax.swing.JButton();
    center = new javax.swing.JPanel();
    dcenter = new javax.swing.JPanel();
    jLabel11 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jSeparator2 = new javax.swing.JSeparator();
    seleccion = new javax.swing.JComboBox<>();
    movimiento = new javax.swing.JTabbedPane();
    Pacvistos = new javax.swing.JPanel();
    jLabel17 = new javax.swing.JLabel();
    jLabel18 = new javax.swing.JLabel();
    Empleado = new javax.swing.JRadioButton();
    Comunidad = new javax.swing.JRadioButton();
    estudiante = new javax.swing.JRadioButton();
    jLabel1 = new javax.swing.JLabel();
    Todas = new javax.swing.JRadioButton();
    emergencia = new javax.swing.JRadioButton();
    cita = new javax.swing.JRadioButton();
    todos = new javax.swing.JRadioButton();
    jLabel19 = new javax.swing.JLabel();
    Calendario = new javax.swing.JButton();
    pri_patologia = new javax.swing.JPanel();
    peso = new javax.swing.JLabel();
    fechamin = new datechooser.beans.DateChooserCombo();
    fechafin = new datechooser.beans.DateChooserCombo();

    setMaximumSize(new java.awt.Dimension(708, 452));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });

    panel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
    panel5.setMaximumSize(new java.awt.Dimension(708, 452));
    panel5.setPreferredSize(new java.awt.Dimension(708, 452));
    panel5.setLayout(new java.awt.BorderLayout());

    header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/morbilidad.png"))); // NOI18N
    header.setMaximumSize(new java.awt.Dimension(708, 75));
    header.setName(""); // NOI18N
    header.setPreferredSize(new java.awt.Dimension(708, 75));

    javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
    header.setLayout(headerLayout);
    headerLayout.setHorizontalGroup(
      headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    headerLayout.setVerticalGroup(
      headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );

    panel5.add(header, java.awt.BorderLayout.NORTH);

    footer.setOpaque(false);
    footer.setPreferredSize(new java.awt.Dimension(600, 78));
    footer.setVerifyInputWhenFocusTarget(false);
    footer.setLayout(new java.awt.BorderLayout());

    Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    Leyenda.setForeground(new java.awt.Color(255, 255, 255));
    Leyenda.setPreferredSize(new java.awt.Dimension(0, 30));
    footer.add(Leyenda, java.awt.BorderLayout.CENTER);

    headfooter.setOpaque(false);
    headfooter.setPreferredSize(new java.awt.Dimension(600, 40));

    Emitir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/reporte.png"))); // NOI18N
    Emitir.setText("Emitir");
    Emitir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Emitir.setEnabled(false);
    Emitir.setPreferredSize(new java.awt.Dimension(200, 30));
    headfooter.add(Emitir);

    Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
    Salir.setText("Salir");
    Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Salir.setPreferredSize(new java.awt.Dimension(200, 30));
    headfooter.add(Salir);

    footer.add(headfooter, java.awt.BorderLayout.NORTH);

    panel5.add(footer, java.awt.BorderLayout.SOUTH);

    center.setOpaque(false);
    center.setPreferredSize(new java.awt.Dimension(708, 75));
    java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 60, 20);
    flowLayout1.setAlignOnBaseline(true);
    center.setLayout(flowLayout1);

    dcenter.setOpaque(false);
    dcenter.setPreferredSize(new java.awt.Dimension(600, 70));
    dcenter.setLayout(new java.awt.BorderLayout(0, 3));

    jLabel11.setFont(new java.awt.Font("HelveticaLT", 1, 24)); // NOI18N
    jLabel11.setForeground(new java.awt.Color(255, 255, 255));
    jLabel11.setText("Reporte de Morbilidad");
    jLabel11.setToolTipText("");
    jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    jLabel11.setIconTextGap(0);
    jLabel11.setMaximumSize(new java.awt.Dimension(600, 30));
    jLabel11.setMinimumSize(new java.awt.Dimension(600, 30));
    jLabel11.setPreferredSize(new java.awt.Dimension(600, 30));
    dcenter.add(jLabel11, java.awt.BorderLayout.NORTH);

    jPanel1.setOpaque(false);
    jPanel1.setLayout(new java.awt.BorderLayout(0, 5));

    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("Reporte:");
    jPanel1.add(jLabel3, java.awt.BorderLayout.WEST);
    jPanel1.add(jSeparator2, java.awt.BorderLayout.NORTH);

    seleccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Pacientes Vistos", "10 Principales Patologías" }));
    jPanel1.add(seleccion, java.awt.BorderLayout.CENTER);

    dcenter.add(jPanel1, java.awt.BorderLayout.CENTER);

    center.add(dcenter);

    movimiento.setEnabled(false);

    Pacvistos.setEnabled(false);
    Pacvistos.setPreferredSize(new java.awt.Dimension(600, 134));

    jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    jLabel17.setForeground(new java.awt.Color(0, 0, 0));
    jLabel17.setText("Filtrar:");

    jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    jLabel18.setForeground(new java.awt.Color(0, 0, 0));
    jLabel18.setText("Tipo de servicio");

    tipopersona.add(Empleado);
    Empleado.setForeground(new java.awt.Color(0, 0, 0));
    Empleado.setText("Empleado");
    Empleado.setEnabled(false);

    tipopersona.add(Comunidad);
    Comunidad.setForeground(new java.awt.Color(0, 0, 0));
    Comunidad.setText("Comunidad");
    Comunidad.setEnabled(false);

    tipopersona.add(estudiante);
    estudiante.setForeground(new java.awt.Color(0, 0, 0));
    estudiante.setText("Estudiante");
    estudiante.setEnabled(false);

    jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(0, 0, 0));
    jLabel1.setText("Tipo de paciente");

    tipopersona.add(Todas);
    Todas.setForeground(new java.awt.Color(0, 0, 0));
    Todas.setText("Todas");
    Todas.setEnabled(false);

    tipodeservicio.add(emergencia);
    emergencia.setForeground(new java.awt.Color(0, 0, 0));
    emergencia.setText("Emergencia");
    emergencia.setEnabled(false);

    tipodeservicio.add(cita);
    cita.setForeground(new java.awt.Color(0, 0, 0));
    cita.setText("Cita");
    cita.setEnabled(false);

    tipodeservicio.add(todos);
    todos.setForeground(new java.awt.Color(0, 0, 0));
    todos.setText("Todas");
    todos.setEnabled(false);

    jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    jLabel19.setForeground(new java.awt.Color(0, 0, 0));
    jLabel19.setText("Fecha");

    Calendario.setText("Rango");
    Calendario.setEnabled(false);

    javax.swing.GroupLayout PacvistosLayout = new javax.swing.GroupLayout(Pacvistos);
    Pacvistos.setLayout(PacvistosLayout);
    PacvistosLayout.setHorizontalGroup(
      PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PacvistosLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel17)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(Todas)
          .addComponent(estudiante)
          .addComponent(Comunidad)
          .addComponent(Empleado))
        .addGap(39, 39, 39)
        .addGroup(PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(todos)
          .addComponent(cita)
          .addComponent(emergencia)
          .addComponent(jLabel18))
        .addGap(36, 36, 36)
        .addGroup(PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel19)
          .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(90, Short.MAX_VALUE))
    );
    PacvistosLayout.setVerticalGroup(
      PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(PacvistosLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jLabel18)
          .addComponent(jLabel19))
        .addGap(5, 5, 5)
        .addGroup(PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(PacvistosLayout.createSequentialGroup()
            .addGroup(PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(Empleado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(Comunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(estudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(Todas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(PacvistosLayout.createSequentialGroup()
            .addGroup(PacvistosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(PacvistosLayout.createSequentialGroup()
                .addComponent(emergencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cita, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addComponent(Calendario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(todos, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    movimiento.addTab("Paciente Vistos", Pacvistos);

    pri_patologia.setEnabled(false);

    peso.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    peso.setForeground(new java.awt.Color(0, 0, 0));
    peso.setText("Rango");
    peso.setToolTipText("");

    fechamin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED,
      (java.awt.Color)null,
      (java.awt.Color)null),
    "Fecha InicIal",
    javax.swing.border.TitledBorder.LEADING,
    javax.swing.border.TitledBorder.DEFAULT_POSITION ,
    new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
    new java.awt.Color(0, 0, 0)));
fechamin.setCalendarPreferredSize(new java.awt.Dimension(340, 200));
fechamin.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
try {
  fechamin.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
  } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
    e1.printStackTrace();
  }
  fechamin.setEnabled(false);
  fechamin.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));

  fechafin.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED,
    (java.awt.Color)null,
    (java.awt.Color)null),
  "Fecha Final",
  javax.swing.border.TitledBorder.LEFT,
  javax.swing.border.TitledBorder.ABOVE_TOP,
  new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12),
  new java.awt.Color(0, 0, 0)));
  fechafin.setCalendarPreferredSize(new java.awt.Dimension(340, 200));
  try {
    fechafin.setDefaultPeriods(new datechooser.model.multiple.PeriodSet());
  } catch (datechooser.model.exeptions.IncompatibleDataExeption e1) {
    e1.printStackTrace();
  }
  fechafin.setEnabled(false);
  fechafin.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 10));

  javax.swing.GroupLayout pri_patologiaLayout = new javax.swing.GroupLayout(pri_patologia);
  pri_patologia.setLayout(pri_patologiaLayout);
  pri_patologiaLayout.setHorizontalGroup(
    pri_patologiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(pri_patologiaLayout.createSequentialGroup()
      .addGap(17, 17, 17)
      .addGroup(pri_patologiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(peso, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(pri_patologiaLayout.createSequentialGroup()
          .addComponent(fechamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGap(18, 18, 18)
          .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
      .addContainerGap(255, Short.MAX_VALUE))
  );
  pri_patologiaLayout.setVerticalGroup(
    pri_patologiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(pri_patologiaLayout.createSequentialGroup()
      .addContainerGap()
      .addComponent(peso)
      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
      .addGroup(pri_patologiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
        .addComponent(fechamin, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(fechafin, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
      .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
  );

  movimiento.addTab("10 Primeras Patologías", pri_patologia);

  center.add(movimiento);
  movimiento.getAccessibleContext().setAccessibleName("a");

  panel5.add(center, java.awt.BorderLayout.LINE_START);

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
  this.setLayout(layout);
  layout.setHorizontalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
  );
  layout.setVerticalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
  );

  pack();
  }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
        
    }//GEN-LAST:event_closeDialog



  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Calendario;
  private javax.swing.JRadioButton Comunidad;
  private javax.swing.JButton Emitir;
  private javax.swing.JRadioButton Empleado;
  public javax.swing.JLabel Leyenda;
  private javax.swing.JPanel Pacvistos;
  private javax.swing.JButton Salir;
  private javax.swing.JRadioButton Todas;
  private javax.swing.JPanel center;
  private javax.swing.JRadioButton cita;
  private javax.swing.JPanel dcenter;
  private javax.swing.JRadioButton emergencia;
  private javax.swing.JRadioButton estudiante;
  private javax.swing.ButtonGroup fecha;
  private datechooser.beans.DateChooserCombo fechafin;
  private datechooser.beans.DateChooserCombo fechamin;
  private javax.swing.JPanel footer;
  private org.edisoncor.gui.panel.Panel header;
  private javax.swing.JPanel headfooter;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel17;
  private javax.swing.JLabel jLabel18;
  private javax.swing.JLabel jLabel19;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JTabbedPane movimiento;
  private org.edisoncor.gui.panel.Panel panel5;
  private javax.swing.JLabel peso;
  private javax.swing.JPanel pri_patologia;
  private javax.swing.JComboBox<String> seleccion;
  private javax.swing.ButtonGroup tipodeservicio;
  private javax.swing.ButtonGroup tipopersona;
  private javax.swing.JRadioButton todos;
  // End of variables declaration//GEN-END:variables

    
}
