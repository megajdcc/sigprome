/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloTipoProducto;
import Vista.Principal;
import Vista.TipoProducto;
import Vista.CatTipoproducto;
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
public class ControladorTipoProducto implements ActionListener{
    TipoProducto vista;
    Principal principal;
    CatTipoproducto cat;
    ModeloTipoProducto modelo;
    private String nombre;
    
     public ControladorTipoProducto(TipoProducto vista)
    {
        this.vista = vista;
    }
     public ControladorTipoProducto(CatTipoproducto cat)
     {
         this.cat = cat;
     }
     
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(evento.getSource()==vista.getRegistrar()){
            if (vista.getTexttipoproducto().getText().isEmpty()) {
               this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
            
            else if (vista.getTexttipoproducto().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            if(vista.getTexttipoproducto().getText().length() < 4){
                
            this.menorletras();
              
            }else{codigoBotonregistrar();}
            
        }else if(evento.getSource()==vista.getModificar()){
            
            if (vista.getTexttipoproducto().getText().isEmpty()) {
               this.espacioblanco();
//                vista.getTextpatologia().requestFocus(); 
             }
            
            else if (vista.getTexttipoproducto().getText().contains("  "))
            {
                this.parespacios();
            }
             else
            if(vista.getTexttipoproducto().getText().length() < 4){
                
            this.menorletras();
              
            }else{
                
                codigoBotonModificar();
             }
                             
            }else if(evento.getSource()==vista.getEliminar()){
                
            String si = "SI";
            String no = "NO";
            Object[] options ={si,no}; 
           int respuesta = JOptionPane.showOptionDialog(principal, "¿Desea Eliminar el Tipo Producto?", " Tipo Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,null, options); 
            if (respuesta == 0) {
                 codigoBotonEliminar();
            }
                System.out.println("Botón Eliminar...");
          
            }else if(evento.getSource() == vista.getSalir())
            {
                vista.setVisible(false);
                cat = new CatTipoproducto(principal, true);
                cat.setVisible(true);
            }
    }
     public void codigoBotonregistrar(){
        
         /*/tomar datos del paciente/*/   
        String nombre = vista.getTexttipoproducto().getText();
       
     
               modelo = new ModeloTipoProducto(nombre);
               
                boolean existe = modelo.verificar(nombre);
                System.out.println(existe);
                if (existe) {
                    
                     JOptionPane.showMessageDialog(new JFrame(),"No se pudo registrar el Tipo de Producto porque ya existe","Incluir",JOptionPane.INFORMATION_MESSAGE);
                }else{
                modelo = new ModeloTipoProducto(nombre);
                boolean incluido=modelo.incluirModelo();
                    if (incluido){
                        this.nombre = nombre;
          //           System.out.println(nombre);
                        String Leyenda = "Tipo de Producto " +nombre+ " registrado";
                        vista.getLeyenda().setText(Leyenda);
                        vista.getLeyenda().setForeground(Color.green);
                        //JOptionPane.showMessageDialog(new JFrame(),"Registro incluido","Incluir",JOptionPane.INFORMATION_MESSAGE);
                    }else{
                         JOptionPane.showMessageDialog(new JFrame(),"Tipo de Producto no incluido","Incluir",JOptionPane.ERROR_MESSAGE);
                }
            
           codigoBotonLimpiar();
                }
        }//fin de Registrar
      public void codigoBotonModificar(){
       String nombre = vista.getTexttipoproducto().getText();
       int idtipoproducto = vista.getIdTipoproducto();
      // System.out.println(idpatologia);
       
        modelo = new ModeloTipoProducto(nombre);
        boolean modificado=modelo.modificarModelo(nombre, idtipoproducto);
        if (modificado){
            this.nombre = nombre;
            String Leyenda = "Tipo de Producto " +nombre+ " modificado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.green);
//            JOptionPane.showMessageDialog(new JFrame(),"Registro modificado","Modificar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de Producto no modificado","Modificar",JOptionPane.ERROR_MESSAGE);
        }
        codigoBotonLimpiar();
       
        
        }//fin de codigoModificar
       public void codigoBotonEliminar(){
        String nombre = vista.getTexttipoproducto().getText();
 
        modelo = new ModeloTipoProducto();
        boolean eliminado=modelo.eliminarModelo(nombre);
        if (eliminado){
            this.nombre = nombre;
            String Leyenda = "Tipo de Producto "+nombre+ " Eliminado";
            vista.getLeyenda().setText(Leyenda);
            vista.getLeyenda().setForeground(Color.red);
            //JOptionPane.showMessageDialog(new JFrame(),"Registro eliminado","Eliminar",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(new JFrame(),"Tipo de Producto no eliminado","Eliminar",JOptionPane.ERROR_MESSAGE);
        }  
        codigoBotonLimpiar();
    }//fin de codigoEliminar
        public void codigoBotonLimpiar(){
        vista.getTexttipoproducto().setText("");
        
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
        vista.getTexttipoproducto().setEnabled(true);
        //habilitar-deshabilitar botones
        
    }//fin de habilitar
     
    
}
