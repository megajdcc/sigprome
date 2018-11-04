/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloPatologia;
import Vista.Patologias;
import Vista.catapatologia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import static java.awt.Color.orange;
import Vista.Principal;

/**
 *
 * @author Jnatn'h
 */
public class ControladorPatologia implements ActionListener
{
    Patologias vista;
    Principal principal;
    catapatologia cat;
    ModeloPatologia modelo;
    private String nombre;

    public ControladorPatologia(Patologias vista){
        this.vista = vista;
      modelo = new ModeloPatologia();
    }
    public ControladorPatologia(catapatologia cat){
         this.cat = cat;
         modelo = new ModeloPatologia();
    }
    @Override
      public void actionPerformed(ActionEvent evento) {
         if(evento.getSource()==vista.getRegistrar()){
             
             
             if (vista.getTextpatologia().getText().isEmpty()){
                this.espacioblanco();
             }else if(vista.getTextpatologia().getText().contains("  ")){
                 this.parespacios();
             }else if(vista.getTextpatologia().getText().length() < 4){
                this.menorletras();
             }else if (vista.getdescripcion().getText().contains("  ")){
                 String leyendas = "¡No permitido varios espacios entre palabras en Descripción!";
                vista.getLeyenda().setText(leyendas);
                vista.getLeyenda().setForeground(orange);
             }else{
               codigoBotonregistrar();
             }
             
             
          //BOTON MODIFICAR  
        }else if(evento.getSource()==vista.getModificar()){
            
            if (vista.getTextpatologia().getText().isEmpty())// || this.validarcaracteres())
                 {
                this.espacioblanco();//               
             }
            
             else if(vista.getTextpatologia().getText().contains("  "))
             {
                this.parespacios();
             }
             
             else if(vista.getTextpatologia().getText().length() < 4){
                this.menorletras();
             } 
             else if (vista.getdescripcion().getText().contains("  "))
             {
                 this.parespacios();             
             }
             else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
              // MUESTRA EL MENSAJE SI DESEA ELIMINAR   
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar la Patología?", "Patologia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null, options); 
            if (respuesta == 0) {
                codigoBotonEliminar();
            }
            }else if(evento.getSource() == vista.getSalir())
            {
                if(vista.desdeconsulta){
                    vista.dispose();
                }else{
                vista.setVisible(false);
               vista.getBack().setVisible(true);
                }
            }
      }
      
      public void codigoBotonregistrar(){
         /*/tomar datos de la patologia.../*/   
        nombre = vista.getTextpatologia().getText().trim();
        String descripcion = vista.getdescripcion().getText();
           //System.out.println(nombre);
                
                boolean existe = modelo.verificar(nombre);
                if (existe) {
                     JOptionPane.showMessageDialog(new JFrame(),"No se pudo registrar la patología porque ya existe","Incluir",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    modelo.setNombre(nombre);
                    modelo.setDescripcion(descripcion);
                    boolean incluido = modelo.incluirModelo();
                    if (incluido){
                        
                       // modelo = new ModeloPatologia(nombre, descripcion);
                        String Leyenda = "Patología "+nombre+" Registrada";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                         JOptionPane.showMessageDialog(new JFrame(),"Patología no incluida","Incluir",JOptionPane.ERROR_MESSAGE);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
      
      public void codigoBotonModificar(){
       nombre = vista.getTextpatologia().getText();
       String descripcion = vista.getdescripcion().getText();
       int idpatologia = vista.getIdPatologia();
       
                modelo.setNombre(nombre);
                modelo.setDescripcion(descripcion);
        boolean modificado=modelo.modificarModelo(idpatologia);
        if (modificado){
            
            String Leyenda = "Patologia "+nombre+" modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Patología no modificada","Modificar",JOptionPane.ERROR_MESSAGE);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
      
      public void codigoBotonEliminar(){
        String nombre = vista.getTextpatologia().getText();
 
        modelo = new ModeloPatologia();
        boolean eliminado=modelo.eliminarModelo(nombre);
        if (eliminado){
            this.nombre = nombre;
            String Leyenda = "Patologia "+nombre+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            //JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Patología no eliminada, Esta siendo usada en registros ya hecho desde consulta","Eliminar",JOptionPane.ERROR_MESSAGE);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
      
    public void codigoBotonConsultar(){
        modelo = new ModeloPatologia();
            boolean encontrado = modelo.consultarModelo(vista.getTextpatologia().getText());
            
            if (encontrado){
                vista.getTextpatologia().setText(modelo.getNombre());
               // vista.getIdPatologia().setIdPatologia(modelo.getId());
                int idpatologia = modelo.getId();
                String Idpatologia = Integer.toString(idpatologia);
                vista.setIdPatologia(idpatologia);
                
              
                //Acción de los botones y cajas de texto 
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
            
            }
            else
            {
                vista.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Patología no encontrada","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
            habilitar();
    } // Fin del consultar... 
    public void codigoBotonLimpiar(){
        vista.getTextpatologia().setText("");
        vista.getdescripcion().setText("");
        
        vista.getRegistrar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false); 
        
       
//        vista.setVisible(false);
//        cat = new catapatologia(principal, true);
//        cat.show();
       

        
        
    }//fin de codigoLimpiar
    
    //VALIDAR CARACTERES AL SER PEGADO
     public boolean validarcaracteres()
     {
         boolean algo = false;
         String nombres = vista.getTextpatologia().getText(); 
         
         char nom[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','w','x','y','z'};
         if(!nombres.equals(String.valueOf(nom))){
             algo = true;
             String leyenda = "¡No puede registrar caraceteres en Nombre!";
                vista.getLeyenda().setText(leyenda);
                vista.getLeyenda().setForeground(orange);
         }
         return algo;
     }
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
        vista.getTextpatologia().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
            
}
