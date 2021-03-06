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

import Controlador.ControllerBitacora;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 *
 * @author Jnatn'h
 */
public class Bitacora extends java.awt.Dialog {

    ControllerBitacora controlador;
    public void setController(ControllerBitacora controlador){
        this.controlador = controlador;
        this.asignarlistener();
    }
    public ControllerBitacora getController(){
        return this.controlador;
    }
    public void asignarlistener(){
        getOperacion().addActionListener(getController());
        getSalir().addActionListener(getController());
        getAceptar().addActionListener(getController());
        getImpresion().addActionListener(getController());
        getLimpiar().addActionListener(getController());
        getBitacora().addMouseListener(getController());
        getUsuarios().addActionListener(getController());
        getBusqueda().addActionListener(getController());

        getOperacion().addFocusListener(getController());
        getSalir().addFocusListener(getController());
        getAceptar().addFocusListener(getController());
        getImpresion().addFocusListener(getController());
        getBitacora().addFocusListener(getController());
        getLimpiar().addFocusListener(getController());
        getUsuarios().addFocusListener(getController());
        getBusqueda().addFocusListener(getController());
        getBusqueda().addKeyListener(getController());    
}
    //Getters y setters 
    public void setLeyenda(JLabel leyenda){
        this.leyenda = leyenda;
    }
    public JLabel getLeyenda(){
        return this.leyenda;
    }
    public void setOperacion(JComboBox operacion ){
        this.operacion = operacion;
    }
    public JComboBox getOperacion(){
        return this.operacion;
    }
    public void setBitacora(JTable bita){
        this.bitacora = bita;
    }
    public JTable getBitacora(){
        return this.bitacora;
    }
    public void setImpresion(JButton impre){
        this.impresion = impre;
    }
    public JButton getImpresion(){
        return this.impresion;
    }
    public void setLimpiar(JButton limpiar){
        this.limpiar = limpiar;
    }
    public JButton getLimpiar(){
        return this.limpiar;
    }
    public void setSalir(JButton salir){
        this.salir = salir;
    }
    public JButton getSalir(){
        return this.salir;
    }
    public void setAceptar(JButton aceptar){
        this.aceptar = aceptar;
    }
    public JButton getAceptar(){
        return this.aceptar;
    }
    public void setBusqueda(JTextField busq){
        this.busqueda = busq;
    }
    public JTextField getBusqueda(){
        return this.busqueda;
    }
    public void setUsuarios(JComboBox usuarios){
        this.usuarios = usuarios;
    }
    public JComboBox getUsuarios(){
        return usuarios;
    }
    
