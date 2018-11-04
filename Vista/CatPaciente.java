/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorConsulta;
import Controlador.ControladorPaciente;
import Controlador.ControllerFevida;
import Vista.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase CatPaciente hereda de la clase Dialog, es utilizada para mostrar una ventana, que a su vez  es un catalogo de datos personales de pacientes
 * Regisrado en la base de dato SIGPROME... 
 * @author Jnatn'h
 */
public class CatPaciente extends Dialog {

/*******************************/
/*    Campos de clase          */
/* **************************  */
    
    int idusuario;
    private boolean afiliado = false;
    private boolean deconsulta = false;
    private ControladorPaciente controller;
    private ControladorConsulta contr;
    private ControllerFevida controllerfevida;
    Principal principal;
    
/*******************************/
/*    Getters y Setters        */
/* **************************  */
    
    /**
     * El metodo setController es utilizado para asignar un objeto a la ventana que sirva de oyentes antes las acciones que se realicen 
     * en los component... 
     * @param cont Parametro del tipo ControladorConsulta. 
     */
    public void setController(ControladorConsulta cont){
        this.contr = cont;
       setListener();
    }
    
    /**
     * El metodo setController es utilizado para asignar un objeto a la ventana que sirva de oyentes antes las acciones que se realicen 
     * en los component... 
     * @param controller Parametro de tipo ControladorPaciente
     */
    public void setController(ControladorPaciente controller){
        this.controller = controller;
        setListener();
    }
    
    
    
    public void setController(ControllerFevida controladorfevida){
        this.controllerfevida = controladorfevida;
        Textbusqueda.addKeyListener(controllerfevida);
        Catpaciente.addMouseListener(controllerfevida);
    }
    
   
    
    /**
     * 
     * @return Devuelve un objeto del tipo Objet utilizar casting para convertir al objeto correspondiente...  
     */
    public Object getController(){

        if(afiliado){
            return this.controller;
        }else if(deconsulta){
           return this.contr;
        }
           return null;
    }
    
    /**
     * EL metodo privado setListener es utilizado para asignar a los componentes los oyentes (Listener correspondiente) que puede ser en otro
     * objeto o en el mismo. 
     */
    private void setListener(){
        salircat.addActionListener((ActionEvent ae) -> {
        		setVisible(false);
            });
        if(afiliado){
            Textbusqueda.addKeyListener(controller);
            Catpaciente.addMouseListener(controller);
            nuevopaciente.addActionListener(controller);
             refres.addActionListener((ActionEvent ae) -> {
                controller.listarpacientes();
            });
        }else if(deconsulta){
            Textbusqueda.addKeyListener(contr);
            Catpaciente.addMouseListener(contr);
            nuevopaciente.addActionListener(contr);
            refres.addActionListener((ActionEvent ae) -> {
                contr.ListarPaciente();
            });
        }else{
            Textbusqueda.addKeyListener(controller);
            Catpaciente.addMouseListener(controller);
            nuevopaciente.addActionListener(controller);
            refres.addActionListener((ActionEvent ae) -> {
                controller.listarpacientes();
            });
            
        }
    }
     
    public JTextField getTextbusqueda(){
        return Textbusqueda;
    }
    
    public void setTextBusqueda(JTextField Textbusqueda){
        this.Textbusqueda = Textbusqueda;
    }
    
    public JTable getCatpaciente(){
        return Catpaciente;
    }
    
    public void setCatpaciente(JTable Catpaciente){
        this.Catpaciente = Catpaciente;
    }
    
    public JLabel getLeyenda(){
        return Leyenda;
    }
    
    public void setLeyenda(JLabel Leyenda){
        this.Leyenda = Leyenda;
    }
    
    public JButton getNuevopaciente(){
        return nuevopaciente;
    }
    
    public void setNuevopaciente(JButton nuevopaciente){
        this.nuevopaciente = nuevopaciente;
    }
    
    public JButton getSalircat(){
        return salircat;
    }
    
    public void setSalircat(JButton salircat){
        this.salircat = salircat;
    }

    public JButton getRefres() {
        return refres;
    }

    public void setRefres(JButton refres) {
        this.refres = refres;
    }
  
    
/*******************************/
/*          Constructor        */
/* **************************  */
    
