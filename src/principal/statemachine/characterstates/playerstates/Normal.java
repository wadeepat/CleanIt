package principal.statemachine.characterstates.playerstates;

import java.awt.Image;

import principal.Game;
import principal.statemachine.characterstates.State;

public class Normal extends State {
	
	private final static Normal normal = new Normal();
	
//	private String[] paths = {
//		"images/felix/normal/normalLeft/0.png",
//	};
	
	
	private Normal(){
		animation  = Game.animations.getPlayerNormalRight();
		animUpdate = 1;
	}
	
	public static Normal getNormal(){
		return normal;
	}

	@Override
	public Image getImage(int dir) {
		if (dir == -1){
			animation = Game.animations.getPlaterNormalLeft();
		}else
			animation = Game.animations.getPlayerNormalRight();
		return animation.getActualFrame();
	}


	
}
