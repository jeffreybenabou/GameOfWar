package ObjectPackege;


import GameCore.LifeBar;
import GameCore.MainFrame;
import ImageHandel.SpriteSheet;

import java.awt.*;


public class Factory extends GameObject {


    protected SpriteSheet spriteSheet;

    public Factory() {

        super();
        spriteSheet=new SpriteSheet(imageLoader.loadImage("image/factor/userFactory/building.png"));
        bound=new Rectangle(0,0, MainFrame.world.getBackGroundImage().getWidth()/50,MainFrame.world.getBackGroundImage().getHeight()/70);
        setLayout(new GridLayout(12,1));
    }




}
