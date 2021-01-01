package TPO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


import Implementacion_Interfaces.ColaPrioridadTDA;
import Implementacion_Interfaces.ColaPrioridad;


public class Registro {
	
	/** CONSTRUCTOR **/
	
    public Registro() {
    	this.listado = new ColaPrioridad();
    	listado.inicializarCola();
    }

   
    /** ATRIBUTOS **/
    
    private ColaPrioridadTDA listado;
    
    
    /** METODOS **/ 
    
    public Registro getRegistro() {
    	return this;
    }
    
    
    public void leerRegistro() {
    	try {
            File archivo = new File ("ranking.txt");
            FileReader arch = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(arch);
            String linea =buffer.readLine();
            while(linea != null) {
                String[] partes = linea.split(" ");
                
                listado.acolarPrioridad(partes[0], Integer.valueOf(partes[1]));
                linea = buffer.readLine();
            }
            arch.close();
          }
          catch(Exception e) {
            System.out.println("Excepcion leyendo archivo "+ "ranking.txt" + ": " + e);
          }
    }
    
    public void escribirRegistro(){
    	FileWriter fichero = null;
        PrintWriter pw = null;
        ColaPrioridadTDA copiaListado = new ColaPrioridad();
        copiaListado.inicializarCola();
        copiarListado(listado, copiaListado);
        try {
            fichero = new FileWriter("ranking.txt");
            pw = new PrintWriter(fichero);

           while (!copiaListado.ColaVacia()) {
               pw.println(copiaListado.primerJugador() + " "  + copiaListado.prioridad());
               copiaListado.desacolar();
           }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    
    private void copiarListado(ColaPrioridadTDA cola1, ColaPrioridadTDA cola2) {
		ColaPrioridadTDA aux;
		aux = new ColaPrioridad();
		aux.inicializarCola();
		pasarListado(cola1,aux);
		while (!aux.ColaVacia()) {
			cola1.acolarPrioridad(aux.primerJugador(), aux.prioridad());
			cola2.acolarPrioridad(aux.primerJugador(), aux.prioridad());
			aux.desacolar();

		}
	}
    
    private void pasarListado(ColaPrioridadTDA cola1, ColaPrioridadTDA cola2) {
		while (!cola1.ColaVacia()) {
			cola2.acolarPrioridad(cola1.primerJugador(), cola1.prioridad());
			cola1.desacolar();
		}
	}

    public void verificarPuntos(String nombre, int puntos) {
    	ColaPrioridadTDA copia = new ColaPrioridad();
    	copia.inicializarCola();
    	copiarListado(this.listado,copia);
    	boolean aux = false;
    	while (!copia.ColaVacia()) {
    		if (puntos > copia.prioridad() || copia.cantPersonas() <= 10) {
    			aux=true;
    			break;
    		}
    		copia.desacolar();
    	}
    	if (aux) {
    		actualizarRegistro(nombre,puntos);
    		escribirRegistro();
    	}
    }

    public void actualizarRegistro(String nombre, int puntos) {
    	if (listado.cantPersonas() == 10) {
    		listado.acomodarCola();
        	listado.desacolar();
    	}
        listado.acolarPrioridad(nombre, puntos);
    }

    
    public ColaPrioridadTDA getListado() {
    	ColaPrioridadTDA copia = new ColaPrioridad();
    	copia.inicializarCola();
    	copiarListado(listado,copia);
    	return copia;
    }

}