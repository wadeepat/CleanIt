package principal.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import principal.Camera;
import principal.Constant;
import principal.HUD;
import principal.entities.Building;
import principal.input.KeyBoard;
import principal.input.MouseInput;
import principal.statemachine.GameStatus;
import principal.statemachine.gamestate.GameManager;


public class DrawingSurface extends Canvas {

	private static final long serialVersionUID = -4338850574868182269L;
	
	private KeyBoard inputKeys; 
	private MouseInput mouse;
	private static Camera cam;
	private static boolean prevGM = false; //previous GameManager
	private static int floor;
	
	public DrawingSurface() {
		cam = new Camera(0, 0);
		inputKeys = new KeyBoard();
		mouse = new MouseInput();
		addKeyListener(inputKeys);
		addMouseListener(mouse);
		setFocusable(true);
		setIgnoreRepaint(true);
		requestFocus();
		floor = 237;
	}

	
	public void update() {
		
		inputKeys.update();
		mouse.update();
		if (GameStatus.actualState instanceof GameManager){
			HUD.getHud().update();
		}
		
		
		if (GameStatus.actualState instanceof GameManager){	
			if (Building.getBuilding().getGM()) {
				if (cam.getY() < floor) {
					cam.tick();
					prevGM = true;		
				}else{
					Building.getBuilding().stopGM();
				}
			}else{	
				if(prevGM){
					floor = floor + 203;
					prevGM = false;
				}
			}
		}
	}
		
	public static void resetSurface() {
		prevGM = false;
		cam.setY(0);
		floor = 237;
	}

	public void render(GameStatus gs, long time) {
		
	
		BufferStrategy bufferStrat = getBufferStrategy();
		if (bufferStrat == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bufferStrat.getDrawGraphics();
		
		clear(g);
		
		if (GameStatus.actualState instanceof GameManager)
			g.translate(0, cam.getY());
		
		gs.render(g, time);
		
		g.translate(0, -cam.getY());
		
		if (GameStatus.actualState instanceof GameManager)
			HUD.getHud().draw(g);
		

		Toolkit.getDefaultToolkit().sync();
		
		
		g.dispose();

		bufferStrat.show();
	}


	private void clear(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);
	}
	
	
}
