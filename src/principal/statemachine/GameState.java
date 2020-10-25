package principal.statemachine;

import java.awt.Graphics2D;

public interface GameState {
	
	void render(final Graphics2D g, long time);

	void update(long time);
	
}
