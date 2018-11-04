/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;  

import Controlador.ControladorPatologia;
import Controlador.ControladorCatPatologia;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @author Jnatn'h
 */
public class Patologias extends java.awt.Dialog {
    int Idpatologia;
    public boolean desdeconsulta = false; 
    private ControladorPatologia controlador;
    private ControladorCatPatologia controladorcat;
    private catapatologia catalogo;
    //setters y getters...
    private void setController(ControladorPatologia controlador){
        this.controlador = controlador;
        this.setListener();
    }
    private void setListener(){
        getRegistrar().addActionListener(getController());
        getModificar().addActionListener(getController());
        getEliminar().addActionListener(getController());
        getSalir().addActionListener(getController());
    }
    public ControladorPatologia getController(){
        return this.controlador;
    }
    public void IdPatologia(int Idpatologia){
        this.Idpatologia = Idpatologia;
        this.setIdPatologia(Idpatologia);      
    }
    public int getIdPatologia(){
        return Idpatologia;
    }
    public void setIdPatologia(int Idpatologia){
        this.Idpatologia = Idpatologia;
    }
    public JLabel getLeyenda(){
        return Leyenda;
    }
    public void setLeyenda(JLabel Leyenda) {
        this.Leyenda = Leyenda;
    }
    public JTextField getTextpatologia (){
        return Textpatologia;
    }
    public void setTextpatologia(JTextField Textpatologia){
        this.Textpatologia = Textpatologia;
    }
    public JButton getRegistrar(){
        return Registrar;
    }
    public void setRegistrar(JButton Registrar){
        this.Registrar = Registrar;
    }
    public JButton getModificar(){
        return Modificar;
    }
    public void setModificar(JButton Modificar){
        this.Modificar = Modificar;
    }
    public JButton getEliminar(){
        return Eliminar;
    }
    public void setEliminar(JButton Eliminar){
        this.Eliminar = Eliminar;
    }
    public JButton getSalir(){
        return Salir;
    }
    public void setSalir(JButton Salir){
        this.Salir = Salir;
    }
    public JTextArea getdescripcion(){
        return descripcion;
    }
    public void setDescripcion(JTextArea descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Creates new form Patologias
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Patologias(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.Textpatologia.requestFocus();
        this.setResizable(false);
         setLocationRelativeTo(null);  
        this.desabilitaroptionpegar(this.getTextpatologia());
        this.setController(new ControladorPatologia(this));
    }
    /**
     * Creates new form Patologias
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     * @param desdeconsulta, un booleano para indicar que esta levantando instancia desde consulta
     */
    public Patologias(java.awt.Frame parent, boolean modal, boolean desdeconsulta) {
        super(parent, modal);
        initComponents();
        this.Textpatologia.requestFocus();
        this.setResizable(false);
        setLocationRelativeTo(null);
        this.desdeconsulta = desdeconsulta;
        this.desabilitaroptionpegar(this.getTextpatologia());
        this.setController(new ControladorPatologia(this));
    }
    public void setBack(catapatologia cata){
      this.catalogo = cata;
    }
    public catapatologia getBack(){
      catalogo.getControllerpatologia().ListaPatologia();
      return catalogo;
    }
    public void desabilitaroptionpegar(JTextField text){
        InputMap map = text.getInputMap();
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, Event.SHIFT_MASK), "null");
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
         if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE ){
            e.consume();
    }     
         if( this.getTextpatologia().getText().length() >= 30)
    {
        e.consume();
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new org.edisoncor.gui.panel.Panel();
        panel1 = new org.edisoncor.gui.panel.Panel();
        Textpatologia = new javax.swing.JTextField();
        Registrar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Leyenda = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(700, 430));
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(new java.awt.CardLayout());

        panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        panel.setMaximumSize(new java.awt.Dimension(700, 430));
        panel.setPreferredSize(new java.awt.Dimension(700, 430));

        panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-patologia.png"))); // NOI18N
        panel1.setMaximumSize(new java.awt.Dimension(700, 75));
        panel1.setMinimumSize(new java.awt.Dimension(700, 75));
        panel1.setPreferredSize(new java.awt.Dimension(700, 75));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        Textpatologia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Textpatologia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextpatologiaFocusGained(evt);
            }
        });
        Textpatologia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextpatologiaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextpatologiaKeyTyped(evt);
            }
        });

        Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
        Registrar.setText("Registrar");
        Registrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Registrar.setMaximumSize(new java.awt.Dimension(112, 30));
        Registrar.setMinimumSize(new java.awt.Dimension(112, 30));
        Registrar.setPreferredSize(new java.awt.Dimension(112, 30));

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

        descripcion.setColumns(20);
        descripcion.setLineWrap(true);
        descripcion.setRows(5);
        descripcion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        descripcion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descripcionFocusGained(evt);
            }
        });
        jScrollPane3.setViewportView(descripcion);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Descripción:");

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(254, 254, 254));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Textpatologia)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addGap(75, 81, Short.MAX_VALUE))
            .addComponent(panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Textpatologia, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void descripcionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descripcionFocusGained
        String leyend = "Ingrese la descripción relacionada con la patología.";
        this.getLeyenda().setText(leyend);
        this.getLeyenda().setForeground(Color.white);
    }//GEN-LAST:event_descripcionFocusGained

    private void TextpatologiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextpatologiaKeyTyped
        this.validarcampo(evt);
    }//GEN-LAST:event_TextpatologiaKeyTyped

    private void TextpatologiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextpatologiaKeyPressed
        // TODO add your handling code here:
        char a = evt.getKeyChar();
        if (KeyEvent.VK_PASTE == a){
            evt.consume();
    }//GEN-LAST:event_TextpatologiaKeyPressed
    }
    private void TextpatologiaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextpatologiaFocusGained
        String leyen = "Ingrese o modifique la patología";
        this.getLeyenda().setText(leyen);
        this.getLeyenda().setForeground(Color.white);
    }//GEN-LAST:event_TextpatologiaFocusGained
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    public javax.swing.JLabel Leyenda;
    private javax.swing.JButton Modificar;
    private javax.swing.JButton Registrar;
    private javax.swing.JButton Salir;
    private javax.swing.JTextField Textpatologia;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane3;
    private org.edisoncor.gui.panel.Panel panel;
    private org.edisoncor.gui.panel.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
