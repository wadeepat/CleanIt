package principal.statemachine.characterstates.playerstates;

import java.awt.Image;

import principal.Game;
import principal.statemachine.characterstates.State;


public class Cleaning extends State{
	
	private final static Cleaning CLEANING = new Cleaning();
	
//	private String[] paths = {
//		"images/felix/fixing/0.png",
//		"images/felix/fixing/1.png"
//	};
	
	private Cleaning() {
		animUpdate = 300;
	}
	
	public static Cleaning getFixing(){
		return CLEANING;
	}
	
		
	@Override
	public Image getImage(int dir) {
		if (dir == -1){
			animation = Game.animations.getPlayerCleaningLeft();
		}else
			animation = Game.animations.getPlayerCleaningRight();
		return animation.getActualFrame();
	}


	
	
}
