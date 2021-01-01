package TPO;

import java.util.*;

import Views.NaveInvasoraView;

public class NaveInvasora {

	/** CONSTRUCTOR **/ 
	
    public NaveInvasora(int posX,int posY) {
    	this.vidaNave = true;
    	this.nroNave = navesTotales;
    	navesTotales +=1;
    	this.posicionX = posX;
    	this.posicionY = posY;
    	this.posInicial = new int[2];
    	this.posInicial[0] = posX;
    	this.posInicial[1] = posY;
    }

    /** ATRIBUTOS **/
    
    private boolean vidaNave;
    private int posicionX;
    private int posicionY;
    private int nroNave;
    private int IDENTIFICADOR =1;
    private int posInicial[];
    private static int navesTotales=0;
    private Proyectil proyectil;

                                                   
    /** METODOS **/
    
    public void disparoInvasor() {
    	this.proyectil = new Proyectil(this.posicionX+25,this.posicionY+70,this.IDENTIFICADOR);
    }
    
    public Proyectil getProyectilInvasor() {
    	return this.proyectil;
    }
    
    public void borrarProyectil() {
    	this.proyectil = null;
    }
    
    public void moverPosicionX(boolean direccionNavesInvasoras) {
    	if (direccionNavesInvasoras == false) {
        	this.posicionX +=10;
    	} else {
        	this.posicionX -=10; 
    	}
    }
    
    public void bajarLinea() {
    	this.posicionY +=8;
    }
    
    public void destruirNave() {
    	this.vidaNave=false;
    }
    
    public void regenerarNaveInvasora() {
    	this.vidaNave=true;
    	this.posicionX = this.posInicial[0];
    	this.posicionY = this.posInicial[1];
    }
    
    public boolean recibiImpacto(int posicionXProyectil,int posicionYProyectil) {
    	boolean band = false;
    	if (this.vidaNave == true) {
    		if ((posicionXProyectil >= this.posicionX) && (posicionXProyectil <= this.posicionX + 20)) {
        		if (posicionYProyectil >= this.posicionY && posicionYProyectil<= this.posicionY + 50) {        			
            		band=true;
        		}
        	}
    	}
    	return band;
    }
    
    public boolean soyEsaNave(int nroNave) {
    	return this.nroNave==nroNave;
    }
    
    public int getIdentificador() {
    	return this.IDENTIFICADOR;
    }
    
    public boolean getVidaNave() { ////////////////
    	return this.vidaNave;
    }
    
    public int getPosicionX() { //////////////////
    	return this.posicionX;
    }
    
    public int getPosicionY() { ///////////////////
    	return this.posicionY;
    }
    
    public NaveInvasoraView naveInvasoraToView() {
    	return new NaveInvasoraView(this.vidaNave, this.posicionX, this.posicionY, this.nroNave);
    }
}