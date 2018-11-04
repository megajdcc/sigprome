/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.Principal;
import Modelo.ModeloUsuario;
import Vista.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 *
 * @author Jnatn'h
 */
public class Controladorprincipal implements ActionListener,MouseListener,TreeSelectionListener,TreeExpansionListener,KeyListener{
    Principal Vista;
    ModeloUsuario modelo;
    Login login;
    private int id;
   private CatPaciente paciente;
    private CatEspecialidad especialidad;
    public 	catapatologia patologia;
    private CatTiposervicios tiposervicios;
    private CatTipoempleado tipoempleado;
    private CatTipopersona tipopersona;
    private cattiporden tipoorden;
    private CatVias vias;
    private CatTipoproducto tipoproducto;
    private CatPresentacion presentacion;
    private CatPotencia potencia;
    private Catprincipioactivo principioactivo;
    private CatExamen examen;
    private CatTipousuario tipousuario;
    private Servicio servicio;
    private CatUsuario usuario;
    private Triaje triaje;
    private Consulta consulta;
    private Bitacora bitacora;
    private CatEstudios estudio;
    private CatMedicamento medicamentos;
    private  Respaldo respaldo; 
    private Morbilidad morbilidad;
    private Opcadministrativas opcionadministrativa;
    private EstadoProceso estadosproceso ;
    private Fevida fedevida;
    //private objetos objec;
    public Controladorprincipal(Principal Vista){
        this.Vista = Vista;
        cargarobjetos();
    }
    
    public Principal getVista(){
      return Vista;
    }

