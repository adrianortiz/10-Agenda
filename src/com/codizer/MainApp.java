package com.codizer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import com.codizer.dao.ContactoDao;
import com.codizer.pojo.Contacto;

/**
 * 
 * @author Adrian
 *
 */
public class MainApp {

	/**
	 * Aplicación de gestión de contactos
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Asignamos configuración
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
		
		// Inyectar Bean por identificador
		ContactoDao contactoDao = (ContactoDao) applicationContext.getBean("contactDao");
		
		Timestamp ts = new Timestamp(new Date().getTime());
		
		Contacto contacto = new Contacto();
		contacto.setNombre("Remedios"); 
		contacto.setApellidos("Alvazir Herrera");
		contacto.setfNacimiento(ts);
		
		try {
			
//			contactoDao.save(contacto); 
			
			
//			List<Contacto> contactos = contactoDao.findAll();
//			for (Contacto contacto2 : contactos) {
//				System.out.println(contacto2);
//			}
			
			
			Contacto contacto2 = contactoDao.findById(5);
			System.out.println("-> " + contacto2);
			contacto2.setNombre("Roberto");
			contacto2.setApellidos("Moran Alvarado");
			contactoDao.update(contacto2);
			contacto2 = contactoDao.findById(3);
			System.out.println("--> " + contacto2);
			
			
//			System.out.println(contactoDao.findById(2));
			
//			System.out.println(contactoDao.findByName("a").toString());
			
//			if (contactoDao.delete(3)) {
//				System.out.println("Contacto eliminado");
//				
//			} else {
//				System.out.println("Error");
//			}
			
		
		} catch (CannotGetJdbcConnectionException e) {
			e.printStackTrace();
			
		} catch (DataAccessException e) {
			e.printStackTrace();
			
		}
		
		((ClassPathXmlApplicationContext)applicationContext).close();
		
	}
	
}
 