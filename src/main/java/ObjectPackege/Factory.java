package ObjectPackege;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Factory extends GameObject {

    protected Point moveTheBuildingAroundTheWorld=new Point(0,0);

    public Factory(Rectangle bound,boolean objectIsOnWorld) {

        super(bound,objectIsOnWorld);


    }

 /*   public void moveTheFactory(){
        *//*
        * this thread will move the factory around the world until the user pick the
        * place of the factory to be.
        *
        * *//*
        new Thread(()->{
            while (!objectIsPressed)
            {


                moveTheBuildingAroundTheWorld=(new Point(MouseInfo.getPointerInfo().getLocation().x- StaticVariables.world.getxWorld(),MouseInfo.getPointerInfo().getLocation().y-StaticVariables.world.getyWorld()));
                setLocation(moveTheBuildingAroundTheWorld);

            }




        }).start();
    }

// TODO: 29/04/2018 add factory intersect with other factory

    public  void addTheRightFactory(MouseEvent e) {
        Factory factory = null;
        switch (e.getSource().getClass().getSimpleName()) {

            case "PowerFactory":

            {

                factory = new PowerFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.POWER_FACTORY_WIDTH, StaticVariables.POWER_FACTORY_HEIGHT), true);
                break;
            }
            case "InfentryFactory":


            {
                factory = new InfentryFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.INFENTRY_FACTORY_WIDTH, StaticVariables.INFENTRY_FACTORY_HEIGHT), true);

                break;
            }
            case "MoneyFactory":

            {
                factory = new MoneyFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.MONEY_FACTORY_WIDTH, StaticVariables.MONEY_FACTORY_HEIGHT), true);
                break;
            }
            case "TankFactory":

            {
                factory = new TankFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.TANK_FACTORY_WIDTH, StaticVariables.TANK_FACTORY_HIEGHT), true);
                break;
            }
            case "SateliteFactory":

            {
                factory = new SateliteFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.SATELITE_FACTORY_WIDTH, StaticVariables.SATELITE_FACTORY_HEIGHT), true);
                break;
            }
            case "AirForceFactory":

            {
                factory = new AirForceFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.AIRFIELD_FACTORY_WIDTH, StaticVariables.AIRFIELD_FACTORY_HEIGHT), true);
                break;
            }
            case "SpacielOpsFactory":

            {
                factory = new SpacielOpsFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.SPECIALOPS_FACTORY_WIDTH, StaticVariables.SPECIALOPS_FACTORY_HEIGHT), true);
                break;
            }
            case "SuperWeponeFactory":

            {
                factory = new SuperWeponeFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.SUPERWEPONE_FACTORY_WIDTH, StaticVariables.SUPERWEPONE_FACTORY_HEIGHT), true);
                break;
            }
            case "CloneFactory":

            {
                factory = new CloneFactory(new Rectangle(moveTheBuildingAroundTheWorld.x, moveTheBuildingAroundTheWorld.y, StaticVariables.MAIN_FACTORY_WIDTH, StaticVariables.MAIN_FACTORY_HEIGHT), true);
                break;

            }

        }

        if (factory != null&&factory.checkRequirement()) {
            factory.setSize(factory.getWidth()+StaticVariables.world.getComponnentWidthAndHeight(),factory.getHeight()+StaticVariables.world.getComponnentWidthAndHeight());
            factory.canAddToWorld();
            StaticVariables.world.add(factory);
            StaticVariables.allFactoryInWorld.add(factory) ;
            StaticVariables.factory = factory;
            factory.moveTheFactory();

        }



    }

    public boolean CheckIfIntersects(){
        for (Factory factory : StaticVariables.allFactoryInWorld) {
            if (factory!=this&&factory.getBounds().intersects(StaticVariables.factory.getBounds())) {

                Font font = new Font("Dialog", Font.PLAIN, 25);
                JLabel jLabel = new JLabel();
                jLabel.setText("Not a valid place");
                jLabel.setBounds(650, 150, 500, 80);
                jLabel.setFont(font);
                jLabel.setForeground(Color.RED);
                StaticVariables.panelFrame.add(jLabel);
                StaticVariables.panelFrame.repaint();
                new Thread(() -> {
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StaticVariables.panelFrame.remove(jLabel);
                    StaticVariables.panelFrame.repaint();
                }).start();

                return true;
            }
        }
        return false;
    }*/







}
