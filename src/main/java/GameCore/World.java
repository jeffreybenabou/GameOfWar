package GameCore;

import ObjectPackege.*;
import Units.AirUnits.AntiAir;
import Units.AirUnits.Choper;
import Units.AirUnits.SpaceShip;
import Units.Factory.*;
import Units.InfantryUnit.*;
import Units.MechanicUnits.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class World extends JLayeredPane  {

    public static ArrayList<Factory> airFactory;
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
    private AirUnitsMenu airUnitMenu;
    public JLabel unitsPickRectangle;


    public World(boolean isTesting) {
        this.isTesting = isTesting;


        allUnit = new ArrayList<Unit>();
        airFactory=new ArrayList<Factory>();
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
        setTheUnitPickRectangle();
        setBackground(Color.cyan);
        setLayout(null);
        checkIfIntersect();


    }

    private void setTheUnitPickRectangle() {

        unitsPickRectangle=new JLabel();
        unitsPickRectangle.setOpaque(true);
        unitsPickRectangle.setBorder(        BorderFactory.createLineBorder(Color.yellow, 1));
        unitsPickRectangle.setBackground(new Color(0,0,0,100));
        unitsPickRectangle.setVisible(false);
        backGroundImage.add(unitsPickRectangle,0);
    }

    public void checkIfIntersect(){


        new Thread(new Runnable() {
            public void run() {
                while (true)
                {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i=0;i<allUnit.size();i++) {
                        if(!allUnit.get(i).isUnitHasBeenCheckForIntersect()&&allUnit.get(i).isObjectIsStanding())
                        {
                            if(allUnit.get(i).getClass().getPackage().getName().contains("Air"))
                            checkTheAirUnitsIntersection(allUnit.get(i));
                            else
                                checkTheGroundUnitsIntersection(allUnit.get(i));


                        }
                    }
                }
            }
        }).start();









    }

            private void checkTheGroundUnitsIntersection(final Unit unit) {

                new Thread(new Runnable() {
                    public void run() {

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < World.allObjects.size(); i++) {
                            if (!World.allObjects.get(i).getClass().getPackage().getName().contains("Air")
                                    && World.allObjects.get(i) != unit)

                            {


                                Random random=new Random();
                                int num2=random.nextInt(1)+30;
                                int num=1;
                                if(random.nextBoolean())
                                {
                                    num*=-1;
                                    num2*=-1;
                                }



                                if (World.allObjects.get(i).getClass().getPackage().getName().contains("InfantryUnit"))
                                    if (World.allObjects.get(i).calculateTheDistanceBetweenUnits(unit) <= 30) {
                                        unit.setBound(new Rectangle(unit.getX() + 30*num, unit.getY() + num2, unit.getWidth(), unit.getHeight()));
                                        unit.setBounds(unit.getBound());
                                    }

                                if (World.allObjects.get(i).getClass().getPackage().getName().contains("MechanicUnits"))
                                    if (World.allObjects.get(i).calculateTheDistanceBetweenUnits(unit) <= 70) {
                                        unit.setBound(new Rectangle(unit.getX()  + 70*num, unit.getY() + num2, unit.getWidth(), unit.getHeight()));
                                        unit.setBounds(unit.getBound());
                                    }

                                if (World.allObjects.get(i).getClass().getPackage().getName().contains("Factory"))
                                    if (World.allObjects.get(i).calculateTheDistanceBetweenUnits(unit) <= World.allObjects.get(i).getWidth()) {
                                        unit.setBound(new Rectangle(unit.getX() + World.allObjects.get(i).getWidth(), unit.getY(), unit.getWidth(), unit.getHeight()));
                                        unit.setBounds(unit.getBound());
                                    }
                            }




                        }


                    }
                }).start();


            }

                private void checkTheAirUnitsIntersection(final Unit unit) {
        new Thread(new Runnable() {
            public void run() {

                    for (int b = 0; b < World.allUnit.size(); b++) {

                            if (World.allUnit.get(b).getClass().getPackage().getName().contains("Air")
                                    && World.allUnit.get(b) != unit
                                    && World.allUnit.get(b).calculateTheDistanceBetweenUnits(unit) <= 50
                                    ) {

                                Random random = new Random();
                                if (random.nextBoolean())
                                    unit.setBound(new Rectangle(unit.getX() + 70, unit.getY() + random.nextInt(10) + 40, unit.getWidth(), unit.getHeight()));
                                else
                                    unit.setBound(new Rectangle(unit.getX() - 70, unit.getY() - random.nextInt(10) + 40, unit.getWidth(), unit.getHeight()));


                                unit.setBounds(unit.getBound());
                            }

                    }
                unit.setUnitHasBeenCheckForIntersect(true);
                }

        }).start();


    }

    public void addMiniMap()
    {
        miniMap =new MiniMap();

         buildingMenu =new BuildingMenu();
         unitTrainMenu=new UnitTrainMenu();
         mechanicMenu=new MechanicMenu();
         airUnitMenu=new AirUnitsMenu();

        getBackGroundImage().add(buildingMenu,0);
        getBackGroundImage().add(unitTrainMenu,0);
        getBackGroundImage().add(mechanicMenu,1);
        getBackGroundImage().add(airUnitMenu,2);
        getBackGroundImage().add(miniMap,3);



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

                if(checkEnoughMoney(Factory.factory))
                {
                    Factory.objectIsFlotingWorld=true;
                    Factory.factory.setTheLocation();
                    StaticVariables.sumOfMoney-=Factory.factory.getCostToBuild();
                    backGroundImage.add( Factory.factory);
                    World.allObjects.add(Factory.factory);
                }


                break;
           case 2:
               MoneyFactory factory=new MoneyFactory();
               if(checkEnoughMoney(factory)) {
                   factory.gainMoney();
                   Factory.factory = factory;
                   StaticVariables.sumOfMoney -= Factory.factory.getCostToBuild();
                   Factory.objectIsFlotingWorld = true;
                   factory.setTheLocation();
                   backGroundImage.add(factory);
                   World.allObjects.add(factory);
               }
                break;
            case 3:
                InfentryFactory infentryFactory=new InfentryFactory(true);
                if(checkEnoughMoney(infentryFactory))
                {
                    Factory.factory=infentryFactory;
                    StaticVariables.sumOfMoney-=Factory.factory.getCostToBuild();
                    Factory.objectIsFlotingWorld=true;
                    infentryFactory.setTheLocation();
                    backGroundImage.add(infentryFactory);
                    World.allObjects.add(infentryFactory);
                }


                break;
            case 4:
                TankFactory tankFactory=new TankFactory(true);
                if(checkEnoughMoney(tankFactory))
                {
                    Factory.factory=tankFactory;
                    StaticVariables.sumOfMoney-=Factory.factory.getCostToBuild();
                    Factory.objectIsFlotingWorld=true;
                    tankFactory.setTheLocation();
                    backGroundImage.add(tankFactory);
                    World.allObjects.add(tankFactory);
                }


                break;
            case 5:
                SateliteFactory sateliteFactory=new SateliteFactory();
                if(checkEnoughMoney(sateliteFactory))
                {
                    Factory.factory=sateliteFactory;
                    StaticVariables.sumOfMoney-=Factory.factory.getCostToBuild();
                    Factory.objectIsFlotingWorld=true;
                    sateliteFactory.setTheLocation();
                    backGroundImage.add(sateliteFactory);
                    World.allObjects.add(sateliteFactory);
                }



                break;
            case 6:
                AirForceFactory airForceFactory=new AirForceFactory(true);
                if(checkEnoughMoney(airForceFactory))
                {
                    Factory.factory=airForceFactory;
                    StaticVariables.sumOfMoney-=Factory.factory.getCostToBuild();
                    Factory.objectIsFlotingWorld=true;
                    airForceFactory.setTheLocation();
                    backGroundImage.add(airForceFactory);
                    World.allObjects.add(airForceFactory);
                }



                break;
            case 7:
                SuperWeponeFactory superWeponeFactory=new SuperWeponeFactory();
                if(checkEnoughMoney(superWeponeFactory))

                {
                    Factory.factory=superWeponeFactory;
                    StaticVariables.sumOfMoney-=Factory.factory.getCostToBuild();
                    Factory.objectIsFlotingWorld=true;
                    superWeponeFactory.setTheLocation();
                    backGroundImage.add(superWeponeFactory);
                    World.allObjects.add(superWeponeFactory);
                }


                break;
            case 8:
                SpacielOpsFactory spacielOpsFactory=new SpacielOpsFactory();
                if(checkEnoughMoney(spacielOpsFactory))

                {
                    Factory.factory=spacielOpsFactory;
                    StaticVariables.sumOfMoney-=Factory.factory.getCostToBuild();
                    Factory.objectIsFlotingWorld=true;
                    spacielOpsFactory.setTheLocation();
                    backGroundImage.add(spacielOpsFactory);
                    World.allObjects.add(spacielOpsFactory);
                }

                break;
            case 9:
                CloneFactory cloneFactory=new CloneFactory();
                if(checkEnoughMoney(cloneFactory))
                {
                    Factory.factory=cloneFactory;
                    StaticVariables.sumOfMoney-=Factory.factory.getCostToBuild();
                    Factory.objectIsFlotingWorld=true;
                    cloneFactory.setTheLocation();
                    backGroundImage.add(cloneFactory);
                    World.allObjects.add(cloneFactory);
                }



                break;


        }


    }

    private boolean checkEnoughMoney(Factory factory) {
        if(factory.getCostToBuild()<=StaticVariables.sumOfMoney)
        return true;
        else
        {
            JOptionPane.showMessageDialog(null,"not enough money");
            return false;

        }
    }

    private void setTheBackGroundWorld() {

                backGroundImage=new JLabel();
                backGroundImage.setBounds(0,0,MainFrame.screenSize.width*10,MainFrame.screenSize.height*20);
                backGroundImage.setBackground(Color.black);
                backGroundImage.setOpaque(true);
                backGroundImage.addMouseListener(MainFrame.mainFrame);
        backGroundImage.addMouseMotionListener(MainFrame.mainFrame);

                add(backGroundImage);




    }
    public void addUnitToQuaqe(MouseEvent e, int typeOfFactory) {


        if(typeOfFactory==0)
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    factoryPreesed.getQuaqe().setTheQueue(new ArmoredInfentry(),factoryPreesed);
                    break;
                case 1:
                    factoryPreesed.getQuaqe().setTheQueue(new Infantry(),factoryPreesed);
                    break;
                case 2:
                    factoryPreesed.getQuaqe().setTheQueue(new Medic(),factoryPreesed);
                    break;
                case 3:
                    factoryPreesed.getQuaqe().setTheQueue(new Sniper(),factoryPreesed);
                    break;
                case 4:
                    factoryPreesed.getQuaqe().setTheQueue(new BazzokaUnit(),factoryPreesed);
                    break;
            }
        else if(typeOfFactory==1)
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    factoryPreesed.getQuaqe().setTheQueue(new Tank(),factoryPreesed);
                    break;
                case 1:
                    factoryPreesed.getQuaqe().setTheQueue(new MiniGun(),factoryPreesed);
                    break;
                case 2:
                    factoryPreesed.getQuaqe().setTheQueue(new SuperTank(),factoryPreesed);
                    break;
                case 3:
                    factoryPreesed.getQuaqe().setTheQueue(new AntiAirTank(),factoryPreesed);
                    break;
                case 4:
                    factoryPreesed.getQuaqe().setTheQueue(new BigBoss(),factoryPreesed);
                    break;
            }
        else if(typeOfFactory==2)
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    factoryPreesed.getQuaqe().setTheQueue(new AntiAir(),factoryPreesed);
                    break;
                case 1:
                    factoryPreesed.getQuaqe().setTheQueue(new Choper(),factoryPreesed);
                    break;
                case 2:
                    factoryPreesed.getQuaqe().setTheQueue(new SpaceShip(),factoryPreesed);
                    break;
            }

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

    public AirUnitsMenu getAirUnitMenu() {
        return airUnitMenu;
    }

    public void setAirUnitMenu(AirUnitsMenu airUnitMenu) {
        this.airUnitMenu = airUnitMenu;
    }

    public static ArrayList<Factory> getAirFactory() {
        return airFactory;
    }

    public static void setAirFactory(ArrayList<Factory> airFactory) {
        World.airFactory = airFactory;
    }

    public JLabel getUnitsPickRectangle() {
        return unitsPickRectangle;
    }

    public void setUnitsPickRectangle(JLabel unitsPickRectangle) {
        this.unitsPickRectangle = unitsPickRectangle;
    }
}

