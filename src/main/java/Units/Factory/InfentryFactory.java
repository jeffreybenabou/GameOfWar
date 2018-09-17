package Units.Factory;



import GameCore.StaticVariables;
import GameCore.World;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class InfentryFactory extends Factory {




    public InfentryFactory(boolean onWorld) {
        super();
        type=3;
        init();
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*3,0, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
        if(onWorld)
        {

            setTheQuaqe();
        }


    }
}
