package principal.statemachine.characterstates.monsterstates;

import java.awt.Image;
import principal.Game;
import principal.statemachine.characterstates.State;

public class Demolishing extends State{

	
	private static Demolishing demolishing = new Demolishing();

	private Demolishing(){
		animation = Game.animations.getMonsterDemolition();
		animUpdate = 3000;
	}
	
	public static Demolishing getDemolishing(){
		return demolishing;
	}
	
	@Override
	public Image getImage(int dir) {
		return animation.getActualFrame();
	}

}
	

