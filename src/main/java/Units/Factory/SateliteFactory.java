package Units.Factory;

import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class SateliteFactory extends Factory {


    public SateliteFactory() {
        super();
        type=10;
        init();
        setIcon(new ImageIcon(spriteSheet.crop(0,StaticVariables.FACTORY_SHEET_HEIGHT, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
