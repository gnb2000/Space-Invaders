package TPO;

import java.util.*;

import Views.MuroView;
import Views.NaveInvasoraView;


public class Muro {

	/** CONSTRUCTOR **/
	
    public Muro(int posX,int posY) {
    	this.vidaMuro = 20;
    	this.id = identificadorMuro;
    	identificadorMuro+=1;
    	this.posicionX = posX;
    	this.posicionY = posY;
    	this.estadoMuro = true;
    }

    /** ATRIBUTOS **/
    
    private int vidaMuro;
    private int posicionX;
    private int posicionY;
    private static int identificadorMuro=0;
    private int id;
    private boolean estadoMuro;

    /** METODOS **/
    
    public void destruirMuro() {
    	this.estadoMuro = false;
    }
    
    public int posicionX() {
    	return this.posicionX;
    }
    
    public void disminuirVidaMuro(int id) {
    	if (id == 0) { //ES LA BATERIA
    		if (this.vidaMuro-2 <= 0) {
                this.vidaMuro-=2;
                destruirMuro();
            } else {
            	this.vidaMuro-=2;
            }
    	} else { //ES LA NAVE INVASORA
    		if (this.vidaMuro-1 <= 0) {
                this.vidaMuro-=1;
                destruirMuro();
            } else {
            	this.vidaMuro-=1;
            }
    	}

    }

    public void regenerarse() {
    	this.vidaMuro = 20;
    	this.estadoMuro = true;
    }


    public boolean recibiImpacto(int posicionXProyectil,int posicionYProyectil,int identificador) {
    	boolean band=false;
    	if (this.estadoMuro==true) {
    		if (identificador==0) { //ES LA BATERIA
        		if (posicionYProyectil == 340) { 
            		if (posicionXProyectil >= this.posicionX && posicionXProyectil<=this.posicionX+50) {
                		band=true;
            		}
            	} else {
            		band=false;
            	}
        	} else {
        		if (posicionYProyectil >= 320 && posicionYProyectil<=340) {
            		if (posicionXProyectil >= this.posicionX && posicionXProyectil<=this.posicionX+50) {
                		band=true;
            		}
            	} else {
            		band=false;
            	}
        	}
    	}
    	return band;
    }

 
    public boolean verificarVidaMuro() {
        return vidaMuro>0;
    }
    
    public boolean soyEseMuro(int id) {
    	return id==this.id;
    }
    
    public boolean getVida() {
    	return this.estadoMuro;
    }
    
    public int cantVidas() {
    	return this.vidaMuro;
    }
    
    
    public MuroView muroToView() {
    	return new MuroView(this.estadoMuro, this.posicionX, this.posicionY, this.vidaMuro, this.id);
    }
    
}