package principal.statemachine.sectorstates;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import principal.Constant;
import principal.entities.Building;
import principal.entities.windows.Window;

public abstract class Sector {

	protected final int COL = 5;
	protected final int ROW = 3;
	
	protected final static int POS_X = Building.POS_X;
	protected final static int POS_Y = Building.POS_Y;

	protected final int MAX_OBSTACLES = 3;
	protected int obsCounter;
	
	protected Window[] windows;
	protected ArrayList<Window> brokenWindows;
	
	public Sector() {
		brokenWindows = new ArrayList<Window>();
		obsCounter = 0;
	}
	
	public abstract void update(long beforeTime);
	public abstract void render(Graphics2D g, long time);
	
	public abstract boolean  hasBirds();
	public abstract boolean hasBadlander();
	
	public abstract Rectangle getTopBounds();
	public abstract Rectangle getBotBounds();
	
	public Window[] getWindows() {
		return windows;
	}
	
	public boolean changeSector() {
		return brokenWindows.size() <= 0;
	}

	
}
