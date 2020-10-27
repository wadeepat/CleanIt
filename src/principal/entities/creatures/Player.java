package principal.entities.creatures;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import principal.Constant;
import principal.Handler;
import principal.Score;
import principal.entities.Building;
import principal.entities.Entity;
import principal.entities.ID;
import principal.entities.windows.Window;
import principal.input.KeyBoard;
import principal.statemachine.characterstates.playerstates.Falling;
import principal.statemachine.characterstates.playerstates.Cleaning;
import principal.statemachine.characterstates.playerstates.Immune;
import principal.statemachine.characterstates.playerstates.Moving;
import principal.statemachine.characterstates.playerstates.Normal;

public class Player extends Creature {

	private final float JUMP_SPEED = -10f;
	private final float MAX_JUMP = -80;
	private final float VEL = 3f;
	private final long IMMUNE_DEATH = 2000;

	private long immuneDuration;
	private long movDelay = System.currentTimeMillis();
	private long delay = System.currentTimeMillis();
	private long inmTime;

	private float max_jump = 0;
	private float death_x;
	private float death_y;
	
	private boolean dying;
	private boolean onObstacle;
	private boolean onGround;
	private boolean falling;
	private boolean isImmune;
	
	private int life;
	
	
	public Player(float x, float y) {
		super(x, y);
		life = 3;
		state = Normal.getNormal();
			
		onObstacle = false;
		onGround = false;
		falling = false;
		isImmune = false;
		
		directionX = 1;
		id = ID.Player;
		
		width = 20;
		height = 50;
		
		Handler.add(this);
	}	

	public void update(ArrayList<Entity> ent, long beforeTime) {
		
		if (!dying){
			stopFalling();
			checkImmune(beforeTime);
			checkButtons(ent, beforeTime);
			collision(ent, beforeTime);
		}else{
			setY(getY() + 3f);
			if (Building.getBuilding().getActualSector().getBotBounds().y + 100 < getY()){
				dying = false;
				reset(death_x,death_y);
			}
		}
		checkStates();
	}

	private void checkButtons(ArrayList<Entity> ent, long beforeTime) {
		setDx(getInputX(ent));
		setX(getX() + getDx());
			    
		setDy(getInputY(ent,beforeTime));
		setY(getY() + getDy());
	}


	private void checkImmune(long beforeTime) {
		if (isImmune) {
			if (beforeTime - inmTime > immuneDuration) {
				isImmune = false;
			}
		}
	}

		
	private void checkStates() {
		
		if (dying) {
			state = Falling.getFalling();
		}
		
		if (KeyBoard.clean && onGround && !dying) {
			state = Cleaning.getFixing();
		}

		if (isImmune && !KeyBoard.clean && !dying){
			state = Immune.getImmune();
		}
		
		if (getDx() == 0 && getDy() == 0 && !KeyBoard.clean && !isImmune && !dying) {
			state = Normal.getNormal();
		}
			
		
		if ((getDx() != 0 || getDy() != 0) && !isImmune) {
			state = Moving.getMoving();
		}
	}
	
	
	private void collision(ArrayList<Entity> ent, long beforeTime) {
		buildingCollision();
		windowCollision(beforeTime);
		
		for (int i = 0; i < ent.size(); i++) {
			Entity e = ent.get(i);
			monsterCollision(e);
			vomitCollision(e, beforeTime);
			birdCollision(e, beforeTime);
			attackCollision(e, beforeTime);
		}
	}
	

	
	private void windowCollision(long beforeTime) {	
		Window[] windows = Building.getBuilding().getActualWindows();
		for (int i = 0; i < windows.length; i++) {
			Window w = windows[i];

			if(w.getBounds().contains(getBounds()) && KeyBoard.clean && beforeTime - movDelay > 300) {
				movDelay = System.currentTimeMillis();
				w.removeBadlander();
				w.getCleaned();
			}
			
			if (getBotBounds().intersects(w.getBotBounds()) && !w.hasFlowerPot()) {
				onGround = true;
				onObstacle = false;
			}
			
			doubleDoorsCollision(w);
			obstacleCollision(w);
		}  
	}
	
	
	 private void obstacleCollision(Window w) {
		 		 
		  if(getTopBounds().intersects(w.getTopBounds()) && w.hasRoof()){
			  setY(w.getY() +2);
		  }
		  
		  if(getBotBounds().intersects(w.getTopBounds()) && w.hasRoof()){
			  onGround = true;
			  onObstacle = true;
		  }

		  if(getBotBounds().intersects(w.getBotBounds()) && w.hasFlowerPot()){
			  
			  onGround = true;
			  onObstacle = true;
		  }
	}
	

	private void doubleDoorsCollision(Window w) {
		if (getLeftBounds().intersects(w.getLeftBounds())) {
			setX(w.getX() - 6);
		}
		
		if (getRightBounds().intersects(w.getLeftBounds())) {
			setX(w.getX() - 19);
		}
		
		if (getLeftBounds().intersects(w.getRightBounds())) {
			setX(w.getX() + 27);
		}
		
		if (getRightBounds().intersects(w.getRightBounds())) {
			setX(w.getX() + 12);
		}
		
		if (getBotBounds().intersects(w.getRightBounds()) || getBotBounds().intersects(w.getLeftBounds())) {
			onObstacle = true;
			onGround = true;
		}
	}
	
	
	
