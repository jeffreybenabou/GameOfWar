package Units.Factory;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.Factory;


import javax.swing.*;
import java.awt.*;

public class MainFactory extends Factory {


    public MainFactory() {
        super();
        type=1;
        init();
        setIcon(new ImageIcon(spriteSheet.crop(0,0,StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));



    }
}
