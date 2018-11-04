/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPactivo;
import Vista.Catprincipioactivo;
import Vista.Pactivo;
import Vista.Principal;
import java.awt.Color;
import static java.awt.Color.orange;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Jnatn'h
 */
public class ControladorPactivo implements ActionListener{
    Pactivo vista;
    Principal principal;
    Catprincipioactivo cat;
    ModeloPactivo modelo;
    private String nombre;
    
    
     public ControladorPactivo(Pactivo vista)
    {
        this.vista = vista;
    }
     public ControladorPactivo(Catprincipioactivo cat)
     {
         this.cat = cat;
     }
    
     
     
    @Override
      public void actionPerformed(ActionEvent evento) 
      {
         if(evento.getSource()==vista.getRegistrar()){
             if (vista.getTextpactivo().getText().isEmpty()) {
                this.espacioblanco();
                //  vista.getTextpatologia().requestFocus(); 
             }
             else if(vista.getTextpactivo().getText().contains("  "))
             {
                 this.parespacios();
             }
             else            
            if(vista.getTextpactivo().getText().length() < 4){
                this.menorletras();    
            }
            
            else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            if (vista.getTextpactivo().getText().isEmpty()) {
                this.espacioblanco();
                //  vista.getTextpatologia().requestFocus(); 
             }
             else if(vista.getTextpactivo().getText().contains("  "))
             {
                 this.parespacios();
             }
             else if(vista.getTextpactivo().getText().length() < 4){
                this.menorletras();  
            }
            else{codigoBotonModificar();}
                             
            }else if(evento.getSource()==vista.getEliminar()){
                
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar el Principio Activo?", "Principio Activo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {
                 codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new Catprincipioactivo(principal, true);
                cat.setVisible(true);
            }
      }
      
      public void codigoBotonregistrar(){
        
        String nomb = vista.getTextpactivo().getText();

               modelo = new ModeloPactivo(nomb);
                boolean existe = modelo.verificar(nomb);
                if (existe == true) {
                    String leyenda = "No se pudo registrar el Principio Activo porque ya existe";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(Color.YELLOW);
                }else{
                    modelo = new ModeloPactivo(nomb);
                    boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nomb;
                        String Leyenda = "El Principio Activo "+nomb+" Registrado";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                       
                    }else{
                    String leyenda = "No se pudo registrar el Principio Activo";
                    vista.getLeyenda().setText(leyenda);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
      
      public void codigoBotonModificar(){
       String nomb = vista.getTextpactivo().getText();
       int idpactivo = vista.getIdpactivo();
      
        modelo = new ModeloPactivo(nomb);
        boolean modificado=modelo.modificarModelo(nomb, idpactivo);
        if (modificado){
            this.nombre = nomb;
            String Leyenda = "Principio Activo "+nomb+" modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
        }else{
            String Leyenda = "Principio Activo "+nomb+" no modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
      
      public void codigoBotonEliminar(){
        String nomb = vista.getTextpactivo().getText();
 
        modelo = new ModeloPactivo();
        boolean eliminado=modelo.eliminarModelo(nomb);
        if (eliminado){
            this.nombre = nomb;
            String Leyenda = "Principio Activo "+nomb+" Eliminao";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
           
        }else{
            String Leyenda = "El Principio Activo "+nomb+"  no fue eliminado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
      
//    public void codigoBotonConsultar(){
//        modelo = new ModeloPatologia();
//            boolean encontrado = modelo.consultarModelo(vista.getTextpotencia().getText());
//            
//            if (encontrado){
//                vista.getTextpotencia().setText(modelo.getNombre());
//               // vista.getIdPatologia().setIdPatologia(modelo.getId());
//                int idpotencia = modelo.getId();
//                String Idpatologia = Integer.toString(idpotencia);
//                vista.setIdpotencia(Idpotencia);
//                
//              
//                //Acción de los botones y cajas de texto 
//                vista.getModificar().setEnabled(true);
//                vista.getEliminar().setEnabled(true);
//            
//            }
//            else
//            {
//                vista.getRegistrar().setEnabled(true);
//                JOptionPane.showMessageDialog(new JFrame(),"Patologia no encontrada","Consulta",JOptionPane.INFORMATION_MESSAGE);
//            }
//            habilitar();
//    } // Fin del consultar... 
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
      
    public void codigoBotonLimpiar(){
        vista.getTextpactivo().setText("");        
        vista.getRegistrar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false); 
        
       
//        vista.setVisible(false);
//        cat = new catapatologia(principal, true);
//        cat.show();
       

        
        
    }//fin de codigoLimpiar
    
    public void habilitar(){
        vista.getTextpactivo().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
}
