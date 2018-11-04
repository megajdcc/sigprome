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

import Vista.Respaldo;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import Modelo.ModeloRespaldo;
import Vista.Principal;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Jnatn'h
 */
public class ControladorRespaldo implements ActionListener{
    private String rutaRespaldo = "";
    private String extension = "";
    private boolean parcialActivo = true;
    private final byte RESPALDO = 1;
    private final byte RESTAURACION = 2;
    private Respaldo respaldo;
    private ModeloRespaldo modelo;
    private Principal principal;
    public ControladorRespaldo(Respaldo respaldo){
        this.respaldo = respaldo;
        modelo = new ModeloRespaldo();
        //Ocultar el Boton de "Respaldo"
        respaldo.getBtnRespaldar().setVisible(false);
        //Preselección de "Parcial"
        respaldo.getRadioParcial().setSelected(true);
        //Cargar tablas de base de datos en 'Listado Rojo'
        respaldo.getListadoTablas().setModel(modelo.ConsultarTablas() );
        //Inicializar 'Listado verde' (Limpiar)
        respaldo.getListadoRespaldar().setModel( new DefaultListModel() );
        //Detectar el Sistema Operativo
        respaldo.getLabelSO().setText( System.getProperty("os.name") );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object evento = e.getSource();

        if( evento.equals( respaldo.getRadioTotal() ) ){

            //Cargar tablas base de datos en 'Listado Verde'
            respaldo.getListadoRespaldar().setModel(modelo.ConsultarTablas() );
            //Inicializar 'Listado Rojo' (Limpiar)
            respaldo.getListadoTablas().setModel( new DefaultListModel() );

            //Ocultar botones (<, >) ya que no se utilizaran al realizar un respaldo de todas las tablas de la base de datos
            respaldo.getBtnDerecha().setVisible(false);
            respaldo.getBtnIzquierda().setVisible(false);
        
        }else

        //Se ejecutara solo cuando la opción parcial no hubiese estado seleccionada anteriormente
        if( evento.equals( respaldo.getRadioParcial()) && parcialActivo == false ){

            ////Cargar tablas base de datos en 'Listado Rojo'
            respaldo.getListadoTablas().setModel(modelo.ConsultarTablas() );
            //Inicializar 'Listado Rojo' (Limpiar)
            respaldo.getListadoRespaldar().setModel( new DefaultListModel() );

            //Mostrar botones (< , >) ya que se utilizaran para elegir las tablas a respaldar
            respaldo.getBtnDerecha().setVisible(true);
            respaldo.getBtnIzquierda().setVisible(true);
        }else

        if( evento.equals( respaldo.getBtnRutaSource() )){

            //Obtenemos la ruta seleccionada
            rutaRespaldo = seleccionarRuta(RESPALDO);
            //Mostramos la ruta seleccionada, concatenando la ruta más la extesión
            respaldo.getLabelSource().setText(rutaRespaldo + extension);

        }else

        if( evento.equals( respaldo.getRadioSql() ) ){
            extension = ".sql";

            //Mostramos la ruta seleccionada, concatenando la ruta más la extesión (.sql)
            respaldo.getLabelSource().setText( rutaRespaldo + extension);

        }else

        if( evento.equals( respaldo.getRadioBackup()) ){
            extension = ".backup";

            //Mostramos la ruta seleccionada, concatenando la ruta más la extesión (.backup)
            respaldo.getLabelSource().setText( rutaRespaldo + extension);

        }else

        if( evento.equals( respaldo.getBtnDerecha() ) ){

            //Se envian como parametros la Lista Roja y Verde
            moverSeleccion(respaldo.getListadoTablas(), respaldo.getListadoRespaldar());

        }else

        if( evento.equals( respaldo.getBtnIzquierda() ) ){

            //Se envian como parametros la Lista Verde y Roja
            moverSeleccion(respaldo.getListadoRespaldar(), respaldo.getListadoTablas() );

        }else

        if( evento.equals( respaldo.getBtnRespaldar() ) ){

            respaldar();
        }else

        if( evento.equals( respaldo.getBtnRestaurar() ) ){

            restaurar( seleccionarRuta(RESTAURACION) );
        }
        else if(evento.equals(respaldo.getSalir())){
            respaldo.dispose();
        }

        //Metodo encargado de Validar
        todoListo();
    }
    private void moverSeleccion(JList origen, JList destino){

        //Se obitnen los modelos de "origen" y "destino" para poder utilizar el metodo 'addElement' y 'removeRange'
        DefaultListModel listaOrigen = (DefaultListModel) origen.getModel();
        DefaultListModel listaDestino = (DefaultListModel) destino.getModel();

        //Se almacenan los identificadores de las tablas seleccionadas
        int[] indice = origen.getSelectedIndices();
        //Se almacena los nombres de las tablas seleccionadas de la "Lista Roja"
        Object[] tabla = origen.getSelectedValues();

            byte i = 0;

            for(; i < indice.length; i++ ){

                //Se insertan los nombres de las tablas seleccionadas
                listaDestino.addElement( tabla[i] );
            }

            if( indice.length > 0)
                //Se eliminan las tablas de la "Lista Roja" que ya han sido insertadas en la "Lista Verde"
                listaOrigen.removeRange(indice[0], indice[i - 1]);
            else
                //Al no seleccionar un elemento de Origen y dar click en el boton ( < - > )
                JOptionPane.showMessageDialog(principal, "Seleccione un elemento de la lista", "ERROR !!!", JOptionPane.ERROR_MESSAGE);
    }
    private void todoListo(){

        parcialActivo = respaldo.getRadioParcial().isSelected();
        System.out.println(parcialActivo);
        //Inicializa en 0 (invalido)
        byte[] valido = {0, 0, 0};

        //Si la ruta del respaldo y la extensión no estan vacias
        if( rutaRespaldo.isEmpty() == false && extension.isEmpty() == false){
            valido[0] = 1;
        
        }else{
            valido[0] = 0;
        }

        //Si la lista Verde almenos tiene 1 tabla para respaldar
        if( respaldo.getListadoRespaldar().getModel().getSize() > 0){
            valido[1] = 1;
        }else{
            valido[1] = 0;
        }

        //Si almenos seleccionamos un formato
        if( respaldo.getRadioSql().isSelected() == true || respaldo.getRadioBackup().isSelected() == true){
            valido[2] = 1;
        
        }else{
            valido[2] = 0;
        }

        //Se inicializa en 1 (valido)
        byte resultado = 1;

        for(byte i: valido){
            resultado = (byte) (resultado * i);
        }

        if( resultado == 1)
            //Al todos los campos ser validos se mostrara el boton de "Respaldo"
            respaldo.getBtnRespaldar().setVisible(true);
        else
            respaldo.getBtnRespaldar().setVisible(false);
    }
    private String seleccionarRuta(byte tipoRuta){

        //Crear objeto "seleccionar" de tipo "JFileChooser"
        JFileChooser seleccionar = new JFileChooser();
        //Mensaje a mostrar al seleccionar donde almacenaremos la base de datos
        //Y se indican los tipos de archivos que podran ser visualizados

        String[] sql = {" y sql", "sql"};

        if( tipoRuta == RESTAURACION )
            sql[0] = ""; sql[1] = ".backup";

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo backup" + sql[0], "backup", sql[1]);

        //Titulo que mostrara la ventana de exploración
        seleccionar.setDialogTitle("Seleccionar Ruta");
        //Se asigna el filtro previamente declaro a neustra ventana de exploración
        seleccionar.setFileFilter(filtro);

        //"showSaveDialog(respaldo)" Se mostrara nuestra ventana de exploración
        //Se indica que el padre es la respaldo (VistaPrincipal)

        byte estadoVentana = 2;

        if( tipoRuta == RESPALDO){
            estadoVentana = (byte) seleccionar.showSaveDialog(respaldo);

        }else

        if( tipoRuta == RESTAURACION ){
            estadoVentana = (byte) seleccionar.showOpenDialog(respaldo);

        }

         //Guardar retorna 0 (APPROVE_OPTION)
        if( estadoVentana == seleccionar.APPROVE_OPTION ){

            return seleccionar.getSelectedFile().getAbsolutePath();

        }else

        //cancelar retorna 1(CANCEL_OPTION)
        if( estadoVentana == seleccionar.CANCEL_OPTION ){
            JOptionPane.showMessageDialog(principal, "Operación Cancelada");
        }else

        if( estadoVentana == seleccionar.ERROR_OPTION){
            JOptionPane.showMessageDialog(principal, "Operación Cancelada", "Error al Seleccionar ruta", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }
    private void reiniciarValores(){

    //Se inicializan los valores de los atributos y Listas
        respaldo.getLabelSource().setText("");
        rutaRespaldo = "";
        extension = "";

        respaldo.getListadoTablas().setModel( modelo.ConsultarTablas() );
        respaldo.getListadoRespaldar().setModel( new DefaultListModel() );
        respaldo.getFormatoSalida().clearSelection();
        respaldo.getRadioParcial().setSelected(true);

        respaldo.getBtnDerecha().setVisible(true);
        respaldo.getBtnIzquierda().setVisible(true);
    }
     private void respaldar(){

        //Al iniciar el proceso el cursor cambia a modo "Cargando o Espera"
        respaldo.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );

        //Se obtiene la cantidad de tablas que estan en la "Lista Verde"
        byte cantTablas = (byte) respaldo.getListadoRespaldar().getModel().getSize();

        String tablas = "";

        //Si se ha seleccionado el respaldo Parcial
        if( respaldo.getRadioParcial().isSelected() ){

            for(byte i = 0; i < cantTablas; i++){

                //Se recorren todas las tablas y concatenan en el mismo atributo "tablas" agregando ", -t," antes de cada nombre de tabla
                tablas += ",-t," + respaldo.getListadoRespaldar().getModel().getElementAt(i).toString();
            }
        }

        //Se envia la ruta del respaldo, las tablas y Si fue seleccionado (true) o no (False) el formato .sql (Texto Plano)
        if ( modelo.generarBackup( respaldo.getLabelSource().getText(), tablas, respaldo.getRadioSql().isSelected() )  ){

            reiniciarValores();
            JOptionPane.showMessageDialog(principal, "Respaldo Existoso!!!");

        }else{
            JOptionPane.showMessageDialog(principal, "Al respaldar la base de datos", "ERROR !!!", JOptionPane.ERROR_MESSAGE);
        }

        //Al finalizar el proceso el cursor regresa a su estado por defecto
        respaldo.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
    }

    /**
     * Metodo Encargado de:
     * <ul>
     *  <li>Enviar la Ruta de el archivo .sql o.backup</li>
     *  <li>donde se encuentre la data para respaldar la base de datos
     */
    private void restaurar(String rutaArchivo){

        //Al iniciar el proceso el cursor cambia a modo "Cargando o Espera"
        respaldo.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );

        if ( rutaArchivo != null && modelo.realizarRestore(rutaArchivo) == true ){
            JOptionPane.showMessageDialog(principal, "Restauración exitosa!!!");

        }else

        if( rutaArchivo != null && modelo.realizarRestore(rutaArchivo) == false){
            JOptionPane.showMessageDialog(respaldo, "Al restaurar la base de datos", "ERROR !!!", JOptionPane.ERROR_MESSAGE);
        }        

        //Al finalizar el proceso el cursor regresa a su estado por defecto
        respaldo.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

        reiniciarValores();
    }
}
