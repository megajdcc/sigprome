/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTiposervicios;
import Vista.Principal;
import Vista.TipoServicios;
import Vista.CatTiposervicios;
import java.awt.Color;
import static java.awt.Color.orange;
import static java.awt.Color.red;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Jnatn'h
 */
public class ControladorTipoServicios implements ActionListener {
    TipoServicios vista;
    Principal principal;
    CatTiposervicios cat;
    ModeloTiposervicios modelo; 
    private String nombre;
    
      public ControladorTipoServicios(TipoServicios vista)
    {
        this.vista = vista;
    }
     public ControladorTipoServicios(CatTiposervicios cat)
     {
         this.cat = cat;
     }
     @Override
      public void actionPerformed(ActionEvent evento) 
      {
          if(evento.getSource()==vista.getRegistrar()){
              
            if (vista.getTexttiposervicios().getText().isEmpty()) {
               this.espacioblanco();
             }
            
            else if(vista.getTexttiposervicios().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            
            if(vista.getTexttiposervicios().getText().length() < 4){
               this.menorletras();
            }
            else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            if (vista.getTexttiposervicios().getText().isEmpty()) {
               this.espacioblanco();
             }
            
            else if(vista.getTexttiposervicios().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            
             if(vista.getTexttiposervicios().getText().length() <4){
               this.menorletras();
            }else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar el tipo de servicio", "Tipo de servicios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {                
               codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatTiposervicios(principal, true);
                cat.setVisible(true);
            }
      } 
      public void codigoBotonregistrar(){
        
         /*/tomar datos del paciente/*/   
        String nombre = vista.getTexttiposervicios().getText().toUpperCase();
       
     
               modelo = new ModeloTiposervicios(nombre);
               
                boolean existe = modelo.verificar(nombre);
                System.out.println(existe);
                if (existe) {
                    
                     JOptionPane.showMessageDialog(new JFrame(),"No se pudo registrar el tipo de servicio porque ya existe","Incluir",JOptionPane.INFORMATION_MESSAGE);
                }else{
                modelo = new ModeloTiposervicios(nombre);
                boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nombre;
                        String Leyenda = "Tipo de servicio "+nombre+" ha sido registrado";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                         JOptionPane.showMessageDialog(new JFrame(),"Tipo de servicio no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
       public void codigoBotonModificar(){
       String nombre = vista.getTexttiposervicios().getText();
       int idtiposervicios = vista.getIdTiposervicios();
      // System.out.println(idpatologia);
       
        modelo = new ModeloTiposervicios(nombre);
        boolean modificado=modelo.modificarModelo(nombre, idtiposervicios);
        if (modificado){
            this.nombre = nombre;
            String Leyenda = "Tipo de servicio "+nombre+" ha sido modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de servicio no modificado","Modificar",JOptionPane.ERROR_MESSAGE);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
         public void codigoBotonEliminar(){
        String nombre = vista.getTexttiposervicios().getText();
 
        modelo = new ModeloTiposervicios();
        boolean eliminado=modelo.eliminarModelo(nombre);
        if (eliminado){
            this.nombre = nombre;
            String Leyenda = "Tipo de servicio "+nombre+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            //JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de servicio no eliminado","Eliminar",JOptionPane.ERROR_MESSAGE);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
        public void codigoBotonLimpiar(){
        vista.getTexttiposervicios().setText("");
        
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
        vista.getTexttiposervicios().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
}
