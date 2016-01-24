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
	 * @param Contacto obtenidos de una consulta
	 */
	@Override
	public Contacto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Contacto contacto = new Contacto();
		
		contacto.setId(rs.getInt("id"));
		contacto.setNombre(rs.getString("nombre"));
		contacto.setApellidos(rs.getString("apellidos"));
		contacto.setfNacimiento(rs.getTimestamp("fNacimiento"));
		
		return contacto;
	}

}
