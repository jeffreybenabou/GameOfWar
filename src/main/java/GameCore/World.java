package GameCore;

import ObjectPackege.Factory;
import ObjectPackege.GameObject;
import ObjectPackege.HumanUnit;
import ObjectPackege.Unit;
import Units.Factory.*;
import Units.InfantryUnit.*;
import Units.MechanicUnits.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class World extends JPanel {

    private JLabel backGroundImage;
    private boolean isTesting;
    public static ArrayList<Unit> allUnit;
    public static ArrayList<GameObject>allObjects;
    public static ArrayList<Factory> infentryFactory;
    public static ArrayList<Factory> tankFactory;
    public static ArrayList<Factory>allFactorys;
    private MiniMap miniMap;
    private BuildingMenu buildingMenu;
    private UnitTrainMenu unitTrainMenu;
    private MechanicMenu mechanicMenu;
    public static Factory factoryPreesed;
    private HumanUnit human;


    public World(boolean isTesting) {
        this.isTesting = isTesting;
        allUnit = new ArrayList<Unit>();
        tankFactory =new ArrayList<Factory>();
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
        checkIfIntersect();


    }

    public void checkIfIntersect(){
        new Thread(new Runnable() {
            public void run() {
                Random  random=new Random();
                while (true) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < World.allUnit.size(); i++) {
                        for (int j = 0; j <World.allObjects.size() ; j++) {
                            while (World.allUnit.get(i).isObjectIsStanding()&&World.allUnit.get(i)!=World.allObjects.get(j)&&World.allUnit.get(i).calculateTheDistanceBetweenUnits(World.allObjects.get(j))<=30)
                            {
                                World.allUnit.get(i).setBound(new Rectangle(World.allUnit.get(i).getX()+1,World.allUnit.get(i).getY(),World.allUnit.get(i).getWidth(),World.allUnit.get(i).getHeight()));
                                World.allUnit.get(i).setBounds(World.allUnit.get(i).getBound());
                            }
                        }


                    }

                }
            }
        }).start();

    }

    public void addMiniMap()
    {
        miniMap =new MiniMap();
        getBackGroundImage().add(miniMap);
         buildingMenu =new BuildingMenu();
         unitTrainMenu=new UnitTrainMenu();
         mechanicMenu=new MechanicMenu();

        getBackGroundImage().add(buildingMenu);
        getBackGroundImage().add(unitTrainMenu);
        getBackGroundImage().add(mechanicMenu);




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
                World.allObjects.add(Factory.factory);

                break;
           case 2:
               MoneyFactory factory=new MoneyFactory();
               Factory.factory=factory;
               Factory.objectIsFlotingWorld=true;
               factory.setTheLocation();
               backGroundImage.add(factory);
               World.allObjects.add(factory);

                break;
            case 3:
                InfentryFactory infentryFactory=new InfentryFactory(true);
                Factory.factory=infentryFactory;
                Factory.objectIsFlotingWorld=true;
                infentryFactory.setTheLocation();
                backGroundImage.add(infentryFactory);
                World.allObjects.add(infentryFactory);

                break;
            case 4:
                TankFactory tankFactory=new TankFactory(true);
                Factory.factory=tankFactory;
                Factory.objectIsFlotingWorld=true;
                tankFactory.setTheLocation();
                backGroundImage.add(tankFactory);
                World.allObjects.add(tankFactory);

                break;
            case 5:
                SateliteFactory sateliteFactory=new SateliteFactory();
                Factory.factory=sateliteFactory;
                Factory.objectIsFlotingWorld=true;
                sateliteFactory.setTheLocation();
                backGroundImage.add(sateliteFactory);
                World.allObjects.add(sateliteFactory);

                break;
            case 6:
                AirForceFactory airForceFactory=new AirForceFactory();
                Factory.factory=airForceFactory;
                Factory.objectIsFlotingWorld=true;
                airForceFactory.setTheLocation();
                backGroundImage.add(airForceFactory);
                World.allObjects.add(airForceFactory);


                break;
            case 7:
                SuperWeponeFactory superWeponeFactory=new SuperWeponeFactory();
                Factory.factory=superWeponeFactory;
                Factory.objectIsFlotingWorld=true;
                superWeponeFactory.setTheLocation();
                backGroundImage.add(superWeponeFactory);
                World.allObjects.add(superWeponeFactory);

                break;
            case 8:
                SpacielOpsFactory spacielOpsFactory=new SpacielOpsFactory();
                Factory.factory=spacielOpsFactory;

                Factory.objectIsFlotingWorld=true;
                spacielOpsFactory.setTheLocation();
                backGroundImage.add(spacielOpsFactory);
                World.allObjects.add(spacielOpsFactory);
                break;
            case 9:
                CloneFactory cloneFactory=new CloneFactory();
                Factory.factory=cloneFactory;
                Factory.objectIsFlotingWorld=true;
                cloneFactory.setTheLocation();
                backGroundImage.add(cloneFactory);
                World.allObjects.add(cloneFactory);

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

    public static ArrayList<Unit> getAllUnit() {
        return allUnit;
    }

    public static void setAllUnit(ArrayList<Unit> allUnit) {
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

    public static ArrayList<Factory> getTankFactory() {
        return tankFactory;
    }

    public static void setTankFactory(ArrayList<Factory> tankFactory) {
        World.tankFactory = tankFactory;
    }

    public MechanicMenu getMechanicMenu() {
        return mechanicMenu;
    }

    public void setMechanicMenu(MechanicMenu mechanicMenu) {
        this.mechanicMenu = mechanicMenu;
    }

    public HumanUnit getHuman() {
        return human;
    }

    public void setHuman(HumanUnit human) {
        this.human = human;
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

    public void addUnitToQuaqe(MouseEvent e,int typeOfFactory) {


        System.out.println(typeOfFactory);
        System.out.println(factoryPreesed.getClass().getPackage());
        if(typeOfFactory==0)
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
        else if(typeOfFactory==1)
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    factoryPreesed.getQuaqe().setTheQueue(new Tank());
                    break;
                case 1:
                    factoryPreesed.getQuaqe().setTheQueue(new MiniGun());
                    break;
                case 2:
                    factoryPreesed.getQuaqe().setTheQueue(new SuperTank());
                    break;
                case 3:
                    factoryPreesed.getQuaqe().setTheQueue(new AntiAirTank());
                    break;
                case 4:
                    factoryPreesed.getQuaqe().setTheQueue(new BigBoss());
                    break;
            }

    }


}
