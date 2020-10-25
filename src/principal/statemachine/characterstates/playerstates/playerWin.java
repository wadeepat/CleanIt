package principal.statemachine.characterstates.playerstates;

import java.awt.Image;

import principal.Game;
import principal.statemachine.characterstates.State;

public class playerWin extends State{

	private static playerWin playerWin = new playerWin();
	
	private playerWin() {
		animation = Game.animations.getPlayerWin();
		animUpdate = 800;
	}
	
	
	public static playerWin getPlayerWin(){
		return playerWin;
	}

	@Override
	public Image getImage(int dir) {
		return animation.getActualFrame();
	}

}
