package GameCore;

import ObjectPackege.Factory;
import ObjectPackege.Unit;
import Units.InfantryUnit.*;

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
    private Thread counter;


    public Quaqe(Factory factory){
        this.factory=factory;
        setTheTimeToTrain();
    }

    public void setTheTimeToTrain() {

        timeToTrain=new JLabel("<html>next unit:<br> 0</html>");
        timeToTrain.setBounds(MainFrame.world.getUnitTrainMenu().getUnitQueue().getX()+MainFrame.world.getUnitTrainMenu().getUnitQueue().getX()/10,MainFrame.world.getUnitTrainMenu().getHeight()/2+MainFrame.world.getUnitTrainMenu().getHeight()/10,MainFrame.world.getUnitTrainMenu().getWidth()/10,MainFrame.world.getUnitTrainMenu().getHeight()/3);
        timeToTrain.setVisible(false);
        MainFrame.world.getUnitTrainMenu().add(timeToTrain);
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

                }
                else if(unitToAdd.getNameOfObject().equals("Infantry"))
                {
                    Infantry infantry=new Infantry();
                    infantry.setTheUnitMethod();
                    infantry.setLocation((int)point.getX(),(int)point.getY()+infantry.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(infantry);
                    World.allUnit.add(infantry);

                }
                else if(unitToAdd.getNameOfObject().equals("Medic"))
                {
                    Medic unit=new Medic();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("Bazzoka Unit"))
                {
                    BazzokaUnit unit=new BazzokaUnit();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
                }
                else if(unitToAdd.getNameOfObject().equals("sniper"))
                {
                    Sniper unit=new Sniper();
                    unit.setTheUnitMethod();
                    unit.setLocation((int)point.getX(),(int)point.getY()+unit.getHeight()*5);
                    MainFrame.world.getBackGroundImage().add(unit);
                    World.allUnit.add(unit);
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
