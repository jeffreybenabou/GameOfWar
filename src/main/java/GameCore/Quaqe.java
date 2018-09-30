package GameCore;

import ImageHandel.ImageLoader;
import ObjectPackege.Factory;
import ObjectPackege.Unit;
import Units.AirUnits.AntiAir;
import Units.AirUnits.Choper;
import Units.AirUnits.SpaceShip;
import Units.InfantryUnit.*;
import Units.MechanicUnits.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;

public class Quaqe {

    private int unitsInQuaqe=0;
    private  int timeToWait=0;
    public static ArrayList<JLabel>allTimeToTrainLabel=new ArrayList<JLabel>();
    private Factory factory;
    private JLabel timeToTrain;



    public Quaqe(Factory factory){
        this.factory=factory;
        setTheTimeToTrain();
    }

    public void setTheTimeToTrain() {

        timeToTrain=new JLabel("<html>next unit:<br> 0</html>");
        timeToTrain.setVisible(false);
        if(factory.getType()==3)
        {
            timeToTrain.setBounds(MainFrame.gamePanel.getUnitTrainMenu().getUnitQueue().getX()+MainFrame.gamePanel.getUnitTrainMenu().getUnitQueue().getX()/10,MainFrame.gamePanel.getUnitTrainMenu().getHeight()/2+MainFrame.gamePanel.getUnitTrainMenu().getHeight()/10,MainFrame.gamePanel.getUnitTrainMenu().getWidth()/10,MainFrame.gamePanel.getUnitTrainMenu().getHeight()/3);
            MainFrame.gamePanel.getUnitTrainMenu().add(timeToTrain);

        }
        if(factory.getType()==4)
        {
            timeToTrain.setBounds(MainFrame.gamePanel.getUnitTrainMenu().getUnitQueue().getX()+MainFrame.gamePanel.getUnitTrainMenu().getUnitQueue().getX()/10,MainFrame.gamePanel.getUnitTrainMenu().getHeight()/2+MainFrame.gamePanel.getUnitTrainMenu().getHeight()/10,MainFrame.gamePanel.getUnitTrainMenu().getWidth()/10,MainFrame.gamePanel.getUnitTrainMenu().getHeight()/3);
            MainFrame.gamePanel.getUnitTrainMenu().add(timeToTrain);

        }
        if(factory.getType()==9)
        {
            timeToTrain.setBounds(MainFrame.gamePanel.getMechanicMenu().getUnitQueue().getX()+MainFrame.gamePanel.getMechanicMenu().getUnitQueue().getX()/10,MainFrame.gamePanel.getMechanicMenu().getHeight()/2+MainFrame.gamePanel.getMechanicMenu().getHeight()/10,MainFrame.gamePanel.getMechanicMenu().getWidth()/10,MainFrame.gamePanel.getMechanicMenu().getHeight()/3);
            MainFrame.gamePanel.getMechanicMenu().add(timeToTrain);

        }
        allTimeToTrainLabel.add(timeToTrain);
    }
    public  void setTheQueue(final Unit unitToAdd, final Factory typeOfFactory){

        if(StaticVariables.sumOfMoney>=unitToAdd.getCostToBuild()&&StaticVariables.unitHas<StaticVariables.UNIT_LIMIT)
        {
            setTheQuaqeVisible();
            StaticVariables.sumOfMoney-=unitToAdd.getCostToBuild();
            final Point point=factory.getLocation();
            if(unitsInQuaqe>0)
                timeToWait+=unitToAdd.getTimeToTrain();
            else
                timeToWait=0;


            unitsInQuaqe++;










            new Thread(new Runnable() {
                public void run() {
                    int timeOfUnit=unitToAdd.getTimeToTrain();


                    try {

                        Thread.sleep(timeToWait*1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    while (timeOfUnit>0)
                    {
                        if(typeOfFactory.getType()==3)
                            MainFrame.gamePanel.getUnitTrainMenu().getUnitQueue().setIcon(new ImageIcon(unitToAdd.getImage().getScaledInstance( MainFrame.gamePanel.getUnitTrainMenu().getUnitQueue().getWidth(), MainFrame.gamePanel.getUnitTrainMenu().getUnitQueue().getHeight(),4)));
                        else if(typeOfFactory.getType()==9)
                            MainFrame.gamePanel.getMechanicMenu().getUnitQueue().setIcon(new ImageIcon(unitToAdd.getImage().getScaledInstance( MainFrame.gamePanel.getMechanicMenu().getUnitQueue().getWidth(), MainFrame.gamePanel.getMechanicMenu().getUnitQueue().getHeight(),4)));
                        else if(typeOfFactory.getType()==4)
                            MainFrame.gamePanel.getAirUnitMenu().getUnitQueue().setIcon(new ImageIcon(unitToAdd.getImage().getScaledInstance( MainFrame.gamePanel.getAirUnitMenu().getUnitQueue().getWidth(), MainFrame.gamePanel.getAirUnitMenu().getUnitQueue().getHeight(),4)));

                        timeToTrain.setText("<html>next unit:<br> "+timeOfUnit+"</html>");
                        timeOfUnit--;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }


                    timeToWait-=unitToAdd.getTimeToTrain();
                    unitsInQuaqe--;
                    if(unitsInQuaqe==0)
                    {
                        ImageLoader imageLoader=new ImageLoader();
                        MainFrame.gamePanel.getUnitTrainMenu().getUnitQueue().setIcon(new ImageIcon(imageLoader.loadImage("image/panel/box.png").getScaledInstance(MainFrame.gamePanel.getUnitTrainMenu().getBoxBackGround().getWidth(),MainFrame.gamePanel.getUnitTrainMenu().getBoxBackGround().getHeight(),4)));
                        MainFrame.gamePanel.getMechanicMenu().getUnitQueue().setIcon(new ImageIcon(imageLoader.loadImage("image/panel/box.png").getScaledInstance(MainFrame.gamePanel.getMechanicMenu().getBoxBackGround().getWidth(),MainFrame.gamePanel.getMechanicMenu().getBoxBackGround().getHeight(),4)));
                        MainFrame.gamePanel.getAirUnitMenu().getUnitQueue().setIcon(new ImageIcon(imageLoader.loadImage("image/panel/box.png").getScaledInstance(MainFrame.gamePanel.getAirUnitMenu().getBoxBackGround().getWidth(),MainFrame.gamePanel.getAirUnitMenu().getBoxBackGround().getHeight(),4)));

                        timeToTrain.setText("<html>next unit:<br> none</html>");

                    }

                    switch (unitToAdd.getNameOfObject()) {
                        case "Armored Infentry":
                            ArmoredInfentry armoredInfentry = new ArmoredInfentry(true);
                            armoredInfentry.setTheUnitMethod();
                            armoredInfentry.setLocation((int) point.getX(), (int) point.getY() + armoredInfentry.getHeight() * 5);
                            MainFrame.world.getBackGroundImage().add(armoredInfentry, 11);
                            World.allUnit.add(armoredInfentry);
                            World.allObjects.add(armoredInfentry);
                            break;
                        case "Infantry":
                            Infantry infantry = new Infantry(true);
                            infantry.setTheUnitMethod();
                            infantry.setLocation((int) point.getX(), (int) point.getY() + infantry.getHeight() * 5);
                            MainFrame.world.getBackGroundImage().add(infantry, 10);
                            World.allUnit.add(infantry);
                            World.allObjects.add(infantry);
                            break;
                        case "Medic": {
                            Medic unit = new Medic(true);
                            unit.setTheUnitMethod();
                            unit.addLifeToUnit();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 5);
                            MainFrame.world.getBackGroundImage().add(unit, 9);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);
                            break;
                        }
                        case "Bazzoka Unit": {
                            BazzokaUnit unit = new BazzokaUnit(true);
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 5);
                            MainFrame.world.getBackGroundImage().add(unit, 8);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);
                            break;
                        }
                        case "sniper": {
                            Sniper unit = new Sniper(true);
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 5);
                            MainFrame.world.getBackGroundImage().add(unit, 7);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);
                            break;
                        }
                        case "tank": {
                            Tank unit = new Tank(true);
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 2);
                            MainFrame.world.getBackGroundImage().add(unit, 6);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);
                            break;
                        }
                        case "mini gun": {
                            MiniGun unit = new MiniGun(true);
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 2);
                            MainFrame.world.getBackGroundImage().add(unit, 5);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);
                            break;
                        }
                        case "anti air": {
                            AntiAirTank unit = new AntiAirTank(true);
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 2);
                            MainFrame.world.getBackGroundImage().add(unit, 4);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);
                            break;
                        }
                        case "big boss": {
                            BigBoss unit = new BigBoss(true);
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 2);
                            MainFrame.world.getBackGroundImage().add(unit, 3);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);
                            break;
                        }
                        case "Anti air plane": {
                            AntiAir unit = new AntiAir(true);
                            unit.floatShip();
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 2);
                            MainFrame.world.getBackGroundImage().add(unit, 2);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);

                            break;
                        }
                        case "Chopper": {
                            Choper unit = new Choper(true);
                            unit.floatShip();
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 2);
                            MainFrame.world.getBackGroundImage().add(unit, 1);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);


                            break;
                        }
                        case "Space Ship": {
                            SpaceShip unit = new SpaceShip(true);
                            unit.floatShip();
                            unit.setTheUnitMethod();
                            unit.setLocation((int) point.getX(), (int) point.getY() + unit.getHeight() * 2);
                            MainFrame.world.getBackGroundImage().add(unit, 0);
                            World.allUnit.add(unit);
                            World.allObjects.add(unit);

                            break;
                        }
                    }
                    StaticVariables.unitHas++;
                    MainFrame.gamePanel.changeTheText();


                }
            }).start();

        }
        else
        {
            JOptionPane.showMessageDialog(null,"not enough money");
        }






    }

    public void setTheQuaqeVisible() {
        for (JLabel label:allTimeToTrainLabel) {
            if(label.equals(timeToTrain))
                label.setVisible(true);
            else
                label.setVisible(false);

        }
    }

}
