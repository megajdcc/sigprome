/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import Controlador.ControladorOpcAdministrativas;
import java.awt.event.KeyEvent;
/**
 *
 * @author Jnatn'h
 */
public class Opcadministrativas extends java.awt.Dialog {

  private ControladorOpcAdministrativas controller;
  
  public void setController(ControladorOpcAdministrativas controller){
    this.controller = controller;
    this.asignarControll();
  }
  public ControladorOpcAdministrativas getController(){
    return controller;
  }
  private void asignarControll(){
   getBusqueda().addKeyListener(controller);
   getOpcadmins().addMouseListener(controller);
   getRegistrar().addActionListener(controller);
   getSalircat().addActionListener(controller);
  }
  public JLabel getLeyenda() {
    return Leyenda;
  }

  public void setLeyenda(JLabel Leyenda) {
    this.Leyenda = Leyenda;
  }

  public JTextField getBusqueda() {
    return busqueda;
  }

  public void setBusqueda(JTextField busqueda) {
    this.busqueda = busqueda;
  }

  public JTable getOpcadmins() {
    return opcadmins;
  }

  public void setOpcadmins(JTable opcadmins) {
    this.opcadmins = opcadmins;
  }

  public JButton getRegistrar() {
    return registrar;
  }

  public void setRegistrar(JButton registrar) {
    this.registrar = registrar;
  }

  public JButton getSalircat() {
    return salircat;
  }

  public void setSalircat(JButton salircat) {
    this.salircat = salircat;
  }

  
  /**
   * Creates new form Opcadministrativas
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
   */
  public Opcadministrativas(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();
    setTitle("Opciones de Servicio administrativo");
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setController(new ControladorOpcAdministrativas(this));
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    cat1 = new org.edisoncor.gui.panel.Panel();
    busqueda = new javax.swing.JTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    opcadmins = new javax.swing.JTable();
    salircat = new javax.swing.JButton();
    panel2 = new org.edisoncor.gui.panel.Panel();
    Leyenda = new javax.swing.JLabel();
    registrar = new javax.swing.JButton();

    setMaximumSize(new java.awt.Dimension(412, 396));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });

    cat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
    cat1.setMaximumSize(new java.awt.Dimension(412, 396));
    cat1.setName("Potencia"); // NOI18N
    cat1.setPreferredSize(new java.awt.Dimension(412, 396));

    busqueda.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        busquedaFocusGained(evt);
      }
    });
    busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        busquedaKeyReleased(evt);
      }
      public void keyTyped(java.awt.event.KeyEvent evt) {
        busquedaKeyTyped(evt);
      }
    });

    opcadmins.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
    opcadmins.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {""},
        {""},
        {""},
        {null},
        {""},
        {null},
        {null},
        {null},
        {null},
        {null}
      },
      new String [] {
        "Opciones Administrativas"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    opcadmins.setToolTipText("");
    opcadmins.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    opcadmins.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        opcadminsKeyReleased(evt);
      }
      public void keyTyped(java.awt.event.KeyEvent evt) {
        opcadminsKeyTyped(evt);
      }
    });
    jScrollPane1.setViewportView(opcadmins);

    salircat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
    salircat.setText("Salir");
    salircat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    salircat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/opcionesadministrativas-header.png"))); // NOI18N
    panel2.setMaximumSize(new java.awt.Dimension(410, 75));
    panel2.setMinimumSize(new java.awt.Dimension(410, 75));

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

    Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
    Leyenda.setForeground(new java.awt.Color(255, 255, 255));

    registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/registrar.png"))); // NOI18N
    registrar.setText("Nuevo");
    registrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    javax.swing.GroupLayout cat1Layout = new javax.swing.GroupLayout(cat1);
    cat1.setLayout(cat1Layout);
    cat1Layout.setHorizontalGroup(
      cat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(cat1Layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addGroup(cat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(cat1Layout.createSequentialGroup()
            .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(116, 116, 116)
            .addComponent(salircat, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
          .addComponent(busqueda)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        .addContainerGap(21, Short.MAX_VALUE))
      .addGroup(cat1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    cat1Layout.setVerticalGroup(
      cat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(cat1Layout.createSequentialGroup()
        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(cat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(salircat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        .addGap(12, 12, 12))
    );

    add(cat1, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Closes the dialog
   */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
      setVisible(false);
      dispose();
    }//GEN-LAST:event_closeDialog

  private void busquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_busquedaFocusGained
    String Leyend = "Busque las opciones administrativas";
    this.getLeyenda().setText(Leyend);
  }//GEN-LAST:event_busquedaFocusGained

  private void busquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyReleased

  }//GEN-LAST:event_busquedaKeyReleased
 private void validar (KeyEvent e)
    {
        char b = e.getKeyChar();
         if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE){
            e.consume();
         }
    }
  private void busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyTyped
    this.validar(evt);
  }//GEN-LAST:event_busquedaKeyTyped

  private void opcadminsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_opcadminsKeyReleased
    // TODO add your handling code here:
  }//GEN-LAST:event_opcadminsKeyReleased

  private void opcadminsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_opcadminsKeyTyped
    // TODO add your handling code here:
  }//GEN-LAST:event_opcadminsKeyTyped

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel Leyenda;
  private javax.swing.JTextField busqueda;
  private org.edisoncor.gui.panel.Panel cat1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable opcadmins;
  private org.edisoncor.gui.panel.Panel panel2;
  private javax.swing.JButton registrar;
  private javax.swing.JButton salircat;
  // End of variables declaration//GEN-END:variables
}