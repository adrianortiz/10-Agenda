package com.codizer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

/**
 * 
 * @author Adrian
 *
 */
public class VentanaNewEdit extends JFrame implements ActionListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429316808970839914L;

	private String id;
	private ImageIcon image = new ImageIcon("../10-Agenda/src/com/codizer/img/img.png");
	private JLabel fotoContacto = new JLabel(image);

	private JLabel lbNavigateBar = new JLabel("Nuevo");
	private JButton btnOk = new JButton("OK");
	private JButton btnUpdate = new JButton("Update");
	private JButton btnDelete = new JButton("Delete");

	private JTextField txtNombre = new JTextField();
	private JTextField txtApellidos = new JTextField();
	private JTextField txtEmpresa = new JTextField();
	private JTextField txtCelular = new JTextField();
	private JTextField txtTelefono = new JTextField();
	private JTextField txtCorreo = new JTextField();
	private JTextField txtUrl = new JTextField();
	private JTextField txtRedSocial = new JTextField();
	private JTextField txtCumple = new JTextField();
	
	private JTextField txtCalle = new JTextField();
	private JTextField txtNum = new JTextField();
	private JTextField txtCP = new JTextField();
	private JTextField txtCiudad = new JTextField();
	private JTextField txtEstado = new JTextField();
	private JTextField txtNota = new JTextField();

	private JTextField txtFields[] = { txtNombre, txtApellidos, txtEmpresa, txtCelular, txtTelefono, txtCorreo, txtUrl,
			txtRedSocial, txtCumple, txtCalle, txtNum, txtCP, txtCiudad, txtEstado, txtNota };

	private JLabel lbNombre = new JLabel("Nombre");
	private JLabel lbApellidos = new JLabel("Apellidos");
	private JLabel lbEmpresa = new JLabel("Empresa");
	private JLabel lbCelular = new JLabel("Celular");
	private JLabel lbTelefono = new JLabel("Telefono");
	private JLabel lbCorreo = new JLabel("Correo");
	private JLabel lbUrl = new JLabel("URL");
	private JLabel lbRedSocial = new JLabel("Red social");
	private JLabel lbCumple = new JLabel("Cumpleaños. Ejem. yyyy-MM-dd");
	
	private JLabel lbCalle = new JLabel("Calle");
	private JLabel lbNum = new JLabel("Número");
	private JLabel lbCP = new JLabel("C.P.");
	private JLabel lbCiudad = new JLabel("Ciudad");
	private JLabel lbEstado = new JLabel("Estado");
	private JLabel lbNota = new JLabel("Nota");
	
	private JLabel lbLabels[] = { lbNombre, lbApellidos, lbEmpresa, lbCelular, lbTelefono, lbCorreo, lbUrl, lbRedSocial,
			lbCumple, lbCalle, lbNum, lbCP, lbCiudad, lbEstado, lbNota };

	private Color blancoLi = new Color(255, 255, 255);
	private Color blanco = new Color(250, 250, 250);
	private Color grisHard = new Color(150, 150, 150);
	private Color gris = new Color(223, 223, 223);
	private Color azul = new Color(73, 144, 222);
	private Color rojo = new Color(255, 0, 0);

	private Border line = new LineBorder(blanco);
	private Border margin = new EmptyBorder(1, 1, 1, 1);
	private Border compound = new CompoundBorder(line, margin);

	private Border borderButtom = BorderFactory.createMatteBorder(0, 0, 1, 0, gris);

	Container c = getContentPane();

	// Asignar configuración
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");
	// Inyectar Bean por identificador
	private ContactoDao contactoDao = (ContactoDao) applicationContext.getBean("contactDao");

	/**
	 * Constructor vacío
	 */
	public VentanaNewEdit(){}
	
	/**
	 * Constructor con párametro id para obtener la llave del contacto
	 * Si el id está vacío se entiende que se quiere crear un nuevo
	 * contacto, si tiene información se entiende que se quiere
	 * actualizar la información del contacto.
	 * 
	 * @param id de tipo cadena, almacena el id del contacto
	 */
	public VentanaNewEdit(String id) {
		
		this.id = id;
		super.setSize(320, 550);

		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);

		if (!(id.equals(""))) {
			cargarData();
			btnOk.setVisible(false);
			super.setTitle("Editar");
			lbNavigateBar.setText("");
			
			/**
			 *  Soluciona el error de mostrar labels al actualizar
			 *  NOTA: Automatizar constructor
			 */
			for (int i = 0; i < txtFields.length; i++) {
				if (!(txtFields[i].getText().isEmpty())) {
					lbLabels[i].setVisible(false);
				}
			}
			
		} else {
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);
			super.setTitle("Nuevo");
		}
			
		cargarControles();
	}

	/**
	 * Canfigura y carga los controles en la uI
	 * Así mismo se implementas eventos
	 */
	private void cargarControles() {
		
		c.setLayout(null);
		c.setBackground(blancoLi);

		lbNavigateBar.setHorizontalAlignment(SwingConstants.CENTER);
		lbNavigateBar.setOpaque(true);
		lbNavigateBar.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		lbNavigateBar.setBackground(blanco);
		lbNavigateBar.setBounds(0, 0, 320, 52);

		btnOk.setBorder(compound);
		btnOk.setForeground(azul);
		btnOk.setBackground(blanco);
		btnOk.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnOk.setBounds(240, 13, 100, 30);
		
		btnUpdate.setBorder(compound);
		btnUpdate.setForeground(azul);
		btnUpdate.setBackground(blanco);
		btnUpdate.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnUpdate.setBounds(220, 10, 100, 30);
		
		btnDelete.setBorder(compound);
		btnDelete.setForeground(rojo);
		btnDelete.setBackground(blanco);
		btnDelete.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		btnDelete.setBounds(0, 10, 80, 30);

		fotoContacto.setBounds(20, 20, 60, 60);

		txtNombre.setBounds(100, 15, 210, 35);
		lbNombre.setBounds(100, 15, 210, 35);

		txtApellidos.setBounds(100, 50, 210, 35);
		lbApellidos.setBounds(100, 50, 210, 35);

		txtEmpresa.setBounds(100, 85, 210, 35);
		lbEmpresa.setBounds(100, 85, 210, 35);

		txtCelular.setBounds(20, 150, 290, 35);
		lbCelular.setBounds(20, 150, 290, 35);

		txtTelefono.setBounds(20, 185, 290, 35);
		lbTelefono.setBounds(20, 185, 290, 35);

		txtCorreo.setBounds(20, 250, 290, 35);
		lbCorreo.setBounds(20, 250, 290, 35);

		txtUrl.setBounds(20, 285, 290, 35);
		lbUrl.setBounds(20, 285, 290, 35);
		
		txtRedSocial.setBounds(20, 350, 290, 35);
		lbRedSocial.setBounds(20, 350, 290, 35);
		
		txtCumple.setBounds(20, 410, 290, 35);
		lbCumple.setBounds(20, 410, 290, 35);
		
		txtCalle.setBounds(20, 470, 290, 35);
		lbCalle.setBounds(20, 470, 290, 35);
		
		txtNum.setBounds(20, 505, 295, 35);
		lbNum.setBounds(20, 505, 295, 35);
		
		txtCP.setBounds(20, 540, 295, 35);
		lbCP.setBounds(20, 540, 295, 35);
		
		txtCiudad.setBounds(20, 575, 295, 35);
		lbCiudad.setBounds(20, 575, 295, 35);
		
		txtEstado.setBounds(20, 610, 295, 35);
		lbEstado.setBounds(20, 610, 295, 35);
		
		txtNota.setBounds(20, 670, 295, 35);
		lbNota.setBounds(20, 670, 295, 35);
		

		JPanel panel = new JPanel();
		panel.setBackground(blancoLi);

		panel.setLayout(null);
		panel.setBounds(0, 0, 320, 730);

		panel.add(fotoContacto);

		for (int i = 0; i < txtFields.length; i++) {
			txtFields[i].setBorder(borderButtom);
			lbLabels[i].setForeground(grisHard);
			panel.add(lbLabels[i]);
			panel.add(txtFields[i]);
			txtFields[i].addFocusListener(this);
		}

		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(0, 52, 320, 476);
		scroll.setViewportView(panel);
		scroll.getViewport().setView(panel);
		scroll.setBorder(borderButtom);

		panel.setPreferredSize(new Dimension(300, 730));

		c.add(scroll);

		c.add(btnOk);
		c.add(btnUpdate);
		c.add(btnDelete);
		c.add(lbNavigateBar);

		btnOk.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
	}
	
	/**
	 * Carga la información del contacto a editar en la UI
	 * se realiza una busqueda en base al id del mismo.
	 */
	private void cargarData() {
		
		Contacto leerContacto = contactoDao.findById(Integer.parseInt(id));
		
		txtNombre.setText(leerContacto.getNombre());
		txtApellidos.setText(leerContacto.getApellidos());
		txtEmpresa.setText(leerContacto.getEmpresa());
		txtCelular.setText(leerContacto.getCelular());
		txtTelefono.setText(leerContacto.getTelefono());
		txtCorreo.setText(leerContacto.getCorreo());
		txtUrl.setText(leerContacto.getUrl());
		txtRedSocial.setText(leerContacto.getRedSocial());
		txtCumple.setText("" + leerContacto.getfNacimiento());
		txtCalle.setText(leerContacto.getCalle()); 
		txtNum.setText(Integer.toString(leerContacto.getNum()));
		txtCP.setText(Integer.toString(leerContacto.getCp()));
		txtCiudad.setText(leerContacto.getCiudad());
		txtEstado.setText(leerContacto.getEstado());
		txtNota.setText(leerContacto.getNota());
	}
	
	/**
	 * Se modifican los atributos del POJO de un contacto especifico
	 * para guardarlo o actualizarlo.
	 *  
	 * @param contact
	 */
	private void setDataContacto(Contacto contact) {
		
		contact.setNombre(txtNombre.getText());
		contact.setApellidos(txtApellidos.getText());
		contact.setEmpresa(txtEmpresa.getText());
		contact.setCelular(txtCelular.getText());
		contact.setTelefono(txtTelefono.getText());
		contact.setCorreo(txtCorreo.getText());
		contact.setUrl(txtUrl.getText());
		contact.setRedSocial(txtRedSocial.getText());
		
		contact.setCalle(txtCalle.getText());
		
		try {
			DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		    Date date = formatoFecha.parse(txtCumple.getText());
			Timestamp timeStampDate = new Timestamp(date.getTime());
			contact.setfNacimiento(timeStampDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Eventos Para realizar altas, actulizaciones y bajas,
	 * de un contacto especifico.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		/**
		 * Crear contacto
		 */
		if (e.getSource() == btnOk) {

			if (isFechaValida(txtCumple.getText())) {
				Contacto contact = new Contacto();
				setDataContacto(contact);
				
				if (contactoDao.save(contact)) {
					super.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Parece que algo salio mal al guardar, intentelo más tarde.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Por favor ingresa una fecha Valida");
			}
			
		}
		
		/**
		 * Actualizar contacto
		 */
		if (e.getSource() == btnUpdate) {
			
			if (isFechaValida(txtCumple.getText())) {
				Contacto contact = contactoDao.findById(Integer.parseInt(id));
				setDataContacto(contact);
				
				if (contactoDao.update(contact)) {
					super.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Parece que algo salio mal al actulizar, intentelo más tarde.");
				}
			
			} else {
				JOptionPane.showMessageDialog(null, "Por favor ingresa una fecha Valida");
			}
		}
		
		/**
		 * Eliminar contacto
		 */
		if (e.getSource() == btnDelete) {
			
			int result = JOptionPane.showConfirmDialog(null, "¿Esta seguro?");
			
			if (result == 0) {
				if (contactoDao.delete(Integer.parseInt(id))) {
					super.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Parece que algo salio mal, intentelo más tarde.");
				}
			}
			
		}
	}
	
	/**
	 * Valida la fecha que sea real en formato yyyy-MM-dd
	 * De esta forma se permite el inicio del guardado y actualizado
	 * 
	 * @param fecha
	 * @return true o false
	 */
	public static boolean isFechaValida(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
	

	/**
	 * Se revisa si un JTextField tiene información,
	 * para emular un placeholder en el formulario
	 * de altas y actualizaciones.
	 */
	@Override
	public void focusGained(FocusEvent e) {

		for (int i = 0; i < txtFields.length; i++) {
			if (e.getSource() == txtFields[i] && txtFields[i].getText().isEmpty()) {
				lbLabels[i].setVisible(false);
			}
		}
	}

	
	/**
	 * Se revisa si un JTextField tiene información,
	 * para emular un placeholder en el formulario
	 * de altas y actualizaciones.
	 */
	@Override
	public void focusLost(FocusEvent e) {

		for (int i = 0; i < txtFields.length; i++) {
			if (e.getSource() == txtFields[i] && txtFields[i].getText().isEmpty()) {
				lbLabels[i].setVisible(true);
			}
		}
	}
}
