
package Controlador;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.*;
import org.apache.commons.codec.digest.DigestUtils;

import Modelo.ModeloUsuario;
import Vista.Login;
import Vista.Principal;

/**
 * La clase Controladorinicio es la sirvientes de acciones de los eventos ocurridos en el objeto Login.
 * La mas importante es la de Verificar el inicio de sesion, la de validar los campos de usuario y contraseña y la de inicializar una conexion
 * a la base de dato mientras el usuario este en el sistema ... 
 * Esta clase implementa interfaz como ActionListener y KeyListener, utilizadas para controlar los eventos de algunos componentes de la 
 * Clase Login... 
 * 
 * @author Jnatn'h
 */
public class Controladorinicio implements ActionListener, KeyListener{
    
 /*******************************************/
 /*             Campos de clase             */
 /*******************************************/
 public int Idusuario;
 public Login vista;
 private final Principal principal;
 private final ModeloUsuario mu;
 
 /*******************************************/
 /*          Constructor de objecto         */
 /*******************************************/
 
 /**
  * El contructor recibe por parametro un objeto del tipo de Login... 
  * Es tambien el encargado de inicializar los objetos ModeloUsuario y principal para tenerlos preparados para su posterior uso ... 
  * @param v Objeto de tipo Login, vista de arranque del sistema Sigprome ... 
  */
 public Controladorinicio (Login v){
    this.vista = v;   
    mu =  new ModeloUsuario();
    principal = new Principal();
 }
 
 /*******************************************/
 /*          Getters y setters              */
 /*******************************************/
 
 /**
  * 
  * @return  Devuelve un Objeto del tipo de ModeloUsuario ... 
  */
 public ModeloUsuario getModelU(){
   return mu;
 }
 
 /**
  * 
  * @return Devuelve un entero, el valor de identificacion del usuario.   
  */
 public int getIdUsuario(){
     return Idusuario;
 }
 
 /**
  * 
  * @param Idusuario Un parametro del tipo entero, identificador de usuario ... 
  */
 public void setIdusuario(int Idusuario){
     this.Idusuario = Idusuario;
 }
 
 
 /*******************************************/
 /*          Metodos de objeto              */
 /*******************************************/
 
 /**
  * 
  * @param e Objeto del tipo ActionEvent, desencadenante de acciones del usuario... 
  */
    @Override
    public void actionPerformed(ActionEvent e){ 
        Object origen = e.getSource();
        if(origen.equals(vista.getLogininiciar())){
            vista.setCursor(new Cursor(Cursor.WAIT_CURSOR));
             boolean validado = this.validar();
             if(validado){
                 this.verificarinicio();
             }
        }
    }
    
    /**
     * Metodo encargado de validar los JtextField de usuario y contrasena ... 
     * @return 
     */
    private boolean validar(){
        boolean validado = false;
     
        if(vista.getJuser().getText().length() == 0){
              vista.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
              vista.getLeyenda().setText(null);
                JOptionPane.showMessageDialog(new JFrame(), "Debe rellenar el campo de usuario", "Advertencia",JOptionPane.WARNING_MESSAGE);
            }else if(vista.getJpass().getPassword().length == 0){
                vista.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                 vista.getLeyenda().setText(null);
                JOptionPane.showMessageDialog(new JFrame(), "Debe rellenar el campo de contraseña", "Advertencia",JOptionPane.WARNING_MESSAGE);
            }else{
                validado = true;
            }
        return validado;
    }
    
    /**
     * Metodo encargado de verificar el inicio de sesion del usuario ... 
     */
    private void verificarinicio(){
            char contra[] = vista.getJpass().getPassword();
            String contrasena = "";
            for(int i = 0; i<contra.length; i++){
              contrasena += ""+contra[i];
            }
            contrasena = DigestUtils.sha1Hex(contrasena);
            String usuario = vista.getJuser().getText().toUpperCase();
            mu.setNombreUsuario(usuario);
            mu.setContrasena(contrasena);
          
            boolean validar = mu.validarusuario();  
            this.vista.getLeyenda().setText("ha validado perfectamente"+validar);
            if(validar){ 
                int idusuario = 0;
                try {
                    idusuario = mu.capturarid();
                    Principal.setIdUsuario(idusuario);
                    boolean users  = mu.capturarusuario(idusuario);
                    if (users){
                        principal.getNomUsuario().setText(mu.getNombreUsuario());
                        principal.getTipousuario().setText(mu.getNombtipousuario());     
                    }else{ 
                        principal.getNomUsuario().setText("No definido");
                        principal.getTipousuario().setText("No definido");
                    }
                    mu.getConexion().cerrar();
                    principal.setVisible(true);
                    vista.dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(Controladorinicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
            
               vista.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
               vista.getLeyenda().setText(null);
               JOptionPane.showMessageDialog(new JFrame(),"Usuario ó Contraseña invalidos.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
      Component origen = e.getComponent();
      if(origen.equals(vista.getJpass())){
           if (e.getKeyCode()==KeyEvent.VK_ENTER){
           boolean validado = this.validar();
           if(validado){
               this.verificarinicio();
           }
        }else if(e.getKeyCode()==KeyEvent.VK_TAB){
            vista.getLogininiciar().requestFocus();
        }
      }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
}
