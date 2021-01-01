package Ventanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import TPO.Juego;
import TPO.Muro;
import TPO.NaveInvasora;
import Views.MuroView;
import Views.NaveInvasoraView;

public class VentanaJuego extends JFrame{
	private JLabel lblBateria,lblVidas,lblPuntos,lblNroNivel,lblProyectilBateria,lblProyectilInvasor;
	private ArrayList<JLabel> muros = new ArrayList<JLabel>();
	private ArrayList<JLabel> navesInvasoras = new ArrayList<JLabel>();
	private Timer timerInvasoras,timer1,timerCreacionProyectilInvasor,timerMovimientoProyectilInvasor;
	private boolean disparoEnProceso=true;
	private VentanaJuego ventanaActual;
	
	
	public VentanaJuego() {
		configurarJuego();
		ventanaActual = this;
	}

	
	private void configurarJuego() {
		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.BLACK);
		this.setTitle("Space Invaders");
		this.setVisible(true);
		this.setSize(750,500);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Posicionar bateria
		
		lblBateria = new JLabel();
		lblBateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Bateria.png")));
		lblBateria.setBounds(Juego.getInstancia().getPosicionXBateria(),385,40,40);
		lblBateria.setVisible(true);
		c.add(lblBateria);
		
		//Posicionar Vidas
		
		lblVidas = new JLabel("Vidas: "+Juego.getInstancia().getVidaJugador());
		lblVidas.setBounds(20, 0, 50, 30);
		lblVidas.setForeground(Color.WHITE);
		lblVidas.setVisible(true);
		c.add(lblVidas);
		
		//Posicionar Proyectil Bateria
		
		lblProyectilBateria = new JLabel();
		lblProyectilBateria.setBounds(Juego.getInstancia().getPosicionXBateria()+17, 380, 4, 12);
		lblProyectilBateria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Disparo.png")));
		lblProyectilBateria.setVisible(false);
		c.add(lblProyectilBateria);
		
		//Posicionar Proyectil Invasor
		
		lblProyectilInvasor = new JLabel();
		lblProyectilInvasor.setBounds(0, 0, 4, 12);
		lblProyectilInvasor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Disparo.png")));
		lblProyectilInvasor.setVisible(false);
		c.add(lblProyectilInvasor);
		
		//Posicionar Puntos
		
		lblPuntos = new JLabel("Puntos: "+Juego.getInstancia().getPuntosJugador());
		lblPuntos.setBounds(80,0,100,30);
		lblPuntos.setForeground(Color.WHITE);
		lblPuntos.setVisible(true);
		c.add(lblPuntos);
		
		//Posicionar Nivel
		
		lblNroNivel = new JLabel("Nivel: "+Juego.getInstancia().getNroNivelActual());
		lblNroNivel.setBounds(680,0,50,30);
		lblNroNivel.setForeground(Color.WHITE);
		lblNroNivel.setVisible(true);
		c.add(lblNroNivel);
		
		//Armar bloque de muros
		
		for (int i=0;i<4;i++) {
			JLabel muro = new JLabel();
			muro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 1.png")));
			muros.add(muro);
			c.add(muro);
		}
		ubicarMuros();
		
		//Armar bloque de naves invasoras
		
		for (int j = 0;j<15;j++) {
			JLabel naveInvasora = new JLabel();
			naveInvasora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Invasora.png")));
			navesInvasoras.add(naveInvasora);
			c.add(naveInvasora);
		}
		
		ubicarNavesInvasoras();
		timerCrearProyectilesInvasores();		
		
		//Mover cosas
		
