/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorServicio;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Jnatn'h
 */
public class ProcesoEstado extends java.awt.Dialog {

  ControladorServicio controller;
  public void setController(ControladorServicio controller){
    this.controller = controller;
    this.asignarlistener();
  }
  public ControladorServicio getController(){
    return this.controller;
  }
  private void asignarlistener(){
    estadoopcion.addItemListener(getController());
    grabar.addActionListener(getController());
  }

  public JLabel getLeyenda() {
    return Leyenda;
  }

  public void setLeyenda(JLabel Leyenda) {
    this.Leyenda = Leyenda;
  }

  public JFormattedTextField getCedula() {
    return cedula;
  }

  public void setCedula(JFormattedTextField cedula) {
    this.cedula = cedula;
  }

  public JComboBox<String> getEstadoopcion() {
    return estadoopcion;
  }

  public void setEstadoopcion(JComboBox<String> estadoopcion) {
    this.estadoopcion = estadoopcion;
  }

  public JButton getGrabar() {
    return grabar;
  }

  public void setGrabar(JButton grabar) {
    this.grabar = grabar;
  }

  public JTextField getNombr() {
    return nombr;
  }

  public void setNombr(JTextField nombr) {
    this.nombr = nombr;
  }

  public JTextField getOpcionadmin() {
    return opcionadmin;
  }

  public void setOpcionadmin(JTextField opcionadmin) {
    this.opcionadmin = opcionadmin;
  }

  public JButton getSalir() {
    return salir;
  }

  public void setSalir(JButton salir) {
    this.salir = salir;
  }

  public JTextField getTipopaciente() {
    return tipopaciente;
  }

  public void setTipopaciente(JTextField tipopaciente) {
    this.tipopaciente = tipopaciente;
  }
  
