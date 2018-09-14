package GameCore;

import ImageHandel.SpriteSheet;
import ObjectPackege.Factory;
import ObjectPackege.GameObject;
import ObjectPackege.HumanUnit;
import ObjectPackege.Unit;
import Units.Factory.*;
import Units.InfantryUnit.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class World extends JPanel {

    private JLabel backGroundImage;
    private boolean isTesting;
    public static ArrayList<HumanUnit> allUnit;
    public static ArrayList<GameObject>allObjects;
    public static ArrayList<Factory> infentryFactory;
    public static ArrayList<Factory>allFactorys;
    private MiniMap miniMap;
    private BuildingMenu buildingMenu;
    private UnitTrainMenu unitTrainMenu;
    public static Factory factoryPreesed;
    private HumanUnit human;


    public World(boolean isTesting) {
        this.isTesting = isTesting;
        allUnit = new ArrayList<HumanUnit>();

        allObjects=new ArrayList<GameObject>();
        infentryFactory =new ArrayList<Factory>();
        allFactorys=new ArrayList<Factory>();
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
         buildingMenu =new BuildingMenu();
         unitTrainMenu=new UnitTrainMenu();

        getBackGroundImage().add(buildingMenu);
        getBackGroundImage().add(unitTrainMenu);

        MainFrame.mainFrame.repaint();


    }




    private void setTheTestScreen() {

    }

    public void addFactoryToWorld(MouseEvent e)
    {
        switch (Integer.parseInt(e.getComponent().getName()))
        {
            case 0:

                break;
            case 1:

                Factory.factory=new PowerFactory();
                Factory.objectIsFlotingWorld=true;
                Factory.factory.setTheLocation();
                backGroundImage.add( Factory.factory);


                break;
           case 2:
               MoneyFactory factory=new MoneyFactory();
               Factory.factory=factory;
               Factory.objectIsFlotingWorld=true;
               factory.setTheLocation();
               backGroundImage.add(factory);

                break;
            case 3:
                InfentryFactory infentryFactory=new InfentryFactory(true);
                Factory.factory=infentryFactory;
                Factory.objectIsFlotingWorld=true;
                infentryFactory.setTheLocation();
                backGroundImage.add(infentryFactory);

                break;
            case 4:
                TankFactory tankFactory=new TankFactory();
                Factory.factory=tankFactory;
                Factory.objectIsFlotingWorld=true;
                tankFactory.setTheLocation();
                backGroundImage.add(tankFactory);

                break;
            case 5:
                SateliteFactory sateliteFactory=new SateliteFactory();
                Factory.factory=sateliteFactory;
                Factory.objectIsFlotingWorld=true;
                sateliteFactory.setTheLocation();
                backGroundImage.add(sateliteFactory);

                break;
            case 6:
                AirForceFactory airForceFactory=new AirForceFactory();
                Factory.factory=airForceFactory;
                Factory.objectIsFlotingWorld=true;
                airForceFactory.setTheLocation();
                backGroundImage.add(airForceFactory);


                break;
            case 7:
                SuperWeponeFactory superWeponeFactory=new SuperWeponeFactory();
                Factory.factory=superWeponeFactory;
                Factory.objectIsFlotingWorld=true;
                superWeponeFactory.setTheLocation();
                backGroundImage.add(superWeponeFactory);

                break;
            case 8:
                SpacielOpsFactory spacielOpsFactory=new SpacielOpsFactory();
                Factory.factory=spacielOpsFactory;

                Factory.objectIsFlotingWorld=true;
                spacielOpsFactory.setTheLocation();
                backGroundImage.add(spacielOpsFactory);
                break;
            case 9:
                CloneFactory cloneFactory=new CloneFactory();
                Factory.factory=cloneFactory;
                Factory.objectIsFlotingWorld=true;
                cloneFactory.setTheLocation();
                backGroundImage.add(cloneFactory);

                break;


        }

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


    public static ArrayList<GameObject> getAllObjects() {
        return allObjects;
    }

    public static void setAllObjects(ArrayList<GameObject> allObjects) {
        World.allObjects = allObjects;
    }

    public BuildingMenu getBuildingMenu() {
        return buildingMenu;
    }

    public static ArrayList<HumanUnit> getAllUnit() {
        return allUnit;
    }

    public static void setAllUnit(ArrayList<HumanUnit> allUnit) {
        World.allUnit = allUnit;
    }

    public static ArrayList<Factory> getInfentryFactory() {
        return infentryFactory;
    }

    public static void setInfentryFactory(ArrayList<Factory> infentryFactory) {
        World.infentryFactory = infentryFactory;
    }

    public static ArrayList<Factory> getAllFactorys() {
        return allFactorys;
    }

    public static void setAllFactorys(ArrayList<Factory> allFactorys) {
        World.allFactorys = allFactorys;
    }

    public static Factory getFactoryPreesed() {
        return factoryPreesed;
    }

    public static void setFactoryPreesed(Factory factoryPreesed) {
        World.factoryPreesed = factoryPreesed;
    }

    public UnitTrainMenu getUnitTrainMenu() {
        return unitTrainMenu;
    }

    public void setUnitTrainMenu(UnitTrainMenu unitTrainMenu) {
        this.unitTrainMenu = unitTrainMenu;
    }

    public void setBuildingMenu(BuildingMenu buildingMenu) {
        this.buildingMenu = buildingMenu;
    }

    public MiniMap getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(MiniMap miniMap) {
        this.miniMap = miniMap;
    }

    public void addUnitToQuaqe(MouseEvent e) {
        switch (Integer.parseInt(e.getComponent().getName()))
        {
            case 0:
                factoryPreesed.getQuaqe().setTheQueue(new ArmoredInfentry());
                break;
            case 1:
                factoryPreesed.getQuaqe().setTheQueue(new Infantry());
                break;
            case 2:
                factoryPreesed.getQuaqe().setTheQueue(new Medic());
                break;
            case 3:
                factoryPreesed.getQuaqe().setTheQueue(new Sniper());
                break;
            case 4:
                factoryPreesed.getQuaqe().setTheQueue(new BazzokaUnit());
                break;
        }


    }


}
