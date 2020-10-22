package principal.entities.creatures;

import principal.Handler;
import principal.entities.Entity;
import principal.entities.ID;
import principal.physics.Displacement;

public abstract class Creature extends Entity{

	private Displacement displacement;
	
	protected int directionX;
	protected int directionY;
	
	protected float vel; 
	
	
	protected ID id;
	
	public Creature(float x, float y) {
		super(x,y);
		displacement = new Displacement();
		
	}

	public float getDx(){
		return displacement.getDx();
	}
		
	public float getDy(){
		return displacement.getDy();
	}

	public void setDx(float dx){
		displacement.setDx(dx);
	}
	
	public void setDy(float dy){
		displacement.setDy(dy);
	}

	public void setVelocity(float vel) {
		this.vel = vel;
	}

}
