package principal.statemachine;

import java.awt.Graphics2D;
import principal.Constant;
import principal.statemachine.gamestate.*;
import principal.statemachine.init.*;

public class GameStatus implements GameState{
	
	public static GameState[] states;
	public static GameState actualState;
	
	
	public GameStatus(){
		initState();
		initActualState();
	}


	private void initState() {
		states = new GameState[Constant.STATES];
		states[0] = new PrincipalMenu();
		states[1] = GameManager.getGameManager();
		states[2] = new PauseMenu();
		states[3] = new ScoreMenu();
		states[4] = new GameRules();
		states[5] = new Win();
		states[6] = new Shop();
	}

	
	private void initActualState(){
		actualState = states[0];
	}

	
	public static void changeState(int i) {
		actualState = states[i];
	}
	
	
	@Override
	public void update(long time) {
		actualState.update(time);
	}


	@Override
	public void render(Graphics2D g, long time) {
		actualState.render(g, time);
	}
	
	
	public GameState getActualState(){
		return actualState;
	}

	
	
}

