package principal.statemachine.characterstates.playerstates;

import java.awt.Image;

import principal.Game;
import principal.statemachine.characterstates.State;

public class Immune extends State{
	
private final static Immune inmmune = new Immune();
	
	private Immune() {
		animation  = Game.animations.getPlayerMoveRight();
		animUpdate = 2;
	}
	
	public static Immune getImmune(){
		return inmmune;
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
