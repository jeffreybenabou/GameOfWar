package GameCore;

import ObjectPackege.Factory;
import ObjectPackege.Unit;
import Units.AirUnits.AntiAir;
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
            timeToTrain.setBounds(MainFrame.world.getUnitTrainMenu().getUnitQueue().getX()+MainFrame.world.getUnitTrainMenu().getUnitQueue().getX()/10,MainFrame.world.getUnitTrainMenu().getHeight()/2+MainFrame.world.getUnitTrainMenu().getHeight()/10,MainFrame.world.getUnitTrainMenu().getWidth()/10,MainFrame.world.getUnitTrainMenu().getHeight()/3);
            MainFrame.world.getUnitTrainMenu().add(timeToTrain);

        }
        if(factory.getType()==9)
        {
            timeToTrain.setBounds(MainFrame.world.getMechanicMenu().getUnitQueue().getX()+MainFrame.world.getMechanicMenu().getUnitQueue().getX()/10,MainFrame.world.getMechanicMenu().getHeight()/2+MainFrame.world.getMechanicMenu().getHeight()/10,MainFrame.world.getMechanicMenu().getWidth()/10,MainFrame.world.getMechanicMenu().getHeight()/3);
            MainFrame.world.getMechanicMenu().add(timeToTrain);

        }
        allTimeToTrainLabel.add(timeToTrain);
    }
    public  void setTheQueue(final Unit unitToAdd){

        setTheQuaqeVisible();

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
                    timeToTrain.setText("<html>next unit:<br> none</html>");

                if(unitToAdd.getNameOfObject().equals("Armored Infentry"))
                {
                    ArmoredInfentry armoredInfentry=new ArmoredInfentry();
                    armoredInfentry.setTheUnitMethod();
                    armoredInfentry.setLocation((int)point.getX(),(int)point.getY()+armoredInfentry.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(armoredInfentry);
                    World.allUnit.add(armoredInfentry);
                    World.allObjects.add(armoredInfentry);
                }
                else if(unitToAdd.getNameOfObject().equals("Infantry"))
                {
                    Infantry infantry=new Infantry();
                    infantry.setTheUnitMethod();
                    infantry.setLocation((int)point.getX(),(int)point.getY()+infantry.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(infantry);
                    World.allUnit.add(infantry);
                    World.allObjects.add(infantry);
                }
                else if(unitToAdd.getNameOfObject().equals("Medic"))
                {
                    Medic unit=new Medic();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                    World.allObjects.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("Bazzoka Unit"))
                {
                    BazzokaUnit unit=new BazzokaUnit();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                    World.allObjects.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("sniper"))
                {
                    Sniper unit=new Sniper();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                    World.allObjects.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("tank"))
                {
                    Tank unit=new Tank();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*2);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                    World.allObjects.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("mini gun"))
                {
                    MiniGun unit=new MiniGun();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*2);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                    World.allObjects.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("anti air"))
                {
                    AntiAirTank unit=new AntiAirTank();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*2);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                    World.allObjects.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("big boss"))
                {
                    BigBoss unit=new BigBoss();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*2);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                    World.allObjects.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("Super tank"))
                {
                    SuperTank unit=new SuperTank();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*2);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                    World.allObjects.add(unit);
                }

            }
        }).start();






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
