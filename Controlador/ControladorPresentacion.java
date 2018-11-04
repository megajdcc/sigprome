/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloPresentacion;
import Vista.Principal;
import Vista.Presentacion;
import Vista.CatPresentacion;
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
public class ControladorPresentacion  implements ActionListener{
    
    Presentacion vista;
    Principal principal;
    CatPresentacion cat;
    ModeloPresentacion modelo; 
    private String nombre;
    
     public ControladorPresentacion(Presentacion vista)
    {
        this.vista = vista;
    }
     public ControladorPresentacion(CatPresentacion cat)
     {
         this.cat = cat;
     }
     
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==vista.getRegistrar()){
            if (vista.getTextpresentacion().getText().isEmpty()) {
                this.espacioblanco();
                //vista.getTextpatologia().requestFocus(); 
             }
            else if(vista.getTextpresentacion().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            if(vista.getTextpresentacion().getText().length() < 4){
                this.menorletras();
            }else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            
           if (vista.getTextpresentacion().getText().isEmpty()) {
                this.espacioblanco();
                //vista.getTextpatologia().requestFocus(); 
             }
            else if(vista.getTextpresentacion().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            if(vista.getTextpresentacion().getText().length() < 4){
                this.menorletras();  
            }else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar la Presentación?", "Presentación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {
                 codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatPresentacion(principal, true);
                cat.setVisible(true);
            }
    }
     public void codigoBotonregistrar(){
        
         /*/tomar datos del paciente/*/   
        String nombre = vista.getTextpresentacion().getText();
       
     
               modelo = new ModeloPresentacion(nombre);
               
                boolean existe = modelo.verificar(nombre);
                System.out.println(existe);
                if (existe) {
                    
                     JOptionPane.showMessageDialog(new JFrame(),"No se pudo registrar la Presentación porque ya existe","Incluir",JOptionPane.INFORMATION_MESSAGE);
                }else{
                modelo = new ModeloPresentacion(nombre);
                boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nombre;
                        String Leyenda = "Presentación "+nombre+" registrada";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                         JOptionPane.showMessageDialog(new JFrame(),"Presentación no registrada","Incluir",JOptionPane.ERROR_MESSAGE);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
      public void codigoBotonModificar(){
       String nombre = vista.getTextpresentacion().getText();
       int idpresentacion = vista.getIdPresentacion();
      // System.out.println(idpatologia);
       
        modelo = new ModeloPresentacion(nombre);
        boolean modificado=modelo.modificarModelo(nombre, idpresentacion);
        if (modificado){
            this.nombre = nombre;
            String Leyenda = "Presentación "+nombre+" modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Presentacíón no modificada","Modificar",JOptionPane.ERROR_MESSAGE);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
       public void codigoBotonEliminar(){
        String nombre = vista.getTextpresentacion().getText();
 
        modelo = new ModeloPresentacion();
        boolean eliminado=modelo.eliminarModelo(nombre);
        if (eliminado){
            this.nombre = nombre;
            String Leyenda = "Presentación "+nombre+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            //JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Presentación no eliminada","Eliminar",JOptionPane.ERROR_MESSAGE);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
     public void codigoBotonLimpiar(){
        vista.getTextpresentacion().setText("");
        
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
        vista.getTextpresentacion().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
}
