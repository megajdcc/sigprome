/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.ControladorCatPatologia;
import Controlador.ControladorConsulta;
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
public class catapatologia extends java.awt.Dialog {
    Principal principal;
    ControladorCatPatologia controlador;
    ControladorConsulta controllerconsulta;
    public void setController(ControladorConsulta controller){
        this.controllerconsulta = controller;
        this.asignaroyentes();
    }
    public ControladorConsulta getController(){
        return controllerconsulta;
    }
    public void setControllerpatologia(ControladorCatPatologia controller){
        this.controlador = controller;
        this.asignaroyentespatologia();
        
    }
    public ControladorCatPatologia getControllerpatologia(){
        return controlador;
    }
    private void asignaroyentes(){
        Textbusqueda.addKeyListener(getController());
        Catpatologia.addMouseListener(getController());
        nuevapat.addActionListener(getController());
        salircat.addActionListener(getController());
        imprimir.addActionListener(getController());
    }
    private void asignaroyentespatologia(){
        Textbusqueda.addKeyListener(getControllerpatologia());
        Catpatologia.addMouseListener(getControllerpatologia());
        nuevapat.addActionListener(getControllerpatologia());
        salircat.addActionListener(getControllerpatologia());
        imprimir.addActionListener(getControllerpatologia());
    }
    public JTextField getTextbusqueda(){
        return Textbusqueda;
    }
    public void setTextBusqueda(JTextField Textbusqueda){
        this.Textbusqueda = Textbusqueda;
    }
    public JTable getCatpatologia(){
        return Catpatologia;
    }
    public void setCatpatologia(JTable Catpatologia){
        this.Catpatologia = Catpatologia;
    }
    public JLabel getLeyenda(){
        return Leyenda;
    }
    public void setLeyenda(JLabel Leyenda){
        this.Leyenda = Leyenda;
    }
    public JButton getNuevapat(){
        return nuevapat;
    }
    public void setNuevapat(JButton nuevapat){
        this.nuevapat = nuevapat;
    }
    public JButton getSalircat(){
        return salircat;
    }
    public void setSalircat(JButton salircat){
        this.salircat = salircat;
    }

  public JButton getImprimir() {
    return imprimir;
  }

  public void setImprimir(JButton imprimir) {
    this.imprimir = imprimir;
  }
    
    /**
     * Creates new form catapatologia
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public catapatologia(java.awt.Frame parent, boolean modal) {
       
        super(parent, modal);
         principal =(Principal) parent;
        initComponents();
         centraVentana();
        this.setTitle("Patologías");
        this.setResizable(false);
        setControllerpatologia(new ControladorCatPatologia(this));
    }
  /**
     * Creates new form catapatologia
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     * @param buscporconsulta, un booleano para indicar si la instancia proviene desde la vista consulta... 
     */
    public catapatologia(java.awt.Frame parent, boolean modal, boolean buscporconsulta ) {
        super(parent, modal);
        initComponents();
         centraVentana();
        this.setTitle("Patologías");
        this.setResizable(false);
    }
    private void validar (KeyEvent e)
    {
        char b = e.getKeyChar();
         if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE){
            e.consume();
         }
    }
  
/*private void validarcampo(KeyEvent event)
    {
        String caracter  = this.getTextpatologia().getText();
        char b = event.getKeyChar();
         if(!Character.isLetter(b) &&  b != KeyEvent.VK_SPACE){
            event.consume();
        }
    if(this.getTextpatologia().getText().length()> 30)
        {
            event.consume();
        }
     }/*
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cat = new org.edisoncor.gui.panel.Panel();
        Textbusqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Catpatologia = new javax.swing.JTable();
        salircat = new javax.swing.JButton();
        nuevapat = new javax.swing.JButton();
        panel2 = new org.edisoncor.gui.panel.Panel();
        idpatologia1 = new java.awt.TextField();
        Leyenda = new javax.swing.JLabel();
        imprimir = new javax.swing.JButton();

        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        cat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondologin.png"))); // NOI18N
        cat.setMaximumSize(new java.awt.Dimension(508, 402));
        cat.setPreferredSize(new java.awt.Dimension(508, 402));

        Textbusqueda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextbusquedaFocusGained(evt);
            }
        });
        Textbusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextbusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextbusquedaKeyTyped(evt);
            }
        });

        Catpatologia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Catpatologia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Patologías"
            }
        ));
        Catpatologia.setToolTipText("Patologías");
        Catpatologia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Catpatologia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CatpatologiaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CatpatologiaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(Catpatologia);

        salircat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        salircat.setText("Salir");
        salircat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        salircat.setMaximumSize(new java.awt.Dimension(112, 30));
        salircat.setMinimumSize(new java.awt.Dimension(112, 30));
        salircat.setPreferredSize(new java.awt.Dimension(112, 30));
        salircat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salircatActionPerformed(evt);
            }
        });

        nuevapat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/registrar.png"))); // NOI18N
        nuevapat.setText("Nueva ");
        nuevapat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nuevapat.setMaximumSize(new java.awt.Dimension(112, 30));
        nuevapat.setMinimumSize(new java.awt.Dimension(112, 30));
        nuevapat.setPreferredSize(new java.awt.Dimension(112, 30));
        nuevapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevapatActionPerformed(evt);
            }
        });

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/patologia-header.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(508, 75));
        panel2.setPreferredSize(new java.awt.Dimension(508, 75));

        idpatologia1.setVisible(false);

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addComponent(idpatologia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(277, Short.MAX_VALUE))
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addComponent(idpatologia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(255, 255, 255));

        imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/imprimir.png"))); // NOI18N
        imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout catLayout = new javax.swing.GroupLayout(cat);
        cat.setLayout(catLayout);
        catLayout.setHorizontalGroup(
            catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(catLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(catLayout.createSequentialGroup()
                        .addComponent(nuevapat, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salircat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Textbusqueda)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, catLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        catLayout.setVerticalGroup(
            catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catLayout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(Textbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevapat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salircat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        add(cat, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void TextbusquedaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextbusquedaFocusGained
        String Leyend = "Buscar en el catálogo el registro de patologías...";
        this.getLeyenda().setText(Leyend);
    }//GEN-LAST:event_TextbusquedaFocusGained

    private void TextbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextbusquedaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TextbusquedaKeyReleased

    private void TextbusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextbusquedaKeyTyped
            this.validar(evt);
    }//GEN-LAST:event_TextbusquedaKeyTyped

    private void nuevapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevapatActionPerformed
       
    }//GEN-LAST:event_nuevapatActionPerformed

    private void CatpatologiaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatpatologiaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_CatpatologiaKeyTyped

    private void CatpatologiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatpatologiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CatpatologiaKeyReleased

    private void salircatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salircatActionPerformed
       this.dispose();
    }//GEN-LAST:event_salircatActionPerformed

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
    private javax.swing.JTable Catpatologia;
    private javax.swing.JLabel Leyenda;
    private javax.swing.JTextField Textbusqueda;
    private org.edisoncor.gui.panel.Panel cat;
    public java.awt.TextField idpatologia1;
    private javax.swing.JButton imprimir;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton nuevapat;
    private org.edisoncor.gui.panel.Panel panel2;
    private javax.swing.JButton salircat;
    // End of variables declaration//GEN-END:variables
}
