package Views;

public class NaveInvasoraView {

	private boolean vidaNave;
    private int posicionX;
    private int posicionY;

    public NaveInvasoraView(boolean vida, int posX, int posY, int nroNave) {
        super();
    	this.vidaNave = vida;
        this.posicionX = posX;
        this.posicionY = posY;
    }
    
    public NaveInvasoraView() {}
    
    public boolean getVidaNave() {
    	return this.vidaNave;
    }
    
    public int getPosicionX() {
    	return this.posicionX;
    }
    
    public int getPosicionY() {
    	return this.posicionY;
    }
}
