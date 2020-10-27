package principal.entities.creatures;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import principal.Handler;
import principal.Level;
import principal.entities.Building;
import principal.entities.Entity;
import principal.entities.ID;
import principal.statemachine.characterstates.monsterstates.Flying;
import principal.statemachine.characterstates.monsterstates.Demolishing;
import principal.statemachine.characterstates.monsterstates.Move;

public class monster extends Creature {

	private float FLYING = 3.0f;
	private long DELAY_PER_VOMIT = 5000;
	private int floor;
	private long time = System.currentTimeMillis();
	private long vomitTime;
	private boolean prevGM;

	public monster(float x, float y) {
		super(x, y);
		id = ID.Ralph;
		state = Move.getMove();

		vel = Level.getLevel().getMonsterVel();
		vomitTime = Level.getLevel().getMonsterTime();

		setDx(vel);
		prevGM = false;

		width = 55;
		height = 42;

		floor = 0;
		Handler.add(this);
	}


	@Override
	public void render(Graphics2D g, long time) {
		state.update();
		g.drawImage(state.getImage(0),(int)getX()-20, (int)getY() + 20, null);

	}


	@Override
	public void update(ArrayList<Entity> ent, long elapsedTime) {

		if (elapsedTime - time > 1500  || Building.getBuilding().isChangingSector()){
			if (Building.getBuilding().getGM()) {
				flying(floor);
				prevGM = true;
				if (getY() == floor - 1)
				moving(elapsedTime);
			}else {
				moving(elapsedTime);
				if(prevGM){
					floor = floor - 238;
					prevGM = false;
				}
			}
		}
	}


	private void flying(int floor){
		state = Flying.getFlying();
		setDy(-FLYING);
		if (getY() > floor ){
			setY(getY() + getDy());
		}
	}

	private void moving(long elapsedTime){

		state = Move.getMove();
		setX(getX() + getDx());

		if (getBounds().intersects(Building.getBuilding().getLeftBounds())){
			setDx(vel);
		}else
			if (getBounds().intersects(Building.getBuilding().getRightBounds()))
				setDx(-vel);

		if (elapsedTime - time > vomitTime) {

			time = System.currentTimeMillis();
			state = Demolishing.getDemolishing();
			throwVomit();
		}

	}


	private void throwVomit() {
		Handler.addVomit(getX()-5, getY() + 50);
		Handler.addVomit(getX()+20, getY() + 50);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)getX(), (int)getY(), width, height);
	}

	@Override
	public Rectangle getTopBounds() {
		return null;
	}

	@Override
	public Rectangle getLeftBounds() {
		return null;
	}

	@Override
	public Rectangle getRightBounds() {
		return null;
	}

	@Override
	public Rectangle getBotBounds() {
		return null;
	}

	public void reset(float x, float y) {
		floor = 0;
		prevGM = false;
		setXY(x,y);
		vel = Level.getLevel().getMonsterVel();
		vomitTime = Level.getLevel().getMonsterTime();
	}

	public void setVomitTime(long time){
		vomitTime = time;
	}


}
