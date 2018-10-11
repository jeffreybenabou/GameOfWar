package GameCore;

import ObjectPackege.Factory;
import ObjectPackege.GameObject;
import ObjectPackege.Unit;
import Server.Sql;
import Units.AirUnits.AntiAir;
import Units.AirUnits.Choper;
import Units.AirUnits.SpaceShip;
import Units.Factory.*;
import Units.InfantryUnit.*;
import Units.MechanicUnits.*;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements MouseListener, MouseMotionListener {



    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private MainMenu mainMenu;
    public static World world;
    public static GamePanel gamePanel;
    public static MainFrame mainFrame;
    public static int xMousePosition = 0, yMousePosition = 0, xMouseLocation, yMouseLocation,xLocationToDragRectangle,yLocationToDragRectangle;
    public static Point locationOfFactory;

    private int xWidth,yHeight;


    public MainFrame() {
        mainFrame=this;

        getContentPane().setBackground(Color.black);
        setTheJFrame();
        setTheMenu();



    }

    private void setTheMenu() {

        mainMenu = new MainMenu(this);
        add(mainMenu);
    }


            private void removeTheFactoryFromWorld(MouseEvent e) {

        Factory factory=null;

        if(e.getComponent().getClass().getPackage().toString().contains("Factory"))
        {
            factory=(Factory)e.getComponent();
            if(Factory.objectIsFlotingWorld&&!factory.isFactoryIsOnWorld())
            {
                world.getBackGroundImage().remove(factory);
                world.getBackGroundImage().repaint();
                Factory.objectIsFlotingWorld=false;
            }
        }



    }

    private void checkIfUserWantsToMoveUnit(MouseEvent e) {
        if(e.getComponent().equals(world.getBackGroundImage()))
        {
            for (Unit unit:World.allUnit) {
                if(unit.isUnitHasBeenPick()&&unit!=e.getComponent())
                {

                    unit.setPointToMove(e.getPoint());
                    unit.setTheRightIcon();
                    unit.setTargetIsEnemy(false);
                    unit.setObjectIsMoving(true);
                    unit.setObjectIsStanding(false);

                }
            }
        }
    }

    private void removeTheUnitPickAndStopHim(MouseEvent e) {

        if(World.allUnit.contains(e.getComponent()))
        {
            Unit unit=(Unit)e.getComponent();
            unit.setUnitHasBeenPick(false);
            unit.setObjectIsMoving(false);
            unit.setObjectIsStanding(true);
            unit.setBorder( null);

        }
        else if(world.getBackGroundImage().equals(e.getComponent())) {
            for (Unit unit:World.allUnit) {
                if(unit.isObjectIsMoving()||unit.isObjectIsStanding()&&unit.isUnitHasBeenPick())
                {
                    unit.setBorder( null);
                    unit.setUnitHasBeenPick(false);

                }
            }
        }

    }

    private void checkIfUserPreesUnit(MouseEvent e) {


        Unit unit = null;

        if(World.allUnit.contains(e.getComponent()))
        {
             unit=(Unit)e.getComponent();
            unit.setUnitHasBeenPick(true);
            unit.setBorder( new LineBorder(Color.blue, 2, true));
            unit.repaint();
        }


    }

    private void moveTheWorld() {

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {


                        xMouseLocation=MouseInfo.getPointerInfo().getLocation().x;
                        yMouseLocation=MouseInfo.getPointerInfo().getLocation().y;

//

                        if(xMouseLocation>getWidth()-20&&world.getBackGroundImage().getX()>-world.getBackGroundImage().getWidth()+MainFrame.screenSize.width )
                        {
//                            right
                            xMousePosition--;
                        }

                        else if(xMouseLocation<20&&world.getBackGroundImage().getX() <=0)
                        {
                            xMousePosition++;
                        }

                        else
                            xMousePosition=0;



                        if(yMouseLocation>getHeight()-20&&world.getBackGroundImage().getY()>-world.getBackGroundImage().getHeight()+MainFrame.screenSize.height)
                        {
                            yMousePosition--;
                        }

                        else if(yMouseLocation<20&&world.getBackGroundImage().getY() <=0)
                        {
                            yMousePosition++;
                        }

                        else
                            yMousePosition=0;


                        gamePanel.getMiniMap().moveTheLocationOnMiniMap( world.getBackGroundImage().getX(),world.getBackGroundImage().getY());

                        world.getBackGroundImage().setLocation(world.getBackGroundImage().getX() + xMousePosition, world.getBackGroundImage().getY() + yMousePosition);

                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        moveTheWorld();
                        break;
                    }
                }
            }
        }).start();





    }

    private void checkIfComponentIsFromWorld(MouseEvent e) {

        if (world != null) {
            xLocationToDragRectangle=e.getX();
            yLocationToDragRectangle=e.getY();

            if (!Factory.objectIsFlotingWorld) {
                checkIfUserPreesUnit(e);
                checkIfUserWantsToMoveUnit(e);
                checkIfUserPreesShowTheMap(e);
                checkIfUserPressBuildingMenu(e);
                checkIfUserPreesUnitToBuild(e);
                checkIfUserSelectFactoryToBuild(e);
                checkIfUnitIsPressingEnemy(e);
            } else {
                addTheFactoryToWorld(e);
            }
        }


    }

    private void checkIfUserPreesUnitToBuild(MouseEvent e) {
        if(gamePanel.getUnitTrainMenu().getUnitLabel().contains(e.getComponent()))
        {

            world.addUnitToQuaqe(e,0);
        }
        else if(gamePanel.getMechanicMenu().getUnitLabel().contains(e.getComponent()))
        {


            world.addUnitToQuaqe(e,1);
        }
        else if(gamePanel.getAirUnitMenu().getUnitLabel().contains(e.getComponent()))
        {


            world.addUnitToQuaqe(e,2);
        }
    }

    private void addTheFactoryToWorld(MouseEvent e) {



        Factory.factory.checkIfFactoryIntercetWithOtherFactory();


    }

    private void checkIfUserSelectFactoryToBuild(MouseEvent e) {
        if(BuildingMenu.buildingLabel.contains(e.getComponent()))
        {
            gamePanel.getBuildingMenu().setVisible(false);
            world.addFactoryToWorld(e);
        }
    }

    private void checkIfUserPressBuildingMenu(MouseEvent e) {

        if(e.getComponent().equals(world.getMainFactory())||e.getComponent().equals(gamePanel.getBuildingMenu()))
        {

            gamePanel.getBuildingMenu().setVisible(true);
            gamePanel.getUnitTrainMenu().setVisible(false);
            gamePanel.getMechanicMenu().setVisible(false);
            gamePanel.getAirUnitMenu().setVisible(false);


        }
        else if (World.infentryFactory.contains(e.getComponent())||e.getComponent().equals(gamePanel.getUnitTrainMenu())||gamePanel.getUnitTrainMenu().getUnitLabel().equals(e.getComponent()))
        {

            if(World.infentryFactory.contains(e.getComponent()))
            {
                locationOfFactory=e.getComponent().getLocation();
                World.factoryPreesed=(Factory) e.getComponent();
                World.factoryPreesed.getQuaqe(). setTheQuaqeVisible();
            }

            gamePanel.getUnitTrainMenu().setVisible(true);
            gamePanel.getBuildingMenu().setVisible(false);
            gamePanel.getMechanicMenu().setVisible(false);
            gamePanel.getAirUnitMenu().setVisible(false);

        }
        else if (World.tankFactory.contains(e.getComponent())||e.getComponent().equals(gamePanel.getMechanicMenu())||gamePanel.getMechanicMenu().getUnitLabel().equals(e.getComponent()))
        {
            if(World.tankFactory.contains(e.getComponent()))
            {

                locationOfFactory=e.getComponent().getLocation();
                World.factoryPreesed=(Factory) e.getComponent();
                World.factoryPreesed.getQuaqe(). setTheQuaqeVisible();
            }

            gamePanel.getUnitTrainMenu().setVisible(false);
            gamePanel.getMechanicMenu().setVisible(true);
            gamePanel.getBuildingMenu().setVisible(false);
        }
        else if (World.airFactory.contains(e.getComponent())||e.getComponent().equals(gamePanel.getAirUnitMenu())||gamePanel.getAirUnitMenu().getUnitLabel().equals(e.getComponent()))
        {
            if(World.airFactory.contains(e.getComponent()))
            {

                locationOfFactory=e.getComponent().getLocation();
                World.factoryPreesed=(Factory) e.getComponent();
                World.factoryPreesed.getQuaqe(). setTheQuaqeVisible();
            }
            gamePanel.getAirUnitMenu().setVisible(true);
            gamePanel.getUnitTrainMenu().setVisible(false);
            gamePanel.getMechanicMenu().setVisible(false);
            gamePanel.getBuildingMenu().setVisible(false);
        }
        else if(!gamePanel.getUnitTrainMenu().getUnitLabel().contains(e.getComponent())&&!gamePanel.getMechanicMenu().getUnitLabel().contains(e.getComponent())&&!gamePanel.getAirUnitMenu().getUnitLabel().contains(e.getComponent()))
        {
            gamePanel.getAirUnitMenu().setVisible(false);
            gamePanel.getUnitTrainMenu().setVisible(false);
            gamePanel.getBuildingMenu().setVisible(false);
            gamePanel.getMechanicMenu().setVisible(false);
        }



    }

    private void checkIfUserPreesShowTheMap(MouseEvent e) {
        if(e.getComponent().equals(gamePanel.getShowTheMap()))
        {
            if(gamePanel.isMapIsVisible())
            {
                gamePanel.getShowTheMap().setText("show the Map");
                gamePanel.getMiniMap().setVisible(false);
                gamePanel.setMapIsVisible(false);
            }
            else
            {
                gamePanel.getShowTheMap().setText("hide the Map");
                gamePanel.getMiniMap().setVisible(true);
                gamePanel.setMapIsVisible(true);

            }


        }
    }

    private void checkIfComponentIsFromMainMenu(MouseEvent e) {
        if(e.getComponent().equals(mainMenu.getSignIn()))
        {
            mainMenu.getSignInMenu().setVisible(true);
        }
        if(e.getComponent().equals(mainMenu.getExitButton()))
        {

            Sql.setTheUserOnline("false",mainMenu.get_userName());
            System.exit(1);
        }
        else if(e.getComponent().equals(mainMenu.getStartTutorial()))
        {
            startTheTutorial();
        }
        else if(e.getComponent().equals(mainMenu.getStartGame()))
        {
            mainMenu.addListOfUsers();
            mainMenu.getUserMenu().setVisible(true);

        }
        else if (e.getComponent().equals(mainMenu.getPickTheRightEnamy()))
        {
            mainMenu.setTheWaitForEnemyLabel();
            mainMenu.getUserMenu().setVisible(false);
        }
        else
            mainMenu.getUserMenu().setVisible(false);
        if(!Sql.serverIsOffline)
        {
            if(e.getComponent().equals(mainMenu.getRegister()))
            {


                mainMenu.getRegisterInMenu().setVisible(true);

            }
            else
                mainMenu.getRegisterInMenu().setVisible(false);


            if(e.getComponent().equals(mainMenu.getSignUpButton()))
            {


                if(mainMenu.getIdName().isHintActive()&&mainMenu.getPassword().isHintActive())
                {
                    JOptionPane.showMessageDialog(this,"enter password and user name");

                    mainMenu.getRegisterInMenu().setVisible(true);
                }

                else if(mainMenu.getIdName().getText().length()>0&&mainMenu.getPassword().getText().length()>0&&!Sql.checkIfUserExist(mainMenu.getIdName().getText()))
                {
                    Sql.register(mainMenu.getPassword().getText(),mainMenu.getIdName().getText());
                    JOptionPane.showMessageDialog(this,"registration complete");
                    Sql.setTheUserOnline("true",""+mainMenu.get_userName());
                    mainMenu.getUserNameShow().setText("Hello "+mainMenu.getIdName().getText());
                    mainMenu.saveUserName(mainMenu.getIdName().getText(),mainMenu.getPassword().getText());
                    mainMenu.getStartGame().setEnabled(true);
                    mainMenu.getStartGame().addMouseListener(this);

                }
                else if (mainMenu.getIdName().getText().length()>0&&mainMenu.getPassword().getText().length()>0&&Sql.checkIfUserExist(mainMenu.getIdName().getText()))
                {
                    mainMenu.getRegisterInMenu().setVisible(true);

                    JOptionPane.showMessageDialog(this,"user exist choose another name");
                }
                else
                {
                    mainMenu.getRegisterInMenu().setVisible(true);
                    JOptionPane.showMessageDialog(this,"enter password and user name");

                }



            }
        }else
        {
            JOptionPane.showMessageDialog(null,"server off line");
        }




    }

    private void startTheTutorial() {
        remove(mainMenu);
        gamePanel = new GamePanel();
        world = new World();

        world. setTheTestScreen();

        add(gamePanel);
        gamePanel.add(world);

        repaint();
        revalidate();
        moveTheWorld();


    }

    private void setTheJFrame() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setBounds(0, 0, screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        setLayout(null);





    }

    private void checkIfUnitIsPressingEnemy(MouseEvent e){
        GameObject gameObject;
        if(e.getComponent().getClass().getPackage().getName().contains("Units"))
        {
            gameObject=(GameObject)e.getComponent();
            for (Unit game:World.allUnit) {

                if(game.isUnitHasBeenPick()&&game!=gameObject)
                    if(gameObject.getGroup().contains("not"))
                    {

                        game.setTargetIsEnemy(true);
                        game.setPointToMove(gameObject.getLocation());
                        game.setObjectIsMoving(true);
                        game.setObjectIsStanding(false);
                    }
                    else
                    {

                        game.setTargetIsEnemy(false);
                        game.setPointToMove(e.getPoint());
                        game.setObjectIsMoving(false);
                        game.setObjectIsStanding(true);
                    }



                game.setTheRightIcon();

            }
        }


    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }

                new MainFrame();
            }
        });
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1)
        {
            checkIfComponentIsFromMainMenu(e);
            checkIfComponentIsFromWorld(e);


        }
        else if(e.getButton()==MouseEvent.BUTTON3)
        {
        removeTheFactoryFromWorld(e);
        removeTheUnitPickAndStopHim(e);
    }


    }


    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
        checkIfComponentIsHoverAFactoryToBuild(e);
        checkIfComponentIsHoverAUnitToBuild(e);
        checkIfComponentIsHoverAMechanicToBuild(e);
        checkIfComponentIsHoverAirUnitToBuild(e);

    }

    private void checkIfComponentIsHoverAirUnitToBuild(MouseEvent e) {
        if(gamePanel!=null&& gamePanel.getAirUnitMenu().getUnitLabel().contains(e.getComponent()))
        {

            if(gamePanel.getAirUnitMenu().getGatherTheInformation()!=null)
                gamePanel.getAirUnitMenu().remove(gamePanel.getAirUnitMenu().getGatherTheInformation());
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    gamePanel.getAirUnitMenu().setTheInformation(new AntiAir(false));

                    break;
                case 1:
                    gamePanel.getAirUnitMenu().setTheInformation(new Choper(false));

                    break;
                case 2:
                    gamePanel.getAirUnitMenu().setTheInformation(new SpaceShip(false));

                    break;



            }
            gamePanel.getAirUnitMenu().getGatherTheInformation().setVisible(true);
        }
        else if(gamePanel!=null&&gamePanel.getAirUnitMenu().getGatherTheInformation()!=null)
        {
            gamePanel.getAirUnitMenu().getGatherTheInformation().setVisible(false);


        }
    }

    private void checkIfComponentIsHoverAMechanicToBuild(MouseEvent e) {
        if(gamePanel!=null&& gamePanel.getMechanicMenu().getUnitLabel().contains(e.getComponent()))
        {


            if(gamePanel.getMechanicMenu().getGatherTheInformation()!=null)
                gamePanel.getMechanicMenu().remove(gamePanel.getMechanicMenu().getGatherTheInformation());
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    gamePanel.getMechanicMenu().setTheInformation(new Tank(false));

                    break;
                case 1:
                    gamePanel.getMechanicMenu().setTheInformation(new MiniGun(false));

                    break;
                case 2:
                    gamePanel.getMechanicMenu().setTheInformation(new SuperTank(false));

                    break;
                case 3:
                    gamePanel.getMechanicMenu().setTheInformation(new AntiAirTank(false));

                    break;
                case 4:
                    gamePanel.getMechanicMenu().setTheInformation(new BigBoss(false));

                    break;



            }
            gamePanel.getMechanicMenu().getGatherTheInformation().setVisible(true);
        }
        else if(gamePanel!=null&&gamePanel.getMechanicMenu().getGatherTheInformation()!=null)
        {
            gamePanel.getMechanicMenu().getGatherTheInformation().setVisible(false);


        }
    }

    private void checkIfComponentIsHoverAUnitToBuild(MouseEvent e) {
        if(gamePanel!=null&& gamePanel.getUnitTrainMenu().getUnitLabel().contains(e.getComponent()))
        {


            if(gamePanel.getUnitTrainMenu().getGatherTheInformation()!=null)
                gamePanel.getUnitTrainMenu().remove(gamePanel.getUnitTrainMenu().getGatherTheInformation());
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    gamePanel.getUnitTrainMenu().setTheInformation(new ArmoredInfentry(false));

                    break;
                case 1:
                    gamePanel.getUnitTrainMenu().setTheInformation(new Infantry(false));

                    break;
                case 2:
                    gamePanel.getUnitTrainMenu().setTheInformation(new Medic(false));

                    break;
                case 3:
                    gamePanel.getUnitTrainMenu().setTheInformation(new Sniper(false));

                    break;
                case 4:
                    gamePanel.getUnitTrainMenu().setTheInformation(new BazzokaUnit(false));

                    break;



            }
            gamePanel.getUnitTrainMenu().getGatherTheInformation().setVisible(true);
        }
        else if(gamePanel!=null&&gamePanel.getUnitTrainMenu().getGatherTheInformation()!=null)
        {
            gamePanel.getUnitTrainMenu().getGatherTheInformation().setVisible(false);


        }
    }

    private void checkIfComponentIsHoverAFactoryToBuild(MouseEvent e) {
        if(gamePanel!=null&& BuildingMenu.buildingLabel.contains(e.getComponent()))
        {


            if(gamePanel.getBuildingMenu().getGatherTheInformation()!=null)
                gamePanel.getBuildingMenu().remove(gamePanel.getBuildingMenu().getGatherTheInformation());
            switch (Integer.parseInt(e.getComponent().getName()))
            {
                case 0:
                    gamePanel.getBuildingMenu().setTheInformation(new MainFactory(false));

                    break;
                case 1:
                    gamePanel.getBuildingMenu().setTheInformation(new PowerFactory(false));

                    break;
                case 2:
                    gamePanel.getBuildingMenu().setTheInformation(new MoneyFactory(false));

                    break;
                case 3:
                    gamePanel.getBuildingMenu().setTheInformation(new InfentryFactory(false));

                    break;
                case 4:
                    gamePanel.getBuildingMenu().setTheInformation(new TankFactory(false));

                    break;
                case 5:
                    gamePanel.getBuildingMenu().setTheInformation(new SateliteFactory(false));

                    break;
                case 6:
                    gamePanel.getBuildingMenu().setTheInformation(new AirForceFactory(false));

                    break;
                case 7:
                    gamePanel.getBuildingMenu().setTheInformation(new SuperWeponeFactory(false));

                    break;
                case 8:
                    gamePanel.getBuildingMenu().setTheInformation(new SpacielOpsFactory(false));

                    break;
                case 9:
                    gamePanel.getBuildingMenu().setTheInformation(new CloneFactory(false));

                    break;


            }
            gamePanel.getBuildingMenu().getGatherTheInformation().setVisible(true);
        }
        else if(gamePanel!=null&&gamePanel.getBuildingMenu().getGatherTheInformation()!=null)
        {
            gamePanel.getBuildingMenu().getGatherTheInformation().setVisible(false);


        }
    }

    public void mouseExited(MouseEvent e) {

    }


    public void mouseDragged(MouseEvent e) {
        if (e.getComponent().equals(world.getBackGroundImage()))
        {

            xWidth=e.getX()-xLocationToDragRectangle;
            yHeight=e.getY()-yLocationToDragRectangle;
            if(xWidth>0&&yHeight>0)
                world.getUnitsPickRectangle().setBounds(xLocationToDragRectangle,yLocationToDragRectangle,xWidth,yHeight);
            else if(xWidth<0&&yHeight<0)
                world.getUnitsPickRectangle().setBounds(xLocationToDragRectangle+xWidth,yLocationToDragRectangle+yHeight,-xWidth,-yHeight);
            else if (xWidth<0&&yHeight>0)
                world.getUnitsPickRectangle().setBounds(xLocationToDragRectangle+xWidth,yLocationToDragRectangle,-xWidth,yHeight);
            else if (xWidth>0&&yHeight<0)
                world.getUnitsPickRectangle().setBounds(xLocationToDragRectangle,yLocationToDragRectangle+yHeight,xWidth,-yHeight);




            world.getBackGroundImage().repaint();
            world.getUnitsPickRectangle().setVisible(true);

        }

    }

    public void mouseMoved(MouseEvent e) {
        world.getUnitsPickRectangle().setVisible(false);
        checkIfUnitIsIntersectWithPickArea();

    }

    private void checkIfUnitIsIntersectWithPickArea() {

        for (Unit unit:World.allUnit) {
            if(unit.getBounds().intersects(world.getUnitsPickRectangle().getBounds()))
            {
                unit.setUnitHasBeenPick(true);
                unit.setBorder( new LineBorder(Color.blue, 2, true));

            }


        }
        world.getUnitsPickRectangle().setBounds(0,0,0,0);
    }



}
