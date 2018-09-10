package GameCore;

import ObjectPackege.GameObject;
import ObjectPackege.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class World extends JPanel {

    private JLabel backGroundImage;
    private boolean isTesting;
    public static ArrayList<Unit> allUnit;
    public static ArrayList<GameObject>allObjects;
    private MiniMap miniMap;

    private Unit tempUnit;

    public World(boolean isTesting) {
        this.isTesting = isTesting;
        allUnit = new ArrayList<Unit>();
        allObjects=new ArrayList<GameObject>();
        if (isTesting) {
            setTheTestScreen();
        }
        setBounds(0, MainFrame.gamePanel.getHeight(), MainFrame.screenSize.width, MainFrame.screenSize.height);
        setTheBackGroundWorld();
        addMiniMap();
        setBackground(Color.cyan);
        setLayout(null);



    }
    public void addMiniMap()
    {
        miniMap =new MiniMap();
        getBackGroundImage().add(miniMap);
    }




    private void setTheTestScreen() {

    }

    private void setTheBackGroundWorld() {

                backGroundImage=new JLabel();
                backGroundImage.setBounds(0,0,MainFrame.screenSize.width*10,MainFrame.screenSize.height*20);
                backGroundImage.setBackground(Color.black);
                backGroundImage.setOpaque(true);
                backGroundImage.addMouseListener(MainFrame.mainFrame);

                add(backGroundImage);



    }

    public JLabel getBackGroundImage() {
        return backGroundImage;
    }

    public void setBackGroundImage(JLabel backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    public boolean isTesting() {
        return isTesting;
    }

    public void setTesting(boolean testing) {
        isTesting = testing;
    }

    public Unit getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(Unit tempUnit) {
        this.tempUnit = tempUnit;
    }

    public static ArrayList<Unit> getAllUnit() {
        return allUnit;
    }

    public static void setAllUnit(ArrayList<Unit> allUnit) {
        World.allUnit = allUnit;
    }

    public MiniMap getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(MiniMap miniMap) {
        this.miniMap = miniMap;
    }
}
