package TPO;


import java.util.*;


public class Nivel {

	/** CONSTRUCTOR **/
	
	public Nivel() {
    	this.estado=true;
    	this.navesInvasoras = new ArrayList<NaveInvasora>();
    	this.muros = new ArrayList<Muro>();
    	this.bateria = new Bateria();
    	
    }
    
    /** ATRIBUTOS **/ 
	
    private int incrementoPorDificultad;
    private static int numeroNivel=1;
    private String dificultad;
    private boolean estado;
    private static int ANCHOPANTALLA=720;
    private static int ALTURAPANTALLA=500;
    private static boolean direccionNavesInvasoras = false; /** false == se mueve derecha - true == se mueve izquierda **/

    private List<NaveInvasora> navesInvasoras;
    private List<Muro> muros;
    private Bateria bateria;

    /** METODOS **/
    
    public void setDificultad(String dificultadIngresada) {
    	this.dificultad = dificultadIngresada;
    	fijarIncrementoPorDificultad();
    }


    public void actualizarEstado() {
    	if (this.estado) {
    		this.estado=false;
    		
    	} else {
    		this.estado = true;
    	}
    }
    
    public boolean InvasorasLlegaronAlFin() {
    	boolean band = false;
    	for (NaveInvasora naveActual : navesInvasoras) {
    		if (naveActual.getPosicionY() >= 340 && naveActual.getVidaNave() == true) {
    			band=true;
    		}
    	}
    	return band;
    }
    
    public void moverNavesInvasoras() {
    	
    	for (NaveInvasora naveActual: navesInvasoras) {
    		naveActual.moverPosicionX(direccionNavesInvasoras);
    	}
    	
    	if ((navesInvasoras.get(navesInvasoras.size()-1).getPosicionX() >= ANCHOPANTALLA - 20 && direccionNavesInvasoras==false) || (navesInvasoras.get(0).getPosicionX() <=10 && direccionNavesInvasoras)) {
    		for (NaveInvasora aux : navesInvasoras) { 
    			aux.bajarLinea();
    		}
    		if (direccionNavesInvasoras == false) {
    			direccionNavesInvasoras = true;
    		} else {
    			direccionNavesInvasoras = false;
    		}
    	}
    	
    }
    	
    public void incrementarVelocidadMovimiento() {
    	
    	if (this.dificultad.equalsIgnoreCase("cadete")) {
        	this.incrementoPorDificultad = (int) (70*0.9);
        } else if (dificultad.equalsIgnoreCase("guerrero")) {
        	this.incrementoPorDificultad = (int) (30*0.8);
        } else {
        	this.incrementoPorDificultad = (int) (10*0.7);
        }
    }


    public boolean existenNavesInvasoras() {
    	boolean band = false;
        for (NaveInvasora aux : navesInvasoras) {
        	if (aux.getVidaNave() == true) {
        		band=true;
        	}
        }
        return band;
    }
    
    public int getIncrementoPorDificultad() {
    	return this.incrementoPorDificultad;
    }


    public void fijarIncrementoPorDificultad() {
    	
    	/**
    	 * LA DIFICULTAD VIENE DE JUEGO
    	 * Tres dificultades (Cadete, Guerrero, Master)
    	 * Cadete = 1
    	 * Guerrero = 2
    	 * Master = 4
    	 **/
    	
        if (this.dificultad.equalsIgnoreCase("cadete")) {
        	this.incrementoPorDificultad = 100;
        } else if (dificultad.equalsIgnoreCase("guerrero")) {
        	this.incrementoPorDificultad = 70;
        } else {
        	this.incrementoPorDificultad = 50;
        }
        
    }

    public void crearBloqueNavesInvasoras() {
    	int aux1 = ANCHOPANTALLA / 16;
    	int posActualY = ALTURAPANTALLA - 400;
    	int posActualX = aux1;
    	int cont=1; 
    	for (int i=1;i<=15;i++) {
    		crearNaveInvasora(posActualX,posActualY);
    		cont+=1;
    		posActualX = posActualX + (aux1*2);
    		if (cont > 5) {
    			posActualX = aux1;
    			posActualY = posActualY - 50;
    			cont=1;
    		}
    	}
    }

    public void crearNaveInvasora(int posX,int posY) {
    	NaveInvasora naveInvasora1 = new NaveInvasora(posX,posY);
        navesInvasoras.add(naveInvasora1);
    	
    }
    
    public void crearBloqueMuros() {
    	int aux1 = ANCHOPANTALLA / 9;
    	int aux2 = 350; 
    	int posActual = aux1;
    	for (int i=1;i<=4;i++) {
    		crearMuro(posActual,aux2);
    		posActual = posActual + (aux1*2);
    	}
    }


    public void crearMuro(int posX,int posY) {
    	Muro muroNuevo = new Muro(posX,posY);
    	muros.add(muroNuevo);
    }
    
