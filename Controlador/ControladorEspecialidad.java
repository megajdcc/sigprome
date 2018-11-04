/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloEspecialidad;
import Vista.CatEspecialidad;
import Vista.Especialidad;
import Vista.Principal;
import java.awt.Color;
import static java.awt.Color.orange;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author jesus
 */
public class ControladorEspecialidad implements ActionListener{
      Especialidad vista;
    Principal principal;
    CatEspecialidad cat;
    ModeloEspecialidad modelo;
    private String nombre;
    
    
     public ControladorEspecialidad(Especialidad vista)
    {
        this.vista = vista;
    }
     public ControladorEspecialidad(CatEspecialidad cat)
     {
         this.cat = cat;
     }
    
     
     
    @Override
      public void actionPerformed(ActionEvent evento) 
      {
         if(evento.getSource()==vista.getRegistrar()){
             if (vista.getTextespecialidad().getText().isEmpty()) {
                this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
             else if(vista.getTextespecialidad().getText().contains("  "))
             {
                 this.parespacios();
             }
             else
            
            if(vista.getTextespecialidad().getText().length() < 4){
                this.menorletras();
            }
            
            else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            
             if (vista.getTextespecialidad().getText().isEmpty()) {
                this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
             else if(vista.getTextespecialidad().getText().contains("  "))
             {
                 this.parespacios();
             }
             else
            
            if(vista.getTextespecialidad().getText().length() < 4){
                this.menorletras();
                
            }else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                String si = "SI";
                String no = "NO";
                Object[] options ={si,no}; 
                int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar la Especialidad?", "Especialidad", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
                if (respuesta == 0) {
                 codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatEspecialidad(principal, true);
                cat.setVisible(true);
            }
      }
      
      public void codigoBotonregistrar(){
        
        String nomb = vista.getTextespecialidad().getText();

               modelo = new ModeloEspecialidad(nomb);
                boolean existe = modelo.verificar(nomb);
                if (existe == true) {
                    String leyenda = "No se pudo registrar la Especialidad porque ya existe";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(Color.YELLOW);
                }else{
                    modelo = new ModeloEspecialidad(nomb);
                    boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nomb;
                       // modelo = new ModeloPatologia(nombre, descripcion);
                        String Leyenda = "Especialidad "+nomb+" Registrada";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                    String leyenda = "No se pudo registrar la Especialidad";
                    vista.getLeyenda().setText(leyenda);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
      
      public void codigoBotonModificar(){
       String nomb = vista.getTextespecialidad().getText();
       int idespecialidad = vista.getIdespecialidad();
      // System.out.println(idpatologia);
       
        modelo = new ModeloEspecialidad(nomb);
        boolean modificado=modelo.modificarModelo(nomb, idespecialidad);
        if (modificado){
            this.nombre = nomb;
            String Leyenda = "Especialidad "+nomb+" modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            String Leyenda = "Especialidad "+nomb+" no modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
      
      public void codigoBotonEliminar(){
        String nomb = vista.getTextespecialidad().getText();
 
        modelo = new ModeloEspecialidad();
        boolean eliminado=modelo.eliminarModelo(nomb);
        if (eliminado){
            this.nombre = nomb;
            String Leyenda = "Especialidad "+nomb+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
           
        }else{
            String Leyenda = "La Especialidad "+nomb+"  no fue eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
      
//    public void codigoBotonConsultar(){
//        modelo = new ModeloPatologia();
//            boolean encontrado = modelo.consultarModelo(vista.getTextespecialidad().getText());
//            
//            if (encontrado){
//                vista.getTextespecialidad().setText(modelo.getNombre());
//               // vista.getIdPatologia().setIdPatologia(modelo.getId());
//                int idespecialidad = modelo.getId();
//                String Idpatologia = Integer.toString(idespecialidad);
//                vista.setIdespecialidad(Idespecialidad);
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
//                JOptionPane.showMessageDialog(new JFrame(),"Especialidad no encontrada","Consulta",JOptionPane.INFORMATION_MESSAGE);
//            }
//            habilitar();
//    } // Fin del consultar... 
    public void codigoBotonLimpiar(){
        vista.getTextespecialidad().setText("");        
        vista.getRegistrar().setEnabled(true);
        vista.getModificar().setEnabled(false);
        vista.getEliminar().setEnabled(false); 
        
       
//        vista.setVisible(false);
//        cat = new catapatologia(principal, true);
//        cat.show();
       

        
        
    }//fin de codigoLimpiar
 public void parespacios()
 {
     String leyenda = "¡No puede registras varios espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
 
 public void menorletras()
 {
     String leyenda = "¡Asegúrese de que los campo Nombre no sean menor a 4 letras!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }
 public void espacioblanco()
 {
     String leyenda = "¡No puede registras espacios en blancos en Nombre!";
     vista.getLeyenda().setText(leyenda);
     vista.getLeyenda().setForeground(orange);
 }  
    public void habilitar(){
        vista.getTextespecialidad().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
}
