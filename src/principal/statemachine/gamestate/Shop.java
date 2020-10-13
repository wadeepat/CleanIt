package principal.statemachine.gamestate;

import principal.Game;
import principal.graphics.Sprite;
import principal.input.MouseInput;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;

import java.awt.*;

public class Shop implements GameState {
    private Font font;
    private Sprite conan;
    private Sprite bg;
    private Sprite block;
    public Shop(){
        font = new Font("BOLD",Font.PLAIN,20);
        conan = Game.animations.getConan();
        bg = Game.animations.getBgShop();
        block = Game.animations.getBlock();
    }
    @Override
    public void draw(Graphics2D g, long time) {
        g.drawImage(bg.getImage(),0,0,null);

        for (int y=70 ;y<=390;y+=155){ //draw block
            for(int x=60;x<=630;x+=140){
                g.drawImage(block.getImage(),x,y,null);
                g.drawImage(conan.getImage(),x+20,y+20,null);
            }
        }


    }

    @Override
    public void tick(long time) {
//        if (MouseInput.leftClick){
////            if (backButton.contains(MouseInput.getPointer())){
////                GameStatus.changeState(0);
////                GameManager.setChoose(false);
////            }
//        }
    }
}
