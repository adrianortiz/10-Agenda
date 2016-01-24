package com.codizer.dao;

import java.util.List;

import com.codizer.pojo.Contacto;

/**
 * 
 * @author Adrian
 *
 */
public interface ContactoDao {
	
	public boolean save(Contacto contacto);
	public boolean update(Contacto contacto);
	public boolean delete(int id);
	
	public List<Contacto> findAll();
	public List<Contacto> findByName(String nombre);
	public Contacto findById(int id);
}
