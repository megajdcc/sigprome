/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Controlador.ControladorPotencia;
import java.awt.Event;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

/**
 *
 * @author Jnatn'h
 */
public class Potencia extends java.awt.Dialog {
    ControladorPotencia controlador;
    int idpotencia;
     public void Idpotencia(int Idpotencia)
    {

        this.idpotencia = Idpotencia;
        this.setIdpotencia(idpotencia);
       // System.out.println(Idpatologia);
      
    }
      public int getIdpotencia()
    {
        return idpotencia;
    }
    public void setIdpotencia(int idpotencia)
    {
        this.idpotencia = idpotencia;
         
    }
    public JLabel getLeyenda()
    {
        return Leyenda;
    }
    public void setLeyenda(JLabel Leyenda)
    {
        this.Leyenda = Leyenda;
    }
    
    public JTextField getTextpotencia ()
    {
        return Textpotencia;
    }
    public void setTextpotencia(JTextField Textpotencia)
    {
        this.Textpotencia = Textpotencia;
    }
    
    public JButton getRegistrar()
    {
        return Registrar;
    }
    public void setRegistrar(JButton Registrar)
    {
        this.Registrar = Registrar;
    }
     public JButton getModificar()
    {
        return Modificar;
    }
    public void setModificar(JButton Modificar)
    {
        this.Modificar = Modificar;
    }
     public JButton getEliminar()
    {
        return Eliminar;
    }
    public void setEliminar(JButton Eliminar)
    {
        this.Eliminar = Eliminar;
    }
     public JButton getSalir()
    {
        return Salir;
    }
    public void setSalir(JButton Salir)
    {
        this.Salir = Salir;
    }
    /**
     * Creates new form Potencia
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Potencia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Potencia");
        centraVentana();
    
        this.desabilitaroptionpegar(this.getTextpotencia());
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        panel2 = new org.edisoncor.gui.panel.Panel();
        idpatologia1 = new java.awt.TextField();
        Textpotencia = new javax.swing.JTextField();
        Registrar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Leyenda = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(642, 2512345));
        setMinimumSize(new java.awt.Dimension(642, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(new java.awt.CardLayout());

        panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        panel1.setMaximumSize(new java.awt.Dimension(642, 252345));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-potencia.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(641, 75));
        panel2.setMinimumSize(new java.awt.Dimension(0, 75));
        panel2.setPreferredSize(new java.awt.Dimension(641, 75));

        idpatologia1.setVisible(false);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(idpatologia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(idpatologia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panel1.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 642, 75));
        panel2.getAccessibleContext().setAccessibleName("");

        Textpotencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Textpotencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextpotenciaFocusGained(evt);
            }
        });
        Textpotencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextpotenciaKeyTyped(evt);
            }
        });
        panel1.add(Textpotencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 93, 434, 35));

        Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
        Registrar.setText("Registrar");
        Registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel1.add(Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 154, -1, 30));
        controlador = new Controlador.ControladorPotencia(this);
        Registrar.addActionListener(controlador);

        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/modificar.png"))); // NOI18N
        Modificar.setText("Modificar");
        Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel1.add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 154, -1, 30));
        controlador = new Controlador.ControladorPotencia(this);
        Modificar.addActionListener(controlador);

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/eliminar.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel1.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 154, -1, 30));
        controlador = new Controlador.ControladorPotencia(this);
        Eliminar.addActionListener(controlador);

        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel1.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(483, 154, -1, 30));
        controlador = new Controlador.ControladorPotencia(this);
        Salir.addActionListener(controlador);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 93, 83, 35));

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(255, 255, 255));
        panel1.add(Leyenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 225, 636, 30));

        add(panel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void TextpotenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextpotenciaFocusGained
        String leyen = "Ingrese o modifique la potencia";
        this.getLeyenda().setText(leyen);
        this.getLeyenda().setForeground(Color.white);
    }//GEN-LAST:event_TextpotenciaFocusGained

    private void TextpotenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextpotenciaKeyTyped
        this.validarcampo(evt);
    }//GEN-LAST:event_TextpotenciaKeyTyped
   private void validarcampo(KeyEvent e)
    {
       // String caracter  = this.getTextpotencia().toString();
     char b = e.getKeyChar();
         if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE){
            e.consume();
    }     
         if( this.getTextpotencia().getText().length() >= 30)
    {
        e.consume();
    }
//        if(!caracter.isEmpty())
//        caracter=caracter.substring(0,caracter.length()-1);
//        caracter+=String.valueOf(b).toUpperCase();
//        this.getTextpotencia().setText(caracter);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    public javax.swing.JLabel Leyenda;
    private javax.swing.JButton Modificar;
    private javax.swing.JButton Registrar;
    private javax.swing.JButton Salir;
    private javax.swing.JTextField Textpotencia;
    public java.awt.TextField idpatologia1;
    private javax.swing.JLabel jLabel1;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panel2;
    // End of variables declaration//GEN-END:variables
}
