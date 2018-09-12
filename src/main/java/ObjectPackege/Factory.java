package ObjectPackege;


import GameCore.LifeBar;
import GameCore.MainFrame;
import GameCore.World;
import ImageHandel.SpriteSheet;

import java.awt.*;
import java.awt.event.MouseEvent;


public class Factory extends GameObject {


    protected SpriteSheet spriteSheet;
    private Thread moveTheFactoryAroundTheWorld;
    public static boolean objectIsFlotingWorld=false;
    private boolean canAddToWorld=true;
    private boolean factoryIsOnWorld=false;
    public static Factory factory;


    public Factory() {

        super();
        spriteSheet=new SpriteSheet(imageLoader.loadImage("image/factor/userFactory/building.png"));
        bound=new Rectangle(0,0, MainFrame.world.getBackGroundImage().getWidth()/50,MainFrame.world.getBackGroundImage().getHeight()/70);
        setLayout(new GridLayout(12,1));

    }

    public void checkIfFactoryIntercetWithOtherFactory(){
        for (Factory factory:World.allFactorys) {

            if(factory.hashCode()!= Factory.factory.hashCode()&&!factory.getBound().intersects( Factory.factory.getBound()))
            {
                canAddToWorld=true;
            }
            else
            {
                canAddToWorld=false;
                break;
            }


        }
        if(canAddToWorld)
        {
            addToWorld();
        }

    }

    protected void addToWorld() {

        World.allFactorys.add(this);
        Factory.objectIsFlotingWorld=false;
        factoryIsOnWorld=true;
    }

    public void setTheLocation() {


         new Thread(new Runnable() {
            public void run() {
                while (objectIsFlotingWorld) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    setBound(new Rectangle(-MainFrame.world.getBackGroundImage().getX() + MainFrame.xMouseLocation - MainFrame.screenSize.width / 8, -MainFrame.world.getBackGroundImage().getY() + MainFrame.yMouseLocation - MainFrame.screenSize.height / 5,getWidth(),getHeight()));
                    setBounds(getBound());

                }

            }
        }).start();

    }


    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public Thread getMoveTheFactoryAroundTheWorld() {
        return moveTheFactoryAroundTheWorld;
    }

    public void setMoveTheFactoryAroundTheWorld(Thread moveTheFactoryAroundTheWorld) {
        this.moveTheFactoryAroundTheWorld = moveTheFactoryAroundTheWorld;
    }

    public static boolean isObjectIsFlotingWorld() {
        return objectIsFlotingWorld;
    }

    public static void setObjectIsFlotingWorld(boolean objectIsFlotingWorld) {
        Factory.objectIsFlotingWorld = objectIsFlotingWorld;
    }

    public boolean isCanAddToWorld() {
        return canAddToWorld;
    }

    public void setCanAddToWorld(boolean canAddToWorld) {
        this.canAddToWorld = canAddToWorld;
    }

    public boolean isFactoryIsOnWorld() {
        return factoryIsOnWorld;
    }

    public void setFactoryIsOnWorld(boolean factoryIsOnWorld) {
        this.factoryIsOnWorld = factoryIsOnWorld;
    }
}
