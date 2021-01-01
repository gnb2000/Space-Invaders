package TPO;

import java.util.*;

import Implementacion_Interfaces.ColaPrioridad;
import Implementacion_Interfaces.ColaPrioridadTDA;
import Views.MuroView;
import Views.NaveInvasoraView;



public class Juego {
	

    /** CONSTRUCTOR **/ 
	
    private Juego() {
    	this.nivel = new Nivel();
    	this.jugador = new Jugador();
    	this.registro = new Registro();
    }
    
    /** SINGLETON **/
    
    public static Juego getInstancia() { 
		if (instancia == null) { 
			instancia = new Juego();
		}
		return instancia;
	}
    
    /** ATRIBUTOS **/ 
    
    private Nivel nivel;
    private Jugador jugador;
    private Registro registro;
    private int contador500Puntos = 0;    
	private static Juego instancia; 

    /** METODOS **/
     
	public ArrayList<NaveInvasoraView> regenerarNavesInvasoras() {										
		ArrayList <NaveInvasora> bloqueNaves = nivel.getNavesInvasoras();
		for (NaveInvasora invasoraActual : bloqueNaves) {
			invasoraActual.regenerarNaveInvasora();
		}
		ArrayList<NaveInvasoraView> invasorasView = obtenerInvasoras();
		return invasorasView;
	}
	
	public ArrayList <MuroView> regenerarMuros() {												
		ArrayList <Muro> bloqueMuros = nivel.getMuros();
		for (Muro muro : bloqueMuros) {
			muro.regenerarse();
		}
		ArrayList<MuroView> murosView = obtenerMurosView();
		return murosView;
	}
	
	public ArrayList <NaveInvasoraView> obtenerInvasoras() {									
		ArrayList<NaveInvasoraView> navesView = new ArrayList<NaveInvasoraView>();
		ArrayList<NaveInvasora> naves = nivel.getNavesInvasoras();
		for (NaveInvasora actual : naves) {
			navesView.add(actual.naveInvasoraToView());
		}
		return navesView;
	}
	
	public ArrayList <MuroView> obtenerMurosView() {											
		ArrayList<MuroView> murosView = new ArrayList<MuroView>();
		ArrayList<Muro> muros = nivel.getMuros();
		for (Muro actual : muros) {
			murosView.add(actual.muroToView());
		}
		return murosView;
	}
	
	public int getPosicionYProyectil(int nroNave) {
		NaveInvasora aux = nivel.buscarNaveInvasora(nroNave);
		return aux.getProyectilInvasor().getPosicionY();
	}
	
	public int getPosicionXProyectil(int nroNave) {												
		NaveInvasora aux = nivel.buscarNaveInvasora(nroNave);
		return aux.getProyectilInvasor().getPosicionX();
	}
	
	
    public boolean verificarImpactoProyectilMuro() {
    	return this.nivel.verificoImpactoEnMuro();
    }
    
    public boolean verificarImpactoProyectilInvasorMuro() {
    	return this.nivel.verificoImpactoProyectilInvasorEnMuro();
    }
    
    public boolean verificarExistenciaNavesInvasoras() {
    	return this.nivel.existenNavesInvasoras();
    }
    
    public int muroImpactadoProyectilInvasor() {
    	return this.nivel.impactaProyectilInvasorEnMuro();
    }
    
    public void cargarRegistro() {
    	this.registro.leerRegistro();
    }
    
    public boolean verificarImpactoProyectilEnInvasora() {
    	return this.nivel.verificoImpactoProyectilEnInvasora();
    }
    
    public void actualizaVidaInvasora(int subindice) {
    	this.nivel.actualizarVidaInvasoraEspecifica(subindice);
    	
    }
    
    public int subIndiceInvasoraAtacada() {
    	return this.nivel.obtengoInvasoraAtacada();
    }
    
    public int actualizarVidaMuro() {
    	return this.nivel.impactaProyectilEnMuro();
    }
    
   public int cantVidasDeUnMuro(int subindice) {
	  return this.nivel.buscarMuro(subindice).cantVidas();
   }
    
