package com.codizer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codizer.dao.ContactoDao;
import com.codizer.pojo.Contacto;

/**
 * 
 * @author Adrian
 *
 */
public class VentanaView extends JFrame implements WindowListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1429316808970839914L;
	Container c = getContentPane();
	
	private String id;
	private JLabel lbNavigateBar = new JLabel();
	private JButton btnEditar = new JButton("Editar");
	
	private ImageIcon image = new ImageIcon("../10-Agenda/src/com/codizer/img/img.png");
	private JLabel fotoContacto = new JLabel(image);
	
	private JLabel lbNombreCompleto = new JLabel();
	private JLabel lbeEmpresa = new JLabel();
	
	private JLabel lbCelularInfo = new JLabel("Celular");
	private JLabel lbCelular = new JLabel();
	
	private JLabel lbTelefonoInfo = new JLabel("Telefono");
	private JLabel lbTelefono = new JLabel();
	
	private JLabel lbCorreoInfo = new JLabel("Correo");
	private JLabel lbCorreo = new JLabel();
	
	private JLabel lbUrlInfo = new JLabel("URL");
	private JLabel lbUrl = new JLabel();
	
	private JLabel lbRedSocialInfo = new JLabel("Red social");
	private JLabel lbRedSocial = new JLabel();
	
	private JLabel lbFNacimientoInfo = new JLabel("Cumpleaños");
	private JLabel lbFNacimiento = new JLabel();
	
	private JLabel lbDireccionInfo = new JLabel("Dirección");
	private JLabel lbCalle = new JLabel();
	private JLabel lbNum = new JLabel();
	private JLabel lbCP = new JLabel();
	private JLabel lbCiudad = new JLabel();
	private JLabel lbEstado = new JLabel();
	private JLabel lbNotaInfo = new JLabel("Nota");
	private JLabel lbNota = new JLabel();
	
	private JLabel[] lbEtiquetas = {fotoContacto, lbNombreCompleto, lbeEmpresa, lbCelularInfo, lbCelular, lbCelular, lbTelefonoInfo, lbTelefono,
			lbTelefono, lbCorreoInfo, lbCorreo, lbUrlInfo, lbUrl, lbRedSocialInfo, lbRedSocial, lbFNacimientoInfo, lbFNacimiento, lbDireccionInfo,
			lbCalle, lbNum, lbCP, lbCiudad, lbEstado, lbNota, lbNotaInfo
	};
	
	private Color blancoLi = new Color(255, 255, 255);
	private Color blanco = new Color(250, 250, 250);
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
	
	/**
	 * Constrictor base
	 */
	public VentanaView(){}

	/**
	 * Constructor que cargar los componentes de 
	 * la UI, recibe un paramatro que pertenece
	 * al id de un contacto especifico.
	 * 
	 * @param id Clave de un contacto en Cadena
	 */
	public VentanaView(String id) {
		
		this.id = id;
		
		super.setTitle("Información  Contacto");
		super.setSize(320, 550);
		
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		
		cargarControles();
		super.addWindowListener(this);
	}

	/**
	 * Cargar la información de un contacto especifico
	 * en base a una busqueda por ID, para asi
	 * asignar la información del contacto
	 * a los componentes de la UI.
	 */
	private void cargarData() {
		
		Contacto leerContacto = contactoDao.findById(Integer.parseInt(id));
		
		lbNombreCompleto.setText(leerContacto.getNombre() + " " + leerContacto.getApellidos());
		lbeEmpresa.setText(leerContacto.getEmpresa());
		lbCelular.setText(leerContacto.getCelular());
		lbTelefono.setText(leerContacto.getTelefono());
		lbCorreo.setText(leerContacto.getCorreo());
		lbUrl.setText(leerContacto.getUrl());
		lbRedSocial.setText(leerContacto.getRedSocial());
		lbFNacimiento.setText("" + leerContacto.getfNacimiento());
		lbCalle.setText(leerContacto.getCalle()); 
		lbNum.setText(Integer.toString(leerContacto.getNum()));
		lbCP.setText(Integer.toString(leerContacto.getCp()));
		lbCiudad.setText(leerContacto.getCiudad());
		lbEstado.setText(leerContacto.getEstado());
		lbNota.setText(leerContacto.getNota());
	}
	
	/**
	 * Cargar componentes de la UI al JFrame
	 * o a los paneles de la UI
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
		
		fotoContacto.setBounds(20, 10, 60, 60);
		
		lbNombreCompleto.setFont(medFont);
		lbNombreCompleto.setBounds(100, 15, 210, 30);
		lbeEmpresa.setBounds(100, 35, 210, 30);
		
		lbCelularInfo.setForeground(azul);
		lbCelularInfo.setFont(miniFont);
		lbCelularInfo.setBounds(25, 95, 300, 10);
		lbCelular.setBounds(25, 105, 300, 30);
		
		lbTelefonoInfo.setForeground(azul);
		lbTelefonoInfo.setFont(miniFont);
		lbTelefonoInfo.setBounds(25, 140, 300, 30);
		lbTelefono.setBorder(borderButtom);
		lbTelefono.setBounds(25, 160, 300, 30);
		
		lbCorreoInfo.setForeground(azul);
		lbCorreoInfo.setFont(miniFont);
		lbCorreoInfo.setBounds(25, 210, 300, 30);
		lbCorreo.setBounds(25, 230, 300, 30);
		
		lbUrlInfo.setForeground(azul);
		lbUrlInfo.setFont(miniFont);
		lbUrlInfo.setBounds(25, 260, 300, 30);
		lbUrl.setBorder(borderButtom);
		lbUrl.setBounds(25, 280, 300, 30);
		
		lbRedSocialInfo.setForeground(azul);
		lbRedSocialInfo.setFont(miniFont);
		lbRedSocialInfo.setBounds(25, 310, 300, 30);
		lbRedSocial.setBorder(borderButtom);
		lbRedSocial.setBounds(25, 330, 300, 30);
		
		lbFNacimientoInfo.setForeground(azul);
		lbFNacimientoInfo.setFont(miniFont);
		lbFNacimientoInfo.setBounds(25, 390, 300, 30);
		lbFNacimiento.setBorder(borderButtom);
		lbFNacimiento.setBounds(25, 410, 300, 30);
		
		lbDireccionInfo.setForeground(azul);
		lbDireccionInfo.setFont(miniFont);
		lbDireccionInfo.setBounds(25, 470, 300, 30);
	
		lbCiudad.setBounds(25, 490, 145, 30);
		lbEstado.setBounds(170, 490, 145, 30);
		lbCalle.setBounds(25, 515, 145, 30);
		lbNum.setBounds(170, 515, 145, 30);
		lbCP.setBounds(25, 540, 300, 30);
		lbCP.setBorder(borderButtom);
		
		lbNotaInfo.setForeground(azul);
		lbNotaInfo.setFont(miniFont);
		lbNotaInfo.setBounds(25, 590, 300, 30);
		lbNota.setBounds(25, 610, 280, 60);
		
		JPanel panel = new JPanel();
		panel.setBackground(blancoLi);
		// panel.setLayout(null);
		
		panel.setLayout(null);
		panel.setBounds(0, 0, 320, 730);
		
		for (JLabel jLabel : lbEtiquetas) {
			panel.add(jLabel);
		}
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(0, 52, 320, 476);
		scroll.setViewportView(panel);
		scroll.getViewport().setView(panel);
		scroll.setBorder(borderButtom);
		
		panel.setPreferredSize(new Dimension(300,730));
		
		c.add(scroll);
		c.add(btnEditar);
		c.add(lbNavigateBar);
		
		btnEditar.addActionListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	/**
	 * Evento que se encarga de actualiza la
	 * información al ser reactivada la ventana
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		cargarData();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	/**
	 * Evento que se encarga de iniciar el JFrame
	 * para la creación de nuevos contactos
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnEditar) {
			VentanaNewEdit ventanaNew = new VentanaNewEdit(id);
			ventanaNew.setVisible(true);
			dispose();
		}
		
	}
}
