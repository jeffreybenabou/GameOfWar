package Units.Factory;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class SpacielOpsFactory extends Factory {

    public static final int SPECIALOPS_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/55, SPECIALOPS_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getHeight()/60;

    public SpacielOpsFactory(boolean onWorld) {
        super();
        type=6;

        setBound(new Rectangle(0,0,SPECIALOPS_FACTORY_WIDTH,SPECIALOPS_FACTORY_HEIGHT));
        saveTheWidthAndHeight(bound.width,bound.height);
        init(onWorld);
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*3,StaticVariables.FACTORY_SHEET_HEIGHT, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
