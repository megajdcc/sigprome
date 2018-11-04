/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloTipousuario;
import Vista.CatTipousuario;
import Vista.Principal;
import Vista.Tipousuario;
import java.awt.Color;
import static java.awt.Color.orange;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Jnatn'h
 */
public class ControladorTipousuario implements ActionListener{
    Tipousuario vista;
    Principal principal;
    CatTipousuario cat;
    ModeloTipousuario modelo;
    private String nombre;
    
    
     public ControladorTipousuario(Tipousuario vista)
    {
        this.vista = vista;
    }
     public ControladorTipousuario(CatTipousuario cat)
     {
         this.cat = cat;
     }
    
     
     
    @Override
      public void actionPerformed(ActionEvent evento) 
      {
         if(evento.getSource()==vista.getRegistrar()){
             if(vista.getTexttipousuario().getText().isEmpty())
             {
                 this.espacioblanco();
             }
             else if (vista.getTexttipousuario().getText().contains("  "))
             {
                 this.parespacios();
             }
            if(vista.getTexttipousuario().getText().length() < 4){
                this.menorletras();
            }
            
            else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            
           if(vista.getTexttipousuario().getText().isEmpty())
             {
                 this.espacioblanco();
             }
             else if (vista.getTexttipousuario().getText().contains("  "))
             {
                 this.parespacios();
             }
            if(vista.getTexttipousuario().getText().length() < 4){
                this.menorletras();
                
            }else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                String si = "SI";
                String no = "NO";
                Object[] options ={si,no}; 
                int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar la Tipo de Usuario?", "Tipo de Usuario", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null,null, options); 
                if (respuesta == 0) {
                codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatTipousuario(principal, true);
                cat.setVisible(true);
            }
      }
      
      public void codigoBotonregistrar(){
        
        String nomb = vista.getTexttipousuario().getText();

               modelo = new ModeloTipousuario(nomb);
                boolean existe = modelo.verificar(nomb);
                if (existe == true) {
                    String leyenda = "No se pudo registrar la potencia porque ya existe";
                    vista.getLeyenda().setText(leyenda);
                    vista.getLeyenda().setForeground(Color.YELLOW);
                }else{
                    modelo = new ModeloTipousuario(nomb);
                    boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nomb;
                       // modelo = new ModeloPatologia(nombre, descripcion);
                        String Leyenda = "Tipo de usuario, "+nomb+" Registrado";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                    String leyenda = "No se pudo registrar el tipo de usuario";
                    vista.getLeyenda().setText(leyenda);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
      
      public void codigoBotonModificar(){
       String nomb = vista.getTexttipousuario().getText();
       int idpotencia = vista.getIdtipousuario();
      // System.out.println(idpatologia);
       
        modelo = new ModeloTipousuario(nomb);
        boolean modificado=modelo.modificarModelo(nomb, idpotencia);
        if (modificado){
            this.nombre = nomb;
            String Leyenda = "Tipo de usuario, "+nomb+" modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            String Leyenda = "Tipo de usuario,"+nomb+" no modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
      
      public void codigoBotonEliminar(){
        String nomb = vista.getTexttipousuario().getText();
 
        modelo = new ModeloTipousuario();
        boolean eliminado=modelo.eliminarModelo(nomb);
        if (eliminado){
            this.nombre = nomb;
            String Leyenda = "Tipo de usuario, "+nomb+" Eliminado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
           
        }else{
            String Leyenda = "El Tipo de usuario, "+nomb+"  no fue eliminado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
      
//    public void codigoBotonConsultar(){
//        modelo = new ModeloPatologia();
//            boolean encontrado = modelo.consultarModelo(vista.getTexttipousuario().getText());
//            
//            if (encontrado){
//                vista.getTexttipousuario().setText(modelo.getNombre());
//               // vista.getIdPatologia().setIdPatologia(modelo.getId());
//                int idpotencia = modelo.getId();
//                String Idpatologia = Integer.toString(idpotencia);
//                vista.setIdtipousuario(Idtipousuario);
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
        vista.getTexttipousuario().setText("");        
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
        vista.getTexttipousuario().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar

}
