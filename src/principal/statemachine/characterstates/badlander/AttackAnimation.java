package principal.statemachine.characterstates.badlander;

import principal.Game;
import principal.graphics.Animation;
import principal.statemachine.characterstates.State;

import java.awt.*;

public class AttackAnimation extends State {
    protected State state;
    private final String[] attackPaths={
            "images/entities/badlander/2.png",
    };

    private static AttackAnimation attackAnimation = new AttackAnimation();
    private AttackAnimation(){
        animUpdate = 1000;
        animation = new Animation(attackPaths);
    }

    public static AttackAnimation getAttackAnimation(){return attackAnimation;}

    @Override
    public Image getImage(int dir) {
        return animation.getActualFrame();
    }
}
