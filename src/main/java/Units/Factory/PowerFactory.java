package Units.Factory;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class PowerFactory extends Factory {
    public static final int POWER_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/100, POWER_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getHeight()/130;


    public PowerFactory(boolean onWorld) {
        super();
        type=2;
        bound=new Rectangle(0,0, POWER_FACTORY_WIDTH,POWER_FACTORY_HEIGHT);
        saveTheWidthAndHeight(bound.width,bound.height);
        setLayout(new GridLayout(8,1));
        init(onWorld);
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH,0, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();

    }
}
