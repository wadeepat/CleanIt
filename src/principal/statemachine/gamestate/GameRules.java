package principal.statemachine.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import principal.Constant;
import principal.Game;
import principal.graphics.Animation;
import principal.graphics.Sprite;
import principal.input.MouseInput;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;
import principal.statemachine.characterstates.State;
import principal.statemachine.characterstates.playerstates.induction;

public class GameRules implements GameState{

	public Rectangle backButton = new Rectangle(25, 525, 50, 25);
	
	private Font font;
	private State inducAni = induction.getInduction();
	
	public GameRules(){

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
		inducAni.update();
		g.drawImage(inducAni.getImage(0), 0, 0, null);

		
//		String rules = "Para ganar tenes que arreglar el edificio completo";
//		g.drawString(rules,100, 150);
//
//		rules = "Movete con las flechas y con el espacio arreglas las ventanas. Pausa el juego con ESC.";
//		g.drawString(rules,100,200);
//
//		rules = "Esquiva todos los ladrillos y aves que cruces en tu camino.";
//		g.drawString(rules,100,250);
//
//		rules = "Los nicelander te ofrecen pasteles �cuidado no los golpees con el martillo!.";
//		g.drawString(rules,100,300);
//
//		rules = "Tambi�n podes elegir el nivel en el que quieras empezar a jugar.";
//		g.drawString(rules,100,350);

		g.drawString("Back",backButton.x + 5,backButton.y+17);
		g.draw(backButton);
		
	}

}
