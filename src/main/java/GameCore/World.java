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

public class World extends JLabel  {

    public static ArrayList<Factory> airFactory;
    private JLabel backGroundImage;
    public static ArrayList<Unit> allUnit;

    public static ArrayList<GameObject>allObjects;
    public static ArrayList<GameObject>allEnemyObjects;
    public static ArrayList<Factory> infentryFactory;
    public static ArrayList<Factory> tankFactory;
    public static ArrayList<Factory>allFactorys;
    public static ArrayList<GameObject>allObjectsOnMapIncludeEnemy;
    public static Factory factoryPreesed;
    private HumanUnit human;

    public JLabel unitsPickRectangle;
    private MainFactory mainFactory;


    public World() {


        setTheBackGroundWorld();
        setTheUnitPickRectangle();
        checkIfIntersect();

        setBounds(0,0, MainFrame.screenSize.width, MainFrame.screenSize.height);







        setLayout(null);





    }

    public static void initTheArrayList()
    {
        allObjectsOnMapIncludeEnemy=new ArrayList<>();
        allEnemyObjects=new ArrayList<>();
        allUnit = new ArrayList<Unit>();
        airFactory=new ArrayList<Factory>();
        tankFactory =new ArrayList<Factory>();
        allObjects=new ArrayList<GameObject>();
        infentryFactory =new ArrayList<Factory>();
        allFactorys=new ArrayList<Factory>();
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



                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
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
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }





            }

                private void checkTheAirUnitsIntersection(final Unit unit) {

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






    public void setTheTestScreen() {
        mainFactory = new MainFactory(true);
        mainFactory.setLocation(400,400);
        mainFactory.setTheFactoryMethod();


        for (int i = 0; i < 10; i++) {
            BigBoss bigBoss2 = new BigBoss(true);
            bigBoss2.setGroup("not friendly");
            bigBoss2.setTheUnitMethod();

            bigBoss2.setLocation(getX() + i * 100, getY() + 2000);
            getBackGroundImage().add(bigBoss2);
            World.allEnemyObjects.add(bigBoss2);
        }


        mainFactory.repaint();
        mainFactory.revalidate();
        World.allObjects.add(mainFactory);
        World.allFactorys.add(mainFactory);
        getBackGroundImage().add(mainFactory);
    }

    public void addFactoryToWorld(MouseEvent e)
    {

        switch (Integer.parseInt(e.getComponent().getName()))
        {
            case 0:

                break;
            case 1:
                Factory.factory=new PowerFactory(true);

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
               MoneyFactory factory=new MoneyFactory(true);
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
                SateliteFactory sateliteFactory=new SateliteFactory(true);
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
                SuperWeponeFactory superWeponeFactory=new SuperWeponeFactory(true);
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
                SpacielOpsFactory spacielOpsFactory=new SpacielOpsFactory(true);
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
                CloneFactory cloneFactory=new CloneFactory(true);
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

                backGroundImage.addMouseListener(MainFrame.mainFrame);
                backGroundImage.addMouseMotionListener(MainFrame.mainFrame);



                add(backGroundImage);




    }
    public void addUnitToQuaqe(MouseEvent e, int typeOfFactory) {


        if(typeOfFactory==0)
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    factoryPreesed.getQuaqe().setTheQueue(new ArmoredInfentry(false),factoryPreesed);
                    break;
                case 1:
                    factoryPreesed.getQuaqe().setTheQueue(new Infantry(false),factoryPreesed);
                    break;
                case 2:
                    factoryPreesed.getQuaqe().setTheQueue(new Medic(false),factoryPreesed);
                    break;
                case 3:
                    factoryPreesed.getQuaqe().setTheQueue(new Sniper(false),factoryPreesed);
                    break;
                case 4:
                    factoryPreesed.getQuaqe().setTheQueue(new BazzokaUnit(false),factoryPreesed);
                    break;
            }
        else if(typeOfFactory==1)
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    factoryPreesed.getQuaqe().setTheQueue(new Tank(false),factoryPreesed);
                    break;
                case 1:
                    factoryPreesed.getQuaqe().setTheQueue(new MiniGun(false),factoryPreesed);
                    break;
                case 2:
                    factoryPreesed.getQuaqe().setTheQueue(new SuperTank(false),factoryPreesed);
                    break;
                case 3:
                    factoryPreesed.getQuaqe().setTheQueue(new AntiAirTank(false),factoryPreesed);
                    break;
                case 4:
                    factoryPreesed.getQuaqe().setTheQueue(new BigBoss(false),factoryPreesed);
                    break;
            }
        else if(typeOfFactory==2)
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    factoryPreesed.getQuaqe().setTheQueue(new AntiAir(false),factoryPreesed);
                    break;
                case 1:
                    factoryPreesed.getQuaqe().setTheQueue(new Choper(false),factoryPreesed);
                    break;
                case 2:
                    factoryPreesed.getQuaqe().setTheQueue(new SpaceShip(false),factoryPreesed);
                    break;
            }

    }


    public JLabel getBackGroundImage() {
        return backGroundImage;
    }

    public void setBackGroundImage(JLabel backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    public static ArrayList<GameObject> getAllEnemyObjects() {
        return allEnemyObjects;
    }

    public static void setAllEnemyObjects(ArrayList<GameObject> allEnemyObjects) {
        World.allEnemyObjects = allEnemyObjects;
    }




    public static ArrayList<GameObject> getAllObjects() {
        return allObjects;
    }

    public static void setAllObjects(ArrayList<GameObject> allObjects) {
        World.allObjects = allObjects;
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



    public HumanUnit getHuman() {
        return human;
    }

    public void setHuman(HumanUnit human) {
        this.human = human;
    }

    public static void setFactoryPreesed(Factory factoryPreesed) {
        World.factoryPreesed = factoryPreesed;
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

    public static ArrayList<GameObject> getAllObjectsOnMapIncludeEnemy() {
        return allObjectsOnMapIncludeEnemy;
    }

    public static void setAllObjectsOnMapIncludeEnemy(ArrayList<GameObject> allObjectsOnMapIncludeEnemy) {
        World.allObjectsOnMapIncludeEnemy = allObjectsOnMapIncludeEnemy;
    }

    public MainFactory getMainFactory() {
        return mainFactory;
    }

    public void setMainFactory(MainFactory mainFactory) {
        this.mainFactory = mainFactory;
    }

    public void setUnitsPickRectangle(JLabel unitsPickRectangle) {
        this.unitsPickRectangle = unitsPickRectangle;
    }
}

