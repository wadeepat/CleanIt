package principal.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ResourceLoader {
	
	private ResourceLoader(){}
	
	private static  ResourceLoader resourceLoader = new ResourceLoader();
	private Image image;
		
	public Image loadImage(String path){

		URL imgUrl = getClass().getClassLoader().getResource(path);
		
		if (imgUrl == null){
			System.err.println("url is null " + path);
		}else{
			try{
				image = ImageIO.read(imgUrl);
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return image;
	}
	
	public static ResourceLoader getLoader(){
		return resourceLoader;
	}
	
}
