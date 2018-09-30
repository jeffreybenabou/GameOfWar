package Units.Factory;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class SateliteFactory extends Factory {

    public static final int SATELITE_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/80, SATELITE_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getHeight()/60;

    public SateliteFactory(boolean onWorld) {
        super();
        type=10;
        setBound(new Rectangle(0,0,SATELITE_FACTORY_WIDTH,SATELITE_FACTORY_HEIGHT));
        saveTheWidthAndHeight(bound.width,bound.height);
        init(onWorld);
        setIcon(new ImageIcon(spriteSheet.crop(0,StaticVariables.FACTORY_SHEET_HEIGHT, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
