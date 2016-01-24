package com.codizer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
public class VentanaList extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6773111825611322554L;
	
	private JLabel lbTodos = new JLabel("Todos");
	private JButton btnNuevo = new JButton("+");
	private JTextField txtBuscar = new JTextField();

	private JTable table = new JTable();
	private JScrollPane pane = new JScrollPane(table);
	private DefaultTableModel columnas = new DefaultTableModel();
	private String[] data = new String[2];
	private ListSelectionModel model = table.getSelectionModel();
	
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
	private List<Contacto> contactos = contactoDao.findAll();
	
	Container c = getContentPane();
	
	/**
	 * 
	 */
	public VentanaList() {

		super.setTitle("10-Contactos");
		super.setSize(320, 550);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLocationRelativeTo(null);
		super.setResizable(false);

		cargarData();
		cargarControles();
	}
	

	/**
	 * 
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
		
		table.setRowHeight(36);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		
		pane.setBounds(0, 100, 320, 450);
		
		c.add(txtBuscar);
		c.add(btnNuevo);
		c.add(lbTodos);
		c.add(pane);
		
		btnNuevo.addActionListener(this);
	}

	/**
	 * 
	 */
	private void cargarData() {
		
		columnas.addColumn("");
		columnas.addColumn("");

		table.setModel(columnas);

		for (Contacto contacto2 : contactos) {
			
			data[0] = String.format("   %s %s", contacto2.getNombre(), contacto2.getApellidos());
			data[1] = Integer.toString(contacto2.getId());
			columnas.addRow(data);
		}
		
		model.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int fila = table.getSelectedRow();
				
				if (fila >= 0) {
					VentanaView ventanaView = new VentanaView((String) table.getValueAt(fila, 1));
					ventanaView.setVisible(true);
				}
			}
		});
	}


	public static void main(String[] args) {
		VentanaList s = new VentanaList();
		s.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		VentanaNew ventanaNew = new VentanaNew();
		ventanaNew.setVisible(true);
		// dispose();
	}


}
