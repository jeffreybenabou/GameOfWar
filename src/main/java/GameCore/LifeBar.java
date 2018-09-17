package GameCore;

import ObjectPackege.GameObject;
import ObjectPackege.Unit;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class LifeBar extends JProgressBar {

    public LifeBar(int max){
        setBackground(Color.black);
        setForeground(Color.green);



         setUI(new BasicProgressBarUI() {
            protected Color getSelectionForeground() { return Color.black; }
        });
        setMaximum(max);
        setBorderPainted(true);
        setString("" + max);
        setStringPainted(true);
        setMinimum(0);
        setValue(max);
        setOpaque(true);
        setVisible(true);
    }
}
