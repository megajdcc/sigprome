/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorCatPotencia;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Jnatn'h
 */
public class CatPotencia extends java.awt.Dialog {
    private ControladorCatPotencia controlador;
    
    public void setController(ControladorCatPotencia controller){
        this.controlador = controller;
        this.asignarlistener();
    }
    public ControladorCatPotencia getController(){
        return this.controlador;
    }
    public void asignarlistener(){
        busqueda.addKeyListener(getController());
        potencia.addMouseListener(getController());
        registrar.addActionListener(getController());
        salircat.addActionListener(getController());
    }
    public JTextField getBusqueda(){
        return busqueda;
    }
    public void setBusqueda(JTextField busqueda){
        this.busqueda = busqueda;
    }
    public JTable getPotencia(){
        return potencia;
    }
    public void setPotencia(JTable potencia){
        this.potencia = potencia;
    }
    public JButton getSalir(){
        return salircat;
    }
    public void setSalir(JButton salir){
        this.salircat = salir;
    }
    public JLabel getLeyenda(){
        return Leyenda;
    }
    public void setLeyenda(JLabel Leyenda){
        this.Leyenda = Leyenda;
    }
    public JButton getRegistrar(){
        return registrar;
    }
    /**
     * Creates new form CatPotencia
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public CatPotencia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setTitle("Potencia");
        setController(new ControladorCatPotencia(this));
        centraVentana();
        this.setModal(modal);
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

        cat1 = new org.edisoncor.gui.panel.Panel();
        busqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        potencia = new javax.swing.JTable();
        salircat = new javax.swing.JButton();
        panel2 = new org.edisoncor.gui.panel.Panel();
        Leyenda = new javax.swing.JLabel();
        registrar = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(410, 399));
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        cat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        cat1.setName("Potencia"); // NOI18N

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

        potencia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        potencia.setModel(new javax.swing.table.DefaultTableModel(
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
                "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        potencia.setToolTipText("");
        potencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        potencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                potenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                potenciaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(potencia);

        salircat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        salircat.setText("Salir");
        salircat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/headercatpotencia.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(410, 75));
        panel2.setMinimumSize(new java.awt.Dimension(410, 75));
        panel2.setPreferredSize(new java.awt.Dimension(410, 75));

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
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cat1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
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
        String Leyend = "Le Facilita buscar la potencia deseada... ";
        this.getLeyenda().setText(Leyend);
    }//GEN-LAST:event_busquedaFocusGained

    private void busquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyReleased
     
    }//GEN-LAST:event_busquedaKeyReleased

    private void busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyTyped
        
    }//GEN-LAST:event_busquedaKeyTyped
 
    private void potenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_potenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_potenciaKeyReleased

    private void potenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_potenciaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_potenciaKeyTyped



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Leyenda;
    private javax.swing.JTextField busqueda;
    private org.edisoncor.gui.panel.Panel cat1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.panel.Panel panel2;
    private javax.swing.JTable potencia;
    private javax.swing.JButton registrar;
    private javax.swing.JButton salircat;
    // End of variables declaration//GEN-END:variables
}