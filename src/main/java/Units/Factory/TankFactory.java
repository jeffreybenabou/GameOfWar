package Units.Factory;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class TankFactory extends Factory {


    public static final int TANK_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/50, TANK_FACTORY_HIEGHT = MainFrame.world.getBackGroundImage().getWidth()/60;



    public TankFactory(boolean onWorld) {
        super();
        type=9;
        setBound(new Rectangle(0,0,TANK_FACTORY_WIDTH,TANK_FACTORY_HIEGHT));
        saveTheWidthAndHeight(bound.width,bound.height);
        init(onWorld);
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*4,0, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
        if(onWorld)
        {

            setTheQuaqe();
        }
    }
}
