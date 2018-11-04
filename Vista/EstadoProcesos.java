/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorEstadoProceso;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Jnatn'h
 */
public class EstadoProcesos extends java.awt.Dialog {
  private ControladorEstadoProceso controlador;

  public ControladorEstadoProceso getControlador() {
    return controlador;
  }

  public void setControlador(ControladorEstadoProceso controlador) {
    this.controlador = controlador;
    setListener();
  }
  private void setListener(){
    getGrabar().addActionListener(getControlador());
    getModificar().addActionListener(getControlador());
    getEliminar().addActionListener(getControlador());
    getSalir().addActionListener(getControlador());
    getEncurso().addActionListener(getControlador());
    getFinalizado().addActionListener(getControlador());
    getEncurso().addMouseListener(getControlador());
    getFinalizado().addMouseListener(getControlador());
  }
  public JButton getEliminar() {
    return Eliminar;
  }

  public void setEliminar(JButton Eliminar) {
    this.Eliminar = Eliminar;
  }

  public JButton getGrabar() {
    return Grabar;
  }

  public void setGrabar(JButton Grabar) {
    this.Grabar = Grabar;
  }

  public JLabel getLeyenda() {
    return Leyenda;
  }

  public void setLeyenda(JLabel Leyenda) {
    this.Leyenda = Leyenda;
  }

  public JButton getModificar() {
    return Modificar;
  }

  public void setModificar(JButton Modificar) {
    this.Modificar = Modificar;
  }

  public JButton getSalir() {
    return Salir;
  }

  public void setSalir(JButton Salir) {
    this.Salir = Salir;
  }

  public JRadioButton getEncurso() {
    return encurso;
  }

  public void setEncurso(JRadioButton encurso) {
    this.encurso = encurso;
  }

  public JRadioButton getFinalizado() {
    return finalizado;
  }

  public void setFinalizado(JRadioButton finalizado) {
    this.finalizado = finalizado;
  }

  public ButtonGroup getCondiciones() {
    return condiciones;
  }

  public void setCondiciones(ButtonGroup condiciones) {
    this.condiciones = condiciones;
  }

  public JTextField getEstado() {
    return estado;
  }

  public void setEstado(JTextField estado) {
    this.estado = estado;
  }
  
