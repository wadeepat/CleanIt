package principal.graphics;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import principal.Constant;
import principal.Game;


public class Window extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private final String title;
	
	public Window(String title, final DrawingSurface ds){
		this.title = title;
		init(ds);
	}
	

	private void init(final DrawingSurface ds) {

		add(ds, BorderLayout.CENTER);
		setSize(new Dimension(Constant.WIDTH, Constant.HEIGHT));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setTitle(title);
		setIconImage(Game.animations.getIcon().getImage());
	}

}
