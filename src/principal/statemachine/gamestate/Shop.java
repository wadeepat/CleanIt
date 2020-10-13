package principal.statemachine.gamestate;

import principal.input.MouseInput;
import principal.statemachine.GameState;
import principal.statemachine.GameStatus;

import java.awt.*;

public class Shop implements GameState {
    @Override
    public void draw(Graphics2D g, long time) {

    }

    @Override
    public void tick(long time) {
        if (MouseInput.leftClick){
//            if (backButton.contains(MouseInput.getPointer())){
//                GameStatus.changeState(0);
//                GameManager.setChoose(false);
//            }
        }
    }
}