    /**
     * Constructor de clase CatPaciente, una de su funcionalidad importante es la de inicializar los components en la ventana, con el metodo initComponents()
     * En el tambien se centra la ventana en medio de la pantalla principal. 
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public CatPaciente(Frame parent, boolean modal) {
        super(parent,modal);
        initComponents();
        this.setLocationRelativeTo(principal);
        this.setTitle("Paciente");
        this.setResizable(false);
        this.setModal(modal);
    }
     
    /**
     * Constructor de clase CatPaciente, una de su funcionalidad importante es la de inicializar los components en la ventana, con el metodo initComponents()
     * En el tambien se centra la ventana en medio de la pantalla principal. 
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     * @param afiliado, para indicar si es afiliado si o no el paciente... 
     */
    public CatPaciente(Frame parent, boolean modal, boolean afiliado) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(principal);
        this.afiliado = afiliado;
        this.setTitle("Paciente");
        this.setResizable(false);
        this.setModal(modal);
    }
    
    /**
     * Constructor de clase CatPaciente, una de su funcionalidad importante es la de inicializar los components en la ventana, con el metodo initComponents()
     * En el tambien se centra la ventana en medio de la pantalla principal. 
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     * @param deconsulta , un entero para indicar si proviene desde el objecto consulta...
     */
    public CatPaciente(Frame parent, boolean modal, int deconsulta) {
        super(parent, modal);
//        this.idusuario = idusuario;
        initComponents();
        setLocationRelativeTo(principal);
        if(deconsulta != 0){
            this.deconsulta = true;
        }
        this.setTitle("Paciente");
        this.setResizable(false);
        this.setModal(modal);
    }
    
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
        Catpaciente = new javax.swing.JTable();
        salircat = new javax.swing.JButton();
        nuevopaciente = new javax.swing.JButton();
        panel2 = new org.edisoncor.gui.panel.Panel();
        Leyenda = new javax.swing.JLabel();
        refres = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(690, 426));
        setPreferredSize(new java.awt.Dimension(690, 426));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        cat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        cat.setMaximumSize(new java.awt.Dimension(690, 426));
        cat.setMinimumSize(new java.awt.Dimension(485, 426));
        cat.setPreferredSize(new java.awt.Dimension(690, 426));

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

        Catpaciente.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Catpaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cédula", "Nombre", "Apellido", "Tipo Paciente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        salircat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        salircat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        nuevopaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/registrar.png"))); // NOI18N
        nuevopaciente.setText("Nuevo");
        nuevopaciente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        nuevopaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));


        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-pacientes.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(485, 75));
        panel2.setMinimumSize(new java.awt.Dimension(485, 75));
        panel2.setPreferredSize(new java.awt.Dimension(485, 75));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(255, 255, 255));

        refres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/refrescar.png"))); // NOI18N
        refres.setText("Refrescar");
        refres.setToolTipText("");
        refres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout catLayout = new javax.swing.GroupLayout(cat);
        cat.setLayout(catLayout);
        catLayout.setHorizontalGroup(
            catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
            .addGroup(catLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, catLayout.createSequentialGroup()
                        .addComponent(nuevopaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salircat, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addComponent(Textbusqueda))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(catLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Leyenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        catLayout.setVerticalGroup(
            catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(catLayout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Textbusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(catLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevopaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salircat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        String Leyend = "Busque el tipo de paciente...";
        this.getLeyenda().setText(Leyend);
    }//GEN-LAST:event_TextbusquedaFocusGained

    private void TextbusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextbusquedaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_TextbusquedaKeyReleased

    private void TextbusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextbusquedaKeyTyped

    }//GEN-LAST:event_TextbusquedaKeyTyped

    private void CatpacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatpacienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CatpacienteKeyReleased

    private void CatpacienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatpacienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_CatpacienteKeyTyped

    private void nuevopacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevopacienteActionPerformed

    }//GEN-LAST:event_nuevopacienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable Catpaciente;
    private javax.swing.JLabel Leyenda;
    private javax.swing.JTextField Textbusqueda;
    private org.edisoncor.gui.panel.Panel cat;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton nuevopaciente;
    private org.edisoncor.gui.panel.Panel panel2;
    private javax.swing.JButton refres;
    private javax.swing.JButton salircat;
    // End of variables declaration//GEN-END:variables
}
