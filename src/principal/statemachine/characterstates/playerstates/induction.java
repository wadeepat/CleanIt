package principal.statemachine.characterstates.playerstates;

import principal.Game;
import principal.statemachine.characterstates.State;

import java.awt.*;

public class induction extends State {

    private static induction induction = new induction();

    private induction() {
        animation = Game.animations.getInductions();
        animUpdate = 400;
    }


    public static induction getInduction(){
        return induction;
    }

    @Override
    public Image getImage(int dir) {
        return animation.getActualFrame();
    }

}