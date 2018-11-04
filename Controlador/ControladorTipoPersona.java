/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTipoPersona;
import Vista.Principal;
import Vista.TipoPersona;
import Vista.CatTipopersona;
import java.awt.Color;
import static java.awt.Color.orange;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jnatn'h
 */
public class ControladorTipoPersona implements ActionListener{
    TipoPersona vista;
    Principal principal;
    CatTipopersona cat;
    ModeloTipoPersona modelo;
    private boolean validafechanac = false;
    private String nombre;
    
     public ControladorTipoPersona(TipoPersona vista)
    {
        this.vista = vista;
    }
     public ControladorTipoPersona(CatTipopersona cat)
     {
         this.cat = cat;
     }

    @Override
    public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getRegistrar()){
             
             if (vista.getTexttipopersona().getText().isEmpty()) {
                this.espacioblanco();
             }
             
             else if (vista.getTexttipopersona().getText().contains("  "))
             {
               this.parespacios();
             }
             else
            
            if(vista.getTexttipopersona().getText().length() <4){
              this.menorletras();
                
            }else{
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Este tipo de paciente obligatoriamente tiene que ser mayor de edad?", "Tipo Paciente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {
                this.validafechanac = true;  
                codigoBotonregistrar();
                
            }else{
                this.validafechanac = false; 
                codigoBotonregistrar();
            }   
            }
        }else if(evento.getSource() == vista.getModificar()){
            
            if (vista.getTexttipopersona().getText().isEmpty()) {
                this.espacioblanco();
             }
             
             else if (vista.getTexttipopersona().getText().contains("  "))
             {
               this.parespacios();
             }
             else
            
             if(vista.getTexttipopersona().getText().length() <4){
              this.menorletras();
                
            }else{
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Este tipo de paciente obligatoriamente tiene que ser mayor de edad?", "Tipo Paciente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options);  
           if (respuesta == 0) {
                validafechanac = true;  
                codigoBotonModificar();
               
            }else{
               validafechanac = false; 
                codigoBotonModificar();
                
            }
                
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar el Tipo Paciente?", "Tipo Paciente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
             
           if (respuesta == 0) {
                 codigoBotonEliminar();
            }

          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                vista.dispose();
                cat = new CatTipopersona(principal, true);
                cat.setVisible(true);
            }
    }
     public void codigoBotonregistrar(){   
         /*/tomar datos del paciente/*/   
        String nombre = vista.getTexttipopersona().getText();
        modelo = new ModeloTipoPersona();
        boolean existe = modelo.verificar(nombre);
        if (existe) {               
             JOptionPane.showMessageDialog(new JFrame(),"No se pudo registrar el tipo de Paciente porque ya existe","Incluir",JOptionPane.INFORMATION_MESSAGE);
        }else{
        modelo = new ModeloTipoPersona(nombre,validafechanac);
        boolean incluido = modelo.incluirModelo();
            if (incluido){
                this.nombre = nombre;
                String Leyenda = "Tipo de Paciente "+nombre+" registrado";
                vista.getLeyenda().setText(Leyenda);
                vista.getLeyenda().setForeground(Color.green);
                //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
            }else{
                 JOptionPane.showMessageDialog(new JFrame(),"Tipo Paciente no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
        }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
    public void codigoBotonModificar(){
       String nombre = vista.getTexttipopersona().getText();
       int idtipopersona = vista.getIdTipopersona();
        modelo = new ModeloTipoPersona();
        boolean modificado=modelo.modificarModelo(nombre, idtipopersona,this.validafechanac);
        if (modificado){
            this.nombre = nombre;
            String Leyenda = "Tipo de Paciente "+nombre+" modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de Paciente no modificado","Modificar",JOptionPane.ERROR_MESSAGE);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
        public void codigoBotonEliminar(){
        String nombre = vista.getTexttipopersona().getText();

        modelo = new ModeloTipoPersona();
        boolean eliminado=modelo.eliminarModelo(nombre);
        if (eliminado){
            this.nombre = nombre;
            String Leyenda = "Tipo de Paciente "+nombre+" Eliminado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            //JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de Paciente no eliminado","Eliminar",JOptionPane.ERROR_MESSAGE);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
        public void codigoBotonLimpiar(){
        vista.getTexttipopersona().setText("");
        
        vista.getRegistrar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false); 
            
    }//fin de codigoLimpiar
        public void parespacios()
 {
     String leyenda = "¡No puede registrar varios espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
 
 public void menorletras()
 {
     String leyenda = "Asegúrese de que los campo Nombre no sean menor a 4 letras!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
 public void espacioblanco()
 {
     String leyenda = "¡No puede registrar espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
        
        public void habilitar(){
        vista.getTexttipopersona().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
}