	private void vomitCollision(Entity e, long beforeTime) {
		if (e instanceof Vomit) {
			if(getTopBounds().intersects(e.getBounds()) && !isImmune) {
				setY(getY());
				savePosition(e);
				decLife(beforeTime);
				Handler.remove(e);
				setImmune(IMMUNE_DEATH);
			}
		}
	}

	private void birdCollision(Entity e, long beforeTime) {
		if (e instanceof Bird) {
			if(getAllBounds().intersects(e.getBounds()) && !isImmune) {	
				setY(getY());
				savePosition(e);
				decLife(beforeTime);
				Handler.remove(e);
				setImmune(IMMUNE_DEATH);
			}
		}
	}

	private void monsterCollision(Entity e) {
		if (e instanceof monster){
			if (getTopBounds().intersects(e.getBounds())) {
				setY(e.getY() + 82);
				max_jump = MAX_JUMP;
			}
		}
	}

	
	private void attackCollision(Entity e, long beforeTime) {
		if (e.getID() == ID.Attack) {
			if (getAllBounds().intersects(e.getBounds())) {
				setY(getY());
				savePosition(e);
				decLife(beforeTime);
				Handler.remove(e);
				setImmune(IMMUNE_DEATH);
			}
		}
	}
	
	
	private void setImmune(long immuneTime) {
		inmTime = System.currentTimeMillis();
		isImmune = true;
		immuneDuration = immuneTime;
	}
	
	
	private void savePosition(Entity e) {
		death_x = e.getX() - 10;
		death_y = e.getY() - 10;
	}
	
	
	private void buildingCollision() {
		
		Building b = Building.getBuilding();
		
		
		if (getLeftBounds().intersects(b.getLeftBounds())){
			setX(Building.getBuilding().getX() + 9);
		}
		
		
		if (getRightBounds().intersects(b.getRightBounds())){
			setX(b.getX() + 279);
		}
		
		
		if (getBotBounds().intersects(b.getTopBounds()) && b.isChangingSector()) {
			Building.getBuilding().changeSector();
			onGround = true;
			isImmune = false;
		}
		
		
		if (getBotBounds().intersects(b.getBotBounds()) ){		
			onGround = true;
		}else 
			onGround = false;
		
	}

		
	
	
	private float getInputX(ArrayList<Entity> ent) {
		if (KeyBoard.right && !KeyBoard.clean) {
			directionX = 1;
			return VEL;
		}

		if (KeyBoard.left && !KeyBoard.clean){
			directionX = -1;
			return -VEL;
		}
		return 0;
	}
		
		
		
	private float getInputY(ArrayList<Entity> ent, long beforeTime) {

		if (KeyBoard.up && !falling && max_jump > MAX_JUMP && beforeTime - movDelay > 150) {
			directionY = -1;
			max_jump += JUMP_SPEED;
				
			return JUMP_SPEED;
		}

		if (KeyBoard.down && onGround && !onObstacle && getY() < 503 && beforeTime - movDelay > 100
				&& !getBotBounds().intersects(Building.getBuilding().getBotBounds())) {
			movDelay = System.currentTimeMillis();
			directionY = 1; 

			return Constant.GRAVITY;
		}
			
		if (!onGround) {
			movDelay = System.currentTimeMillis();
			falling = true;
			onObstacle = false;
			return Constant.GRAVITY;	
		}
		return 0;
	}
		

	private void  stopFalling() {
		if(onGround && falling) {
			falling = false;
			max_jump = 0;
		}
	}

	
	private void decLife(long beforeTime) {
		dying = true;
		if (!isImmune){
			if (beforeTime - delay > 20) {
				delay = System.currentTimeMillis();
				life--;
				if (life > 0)	
					Score.getScore().loseHP();
			}
		}
	}

	@Override
	public void render(Graphics2D g, long time) {
		state.update();
		g.drawImage(state.getImage(directionX), (int)getX(), (int)getY(), null);
	}

	public int getLife() {
		return life;
	}

	@Override
	public Rectangle getTopBounds() {
		return new Rectangle((int)getX() + 12, (int)getY(), 15, 3);
	}

	@Override
	public Rectangle getLeftBounds() {
		return new Rectangle((int)getX() + 7 ,(int)getY() + 6 , 3, 39);
	}

	@Override
	public Rectangle getRightBounds() {
		return new Rectangle((int)getX() + 15, (int)getY() + 6, 3, 39);
	}

	@Override
	public Rectangle getBotBounds() {
		if (directionX == -1){
			return new Rectangle((int)getX() + 12, (int)getY() + 52, 12, 2);
		}
		return new Rectangle((int)getX() + 6, (int)getY() + 52, 12, 2);
	}

	
	public Rectangle getAllBounds(){
		if (directionX == -1){
			return new Rectangle((int)getX() + 10, (int)getY(), width, height);
		}
		return new Rectangle((int)getX(), (int)getY(), width, height);
	}
	
	
	@Override
	public Rectangle getBounds() {
		if (directionX == -1){
			return new Rectangle((int)getX() + 6, (int)getY() + 30, 7, 7);
		}
		return new Rectangle((int)getX() + 26, (int)getY() + 30, 7, 7);
	}

	public void resetAll(float x, float y){
		setXY(x,y);
		life = 3;
		isImmune = false;
		dying = false;
	}

	public void reset(float x, float y) {
		setXY(x,y);
	}
}
