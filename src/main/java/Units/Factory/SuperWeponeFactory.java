package Units.Factory;



import GameCore.MainFrame;
import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class SuperWeponeFactory extends Factory {


    public static final int SUPERWEPONE_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/55, SUPERWEPONE_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getHeight()/60;

    public SuperWeponeFactory() {
        super();
        type=7;
        setBound(new Rectangle(0,0,SUPERWEPONE_FACTORY_WIDTH,SUPERWEPONE_FACTORY_HEIGHT));
        init();
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*2,StaticVariables.FACTORY_SHEET_HEIGHT, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