		moverConFlechas();
		dispararConEspacio();
		eventos();

	}
	
	private void moverConFlechas() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 39 ) { //Flecha derecha
					Juego.getInstancia().moverBateria(1);
					lblBateria.setBounds(Juego.getInstancia().getPosicionXBateria(),385,40,40);
				} else if (e.getKeyCode() == 37) {
					Juego.getInstancia().moverBateria(-1);
					lblBateria.setBounds(Juego.getInstancia().getPosicionXBateria(),385,40,40);
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {				
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
			
		});
	}
	
	private void ubicarMuros() {
		Juego.getInstancia().crearBloqueMuro();
		ArrayList<MuroView> bloqueMuros = Juego.getInstancia().obtenerMurosView();
		int cont = 0;
		for (MuroView muroActual: bloqueMuros) {
			muros.get(cont).setBounds(muroActual.posicionX(), 330, 50, 20);
			muros.get(cont).setVisible(true);
			cont+=1;
		}
	}
	
	private void ubicarNavesInvasoras() {
		Juego.getInstancia().crearBloqueNavesInvasoras();
		ArrayList<NaveInvasoraView> bloqueNavesInvasoras = Juego.getInstancia().obtenerInvasoras();
		int cont1 = 0;
		for (NaveInvasoraView invasoraActual: bloqueNavesInvasoras) {
			navesInvasoras.get(cont1).setBounds(invasoraActual.getPosicionX(), invasoraActual.getPosicionY(), 34, 25);
			navesInvasoras.get(cont1).setVisible(true);
			cont1+=1;
		}
	}
	
	//Timer Proyectil Naves Invasoras
	
	private void timerMoverProyectilInvasora(int posNave,int posXInicial) {
		int delay = 80;
		ActionListener disparoInvasor3 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Juego.getInstancia().verificarImpactoProyectilInvasorMuro() == false && Juego.getInstancia().verificarImpactoProyectilEnBateria(posXInicial, Juego.getInstancia().getPosicionYProyectil(posNave)) == false && Juego.getInstancia().getPosicionYProyectil(posNave) <= 500) {
					Juego.getInstancia().moverProyectilInvasora(posNave);
					lblProyectilInvasor.setBounds(posXInicial, Juego.getInstancia().getPosicionYProyectil(posNave), 4, 12);
				} else {
					if (Juego.getInstancia().verificarImpactoProyectilInvasorMuro() == true) {
						int indice = Juego.getInstancia().muroImpactadoProyectilInvasor();
						if (indice != -1) {
							if (Juego.getInstancia().cantVidasDeUnMuro(indice) > 10 && Juego.getInstancia().cantVidasDeUnMuro(indice)<=15) {
								muros.get(indice).setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 2.png")));
							} else if (Juego.getInstancia().cantVidasDeUnMuro(indice) > 5 && Juego.getInstancia().cantVidasDeUnMuro(indice)<=10) {
								muros.get(indice).setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 3.png")));
							} else if (Juego.getInstancia().cantVidasDeUnMuro(indice) > 0 && Juego.getInstancia().cantVidasDeUnMuro(indice)<=5) {
								muros.get(indice).setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 4.png")));
							} else if (Juego.getInstancia().cantVidasDeUnMuro(indice) <= 0) {
								muros.get(indice).setVisible(false);
							}
						}
						
					} else if (Juego.getInstancia().verificarImpactoProyectilEnBateria(posXInicial, Juego.getInstancia().getPosicionYProyectil(posNave))) {
						Juego.getInstancia().restarVida();
						lblVidas.setText("Vidas: "+Juego.getInstancia().getVidaJugador());
						if (Juego.getInstancia().getEstadoNivel() == false) {
							timerCreacionProyectilInvasor.stop();
							timerMovimientoProyectilInvasor.stop();
							timerInvasoras.stop();
							JOptionPane.showMessageDialog(null,"Has perdido, no tenes mas vidas. GAME OVER");
							Juego.getInstancia().finalizar();
							Ranking ranking = new Ranking();
							ventanaActual.setVisible(false);
							ranking.setVisible(true);

						} else if (Juego.getInstancia().getVidaJugador() > 0) {
							int pos = 0;
							ArrayList<NaveInvasoraView> bloqueInvasoras = Juego.getInstancia().regenerarNavesInvasoras();
							for (JLabel invasoraActual : navesInvasoras) {
								invasoraActual.setBounds(bloqueInvasoras.get(pos).getPosicionX(), bloqueInvasoras.get(pos).getPosicionY(), 100, 100);
								invasoraActual.setVisible(true);
								pos+=1;
							}
							pos=0;
							ArrayList<MuroView> bloqueMuros = Juego.getInstancia().regenerarMuros();
							for (JLabel muroActual : muros) {
								muroActual.setBounds(bloqueMuros.get(pos).posicionX(), 330, 50, 20);
								muroActual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 1.png")));
								muroActual.setVisible(true);
								pos+=1;
							}
						}						
					}
					lblProyectilInvasor.setVisible(false);
					timerMovimientoProyectilInvasor.stop();
				}
			}
			
		};

		timerMovimientoProyectilInvasor = new Timer(delay,disparoInvasor3);
		timerMovimientoProyectilInvasor.start();
	}
	
	private void timerCrearProyectilesInvasores() {
		int delay = 5000; 
		ActionListener disparoInvasor2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int invasoraAtacante = Juego.getInstancia().disparoNaveInvasora(); //Creo el disparo invasor
				int xInicial = Juego.getInstancia().getPosicionXProyectil(invasoraAtacante);
				lblProyectilInvasor.setBounds(xInicial, Juego.getInstancia().getPosicionYProyectil(invasoraAtacante), 4, 12);
				lblProyectilInvasor.setVisible(true);
				timerMoverProyectilInvasora(invasoraAtacante,xInicial);				
				}
		};

		timerCreacionProyectilInvasor = new Timer(delay,disparoInvasor2);
		timerCreacionProyectilInvasor.start();
	}
	
	//Timer movimiento naves invasoras
	
	private void eventos() {
		timerInvasoras = new Timer(Juego.getInstancia().getIncrementoPorDificultad(),new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<NaveInvasoraView> bloqueNavesInvasoras = Juego.getInstancia().obtenerInvasoras();
				Juego.getInstancia().moverNaveInvasora();
				int pos = 0;
				for (JLabel invasoraActual : navesInvasoras) {
					invasoraActual.setBounds(bloqueNavesInvasoras.get(pos).getPosicionX(), bloqueNavesInvasoras.get(pos).getPosicionY(), 100, 100);
					pos+=1;
				}
				pos=0;
				if (Juego.getInstancia().invasoraLlegoAlFin()) {
					timerInvasoras.stop();
					Juego.getInstancia().restarVida();
					
					if (Juego.getInstancia().getEstadoNivel() == false) {
						timerCreacionProyectilInvasor.stop();
						timerMovimientoProyectilInvasor.stop();						
						JOptionPane.showMessageDialog(null,"Has perdido, no tenes mas vidas. GAME OVER");
						Juego.getInstancia().finalizar();
						Ranking ranking = new Ranking();
						ventanaActual.setVisible(false);
						ranking.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null,"Has perdido, se reiniciara el nivel");
						lblVidas.setText("Vidas: "+Juego.getInstancia().getVidaJugador());
						timerInvasoras.restart();
						ArrayList<NaveInvasoraView> bloqueInvasoras = Juego.getInstancia().regenerarNavesInvasoras();
						for (JLabel invasoraActual : navesInvasoras) {
							invasoraActual.setBounds(bloqueInvasoras.get(pos).getPosicionX(), bloqueInvasoras.get(pos).getPosicionY(), 100, 100);
							invasoraActual.setVisible(true);
							pos+=1;
						}
						pos=0;
						ArrayList<MuroView> bloqueMuros = Juego.getInstancia().regenerarMuros();
						for (JLabel muroActual : muros) {
							muroActual.setBounds(bloqueMuros.get(pos).posicionX(), 330, 50, 20);
							muroActual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 1.png")));
							muroActual.setVisible(true);
							pos+=1;
						}
					}
				}	
			}
		});
		timerInvasoras.start();
	}
	
	/** ActionListener disparo Bateria **/
	
	private void dispararConEspacio() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE && disparoEnProceso == true) { 
					disparoEnProceso=false;
					Juego.getInstancia().disparoBateria();
					disparoBateria(Juego.getInstancia().getPosicionXBateria()+17);
					lblProyectilBateria.setBounds(Juego.getInstancia().getPosicionXBateria()+17, 380, 4, 12);
					lblProyectilBateria.setVisible(true);				
					}
			}
			@Override
			public void keyReleased(KeyEvent e) {				
			}
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
		});
	}

	
	/** Timer proyectil bateria **/
	
	private void disparoBateria(int posBateriaActual) { 
		int delay = 70;
		ArrayList<NaveInvasoraView> bloqueNavesInvasoras = Juego.getInstancia().obtenerInvasoras();
		ArrayList<MuroView> bloqueMuros = Juego.getInstancia().obtenerMurosView();

		ActionListener disparo = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if (Juego.getInstancia().verificarImpactoProyectilEnInvasora() == false && Juego.getInstancia().verificarImpactoProyectilMuro() == false && Juego.getInstancia().posicionYProyectil() > 0) {
					Juego.getInstancia().moverProyectilBateria();
					lblProyectilBateria.setBounds(posBateriaActual, Juego.getInstancia().posicionYProyectil(),4,12);
				} else {
					if (Juego.getInstancia().verificarImpactoProyectilMuro() == true) {
						int indice = Juego.getInstancia().actualizarVidaMuro();
						if (Juego.getInstancia().cantVidasDeUnMuro(indice) > 10 && Juego.getInstancia().cantVidasDeUnMuro(indice)<=15) {
							muros.get(indice).setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 2.png")));
						} else if (Juego.getInstancia().cantVidasDeUnMuro(indice) > 5 && Juego.getInstancia().cantVidasDeUnMuro(indice)<=10) {
							muros.get(indice).setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 3.png")));
						} else if (Juego.getInstancia().cantVidasDeUnMuro(indice) > 0 && Juego.getInstancia().cantVidasDeUnMuro(indice)<=5) {
							muros.get(indice).setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 4.png")));
						} else if (Juego.getInstancia().cantVidasDeUnMuro(indice) <= 0) {
							muros.get(indice).setVisible(false);
						}
					} else if (Juego.getInstancia().verificarImpactoProyectilEnInvasora() == true) {
						int indice2 = Juego.getInstancia().subIndiceInvasoraAtacada();
						Juego.getInstancia().actualizaVidaInvasora(indice2);
						navesInvasoras.get(indice2).setVisible(false);
						Juego.getInstancia().sumarPuntos(10);
						lblPuntos.setText("Puntos: "+Juego.getInstancia().getPuntosJugador());
						lblVidas.setText("Vidas: "+Juego.getInstancia().getVidaJugador());
						if (Juego.getInstancia().verificarExistenciaNavesInvasoras() == false) {
							Juego.getInstancia().pasarNivel();
							lblPuntos.setText("Puntos: "+Juego.getInstancia().getPuntosJugador());
							lblNroNivel.setText("Nivel: "+Juego.getInstancia().getNroNivelActual());
							int pos=0;
							for (JLabel invasoraActual : navesInvasoras) {								
								invasoraActual.setBounds(bloqueNavesInvasoras.get(pos).getPosicionX(), bloqueNavesInvasoras.get(pos).getPosicionY(), 100, 100);
								invasoraActual.setVisible(true);
								pos+=1;
							}
							pos=0;
							for (JLabel muroActual : muros) {
								muroActual.setBounds(bloqueMuros.get(pos).posicionX(), 330, 50, 20);
								muroActual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Muro 1.png")));
								muroActual.setVisible(true);
								pos+=1;
							}

						}

					}
					timer1.stop();					
					lblProyectilBateria.setVisible(false);
					disparoEnProceso=true;
				}
			}
			
		};

		timer1 = new Timer(delay,disparo);
		timer1.start();
	}
	
}
