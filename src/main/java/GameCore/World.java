package GameCore;

import ObjectPackege.Factory;
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
    public static ArrayList<Factory>factoryWhoCanBuild;
    private MiniMap miniMap;
    private BuildingAndUnitsMenu buildingAndUnitsMenu;

    private Unit tempUnit;

    public World(boolean isTesting) {
        this.isTesting = isTesting;
        allUnit = new ArrayList<Unit>();
        allObjects=new ArrayList<GameObject>();
        factoryWhoCanBuild=new ArrayList<Factory>();
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
         buildingAndUnitsMenu=new BuildingAndUnitsMenu();
        getBackGroundImage().add(buildingAndUnitsMenu);

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

    public static ArrayList<GameObject> getAllObjects() {
        return allObjects;
    }

    public static void setAllObjects(ArrayList<GameObject> allObjects) {
        World.allObjects = allObjects;
    }

    public BuildingAndUnitsMenu getBuildingAndUnitsMenu() {
        return buildingAndUnitsMenu;
    }

    public void setBuildingAndUnitsMenu(BuildingAndUnitsMenu buildingAndUnitsMenu) {
        this.buildingAndUnitsMenu = buildingAndUnitsMenu;
    }

    public MiniMap getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(MiniMap miniMap) {
        this.miniMap = miniMap;
    }
}
