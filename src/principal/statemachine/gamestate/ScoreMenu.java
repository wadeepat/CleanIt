package principal.statemachine.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import principal.Constant;
import principal.Game;
import principal.Score;
import principal.graphics.Sprite;
import principal.input.MouseInput;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;

public class ScoreMenu implements GameState{
	
	private Font font;
	private Font font1;
	private Sprite bg;
	
	public Rectangle backButton = new Rectangle(300, 450, 100, 50); // 300,450
	
	public ScoreMenu() {
		font = new Font("BOLD", Font.PLAIN,15);
		font1 = new Font("BOLD", Font.PLAIN, 70);
		bg = Game.animations.getBgScore();
	}
	
	@Override
	public void update(long time) {
		if (MouseInput.leftClick){
			if (backButton.contains(MouseInput.getPointer())){
				GameStatus.changeState(0);
				GameManager.setChoose(false);
			}
		}
		
	}

	@Override
	public void render(Graphics2D g, long time) {
		
		g.setColor(Color.WHITE);
	
		g.setFont(font1);
		g.drawString("HIGHSCORES", 30,  100);
		g.drawImage(bg.getImage(),0,0,null);
		
		g.setFont(font);	
		for (int i = 0; i < Score.MAX_SCORE_AMOUNT; i++){
			if(Score.getScore().getCertainName(i)==null ||Score.getScore().getCertainName(i).equals("null"))continue;
			String scores = Score.getScore().getCertainName(i)+":   "+Score.getScore().getCertainScore(i);
			g.drawString(scores, 30, 100 + (i+1) * 50);
		}
		
		g.drawString("Back",backButton.x + 19,backButton.y+30);
		g.draw(backButton);
		
	}

}