    public void cargarobjetos(){
        ArrayList<String> obj = new ArrayList<String>();
    
    	obj.add("paciente");
    	obj.add("patologia");
    	obj.add("especialidad");
    	obj.add("tiposervicios");
    	obj.add("tipoempleado");
    	obj.add("tipopersona");
    	obj.add("tipoorden");
    	obj.add("vias");
    	obj.add("tipoproducto");
    	obj.add("presentacion");
    	obj.add("potencia");
    	obj.add("principioactivo");
    	obj.add("examen");
    	obj.add("tipousuario");
    	obj.add("servicio");
    	obj.add("usuario");
    	obj.add("triaje");
    	obj.add("consulta");
    	obj.add("bitacora");
    	obj.add("estudio");
    	obj.add("medicamentos");
    	obj.add("respaldo");
    	obj.add("morbilidad");
    	obj.add("opcionadministrativa");
    	obj.add("estadosproceso");
        obj.add("fedevida");
    	
    	
    	
    	
    	for(int i = 0; i < obj.size(); i++) {
    		Runnable loadobjec = new objetos(obj.get(i));

    		Thread t = new Thread(loadobjec);
      
        	t.start();
    	}
    }
//    private ArrayList getObject(){
//        ArrayList objetos =  new ArrayList();  
//        objetos.add(paciente);
//        return objetos;
//    }
    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == Vista.getSalir()) {
          String pregunta = "Deseas salir del sistema?";
          int salir  = JOptionPane.showConfirmDialog(Vista, pregunta, "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(salir == 0){
                System.exit(0);
            }else{
            
            }
        }else if(evento.getSource() == Vista.getCerrarSesion()){
            Vista.dispose();
            Login logi  = new Login();
            logi.setVisible(true);            
        }
}

 /*******************************************/
 /*          Getters y setters              */
 /*******************************************/ 
    
    /**
     * 
     * @return Devuelve un entero indicando el id del usuario que ingresado al sistema... 
     */
    public int getId(){
   
      return this.id;
    }
    
    public void setId(int id){
        this.id = id;     
    }

    public ModeloUsuario getModelo() {
        return modelo;
    }

    public void setModelo(ModeloUsuario modelo) {
        this.modelo = modelo;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setVista(Principal Vista) {
        this.Vista = Vista;
    }

    public void setPaciente(CatPaciente paciente) {
        this.paciente = paciente;
    }

    public void setEspecialidad(CatEspecialidad especialidad) {
        this.especialidad = especialidad;
    }

    public void setPatologia(catapatologia patologia) {
        this.patologia = patologia;
    }

    public void setTiposervicios(CatTiposervicios tiposervicios) {
        this.tiposervicios = tiposervicios;
    }

    public void setTipoempleado(CatTipoempleado tipoempleado) {
        this.tipoempleado = tipoempleado;
    }

    public void setTipopersona(CatTipopersona tipopersona) {
        this.tipopersona = tipopersona;
    }

    public void setTipoorden(cattiporden tipoorden) {
        this.tipoorden = tipoorden;
    }

    public void setVias(CatVias vias) {
        this.vias = vias;
    }

    public void setTipoproducto(CatTipoproducto tipoproducto) {
        this.tipoproducto = tipoproducto;
    }

    public void setPresentacion(CatPresentacion presentacion) {
        this.presentacion = presentacion;
    }

    public void setPotencia(CatPotencia potencia) {
        this.potencia = potencia;
    }

    public void setPrincipioactivo(Catprincipioactivo principioactivo) {
        this.principioactivo = principioactivo;
    }

    public void setExamen(CatExamen examen) {
        this.examen = examen;
    }

    public void setTipousuario(CatTipousuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public void setUsuario(CatUsuario usuario) {
        this.usuario = usuario;
    }

    public void setTriaje(Triaje triaje) {
        this.triaje = triaje;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public void setBitacora(Bitacora bitacora) {
        this.bitacora = bitacora;
    }

    public void setEstudio(CatEstudios estudio) {
        this.estudio = estudio;
    }

    public void setMedicamentos(CatMedicamento medicamentos) {
        this.medicamentos = medicamentos;
    }

    public void setRespaldo(Respaldo respaldo) {
        this.respaldo = respaldo;
    }

    public void setMorbilidad(Morbilidad morbilidad) {
        this.morbilidad = morbilidad;
    }

    public void setOpcionadministrativa(Opcadministrativas opcionadministrativa) {
        this.opcionadministrativa = opcionadministrativa;
    }

    public void setEstadosproceso(EstadoProceso estadosproceso) {
        this.estadosproceso = estadosproceso;
    }

//    public void setObjec(objetos objec) {
//        this.objec = objec;
//    }

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        String select = Vista.getMenu().getLastSelectedPathComponent().toString();
        Runnable hilonew = new hilo(select);
        Thread t = new Thread(hilonew);
        t.setPriority(3);
        t.start();
    }

    @Override
    public void treeExpanded(TreeExpansionEvent event) {
      
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
       
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
  
 
  
  class hilo implements Runnable{
      
      private String opcion;
      public hilo(String opcion){
           this.opcion = opcion;
      }
        @Override
        public void run() {
          if(opcion.equals("Paciente")){
            paciente.setVisible(true);
        }else if(opcion.equals("Especialidad")){
            especialidad.setVisible(true);
        }else if(opcion.equals("Patología")){
             patologia.setVisible(true);
        }else if(opcion.equals("Tipo Servicios")){
        
            tiposervicios.setVisible(true);
        }else if(opcion.equals("Tipo Empleado")){
            
            tipoempleado.setVisible(true);
        }else if(opcion.equals("Tipo Paciente")){
          
            tipopersona.setVisible(true);
        }else if(opcion.equals("Tipo Orden")){
         
            tipoorden.setVisible(true);
        }else if(opcion.equals("Vías")){
           
            vias.setVisible(true);
        }else if(opcion.equals("Tipo Producto")){
            
            tipoproducto.setVisible(true);
        }else if(opcion.equals("Presentación")){ 
           
            presentacion.setVisible(true);
        }else if(opcion.equals("Potencia")){
         
           potencia.setVisible(true);
        }else if(opcion.equals("Principio activo")){
          
            principioactivo.setVisible(true);
        }else if(opcion.equals("Examen")){   
           
            examen.setVisible(true);
        }else if(opcion.equals("Tipo de usuario")){

            tipousuario.setVisible(true);
        }else if(opcion.equals("Servicio")){ 
  
            servicio.setVisible(true);
        }else if (opcion.equals("Administrar Usuario")){
    
            usuario.setVisible(true);
        }else if(opcion.equalsIgnoreCase("triage")){
 
           triaje.setVisible(true);
        }else if(opcion.equalsIgnoreCase("Consulta")){

           consulta.setVisible(true);
        }else if(opcion.equalsIgnoreCase("Bitacora")){
         
            bitacora.setVisible(true);
        }else if(opcion.equalsIgnoreCase("Estudios Especiales")){    
   
        	estudio.setVisible(true);
        }else if(opcion.equalsIgnoreCase("medicamentos")){

            medicamentos.setVisible(true);
        }else if(opcion.equalsIgnoreCase("respaldo BD")){

            respaldo.setVisible(true);
        }else if(opcion.equalsIgnoreCase("Morbilidad")){
            morbilidad.setVisible(true);
        }else if(opcion.equalsIgnoreCase("Opc de Servicios Administrativos")){
        	opcionadministrativa.setVisible(true);
        }
        else if(opcion.equalsIgnoreCase("Estados de proceso")){
        
            estadosproceso.setVisible(true);
        }else if(opcion.equalsIgnoreCase("Fe de vida")){
            fedevida.setVisible(true);
        }
        }
      
  }
  
  class objetos implements Runnable{
	  
	  private Object objeto;
	   
	  public objetos(String obc1) {
		  this.objeto = obc1;
	  }
	  
		@Override
		public void run() {
			
			try {
				
				if(objeto.equals("paciente")) {
                                    paciente =new CatPaciente(Vista,true);
                                    paciente.setController(new ControladorPaciente(paciente));
				}else if(objeto.equals("especialidad")){
					especialidad = new CatEspecialidad(Vista,true);
				}else if(objeto.equals("patologia")) {
					patologia = new catapatologia(Vista,true);
				}else if(objeto.equals("tiposervicios")) {
					tiposervicios = new CatTiposervicios(Vista,true);
				}else if(objeto.equals("tipoempleado")) {
					tipoempleado = new CatTipoempleado(Vista,true);
				}else if(objeto.equals("tipopersona")) {
					tipopersona = new CatTipopersona(Vista,true);
				}else if(objeto.equals("tipoorden")) {
					tipoorden = new cattiporden(Vista,true);
				}else if(objeto.equals("vias")) {
					vias = new CatVias(Vista,true);
				}else if(objeto.equals("tipoproducto")) {
					tipoproducto = new CatTipoproducto(Vista,true);
				}else if(objeto.equals("presentacion")) {
					presentacion = new CatPresentacion(Vista,true);
				}else if(objeto.equals("potencia")) {
					potencia = new CatPotencia(Vista,true);
				}else if(objeto.equals("principioactivo")) {
					principioactivo = new Catprincipioactivo(Vista,true);
				}else if(objeto.equals("examen")) {
					examen = new CatExamen(Vista,true);
				}else if(objeto.equals("tipousuario")) {
					tipousuario = new CatTipousuario(Vista,true);
				}else if(objeto.equals("servicio")) {
					servicio = new Servicio(Vista,true);
				}else if(objeto.equals("usuario")) {
					usuario = new CatUsuario(Vista,true);
				}else if(objeto.equals("triaje")) {
					triaje = new Triaje(Vista,true);
				}else if(objeto.equals("consulta")) {
					consulta = new Consulta(Vista,true);
				}else if(objeto.equals("bitacora")) {
					bitacora = new Bitacora(Vista,true);
				}else if(objeto.equals("estudio")) {
					estudio = new CatEstudios(Vista,true);
				}else if(objeto.equals("medicamentos")) {
					medicamentos = new CatMedicamento(Vista,true);
				}else if(objeto.equals("respaldo")) {
					respaldo = new Respaldo(Vista,true);
				}else if(objeto.equals("morbilidad")) {
					morbilidad = new Morbilidad(Vista,true);
				}else if(objeto.equals("opcionadministrativa")) {
					opcionadministrativa = new Opcadministrativas(Vista,true);
				}else if(objeto.equals("estadosproceso")) {
					estadosproceso = new EstadoProceso(Vista,true);
				}else if(objeto.equals("fedevida")) {
					fedevida = new Fevida(Vista,true);
					fedevida.setController(new ControllerFevida(fedevida));
				}
                                

			} catch (Exception e) {
				e.printStackTrace();
			}
			
	
			
			
		}
		
	}
}
