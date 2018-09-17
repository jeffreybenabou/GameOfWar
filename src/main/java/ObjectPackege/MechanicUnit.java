package ObjectPackege;


import GameCore.MainFrame;
import GameCore.StaticVariables;

import java.awt.*;

public class MechanicUnit extends Unit {
    public MechanicUnit() {
        super();
        bound = new Rectangle(0, 0, MainFrame.world.getBackGroundImage().getWidth() / 100, MainFrame.world.getBackGroundImage().getWidth() / 100);
        setLayout(new GridLayout(6,1));
        xWitdhToCrop= StaticVariables.MECHANIC_SIZE_ON_SHEET_WIDTH;
        yHeightToCrop= StaticVariables.MECHANIC_SIZE_ON_SHEET_HEIGHT;
    }
}
