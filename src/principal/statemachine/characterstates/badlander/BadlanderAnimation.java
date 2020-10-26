package principal.statemachine.characterstates.badlander;

import java.awt.Image;

import principal.graphics.Animation;
import principal.statemachine.characterstates.State;

public class BadlanderAnimation extends State{
	protected State state;
	
	private final String[] badlanderPaths = {
//		"images/entities/badlander/0.png",
//		"images/entities/badlander/1.png",
		"images/entities/badlander/2.png",
	};
	
	private static BadlanderAnimation badlander = new BadlanderAnimation();
	
	private BadlanderAnimation() {
		animUpdate = 1000;
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
