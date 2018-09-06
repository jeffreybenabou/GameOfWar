package GameCore;

import javax.swing.*;
import java.awt.*;

public class World extends JPanel {

    private JLabel backGroundImage;

    public World(){
        setBounds(0,0,MainFrame.screenSize.width,MainFrame.screenSize.height);
        setBackground(Color.black);
        setTheBackGroundWorld();


    }

    private void setTheBackGroundWorld() {
        backGroundImage=new JLabel();
        backGroundImage.setBounds(0,0,20000,20000);
        backGroundImage.setBackground(Color.GRAY);
        backGroundImage.setOpaque(true);
        add(backGroundImage);
    }
}
