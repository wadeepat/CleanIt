package principal.statemachine.characterstates.monsterstates;

import java.awt.Image;

import principal.Game;
import principal.statemachine.characterstates.State;


public class Flying extends State{

//	private String[] ralphClimbing = {
//	"images/ralph/Climbing/0.png",
//	"images/ralph/Climbing/1.png",
//	};	
	private static Flying climb = new Flying();


	private Flying(){
		animation = Game.animations.getFlying();
		animUpdate = 600;
	}
	
	public static Flying getFlying(){
		return climb;
	}
	
	@Override
	public Image getImage(int dir) {
		return animation.getActualFrame();
	}

	
}
