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

import Controlador.ControladorMedicamento;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import Modelo.ModeloMedicamento;
import java.awt.event.KeyEvent;

/**
 *
 * @author Jnatn'h
 */
public class Medicamento extends java.awt.Dialog {

    private ControladorMedicamento controlador;
    private int opction = 0;
    public void setController(ControladorMedicamento controller){
        this.controlador = controller;
        this.asignarListener();
    }
    public ControladorMedicamento getController(){
        return this.controlador;
    }
    private void asignarListener(){
//        medicamento.addKeyListener(getController());
        buscar.addActionListener(getController());
       // detalle.addKeyListener(getController());
        cantidad.addKeyListener(getController());
        cantidad.addMouseListener(getController());
        imprimir.addActionListener(getController());
        Registrar.addActionListener(getController());
        Modificar.addActionListener(getController());
        Salir.addActionListener(getController());      
    }
    public void setMedicamento(JTextField nombre){
        this.medicamento = nombre;
    }
    public JTextField getMedicamento(){
        return this.medicamento;
    }
    public void setDetalle(JTextArea detalle){
        this.detalle = detalle;
    }
    public JTextArea getDetalle(){
        return this.detalle;
    }
    public void setFechavencimiento(JDateChooser fecha){
        this.fechavencimiento = fecha;
    }
    public JDateChooser getFechavencimiento(){
        return this.fechavencimiento;
    }
    public void setCantidad(JSpinner cant){
        this.cantidad = cant;
    }
    public JSpinner getCantidad(){
        return this.cantidad;
    }
    public void setTipoproducto(JComboBox tipoproducto){
        this.tipoproducto = tipoproducto;
    }
    public JComboBox getTipoproducto(){
        return this.tipoproducto;
    }
    public void setPresentacion(JComboBox presentacion){
        this.presentacion = presentacion;
    }
    public JComboBox getPresentacion(){
        return this.presentacion;
    }
    public void setPrincipioActivo(JComboBox principio){
        this.principioactivo = principio;
    }
    public JComboBox getPrincipioActivo(){
        return this.principioactivo;
    }
    public void setPotencia(JComboBox potencia){
        this.potencia = potencia;
    }
    public JComboBox getPotencia(){
        return this.potencia;
    }
    public void setImprimir(JButton imprimir){
        this.imprimir = imprimir;
    }
    public JButton getimprimir(){
        return this.imprimir;
    }
    public void setRegistrar(JButton registrar){
        this.Registrar = registrar;
    }
    public JButton getRegistrar(){
        return this.Registrar;
    }
    public void setModificar(JButton modificar){
        this.Modificar = modificar;
    }
    public JButton getModificar(){
        return this.Modificar;
    }
    public void setSalir(JButton salir){
        this.Salir = salir;
    }
    public JButton getSalir(){
        return this.Salir;
    }
    public void setLeyenda(JLabel leyenda){
        this.Leyenda = leyenda;
    }
    public JLabel getLeyenda(){
        return this.Leyenda;
    }
    public void setBuscar(JButton buscar){
        this.buscar = buscar;
    }
    public JButton getBuscar(){
        return this.buscar;
    }
    
