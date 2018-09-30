package GameCore;

import ObjectPackege.GameObject;
import ObjectPackege.Unit;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class LifeBar extends JProgressBar {

    public LifeBar(int max){







        UIManager.put("ProgressBar.background", Color.ORANGE);
        UIManager.put("ProgressBar.foreground", Color.BLUE);
        UIManager.put("ProgressBar.selectionBackground", Color.RED);
        UIManager.put("ProgressBar.selectionForeground", Color.GREEN);
        setMaximum(max);
        setString("" + max);
        setStringPainted(true);
        setMinimum(0);
        setValue(max);


    }
}
