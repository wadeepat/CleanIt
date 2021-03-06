package principal.statemachine.characterstates.playerstates;

import java.awt.Image;

import principal.Game;
import principal.statemachine.characterstates.State;


public class Moving extends State{
	
	private final static Moving moving = new Moving();
	
	
	
	private Moving() {
		animation  = Game.animations.getPlayerMoveRight();
		animUpdate = 200;
	}
	
	public static Moving getMoving(){
		return moving;
	}
	
		
	@Override
	public Image getImage(int dir) {
		if (dir == -1){
			animation = Game.animations.getPlayerMoveLeft();
		}else
			animation = Game.animations.getPlayerMoveRight();
		return animation.getActualFrame();
	}
	
	
	

}

