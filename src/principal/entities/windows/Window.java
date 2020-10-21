package principal.entities.windows;

import principal.Handler;
import principal.entities.Entity;
import principal.entities.badlander;

public abstract class Window extends Entity{

	protected int strokesRequired;
	
	protected boolean hasFlowerPot;
	protected boolean hasRoof;
	
	protected badlander badlander;
	
	public Window(float x, float y) {
		super(x, y);
		badlander = null;
	}

	public int getStrokesRequired(){
		return strokesRequired;
	}
	
	
	public abstract String getName();
	
	
	public void getFixed() {
		if (isBroken()){
			strokesRequired--;
		}
	}
	
	
	public boolean isBroken() {
		return strokesRequired > 0;
	}
	
	
	public boolean hasFlowerPot(){
		return hasFlowerPot;
	}
	
	public boolean hasRoof(){
		return hasRoof;
	}
		
	
	public void setBadlander(badlander badlander){
		this.badlander = badlander;
	}
	
	
	public void removeNicelander(){
		if (badlander != null){
			Handler.remove(badlander);
		}
	}

}
	

