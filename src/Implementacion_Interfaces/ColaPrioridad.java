package Implementacion_Interfaces;

public class ColaPrioridad implements ColaPrioridadTDA {
	
	class Elem{
		String nombre;
		int p; 
	}
	
	Elem [] vector;
	int cant; 
	
	@Override
	public void inicializarCola() {
		vector = new Elem[10]; 
		cant = 0;
	}
	
	public void acolarPrioridad(String x, int prioridad) {
		int i = cant-1; 
		while (i >= 0 && prioridad <=vector[i].p) { 
			vector[i+1] = vector[i]; 
			i-=1; 
		}
		vector[i+1] = new Elem(); 
		vector[i+1].p = prioridad; 
		vector[i+1].nombre = x; 
		cant+=1;
	}
	
	@Override
	public void acomodarCola() {
		for (int i = 0 ; i<cant-1 ; i++) {
			vector[i].nombre = vector[i+1].nombre;
			vector[i].p = vector[i+1].p;
		}
	}

	@Override
	public void desacolar() {
		cant-=1; 
	}
	
	@Override
	public int cantPersonas(){
		return cant;
	}

	@Override
	public String primerJugador() {
		return vector[cant-1].nombre;
	}

	@Override
	public boolean ColaVacia() {
		return cant == 0;
	}
	
	@Override
	public int prioridad() {
		return vector[cant-1].p;
	}

}
