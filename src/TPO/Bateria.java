package TPO;


import java.util.*;


public class Bateria {

	/** CONSTRUCTOR **/
	
    public Bateria() {
    	this.vida = true;
    	this.posicionX = 310; 	/** Es la mitad de la pantalla **/
    	this.posicionY=370;
    }
    
    /** ATRIBUTOS **/ 
    
    private int posicionX;
    private int posicionY;
    private boolean vida;
    private int identificador = 0;
    private Proyectil proyectil;

    /** METODOS **/
    
    public void mover(int sentidoMovimiento) { //Sentido movimiento = 1(derecha) o -1 (izquierda)
    	if (sentidoMovimiento == 1) {
    		if (this.posicionX + 10 <= 700) { 
    			this.posicionX = this.posicionX + 10;
    		}
    	} else if (sentidoMovimiento == -1){
    		if (this.posicionX - 10 >= 0) {
    			this.posicionX = this.posicionX - 10;
    		}
    	}
    }

    public boolean recibirImpacto(int posicionXProyectil,int posicionYProyectil) {
        boolean band = false;
        if (posicionXProyectil>=this.posicionX && posicionXProyectil<=this.posicionX+40) {

        	if (posicionYProyectil<=this.posicionY && posicionYProyectil>=this.posicionY-20) {
        		band=true;
        	}
        }
        return band;
    }
    
    public void actualizarEstadoVida() {
    	this.vida=false;
    }

    public void posicionDefault() {
        this.posicionX=50;
    }

    public int getPosicionX() {
        return this.posicionX;
    }
    
    public int getIdentificador() {
    	return this.identificador;
    }

    public void crearDisparoBateria() {
        this.proyectil = new Proyectil(this.posicionX+17,this.posicionY+10,this.identificador);
    }
    
    public Proyectil getProyectil() {
    	return this.proyectil;
    }
    
    public Bateria getBateriaActual() {
    	return this;
    }

}