  /**
   * Creates new form ProcesoEstado
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
   */
  public ProcesoEstado(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setTitle("Opción administrativa");
    setResizable(false);
    setLocationRelativeTo(null);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    panel1 = new org.edisoncor.gui.panel.Panel();
    panel2 = new org.edisoncor.gui.panel.Panel();
    nombr = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    Leyenda = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    cedula = new javax.swing.JFormattedTextField();
    jLabel3 = new javax.swing.JLabel();
    tipopaciente = new javax.swing.JTextField();
    jSeparator1 = new javax.swing.JSeparator();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    estadoopcion = new javax.swing.JComboBox<>();
    grabar = new javax.swing.JButton();
    opcionadmin = new javax.swing.JTextField();
    salir = new javax.swing.JButton();

    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });

    panel1.setForeground(new java.awt.Color(255, 255, 255));
    panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
    panel1.setMaximumSize(new java.awt.Dimension(830, 297));
    panel1.setMinimumSize(new java.awt.Dimension(830, 297));
    panel1.setPreferredSize(new java.awt.Dimension(830, 297));

    panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/headerestadosolicitud.png"))); // NOI18N
    panel2.setMaximumSize(new java.awt.Dimension(642, 75));
    panel2.setPreferredSize(new java.awt.Dimension(642, 75));

    javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
    panel2.setLayout(panel2Layout);
    panel2Layout.setHorizontalGroup(
      panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 830, Short.MAX_VALUE)
    );
    panel2Layout.setVerticalGroup(
      panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 75, Short.MAX_VALUE)
    );

    nombr.setForeground(new java.awt.Color(0, 0, 0));
    nombr.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    nombr.setDisabledTextColor(new java.awt.Color(0, 0, 0));
    nombr.setEnabled(false);
    nombr.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        nombrFocusGained(evt);
      }
    });
    nombr.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        nombrKeyTyped(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Cédula:");

    Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    Leyenda.setForeground(new java.awt.Color(255, 255, 255));

    jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Opción administrativa:");

    cedula.setForeground(new java.awt.Color(0, 0, 0));
    try {
      cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("V-##.###.###")));
    } catch (java.text.ParseException ex) {
      ex.printStackTrace();
    }
    cedula.setDisabledTextColor(new java.awt.Color(0, 0, 0));
    cedula.setEnabled(false);

    jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setText("Tipo de paciente:");

    tipopaciente.setForeground(new java.awt.Color(0, 0, 0));
    tipopaciente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    tipopaciente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
    tipopaciente.setEnabled(false);
    tipopaciente.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        tipopacienteFocusGained(evt);
      }
    });
    tipopaciente.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        tipopacienteKeyTyped(evt);
      }
    });

    jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
    jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
    jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

    jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setText("Paciente:");

    jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(255, 255, 255));
    jLabel5.setText("Nombre:");

    estadoopcion.setForeground(new java.awt.Color(0, 0, 0));
    estadoopcion.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        estadoopcionFocusGained(evt);
      }
    });

    grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
    grabar.setText("Grabar");
    grabar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    grabar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    grabar.setEnabled(false);

    opcionadmin.setForeground(new java.awt.Color(0, 0, 0));
    opcionadmin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    opcionadmin.setDisabledTextColor(new java.awt.Color(0, 0, 0));
    opcionadmin.setEnabled(false);
    opcionadmin.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        opcionadminFocusGained(evt);
      }
    });
    opcionadmin.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        opcionadminKeyTyped(evt);
      }
    });

    salir.setText("Salir");
    salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    salir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        salirActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
    panel1.setLayout(panel1Layout);
    panel1Layout.setHorizontalGroup(
      panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
      .addGroup(panel1Layout.createSequentialGroup()
        .addGap(60, 60, 60)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(panel1Layout.createSequentialGroup()
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tipopaciente))
          .addGroup(panel1Layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(68, 68, 68)
            .addComponent(cedula))
          .addGroup(panel1Layout.createSequentialGroup()
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
            .addComponent(nombr, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(estadoopcion, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(panel1Layout.createSequentialGroup()
            .addComponent(grabar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addComponent(opcionadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(panel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    panel1Layout.setVerticalGroup(
      panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(16, 16, 16)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(panel1Layout.createSequentialGroup()
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(nombr, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel1)
              .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(tipopaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
          .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(panel1Layout.createSequentialGroup()
            .addComponent(opcionadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(estadoopcion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(grabar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addGap(18, 18, 18)
        .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(160, 160, 160))
    );

    estadoopcion.getAccessibleContext().setAccessibleName("");

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

  private void nombrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombrFocusGained
   
  }//GEN-LAST:event_nombrFocusGained

  private void nombrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombrKeyTyped
//    this.validarcampo(evt);
  }//GEN-LAST:event_nombrKeyTyped

  private void tipopacienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipopacienteFocusGained
    // TODO add your handling code here:
  }//GEN-LAST:event_tipopacienteFocusGained

  private void tipopacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipopacienteKeyTyped
    // TODO add your handling code here:
  }//GEN-LAST:event_tipopacienteKeyTyped

    private void opcionadminFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_opcionadminFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionadminFocusGained

  private void opcionadminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_opcionadminKeyTyped
    // TODO add your handling code here:
  }//GEN-LAST:event_opcionadminKeyTyped

  private void estadoopcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_estadoopcionFocusGained
     String leyen = "Seleccion el Status de la opción administrativa a cambiar";
    this.getLeyenda().setText(leyen);
    this.getLeyenda().setForeground(Color.white);
  }//GEN-LAST:event_estadoopcionFocusGained

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
       this.dispose();
    }//GEN-LAST:event_salirActionPerformed



  // Variables declaration - do not modify//GEN-BEGIN:variables
  public javax.swing.JLabel Leyenda;
  private javax.swing.JFormattedTextField cedula;
  private javax.swing.JComboBox<String> estadoopcion;
  private javax.swing.JButton grabar;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JTextField nombr;
  private javax.swing.JTextField opcionadmin;
  private org.edisoncor.gui.panel.Panel panel1;
  private org.edisoncor.gui.panel.Panel panel2;
  private javax.swing.JButton salir;
  private javax.swing.JTextField tipopaciente;
  // End of variables declaration//GEN-END:variables
}
