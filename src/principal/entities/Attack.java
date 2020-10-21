package principal.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import principal.Handler;

public class Attack extends Entity{

	private long attackTime;
	
	public Attack(float x, float y) {
		super(x, y);
		id = ID.Attack;
		Handler.add(this);
		attackTime = System.currentTimeMillis();
	}

	@Override
	public void draw(Graphics2D g, long time) {
	}

	@Override
	public void tick(ArrayList<Entity> objects, long beforeTime) {
		if (beforeTime - attackTime > 500){
			Handler.remove(this);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX(), (int)getY(), 20, 20);
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
		return new Rectangle(0,0,0,0);
	}



}
