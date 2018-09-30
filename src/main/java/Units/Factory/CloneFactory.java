package Units.Factory;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class CloneFactory extends Factory {

    public static final int CLONE_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/55, CLONE_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getHeight()/60;


    public CloneFactory(boolean onWorld ) {
        super();
        type=5;
        setBound(new Rectangle(0,0,CLONE_FACTORY_WIDTH,CLONE_FACTORY_HEIGHT));
        saveTheWidthAndHeight(bound.width,bound.height);
        init(onWorld);
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*4,StaticVariables.FACTORY_SHEET_HEIGHT, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