    /**
     * Creates new form Bitacora
     * @param parent, Una container de primer nivel de tipo Frame.. 
     * @param modal, para indicar si el objecto es modal o no... 
     * 
     */
    public Bitacora(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Bitacora");
        setResizable(false);
        centraVentana();
        setController(new ControllerBitacora(this));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    operaciones = new javax.swing.ButtonGroup();
    panelBitacora = new org.edisoncor.gui.panel.Panel();
    jScrollPane1 = new javax.swing.JScrollPane();
    bitacora = new javax.swing.JTable();
    panel2 = new org.edisoncor.gui.panel.Panel();
    jLabel1 = new javax.swing.JLabel();
    operacion = new javax.swing.JComboBox<>();
    impresion = new javax.swing.JButton();
    salir = new javax.swing.JButton();
    limpiar = new javax.swing.JButton();
    leyenda = new javax.swing.JLabel();
    aceptar = new javax.swing.JButton();
    busqueda = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    usuarios = new javax.swing.JComboBox<>();

    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        closeDialog(evt);
      }
    });

    panelBitacora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
    panelBitacora.setMaximumSize(new java.awt.Dimension(901, 544));
    panelBitacora.setPreferredSize(new java.awt.Dimension(901, 544));

    bitacora.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
    bitacora.setForeground(new java.awt.Color(0, 0, 0));
    bitacora.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {"df", null, null, null, null, null},
        {"asdfsdf", null, null, null, null, null},
        {"asdfas", null, null, null, null, null},
        {"fsdfas", null, null, null, null, null},
        {"dfasdf", null, null, null, null, null},
        {"asdfasd", null, null, null, null, null},
        {"fasdfasdf", null, null, null, null, null},
        {"asdf", null, null, null, null, null},
        {"asdfasdf", null, null, null, null, null},
        {"asdfsdf", null, null, null, null, null},
        {"asdf", null, null, null, null, null},
        {"asdfasa", null, null, null, null, null},
        {"dfasdf", null, null, null, null, null},
        {"asdfasd", null, null, null, null, null},
        {"fasdf", null, null, null, null, null},
        {"asdf", null, null, null, null, null},
        {null, null, null, null, null, null}
      },
      new String [] {
        "Operación", "Tabla", "Usuario", "Tipo de usuario", "Fecha", "Hora"
      }
    ) {
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    bitacora.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jScrollPane1.setViewportView(bitacora);

    panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-bitacora.png"))); // NOI18N
    panel2.setMaximumSize(new java.awt.Dimension(901, 75));
    panel2.setPreferredSize(new java.awt.Dimension(901, 75));

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

    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Operación:");

    operacion.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(java.awt.event.FocusEvent evt) {
        operacionFocusGained(evt);
      }
    });

    impresion.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
    impresion.setForeground(new java.awt.Color(0, 0, 0));
    impresion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/imprimir.png"))); // NOI18N
    impresion.setText("Imprimir");
    impresion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    impresion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    impresion.setMaximumSize(new java.awt.Dimension(126, 28));
    impresion.setPreferredSize(new java.awt.Dimension(126, 28));
    impresion.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/imprimir.png"))); // NOI18N

    salir.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
    salir.setForeground(new java.awt.Color(0, 0, 0));
    salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
    salir.setText("Salir");
    salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    salir.setMaximumSize(new java.awt.Dimension(126, 28));
    salir.setPreferredSize(new java.awt.Dimension(126, 28));

    limpiar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
    limpiar.setForeground(new java.awt.Color(0, 0, 0));
    limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/mantenimiento.png"))); // NOI18N
    limpiar.setText("Guardar / Limpiar");
    limpiar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

    leyenda.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
    leyenda.setForeground(new java.awt.Color(255, 255, 255));

    aceptar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
    aceptar.setForeground(new java.awt.Color(0, 0, 0));
    aceptar.setText("Aceptar");
    aceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    aceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    aceptar.setEnabled(false);
    aceptar.setMaximumSize(new java.awt.Dimension(126, 28));
    aceptar.setPreferredSize(new java.awt.Dimension(126, 28));

    busqueda.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Usuario:");

    javax.swing.GroupLayout panelBitacoraLayout = new javax.swing.GroupLayout(panelBitacora);
    panelBitacora.setLayout(panelBitacoraLayout);
    panelBitacoraLayout.setHorizontalGroup(
      panelBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBitacoraLayout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(panelBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addGroup(panelBitacoraLayout.createSequentialGroup()
            .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(operacion, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(panelBitacoraLayout.createSequentialGroup()
            .addComponent(impresion, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(15, 15, 15))
      .addComponent(leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    panelBitacoraLayout.setVerticalGroup(
      panelBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBitacoraLayout.createSequentialGroup()
        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(panelBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(operacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel1)
          .addComponent(busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel2)
          .addComponent(usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(panelBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(panelBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(impresion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(18, 18, 18)
        .addComponent(leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(panelBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void operacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_operacionFocusGained
 
    }//GEN-LAST:event_operacionFocusGained
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

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton aceptar;
  private javax.swing.JTable bitacora;
  private javax.swing.JTextField busqueda;
  private javax.swing.JButton impresion;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel leyenda;
  private javax.swing.JButton limpiar;
  private javax.swing.JComboBox<String> operacion;
  private javax.swing.ButtonGroup operaciones;
  private org.edisoncor.gui.panel.Panel panel2;
  private org.edisoncor.gui.panel.Panel panelBitacora;
  private javax.swing.JButton salir;
  private javax.swing.JComboBox<String> usuarios;
  // End of variables declaration//GEN-END:variables
}
