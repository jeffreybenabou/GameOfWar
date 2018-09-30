package Units.Factory;



import GameCore.MainFrame;
import GameCore.StaticVariables;
import GameCore.World;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class InfentryFactory extends Factory {


    public static final int INFENTRY_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/50, INFENTRY_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getWidth()/60;


    public InfentryFactory(boolean onWorld) {
        super();
        type=3;
        bound=new Rectangle(0,0, INFENTRY_FACTORY_WIDTH,INFENTRY_FACTORY_HEIGHT);
        saveTheWidthAndHeight(bound.width,bound.height);
        init(onWorld);
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*3,0, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
        if(onWorld)
        {

            setTheQuaqe();
        }


    }
}
