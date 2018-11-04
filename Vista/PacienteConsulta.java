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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
/**
 *
 * @author Jnatn'h
 */
public class PacienteConsulta extends java.awt.Dialog {
    ControladorConsulta controlador;
    public void setController(ControladorConsulta controlador){
        this.controlador = controlador;
        this.implementarcontrolador();
    }
    public ControladorConsulta getController(){
        return this.controlador;
    }
    
    public void setLeyenda(JLabel leyenda){
        this.Leyenda = leyenda;
    }
    public JLabel getLeyenda(){
        return Leyenda;
    }
    
    /* 
    *Busqueda en el catalogo. 
    * @param nombre
    * @type JTextField
    */
    public void setBusqueda(JTextField nombre){
        this.busqued = nombre;
    }
    public JTextField getBusqueda(){
        return busqued;
    }
    // filtro de la busquedad 
    public void setFiltro(ButtonGroup filtro){
        this.filtro = filtro;
    }
    public ButtonGroup getFiltro(){
        return this.filtro;
    }
    public void setEmergencia(JRadioButton emergencia){
        this.emergencia = emergencia;
    }
    public JRadioButton getEmergencia(){
        return this.emergencia;
    }
    public void setCita(JRadioButton cita){
        this.cita = cita;
    }
    public JRadioButton getCita(){
        return this.cita;
    }
    public void setCatpaciente(JTable cat){
        this.Catpaciente = cat;
    }
    public JTable getCatpaciente(){
        return this.Catpaciente;
    }
    public void setAceptar(JButton aceptar){
        this.Aceptar =aceptar;
    }
    public JButton getAceptar(){
        return this.Aceptar;
    }
    public void setSalir(JButton Salir){
        this.salircat =Salir;
    }
    public JButton getSalir(){
        return this.salircat;
    }
    private void implementarcontrolador(){
        getBusqueda().addKeyListener(getController());
        getEmergencia().addActionListener(getController());
        getEmergencia().addMouseListener(getController());
        getCita().addActionListener(getController());
        getCita().addMouseListener(getController());
        getCatpaciente().addMouseListener(getController());
        getAceptar().addActionListener(getController());
        getSalir().addActionListener(getController());
    }
    /**
     * Creates new form PacienteConsulta
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public PacienteConsulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        setTitle("Pacientes en espera"); 
        this.getBusqueda().setFocusable(true);
        centraVentana();
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filtro = new javax.swing.ButtonGroup();
        cat2 = new org.edisoncor.gui.panel.Panel();
        busqued = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Catpaciente = new javax.swing.JTable();
        salircat = new javax.swing.JButton();
        Aceptar = new javax.swing.JButton();
        panel2 = new org.edisoncor.gui.panel.Panel();
        emergencia = new javax.swing.JRadioButton();
        cita = new javax.swing.JRadioButton();
        Leyenda = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        cat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        cat2.setMaximumSize(new java.awt.Dimension(776, 424));
        cat2.setPreferredSize(new java.awt.Dimension(776, 424));

        busqued.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                busquedFocusGained(evt);
            }
        });
        busqued.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                busquedKeyTyped(evt);
            }
        });

        Catpaciente.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Catpaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cédula", "Nombre", "Apellido", "Tipo Paciente", "Proviene"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Catpaciente.setToolTipText("");
        Catpaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Catpaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CatpacienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CatpacienteKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(Catpaciente);

        salircat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        salircat.setText("Salir");

        Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/registrar.png"))); // NOI18N
        Aceptar.setText("Aceptar");
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/Vista/imagen/headercatpaciente.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(485, 75));
        panel2.setPreferredSize(new java.awt.Dimension(485, 75));

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

        filtro.add(emergencia);
        emergencia.setForeground(new java.awt.Color(255, 255, 255));
        emergencia.setText("Emergencia");

        filtro.add(cita);
        cita.setForeground(new java.awt.Color(255, 255, 255));
        cita.setText("Cita");
        cita.setToolTipText("");

        Leyenda.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout cat2Layout = new javax.swing.GroupLayout(cat2);
        cat2.setLayout(cat2Layout);
        cat2Layout.setHorizontalGroup(
            cat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
            .addGroup(cat2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(cat2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(cat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cat2Layout.createSequentialGroup()
                        .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salircat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cat2Layout.createSequentialGroup()
                        .addComponent(busqued, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(emergencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cita, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        cat2Layout.setVerticalGroup(
            cat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cat2Layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(busqued, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emergencia)
                    .addComponent(cita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cat2Layout.createSequentialGroup()
                        .addComponent(Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(salircat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        add(cat2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void busquedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_busquedFocusGained
        String Leyend = "Ingresar datos para buscar... ";
        this.getLeyenda().setText(Leyend);
        this.getLeyenda().setForeground(Color.white);
    }//GEN-LAST:event_busquedFocusGained

    private void busquedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_busquedKeyReleased

    private void busquedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedKeyTyped

    }//GEN-LAST:event_busquedKeyTyped

    private void CatpacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatpacienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CatpacienteKeyReleased

    private void CatpacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatpacienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_CatpacienteKeyTyped

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed

    }//GEN-LAST:event_AceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Aceptar;
    public javax.swing.JTable Catpaciente;
    private javax.swing.JLabel Leyenda;
    private javax.swing.JTextField busqued;
    private org.edisoncor.gui.panel.Panel cat2;
    private javax.swing.JRadioButton cita;
    private javax.swing.JRadioButton emergencia;
    private javax.swing.ButtonGroup filtro;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.Panel panel2;
    private javax.swing.JButton salircat;
    // End of variables declaration//GEN-END:variables
}
