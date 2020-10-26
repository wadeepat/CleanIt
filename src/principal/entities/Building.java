package principal.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import principal.statemachine.gamestate.GameManager;
import principal.statemachine.sectorstates.*;

import java.util.ArrayList;

import principal.Constant;
import principal.Game;
import principal.Handler;
import principal.Score;
import principal.entities.creatures.Bird;
import principal.entities.windows.Window;
import principal.graphics.Sprite;
import principal.util.Random;

public class Building extends Entity{ 
	
	private final static int BUILDING_WIDTH = 315;
	private final static int BUILDING_HEIGHT = 1065;
	
	public final static int POS_X = Constant.WIDTH/2 - BUILDING_WIDTH/2;
	public final static int POS_Y = Constant.HEIGHT - BUILDING_HEIGHT + 10;

	private long time = System.currentTimeMillis();

	private boolean globalMovement = false;
	
	private badlander badlander;
	private long badlanderDelay;
	private long waitForBad;
	
	private boolean birdInit;
	private Bird bird;

	
	private static Building building =  new Building();
	
	private Sprite sprite; 
	
	private Sector[] sectors;
	private int actualSector;
	private int previousSector;
	
	
	private Building() {
		super(POS_X, POS_Y);
		sprite = Game.animations.getBuilding();
		id = ID.Building;
		
		birdInit = true;
		waitForBad = 7000;
		
		sectors = new Sector[Constant.SECTORS];
		
		initSectors();
		initActualSectors();
	}
	
	
	public void initSectors() {
		sectors[0] = new FirstSector();
		sectors[1] = new SecondSector();
		sectors[2] = new ThirdSector();
		sectors[3] = new FourthSector();
 	}
	
	
	public void initActualSectors() {
		actualSector = 0;
		previousSector = 0;
	}
	
	public static Building getBuilding() {
		return building;
	}
	
	
	@Override
	public void update(ArrayList<Entity> creature, long beforeTime) {
		sectors[actualSector].tick(beforeTime);
		if(isChangingSector()) globalMovement = true;		
		generateBird();
		generateBadlander(beforeTime);
	}

	
	private void generateBird() {
		if (getActualSector().hasBirds()) {
			if (birdInit) {
				float altitude = Random.value(getBotBounds().y - 50, getTopBounds().y + 50);
				bird = new Bird(0, altitude, true);
				birdInit = false;
			}
			
			if (isChangingSector()) {
				Handler.remove(bird);
				birdInit = true;
			}
		}
	}
	
	
	private void generateBadlander(long beforeTime) {
		Window[] windows = getActualWindows();
		Window w;
		if ((System.currentTimeMillis()-time)/1000 >= 5) {
			for (int i = 0; i < windows.length; i++) {
				w = windows[i];
				initNicePosition(w, beforeTime);
				time = System.currentTimeMillis();
			}
		}

	}
	
	
	private void initNicePosition(Window w, long beforeTime) {
		if (getActualSector().hasBadlander()) {
			if (beforeTime - badlanderDelay > waitForBad) {
				waitForBad -= 1500;
				if (w.getStrokesRequired() >= 2 && w.getStrokesRequired() <= 4 && w.getID() != ID.DoubleDoor) {
						if (Random.boolValue(5)) {
							waitForBad = 10000;
							badlander = new badlander(w.getX(),w.getY());
							w.setBadlander(badlander);
						}
				}
			}
		}
	}
	
	public void draw(Graphics2D g, long time) {
		g.drawImage(sprite.getImage(), POS_X, POS_Y, null);
		
		g.setColor(Color.GREEN);
		if (GameManager.showHitBox){
			g.draw(getLeftBounds());
			g.draw(getRightBounds());
			g.draw(getBotBounds());
			g.draw(getTopBounds());
		}
		
		
		sectors[0].draw(g, time);
		sectors[1].draw(g, time); 
		sectors[2].draw(g, time);
		sectors[3].draw(g, time);
	}
	
	
	public void changeSector() {
		previousSector = actualSector;
		actualSector++;
		badlanderDelay = System.currentTimeMillis();
		Score.getScore().nextSector();
	}
	
	public void stopGM(){
		globalMovement = false;
	}
	
	
	public boolean getGM(){
		return globalMovement;
	}
	
	
	public Window[] getActualWindows() {
		return sectors[actualSector].getWindows();
	}
	
//	public Window[] getPreviousWindows() {
//		return sectors[previousSector].getWindows();
//	}
	
	
	public Rectangle getBounds(){
		return null;
	}


	@Override
	public Rectangle getTopBounds() {
		return sectors[actualSector].getTopBounds();
	}

	
	@Override
	public Rectangle getLeftBounds() {
		return new Rectangle(POS_X + 11, POS_Y, 3, 1000);
	}

	
	@Override
	public Rectangle getRightBounds() {
		return new Rectangle(POS_X + 298, POS_Y, 3, 1000);
	}

	
	@Override
	public Rectangle getBotBounds() {
		return sectors[actualSector].getBotBounds();
	}


	
	public boolean isChangingSector() {
		return sectors[actualSector].changeSector();
	}
	

	public int getIndexActualSector() {
		return actualSector;
	}
	
	public Sector getActualSector() {
		return sectors[actualSector];
	}
	
	public Sector getSector(int i) {
		return sectors[i];
	}
	
	public boolean canChangeLevel() {
		return isChangingSector() && actualSector == 3;
	}
	
	public void resetBuilding(){
		initSectors();
		actualSector = 0;
		previousSector = 0;
		stopGM();
	}


	
}