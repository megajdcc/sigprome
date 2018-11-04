/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloTipoorden;
import Vista.Principal;
import Vista.TipoOrden;
import Vista.cattiporden;
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
public class ControladorTipoOrden implements ActionListener{
    TipoOrden vista;
    Principal principal;
    cattiporden cat;
    ModeloTipoorden modelo; 
    private String nombre;
    public ControladorTipoOrden(TipoOrden vista){
        this.vista = vista;
    }
    public ControladorTipoOrden(cattiporden cat){
         this.cat = cat;
     }
    @Override
    public void actionPerformed(ActionEvent evento) {
          if(evento.getSource()==vista.getRegistrar()){
            if(vista.getTexttipoorden().getText().isEmpty()) {
                this.espacioblanco();
             }
            
            else if(vista.getTexttipoorden().getText().contains("  "))
            {
                this.parespacios();
            }
           else if (vista.getTexttipoorden().getText().length() < 4){
                
               this.menorletras();
            }
                           
            else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            
            if(vista.getTexttipoorden().getText().isEmpty()) {
               this.espacioblanco();
             }
            
            else if(vista.getTexttipoorden().getText().contains("  "))
            {
               this.parespacios();
            }
           else if (vista.getTexttipoorden().getText().length() < 4){
                
                this.menorletras();
            }
           else{   
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                String si = "SI";
                String no = "NO";
                Object[] options ={si,no}; 
                int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar el Tipo de Orden?", "Tipo de Orden", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null, options); 
                if (respuesta == 0) {
                codigoBotonEliminar();
            }
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new cattiporden(principal, true);
                cat.setVisible(true);
            }
      }
    public void codigoBotonregistrar(){
         /*/tomar datos del paciente/*/   
        String nombre = vista.getTexttipoorden().getText();
               modelo = new ModeloTipoorden(nombre);
                boolean existe = modelo.verificar(nombre);
                System.out.println(existe);
                if (existe == true) {
                     JOptionPane.showMessageDialog(new JFrame(),"No se pudo registrar el tipo de orden porque ya existe", "Incluir",JOptionPane.INFORMATION_MESSAGE);
                }else{
                boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nombre;
                        modelo = new ModeloTipoorden(nombre);
                        String Leyenda = "Tipo de orden "+nombre+" registrado";
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
       String nombre = vista.getTexttipoorden().getText();
       int idpatologia = vista.getIdTipoorden();
      // System.out.println(idpatologia);
       
        modelo = new ModeloTipoorden(nombre);
        boolean modificado=modelo.modificarModelo(nombre, idpatologia);
        if (modificado){
            this.nombre = nombre;
            String Leyenda = "Tipo de orden "+nombre+" modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de orden no modificado","Modificar",JOptionPane.ERROR_MESSAGE);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
    public void codigoBotonEliminar(){
        String nombre = vista.getTexttipoorden().getText();
 
        modelo = new ModeloTipoorden();
        boolean eliminado=modelo.eliminarModelo(nombre);
        if (eliminado){
            this.nombre = nombre;
            String Leyenda = "Tipo de orden "+nombre+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            //JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de orden no eliminado","Eliminar",JOptionPane.ERROR_MESSAGE);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
    public void codigoBotonConsultar(){
        modelo = new ModeloTipoorden();
            boolean encontrado = modelo.consultarModelo(vista.getTexttipoorden().getText());
            
            if (encontrado){
                vista.getTexttipoorden().setText(modelo.getNombre());
                int idpatologia = modelo.getId();
                String Idpatologia = Integer.toString(idpatologia);
                vista.setIdTipoorden(idpatologia);
                
              
                //Acción de los botones y cajas de texto 
                vista.getModificar().setEnabled(true);
                vista.getEliminar().setEnabled(true);
            
            }
            else
            {
                vista.getRegistrar().setEnabled(true);
                JOptionPane.showMessageDialog(new JFrame(),"Tipo de orden no encontrado","Consulta",JOptionPane.INFORMATION_MESSAGE);
            }
            habilitar();
    } // Fin del consultar... 
    public void codigoBotonLimpiar(){
        vista.getTexttipoorden().setText("");
        
        vista.getRegistrar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false); 
             
    }//fin de codigoLimpiar   
    public void parespacios(){
     String leyenda = "¡No puede registrar varios espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
    public void menorletras(){
     String leyenda = "Asegúrese de que los campo Nombre no sean menor a 4 letras!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
    public void espacioblanco(){
     String leyenda = "¡No puede registrar espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
    public void habilitar(){
        vista.getTexttipoorden().setEnabled(true);
    }//fin de habilitar
}
