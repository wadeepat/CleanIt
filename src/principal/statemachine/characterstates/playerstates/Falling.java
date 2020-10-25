package principal.statemachine.characterstates.playerstates;

import java.awt.Image;

import principal.Game;
import principal.statemachine.characterstates.State;


public class Falling extends State{

//	private String[] paths = {
//		"images/felix/falling/0.png",
//		"images/felix/falling/1.png"
//	};

	private static Falling falling = new Falling();
	
	private Falling() {
		animation  = Game.animations.getPlayerFalling();
		animUpdate = 300;
	}
	
	public static State getFalling(){
		return falling;
	}
	
	@Override
	public Image getImage(int dir) {
		return animation.getActualFrame();
	}	
	
}
