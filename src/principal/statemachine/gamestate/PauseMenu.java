package principal.statemachine.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import principal.Constant;
import principal.Game;
import principal.HUD;
import principal.Level;
import principal.Score;
import principal.entities.Building;
import principal.graphics.DrawingSurface;
import principal.input.KeyBoard;
import principal.input.MouseInput;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;

public class PauseMenu implements GameState{

	private final int DISP_X = 30;

	public Rectangle menuButton = new Rectangle(DISP_X , 250, 180, 50);
	public Rectangle resumeButton = new Rectangle(DISP_X , 150, 180, 50); 
	public Rectangle restartButton = new Rectangle(DISP_X , 200, 180, 50);
	public Rectangle exitButton = new Rectangle (DISP_X , 300, 180, 50);
			
	private Font font;
	private Font font1;
	
	public PauseMenu() {
		font =  new Font("BOLD",Font.PLAIN,60);
		font1 = new Font("BOLD", Font.PLAIN, 20);
	}
	
	@Override
	public void update(long time) {
		if (MouseInput.leftClick){
			if (menuButton.contains(MouseInput.getPointer())){
				GameManager.setChoose(false);
				GameStatus.changeState(0);
			}else if (resumeButton.contains(MouseInput.getPointer())){
				GameStatus.changeState(1);
			}else if (restartButton.contains(MouseInput.getPointer())){
				restart();
			}else if (exitButton.contains(MouseInput.getPointer())){
				Game.quitGame();
			}
		}
		
	}

	
	private void restart() {
		GameManager.getGameManager().resetGameManager();
		GameStatus.changeState(1);	
		Score.getScore().reset();
		DrawingSurface.resetSurface();
	}
		


	@Override
	public void render(Graphics2D g, long time) {
		Building.getBuilding().draw(g, time);
		g.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		g.setFont(font);
		g.setColor(Color.RED);
		g.drawString("Pause", DISP_X, 100);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		g.drawString("Main Menu", DISP_X, menuButton.y + 35);
		g.drawString("Resume", DISP_X, resumeButton.y + 35);
		g.drawString("Restart", DISP_X, restartButton.y + 35);
		g.drawString("Quit", DISP_X, exitButton.y + 35);
		
	}

}
