package principal.statemachine.gamestate;

import java.awt.Graphics2D;

import principal.Constant;
import principal.Game;
import principal.HUD;
import principal.Handler;
import principal.Level;
import principal.Score;
import principal.entities.Building;
import principal.entities.creatures.Player;
//import principal.entities.creatures.Ralph;
import principal.entities.creatures.monster;
import principal.graphics.DrawingSurface;
import principal.graphics.Sprite;
import principal.input.KeyBoard;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;

public class GameManager implements GameState {
	
	public static boolean showHitBox;
	
	private Handler handler;
	
	private Building b;
	
	private Player player;
	private principal.entities.creatures.monster monster;

	private Sprite bush;
	
	private long timing;
	
	private static boolean chooseLevel;

	private static GameManager gm = new GameManager();
	
	private GameManager() {
		handler = new Handler();
		
		b = Building.getBuilding();

		player = new Player(Constant.WIDTH/2 , Constant.HEIGHT -100);

		HUD.getHud().setFelix(player);

		chooseLevel = false;
		
		monster = new monster(300,240);
	
		bush = Game.animations.getBush();
		
	}
	
	
	public static GameManager getGameManager(){
		return gm;
	}
	
	
	@Override
	public void update(long time) {
		
		if (b.canChangeLevel()) {
			Win.setTiming(time);
			GameStatus.changeState(5);	
		}
		
		
		if (b.canChangeLevel()){
			nextLevel();
		}
		
		
		
		if (player.getLife() == 0) {
			Score.getScore().saveScore();
			GameStatus.changeState(3);
			player.resetAll(Constant.WIDTH/2 , Constant.HEIGHT -100);
		}
		
		
		handler.update(time);
		

		
		if (KeyBoard.pause){
			GameStatus.changeState(2);	
		}
		
	}

	
	@Override
	public void render(Graphics2D g, long time) {
		drawBushes(g);
		handler.draw(g, time);
	}

	
	private void drawBushes(Graphics2D g) {
		int bushPosX = 0;
		for (int i = 0; i < 33; i++) {
			g.drawImage(bush.getImage(), 0 + bushPosX, Constant.HEIGHT - 45, null);
			bushPosX += bush.getWidth();
		}
	}

	public static int getLevel(){
		return Level.getLevel().getActualLevel();
	}

	public void resetGameManager() {
		b.resetBuilding();
		if (!chooseLevel){
			Level.getLevel().resetGame();
		}
		
		monster.reset(300 ,240);
		player.resetAll(Constant.WIDTH/2 , Constant.HEIGHT - 100);
		HUD.getHud().setFelix(player);
		DrawingSurface.resetSurface();
		Handler.removeAll();
	}
	
	
	public void nextLevel(){
		b.resetBuilding();
		monster.reset(300 ,240);
		player.resetAll(Constant.WIDTH/2 , Constant.HEIGHT -100);
		DrawingSurface.resetSurface();
		Level.getLevel().levelUp();
		monster.setVelocity(Level.getLevel().getMonsterVel());
		monster.setVomitTime(Level.getLevel().getMonsterTime());
		HUD.getHud().reset();
		Handler.removeAll();
	}

	public static void setChoose(boolean choose){
		chooseLevel = choose;
	}
	
}
