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
import Controlador.ControladorTriaje;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JTextArea;
/**
 *
 * @author Jnatn'h
 */
public class Triaje extends java.awt.Dialog {

    ControladorTriaje controlador;
    
    // Getter y setter... 
    // paciente...
    public void setCedula(JFormattedTextField cedula){
        this.cedula = cedula;
    }
    public JFormattedTextField getCedula(){
        return this.cedula;
    }
    public void setNombre(JTextField nombre){
        this.Nombre = nombre;
    }
    public JTextField getNombre(){
        return this.Nombre;
    }
    public void setApellido(JTextField apellido){
        this.Apellido = apellido;
    }
    public JTextField getApellido(){
        return this.Apellido;
    }
    public void setTipopaciente(JTextField tipopaciente){
        this.Tipopaciente = tipopaciente;
    }
    public JTextField getTipopaciente(){
        return this.Tipopaciente;
    }
    // usuario...
    public void setEnfermera(JLabel nombre){
        this.enfermera = nombre;
    }
    public JLabel getEnfermera(){
        return this.enfermera;
    }
    // Movimiento...
    
    public void setPeso(JFormattedTextField peso){
        this.peso = peso;
    }
    public JFormattedTextField getPeso(){
        return this.peso;
    }
     public void setTalla(JFormattedTextField talla){
        this.talla = talla;
    }
    public JFormattedTextField getTalla(){
        return this.talla;
    }
    public void setTemperatura(JFormattedTextField temperatura){
        this.temperatura = temperatura;
    }
    public JFormattedTextField getTemperatura(){
        return this.temperatura;
    }
    public void setTension(JFormattedTextField tension){
        this.tension = tension;
    }
    public JFormattedTextField getTension(){
        return this.tension;
    }
    public void setglicemia(JFormattedTextField glicemia){
        this.glicemia = glicemia;
    }
    public JFormattedTextField getGlicemia(){
        return this.glicemia;
    }
    public void setObservacion(JTextArea observacion){
        this.observacion = observacion;
    }
    public JTextArea getObservacion(){
        return this.observacion;
    }
    
    // Botones...
    public void setConsultar(JButton consultar){
        this.consultar = consultar;
    }
    public JButton getConsultar(){
        return this.consultar;
    }
    public void setProcesar(JButton procesar){
        this.Procesar = procesar;
    }
    public JButton getProcesar(){
        return this.Procesar;
    }
    public void setSalir(JButton salir){
        this.Salir = salir;
    }
    public JButton getSalir(){
        return this.Salir;
    }
    
