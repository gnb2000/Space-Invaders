package TPO;

import java.util.*;


public class Jugador {

	/** CONSTRUCTOR **/
	
	public Jugador() {
    	this.puntos = 0;
    	this.vidaJugador = 3;
    }
    
    /** ATRIBUTOS **/ 
	
    private String nombre;
    private int puntos;
    private int vidaJugador;
    

    /** METODOS **/ 
    
    public void setNombre(String nombre1) {
    	this.nombre = nombre1;
    }
    
    public int getVidaJugador() {
    	return this.vidaJugador;
    }
    
    public String getNombre() {
    	return this.nombre;
    }
    
    public void actualizarVida(int vidaNueva) {
    	this.vidaJugador = vidaNueva;
    }
    
    public void actualizarPuntos (int puntosNuevos) {
    	this.puntos = puntosNuevos;
    }
    
    public int getPuntos() {
    	return this.puntos;
    }

}