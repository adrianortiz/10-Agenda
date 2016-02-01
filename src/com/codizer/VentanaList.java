package com.codizer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codizer.dao.ContactoDao;
import com.codizer.pojo.Contacto;

/**
 * 
 * @author Adrian
 *
 */
public class VentanaList extends JFrame implements ActionListener, MouseListener, WindowListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6773111825611322554L;
	
	private JLabel lbTodos = new JLabel("Todos");
	private JButton btnNuevo = new JButton("+");
	private JTextField txtBuscar = new JTextField();

	private JTable table = new JTable();
	private JScrollPane jScrollPane = new JScrollPane();
	
	private Color blanco = new Color(250, 250, 250);
	private Color negro = new Color(000, 000, 000);
	private Color gris = new Color(223, 223, 223);
	private Color azul = new Color(73, 144, 222);
	
	private Border line = new LineBorder(blanco);
	private Border margin = new EmptyBorder(1, 1, 1, 1);
	private Border compound = new CompoundBorder(line, margin);
	
	// Asignamos configuración
	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring_config.xml");

	// Inyectar Bean por identificador
	private ContactoDao contactoDao = (ContactoDao) applicationContext.getBean("contactDao");
	
	Container c = getContentPane();
	
	/**
	 * Constructo base
	 * realiza la llamada y carga de los componenetes de la UI
	 * y la información a consumir de la BD
	 */
	public VentanaList() {

		super.setTitle("10-Contactos");
		super.setSize(320, 550);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		
		super.addWindowListener(this);
		
		mostrarBuscarDatos("");
		cargarControles();
	}
	

	/**
	 * Cargar los controles de la UI
	 * También asigna eventos a algunos componentes
	 */
	private void cargarControles() {
		c.setLayout(null);
		c.setBackground(gris);
		
		lbTodos.setHorizontalAlignment(SwingConstants.CENTER);
		lbTodos.setOpaque(true);
		lbTodos.setForeground(negro);
		lbTodos.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
		lbTodos.setBackground(blanco);
		lbTodos.setBounds(0, 0, 320, 52);
		
		btnNuevo.setBorder(compound);
		btnNuevo.setForeground(azul);
		btnNuevo.setBackground(blanco);
		btnNuevo.setFont(new Font("Helvetica Neue", Font.PLAIN, 30));
		
		btnNuevo.setBounds(280, 10, 30, 30);
		txtBuscar.setBounds(10, 58, 300, 35);
		jScrollPane.setBounds(0, 100, 320, 430);
		
		jScrollPane.setViewportView(table);
		
		c.add(txtBuscar);
		c.add(btnNuevo);
		c.add(lbTodos);
		c.add(jScrollPane);
		
		btnNuevo.addActionListener(this);
		
		table.addMouseListener(this);
		txtBuscar.addKeyListener(this);
	}
	
	
	/**
	 * Muestra los contactos en un JTable,
	 * si el parametro está vacío, de lo contrario
	 * busca contactos en base al nombre
	 * 
	 * @param nombre Cadena de texto
	 */
	public void mostrarBuscarDatos(String nombre) {
		
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("");
        modelo.addColumn("");
        
        table.setModel(modelo);
        
        String []datos = new String[2];
        
        List<Contacto> contactos;
        
        if(nombre.equals("")) {
        	contactos = contactoDao.findAll();
        } else {
        	contactos = contactoDao.findByName(nombre);
        }
       
        for (Contacto contacto : contactos) {
        	datos[0] = String.format("   %s %s", contacto.getNombre(), contacto.getApellidos());
        	datos[1] = Integer.toString(contacto.getId());
        	modelo.addRow(datos);
		}
        
        table.setRowHeight(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
    }


	/**
	 * Evento que inicia el JFrame de un nuevo
	 * contacto.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnNuevo) {
			VentanaNewEdit ventanaNew = new VentanaNewEdit("");
			ventanaNew.setVisible(true);
			// dispose();
		}
	}


	/**
	 * Evento que identifica en cuál fila se realizo
	 * para extraer información y mostrar el JFrame
	 * de consulta de contactos
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int filaSeleccionada = table.rowAtPoint(e.getPoint());
		if(filaSeleccionada != -1) {
			VentanaView ventanaView = new VentanaView((String) table.getValueAt(filaSeleccionada, 1));
			ventanaView.setVisible(true);
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {}


	@Override
	public void mouseReleased(MouseEvent e) {}


	@Override
	public void mouseEntered(MouseEvent e) {}


	@Override
	public void mouseExited(MouseEvent e) {}


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
	 * Evento que se lanza al tener la ventana desactivada
	 * a activada, actualiza los contactos de la JTable
	 * a la base de datos
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		mostrarBuscarDatos("");
		txtBuscar.setText("");
	}


	@Override
	public void windowDeactivated(WindowEvent e) {}


	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void keyPressed(KeyEvent e) {}


	/**
	 * Evento que se lanza al escribir en un JTextField
	 * para realizar busquedas en base al nombre de
	 * un contacto, permitiendo filtrar la 
	 * información en tiempo real.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		mostrarBuscarDatos(txtBuscar.getText());
	}


}
