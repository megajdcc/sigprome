/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.ControladorTipoServicios;
import Controlador.ControladorCattiposervicios;
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
public class TipoServicios extends java.awt.Dialog {
     int Idtiposervicios;
    
    ControladorTipoServicios controlador;
    ControladorCattiposervicios controladorcat;
    
     public void Idtiposervicios(int Idtiposervicios)
    {

        this.Idtiposervicios = Idtiposervicios;
        this.setIdTiposervicios(Idtiposervicios);
       // System.out.println(Idpatologia);
      
    }
      public int getIdTiposervicios()
    {
        return Idtiposervicios;
    }
    public void setIdTiposervicios(int Idtiposervicios)
    {
        this.Idtiposervicios = Idtiposervicios;
    }
     public JLabel getLeyenda()
    {
        return Leyenda;
    }
    public void setLeyenda(JLabel Leyenda)
    {
        this.Leyenda = Leyenda;
    }
     public JTextField getTexttiposervicios()
    {
        return Texttiposervicios;
    }
    public void setTexttiposervicios(JTextField Texttiposervicios)
    {
        this.Texttiposervicios = Texttiposervicios;
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
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public TipoServicios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.Texttiposervicios.requestFocus();
        this.setTitle("Registro de Tipo de Servicios");
        this.setResizable(false);
        centraVentana();
        this.desabilitaroptionpegar(this.getTexttiposervicios());
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
          if( this.getTexttiposervicios().getText().length() >= 30)
    {
        e.consume();
    }
    if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE){
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
        Texttiposervicios = new javax.swing.JTextField();
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
        panel1.setMaximumSize(new java.awt.Dimension(593, 233));
        panel1.setMinimumSize(new java.awt.Dimension(593, 233));
        panel1.setPreferredSize(new java.awt.Dimension(593, 233));

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-tipodeservicio.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(593, 75));
        panel2.setMinimumSize(new java.awt.Dimension(593, 75));
        panel2.setPreferredSize(new java.awt.Dimension(593, 75));

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

        Texttiposervicios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TexttiposerviciosFocusGained(evt);
            }
        });
        Texttiposervicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TexttiposerviciosKeyTyped(evt);
            }
        });

        Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
        Registrar.setText("Registrar");
        Registrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/modificar.png"))); // NOI18N
        Modificar.setText("Modificar");
        Modificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/eliminar.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(255, 255, 255));
        Leyenda.setMaximumSize(new java.awt.Dimension(592, 30));
        Leyenda.setMinimumSize(new java.awt.Dimension(592, 30));
        Leyenda.setName(""); // NOI18N
        Leyenda.setPreferredSize(new java.awt.Dimension(592, 30));

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(Registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Texttiposervicios, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Texttiposervicios, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        controlador = new Controlador.ControladorTipoServicios(this);
        Registrar.addActionListener(controlador);
        controlador = new Controlador.ControladorTipoServicios(this);
        Modificar.addActionListener(controlador);
        controlador = new Controlador.ControladorTipoServicios(this);
        Eliminar.addActionListener(controlador);
        controlador = new Controlador.ControladorTipoServicios(this);
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

    private void TexttiposerviciosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TexttiposerviciosFocusGained
        String leyen = "Ingrese o modifique el tipo de servicios";
        this.getLeyenda().setText(leyen);
    }//GEN-LAST:event_TexttiposerviciosFocusGained

    private void TexttiposerviciosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TexttiposerviciosKeyTyped
        this.validar(evt);
    }//GEN-LAST:event_TexttiposerviciosKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    public javax.swing.JLabel Leyenda;
    private javax.swing.JButton Modificar;
    private javax.swing.JButton Registrar;
    private javax.swing.JButton Salir;
    private javax.swing.JTextField Texttiposervicios;
    public java.awt.TextField idpatologia1;
    private javax.swing.JLabel jLabel1;
    private org.edisoncor.gui.panel.Panel panel1;
    private org.edisoncor.gui.panel.Panel panel2;
    // End of variables declaration//GEN-END:variables
}