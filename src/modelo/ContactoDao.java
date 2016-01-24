package modelo;

import java.util.List;

/**
 * 
 * @author Codizer
 *
 */
public interface ContactoDao {
	
	public boolean save(Contacto contacto);
	public boolean update(Contacto contacto);
	public boolean delete(int id);
	
	public List<Contacto> showAll();
	public List<Contacto> showByName(String nombre);
}
