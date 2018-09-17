package GameCore;

import ImageHandel.ImageLoader;
import Server.Sql;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainMenu  extends JPanel  {

    public static Font fontOfMenuItems,fontOfButtons;
    private JMenuItem exitFromPanel, statics,load,register,signIn,signOut;
    private JMenuBar jMenu;
    private MainFrame mainFrame;
    private JPanel menuPanel;

    private String _userName,_password;
    private JLabel imageLabel, buttonLabel,userMenu,signInMenu,writeUserInformation,userNameShow,waitForEnemy;

    private         Border border = BorderFactory.createLineBorder(Color.black, 3);

    private HintTextField idName,password;

    private JList Jlist;

    private Sql sql;

    private JButton startGame,startTutorial,exitButton,signUpButton,cancelTheGame;
    public MainMenu(MainFrame mainFrame){
        this.mainFrame=mainFrame;

//        sql=new Sql();

        fontOfMenuItems =new Font("SERIF", Font.PLAIN, (MainFrame.screenSize.width + MainFrame.screenSize.height) / 100);
        fontOfButtons=new Font("SERIF", Font.PLAIN, (MainFrame.screenSize.width + MainFrame.screenSize.height) / 150);
        setTheProperties();
        addTheMenuOptions();
        addTheMenuButtons();
        addTheImageBackGround();
        addTheUserMenu();
        addSignInWindows();
        /*checkIfLogIn();
        addListOfUsers();*/
        addExitLisenersToProgram();
        setTheWaitForEnemyLabel();
    }

    public void setTheWaitForEnemyLabel(){
        waitForEnemy=new JLabel("online isent working right now ..... ");
        waitForEnemy.setBounds(MainFrame.screenSize.width/2-(MainFrame.screenSize.width/5)/2,MainFrame.screenSize.height/4,MainFrame.screenSize.width/5,MainFrame.screenSize.height/10);
        waitForEnemy.setBackground(Color.BLUE);
        waitForEnemy.setOpaque(true);
        imageLabel.add(waitForEnemy);
        cancelTheGame=new JButton("cancel the game");
        cancelTheGame.setSize(waitForEnemy.getWidth()/2,waitForEnemy.getHeight()/3);
        cancelTheGame.setLocation(waitForEnemy.getWidth()/4,waitForEnemy.getHeight()-cancelTheGame.getHeight());
        waitForEnemy.add(cancelTheGame);
    }
    public void saveUserName(String name,String password) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("user.txt").getFile());
        BufferedWriter writer ;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(name+ "");
            writer.newLine();
            writer.write(password+ "");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }










    }

    private void addTheImageBackGround() {


        try
        {
            ImageLoader imageLoader=new ImageLoader();
            BufferedImage bufferedImage=imageLoader.loadImage("image/mechanic/user unit/tank1.png");
            imageLabel.setIcon(new ImageIcon(bufferedImage.getScaledInstance(MainFrame.screenSize.width,MainFrame.screenSize.height,4)));


        }catch (NullPointerException e)
        {
            imageLabel.setBackground(Color.red);

            e.printStackTrace();
        }
        add(imageLabel);
    }

    private void addTheUserMenu(){

        userNameShow=new JLabel("hello guest");
        userNameShow.setBounds(MainFrame.screenSize.width/2-(MainFrame.screenSize.width/10)/2,MainFrame.screenSize.height/10,MainFrame.screenSize.width/10,MainFrame.screenSize.height/10);
        userNameShow.setFont(fontOfMenuItems);
        userNameShow.setBackground(Color.GRAY);
        userNameShow.setForeground(Color.black);
        userNameShow.setBorder(border);
        userNameShow.setHorizontalAlignment(JLabel.CENTER);
        userNameShow.setOpaque(true);

        JLabel userPick=new JLabel("pick User");
        userMenu =new JLabel();
        userMenu.setBounds(MainFrame.screenSize.width/2-(MainFrame.screenSize.width/5)/2,MainFrame.screenSize.height/4,MainFrame.screenSize.width/5,MainFrame.screenSize.height/2);
        userMenu.setOpaque(true);
        userMenu.setVisible(false);
        userPick.setBounds(0,0,userMenu.getWidth(),userMenu.getHeight()/10);

        userMenu.add(userPick);
        imageLabel.add(userMenu);
        imageLabel.add(userNameShow);






    }

    private void addExitLisenersToProgram(){
       mainFrame.addWindowListener(new WindowListener() {
           public void windowOpened(WindowEvent e) {

           }

           public void windowClosing(WindowEvent e) {

               try
               {
                   Sql.setTheUserOnline("false",_userName);

               }catch (Exception es)
               {
                   es.printStackTrace();
               }

           }

           public void windowClosed(WindowEvent e) {


           }

           public void windowIconified(WindowEvent e) {

           }

           public void windowDeiconified(WindowEvent e) {

           }

           public void windowActivated(WindowEvent e) {

           }

           public void windowDeactivated(WindowEvent e) {

           }
       });
    }


    private void addListOfUsers() {
        String[] data = Sql.addAllOnlineUsers(_userName);
         Jlist = new JList(data);
        Jlist.setBounds(0,50,userMenu.getWidth(),userMenu.getHeight());
        Jlist.setLayoutOrientation(JList.VERTICAL);
        userMenu.add(Jlist);
        userMenu.setBorder(border);
        Jlist.setBorder(border);




    }

    private void addTheMenuButtons() {

        imageLabel=new JLabel();
        GridLayout    flowLayout=new GridLayout(4,0,4,50);
        buttonLabel =new JLabel();
        buttonLabel.setBounds(0,MainFrame.screenSize.height/10,MainFrame.screenSize.width/7,MainFrame.screenSize.height);
        buttonLabel.setLayout(flowLayout);


        startGame=new JButton("start game");
        startGame.setEnabled(false);
        startGame.setFont(fontOfButtons);

        startTutorial=new JButton("start tutorial");
        startTutorial.setFont(fontOfButtons);
        startTutorial.addMouseListener(mainFrame);

        exitButton=new JButton("exit from the game");
        exitButton.setFont(fontOfButtons);
        exitButton.addMouseListener(mainFrame);
        buttonLabel.add(startGame);
        buttonLabel.add(startTutorial);
        buttonLabel.add(exitButton);
        imageLabel.add(buttonLabel);

    }


    private void addSignInWindows() {
        signUpButton=new JButton("click here to sign in");
        signInMenu = new JLabel();
        signInMenu.setBounds(userMenu.getBounds());

        writeUserInformation = new JLabel("create your user - enter password and id ");
        writeUserInformation.setFont(fontOfButtons);
        writeUserInformation.setHorizontalAlignment(JLabel.CENTER);
        writeUserInformation.setBounds(0, 0, signInMenu.getWidth(), signInMenu.getHeight() / 10);

        idName = new HintTextField("enter user name");
        idName.setBounds(0, signInMenu.getHeight() / 3, signInMenu.getWidth(), signInMenu.getHeight() / 8);
        signUpButton.setBounds(signInMenu.getWidth()/2-((signInMenu.getWidth()/2+signInMenu.getWidth()/10)/2), signInMenu.getHeight()-signInMenu.getHeight() / 8 , signInMenu.getWidth()/2+signInMenu.getWidth()/10, signInMenu.getHeight() / 8);
        signUpButton.setFont(fontOfButtons);
        signUpButton.addMouseListener(mainFrame);
        password = new HintTextField("enter password");
        password.setBounds(0, signInMenu.getHeight() / 2 + signInMenu.getHeight() / 8, signInMenu.getWidth(), signInMenu.getHeight() / 8);

        signInMenu.add(writeUserInformation);


        signInMenu.add(idName);
        signInMenu.add(password);
        signInMenu.add(signUpButton);


        signInMenu.setBorder(border);
        signInMenu.setVisible(false);
        signInMenu.setOpaque(true);
        imageLabel.add(signInMenu);

    }

    private void addTheMenuOptions() {
        exitFromPanel =new JMenuItem("exit");
        statics =new JMenuItem("statics");
        load =new JMenuItem("load");
        register=new JMenuItem("register");
        signIn=new JMenuItem("sign in");
        signOut=new JMenuItem("sign out");

        exitFromPanel.setFont(fontOfMenuItems);
        statics.setFont(fontOfMenuItems);
        load.setFont(fontOfMenuItems);
        register.setFont(fontOfMenuItems);
        signIn.setFont(fontOfMenuItems);
        signOut.setFont(fontOfMenuItems);

        register.addMouseListener(mainFrame);
        exitFromPanel.addMouseListener(mainFrame);


        jMenu=new JMenuBar();
        jMenu.setLayout(new GridLayout());
        jMenu.add(exitFromPanel);
        jMenu.add(statics);
        jMenu.add(signIn);
        jMenu.add(signOut);
        jMenu.add(register);
        jMenu.add(load);

        menuPanel=new JPanel();
        menuPanel.add(jMenu);
        menuPanel.setOpaque(true);
        menuPanel.setBorder(border);
        add(menuPanel);


    }

    private void  setTheProperties() {
        setBounds(0,0,MainFrame.screenSize.width,MainFrame.screenSize.height);
        setBackground(Color.red);







    }

    private void checkIfLogIn() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("user.txt").getFile());
        BufferedReader writer ;
        try {
            writer = new BufferedReader(new FileReader(file));
             _userName = writer.readLine();
             _password = writer.readLine();


            if(Sql.checkIfPasswordIsTrue(_userName,_password))
            {
                userNameShow.setText("hello "+_userName);
               getStartGame().setEnabled(true);
                getStartGame().addMouseListener(mainFrame);
                Sql.setTheUserOnline("true",""+_userName);

            }




            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public JMenuItem getExitFromPanel() {
        return exitFromPanel;
    }

    public void setExitFromPanel(JMenuItem exitFromPanel) {
        this.exitFromPanel = exitFromPanel;
    }

    public JMenuItem getStatics() {
        return statics;
    }

    public void setStatics(JMenuItem statics) {
        this.statics = statics;
    }

    public JMenuItem getLoad() {
        return load;
    }

    public void setLoad(JMenuItem load) {
        this.load = load;
    }

    public JMenuBar getjMenu() {
        return jMenu;
    }

    public Font getFontOfMenuItems() {
        return fontOfMenuItems;
    }

    public void setFontOfMenuItems(Font fontOfMenuItems) {
        this.fontOfMenuItems = fontOfMenuItems;
    }

    public JLabel getUserNameShow() {
        return userNameShow;
    }

    public void setUserNameShow(JLabel userNameShow) {
        this.userNameShow = userNameShow;
    }

    @Override
    public Border getBorder() {
        return border;
    }

    @Override
    public void setBorder(Border border) {
        this.border = border;
    }

    public Font getFontOfButtons() {
        return fontOfButtons;
    }

    public void setFontOfButtons(Font fontOfButtons) {
        this.fontOfButtons = fontOfButtons;
    }

    public JLabel getWriteUserInformation() {
        return writeUserInformation;
    }

    public void setWriteUserInformation(JLabel writeUserInformation) {
        this.writeUserInformation = writeUserInformation;
    }

    public JButton getSignUpButton() {
        return signUpButton;
    }

    public void setSignUpButton(JButton signUpButton) {
        this.signUpButton = signUpButton;
    }

    public JLabel getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }

    public JLabel getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(JLabel buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public JLabel getUserMenu() {
        return userMenu;
    }

    public void setUserMenu(JLabel userMenu) {
        this.userMenu = userMenu;
    }

    public JButton getStartGame() {
        return startGame;
    }

    public void setStartGame(JButton startGame) {
        this.startGame = startGame;
    }

    public JButton getStartTutorial() {
        return startTutorial;
    }

    public void setStartTutorial(JButton startTutorial) {
        this.startTutorial = startTutorial;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JMenuItem getRegister() {
        return register;
    }

    public void setRegister(JMenuItem register) {
        this.register = register;
    }

    public JMenuItem getSignIn() {
        return signIn;
    }

    public void setSignIn(JMenuItem signIn) {
        this.signIn = signIn;
    }

    public JMenuItem getSignOut() {
        return signOut;
    }

    public void setSignOut(JMenuItem signOut) {
        this.signOut = signOut;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JLabel getSignInMenu() {
        return signInMenu;
    }

    public void setSignInMenu(JLabel signInMenu) {
        this.signInMenu = signInMenu;
    }


    public HintTextField getIdName() {
        return idName;
    }

    public void setIdName(HintTextField idName) {
        this.idName = idName;
    }

    public HintTextField getPassword() {
        return password;
    }

    public void setPassword(HintTextField password) {
        this.password = password;
    }

    public JList getJlist() {
        return Jlist;
    }

    public void setJlist(JList jlist) {
        Jlist = jlist;
    }

    public Sql getSql() {
        return sql;
    }

    public void setSql(Sql sql) {
        this.sql = sql;
    }

    public void setjMenu(JMenuBar jMenu) {
        this.jMenu = jMenu;
    }
}
