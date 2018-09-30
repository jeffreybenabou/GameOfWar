package Units.Factory;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.Factory;



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainFactory extends Factory {


    public static final int MAIN_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/50, MAIN_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getHeight()/70;

    public MainFactory(boolean onWorld) {
        super();
        type=1;
        bound=new Rectangle(0,0,MAIN_FACTORY_WIDTH,MAIN_FACTORY_HEIGHT);
        saveTheWidthAndHeight(bound.width,bound.height);

        init(onWorld);
        setIcon(new ImageIcon(spriteSheet.crop(0,0,StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
        if(onWorld)
        addToWorld();




    }
}
