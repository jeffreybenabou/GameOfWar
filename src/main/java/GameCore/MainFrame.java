package GameCore;

import Server.Sql;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MainFrame extends JFrame implements MouseListener, MouseMotionListener {


    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private MainMenu mainMenu;
    private Border border = BorderFactory.createLineBorder(Color.black, 3);
    public static World world;
    public static GamePanel gamePanel;
    private int xMousePosition = 0, yMousePosition = 0, xMouseLocation, yMouseLocation;


    public MainFrame() {
        setTheJFrame();
        setTheMenu();
        addMouseMotionListener(this);

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
        setVisible(true);
        setLayout(new BorderLayout(2,1));





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
        checkIfComponentIsFromMainMenu(e);
        checkIfComponentIsFromWorld(e);

    }

    private void moveTheWorld() {

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        // TODO: 08/09/2018 make sure that its not null
                        repaint();
                        world.getBackGroundImage().setLocation(world.getBackGroundImage().getX() + xMousePosition, world.getBackGroundImage().getY() + yMousePosition);
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();





    }

    private void checkIfComponentIsFromWorld(MouseEvent e) {

    }

    private void checkIfComponentIsFromMainMenu(MouseEvent e) {
        if(e.getComponent().equals(mainMenu.getExitButton())||e.getComponent().equals(mainMenu.getExitFromPanel()))
        {

            Sql.setTheUserOnline("false",mainMenu.get_userName());
            System.exit(1);
        }
        if(e.getComponent().equals(mainMenu.getStartTutorial()))
        {

            remove(mainMenu);



            gamePanel=new GamePanel();

            world=new World(true);

            add(world);
            add(gamePanel);


            moveTheWorld();
        }
        if(e.getComponent().equals(mainMenu.getStartGame()))
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

                JOptionPane.showMessageDialog(this,"user excise choose another name");
            }
            else
            {
                mainMenu.getSignInMenu().setVisible(true);
                JOptionPane.showMessageDialog(this,"enter password and user name");

            }



        }



    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {


        xMouseLocation=e.getX();
        yMouseLocation=e.getY();


            if(e.getX()>getWidth()-20)
                xMousePosition--;
            else if(e.getX()<20)
            xMousePosition++;
        else
            xMousePosition=0;



            if(e.getY()>getHeight()-20)
                yMousePosition--;
            else if(e.getY()<20)
                yMousePosition++;
            else
                yMousePosition=0;






    }
}
