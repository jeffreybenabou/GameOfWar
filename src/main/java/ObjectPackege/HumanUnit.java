package ObjectPackege;

import GameCore.MainFrame;

import java.awt.*;

public class HumanUnit extends Unit {
    public HumanUnit() {
        super(new Rectangle(0,200, MainFrame.world.getBackGroundImage().getWidth()/200,MainFrame.world.getBackGroundImage().getWidth()/200));
    }
}
