
package Modelo;

import Vista.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import Controlador.*;
import java.awt.Cursor;
import java.awt.Dialog;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.io.EOFException;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.print.JRPrinterAWT;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jnatn'h
 */
public class Reports {
    private final String url = "src\\Vista\\reporte\\";
    private final String logogobierno = "/Vista/imagen/logogobierno.jpeg";
    private final String logouptyab = "/Vista/imagen/uptyab.png";
    private final String logo200anos = "/Vista/imagen/200patriotas.png";
    private File ruta; 
    private ModeloConexion conexion = null;
    private Principal principal;
    private ControladorPaciente ContrPaciente;
    private ModeloPaciente modelPaciente;
    private ControladorConsulta consulta;
    //Constructores
    public Reports(long Cedulapaciente,ControladorPaciente  Controller){
       conexion = new ModeloConexion();
      if (Controller.toString().contains("ControladorPaciente")){
        modelPaciente = new ModeloPaciente();
        String tipopaciente = modelPaciente.CapturarTipoPaciente(Cedulapaciente);
        Controller.vista.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
      }
    }
    public Reports(){
      conexion = new ModeloConexion();
    }
    
    public void fedevida(Long cedula,String Condicion,boolean impresion){
        
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());        
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        String mes = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, new Locale("Es","Venezuela"));
        int anos = cal.get(Calendar.YEAR);
            
        try {
             
             
            URL rut = getClass().getResource("/Vista/reporte/Fedevida.jasper");
            JasperReport recipe = (JasperReport) JRLoader.loadObject(rut); // cargamos el archivo Jasper.
            Map<String, Object> param = new HashMap<String, Object>();
            
            param.put("cedula", cedula);// el primer parametro es la clave y el segundo el valor... 
            param.put("condicionsalud", Condicion);// el primer parametro es la clave y el segundo el valor... 
            param.put("dia", dia);// el primer parametro es la clave y el segundo el valor... 
            param.put("mes", mes);// el primer parametro es la clave y el segundo el valor... 
            param.put("anos", anos);// el primer parametro es la clave y el segundo el valor... 
            param.put("header", this.getClass().getResourceAsStream("/Vista/reporte/HeaderReports.jasper"));
            param.put("logogobierno1", this.getClass().getResourceAsStream(logogobierno));
            param.put("logouptyab1", this.getClass().getResourceAsStream(logouptyab));
            param.put("logo200anos1", this.getClass().getResourceAsStream(logo200anos));
            
            JasperPrint recip = JasperFillManager.fillReport(recipe, param,conexion.getConec());
            
            JRPropertiesMap objmap = recip.getPropertiesMap();
            
            String[] dat = objmap.getPropertyNames();
             for (String data : dat) {
                 System.out.println("Nombre Propiedad: "+ data);
             }
//            JRPrinterAWT imprimir = new JRPrinterAWT(recip);   
            Locale miregion = new Locale("es","VE");
            JasperViewer jv = new JasperViewer(recip,false,miregion); // herramienta de visualización del informe...
            jv.setAlwaysOnTop(true);
            jv.setAutoRequestFocus(true);
            jv.setExtendedState(MAXIMIZED_BOTH);
            jv.setResizable(true);
            jv.setTitle("Fe de Vida");
            if(impresion){
                boolean imprimio = JRPrinterAWT.printPages(recip,0,recip.getPages().size()-1,true);
            }else{
             
              Controladorprincipal control = Principal.getController();
              control.getVista().toBack();
               jv.setVisible(true);
               
               jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
               jv.toFront();
            } 
            
        }catch(JRException | JRRuntimeException e){
        	e.printStackTrace();
             JOptionPane.showMessageDialog(principal, "No se pudo abrir el reporte:"+e);
        }
        
    }
    public void listpatologia(){
      try {
        URL rut = getClass().getResource("/Vista/reporte/ListaPatologia.jasper");
        JasperReport report = (JasperReport) JRLoader.loadObject(rut);
        JasperPrint j = JasperFillManager.fillReport(report, null,conexion.getConec());
        JasperViewer jv = new JasperViewer(j,false);
        jv.setTitle("Patologías");
        jv.setAlwaysOnTop(true);
       
        jv.setVisible(true);
        jv.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        
      } catch (JRException e) {
       JOptionPane.showMessageDialog(principal, "No se pudo abrir el reporte: "+ e.getMessage());
      }
    }
    public void recipeReports(int idconsulta, boolean impresion,ControladorConsulta consulta){
        try {
            URL rut = getClass().getResource("/Vista/reporte/Recipe.jasper");
            JasperReport recipe = (JasperReport) JRLoader.loadObject(rut); // cargamos el archivo Jasper.
            Map param = new HashMap();
            param.clear();
            
            param.put("idconsulta", idconsulta);// el primer parametro es la clave y el segundo el valor... 
            param.put("header", this.getClass().getResourceAsStream("/Vista/reporte/HeaderReports.jasper"));
            param.put("footerrecip", this.getClass().getResourceAsStream("/Vista/reporte/Recipe_subreport2.jasper"));
//            param.put("indicaciones", this.getClass().getResourceAsStream("/Vista/reporte/Indicaciones.jasper"));
            param.put("logogobiern", this.getClass().getResourceAsStream(logogobierno));
            param.put("logoupt", this.getClass().getResourceAsStream(logouptyab));
            param.put("logo200yeard", this.getClass().getResourceAsStream(logo200anos));
           
           
            JasperPrint recip = JasperFillManager.fillReport(recipe, param,conexion.getConec());
//            JRPrinterAWT imprimir = new JRPrinterAWT(recip);   
            Locale miregion = new Locale("es","VE");
            JasperViewer jv = new JasperViewer(recip,false,miregion); // herramienta de visualización del informe...
            jv.setAlwaysOnTop(true);
            jv.setAutoRequestFocus(true);
            jv.setExtendedState(MAXIMIZED_BOTH);
            jv.setResizable(true);
            jv.setTitle("Recipe");
            if(impresion){
                boolean imprimio = JRPrinterAWT.printPages(recip,0,1,true);
            }else{
             
              Controladorprincipal control = Principal.getController();
              control.getVista().toBack();
               jv.setVisible(true);
               
               jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
               jv.toFront();
            }           
        }catch(JRException | JRRuntimeException e){
             JOptionPane.showMessageDialog(principal, "No se pudo abrir el reporte:"+e);
        }
    }
    public void reposoReports(int idconsulta, boolean impresion,ControladorConsulta consulta){
        try {
            URL rut = getClass().getResource("/Vista/reporte/Reposo.jasper");
            JasperReport reposo = (JasperReport) JRLoader.loadObject(rut); // cargamos el archivo Jasper.
            Map param = new HashMap();
            param.clear();
            
            param.put("idconsulta", idconsulta);// el primer parametro es la clave y el segundo el valor...
            param.put("header", this.getClass().getResourceAsStream("/Vista/reporte/HeaderReports.jasper"));
            param.put("pagefooter", this.getClass().getResourceAsStream("/Vista/reporte/Recipe_subreport2.jasper"));
            param.put("logogobierno", this.getClass().getResourceAsStream(logogobierno));
            param.put("logoupt", this.getClass().getResourceAsStream(logouptyab));
            param.put("logo200anos", this.getClass().getResourceAsStream(logo200anos));
            
            JasperPrint repos = JasperFillManager.fillReport(reposo, param,conexion.getConec());
//            JRPrinterAWT imprimir = new JRPrinterAWT(recip);   
            Locale miregion = new Locale("es","VE");
            JasperViewer jv = new JasperViewer(repos,false,miregion); // herramienta de visualización del informe...
            jv.setAlwaysOnTop(true);
            jv.setAutoRequestFocus(true);
            jv.setExtendedState(MAXIMIZED_BOTH);
            jv.setResizable(true);
            jv.setTitle("Reposo");
            if(impresion){
                boolean imprimio = JRPrinterAWT.printPages(repos,0,0,true);
            }else{
             
              Controladorprincipal control = Principal.getController();
              control.getVista().toBack();
               jv.setVisible(true);
               
               jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
               jv.toFront();
            }           
        } catch (JRException e) {
            JOptionPane.showMessageDialog(principal, "No se pudo abrir el reporte:"+e);
        }
    }
    public void morbilidadPatologia(Date fechainicial,Date fechafinal,boolean impresion){
      String rut = FileSystems.getDefault().toString();
   
       try {
            ruta = new File(url+"MorbilidadPatologias.jasper");
            JasperReport morbilidad = (JasperReport) JRLoader.loadObject(ruta); // cargamos el archivo Jasper.
            Map param = new HashMap();

            param.put("fechainicial", fechainicial);// el primer parametro es la clave y el segundo el valor... 
            param.put("fechafinal", fechafinal);

            JasperPrint morbi = JasperFillManager.fillReport(morbilidad, param,conexion.getConec());
//            JRPrinterAWT imprimir = new JRPrinterAWT(recip);   
            Locale miregion = new Locale("es","VE");
            JasperViewer jv = new JasperViewer(morbi,false,miregion); // herramienta de visualización del informe...
            jv.setAlwaysOnTop(true);
            jv.setAutoRequestFocus(true);
            jv.setExtendedState(MAXIMIZED_BOTH);
            jv.setResizable(true);
            jv.setTitle("Primeras patologias");
            if(impresion){
                boolean imprimio = JRPrinterAWT.printPages(morbi,0,0,true);
            }else{
             
              Controladorprincipal control = Principal.getController();
              control.getVista().toBack();
               jv.setVisible(true);
               
               jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
               jv.toFront();
            }           
        } catch (JRException e) {
            JOptionPane.showMessageDialog(principal, "No se pudo abrir el reporte:"+e);
        }
    }
    public void morbilidadallrang(Date fechaini, Date fechafin,String tipopersona,String tiposervicio, boolean imprimir){
        try {
            JasperReport morbilidad;
              Locale miregion = new Locale("es","VE");
            Map param = new HashMap();
            
            if(tipopersona.equals("TODAS") && tiposervicio.equals("TODAS")){
                ruta = new File(url+"MorbilidadMedica.jasper");
                morbilidad = (JasperReport) JRLoader.loadObject(ruta); // cargamos el archivo Jasper.
                param.put("primerafecha", fechaini);// el primer parametro es la clave y el segundo el valor... 
                param.put("ultimafecha", fechafin);// el primer parametro es la clave y el segundo el valor... 
                JasperPrint morbi = JasperFillManager.fillReport(morbilidad, param,conexion.getConec());
                JasperViewer jv = new JasperViewer(morbi,false,miregion); // herramienta de visualización del informe...
                jv.setAlwaysOnTop(true);
                jv.setAutoRequestFocus(true);
                jv.setExtendedState(MAXIMIZED_BOTH);
                jv.setResizable(true);
                jv.setTitle("Morbilidad");
                if(imprimir){
                  int maxpag = morbi.getPages().size();
                    boolean imprimio = JRPrinterAWT.printPages(morbi,0,maxpag-1,true);
                }else{
                   jv.setVisible(true);
                   jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                   jv.toFront();
                }   
            }else if(!tipopersona.equals("TODAS") && tiposervicio.equals("TODAS")){
              String tipoperson = tipopersona; 
              switch (tipopersona) {
                case "EMPLEADO":
                  {
                    // Cuando la morbilidad se solicitad solo para el empleado...
                    ruta = new File(url+"MorbilidadMedicaEmpleado.jasper");
                    morbilidad = (JasperReport) JRLoader.loadObject(ruta); // cargamos el archivo
                    param.put("primerafecha", fechaini);// el primer parametro es la clave y el segundo el valor... 
                    param.put("ultimafecha", fechafin);// el primer parametro es la clave y el segundo el valor... 
                    param.put("tipoperson", tipopersona);// el primer parametro es la clave y el segundo el valor...
                    JasperPrint morbi = JasperFillManager.fillReport(morbilidad, param,conexion.getConec());
                    JasperViewer jv = new JasperViewer(morbi,false,miregion); // herramienta de visualización del informe...
                    jv.setAlwaysOnTop(true);
                    jv.setAutoRequestFocus(true);
                    jv.setExtendedState(MAXIMIZED_BOTH);
                    jv.setResizable(true);
                    jv.setTitle("Morbilidad de Empleados");
                    if(imprimir){
                      int maxpag = morbi.getPages().size();
                      boolean imprimio = JRPrinterAWT.printPages(morbi,0,maxpag-1,true);
                    }else{
                      jv.setVisible(true);
                      jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                      jv.toFront();
                    }
                    break;
                  }
                case "COMUNIDAD":
                  {
                    //Cuando la morbilidad se solicita solo para la comunidad
                    // Cuando la morbilidad se solicitad solo para el empleado...
                    ruta = new File(url+"MorbilidadMedicaComunidad.jasper");
                    morbilidad = (JasperReport) JRLoader.loadObject(ruta); // cargamos el archivo
                    param.put("primerafecha", fechaini);// el primer parametro es la clave y el segundo el valor... 
                    param.put("ultimafecha", fechafin);// el primer parametro es la clave y el segundo el valor... 
                    param.put("tipoperson", tipopersona);// el primer parametro es la clave y el segundo el valor...
                    JasperPrint morbi = JasperFillManager.fillReport(morbilidad, param,conexion.getConec());
                    JasperViewer jv = new JasperViewer(morbi,false,miregion); // herramienta de visualización del informe...
                    jv.setAlwaysOnTop(true);
                    jv.setAutoRequestFocus(true);
                    jv.setExtendedState(MAXIMIZED_BOTH);
                    jv.setResizable(true);
                    jv.setTitle("Morbilidad de la Comunidad");
                    if(imprimir){
                      int maxpag = morbi.getPages().size();
                      boolean imprimio = JRPrinterAWT.printPages(morbi,0,maxpag-1,true);
                    }else{
                      jv.setVisible(true);
                      jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                      jv.toFront();
                    }
                    break;
                  }
                default:
                  {
                    // cuando es un estudiante...
                    //Cuando la morbilidad se solicita solo para la comunidad
                    // Cuando la morbilidad se solicitad solo para el empleado...
                    ruta = new File(url+"MorbilidadMedicaEstudiante.jasper");
                    morbilidad = (JasperReport) JRLoader.loadObject(ruta); // cargamos el archivo
                    param.put("primerafecha", fechaini);// el primer parametro es la clave y el segundo el valor... 
                    param.put("ultimafecha", fechafin);// el primer parametro es la clave y el segundo el valor... 
                    param.put("tipoperson", tipopersona);// el primer parametro es la clave y el segundo el valor...
                    JasperPrint morbi = JasperFillManager.fillReport(morbilidad, param,conexion.getConec());
                    JasperViewer jv = new JasperViewer(morbi,false,miregion); // herramienta de visualización del informe...
                    jv.setAlwaysOnTop(true);
                    jv.setAutoRequestFocus(true);
                    jv.setExtendedState(MAXIMIZED_BOTH);
                    jv.setResizable(true);
                    jv.setTitle("Morbilidad de Estudiantes");
                    if(imprimir){
                      int maxpag = morbi.getPages().size();
                      boolean imprimio = JRPrinterAWT.printPages(morbi,0,maxpag-1,true);
                    }else{
                      jv.setVisible(true);
                      jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                      jv.toFront();
                    }   
                    break;
                  }
              }
            }else if(!tipopersona.equals("TODAS") && !tiposervicio.equals("TODAS")){
                String tipoperson = tipopersona;
                if(tipopersona.equalsIgnoreCase("Empleado")){
                  // Cuando la morbilidad se solicitad solo para el empleado... 
                  ruta = new File(url+"MorbilidadMedicaE.jasper");
                  morbilidad = (JasperReport) JRLoader.loadObject(ruta); // cargamos el archivo
                  param.put("primerafecha", fechaini);// el primer parametro es la clave y el segundo el valor... 
                  param.put("ultimafecha", fechafin);// el primer parametro es la clave y el segundo el valor... 
                  param.put("tipoperson", tipopersona);// el primer parametro es la clave y el segundo el valor...
                  param.put("tiposervicio", tiposervicio);
                  JasperPrint morbi = JasperFillManager.fillReport(morbilidad, param,conexion.getConec());
                    JasperViewer jv = new JasperViewer(morbi,false,miregion); // herramienta de visualización del informe...
                    jv.setAlwaysOnTop(true);
                    jv.setAutoRequestFocus(true);
                    jv.setExtendedState(MAXIMIZED_BOTH);
                    jv.setResizable(true);
                    jv.setTitle("Morbilidad de "+tipoperson);
                    if(imprimir){
                      int maxpag = morbi.getPages().size();
                        boolean imprimio = JRPrinterAWT.printPages(morbi,0,maxpag-1,true);
                    }else{
                       jv.setVisible(true);
                       jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                       jv.toFront();
                    }
                }else{
                  // Cuando la morbilidad se solicitad solo para el empleado... 
                    ruta = new File(url+"MorbilidadMedicaP.jasper");
                    morbilidad = (JasperReport) JRLoader.loadObject(ruta); // cargamos el archivo
                    param.put("primerafecha", fechaini);// el primer parametro es la clave y el segundo el valor... 
                    param.put("ultimafecha", fechafin);// el primer parametro es la clave y el segundo el valor... 
                    param.put("tipoperson", tipopersona);// el primer parametro es la clave y el segundo el valor...
                    param.put("tiposervicio", tiposervicio);
                    JasperPrint morbi = JasperFillManager.fillReport(morbilidad, param,conexion.getConec());
                    JasperViewer jv = new JasperViewer(morbi,false,miregion); // herramienta de visualización del informe...
                    jv.setAlwaysOnTop(true);
                    jv.setAutoRequestFocus(true);
                    jv.setExtendedState(MAXIMIZED_BOTH);
                    jv.setResizable(true);
                    jv.setTitle("Morbilidad de "+tipoperson);
                    if(imprimir){
                      int maxpag = morbi.getPages().size();
                        boolean imprimio = JRPrinterAWT.printPages(morbi,0,maxpag-1,true);
                    }else{
                       jv.setVisible(true);
                       jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
                       jv.toFront();
                    }
                }
            }
        } catch (JRException e) {
            JOptionPane.showMessageDialog(principal, "No se pudo abrir el reporte:"+e);
        }
    }
    public void morbilidad1(String tpersona, String tpservi, String fechaini,String fechafin){
        try {
           // String url  = System.getProperties("user.dir")+"/Vista/reporte/recipe.jasper";
            //System.out.println(url);
            ruta = new File(url+"Morbilidad.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(ruta);
           // JasperReport report = (JasperReport) JRLoader.loadObject("rep-paciente.jasper");

            Map param = new HashMap();
            
            param.put("tpersona", tpersona);
            param.put("tpservi", tpservi);
            param.put("fechaini", fechaini);
            param.put("fechafin", fechafin);
            param.put("", Principal.getIdUsuario());
            
            JasperPrint j = JasperFillManager.fillReport(report, param,conexion.getConec());
            JasperViewer jv = new JasperViewer(j,false);
            jv.setTitle("recipe");
            jv.setVisible(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(principal, "No se pudo abrir el reporte:"+e);
        }
    }
    public void paciente(long cedula,boolean impresion) {

            
        try {
             
             
            URL rut = getClass().getResource("/Vista/reporte/rep-paciente.jasper");
            JasperReport paciente = (JasperReport) JRLoader.loadObject(rut); // cargamos el archivo Jasper.
            Map param = new HashMap();
            
            param.put("cedul", cedula);// el primer parametro es la clave y el segundo el valor... 
                       
            JasperPrint report = JasperFillManager.fillReport(paciente, param,conexion.getConec());
//          JRPrinterAWT imprimir = new JRPrinterAWT(recip);   
            Locale miregion = new Locale("es","VE");
            JasperViewer jv = new JasperViewer(report,false,miregion); // herramienta de visualización del informe...
            jv.setAlwaysOnTop(true);
            jv.setAutoRequestFocus(true);
            jv.setExtendedState(MAXIMIZED_BOTH);
            jv.setResizable(true);
            jv.setTitle("Paciente");
            if(impresion){
                boolean imprimio = JRPrinterAWT.printPages(report,0,report.getPages().size()-1,true);
            }else{
             
              Controladorprincipal control = Principal.getController();
              control.getVista().toBack();
               jv.setVisible(true);
               
               jv.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
               jv.toFront();
            } 
            
        }catch(JRException | JRRuntimeException e){
        	e.printStackTrace();
             JOptionPane.showMessageDialog(principal, "No se pudo abrir el reporte:"+e);
        }
        
  }
}
