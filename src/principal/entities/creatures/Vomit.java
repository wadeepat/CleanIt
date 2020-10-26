package principal.entities.creatures;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import principal.Game;
import principal.Handler;
import principal.Level;
import principal.entities.Building;
import principal.entities.Entity;
import principal.entities.ID;
import principal.graphics.Animation;

public class Vomit extends Creature {
	
	private Animation vomit;
	
	private int actualSector; 
	
	public Vomit(float x, float y, int actualSector) {
		super(x,y);
		vomit = Game.animations.getVomit();
		vel = Level.getLevel().getVomitVel();
		this.actualSector = actualSector;
		id = ID.Vomit;
		vomit = Game.animations.getVomit();
		Handler.add(this);
	}


	@Override
	public void draw(Graphics2D g, long time) {
		vomit.update();
		g.drawImage(vomit.getActualFrame(), (int)getX(), (int)getY(), null);
		
//		g.draw(getBounds());
	}

	@Override
	public void update(ArrayList<Entity> creat, long BeforeTime) {
				
		Building b = Building.getBuilding();
		
		setY(getY() + vel);

		if (getY() > b.getSector(actualSector).getBotBounds().y + 100) {
			Handler.remove(this);
		}
	}

	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX(), (int)getY(), 18, 12);
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
