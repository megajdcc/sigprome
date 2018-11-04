/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.CatPotencia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.ModeloPotencia;
import Vista.Potencia;
import Vista.Principal;
import java.awt.Color;
import static java.awt.Color.orange;
import static java.awt.Color.white;
import javax.swing.JOptionPane;

/**
 *
 * @author Jnatn'h
 */
public class ControladorPotencia implements ActionListener {
    Potencia vista;
    Principal principal;
    CatPotencia cat;
    ModeloPotencia modelo;
    private String nombre;
    
    
     public ControladorPotencia(Potencia vista)
    {
        this.vista = vista;
    }
     public ControladorPotencia(CatPotencia cat)
     {
         this.cat = cat;
     }
    
     
     
    @Override
      public void actionPerformed(ActionEvent evento) 
      {
         if(evento.getSource()==vista.getRegistrar()){
            if (vista.getTextpotencia().getText().isEmpty()) {
                this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
            else if(vista.getTextpotencia().getText().contains("  "))
            {
                this.parespacios();
            }
             else
             
            if(vista.getTextpotencia().getText().length() < 4){                
                this.menorletras();
            }
            
            else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            
             if (vista.getTextpotencia().getText().isEmpty()) {
                this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
            else if(vista.getTextpotencia().getText().contains("  "))
            {
                this.parespacios();
            }
             else
             
            if(vista.getTextpotencia().getText().length() < 4){                
                this.menorletras();
            }else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                
                String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar la Potencia?", "Potencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {
            codigoBotonEliminar();
            }
                 
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatPotencia(principal, true);
                cat.setVisible(true);
            }
      }
      
      public void codigoBotonregistrar(){
        
        String nomb = vista.getTextpotencia().getText();

               modelo = new ModeloPotencia(nomb);
                boolean existe = modelo.verificar(nomb);
                if (existe == true) {
                    String leyenda = "No se pudo registrar la Potencia porque ya existe";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(Color.red);
                }else{
                    modelo = new ModeloPotencia(nomb);
                    boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nomb;
                       // modelo = new ModeloPatologia(nombre, descripcion);
                        String Leyenda = "Potencia "+nomb+" Registrada";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                    String leyenda = "No se pudo registrar la Potencia";
                    vista.getLeyenda().setText(leyenda);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
      
      public void codigoBotonModificar(){
       String nomb = vista.getTextpotencia().getText();
       int idpotencia = vista.getIdpotencia();
      // System.out.println(idpatologia);
       
        modelo = new ModeloPotencia(nomb);
        boolean modificado=modelo.modificarModelo(nomb, idpotencia);
        if (modificado){
            this.nombre = nomb;
            String Leyenda = "Potencia "+nomb+" modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            String Leyenda = "Potencia "+nomb+" no modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
      
      public void codigoBotonEliminar(){
        String nomb = vista.getTextpotencia().getText();
 
        modelo = new ModeloPotencia();
        boolean eliminado=modelo.eliminarModelo(nomb);
        if (eliminado){
            this.nombre = nomb;
            String Leyenda = "Potencia "+nomb+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
           
        }else{
            String Leyenda = "La Potencia "+nomb+"  no fue eliminada";
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
     String leyenda = "¡No puede registras espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 } 
      
    public void codigoBotonLimpiar(){
        vista.getTextpotencia().setText("");        
        vista.getRegistrar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false); 
        
       
//        vista.setVisible(false);
//        cat = new catapatologia(principal, true);
//        cat.show();
       

        
        
    }//fin de codigoLimpiar
    
    public void habilitar(){
        vista.getTextpotencia().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
}
