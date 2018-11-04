/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControllerFevida;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.edisoncor.gui.panel.Panel;
/**
 *
 * @author Jnatn'h
 */
@SuppressWarnings("serial")
public class Fevida extends JDialog {
    
    /*******************************/
    /*      Field of class         */
    /*******************************/
	
    
    private ControllerFevida controller;
    private Panel header;
    private JTextField namepaciente;
    private JButton buscarpaciente,procesar,salir;
    private JTextArea condicion;
    private JLabel leyenda;
    private Principal principal;
    private void setListener(){
        buscarpaciente.addActionListener(controller);
        procesar.addActionListener(controller);
        salir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                setVisible(false);
            }
            
    });

        
    }
    public void setController(ControllerFevida control){
        this.controller = control;
        setListener();
    }

    public ControllerFevida getController(){
        return this.controller;
    }

    public JButton getBuscarpaciente() {
        return buscarpaciente;
    }

    public void setBuscarpaciente(JButton buscarpaciente) {
        this.buscarpaciente = buscarpaciente;
    }

    public JTextField getNamepaciente() {
        return namepaciente;
    }

    public void setNamepaciente(JTextField namepaciente) {
        this.namepaciente = namepaciente;
    }

    public JButton getProcesar() {
        return procesar;
    }

    public void setProcesar(JButton procesar) {
        this.procesar = procesar;
    }

    public JTextArea getCondicion() {
        return condicion;
    }

    public void setCondicion(JTextArea condicion) {
        this.condicion = condicion;
    }

    public JLabel getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(JLabel leyenda) {
        this.leyenda = leyenda;
    }
    
    
    
    
    
    /**
     * 
     * @param parent
     * @param modal 
     * @param controller 
     */
    public Fevida(JFrame parent, boolean modal){
        
        setModal(false);
        setTitle("Fe de Vida");
       
        setPreferredSize(new Dimension(500,500));
        setMinimumSize(getPreferredSize());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        contenedor();
        setLocationRelativeTo(principal);
    }
    
    
    private void contenedor(){
        JPanel contenedorbase = new JPanel();
        
        contenedorbase.setLayout(new BorderLayout());
        contenedorbase.setBackground(Color.white);
        header = new Panel();
        header.setPreferredSize(new Dimension(500,76));
        header.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/headerfevida.png")));
        
        contenedorbase.add(header,BorderLayout.NORTH);
        
        JPanel contenido = new JPanel();
        contenido.setBackground(Color.white);
        contenido.setPreferredSize(new Dimension(820,0));
        
        
        contenido.setLayout(new GridLayout(1,2));
        
        JPanel left = new JPanel();
        
        JLabel nameperson = new JLabel("Paciente:");
        nameperson.setPreferredSize(new Dimension(60,30));
        
        nameperson.setFont(new Font(Font.SERIF,Font.BOLD,14));
        
        left.add(nameperson);
        
        
        namepaciente = new JTextField();
        namepaciente.setPreferredSize(new Dimension(250,30));
        namepaciente.setEnabled(false);
        left.add(namepaciente);
        
        
        buscarpaciente = new JButton();
        
        buscarpaciente.setIcon(new ImageIcon(getClass().getResource("/Vista/imagen/buscar.png")));
       
        buscarpaciente.setBorderPainted(false);
        buscarpaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        buscarpaciente.setPreferredSize(new Dimension(50,30));
        buscarpaciente.setOpaque(true);
      
        left.add(buscarpaciente);
        
        JLabel condicionsalud = new JLabel("Condición de Salud");
        
        condicionsalud.setPreferredSize(new Dimension(400,30));
        condicionsalud.setFont(new Font(Font.SERIF,Font.BOLD,14));
        condicionsalud.setForeground(Color.black);
        
        left.add(condicionsalud);
        
        JPanel panelcenter = new JPanel();
        
        condicion = new JTextArea();
        condicion.setPreferredSize(new Dimension(400,300));
        condicion.setFont(new Font(Font.SERIF,Font.BOLD,12));
        condicion.setForeground(Color.black);
        condicion.setLineWrap(true);
        panelcenter.add(condicion);
        left.add(panelcenter);
        
        contenido.add(left);
          
        contenedorbase.add(contenido, BorderLayout.CENTER);
        
        JPanel footer = new JPanel();
        
        footer.setPreferredSize(new Dimension(410,100));
        footer.setLayout(new GridLayout(2,1));
        
        leyenda = new JLabel();
        leyenda.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,14));
        leyenda.setForeground(Color.black);
        leyenda.setPreferredSize(new Dimension(405,30));
        leyenda.setMinimumSize(leyenda.getPreferredSize());
        footer.add(leyenda);
        
        JPanel botonera = new JPanel();
        botonera.setPreferredSize(new Dimension(405,30));
        
        procesar = new JButton("Procesar");
        procesar.setFont(new Font(Font.SERIF,Font.BOLD,14));
        procesar.setForeground(Color.black);
        procesar.setPreferredSize(new Dimension(150,30));
        procesar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        procesar.setBorderPainted(false);
        botonera.add(procesar);
        
        salir = new JButton("Salir");
        salir.setFont(new Font(Font.SERIF,Font.BOLD,14));
        salir.setForeground(Color.black);
        salir.setPreferredSize(new Dimension(100,30));
        salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salir.setBorderPainted(false);
        botonera.add(salir);
        
        footer.add(botonera);
        contenedorbase.add(footer,BorderLayout.SOUTH);
        
        add(contenedorbase);
        
    }
    
    

    
}
