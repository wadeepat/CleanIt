package principal;

import principal.graphics.*;
import principal.statemachine.GameStatus;

public class Game {

	public static boolean inGame;

	private Window window;
	private DrawingSurface drawingSurface;
	private GameStatus gameStatus;
	
	private long time;
	
	public static int  fps = 0;
	public static int  tps = 0;
	
	public static Images animations;
	
	private static Game game = new Game();
	
	private Game() {
		animations = new Images("lama");
		inGame = true;
		drawingSurface = new DrawingSurface();
		window = new Window("Clean It.", drawingSurface);
		gameStatus = new GameStatus();	
	}
	
	
	public static Game getGame() {
		return game;
	}
	
	public void startGame(){
		Score.getScore().readFromFile();
		loopGame();
	}

	private void loopGame() {
		
		int framesAmount = 0;
		int ticksAmount = 0;
		
		int NS_PER_SECOND = 1000000000;
		int TPS_OBJ = 60;
		int NS_PER_TICK = NS_PER_SECOND/TPS_OBJ;
		
		long counterReference = System.nanoTime();
		long updateReference = System.nanoTime();
		
		double elapsedTime;
		double delta = 0;
		
		while (inGame)
		{		
			time = System.currentTimeMillis();
			final long startLoop = System.nanoTime();
			elapsedTime = startLoop - updateReference;
			
			updateReference = startLoop;
			
			delta += elapsedTime/NS_PER_TICK;
			
			while (delta >= 1){
				update(time);
				ticksAmount++;
				delta--;
			}
			
			render(time);
			framesAmount++;
			
			if (System.nanoTime() - counterReference > NS_PER_SECOND){
				
				fps = framesAmount;
				tps = ticksAmount;

				framesAmount = 0;
				ticksAmount = 0;
				
				counterReference = System.nanoTime();
			}	
		}	
		System.exit(1);
	}


	private void update(long time) {
		gameStatus.getActualState();
		if(!Score.getScore().askName()){
			gameStatus.update(time);
			drawingSurface.update();
		}
	}

	
	private void render(long time) {
		drawingSurface.render(gameStatus, time);
	}
	
	
	public static void quitGame(){
		inGame = false;
	}
	
}
