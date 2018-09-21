package Units.Factory;


import GameCore.MainFrame;
import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class AirForceFactory extends Factory {

    public static final int AIRFIELD_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/50, AIRFIELD_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getHeight()/70;


    public AirForceFactory(boolean onWorld) {
        super();
        type=4;
        setBound(new Rectangle(0,0,AIRFIELD_FACTORY_WIDTH,AIRFIELD_FACTORY_HEIGHT));
        init();
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
        if(onWorld)
        {

            setTheQuaqe();
        }
    }
}
