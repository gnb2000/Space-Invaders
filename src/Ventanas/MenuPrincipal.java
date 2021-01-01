package Ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Implementacion_Interfaces.ColaPrioridadTDA;
import TPO.Juego;

public class MenuPrincipal extends JFrame{
	private JButton btnJugar,btnSalir,btnComenzar,btnRanking,btnVolverJugar;
	private JLabel lblNombre,lblDificultad;
	private JTextField tfNombre;
	private JComboBox<String> combo1;
	private MenuPrincipal estaVentana;
	
	
	
	public MenuPrincipal() {
		configurarMenu();
		asignarEventos();
		estaVentana=this;
	}
	
	private void configurarMenu() {
		
		Container c = this.getContentPane();
		c.setLayout(null);
		c.setSize(400, 350);
		c.setBackground(Color.LIGHT_GRAY);

		this.setTitle("Space Invaders");
		this.setVisible(true);
		this.setSize(400,350);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Menu Principal
		
		btnJugar = new JButton("JUGAR");
		btnJugar.setHorizontalAlignment(SwingConstants.CENTER);

		btnSalir = new JButton("SALIR");
		btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnJugar.setBounds(120, 100, 150, 40);
		btnSalir.setBounds(120,160,150,40);
		
		c.setVisible(true);
		
		c.add(btnJugar);
		c.add(btnSalir);

		
		//Jugar
		
		lblNombre = new JLabel("Ingrese su nombre");
		lblNombre.setBounds(30,50,130,30);
		lblNombre.setVisible(false);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(160,50,200,30);
		tfNombre.setVisible(false);
		
		lblDificultad = new JLabel("Seleccione dificultad");
		lblDificultad.setBounds(25, 100, 130, 30);
		lblDificultad.setVisible(false);
		
		combo1 = new JComboBox();
		combo1.addItem("Cadete");
		combo1.addItem("Guerrero");
		combo1.addItem("Master");
		combo1.setBounds(160, 100, 200, 30);
		combo1.setVisible(false);
	
		btnComenzar = new JButton("Continuar");
		btnComenzar.setBounds(200, 210, 100, 30);
		btnComenzar.setVisible(false);
		
		btnVolverJugar = new JButton("Volver");
		btnVolverJugar.setBounds(80,210,100,30);
		btnVolverJugar.setVisible(false);
		
		c.add(lblNombre);
		c.add(tfNombre);
		c.add(lblDificultad);
		c.add(combo1);
		c.add(btnComenzar);
		c.add(btnVolverJugar);
	}
	
	

	private void asignarEventos() {
		ManejoBotones mb = new ManejoBotones();
		btnSalir.addActionListener(mb);
		btnJugar.addActionListener(mb);
		btnComenzar.addActionListener(mb);
		btnVolverJugar.addActionListener(mb);

	}
	
	class ManejoBotones implements ActionListener {
		
		@Override
		public void actionPerformed (ActionEvent boton) {
			if (boton.getSource().equals(btnSalir)) {
				System.exit(0);
			} else if (boton.getSource().equals(btnJugar)) {
				btnJugar.setVisible(false);
				btnSalir.setVisible(false);
				lblNombre.setVisible(true);
				tfNombre.setVisible(true);
				lblDificultad.setVisible(true);
				combo1.setVisible(true);
				btnComenzar.setVisible(true);
				btnVolverJugar.setVisible(true);

			} else if (boton.getSource().equals(btnComenzar)) {
				Juego.getInstancia().obtenerNombre(tfNombre.getText());
				Juego.getInstancia().obtenerDificultad((String) combo1.getSelectedItem());
				Juego.getInstancia().cargarRegistro();
				VentanaJuego ventanaJuego1 = new VentanaJuego();
				estaVentana.setVisible(false);
				ventanaJuego1.setVisible(true);
			}  else if (boton.getSource().equals(btnVolverJugar)) {
				btnJugar.setVisible(true);
				btnSalir.setVisible(true);
				lblNombre.setVisible(false);
				tfNombre.setVisible(false);
				lblDificultad.setVisible(false);
				combo1.setVisible(false);
				btnComenzar.setVisible(false);
				btnVolverJugar.setVisible(false);
			} 
		}
	}
}	