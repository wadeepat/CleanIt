package principal.entities.creatures;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import principal.Constant;
import principal.Game;
import principal.Handler;
import principal.Level;
import principal.entities.Building;
import principal.entities.Entity;
import principal.entities.ID;
import principal.graphics.Animation;
import principal.statemachine.gamestate.GameManager;

public class Egg extends Creature{

    private Animation egg;

    private int actualSector;

    public Egg(float x,float y,int actualSector){
        super(x,y);
        egg = Game.animations.getEgg();

        vel = Level.getLevel().getEggVel();

        this.actualSector = actualSector;
        id = ID.Egg;
        egg = Game.animations.getEgg();

        Handler.add(this);
    }


    public void draw(Graphics2D g, long time){
        egg.tick();
        g.drawImage(egg.getActualFrame(),(int)getX(),(int)getY(),null);
    }

    public void tick(ArrayList<Entity> creat, long BeforeTime){
        Building b= Building.getBuilding();

        setY(getY() + vel);

        if(getY() > b.getSector(actualSector).getBotBounds().y + 100){
            Handler.remove(this);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)getX(), (int)getY(), 18, 12);
    }

    @Override
    public Rectangle getTopBounds() {
        return new Rectangle(0,0,0,0);
    }

    @Override
    public Rectangle getLeftBounds() {
        return new Rectangle(0,0,0,0);
    }

    @Override
    public Rectangle getRightBounds() {
        return new Rectangle(0,0,0,0);
    }

    @Override
    public Rectangle getBotBounds() {
        return new Rectangle(0,0,0,0);
    }
}
