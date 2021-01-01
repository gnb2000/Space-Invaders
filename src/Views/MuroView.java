package Views;


public class MuroView {
	
    private int vidaMuro;
    private int posicionX;
    private boolean estadoMuro;

    public MuroView(boolean estadoMuro, int posicionX, int posicionY, int vidaMuro, int id) {
        super();
    	this.estadoMuro = estadoMuro;
        this.posicionX = posicionX;
        this.vidaMuro = vidaMuro;
    }
    
    public int posicionX() {
    	return this.posicionX;
    }
    
    public boolean getVida() {
    	return this.estadoMuro;
    }
    
    public int cantVidas() {
    	return this.vidaMuro;
    }
    
}