  /**
   * Creates new form EstadoProcesos
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
   */
  public EstadoProcesos(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setTitle("Estado de procesos");
    setResizable(false);
    setLocationRelativeTo(null);
//    setControlador(new ControladorEstadoProceso(this));
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    condiciones = new javax.swing.ButtonGroup();
    panel = new org.edisoncor.gui.panel.Panel();
    header = new org.edisoncor.gui.panel.Panel();
    estado = new javax.swing.JTextField();
    Grabar = new javax.swing.JButton();
    Modificar = new javax.swing.JButton();
    Eliminar = new javax.swing.JButton();
    Salir = new javax.swing.JButton();
    jLabel5 = new javax.swing.JLabel();
    Leyenda = new javax.swing.JLabel();
    encurso = new javax.swing.JRadioButton();
    finalizado = new javax.swing.JRadioButton();
    jLabel1 = new javax.swing.JLabel();

    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });
    setLayout(new java.awt.CardLayout());

    panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
    panel.setMaximumSize(new java.awt.Dimension(700, 245));
    panel.setMinimumSize(new java.awt.Dimension(700, 245));
    panel.setPreferredSize(new java.awt.Dimension(700, 245));

    header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/headerestadoproceso.png"))); // NOI18N
    header.setMaximumSize(new java.awt.Dimension(700, 75));
    header.setMinimumSize(new java.awt.Dimension(700, 75));

    javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
    header.setLayout(headerLayout);
    headerLayout.setHorizontalGroup(
      headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 0, Short.MAX_VALUE)
    );
    headerLayout.setVerticalGroup(
      headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 75, Short.MAX_VALUE)
    );

    estado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    estado.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        estadoFocusGained(evt);
      }
    });
    estado.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        estadoKeyPressed(evt);
      }
      public void keyTyped(java.awt.event.KeyEvent evt) {
        estadoKeyTyped(evt);
      }
    });

    Grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
    Grabar.setText("Registrar");
    Grabar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    Grabar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Grabar.setMaximumSize(new java.awt.Dimension(112, 30));
    Grabar.setMinimumSize(new java.awt.Dimension(112, 30));
    Grabar.setPreferredSize(new java.awt.Dimension(112, 30));

    Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/modificar.png"))); // NOI18N
    Modificar.setText("Modificar");
    Modificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Modificar.setMaximumSize(new java.awt.Dimension(112, 30));
    Modificar.setMinimumSize(new java.awt.Dimension(112, 30));
    Modificar.setPreferredSize(new java.awt.Dimension(112, 30));

    Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/eliminar.png"))); // NOI18N
    Eliminar.setText("Eliminar");
    Eliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Eliminar.setMaximumSize(new java.awt.Dimension(112, 30));
    Eliminar.setMinimumSize(new java.awt.Dimension(112, 30));
    Eliminar.setPreferredSize(new java.awt.Dimension(112, 30));

    Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
    Salir.setText("Salir");
    Salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    Salir.setMaximumSize(new java.awt.Dimension(112, 30));
    Salir.setMinimumSize(new java.awt.Dimension(112, 30));
    Salir.setPreferredSize(new java.awt.Dimension(112, 30));

    jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(255, 255, 255));
    jLabel5.setText("Nombre:");

    Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    Leyenda.setForeground(new java.awt.Color(254, 254, 254));

    condiciones.add(encurso);
    encurso.setForeground(new java.awt.Color(255, 255, 255));
    encurso.setText("En curso");
    encurso.setOpaque(false);
    encurso.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        encursoFocusGained(evt);
      }
    });

    condiciones.add(finalizado);
    finalizado.setForeground(new java.awt.Color(255, 255, 255));
    finalizado.setText("Finalizado");
    finalizado.setOpaque(false);
    finalizado.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        finalizadoFocusGained(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Condiciones");

    javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
    panel.setLayout(panelLayout);
    panelLayout.setHorizontalGroup(
      panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
      .addGroup(panelLayout.createSequentialGroup()
        .addGap(39, 39, 39)
        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(19, 19, 19)
        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(panelLayout.createSequentialGroup()
            .addComponent(Grabar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(panelLayout.createSequentialGroup()
            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(panelLayout.createSequentialGroup()
                .addComponent(encurso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finalizado))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)))))
        .addGap(69, 69, Short.MAX_VALUE))
      .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    panelLayout.setVerticalGroup(
      panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(estado, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(encurso)
            .addComponent(finalizado)))
        .addGap(18, 18, 18)
        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(Grabar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(19, 19, 19))
    );

    add(panel, "card2");

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Closes the dialog
   */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
      setVisible(false);
      dispose();
    }//GEN-LAST:event_closeDialog

  private void estadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_estadoFocusGained
    String leyen = "Ingrese o modifique el estado del proceso";
    this.getLeyenda().setText(leyen);
    this.getLeyenda().setForeground(Color.white);
  }//GEN-LAST:event_estadoFocusGained

  private void estadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_estadoKeyPressed
    // TODO add your handling code here:
    char a = evt.getKeyChar();
    if (KeyEvent.VK_PASTE == a){
      evt.consume();
  }//GEN-LAST:event_estadoKeyPressed
  }
  private void estadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_estadoKeyTyped
    this.validarcampo(evt);
  }//GEN-LAST:event_estadoKeyTyped

  private void encursoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_encursoFocusGained
     String leyen = "Seleccione En curso solo si la condicion del estado no es final";
    this.getLeyenda().setText(leyen);
    this.getLeyenda().setForeground(Color.white);
  }//GEN-LAST:event_encursoFocusGained

  private void finalizadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_finalizadoFocusGained
     String leyen = "Seleccione Finalizado solo y solo si la condicion del estado que esta agregando es final";
    this.getLeyenda().setText(leyen);
    this.getLeyenda().setForeground(Color.white);
  }//GEN-LAST:event_finalizadoFocusGained
 private void validarcampo(KeyEvent e){
     char b = e.getKeyChar();
         if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE ){
            e.consume();
    }     
         if( this.getEstado().getText().length() >= 50){
        e.consume();
    }
}



  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Eliminar;
  private javax.swing.JButton Grabar;
  public javax.swing.JLabel Leyenda;
  private javax.swing.JButton Modificar;
  private javax.swing.JButton Salir;
  private javax.swing.ButtonGroup condiciones;
  private javax.swing.JRadioButton encurso;
  private javax.swing.JTextField estado;
  private javax.swing.JRadioButton finalizado;
  private org.edisoncor.gui.panel.Panel header;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel5;
  private org.edisoncor.gui.panel.Panel panel;
  // End of variables declaration//GEN-END:variables
}
