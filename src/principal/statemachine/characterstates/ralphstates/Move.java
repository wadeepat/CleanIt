package principal.statemachine.characterstates.ralphstates;

import java.awt.Image;

import principal.Game;
import principal.statemachine.characterstates.State;

public class Move extends State{

	private static Move move = new Move();
	
//	String[] paths = {
//		"images/ralph/Moving/0.png",
//		"images/ralph/Moving/1.png",
//	};
	
	
	private Move(){
		animation = Game.animations.getMonsterMove();
		animUpdate = 300;
	}
	
	public static Move getMove(){ 
		return move;
	}
	 
	@Override
	public Image getImage(int dir) {
		return animation.getActualFrame();
	}

			
	
	
}
