package ObjectPackege;

import GameCore.MainFrame;
import GameCore.StaticVariables;

import java.awt.*;

public class HumanUnit extends Unit {
    public HumanUnit() {

        super();

        bound = new Rectangle(1000, 200, MainFrame.world.getBackGroundImage().getWidth() / 200, MainFrame.world.getBackGroundImage().getWidth() / 200);
        saveTheWidthAndHeight(200,200);
        xWitdhToCrop= StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH;
        yHeightToCrop= StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT;


    }
}
