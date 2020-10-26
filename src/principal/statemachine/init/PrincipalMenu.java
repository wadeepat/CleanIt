package principal.statemachine.init;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import principal.Constant;
import principal.Game;
import principal.Level;
import principal.Score;
import principal.entities.Building;
import principal.entities.windows.parts.GlassAndDisplacement;
import principal.graphics.DrawingSurface;
import principal.graphics.Sprite;
import principal.input.KeyBoard;
import principal.input.MouseInput;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;
import principal.statemachine.gamestate.GameManager;

public class PrincipalMenu implements GameState{
	
	private int DISP_X = 30;

	public Rectangle playButton = new Rectangle(Constant.WIDTH/2 - 100/2 - 20 , 270, 100, 50);
	public Rectangle scoreButton = new Rectangle(Constant.WIDTH/2 - 100/2  - 20, 320,100, 50);
	public Rectangle helpButton = new Rectangle(Constant.WIDTH/2 - 100/2 - 20, 370, 100, 50);
	public Rectangle configButton = new Rectangle(Constant.WIDTH - 50, 0, 45, 45);
	public Rectangle shopButton = new Rectangle(Constant.WIDTH/2 - 100/2 - 20, 420, 100, 50);
	public Rectangle quitButton = new Rectangle(Constant.WIDTH/2 - 100/2 - 20, 470, 100, 50);

	private boolean drawString;
	private int counter = 0;

	private Building building;
	
	private Font font;
	private Font font2;
	private Sprite menu;
	private Sprite[] menuButton;
	private String[] levels;
	
	private boolean barspacePushed;
	
	private Sprite config;




	public PrincipalMenu() {
		
		font = new Font("arial",Font.ROMAN_BASELINE, 25);
		font2 = new Font("arial", Font.ITALIC, 12);
		
		menu = Game.animations.getMenu();
		menuButton = Game.animations.getMenuButton();

		barspacePushed = false;
		
		building = Building.getBuilding();
		config = Game.animations.getConfig();

		levels = new String[10];
	}
	
	
	private void initLevels() {
		for (int i = 0; i < levels.length; i++){
			levels[i] = "Level " + (i+1);
		}
	}
	
	
	@Override
	public void update(long beforeTime) {
		if (barspacePushed){
			if (MouseInput.leftClick) {
				if (playButton.contains(MouseInput.getPointer())){
					GameManager.getGameManager().resetGameManager();
					restart();

				}else if (scoreButton.contains(MouseInput.getPointer())){
					GameStatus.changeState(3);
				}else if (helpButton.contains(MouseInput.getPointer())){
					GameStatus.changeState(4);
				}else if (configButton.contains(MouseInput.getPointer())){
					drawFrame();
				}else if(shopButton.contains(MouseInput.getPointer())){
					GameStatus.changeState(6);
				}else if (quitButton.contains(MouseInput.getPointer())) {
					Game.quitGame();
				}
			}										
		}
	}


	private void drawFrame() {
		
		final JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton button = new JButton("Choose");
		initLevels();
		final JComboBox combo = new JComboBox(levels);
		
		frame.setSize(300, 90);
		frame.setVisible(true);

		panel.setBackground(Color.BLACK);
		panel.add(button, BorderLayout.EAST);
		panel.add(combo, BorderLayout.SOUTH);

		frame.add(panel);
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Level.getLevel().chooseLevel((String)combo.getSelectedItem());
				GameManager.setChoose(true);
				frame.setVisible(false);
			}
			
		});
		
	}
	
	


	private void restart() {
		GameManager.getGameManager().resetGameManager();
		GameStatus.changeState(1);	
		Score.getScore().reset();
		DrawingSurface.resetSurface();
	}
	
	@Override

	public void render(Graphics2D g, long beforeTime) {

		clean(g);
		
		// Anti aliasing
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		
		building.draw(g, beforeTime);

		
		g.setFont(font);
		g.setColor(Color.YELLOW);
	
		drawPressBar(g);

		g.drawImage(menu.getImage(), Constant.WIDTH/2 - menu.getWidth()/2,
				Constant.HEIGHT/2 - menu.getHeight()/2 - 100,null);
		if (barspacePushed){
			g.drawImage(config.getImage(), Constant.WIDTH - 50, 0, null);

			for (int i=0;i<menuButton.length;i++){
				g.drawImage(menuButton[i].getImage(),playButton.x, playButton.y+(50*i), null);
			}

		}
	}

	
	private void clean(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);
	}
	
	
	private void drawPressBar(Graphics2D g) {
		counter++;
		if (counter  > 2000 && !barspacePushed){
			counter = 0;
			drawString = !drawString;
		}
		if (drawString){
			g.drawString("Press spacebar to start", Constant.WIDTH/2 - 135 ,Constant.HEIGHT/2 + 40);
		}
		if (KeyBoard.fix){
			barspacePushed = true;
			drawString = false;
		}
	}
	
	
}
