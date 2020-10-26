package principal.entities;

import java.awt.*;
import java.util.ArrayList;

import principal.Handler;
import principal.graphics.Animation;
import principal.statemachine.characterstates.badlander.BadlanderAnimation;


public class badlander extends Entity  {
	
	private final long actionsDelay;
	private Attack attack;

	public badlander(float x, float y) {
		super(x,y);
		state = BadlanderAnimation.getBadlander();
		actionsDelay = System.currentTimeMillis();
		Handler.add(this);
	}


	@Override
	public void draw(Graphics2D g, long time) {
		state.update();
		g.drawImage(state.getImage(0), (int)getX(), (int)getY(), null);
	}

	
	@Override
	public void update(ArrayList<Entity> objects, long beforeTime) {
		if (beforeTime - actionsDelay > 1000) {
				attack = new Attack((int)getX(),(int)getY());
				Handler.remove(this);
		}
	}

	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(0,0,0,0);
	}
	

	@Override
	public Rectangle getTopBounds() {
		return new Rectangle(0,0,0,0);
	}
	

	@Override
	public Rectangle getLeftBounds() {
		return new Rectangle(0,0,0,0);
	}
	

	@Override
	public Rectangle getRightBounds() {
		return new Rectangle(0,0,0,0);
	}
	

	@Override
	public Rectangle getBotBounds() {
		return new Rectangle (0,0,0,0);
	}

}
