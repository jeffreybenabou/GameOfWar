package GameCore;

import ObjectPackege.Unit;
import Server.Sql;
import Units.Factory.MainFactory;
import Units.InfantryUnit.Infantry;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainFrame extends JFrame implements MouseListener{


    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private MainMenu mainMenu;
    private Border border = BorderFactory.createLineBorder(Color.black, 3);
    public static World world;
    public static GamePanel gamePanel;
    public static MainFrame mainFrame;
    public static int xMousePosition = 0, yMousePosition = 0, xMouseLocation, yMouseLocation;


    public MainFrame() {
        mainFrame=this;
        setTheJFrame();
        setTheMenu();


    }

    private void setTheMenu() {
        mainMenu = new MainMenu(this);
        add(mainMenu);
    }

    private void setTheJFrame() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setBounds(0, 0, screenSize.width, screenSize.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLUE);

        setVisible(true);





    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
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
            checkIfUserPreesUnit(e);
            checkIfUserWantsToMoveUnit(e);
        }
        else if(e.getButton()==MouseEvent.BUTTON3)
        {
            removeTheUnitPickAndStopHim(e);
        }


    }

    private void checkIfUserWantsToMoveUnit(MouseEvent e) {
        if(e.getComponent().equals(world.getBackGroundImage()))
        {
            for (Unit unit:World.allUnit) {
                if(unit.isUnitHasBeenPick())
                {

                    unit.setPointToMove(e.getPoint());
                    unit.setTheRightIcon();
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
        }
        else {
            for (Unit unit:World.allUnit) {
                if(unit.isObjectIsMoving())
                {
                    unit.setObjectIsMoving(false);
                    unit.setObjectIsStanding(true);
                }
            }
        }
    }

    private void checkIfUserPreesUnit(MouseEvent e) {



        if(World.allUnit.contains(e.getComponent()))
        {
            Unit unit=(Unit)e.getComponent();
            unit.setUnitHasBeenPick(true);
        }
    }

    private void moveTheWorld() {

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {

                        xMouseLocation=MouseInfo.getPointerInfo().getLocation().x;
                        yMouseLocation=MouseInfo.getPointerInfo().getLocation().y;


                        if(xMouseLocation>getWidth()-20)
                            xMousePosition--;
                        else if(xMouseLocation<20)
                            xMousePosition++;
                        else
                            xMousePosition=0;



                        if(yMouseLocation>getHeight()-20)
                            yMousePosition--;
                        else if(yMouseLocation<20)
                            yMousePosition++;
                        else
                            yMousePosition=0;

                        // TODO: 08/09/2018 make sure that its not null

                        world.getBackGroundImage().setLocation(world.getBackGroundImage().getX() + xMousePosition, world.getBackGroundImage().getY() + yMousePosition);
                        world.getMiniMap().setLocation(-(world.getBackGroundImage().getLocation().x),-(world.getBackGroundImage().getLocation().y));
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

        if(e.getComponent().equals(gamePanel.getShowTheMap()))
        {
            if(gamePanel.isMapIsVisible())
            {
                gamePanel.getShowTheMap().setText("show the Map");
                world.getMiniMap().setVisible(false);
                gamePanel.setMapIsVisible(false);
            }
            else
            {
                gamePanel.getShowTheMap().setText("hide the Map");
                world.getMiniMap().setVisible(true);
                gamePanel.setMapIsVisible(true);

            }


        }

    }

    private void checkIfComponentIsFromMainMenu(MouseEvent e) {
        if(e.getComponent().equals(mainMenu.getExitButton())||e.getComponent().equals(mainMenu.getExitFromPanel()))
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

            mainMenu.getUserMenu().setVisible(true);

        }
        else
            mainMenu.getUserMenu().setVisible(false);

        if(e.getComponent().equals(mainMenu.getRegister()))
        {

            mainMenu.getSignInMenu().setVisible(true);

        }
        else
            mainMenu.getSignInMenu().setVisible(false);


        if(e.getComponent().equals(mainMenu.getSignUpButton()))
        {


            if(mainMenu.getIdName().isHintActive()&&mainMenu.getPassword().isHintActive())
            {
                JOptionPane.showMessageDialog(this,"enter password and user name");

                mainMenu.getSignInMenu().setVisible(true);
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
                mainMenu.getSignInMenu().setVisible(true);

                JOptionPane.showMessageDialog(this,"user exist choose another name");
            }
            else
            {
                mainMenu.getSignInMenu().setVisible(true);
                JOptionPane.showMessageDialog(this,"enter password and user name");

            }



        }



    }

    private void startTheTutorial() {
        remove(mainMenu);
        gamePanel=new GamePanel();
        world=new World(true);

        Infantry tempUnit=new Infantry();
        World.allUnit.add(tempUnit);
        world.getBackGroundImage().add(tempUnit);

        MainFactory mainFactory=new MainFactory();
        world.getBackGroundImage().add(mainFactory);
        world.getMiniMap().setTheObjectsOnMiniMap();





        add(world);


        add(gamePanel);


        moveTheWorld();
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }


















}
