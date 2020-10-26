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
	private Font font2;
	private Sprite bg;
	
	public Rectangle backButton = new Rectangle(25, 525, 50, 25);
	
	public ScoreMenu() {
		font = new Font("BOLD", Font.ITALIC,30);
		font1 = new Font("BOLD", Font.PLAIN,15);
		font2 = new Font("BOLD", Font.PLAIN,25);
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

		g.drawImage(bg.getImage(),0,0,null);


		for (int i = 0; i < Score.MAX_SCORE_AMOUNT; i++){
			if(Score.getScore().getCertainName(i)==null ||Score.getScore().getCertainName(i).equals("null"))continue;
			if(i==0){
				g.setColor(Color.YELLOW);
				g.setFont(font);
				String scores = Score.getScore().getCertainName(i)+":   "+Score.getScore().getCertainScore(i);
				g.drawString(scores, 160, 173 + (i+1) * 50);
			}else if(i==1){
				g.setColor(Color.WHITE);
				g.setFont(font2);
				String scores = Score.getScore().getCertainName(i)+":   "+Score.getScore().getCertainScore(i);
				g.drawString(scores, 160, 315);
			}else if(i==2){
				g.setFont(font2);
				String scores = Score.getScore().getCertainName(i)+":   "+Score.getScore().getCertainScore(i);
				g.drawString(scores, 470, 315);
			}else if(i==3){
				g.setFont(font2);
				String scores = Score.getScore().getCertainName(i)+":   "+Score.getScore().getCertainScore(i);
				g.drawString(scores, 160, 400);
			}else if(i==4){
				g.setFont(font2);
				String scores = Score.getScore().getCertainName(i)+":   "+Score.getScore().getCertainScore(i);
				g.drawString(scores, 470, 400);
			}

		}
		g.setFont(font1);
		g.drawString("Back",backButton.x + 5,backButton.y+17);
		g.draw(backButton);
		
	}

}
