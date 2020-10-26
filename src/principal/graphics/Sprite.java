package principal.graphics;

import java.awt.Image;

public class Sprite {
	
	private Image image;
	
	public Sprite(Image image){
		this.image = image;
	}
	
	public Image getImage(){
		return image;
	}
	
	//Resize start background
	public int getWidth(){
		return 800;
	}
	
	public int getHeight(){
		return 400;
	}
	
}
