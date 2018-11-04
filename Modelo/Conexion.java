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
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Vista.*;
import Controlador.*;
/**
 *Clase que se utiliza para conectarse a la base de datos dada... 
 * Cada usuario tiene roles y privilegios distintos por lo tanto es necesario tenerlo en cuenta... 
 * @author Jnatn'h
 */
public class Conexion{
    private Connection conexion;
    private Statement stmt;
    private ResultSet rs;
    private final String driver = "org.postgresql.Driver";
    protected final String usuario = "postgres";
    protected final String clave = "paterblat";
    protected final String puerto = "5432";
    protected final String database = "sigprome";
    protected final String ip = "localhost";

    /**
     * El Constructor de la Class Conexion, inicializa al instanciarla la clase del controlador o driver
     * de la base de datos y esto lo hace utilizando la clase Class que no tiene constructor publico,
     * y utilizando el metodo static forName carga automaticamente la clase con el nombre dado en el parametro de forName.
     * Si la clase no es encontrada lanza una exception de tipo ClassNotFoundException...
     */
    public Conexion() {

        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
        }

    }

    protected final Connection crearConexion() {

        try {
            conexion = DriverManager.getConnection("jdbc:postgresql://"+ip+":" + puerto + "/" + database , usuario, clave);
//            conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sigprome", usuario, clave);
            conexion.setAutoCommit(false);

        } catch (SQLException e) {
            Login.cerrarVista();
        }
        return conexion;
    }

    protected final void cerrarConexion(Connection con) {

        if (con != null) {

            try {
                con.close();

            } catch (SQLException e) {
            }

        }

    }

    protected ResultSet consultarDatos(Connection con, String sql) {

        try {

            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return rs;

    }

    protected boolean actualizarDatos(Connection con, String sql) {

        boolean sw = false;

        try {
            stmt = con.createStatement();
            if (stmt.executeUpdate(sql) > 0) {
                sw = true;
            }

        } catch (SQLException e) {


            deshacerCambios(con);

        }

        return sw;

    }

    protected final void realizarCambios(Connection con) {

        try {

            con.commit();

        } catch (SQLException e) {

 

        }

    }

    protected final void deshacerCambios(Connection con) {

        try {

            con.rollback();

        } catch (SQLException e) {



        }

    }
}
