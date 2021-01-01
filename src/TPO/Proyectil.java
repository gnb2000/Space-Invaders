package TPO;

import java.util.*;

public class Proyectil {

	/** CONSTRUCTOR **/
 	
	public Proyectil(int posicionX,int posicionY, int identificador) {
    	this.posicionXProyectil = posicionX;
    	this.posicionYProyectil = posicionY;
    	establecerTrayectoria(identificador);
    }

    /** ATRIBUTOS **/ 
	
    private int trayectoriaProyectil;
    private int posicionXProyectil;
    private int posicionYProyectil;
    

    /** METODOS **/
    
    public int getPosicionX() {
    	return this.posicionXProyectil;
    }
    
    public int getPosicionY() {
    	return this.posicionYProyectil;
    }
    
    public boolean impactaProyectil(int posicionX,int posicionY) {
        if (posicionX == this.posicionXProyectil && posicionY == this.posicionYProyectil) {
        	return true;
        } else {
        	return false;
        }
    }
    
    public int getTrayectoria() {
    	return this.trayectoriaProyectil;
    }
    
    public void establecerTrayectoria(int identificador) {
    	/** Cero (Bateria) == False , Uno (NaveInvasora)  == True **/
    	
    	if (identificador == 1) {
    		this.trayectoriaProyectil= -1 ; //Trayectoria para abajo
    	} else {
    		this.trayectoriaProyectil = 1;//Trayectoria para arriba
    	}
    }
    
     
    public void lanzarProyectil() {
    	/** Cero (Bateria) == False , Uno (NaveInvasora)  == True **/

    	if ((this.posicionYProyectil>0 && this.trayectoriaProyectil == 1) ||( this.posicionYProyectil<720 && this.trayectoriaProyectil == -1)) {
    		if (this.trayectoriaProyectil == 1) {
    			this.posicionYProyectil = this.posicionYProyectil-20;
    		} else if (trayectoriaProyectil==-1){
    			this.posicionYProyectil = this.posicionYProyectil +20;
    		}
    	}
			
    }

}