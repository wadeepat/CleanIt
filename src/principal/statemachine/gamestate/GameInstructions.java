package principal.statemachine.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import principal.input.MouseInput;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;
import principal.statemachine.characterstates.State;
import principal.graphics.instruction;

public class GameInstructions implements GameState{

	public Rectangle backButton = new Rectangle(25, 525, 50, 25);
	
	private Font font;
	private State instrucAni = instruction.getInstruction();
	
	public GameInstructions(){
		font = new Font("Arial",Font.BOLD,15);
	}
	
	@Override
	public void update(long time) {
		if (MouseInput.leftClick) {
			if (backButton.contains(MouseInput.getPointer())){
				GameStatus.changeState(0);
			}
		}
	}

	@Override
	public void render(Graphics2D g, long time) {
		g.setColor(Color.white);
		g.setFont(font);
		instrucAni.update();
		g.drawImage(instrucAni.getImage(0), 0, 0, null);
		g.drawString("Back",backButton.x + 5,backButton.y+17);
		g.draw(backButton);
		
	}

}
