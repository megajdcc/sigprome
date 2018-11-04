/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloExamen;
import Vista.CatExamen;
import Vista.Examen;
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
public class ControladorExamen implements ActionListener{
    Examen vista;
    Principal principal;
    CatExamen cat;
    ModeloExamen modelo;
    private String nombre;
    
    
     public ControladorExamen(Examen vista)
    {
        this.vista = vista;
    }
     public ControladorExamen(CatExamen cat)
     {
         this.cat = cat;
     }
    
     
     
    @Override
      public void actionPerformed(ActionEvent evento) 
      {
         if(evento.getSource()==vista.getRegistrar()){
            if (vista.getTextexamen().getText().isEmpty()) {
               this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
            else if(vista.getTextexamen().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            if(vista.getTextexamen().getText().length() < 4){
                this.menorletras();
            }
            
            else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            
             if (vista.getTextexamen().getText().isEmpty()) {
               this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
            else if(vista.getTextexamen().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            if(vista.getTextexamen().getText().length() < 4){
                this.menorletras();
            }
            else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                 
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar el Examen?", "Examen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {
                codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatExamen(principal, true);
                cat.setVisible(true);
            }
      }
      
      public void codigoBotonregistrar(){
        
        String nomb = vista.getTextexamen().getText();

               modelo = new ModeloExamen(nomb);
                boolean existe = modelo.verificar(nomb);
                if (existe == true) {
                    String leyenda = "No se pudo registrar el Examen porque ya existe";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(Color.YELLOW);
                }else{
                    modelo = new ModeloExamen(nomb);
                    boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nomb;
                       // modelo = new ModeloPatologia(nombre, descripcion);
                        String Leyenda = "Examen "+nomb+" Registrado";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                    String leyenda = "No se pudo registrar el examen";
                    vista.getLeyenda().setText(leyenda);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
      
      public void codigoBotonModificar(){
       String nomb = vista.getTextexamen().getText();
       int idpotencia = vista.getIdexamen();
      // System.out.println(idpatologia);
       
        modelo = new ModeloExamen(nomb);
        boolean modificado=modelo.modificarModelo(nomb, idpotencia);
        if (modificado){
            this.nombre = nomb;
            String Leyenda = "Examen "+nomb+" modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            String Leyenda = "Examen "+nomb+" no modificada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
      
      public void codigoBotonEliminar(){
        String nomb = vista.getTextexamen().getText();
 
        modelo = new ModeloExamen();
        boolean eliminado=modelo.eliminarModelo(nomb);
        if (eliminado){
            this.nombre = nomb;
            String Leyenda = "Examen "+nomb+" Eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
           
        }else{
            String Leyenda = "La Examen "+nomb+"  no fue eliminada";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
      
//    public void codigoBotonConsultar(){
//        modelo = new ModeloPatologia();
//            boolean encontrado = modelo.consultarModelo(vista.getTextexamen().getText());
//            
//            if (encontrado){
//                vista.getTextexamen().setText(modelo.getNombre());
//               // vista.getIdPatologia().setIdPatologia(modelo.getId());
//                int idpotencia = modelo.getId();
//                String Idpatologia = Integer.toString(idpotencia);
//                vista.setIdexamen(Idexamen);
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
    public void codigoBotonLimpiar(){
        vista.getTextexamen().setText("");        
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
    public void habilitar(){
        vista.getTextexamen().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar


}
