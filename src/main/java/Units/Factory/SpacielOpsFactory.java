package Units.Factory;

import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class SpacielOpsFactory extends Factory {


    public SpacielOpsFactory() {
        super();
        type=6;
        init();
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*3,StaticVariables.FACTORY_SHEET_HEIGHT, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
