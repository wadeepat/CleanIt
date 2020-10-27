package principal.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import principal.Handler;
import principal.statemachine.characterstates.badlander.AttackAnimation;

public class Attack extends Entity{

	private final long attackTime;
	
	public Attack(float x, float y) {
		super(x, y);
		id = ID.Attack;
		Handler.add(this);
		attackTime = System.currentTimeMillis();
		state = AttackAnimation.getAttackAnimation();
	}

	@Override
	public void render(Graphics2D g, long time) {
		state.update();
		g.drawImage(state.getImage(0), (int)getX(), (int)getY(), null);
	}

	@Override
	public void update(ArrayList<Entity> objects, long beforeTime) {
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
