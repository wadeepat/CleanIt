package principal.statemachine.characterstates.nicelander;

import java.awt.Image;

import principal.graphics.Animation;
import principal.statemachine.characterstates.State;

public class BadlanderAnimation extends State{
	
	
	private final String[] badlanderPaths = {
		"images/entities/nicelander/0.png",
		"images/entities/nicelander/1.png",
		"images/entities/nicelander/2.png",
	};
	
	private static BadlanderAnimation badlander = new BadlanderAnimation();
	
	private BadlanderAnimation() {
		animUpdate = 3800;
		animation = new Animation(badlanderPaths);
	}
	
	public static BadlanderAnimation getBadlander(){
		return badlander;
	}

	@Override
	public Image getImage(int dir) {
		return animation.getActualFrame();
	}
	
	
}
