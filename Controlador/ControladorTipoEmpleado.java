/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTipoEmpleado;
import Vista.Principal;
import Vista.TipoEmpleado;
import Vista.CatTipoempleado;
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
public class ControladorTipoEmpleado implements ActionListener{
    
    TipoEmpleado vista;
    Principal principal;
    CatTipoempleado cat;
    ModeloTipoEmpleado modelo; 
    private String nombre;
    
     public ControladorTipoEmpleado(TipoEmpleado vista)
    {
        this.vista = vista;
    }
     public ControladorTipoEmpleado(CatTipoempleado cat)
     {
         this.cat = cat;
     }
     
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==vista.getRegistrar()){
            if (vista.getTexttipoempleado().getText().isEmpty()) {
                this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
            else if (vista.getTexttipoempleado().getText().contains("  "))
            {
                this.parespacios();
            }
            
             else
            if(vista.getTexttipoempleado().getText().length() < 4){
            
              this.menorletras();
              
            }else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            if (vista.getTexttipoempleado().getText().isEmpty()) {
                this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
            else if (vista.getTexttipoempleado().getText().contains("  "))
            {
                this.parespacios();
            }
            
             else
             if(vista.getTexttipoempleado().getText().length() < 4){
                 this.menorletras();
            }else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                
                String si = "SI";
            String no = "NO";
            String cancelar = "Cancelar";
            Object[] options ={si,no,cancelar}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar el Tipo de Empleado?", "Tipo Empleado", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, cancelar); 
            if (respuesta == 0) {
                 codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatTipoempleado(principal, true);
                cat.setVisible(true);
            }
    }
     public void codigoBotonregistrar(){
     String nombre = vista.getTexttipoempleado().getText();
       
     
               modelo = new ModeloTipoEmpleado(nombre);
               
                boolean existe = modelo.verificar(nombre);
                System.out.println(existe);
                if (existe) {
                    
                     JOptionPane.showMessageDialog(new JFrame(),"No se pudo registrar el tipo de empleados porque ya existe","Incluir",JOptionPane.INFORMATION_MESSAGE);
                }else{
                modelo = new ModeloTipoEmpleado(nombre);
                boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nombre;
                        String Leyenda = "Tipo de empleado "+nombre+" registrado";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                         JOptionPane.showMessageDialog(new JFrame(),"Tipo de orden no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
        
      public void codigoBotonModificar(){
       String nombre = vista.getTexttipoempleado().getText();
       int idtipoempleado = vista.getIdTipoempleado();
      // System.out.println(idpatologia);
       
        modelo = new ModeloTipoEmpleado(nombre);
        boolean modificado=modelo.modificarModelo(nombre, idtipoempleado);
        if (modificado){
            this.nombre = nombre;
            String Leyenda = "Tipo de empleado "+nombre+" modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de empleado no modificado","Modificar",JOptionPane.ERROR_MESSAGE);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
        public void codigoBotonEliminar(){
        String nombre = vista.getTexttipoempleado().getText();
 
        modelo = new ModeloTipoEmpleado();
        boolean eliminado=modelo.eliminarModelo(nombre);
        if (eliminado){
            this.nombre = nombre;
            String Leyenda = "Tipo de empleado "+nombre+" Eliminado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            //JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de orden no eliminado","Eliminar",JOptionPane.ERROR_MESSAGE);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
    
 public void codigoBotonLimpiar(){
        vista.getTexttipoempleado().setText("");
        
        vista.getRegistrar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false); 

//        vista.setVisible(false);
//        cat = new catapatologia(principal, true);
//        cat.show();
             
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
        vista.getTexttipoempleado().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
    
}
