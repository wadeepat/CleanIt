package principal.statemachine.gamestate;

import principal.Game;
import principal.Images;
import principal.graphics.Sprite;
import principal.input.MouseInput;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Shop implements GameState {
    private Font font;
    private Sprite[] shopchar; //array of character images
    private Sprite bg;
    private Sprite block;

    public Rectangle backButton = new Rectangle(10,15,40,25);

public Rectangle selectButton[] = new Rectangle[15];

    public Shop(){

        shopchar = Game.animations.getShopchar();
        bg = Game.animations.getBgShop();
        block = Game.animations.getBlock();

        font = new Font("Lucida Sans",Font.PLAIN,13);
    }
    @Override
    public void draw(Graphics2D g, long time) {
        g.setFont(font);
        g.setColor(Color.white); //set font color
        g.drawImage(bg.getImage(),0,0,null); //background image

        g.draw(backButton); //draw back button
        g.drawString("Back", backButton.x+5, backButton.y+15);

        int i= 0;
        for (int y=70 ;y<=390;y+=155){ //draw shop window
            for(int x=60;x<=630;x+=140){
                g.drawImage(block.getImage(),x,y,null); //draw block
                g.drawImage(shopchar[i].getImage(),x+20,y+20,null); //draw  character
                selectButton[i] = new Rectangle(x+30,y+110,35,15); //create select button
                g.drawString("select",x+30,y+120); //draw string select
                i++;
            }
        }

    }

    @Override
    public void update(long time) {
        if (MouseInput.leftClick){ //select character
            if (selectButton[0].contains(MouseInput.getPointer())){
                System.out.println("selected 0");
                Game.animations = new Images("conan");
            }else if (selectButton[1].contains(MouseInput.getPointer())){
                System.out.println("select 1");
                Game.animations = new Images("sonic");
            }else if (selectButton[2].contains(MouseInput.getPointer())){
                System.out.println("selected 2");
                Game.animations = new Images("red");
            }else if (selectButton[3].contains(MouseInput.getPointer())){
                System.out.println("selected 3");
                Game.animations = new Images("zombie");
            }else if (selectButton[4].contains(MouseInput.getPointer())){
                System.out.println("selected 4");
                Game.animations = new Images("lama");
            }else if (selectButton[5].contains(MouseInput.getPointer())){
                System.out.println("selected 5");
            }else if (selectButton[6].contains(MouseInput.getPointer())){
                System.out.println("selected 6");
            }else if (selectButton[7].contains(MouseInput.getPointer())){
                System.out.println("selected 7");
            }

            if(backButton.contains(MouseInput.getPointer())){ //if pressed back button go to menu
                GameStatus.changeState(0);
            }
        }
    }
}
