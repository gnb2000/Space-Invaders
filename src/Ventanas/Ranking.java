package Ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Implementacion_Interfaces.ColaPrioridadTDA;
import TPO.Juego;

public class Ranking extends JFrame{
	private JTextArea textA,textB;
	private JLabel lblTitulo,lblNombres,lblPuntos;
	private JButton btnVolverInicio;
	
	
	public Ranking() {
		configurarRanking();
	}
	
	private void configurarRanking() {
		Container c = this.getContentPane();
		c.setLayout(null);
		this.setTitle("Ranking TOP 10");
		this.setVisible(true);
		this.setSize(400, 350);
		c.setBackground(Color.GRAY);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		/** Ranking **/
		
		lblTitulo = new JLabel("RANKING TOP 10");
		Font font = new Font("Segoe Script", Font.ITALIC, 20);
        lblTitulo.setFont(font);
        lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(100, 0, 210, 40);
		lblTitulo.setVisible(true);
		c.add(lblTitulo);
		
		lblNombres = new JLabel("NOMBRES");
		lblNombres.setBounds(80, 60, 70, 30);
		lblNombres.setForeground(Color.WHITE);
		lblNombres.setVisible(true);
		c.add(lblNombres);
		
		lblPuntos = new JLabel("PUNTOS");
		lblPuntos.setBounds(250, 60, 70, 30);
		lblPuntos.setForeground(Color.WHITE);
		lblPuntos.setVisible(true);
		c.add(lblPuntos);
		
		btnVolverInicio = new JButton("SALIR");
		btnVolverInicio.setBounds(130,280,130,30);
		btnVolverInicio.setVisible(true);
		c.add(btnVolverInicio);
		

		textA = new JTextArea();
		textA.setBounds(80, 90, 150, 180);
		textA.setEditable(false);
		textA.setVisible(true);
		
		textA.setFont(new Font("monospaced", Font.PLAIN, 12));
        textA.setForeground(Color.WHITE);
		
		textA.setBackground(Color.GRAY);
		c.add(textA);
		
		textB = new JTextArea();
		textB.setBounds(250, 90, 30, 180);
		textB.setEditable(false);
		textB.setVisible(true);
		
		textB.setFont(new Font("monospaced", Font.PLAIN, 12));
        textB.setForeground(Color.WHITE);
		
		textB.setBackground(Color.GRAY);
		c.add(textB);
		

	    
		ColaPrioridadTDA cola = Juego.getInstancia().getCola();
		
		while (!cola.ColaVacia()) {
			textA.append(cola.primerJugador()  + "\n");
			textB.append(Integer.valueOf(cola.prioridad()) + "\n" );
			
			cola.desacolar();
		}
		
		eventosBotones();
	}
	
	private void eventosBotones() {
		ManejoBotones1 mb = new ManejoBotones1();
		btnVolverInicio.addActionListener(mb);
		
	}
	
	class ManejoBotones1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnVolverInicio)) {
				System.exit(0);
			}
			
		}
		
	}
}
