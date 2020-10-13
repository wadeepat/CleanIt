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
    public Shop(){
        font = new Font("BOLD",Font.PLAIN,20);
        conan = Game.animations.getConanBuy();
    }
    @Override
    public void draw(Graphics2D g, long time) {
        g.drawImage(conan.getImage(),30,30,null);
        g.setBackground(Color.lightGray);
        g.getBackground();
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
