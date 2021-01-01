package Implementacion_Interfaces;

public interface ColaPrioridadTDA {
	void inicializarCola(); 
	void acolarPrioridad(String nombre, int puntos); 
	void desacolar(); 
	String primerJugador(); 
	boolean ColaVacia(); 
	int prioridad();
	int cantPersonas();
	void acomodarCola();
}
