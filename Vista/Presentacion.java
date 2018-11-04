/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.ControladorPresentacion;
import Controlador.ControladorCatpresentacion;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
/**
 *
 * @author Jnatn'h
 */
public class Presentacion extends java.awt.Dialog {
    int Idpresentacion;
    
    ControladorPresentacion controlador;
    ControladorCatpresentacion controladorcat;
    
     public void Idpresentacion(int Idpresentacion)
    {

        this.Idpresentacion = Idpresentacion;
        this.setIdPresentacion(Idpresentacion);
       // System.out.println(Idpatologia);
      
    }
      public int getIdPresentacion()
    {
        return Idpresentacion;
    }
    public void setIdPresentacion(int Idpresentacion)
    {
        this.Idpresentacion = Idpresentacion;
    }
     public JLabel getLeyenda()
    {
        return Leyenda;
    }
    public void setLeyenda(JLabel Leyenda)
    {
        this.Leyenda = Leyenda;
    }
     public JTextField getTextpresentacion ()
    {
        return Textpresentacion;
    }
    public void setTextpresentacion(JTextField Textpresentacion)
    {
        this.Textpresentacion = Textpresentacion;
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
     * Creates new form Presentacion
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Presentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.Textpresentacion.requestFocus();
        this.setResizable(false);
        this.setTitle("Presentación");
        centraVentana();
    
        this.desabilitaroptionpegar(this.getTextpresentacion());
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

private void validar(KeyEvent e)
{
    char b = e.getKeyChar();
         if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE){
            e.consume();
    }     
         if( this.getTextpresentacion().getText().length() >= 30)
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

        panel1 = new org.edisoncor.gui.panel.Panel();
        panel2 = new org.edisoncor.gui.panel.Panel();
        idpatologia1 = new java.awt.TextField();
        Textpresentacion = new javax.swing.JTextField();
        Registrar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Leyenda = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        panel1.setMaximumSize(new java.awt.Dimension(624, 225));

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-presentacion.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(623, 75));
        panel2.setPreferredSize(new java.awt.Dimension(623, 75));

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

        Textpresentacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextpresentacionFocusGained(evt);
            }
        });
        Textpresentacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextpresentacionKeyTyped(evt);
            }
        });

        Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
        Registrar.setText("Registrar");
        Registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/modificar.png"))); // NOI18N
        Modificar.setText("Modificar");
        Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/eliminar.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Textpresentacion)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(Registrar)
                        .addGap(18, 18, 18)
                        .addComponent(Modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Salir)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Textpresentacion, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        controlador = new Controlador.ControladorPresentacion(this);
        Registrar.addActionListener(controlador);
        controlador = new Controlador.ControladorPresentacion(this);
        Modificar.addActionListener(controlador);
        controlador = new Controlador.ControladorPresentacion(this);
        Eliminar.addActionListener(controlador);
        controlador = new Controlador.ControladorPresentacion(this);
        Salir.addActionListener(controlador);

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

    private void TextpresentacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextpresentacionFocusGained
        String leyen = "Ingrese o modifique la presentación";
        this.getLeyenda().setText(leyen);
    }//GEN-LAST:event_TextpresentacionFocusGained

    private void TextpresentacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextpresentacionKeyTyped
        this.validar(evt);
    }//GEN-LAST:event_TextpresentacionKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    public javax.swing.JLabel Leyenda;
    private javax.swing.JButton Modificar;
    private javax.swing.JButton Registrar;
    private javax.swing.JButton Salir;
    private javax.swing.JTextField Textpresentacion;
    public java.awt.TextField idpatologia1;
    private javax.swing.JLabel jLabel1;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panel2;
    // End of variables declaration//GEN-END:variables
}