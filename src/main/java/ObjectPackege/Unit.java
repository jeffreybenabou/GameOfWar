package ObjectPackege;



import GameCore.StaticVariables;
import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;

import javax.swing.*;
import java.awt.*;


public class Unit extends GameObject {

    protected SpriteSheet standSpriteSheet, moveSpriteSheet;

    protected int xToMove=0,yToMove=0;
    public Unit(Rectangle bound) {
        super(bound,true);
        initTheSpriteSheet();


    }

    protected void setTheUnitMethod(final SpriteSheet move, final SpriteSheet walk, final int x, final int y) {
        new Thread(new Runnable() {
            public void run() {
                while (objectIsLive)
                {

                    if(objectIsMoving)
                    {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        moveTheUnit(move,x,y);

                    }else if (objectIsStanding)
                    {
                        changeTheImage(walk, x, y);

                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void moveTheUnit(SpriteSheet move,int x,int y) {



        changeTheImage(move,x,y);
        yToMove+=y;
        if(yToMove==y*3)
            yToMove=0;

    }

    public void changeTheImage(SpriteSheet spriteSheet, int x, int y){



        setIcon(new ImageIcon(spriteSheet.crop(xToMove,yToMove,115,115).getScaledInstance(getWidth(),getHeight(),4)));
    }
    private void initTheSpriteSheet() {
        imageLoader=new ImageLoader();
}


}