    // Leyenda..
    public void setLeyenda(JLabel leyenda){
        this.Leyenda = leyenda;
    }
    public JLabel getLeyenda(){
        return this.Leyenda;
    }
    public void setController(ControladorTriaje controlador){
      this.controlador = controlador;
      this.Oyentes();
    }
    public ControladorTriaje getController(){
      return this.controlador;
    }
    private void Oyentes(){
        getConsultar().addActionListener(controlador);
        getProcesar().addActionListener(controlador);
        getSalir().addActionListener(controlador);
    }
    /**
     * Creates new form Triaje
     * @param parent, una contenedor de primer nivel como lo es Frame. 
     * @param modal, si queremos que sea modal o no ... 
     */
    public Triaje(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setTitle("Triage");
        setController(new ControladorTriaje(this));      
        this.centraVentana();
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

        panel5 = new org.edisoncor.gui.panel.Panel();
        idpatologia4 = new java.awt.TextField();
        Procesar = new javax.swing.JButton();
        Salir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cedula = new javax.swing.JFormattedTextField();
        Nombre = new javax.swing.JTextField();
        Apellido = new javax.swing.JTextField();
        Tipopaciente = new javax.swing.JTextField();
        consultar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        peso = new javax.swing.JFormattedTextField();
        talla = new javax.swing.JFormattedTextField();
        temperatura = new javax.swing.JFormattedTextField();
        tension = new javax.swing.JFormattedTextField();
        glicemia = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacion = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        Leyenda = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        enfermera = new javax.swing.JLabel();
        historiatriaje = new javax.swing.JButton();
        panel2 = new org.edisoncor.gui.panel.Panel();

        setMaximumSize(new java.awt.Dimension(774, 519));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        panel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/fondo-registros.png"))); // NOI18N
        panel5.setMaximumSize(new java.awt.Dimension(774, 519));

        idpatologia4.setVisible(false);

        Procesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/Floppy-Small-icon.png"))); // NOI18N
        Procesar.setText("Procesar");
        Procesar.setToolTipText("");
        Procesar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Procesar.setEnabled(false);
        Procesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcesarActionPerformed(evt);
            }
        });

        Salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/salir1.1.png"))); // NOI18N
        Salir.setText("Salir");
        Salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cédula:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido:");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tipo de paciente:");

        try {
            cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("V-##.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cedula.setEnabled(false);

        Nombre.setEnabled(false);

        Apellido.setEnabled(false);

        Tipopaciente.setEnabled(false);

        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/buscar.png"))); // NOI18N
        consultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Peso:");
        jLabel1.setToolTipText("");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Talla:");
        jLabel6.setToolTipText("");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Temperatura:");
        jLabel7.setToolTipText("");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Tensión:");
        jLabel8.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Glicemia:");
        jLabel9.setToolTipText("");

        try {
            peso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("Kg:##.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        peso.setEnabled(false);
        peso.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pesoFocusGained(evt);
            }
        });

        try {
            talla.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("Mtrs:#.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        talla.setEnabled(false);
        talla.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tallaFocusGained(evt);
            }
        });

        try {
            temperatura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("## .# Cº")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        temperatura.setEnabled(false);
        temperatura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                temperaturaFocusGained(evt);
            }
        });

        try {
            tension.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###/##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tension.setEnabled(false);
        tension.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tensionFocusGained(evt);
            }
        });

        try {
            glicemia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###mg/dl")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        glicemia.setToolTipText("Gliecemia");
        glicemia.setEnabled(false);
        glicemia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                glicemiaFocusGained(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Observación:");
        jLabel10.setToolTipText("");

        observacion.setColumns(20);
        observacion.setRows(5);
        observacion.setEnabled(false);
        observacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                observacionFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(observacion);

        Leyenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Leyenda.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("HelveticaLT", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Paciente");
        jLabel11.setToolTipText("");

        enfermera.setFont(new java.awt.Font("HelveticaLT", 1, 18)); // NOI18N
        enfermera.setForeground(new java.awt.Color(255, 255, 255));
        enfermera.setText("Enfermera/o");
        enfermera.setToolTipText("");

        historiatriaje.setText("Historia de triaje");
        historiatriaje.setEnabled(false);

        panel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/imagen/header-triage.png"))); // NOI18N
        panel2.setMaximumSize(new java.awt.Dimension(774, 75));
        panel2.setMinimumSize(new java.awt.Dimension(774, 75));
        panel2.setPreferredSize(new java.awt.Dimension(774, 75));

        javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Tipopaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idpatologia4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(74, 74, 74)
                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panel5Layout.createSequentialGroup()
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addGap(52, 52, 52)
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panel5Layout.createSequentialGroup()
                                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panel5Layout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addGap(604, 604, 604))
                                        .addGroup(panel5Layout.createSequentialGroup()
                                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel5Layout.createSequentialGroup()
                                                    .addComponent(jLabel11)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(enfermera, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panel5Layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(Nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                                        .addComponent(Apellido))
                                                    .addGap(49, 49, 49)
                                                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(panel5Layout.createSequentialGroup()
                                                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel6)
                                                                .addComponent(jLabel1))
                                                            .addGap(72, 72, 72)
                                                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(peso, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(talla, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(panel5Layout.createSequentialGroup()
                                                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel7)
                                                                .addComponent(jLabel8)
                                                                .addComponent(jLabel9))
                                                            .addGap(18, 18, 18)
                                                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(glicemia)
                                                                .addComponent(tension)
                                                                .addComponent(temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addGroup(panel5Layout.createSequentialGroup()
                                                    .addGap(0, 0, Short.MAX_VALUE)
                                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(18, 18, 18)))
                                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panel5Layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(panel5Layout.createSequentialGroup()
                                            .addComponent(historiatriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(Procesar)
                                            .addGap(50, 50, 50)
                                            .addComponent(Salir))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel5Layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(enfermera))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel5Layout.createSequentialGroup()
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel2))
                            .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(consultar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(idpatologia4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Tipopaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))))
                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panel5Layout.createSequentialGroup()
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(peso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(3, 3, 3)
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(talla, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(temperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(tension, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)
                            .addComponent(jLabel9))
                        .addComponent(glicemia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Procesar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(historiatriaje, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Leyenda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void ProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcesarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProcesarActionPerformed

    private void pesoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pesoFocusGained
        String leyenda = "Peso del paciente";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_pesoFocusGained

    private void tallaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tallaFocusGained
        String leyenda = "Talla del paciente";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_tallaFocusGained

    private void temperaturaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_temperaturaFocusGained
        String leyenda = "Temperatura del paciente, si está por encima de los 37 grados esta fuera de lo normal...";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_temperaturaFocusGained

    private void tensionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tensionFocusGained
        String leyenda = "Tensión del paciente...";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_tensionFocusGained

    private void glicemiaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_glicemiaFocusGained
        String leyenda = "Glicemia del paciente...";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_glicemiaFocusGained

    private void observacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_observacionFocusGained
        String leyenda = "Ingresar alguna observación que, allá tenido con el paciente, es opcional hacerlo... ";
        getLeyenda().setText(leyenda);
    }//GEN-LAST:event_observacionFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido;
    public javax.swing.JLabel Leyenda;
    private javax.swing.JTextField Nombre;
    private javax.swing.JButton Procesar;
    private javax.swing.JButton Salir;
    private javax.swing.JTextField Tipopaciente;
    private javax.swing.JFormattedTextField cedula;
    private javax.swing.JButton consultar;
    private javax.swing.JLabel enfermera;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JFormattedTextField glicemia;
    private javax.swing.JButton historiatriaje;
    public java.awt.TextField idpatologia4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea observacion;
    private org.edisoncor.gui.panel.Panel panel2;
    private org.edisoncor.gui.panel.Panel panel5;
    private javax.swing.JFormattedTextField peso;
    private javax.swing.JFormattedTextField talla;
    private javax.swing.JFormattedTextField temperatura;
    private javax.swing.JFormattedTextField tension;
    // End of variables declaration//GEN-END:variables
}
