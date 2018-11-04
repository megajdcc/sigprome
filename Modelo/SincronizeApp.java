package Modelo;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.DigestUtils;

import Vista.Principal;
import sun.util.calendar.Gregorian;

public class SincronizeApp<ListArray> extends ConectionApp{
	
	
	private ModeloConexion conexion;
	public SincronizeApp() {
		super();
		conexion = new ModeloConexion();
		
		runsincron();
		
		
	}
	
	private void runsincron() {
		
		//Table Tipo Persona 
		try {
			usuariosesion(conexion.getConec());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			sintipopersona();
			sincedo();
			sincmunicipio();
			sincparroquia();
			sincpersona();
			sinctiposervicio();
			sinccita();
			sincopcionesadministrativas();
			sincusuario();
			sincservicio();
			sincestado();
			sincopcionservicio();
			sinctriaje();
			sincpatologias();
			sincconsulta();
			sincassocpatologia();
			sincreposo();
			sincrecipe();
			sincindicaciones();
	}
	
	private void sintipopersona() {
		
		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from tipopersona";
		String tipoperson1 = "INSERT INTO tipopersona(id,nombre) values(?,?)";
		String tipoperson3 = "UPDATE tipopersona set nombre = ? where id = ?";
		String tipoperson4 = "select * from tipopersona";
		String deletipopers = "DELETE FROM tipopersona where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("actualizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("nombre"));
						tipopers3.setInt(2, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("nombre"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE tipopersona set actualizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("Ejecutado exitosamente...");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
	}
	
	protected void usuariosesion(Connection con) throws SQLException{
	        Statement sen;
	        ResultSet rs;
	        try
	        {
	            String sentenciaSQL = "SELECT table_temporal("+Principal.getIdUsuario()+")";
	            sen = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            rs = sen.executeQuery(sentenciaSQL);
	            if (rs.next()) {
	                rs.beforeFirst();
	            }else{
	                System.out.println("Error! Problema creando la tabla temporal...");
	            }
	        } catch(SQLException ex)
	        {
	           // System.out.println("Error: "+ex.getMessage());
	        }
	    }
	
	private void sincedo() {
		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from estado";
		String tipoperson1 = "INSERT INTO edo(id,nombre) values(?,?)";
		String tipoperson3 = "UPDATE edo set nombre = ? where id = ?";
		String tipoperson4 = "select * from edo";
		String deletipopers = "DELETE FROM edo where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("nombre"));
						tipopers3.setInt(2, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("nombre"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE estado set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("Edo sincronizado exitosamente ... ");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
	private void sincmunicipio() {
		
		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from municipio";
		String tipoperson1 = "INSERT INTO municipio(id,nombre,id_estado) values(?,?,?)";
		String tipoperson3 = "UPDATE municipio set nombre = ?, id_estado = ? where id = ?";
		String tipoperson4 = "select * from municipio";
		String deletipopers = "DELETE FROM municipio where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("nombre"));
						tipopers3.setInt(2, result.getInt("idestado"));
						tipopers3.setInt(3, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
					
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("nombre"));
						tipo1.setInt(3, result.getInt("idestado"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE municipio set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("municipio sincronizado exitosamente ... ");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	private void sincparroquia() {
		
		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from parroquia";
		String tipoperson1 = "INSERT INTO parroquia(id,nombre,id_municipio) values(?,?,?)";
		String tipoperson3 = "UPDATE parroquia set nombre = ?, id_municipio = ? where id = ?";
		String tipoperson4 = "select * from parroquia";
		String deletipopers = "DELETE FROM parroquia where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("nombre"));
						tipopers3.setInt(2, result.getInt("idmunicipio"));
						tipopers3.setInt(3, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("nombre"));
						tipo1.setInt(3, result.getInt("idmunicipio"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE parroquia set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("parroquia sincronizado exitosamente ... ");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	private void sincpersona() {
		
		Connection conec1 = conexion.getConec();
		Connection conec2 = super.getConec();
		
		try {
			conec1.setAutoCommit(false);
			conec2.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String sql1 = "select * from persona";
		String sincronizado1 = "UPDATE persona set sincronizado = ? where cedula = ? ";
		String sincronizado2 = "UPDATE usuario set sincronizado = ? where cedulapersona = ? ";
		
		String verificarapp = "select p.telefono,u.email,p.cedula from persona as p "
				+ " inner join usuario as u on p.cedula = u.cedulapersona where (p.sincronizado = false || u.sincronizado = false)";
		
		
		String consultid = "select * from persona";
		String insert = "insert into persona (cedula,"
				+ "nombre,"
				+ "apellido,"
				+ "fechanacimiento,"
				+ "direccion,"
				+ "id_tipopersona,"
				+ "telefono,"
				+ "genero,"
				+ "id_parroquia,"
				+ "actualizado)"
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		String actualizar = "UPDATE persona set nombre = ?, apellido = ?,fechanacimiento = ?, direccion = ?, id_tipopersona = ?, telefono = ?,genero= ?,id_parroquia = ? , actualizado = ? where cedula = ?";
		
		
		String updatesigpro = "UPDATE persona set telefono = ?, correo = ? where cedula = ?";
		
		String cambiarupdate = "UPDATE persona set actualizado = ? where cedula = ?";
		String deletepersona = "DELETE FROM persona where cedula = ?";
		
		
		PreparedStatement delete,consulta,insertar,update,consultaid, updateapp,insertarsigpro,sincroni1,sincroni2,updatepersona;
		
		
		
		
		try {
			
			updateapp = conec2.prepareCall(verificarapp, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			insertarsigpro = conec1.prepareStatement(updatesigpro);
			
			sincroni1 = conec2.prepareStatement(sincronizado1);
			sincroni2 = conec2.prepareStatement(sincronizado2);
			
			ResultSet appperson = updateapp.executeQuery();
			
			ArrayList<Long> listidd = new ArrayList<Long>();
			
			while(appperson.next()) {
				
				listidd.add(appperson.getLong("cedula"));
				
				insertarsigpro.setLong(1, appperson.getLong("telefono"));
				insertarsigpro.setString(2, appperson.getString("email"));
				insertarsigpro.setLong(3, appperson.getLong("cedula"));
				
				insertarsigpro.executeUpdate();
				
				sincroni1.setBoolean(1, true);
				sincroni1.setLong(2, appperson.getLong("cedula"));
				sincroni1.executeUpdate();
				
				sincroni2.setBoolean(1, true);
				sincroni2.setLong(2, appperson.getLong("cedula"));
				sincroni2.executeUpdate();

			}
			
			consulta = conec1.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			update = conec2.prepareStatement(actualizar);
			
			insertar = conec2.prepareStatement(insert);
			
			consultaid = conec2.prepareStatement(consultid);
			
			ResultSet contentid = consultaid.executeQuery();
			
			ArrayList<Long> cedula = new ArrayList<Long>();
			
			while(contentid.next()) {
				cedula.add(contentid.getLong("cedula"));
			}
			
			updatepersona = conec1.prepareStatement(cambiarupdate);
			
			ResultSet result = consulta.executeQuery();
			
			while(result.next()) {
				if(	result.getBoolean("actualizado") == false ) {
					
					if(cedula.contains(result.getLong("cedula"))) {						
						update.setString(1, result.getString("nombre"));
						update.setString(2, result.getString("apellido"));
						update.setDate(3, result.getDate("fecha_nacimiento"));
						update.setString(4, result.getString("direccion"));
						update.setInt(5, result.getInt("id_tipopersona"));
						update.setLong(6, result.getLong("telefono"));
						update.setString(7, result.getString("sexo"));
						update.setInt(8, result.getInt("id_parroquia"));
						update.setBoolean(9, true);
						update.setLong(10,result.getLong("cedula"));
						update.executeUpdate();
						
						
						updatepersona.setBoolean(1, true);
						updatepersona.setLong(2, result.getLong("cedula"));
						updatepersona.executeUpdate();
					}else {
						insertar.setLong(1,result.getLong("cedula"));
						insertar.setString(2, result.getString("nombre"));
						insertar.setString(3, result.getString("apellido"));
						insertar.setDate(4, result.getDate("fecha_nacimiento"));
						insertar.setString(5, result.getString("direccion"));
						
						insertar.setInt(6, result.getInt("id_tipopersona"));
						insertar.setLong(7, result.getLong("telefono"));
						insertar.setString(8, result.getString("sexo"));
						insertar.setInt(9, result.getInt("id_parroquia"));
						insertar.setBoolean(10, true);
						insertar.executeUpdate();
						
						updatepersona.setBoolean(1, true);
						updatepersona.setLong(2, result.getLong("cedula"));
						updatepersona.executeUpdate();
					}
				}
				
			}
			
			
			// eliminar personas si no estan en la base de dato  por si ... ... 
			
//			ArrayList<Long> list = new ArrayList<Long>();
//			
//			while(result.next()) {
//				list.add(result.getLong("cedula"));
//				
//			}
//			delete = conec2.prepareStatement(deletepersona);
//			
//			for(int i = 0; i < listidd.size(); i++) {
//				if(!list.contains(listidd.get(i))) {
//					delete.setLong(1, listidd.get(i));
//					delete.executeUpdate();
//				}
//			}
//			
//			
//			
			conec1.commit();
			conec2.commit();
			
			System.out.println("persona Sincronizada");
		} catch (SQLException e) {
			try {
				conec1.rollback();
				conec2.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
		
	}

	private void sinctiposervicio() {
		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from tiposervicio";
		String tipoperson1 = "INSERT INTO tiposervicio(id,nombre) values(?,?)";
		String tipoperson3 = "UPDATE tiposervicio set nombre = ? where id = ?";
		String tipoperson4 = "select * from tiposervicio";
		String deletipopers = "DELETE FROM tiposervicio where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("nombre"));
						tipopers3.setInt(2, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("nombre"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE tiposervicio set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("se ha sincronizado tiposervicio");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	private void sinccita() {
		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from cita";
		String tipoperson1 = "INSERT INTO cita(id,fecha, cancelada,procesada) values(?,?,?,?)";
		String tipoperson3 = "UPDATE cita set fecha = ?, cancelada= ? , procesada = ? where id = ?";
		String tipoperson4 = "select * from cita";
		String deletipopers = "DELETE FROM cita where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setDate(1, result.getDate("fecha"));
						tipopers3.setBoolean(2, result.getBoolean("cancelada"));
						tipopers3.setBoolean(3, result.getBoolean("procesada"));
						tipopers3.setInt(4, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setDate(2, result.getDate("fecha"));
						tipo1.setBoolean(3, result.getBoolean("cancelada"));
						tipo1.setBoolean(4, result.getBoolean("procesada"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE cita set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("se ha sincronizado la cita...");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	private void sincopcionesadministrativas() {
		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from opcionesadministrativas";
		String tipoperson1 = "INSERT INTO opcionadministrativa(id,opcion) values(?,?)";
		String tipoperson3 = "UPDATE opcionadministrativa set opcion = ? where id = ?";
		String tipoperson4 = "select * from opcionadministrativa";
		String deletipopers = "DELETE FROM opcionadministrativa where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("nombre"));
						tipopers3.setInt(2, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("nombre"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE opcionesadministrativas set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("Se ha sincronizado opcionesadministrativas");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	private void sincusuario() {
		
		Connection conec1 = conexion.getConec();
		Connection conec2 = super.getConec();
		
		
		try {
			conec1.setAutoCommit(false);
			conec2.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String capturepaciente = "SELECT * FROM paciente";
		String captureusuario = "SELECT * from usuario";
		String insertemail = "update persona set correo = ? where cedula = ?";
		String updatesincroniuser = "update usuario set sincronizado = ?, ultimaactualizacion = ? where cedulapersona = ? ";
		String newuser = "insert into usuario(id,cedulapersona,contrasena,sincronizado) values(?,?,?,?)";
		
		PreparedStatement pacientes,usuarios,ingresaremail,sincroniuser,nuevo;
		try {
			pacientes = conec1.prepareStatement(capturepaciente,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			usuarios = conec2.prepareStatement(captureusuario,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			ingresaremail = conec1.prepareStatement(insertemail);
			sincroniuser = conec2.prepareStatement(updatesincroniuser);
			nuevo = conec2.prepareStatement(newuser);
			
			ResultSet result = pacientes.executeQuery();
			ResultSet resultado = usuarios.executeQuery();
			
			ArrayList iduser = new ArrayList();
			
			
			while(resultado.next()) {
				iduser.add(resultado.getInt("id"));
				if(resultado.getBoolean("sincronizado") == false ) {
						ingresaremail.setString(1, resultado.getString("email"));
						ingresaremail.setLong(2, resultado.getLong("cedulapersona"));						
						ingresaremail.executeUpdate();
						
						sincroniuser.setInt(1, 1);
						sincroniuser.setDate(2, new Date(new java.util.Date().getTime()));
						sincroniuser.setLong(3, resultado.getLong("cedulapersona"));
						sincroniuser.executeUpdate();
						
				}
			}
			
			
			while(result.next()) {
				
				if(result.getBoolean("sincronizado") == false ) {
					
					if(iduser.contains(result.getInt("id"))) {
						
					}else {
						nuevo.setInt(1, result.getInt("id"));
						nuevo.setLong(2, result.getLong("cedula"));
						nuevo.setString(3, DigestUtils.sha1Hex( String.valueOf(result.getLong("cedula") )));
						nuevo.setInt(4, 1);
						nuevo.executeUpdate();
					}
					
				}
				
			}
			
			
			
			conec1.commit();
			conec2.commit();
			System.out.println("Usuario Sincronizado... ");
		} catch (SQLException e) {
			
			
			try {
				conec1.rollback();
				conec2.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private void sincservicio() {
		
		Connection conec = conexion.getConec();
		Connection conec1 = super.getConec();
		
		try {
			conec.setAutoCommit(false);
			conec1.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		String servicios = "select * from servicio";
		String inservic = "INSERT INTO servicio(id,fecha,id_cita,id_tiposervicio,id_usuario,procesado,ultimaactualizacion)"
				+ "values(?,?,?,?,?,?,?)";
		String updateservi = "UPDATE servicio set fecha = ? id_cita = ? id_tiposervicio =?, id_usuario = ?, procesado = ? ultimaactualizacion = ? where id = ?";
		String updatesincroni = "UPDATE servicio set sincronizado = ? where id = ?";
		
		PreparedStatement listservicios, serviciosapp,ingresarservicio,actualizar,updatesincro;
		
		
		
		try {
			listservicios = conec.prepareStatement(servicios,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			serviciosapp = conec1.prepareStatement(servicios,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ingresarservicio = conec1.prepareStatement(inservic);
			updatesincro = conec.prepareStatement(updatesincroni);
			actualizar = conec1.prepareStatement(updateservi);
			
			ResultSet result;
			result = listservicios.executeQuery();
			
			ResultSet serviciosapk = serviciosapp.executeQuery();
			
			ArrayList<Integer> idservicios  = new ArrayList<Integer>();
			
			while(serviciosapk.next()) {
				idservicios.add(serviciosapk.getInt("id"));
			}
			
			while(result.next()) {
				if(result.getBoolean("sincronizado") == false) {
					if(idservicios.contains(result.getInt("id"))) {
							
							
						actualizar.setDate(1, result.getDate("fecha"));
						actualizar.setInt(2, result.getInt("id_cita"));
						actualizar.setInt(3, result.getInt("id_tipo_servicio"));
						actualizar.setInt(4, result.getInt("id_paciente"));
						actualizar.setBoolean(5, result.getBoolean("procesado"));
						actualizar.setDate(6, new Date(new java.util.Date().getTime()));
						actualizar.setInt(7, result.getInt("id"));
						
						actualizar.executeUpdate();
						
						  updatesincro.setBoolean(1, true);
						  updatesincro.setInt(2, result.getInt("id"));
						  
						  updatesincro.executeUpdate();
						
						
					}else {
						
						  ingresarservicio.setInt(1, result.getInt("id"));
						  ingresarservicio.setDate(2, result.getDate("fecha"));
						  
						  if(result.getInt("id_cita") > 0) {
						 	ingresarservicio.setInt(3, result.getInt("id_cita"));
						  }else{
							  ingresarservicio.setString(3,null);
						  }
						  ingresarservicio.setInt(4, result.getInt("id_tipo_servicio"));
						  ingresarservicio.setInt(5, result.getInt("id_paciente"));
						  ingresarservicio.setBoolean(6, result.getBoolean("procesado"));
						  ingresarservicio.setDate(7,new Date(new java.util.Date().getTime()));
						  
						  ingresarservicio.executeUpdate();
						  
						  
						  updatesincro.setBoolean(1, true);
						  updatesincro.setInt(2, result.getInt("id"));
						  
						  updatesincro.executeUpdate();
						  
						  
						  
					}
				}
			}
			
			
			
			
			conec.commit();
			conec1.commit();
			System.out.println("servicio sincronizado exitosamente...");
		} catch (SQLException e) {
			
			try {
				conec.rollback();
				conec1.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	private void sincestado() {
		
		Connection conec = conexion.getConec();		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from estadoproceso";
		String tipoperson1 = "INSERT INTO estado(id,estado,condicion) values(?,?,?)";
		String tipoperson3 = "UPDATE estado set estado = ?, condicion = ?, where id = ?";
		String tipoperson4 = "select * from estado";
		String deletipopers = "DELETE FROM estado where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("nombre"));
						tipopers3.setString(2, result.getString("condicion"));
						tipopers3.setInt(3, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("nombre"));
						tipo1.setString(3, result.getString("condicion"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE estadoproceso set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("estado sincronizado exitosamente...");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void sincopcionservicio() {
		Connection conec = conexion.getConec();
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String opciones = "SELECT * from opcionservicio";
		String insertopciones = "insert into opcionservicio(idservicio,idestado,idopcion) values(?,?,?)";
		String delete = "DELETE from opcionservicio where idservicio = ?";
		String updatesincro = "UPDATE opcionservicio set sincronizado = ? where idservicios = ?";
		PreparedStatement listopciones,opcionesapp,insertar,deleteservicios,sincroni;
		
		
		listopciones = conec.prepareStatement(opciones,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		opcionesapp = conect.prepareStatement(opciones,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		insertar = conect.prepareStatement(insertopciones);
		deleteservicios = conect.prepareStatement(delete);
		sincroni = conec.prepareStatement(updatesincro);
		ResultSet result = listopciones.executeQuery();
		ResultSet resultado = opcionesapp.executeQuery();
		ArrayList idservi = new ArrayList();
			
		while(resultado.next()) {
			idservi.add(resultado.getInt("idservicio"));
		}	
		ArrayList idservicios = new ArrayList();
		while(result.next()) {
			idservicios.add(result.getInt("idservicios"));
			
			if(result.getBoolean("sincronizado") == false) {
				if(!idservi.contains(result.getInt("idservicios"))) {
					insertar.setInt(1, result.getInt("idservicios"));
					insertar.setInt(2, result.getInt("idestadoproceso"));
					insertar.setInt(3, result.getInt("idopciones"));
					insertar.executeUpdate();
					
					sincroni.setBoolean(1, true);
					sincroni.setInt(2, result.getInt("idservicios"));
					sincroni.executeUpdate();
					
				}
			}
		}
		
		// verificamos para eliminar 
		
		resultado.beforeFirst();
		
//		while(resultado.next()) {
//			if(!idservicios.contains(resultado.getInt("idservicio"))) {
//				deleteservicios.setInt(1, resultado.getInt("idservicio"));
//				deleteservicios.executeUpdate();
//				
//			}
//		}
		conect.commit();
		conec.commit();
		
		System.out.println("opciones de servicios sincronizada");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void sinctriaje() {
		
		Connection conec = conexion.getConec();
		Connection conec1 = super.getConec();
		
		try {
			conec.setAutoCommit(false);
			conec1.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String triajes = "SELECT * FROM triaje";
		String insert = "insert into triaje(id,talla,temperatura,presion,peso,glicemia,id_servicio,fechaupdate) values(?,?,?,?,?,?,?,?)";
		String upda = "update triaje set talla = ?, temperatura = ?, presion = ?, peso = ?, glicemia = ? , id_servicio = ?, fechaupdate = ? where id = ?";
		String sincro = "update triaje set sincronizado = ? where id = ?";
		
		PreparedStatement listtriaje,insertar,idtriaj,actualizar,updatesincro;
		
		try {
			listtriaje = conec.prepareStatement(triajes,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			insertar = conec1.prepareStatement(insert);
			
			idtriaj = conec1.prepareStatement(triajes,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			actualizar = conec1.prepareStatement(upda);
			
			updatesincro = conec.prepareStatement(sincro);
			
			ResultSet result = listtriaje.executeQuery();
			ResultSet listid = idtriaj.executeQuery();
			
			
			ArrayList idtriajes = new ArrayList();
			
			while(listid.next()) {
				idtriajes.add(listid.getInt("id"));
			}
			while(result.next()) {
				
				if(result.getBoolean("sincronizado") == false) {
					
					if(idtriajes.contains(result.getInt("id"))) {
						actualizar.setString(1, result.getString("talla"));
						actualizar.setString(2, result.getString("temperatura"));
						actualizar.setString(3, result.getString("presion_arterial"));
						actualizar.setString(4, result.getString("peso"));
						actualizar.setString(5, result.getString("glicemia"));
						actualizar.setInt(6, result.getInt("id_servicio"));
						actualizar.setTimestamp(7, new Timestamp(new GregorianCalendar().getTimeInMillis()));
						actualizar.setInt(8, result.getInt("id"));
						
						actualizar.executeUpdate();
						
						updatesincro.setBoolean(1, true);
						updatesincro.setInt(2, result.getInt("id"));
					}else {
						insertar.setInt(1, result.getInt("id"));
						insertar.setString(2, result.getString("talla"));
						insertar.setString(3, result.getString("temperatura"));
						insertar.setString(4, result.getString("presion_arterial"));
						insertar.setString(5, result.getString("peso"));
						insertar.setString(6, result.getString("glicemia"));
						insertar.setInt(7, result.getInt("id_servicio"));
						insertar.setTimestamp(8, new Timestamp(new GregorianCalendar().getTimeInMillis()));
						
						insertar.executeUpdate();
						
						updatesincro.setBoolean(1, true);
						updatesincro.setInt(2, result.getInt("id"));
					}
				}
			}
		
		
			conec.commit();
			conec1.commit();
			System.out.println("triajes sincronizada");
		} catch (SQLException e) {
			try {
				conec.rollback();
				conec1.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void sincpatologias() {

		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from patologia";
		String tipoperson1 = "INSERT INTO patologia(id,nombre) values(?,?)";
		String tipoperson3 = "UPDATE patologia set nombre = ? where id = ?";
		String tipoperson4 = "select * from patologia";
		String deletipopers = "DELETE FROM patologia where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("nombre"));
						tipopers3.setInt(2, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("nombre"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE patologia set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("patologia exitosamente...");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void sincconsulta() {

		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from consulta";
		String tipoperson1 = "INSERT INTO consulta(id,sintoma,diagnostico,id_triaje) values(?,?,?,?)";
		String tipoperson3 = "UPDATE consulta set sintoma = ?, diagnostico = ?, id_triaje = ? where id = ?";
		String tipoperson4 = "select * from consulta";
		String deletipopers = "DELETE FROM consulta where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("sintoma"));
						tipopers3.setString(2, result.getString("diagnostico"));
						tipopers3.setInt(3, result.getInt("id_triaje"));
						tipopers3.setInt(4, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("sintoma"));
						tipo1.setString(3, result.getString("diagnostico"));
						tipo1.setInt(4, result.getInt("id_triaje"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE consulta set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("consulta sincronizada exitosamente..");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	private void sincassocpatologia() {
		Connection conec = conexion.getConec();
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String opciones = "SELECT * from assocpatologia";
		String opciones1 = "SELECT * FROM asocpatologia";
		String insertopciones = "insert into asocpatologia(id_consulta,id_patologia) values(?,?)";
		String delete = "DELETE from asocpatologia where idservicio = ?";
		String updatesincro = "UPDATE assocpatologia set sincronizado = ? where id_consulta = ?";
		PreparedStatement listopciones,opcionesapp,insertar,deleteservicios,sincroni;
		
		
		listopciones = conec.prepareStatement(opciones,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		opcionesapp = conect.prepareStatement(opciones1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		insertar = conect.prepareStatement(insertopciones);
		deleteservicios = conect.prepareStatement(delete);
		sincroni = conec.prepareStatement(updatesincro);
		ResultSet result = listopciones.executeQuery();
		ResultSet resultado = opcionesapp.executeQuery();
		ArrayList idservi = new ArrayList();
			
		while(resultado.next()) {
			idservi.add(resultado.getInt("id_consulta"));
		}	
		ArrayList idservicios = new ArrayList();
		while(result.next()) {
			idservicios.add(result.getInt("id_consulta"));
			
			if(result.getBoolean("sincronizado") == false) {
				if(!idservi.contains(result.getInt("id_consulta"))) {
					insertar.setInt(1, result.getInt("id_consulta"));
					insertar.setInt(2, result.getInt("id_patologia"));
				
					insertar.executeUpdate();
					
					sincroni.setBoolean(1, true);
					sincroni.setInt(2, result.getInt("id_consulta"));
					sincroni.executeUpdate();
					
				}
			}
		}
		
		// verificamos para eliminar 
		
		resultado.beforeFirst();
		
//		while(resultado.next()) {
//			if(!idservicios.contains(resultado.getInt("idservicio"))) {
//				deleteservicios.setInt(1, resultado.getInt("idservicio"));
//				deleteservicios.executeUpdate();
//				
//			}
//		}
		conect.commit();
		conec.commit();
		
		System.out.println("assoc patologia sincronizada exitosamente...");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void sincreposo() {
		

		Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from reposo";
		String tipoperson1 = "INSERT INTO reposo(id,motivo,dias,id_consulta) values(?,?,?,?)";
		String tipoperson3 = "UPDATE reposo set motivo = ?, dias = ?, id_consulta = ? where id = ?";
		String tipoperson4 = "select * from reposo";
		String deletipopers = "DELETE FROM reposo where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("motivo"));
						tipopers3.setString(2, result.getString("dias"));
						tipopers3.setInt(3, result.getInt("id_consulta"));
						tipopers3.setInt(4, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("motivo"));
						tipo1.setString(3, result.getString("dias"));
						tipo1.setInt(4, result.getInt("id_consulta"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE reposo set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("reposo sincronizada exitosamente..");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
	}

	private void sincrecipe() {
		
Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from recipe";
		String tipoperson1 = "INSERT INTO recipe(id,recipe,vencimiento,id_consulta) values(?,?,?,?)";
		String tipoperson3 = "UPDATE recipe set recipe = ?, vencimiento = ?, id_consulta = ? where id = ?";
		String tipoperson4 = "select * from recipe";
		String deletipopers = "DELETE FROM recipe where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("recipe"));
						tipopers3.setDate(2, result.getDate("vencimiento"));
						tipopers3.setInt(3, result.getInt("id_consulta"));
						tipopers3.setInt(4, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("recipe"));
						tipo1.setDate(3, result.getDate("vencimiento"));
						tipo1.setInt(4, result.getInt("id_consulta"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE recipe set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("recipe sincronizada exitosamente..");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

	private void sincindicaciones() {
Connection conec = conexion.getConec();
		
		Connection conect = super.getConec();
		
		try {
		
		conec.setAutoCommit(false);
		conect.setAutoCommit(false);
		
		String tipoperson = "Select * from indicaciones";
		String tipoperson1 = "INSERT INTO indicaciones(id,indicacion,vencimiento,id_recipe) values(?,?,?,?)";
		String tipoperson3 = "UPDATE indicaciones set indicacion = ?,vencimiento = ?, id_recipe = ? where id = ?";
		String tipoperson4 = "select * from indicaciones";
		String deletipopers = "DELETE FROM indicaciones where id = ?";
		PreparedStatement deletipo = null;
		PreparedStatement tipopers4 = null;
		PreparedStatement tipopers3 = null;
		PreparedStatement tipopersona = null;
		PreparedStatement tipo1 = null;
		
		tipopersona = conec.prepareStatement(tipoperson,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet result = tipopersona.executeQuery();
		
		tipo1 = conect.prepareStatement(tipoperson1);
		tipopers3 = conect.prepareStatement(tipoperson3);
		tipopers4 = conect.prepareStatement(tipoperson4,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet resultado = tipopers4.executeQuery();
		ArrayList<Integer> listidd = new ArrayList<Integer>();
		
		while(resultado.next()) {
			listidd.add(resultado.getInt("id"));
		}
		
		while(result.next()) {
			if(result.getBoolean("sincronizado") == false) {
				
					if(listidd.contains(result.getInt("id"))) {
						tipopers3.setString(1, result.getString("indicacion"));
						tipopers3.setDate(2, result.getDate("vencimiento"));
						tipopers3.setInt(3, result.getInt("id_recipe"));
						tipopers3.setInt(4, result.getInt("id"));
						tipopers3.executeUpdate();
					}else {
						
						tipo1.setInt(1, result.getInt("id"));
						tipo1.setString(2, result.getString("indicacion"));
						tipo1.setDate(3, result.getDate("vencimiento"));
						tipo1.setInt(4, result.getInt("id_recipe"));
						tipo1.executeUpdate();
					}
			}
		}
		
		
		
		result.beforeFirst();
		
		
		
		String tipoperson2 = "UPDATE indicaciones set sincronizado = ? where id = ? ";
		
		PreparedStatement tipoper2 = null;
		
		tipoper2 = conec.prepareStatement(tipoperson2);
		
		while(result.next()) {
			tipoper2.setBoolean(1,true);
			tipoper2.setInt(2, result.getInt("id"));
			tipoper2.executeUpdate();
		}
		
		result.beforeFirst();
		
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(result.next()) {
			list.add(result.getInt("id"));
			
		}
		deletipo = conect.prepareStatement(deletipopers);

		
		for(int i = 0; i < listidd.size(); i++) {
			if(!list.contains(listidd.get(i))) {
	
				deletipo.setInt(1, listidd.get(i));
				deletipo.executeUpdate();
			}
		}
		
		conect.commit();
		conec.commit();
		
		System.out.println("indicaciones sincronizada exitosamente..");

		} catch (SQLException e) {
			try {
				conec.rollback();
				conect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
}
