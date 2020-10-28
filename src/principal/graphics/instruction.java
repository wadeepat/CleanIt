package principal.graphics;

import principal.Game;
import principal.statemachine.characterstates.State;

import java.awt.*;

public class instruction extends State {

    private static instruction instruction = new instruction();

    private instruction() {
        animation = Game.animations.getInductions();
        animUpdate = 400;
    }


    public static instruction getInstruction(){
        return instruction;
    }

    @Override
    public Image getImage(int dir) {
        return animation.getActualFrame();
    }

}