/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.Vias;
import Vista.Principal;
import Vista.CatVias;
import Modelo.Modelovias;
import java.awt.Color;
import static java.awt.Color.orange;
import static java.awt.Color.red;
import static java.awt.Color.yellow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jnatn'h
 */
public class ControladorVias implements ActionListener{
    Vias vista;
    Principal principal;
    CatVias cat;
    Modelovias modelo;
    private String nombre;
    
      public ControladorVias(Vias vista)
    {
        this.vista = vista;
    }
     public ControladorVias(CatVias cat)
     {
         this.cat = cat;
     }
     
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==vista.getRegistrar()){
            if (vista.getTextvias().getText().isEmpty()) {
                this.espacioblanco();
             }
            else if(vista.getTextvias().getText().contains("  "))
            {
                this.parespacios();
            }
            
           else if(vista.getTextvias().getText().length() < 4){
                
                this.menorletras();
            }
        
           else{codigoBotonregistrar();}
        }
            
        else if(e.getSource()==vista.getModificar()){
            
            if (vista.getTextvias().getText().isEmpty()) {
               this.espacioblanco();
             }
            else if(vista.getTextvias().getText().contains("  "))
            {
              this.parespacios();
            }
            
             if(vista.getTextvias().getText().length()< 4){            
              this.menorletras();
            }else{
                
                codigoBotonModificar();
             }
                             
            }else if(e.getSource()==vista.getEliminar()){
                
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar la Via?", "Via", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {
                codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(e.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatVias(principal, true);
                cat.setVisible(true);
            }
    }
           public void codigoBotonregistrar(){
         
        String nombre = vista.getTextvias().getText();
       
     
               modelo = new Modelovias(nombre);
               
                boolean existe = modelo.verificar(nombre);
                System.out.println(existe);
                if (existe) {
                    
                     JOptionPane.showMessageDialog(new JFrame(),"No se pudo registrar el tipo de vías porque ya existe","Incluir",JOptionPane.INFORMATION_MESSAGE);
                }else{
                modelo = new Modelovias(nombre);
                boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nombre;
                        String Leyenda = "Vía "+nombre+" registrada";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                         JOptionPane.showMessageDialog(new JFrame(),"Vía no incluida","Incluir",JOptionPane.ERROR_MESSAGE);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
       public void codigoBotonModificar(){
       String nombre = vista.getTextvias().getText();
       int idvias = vista.getIdVias();
      // System.out.println(idpatologia);
       
        modelo = new Modelovias(nombre);
        boolean modificado=modelo.modificarModelo(nombre, idvias);
        if (modificado){
            this.nombre = nombre;
            String Leyenda = "Vía "+nombre+" modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Vía no modificada","Modificar",JOptionPane.ERROR_MESSAGE);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
       public void codigoBotonEliminar(){
        String nombre = vista.getTextvias().getText();
 
        modelo = new Modelovias();
        boolean eliminado=modelo.eliminarModelo(nombre);
        if (eliminado){
            this.nombre = nombre;
            String Leyenda = "Vía "+nombre+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            //JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"La Vía no se pudo eliminar","Eliminar",JOptionPane.ERROR_MESSAGE);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
         public void codigoBotonLimpiar(){
        vista.getTextvias().setText("");
        
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
        vista.getTextvias().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar   
}