    public void moverProyectilBateria() {
    	this.nivel.getBateriaActual().getProyectil().lanzarProyectil();
    }
    
    public void moverProyectilInvasora(int pos) {
    	this.nivel.buscarNaveInvasora(pos).getProyectilInvasor().lanzarProyectil();
    }
    
    public int posicionYProyectil() {
    	/** Proyecil de la bateria **/
    	return this.nivel.getBateriaActual().getProyectil().getPosicionY();
    }
    
    public int getPuntosJugador () {
    	return this.jugador.getPuntos();
    }
    
    public int getPosicionXBateria() {
    	return this.nivel.getBateriaActual().getPosicionX();
    }
    
    public boolean verificarImpactoProyectilEnBateria(int posicionX,int posicionY) {
    	return this.nivel.getBateriaActual().recibirImpacto(posicionX, posicionY);
    }
    
    public void moverNaveInvasora() {
    	this.nivel.moverNavesInvasoras();
    }
    
    public int getIncrementoPorDificultad() {
    	return this.nivel.getIncrementoPorDificultad();
    }
    
    public void crearBloqueMuro() {
    	this.nivel.crearBloqueMuros();
    }
    
    public void crearBloqueNavesInvasoras() {
    	this.nivel.crearBloqueNavesInvasoras();
    }
    
    public int getNroNivelActual() {
    	return this.nivel.getNivel();
    }
    
    public int getVidaJugador() {
    	return this.jugador.getVidaJugador();
    }
    
    public boolean invasoraLlegoAlFin() {
    	return this.nivel.InvasorasLlegaronAlFin();
    }
    
    public void obtenerNombre(String nombre1) {
    	this.jugador.setNombre(nombre1);
    }
    
    public ColaPrioridadTDA getCola() {
    	return this.registro.getListado();
    }
    
    public void finalizar() {
        Registro registroActual = this.registro;
        registroActual.verificarPuntos(this.jugador.getNombre(), this.jugador.getPuntos());    
    }


    public void obtenerDificultad(String dificultad) {
    	this.nivel.setDificultad(dificultad);
    }

 
    public void pasarNivel() { 
    	this.nivel.incrementarNumeroNivel();
    	ArrayList<Muro> muros = this.nivel.getMuros();
    	ArrayList<NaveInvasora> navesInvasoras = this.nivel.getNavesInvasoras();
    	for (Muro muroActual : muros) {
    		muroActual.regenerarse();
    	}
    	for (NaveInvasora naveActual : navesInvasoras) {
    		naveActual.regenerarNaveInvasora();
    	}
    	this.nivel.incrementarVelocidadMovimiento();
    	
    	sumarPuntos(200);
    }
 
    public void sumarPuntos(int puntosNuevos) { 
    	int puntosActuales = this.jugador.getPuntos();
    	puntosActuales +=puntosNuevos;
    	contador500Puntos += puntosNuevos;
    	if (contador500Puntos >= 500) {
    		sumarVida();
    		contador500Puntos = 0;
    	}
    	this.jugador.actualizarPuntos(puntosActuales);
    }

    public void sumarVida() {
    	int vidaActual = this.jugador.getVidaJugador();
    	vidaActual +=1;
    	
    	this.jugador.actualizarVida(vidaActual);
    }
    
    public int disparoNaveInvasora() {
    	return this.nivel.disparoInvasor();
    }

    public void disparoBateria() {
        this.nivel.getBateriaActual().crearDisparoBateria();
    }
    
    public boolean getEstadoNivel() {
    	return this.nivel.getEstado();
    }
    
    public void moverBateria(int sentidoMovimiento) {
    	this.nivel.getBateriaActual().mover(sentidoMovimiento);
    }
    
    public void restarVida() {
    	int vidaActual = this.jugador.getVidaJugador();
    	vidaActual -=1;
    	if (vidaActual == 0) {
        	this.nivel.actualizarEstado();
        	this.jugador.actualizarVida(vidaActual);
    	} else {
    		this.jugador.actualizarVida(vidaActual);
    	}	
    }
}