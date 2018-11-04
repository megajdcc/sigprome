/*
 * The MIT License
 *
 * Copyright 2017 Jnatn'h.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Controlador;

import Modelo.Reports;
import Vista.Morbilidad;
import Vista.Principal;
import Vista.rangocalendar;
import datechooser.events.SelectionChangedEvent;
import datechooser.events.SelectionChangedListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;


/**
 *
 * @author Jnatn'h
 */
public class ControladorMorbilidad implements ActionListener,SelectionChangedListener {
    
    private Morbilidad vista;
    private Reports reporte;
    private int elecciones = 0 ;
    private String fechaini = null;
    private String fechafin = null;
    private String tiposervicio = null;
    private String tipopersona = null;
    private rangocalendar rango;
    private Principal principal;
    public ControladorMorbilidad(Morbilidad vist){
        this.vista = vist;
        reporte = new Reports();
        rango = new rangocalendar(principal, true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object Evento = e.getSource();/// un objeto que recibe el objet que genera el evento
        // Las acciones generadas desde la vista de paciente
        if(Evento.equals(vista.getSeleccion())){
            String captura;
            captura = (String) vista.getSeleccion().getSelectedItem();
            if (null != captura) switch (captura) {
                case "Pacientes Vistos":
                   this.elecciones = 1; 
                   this.habilitar("pacientev");
                   vista.getMovimiento().setSelectedIndex(0);
                   break;
                case "10 Principales Patologías":
                   this.habilitar("patologia");
                   vista.getMovimiento().setSelectedIndex(1);
                   break;
                case "SELECCIONE":
                    break;
            }
        }else if(Evento.equals(vista.getSalir())){
            vista.dispose();
        }else if(Evento.equals(vista.getCalendario())){
            ButtonModel tp1,tp2,tp3,tp4, ts1,ts2,ts3;
             tp1= vista.getEmpleado().getModel();
             tp2 = vista.getComunidad().getModel();
             tp3 = vista.getEstudiante().getModel();
             tp4 = vista.getTodas().getModel();
             ts1 = vista.getEmergencia().getModel();
             ts2 = vista.getCita().getModel();
             ts3 = vista.getTodos().getModel();
             int opt = 0;
            if(vista.getTipopersona().isSelected(tp1) 
                    || vista.getTipopersona().isSelected(tp2) 
                    || vista.getTipopersona().isSelected(tp3) 
                    || vista.getTipopersona().isSelected(tp4)){ 
                opt = 1;
            }
            if(vista.getTipodeservicio().isSelected(ts1)
                    || vista.getTipodeservicio().isSelected(ts2)
                    || vista.getTipodeservicio().isSelected(ts3)){
                opt += opt;
            }
            
           switch (opt) {
               case 2:
                   rango.setController(this);
                   rango.setVisible(true);
                   break;
               case 1:
                   {
                       String leyenda = "Debe seleccionar las opciones pertinentes a tipos de servicios";
                       vista.getLeyenda().setText(leyenda);
                       break;
                   }
               default:
                   {
                       String leyenda = "Debe Seleccionar las opciones anteriores";
                       vista.getLeyenda().setText(leyenda);
                       break;
                   }
           }
        }else if(Evento.equals(rango.getSalircalendario())){
            rango.dispose();
        }else if(Evento.equals(rango.getSiguiente())){
            
            this.fechaini = this.Capturarfechainicial();
            if(fechaini !=null){
                rango.getAsignacionfecha().setSelectedIndex(1);
                rango.getFechaFinal().setEnabled(true);
                rango.getSiguiente().setEnabled(false);
                rango.getAsignar().setEnabled(true);
            }else{
             String leyenda = "Debe Seleccionar una fecha inicial";
             rango.getLeyendacalendario().setText(leyenda);
            }
        }else if(Evento.equals(rango.getAsignar())){
            this.fechafin = this.Capturarfechafinal();
            if(fechafin != null){
                rango.dispose();
                vista.getEmitir().setEnabled(true);
            }else{
                 String leyenda = "Debe Seleccionar una fecha final";
                rango.getLeyendacalendario().setText(leyenda);
            }
        }else if(Evento.equals(vista.getEmitir())){
          if(vista.getSeleccion().getSelectedIndex() == 1){
            tiposervicio = this.capturartiposervicio().toUpperCase();
            tipopersona = this.capturartipopersona().toUpperCase();
            if (tiposervicio != null && tipopersona != null){
                 Icon icono = new ImageIcon(getClass().getResource("/Vista/imagen/reportes.png"));
                  String[] opciones ={"Imprimir","Mostrar"};
                  String pregunta = "Elija entre imprimir o mostrar el reporte de morbílidad?";
                  int opcion = JOptionPane.showOptionDialog(principal, pregunta, "Recipe", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,icono,opciones,opciones[0]);
                    Calendar fecha1 = rango.getFechainicial().getSelectedDate();
                    Date fechainii = fecha1.getTime();
                    Calendar fecha2 = rango.getFechaFinal().getSelectedDate();
                    Date fechafinn = fecha2.getTime();
                  if(opcion == 0){
                     reporte.morbilidadallrang(fechainii,fechafinn,tipopersona,tiposervicio,true);
                     limpiar();
                  }else{
                     reporte.morbilidadallrang(fechainii,fechafinn,tipopersona,tiposervicio,false);
                     this.limpiar();
                  }
            }
          }else{
            Calendar fecha1 = null,fecha2 = null;
            try{
               fecha1 = vista.getFechamin().getSelectedDate();
               fecha2 = vista.getFechafin().getSelectedDate();
            }catch(Exception et){
              String leyenda = "Hey! Tienes un campo de rango sin seleccionar verifique";
              vista.getLeyenda().setText(leyenda);
            }
           
            Date fechainicial =fecha1.getTime();
            Date fechafinal =fecha2.getTime();

              Icon icono = new ImageIcon(getClass().getResource("/Vista/imagen/reportes.png"));
                  String[] opciones ={"Imprimir","Mostrar"};
                  String pregunta = "Elija entre imprimir o mostrar el reporte de morbílidad?";
                  int opcion = JOptionPane.showOptionDialog(principal, pregunta, "Recipe", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,icono,opciones,opciones[0]);
                  if(opcion == 0){
                     reporte.morbilidadPatologia(fechainicial,fechafinal,true);
                     limpiar();
                  }else{
                     reporte.morbilidadPatologia(fechainicial,fechafinal,false);
                     this.limpiar();
                  }
        }
    }
 }
     private String capturartiposervicio(){
         String tp = null;
         
          for (Enumeration<AbstractButton > buttons = vista.getTipodeservicio().getElements(); buttons.hasMoreElements();)
            {
                   AbstractButton button = buttons.nextElement();
                  if(button.isSelected())
                    {
                       tp = button.getText();

                   }
             } 
          return tp;
     }
     private String capturartipopersona(){
         String tpers = null;
         
          for (Enumeration<AbstractButton > buttons = vista.getTipopersona().getElements(); buttons.hasMoreElements();)
            {
                   AbstractButton button = buttons.nextElement();
                  if(button.isSelected())
                    {
                       tpers = button.getText();

                   }
             } 
          return tpers;
     }
    private void habilitar(String tpe){
        if(tpe.equals("pacientev")){
          vista.getMovimiento().setEnabled(true);
          vista.getPacvistos().setEnabled(true);
          vista.getPri_patologia().setEnabled(true);
          vista.getComunidad().setEnabled(true);
          vista.getEmpleado().setEnabled(true);
          vista.getEstudiante().setEnabled(true);
          vista.getCita().setEnabled(true);
          vista.getEmergencia().setEnabled(true);
          vista.getEmpleado().setEnabled(true);
          vista.getComunidad().setEnabled(true);
          vista.getTodas().setEnabled(true);
          vista.getTodos().setEnabled(true);
          vista.getCalendario().setEnabled(true);
          
          vista.getFechamin().setEnabled(false);
          vista.getFechafin().setEnabled(false);
        }else{
          vista.getTipopersona().clearSelection();
          vista.getTipodeservicio().clearSelection();
          vista.getFechamin().setEnabled(true);
          vista.getFechafin().setEnabled(true);
   
          vista.getComunidad().setEnabled(false);
          vista.getEmpleado().setEnabled(false);
          vista.getEstudiante().setEnabled(false);
          vista.getCita().setEnabled(false);
          vista.getEmergencia().setEnabled(false);
          vista.getEmpleado().setEnabled(false);
          vista.getComunidad().setEnabled(false);
          vista.getTodas().setEnabled(false);
          vista.getTodos().setEnabled(false);
          vista.getCalendario().setEnabled(false);
          vista.getMovimiento().setEnabled(true);
          vista.getPacvistos().setEnabled(false);
          vista.getPri_patologia().setEnabled(false);
          vista.getComunidad().setEnabled(false);
          vista.getEmpleado().setEnabled(false);
          vista.getEstudiante().setEnabled(false);
          vista.getTodas().setEnabled(false);
        }
    }

    public void limpiar(){
        vista.getTipodeservicio().clearSelection();
        vista.getTipopersona().clearSelection();
        vista.getSeleccion().setSelectedItem("Seleccione");
        vista.getComunidad().setEnabled(false);
        vista.getEmpleado().setEnabled(false);
        vista.getEstudiante().setEnabled(false);
        vista.getCita().setEnabled(false);
        vista.getEmergencia().setEnabled(false);
        vista.getEmpleado().setEnabled(false);
        vista.getComunidad().setEnabled(false);
        vista.getTodas().setEnabled(false);
        vista.getTodos().setEnabled(false);
        vista.getCalendario().setEnabled(false);
        vista.getMovimiento().setEnabled(false);
        vista.getPacvistos().setEnabled(false);
        vista.getPri_patologia().setEnabled(false);
        vista.getComunidad().setEnabled(false);
        vista.getEmpleado().setEnabled(false);
        vista.getEstudiante().setEnabled(false);
        vista.getTodas().setEnabled(false);
        vista.getEmitir().setEnabled(false);
        vista.getFechafin().setEnabled(false);
        vista.getFechamin().setEnabled(false);
        vista.getFechafin().setSelectedDate(null);
        vista.getFechamin().setSelectedDate(null);
        vista.getTipopersona().clearSelection();
        vista.getTipodeservicio().clearSelection();
    }
    public String Capturarfechainicial(){
         Calendar fechini =  rango.getFechainicial().getSelectedDate();
         Date fecha1 = fechini.getTime();
         SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
         String fechaselec = dat.format(fecha1);
         System.out.println(fechaselec);
         return fechaselec;
    }
    public String Capturarfechafinal(){
         Calendar fechfin =  rango.getFechaFinal().getSelectedDate();
         Date fecha1 = fechfin.getTime();
         SimpleDateFormat dat = new SimpleDateFormat("dd-MM-yyyy");
         String fechaselec = dat.format(fecha1);
         System.out.println(fechaselec);
         return fechaselec;
    }

  @Override
  public void onSelectionChange(SelectionChangedEvent sce) {
   vista.getEmitir().setEnabled(true);
  }


    
}
