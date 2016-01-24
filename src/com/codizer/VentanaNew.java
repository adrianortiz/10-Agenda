package com.codizer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class VentanaNew extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1429316808970839914L;

	ImageIcon image = new ImageIcon("/Users/Codizer/Dropbox/dev/Eclipse/10-Agenda/src/com/codizer/img/img.png");
	private JLabel fotoContacto = new JLabel(image);
	
	private JLabel lbNavigateBar = new JLabel("Nuevo");
	private JButton btnOk = new JButton("OK");
	
	private JTextField nombre = new JTextField();
	private JTextField apellidos = new JTextField();
	private JTextField empresa = new JTextField();
	private JTextField celular = new JTextField();
	private JTextField telefono = new JTextField();
	private JTextField correo = new JTextField();
	private JTextField url = new JTextField();
	
	private Color blancoLi = new Color(255, 255, 255);
	private Color blanco = new Color(250, 250, 250);
	private Color negro = new Color(000, 000, 000);
	private Color gris = new Color(223, 223, 223);
	private Color azul = new Color(73, 144, 222);
	
	private Border line = new LineBorder(blanco);
	private Border margin = new EmptyBorder(1, 1, 1, 1);
	private Border compound = new CompoundBorder(line, margin);
	
	private Border borderButtom = BorderFactory.createMatteBorder(0,0,1,0,gris);
	
	Container c = getContentPane();
	
	// Asignamos configuración
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
	// Inyectar Bean por identificador
	private ContactoDao contactoDao = (ContactoDao) applicationContext.getBean("contactDao");
	
	public VentanaNew() {
		super.setTitle("Nuevo contacto");
		super.setSize(320, 550);
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		
		cargarControles();
	}

	private void cargarControles() {
		c.setLayout(null);
		c.setBackground(blancoLi);
		
		lbNavigateBar.setHorizontalAlignment(SwingConstants.CENTER);
		lbNavigateBar.setOpaque(true);
		lbNavigateBar.setForeground(negro);
		lbNavigateBar.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		lbNavigateBar.setBackground(blanco);
		lbNavigateBar.setBounds(0, 0, 320, 52);
		
		btnOk.setBorder(compound);
		btnOk.setForeground(azul);
		btnOk.setBackground(blanco);
		btnOk.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnOk.setBounds(240, 13, 100, 30);
		
		fotoContacto.setBounds(20, 70, 60, 60);
		nombre.setBorder(borderButtom);
		nombre.setBounds(100, 65, 210, 35);
		apellidos.setBorder(borderButtom);
		apellidos.setBounds(100, 100, 210, 35);
		empresa.setBorder(borderButtom);
		empresa.setBounds(100, 135, 210, 35);
		
		celular.setBorder(borderButtom);
		celular.setBounds(20, 200, 290, 35);
		telefono.setBorder(borderButtom);
		telefono.setBounds(20, 235, 290, 35);
		
		correo.setBorder(borderButtom);
		correo.setBounds(20, 300, 290, 35);
		url.setBorder(borderButtom);
		url.setBounds(20, 335, 290, 35);
		
		
		c.add(btnOk);
		c.add(lbNavigateBar);
		c.add(fotoContacto);
		c.add(nombre);
		c.add(apellidos);
		c.add(empresa);
		c.add(celular);
		c.add(telefono);
		c.add(correo);
		c.add(url);
		
		btnOk.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnOk) {
			
			Contacto contacto = new Contacto();
			
			contacto.setNombre(nombre.getText());
			contacto.setApellidos(apellidos.getText());
			contacto.setEmpresa(empresa.getText());
			contacto.setCelular(celular.getText());
			contacto.setTelefono(telefono.getText());
			contacto.setCorreo(correo.getText());
			contacto.setUrl(url.getText());
			
			if (contactoDao.save(contacto) ) {
				System.out.println("Se guardo correctamente");
			}
		}
		
	}
}