    public int disparoInvasor() {
    	int subindice = (int) Math.floor(Math.random()*14+1);
    	int aux = -1;
    	while (navesInvasoras.get(subindice).getVidaNave() == false) {
    		subindice = (int) Math.floor(Math.random()*14+1);
    	}
    
    	navesInvasoras.get(subindice).disparoInvasor();
    	aux = subindice;
    	
    	return aux;
    	
    }

    public void incrementarNumeroNivel() {
    	numeroNivel+=1;
    }


    public boolean getEstado() {
        return this.estado;
    }
    
    public NaveInvasora buscarNaveInvasora(int nroNave) {
    	for (NaveInvasora aux : navesInvasoras) {
    		if (aux.soyEsaNave(nroNave)) {
    			return aux;
    		}
    	}
    	return null;
    }
    
    public Muro buscarMuro(int id) {
    	for (Muro aux : muros) {
    		if (aux.soyEseMuro(id)) {
    			return aux;
    		}
    	}
    	return null;
    }
    
    public Bateria getBateriaActual() {
    	return this.bateria;
    }
    
    
    /** @Tarea: Verifica si un proyectil lanzado por la bateria impactó en el muro **/
    public boolean verificoImpactoEnMuro() {
    	boolean band = false;
    	for (Muro muroActual : muros) {
    		if (muroActual.recibiImpacto(this.bateria.getProyectil().getPosicionX(), this.bateria.getProyectil().getPosicionY(),this.bateria.getIdentificador())) {
    			band = true;
    		}
    	}
    	return band;
    }
    
    public void actualizarVidaInvasoraEspecifica(int pos) {
    	navesInvasoras.get(pos).destruirNave();
    }
    
    
    /** @Tarea: Devuelve la posicion del muro impactado, -1 si no le pego a nada **/
    public int impactaProyectilEnMuro() {
    	int pos = -1;
    	int cont=0;
    	for (Muro muroActual : muros) {
    		if (muroActual.recibiImpacto(this.bateria.getProyectil().getPosicionX(), this.bateria.getProyectil().getPosicionY(),this.bateria.getIdentificador())) {
    			muroActual.disminuirVidaMuro(this.bateria.getIdentificador());
    			pos = cont;
    		}
    		cont+=1;
    	}
    	return pos;
    	
    }
    
    public boolean verificoImpactoProyectilInvasorEnMuro() {
    	boolean band = false;
    	for (Muro muroActual1 : muros) {
    		for (NaveInvasora naveActual1 : navesInvasoras) {
    			if (naveActual1.getProyectilInvasor()!=null) {
    				if (muroActual1.recibiImpacto(naveActual1.getProyectilInvasor().getPosicionX(), naveActual1.getProyectilInvasor().getPosicionY(),naveActual1.getIdentificador())) {
    					band = true;
            		}
    			}	
    		}
    	}
    	return band;
    }
    
    public int impactaProyectilInvasorEnMuro() {
    	int pos = -1;
    	int cont=0;
    	for (Muro muroActual1 : muros) {
    		for (NaveInvasora naveActual1 : navesInvasoras) {
    			if (naveActual1.getProyectilInvasor() != null) {
    				if (muroActual1.recibiImpacto(naveActual1.getProyectilInvasor().getPosicionX(), naveActual1.getProyectilInvasor().getPosicionY(), naveActual1.getIdentificador())) {
            			pos=cont;
    					muroActual1.disminuirVidaMuro(naveActual1.getIdentificador());
    					naveActual1.borrarProyectil();
            		}
    			}       		
    		}
    		cont+=1;
    	}
    	return pos;
    }
    
    /** @Tarea: Devuelvo la posicion de una nave atacada por un proyectil de la bateria **/
    public int obtengoInvasoraAtacada() {
    	int pos=-1,cont=0;
    	for (NaveInvasora invasoraActual : navesInvasoras) {
    		if (invasoraActual.recibiImpacto(this.bateria.getProyectil().getPosicionX(), this.bateria.getProyectil().getPosicionY())) {
    			pos=cont;
    		}
    		cont+=1;
    	}
    	return pos;
    }
    
    
    /** @Tarea: Verifica si impacto un proyectil en alguna nave invasora **/
    public boolean verificoImpactoProyectilEnInvasora() {
    	boolean band=false;
    	for (NaveInvasora invasoraActual : navesInvasoras) {
    		if (invasoraActual.recibiImpacto(this.bateria.getProyectil().getPosicionX(), this.bateria.getProyectil().getPosicionY())) {
    			band=true;
    		}
    	}
    	return band;
    }
    
    public int getNivel() {
    	return numeroNivel;
    }
    
    public ArrayList<NaveInvasora> getNavesInvasoras(){
    	return (ArrayList<NaveInvasora>) navesInvasoras;
    }
    
    public ArrayList<Muro> getMuros(){
    	return (ArrayList<Muro>) muros;
    }
    
    public int getAlturaPantalla() {
    	return ALTURAPANTALLA;
    }
    
    public int getAnchoPantalla() {
    	return ANCHOPANTALLA;
    }
}