package com.codizer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codizer.dao.ContactoDao;
import com.codizer.pojo.Contacto;

public class VentanaView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1429316808970839914L;
	Container c = getContentPane();
	
	private String id;
	private JLabel lbNavigateBar = new JLabel();
	private JButton btnEditar = new JButton("Editar");
	
	ImageIcon image = new ImageIcon("/Users/Codizer/Dropbox/dev/Eclipse/10-Agenda/src/com/codizer/img/img.png");
	private JLabel fotoContacto = new JLabel(image);
	
	private JLabel nombreCompleto = new JLabel();
	private JLabel empresa = new JLabel();
	
	private JLabel lbCelular = new JLabel("Celular");
	private JLabel celular = new JLabel();
	
	private JLabel lbTelefono = new JLabel("Telefono");
	private JLabel telefono = new JLabel();
	
	private JLabel correo = new JLabel();
	private JLabel url = new JLabel();
	private JLabel fNacimiento = new JLabel();
	private JLabel redSocial = new JLabel();
	private JLabel calle = new JLabel();
	private JLabel num = new JLabel();
	private JLabel cp = new JLabel();
	private JLabel ciudad = new JLabel();
	private JLabel estado = new JLabel();
	
	private Color blancoLi = new Color(255, 255, 255);
	private Color blanco = new Color(250, 250, 250);
	private Color negro = new Color(000, 000, 000);
	private Color gris = new Color(223, 223, 223);
	private Color azul = new Color(73, 144, 222);
	
	private Font miniFont = new Font("Helvetica Neue", Font.PLAIN, 12);
	private Font medFont = new Font("Helvetica Neue", Font.PLAIN, 18);
	
	private Border line = new LineBorder(blanco);
	private Border margin = new EmptyBorder(1, 1, 1, 1);
	private Border compound = new CompoundBorder(line, margin);
	private Border borderButtom = BorderFactory.createMatteBorder(0,0,1,0,gris);
	
	// Asignamos configuración
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
	// Inyectar Bean por identificador
	private ContactoDao contactoDao = (ContactoDao) applicationContext.getBean("contactDao");
	
	
	public VentanaView(){}

	public VentanaView( String id ) {
		
		this.id = id;
		
		super.setTitle("Información  Contacto");
		super.setSize(320, 550);
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		
		cargarData();
		cargarControles();
	}

	/**
	 * 
	 */
	private void cargarData() {
		Contacto leerContacto = contactoDao.findById(Integer.parseInt(id));
		
		nombreCompleto.setText(leerContacto.getNombre() + " " + leerContacto.getApellidos());
		empresa.setText(leerContacto.getEmpresa());
		celular.setText(leerContacto.getCelular());
		telefono.setText(leerContacto.getTelefono());

	}
	
	/**
	 * 
	 */
	private void cargarControles() {
		c.setLayout(null);
		c.setBackground(blancoLi);
		
		lbNavigateBar.setHorizontalAlignment(SwingConstants.CENTER);
		lbNavigateBar.setOpaque(true);
		lbNavigateBar.setBackground(blanco);
		lbNavigateBar.setBounds(0, 0, 320, 52);
		
		btnEditar.setBorder(compound);
		btnEditar.setForeground(azul);
		btnEditar.setBackground(blanco);
		btnEditar.setFont(medFont);
		btnEditar.setBounds(230, 10, 100, 30);
		
		fotoContacto.setBounds(20, 60, 60, 60);
		
		nombreCompleto.setFont(medFont);
		nombreCompleto.setBounds(100, 65, 210, 30);
		empresa.setBounds(100, 85, 210, 30);
		
		lbCelular.setForeground(azul);
		lbCelular.setFont(miniFont);
		lbCelular.setBounds(25, 145, 300, 10);
		celular.setBounds(25, 155, 300, 30);
		
		lbTelefono.setForeground(azul);
		lbTelefono.setFont(miniFont);
		lbTelefono.setBounds(25, 185, 300, 30);
		telefono.setBorder(borderButtom);
		telefono.setBounds(25, 210, 300, 30);
		
		c.add(celular);
		c.add(empresa);
		c.add(nombreCompleto);
		c.add(fotoContacto);
		c.add(btnEditar);
		c.add(lbNavigateBar);
		c.add(lbCelular);
		c.add(lbTelefono);
		c.add(telefono);
		
		
		
		
	}
}
