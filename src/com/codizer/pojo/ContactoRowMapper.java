package com.codizer.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Adrian
 *
 */
public class ContactoRowMapper implements RowMapper<Contacto>{

	/**
	 * Mapea las tuplas de una consulta y retorna objetos de tipo Contacto
	 * 
	 * @param Contacto obtenido de una consulta
	 */
	@Override
	public Contacto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Contacto contacto = new Contacto();
		
		contacto.setId(rs.getInt("id"));
		contacto.setNombre(rs.getString("nombre"));
		contacto.setApellidos(rs.getString("apellidos"));
		contacto.setEmpresa(rs.getString("empresa"));
		contacto.setTelefono(rs.getString("telefono"));
		contacto.setCelular(rs.getString("celular"));
		contacto.setCorreo(rs.getString("correo"));
		contacto.setUrl(rs.getString("url"));
		contacto.setfNacimiento(rs.getTimestamp("fNacimiento"));
		contacto.setRedSocial(rs.getString("redSocial"));
		contacto.setCalle(rs.getString("calle"));
		contacto.setNum(rs.getInt("num"));
		contacto.setCp(rs.getInt("cp"));
		contacto.setCiudad(rs.getString("ciudad"));
		contacto.setEstado(rs.getString("estado"));
		contacto.setNota(rs.getString("nota"));
		
		return contacto;
	}

}
