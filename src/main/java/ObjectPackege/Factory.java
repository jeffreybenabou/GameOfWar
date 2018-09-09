package ObjectPackege;


import GameCore.MainFrame;
import ImageHandel.SpriteSheet;

import java.awt.*;


public class Factory extends GameObject {


    protected SpriteSheet spriteSheet;

    public Factory() {

        super();
        spriteSheet=new SpriteSheet(imageLoader.loadImage("image/factor/userFactory/building.png"));
        bound=new Rectangle(0,0, MainFrame.world.getBackGroundImage().getWidth()/50,MainFrame.world.getBackGroundImage().getHeight()/70);

    }








}