    /**
     * Creates new form Medicamento
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Medicamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setModal(modal);
        this.setResizable(false);
        setTitle("Medicamento");
        centraVentana();
        setController(new ControladorMedicamento(this));
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

        panel = new org.edisoncor.gui.panel.Panel();
        panel1 = new org.edisoncor.gui.panel.Panel();
        Registrar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Leyenda = new javax.swing.JLabel();
        medicamento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tipoproducto = new javax.swing.JComboBox<>();
        buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        detalle = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        presentacion = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        principioactivo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        potencia = new javax.swing.JComboBox<>();
        fechavencimiento = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cantidad = new javax.swing.JSpinner();
        imprimir = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        panel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        panel.setMaximumSize(new java.awt.Dimension(917, 428));
        panel.setPreferredSize(new java.awt.Dimension(917, 428));
        panel.setRequestFocusEnabled(false);

        panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-medicamento.png"))); // NOI18N
        panel1.setMaximumSize(new java.awt.Dimension(917, 75));
        panel1.setName(""); // NOI18N
        panel1.setPreferredSize(new java.awt.Dimension(917, 75));

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

        Registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
        Registrar.setText("Registrar");
        Registrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Registrar.setMaximumSize(new java.awt.Dimension(112, 30));
        Registrar.setMinimumSize(new java.awt.Dimension(112, 30));
        Registrar.setPreferredSize(new java.awt.Dimension(112, 30));
        Registrar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                RegistrarFocusGained(evt);
            }
        });

        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/modificar.png"))); // NOI18N
        Modificar.setText("Modificar");
        Modificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Modificar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Modificar.setMaximumSize(new java.awt.Dimension(112, 30));
        Modificar.setMinimumSize(new java.awt.Dimension(112, 30));
        Modificar.setPreferredSize(new java.awt.Dimension(112, 30));
        Modificar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ModificarFocusGained(evt);
            }
        });

        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Salir.setMaximumSize(new java.awt.Dimension(112, 30));
        Salir.setMinimumSize(new java.awt.Dimension(112, 30));
        Salir.setPreferredSize(new java.awt.Dimension(112, 30));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre:");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Descripción:");

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(254, 254, 254));

        medicamento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                medicamentoFocusGained(evt);
            }
        });
        medicamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                medicamentoKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tipo de producto:");

        tipoproducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tipoproductoFocusGained(evt);
            }
        });

        buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/buscar.png"))); // NOI18N
        buscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        detalle.setColumns(20);
        detalle.setLineWrap(true);
        detalle.setRows(5);
        detalle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                detalleFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(detalle);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Presentación:");

        presentacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                presentacionFocusGained(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Principio Activo:");

        principioactivo.setToolTipText("");
        principioactivo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                principioactivoFocusGained(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Potencia:");

        potencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                potenciaFocusGained(evt);
            }
        });

        fechavencimiento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fechavencimientoFocusGained(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("F-vencimiento:");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Cant:");

        cantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cantidadFocusGained(evt);
            }
        });

        imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/imprimir.png"))); // NOI18N
        imprimir.setText("Imprimir");
        imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        imprimir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                imprimirFocusGained(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelLayout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imprimir)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(fechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                            .addComponent(medicamento, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(principioactivo, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(potencia, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(presentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tipoproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medicamento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tipoproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(presentacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(principioactivo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(potencia)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fechavencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        add(panel, java.awt.BorderLayout.CENTER);
        panel.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void medicamentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_medicamentoKeyTyped

            if(getMedicamento().getText().length() > 100){
                evt.consume();
            }
    }//GEN-LAST:event_medicamentoKeyTyped

    private void medicamentoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_medicamentoFocusGained
        String leyenda = "Nombre del medicamento, sea explicito";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_medicamentoFocusGained

    private void detalleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_detalleFocusGained
        String leyenda = "Características del medicamento.";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_detalleFocusGained

    private void fechavencimientoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fechavencimientoFocusGained
        String leyenda = "Fecha de vencimiento, la misma no debe estar vencida.";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_fechavencimientoFocusGained

    private void cantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cantidadFocusGained
        String leyenda = "Existencia del producto, no puede ser menor a 0.";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_cantidadFocusGained

    private void tipoproductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipoproductoFocusGained
        String leyenda = "Seleccione un tipo de productos";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_tipoproductoFocusGained

    private void presentacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_presentacionFocusGained
        String leyenda = "Seleccione una presentación";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_presentacionFocusGained

    private void principioactivoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_principioactivoFocusGained
        String leyenda = "Seleccione un principio activo";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_principioactivoFocusGained

    private void potenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_potenciaFocusGained
        String leyenda = "Seleccione una potencia";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_potenciaFocusGained

    private void imprimirFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_imprimirFocusGained
        String leyenda = "Imprima con detalle la información de este medicamento";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_imprimirFocusGained

    private void RegistrarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_RegistrarFocusGained
        String leyenda = "Guarde el nuevo medicamento";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_RegistrarFocusGained

    private void ModificarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ModificarFocusGained
        String leyenda = "Modifique los datos del medicamento";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_ModificarFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Leyenda;
    private javax.swing.JButton Modificar;
    private javax.swing.JButton Registrar;
    private javax.swing.JButton Salir;
    private javax.swing.JButton buscar;
    private javax.swing.JSpinner cantidad;
    private javax.swing.JTextArea detalle;
    private com.toedter.calendar.JDateChooser fechavencimiento;
    private javax.swing.JButton imprimir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField medicamento;
    private org.edisoncor.gui.panel.Panel panel;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JComboBox<String> potencia;
    private javax.swing.JComboBox<String> presentacion;
    private javax.swing.JComboBox<String> principioactivo;
    private javax.swing.JComboBox<String> tipoproducto;
    // End of variables declaration//GEN-END:variables
}
