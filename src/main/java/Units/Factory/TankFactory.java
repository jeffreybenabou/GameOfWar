package Units.Factory;

import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class TankFactory extends Factory {



    public TankFactory(boolean onWorld) {
        super();
        type=9;
        init();
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*4,0, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
        if(onWorld)
        {

            setTheQuaqe();
        }
    }
}
