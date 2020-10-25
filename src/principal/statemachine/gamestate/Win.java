package principal.statemachine.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import principal.Constant;
import principal.Game;
import principal.Level;
import principal.graphics.Sprite;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;
import principal.statemachine.characterstates.State;
import principal.statemachine.characterstates.felixstates.FelixWin;

public class Win implements GameState{
	
	private State felixAnim = FelixWin.getFelixWin();
	private static long timing;
	private Font font;
	private Sprite buildingRoof;

	
	public Win(){
		font = new Font("BOLD", Font.PLAIN, 70);
		buildingRoof = Game.animations.getBuildingRoof();
	}

	
	@Override
	public void render(Graphics2D g, long time) {

		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		felixAnim.update(); 
		g.drawRect(0, 0, 800, 600);
		g.drawImage(felixAnim.getImage(0), Constant.WIDTH/2, Constant.HEIGHT/2 + 78, null);
		g.drawImage(buildingRoof.getImage(), Constant.WIDTH/2 - buildingRoof.getWidth()/2,Constant.HEIGHT - 186, null);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Level " + (Level.getLevel().getActualLevel()- 1) + " Completed", 90, 200);
	}

	@Override
	public void update(long beforeTime) {
		if (beforeTime - timing > 5000){
			GameStatus.changeState(1);
		}
		
	}

	public static void setTiming(long time){
		timing = time;
	}
	
	
}
