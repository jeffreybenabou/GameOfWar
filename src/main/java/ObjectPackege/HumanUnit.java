package ObjectPackege;

import GameCore.MainFrame;

import java.awt.*;

public class HumanUnit extends Unit {
    public HumanUnit() {

        super();
        bound = new Rectangle(1000, 200, MainFrame.world.getBackGroundImage().getWidth() / 200, MainFrame.world.getBackGroundImage().getWidth() / 200);
        setLayout(new GridLayout(6,1));
    }
}
