package com.codizer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.codizer.pojo.Contacto;
import com.codizer.pojo.ContactoRowMapper;

/**
 * Clase auto-inyectada como dependencia
 * 
 * @author Adrian
 *
 */
@Component("contactDao")
public class ContactoDaoImpl implements ContactoDao {

	// Plantilla que acepta comodines y evita inyecciones
	private NamedParameterJdbcTemplate JdbcTemplate;

	
	/**
	 * Referencia a config para instanciar y configurar
	 * la base de datos
	 * 
	 * @param dataSource Conexión a la BD
	 */
	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.JdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	/**
	 * Almacena un objeto de tipo Contacto en la base de datos
	 * Retorna un boolean para saber si se alamaceno o no un contacto
	 * en la base de datos
	 * 
	 * @param contacto Objeto de un contacto
	 * @return boolean Saber si se realizaron cambios
	 */
	@Override
	public boolean save(Contacto contacto) {

		// MapSqlParameterSource paramMap = new MapSqlParameterSource();
		//
		// paramMap.addValue("nombre", contacto.getNombre());
		// paramMap.addValue("apellidos", contacto.getApellidos());

		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(contacto);

		return JdbcTemplate.update(
				"INSERT INTO Contacto (nombre, apellidos, empresa, telefono, celular, correo, url, fNacimiento, redSocial, calle, num, cp, ciudad, estado, nota) VALUES (:nombre, :apellidos, :empresa, :telefono, :celular, :correo, :url, :fNacimiento, :redSocial, :calle, :num, :cp, :ciudad, :estado, :nota)",
				paramMap) == 1;
	}

	/**
	 * Actualiza un objeto de tipo Contacto en la base de datos
	 * Retorna un boolean para saber si se actualizo o no un contacto
	 * en la base de datos
	 * 
	 * @param contacto Objeto de un contacto
	 * @return boolean Saber si se realizaron cambios
	 */
	@Override
	public boolean update(Contacto contacto) {
		return JdbcTemplate.update("UPDATE Contacto set nombre=:nombre, apellidos=:apellidos, empresa=:empresa, telefono=:telefono, celular=:celular, correo=:correo, url=:url, fNacimiento=:fNacimiento, redSocial=:redSocial, calle=:calle, num=:num, cp=:cp, ciudad=:ciudad, estado=:estado, nota=:nota WHERE id=:id", 
				new BeanPropertySqlParameterSource(contacto)) == 1;
	}

	/**
	 * Elimina un objeto de tipo Contacto en la base de datos
	 * Retorna un boolean para saber si se elimino o no un contacto
	 * en la base de datos
	 * 
	 * @param id de un contacto de tipo Int
	 * @return boolean Saber si se realizaron cambios
	 */
	@Override
	public boolean delete(int id) {
		return JdbcTemplate.update("DELETE FROM Contacto WHERE id=:id", new MapSqlParameterSource("id", id)) == 1;
	}

	/**
	 * Busca todos los Contactos en la base de datos
	 * Retorno una lista de Contactos
	 * 
	 * @return Retorna una lista de contatos
	 */
	@Override
	public List<Contacto> findAll() {

		// RowMapper de forma anónima
		return JdbcTemplate.query("SELECT * FROM Contacto", new RowMapper<Contacto>() {

			@Override
			public Contacto mapRow(ResultSet rs, int rowNum) throws SQLException {

				Contacto contacto = new Contacto();

				contacto.setId(rs.getInt("id"));
				contacto.setNombre(rs.getString("nombre"));
				contacto.setApellidos(rs.getString("apellidos"));
				contacto.setfNacimiento(rs.getTimestamp("fNacimiento"));

				return contacto;
			}

		});
	}

	/**
	 * 
	 * Busca un Contacto en la base de datos
	 * en base a su identificador unico
	 * Retorno una lista de Contactos
	 * 
	 * @param id identificado de un contacto de tipo Int
	 * @return Retorna un Contato
	 */
	@Override
	public Contacto findById(int id) {
		
//		return (Contacto) JdbcTemplate.query("SELECT * FROM Contacto WHERE id=:id",
//		new MapSqlParameterSource("id", id), new ContactoRowMapper());
		
		return JdbcTemplate.queryForObject("SELECT * FROM Contacto WHERE id=:id",
				new MapSqlParameterSource("id", id), new ContactoRowMapper());
	}

	/**
	 * 
	 * Busca Contactos en la base de datos
	 * en base a nombre del contacto
	 * Retorno una lista de Contactos
	 * 
	 * @param nombre de un Contacto de tipo String
	 * @return Retorna una lista de contatos
	 */
	@Override
	public List<Contacto> findByName(String nombre) {
		return JdbcTemplate.query("SELECT * FROM Contacto WHERE nombre like :nombre",
				new MapSqlParameterSource("nombre", "%" + nombre + "%"), new ContactoRowMapper());
	}

}
