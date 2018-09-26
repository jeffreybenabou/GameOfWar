package GameCore;

import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;
import ObjectPackege.GameObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;


public class MiniMap extends JLabel {

    private final ArrayList<JLabel> enemyLabel;
    private ImageLoader imageLoader;
    private SpriteSheet spriteSheet;
    private JLabel map,location;
    private ArrayList<JLabel> userLabel;
    private volatile boolean running = true;

    public MiniMap(){
        imageLoader = new ImageLoader();
        spriteSheet=new SpriteSheet(imageLoader.loadImage("image/panel/grid.png"));
        userLabel =new ArrayList<>();
        enemyLabel =new ArrayList<>();
        setTheMap();
        showTheObjectsOnMiniMap();
        addLocationOfUserOnMiniMap();




    }

    public void moveTheLocationOnMiniMap(int x,int y)
    {


        try
        {

                location.setLocation(  getWidth()/15-x/70,getHeight()/5-y/105);

        }catch (ArithmeticException e)
        {

        }






    }


    private void addLocationOfUserOnMiniMap() {
        location=new JLabel();
        location.setBounds(getWidth()/15,getHeight()/5,getWidth()/10,getHeight()/10);
        location.setBackground(new Color(100,100,100,150));
         Border border = BorderFactory.createLineBorder(Color.yellow, 1);

        location.setBorder(border);
        location.setOpaque(true);
        add(location);
    }

    public void showTheObjectsOnMiniMap() {


        new Thread(new Runnable() {
            public void run() {
                while (running)
                {

                    for (int i = 0; i < getUserLabel().size()-1; i++) {
                        try
                        {


                            userLabel.get(i).setBounds(getWidth()/15+World.allObjects.get(i).getX()/73,getHeight()/5+World.allObjects.get(i).getY()/100,3,3);
                            userLabel.get(i).setBackground(Color.blue);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                            running=false;
                            break;

                        }

                    }
                    int j=0;
                    for (int i = 0; i < getEnemyLabel().size()-1; i++) {
                        try
                        {

                            if( getUserLabel().get(i).getName().equals("enemy")) {
                                enemyLabel.get(j).setBounds(getWidth() / 15 + World.allEnemyObjects.get(j).getX() / 73, getHeight() / 5 + World.allEnemyObjects.get(j).getY() / 100, 3, 3);
                                enemyLabel.get(j).setBackground(Color.red);
                                j++;
                            }

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                            running=false;
                            break;


                        }

                    }
                    setTheMiniMapObjects();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                running=true;
                showTheObjectsOnMiniMap();
            }
        }).start();
    }

    public void setTheMiniMapObjects (){
        try
        {
            for (int i=0;i<World.allObjects.size();i++) {
                if (!World.allObjects.get(i).isAddedToMiniMap()) {
                    addAllObjectFromArrayListToTheMap(World.allObjects.get(i),false);
                    World.allObjects.get(i).setAddedToMiniMap(true);
                }

            }
            for (int i=0;i<World.allEnemyObjects.size();i++) {

                    if (!World.allEnemyObjects.get(i).isAddedToMiniMap()) {
                        addAllObjectFromArrayListToTheMap(World.allEnemyObjects.get(i),true);
                        World.allEnemyObjects.get(i).setAddedToMiniMap(true);
                    }

            }
        }

        catch (Exception e)
        {
            e.printStackTrace();

        }

    }

    public  void addAllObjectFromArrayListToTheMap(GameObject g,boolean enemy) {
        JLabel k=new JLabel();
        k.setBounds(getWidth()/15+g.getX()/73,getHeight()/5+g.getY()/100,3,3);



        if(!enemy)
        {
            k.setName("friendly");
            k.setBackground(Color.blue);
            userLabel.add(k);
        }
        else
        {
            k.setName("enemy");
            k.setBackground(Color.red);
            enemyLabel.add(k);
        }

        k.setOpaque(true);
        add(k);

    }

    private void setTheMap() {
        setBounds(0, 500,MainFrame.screenSize.width/6,MainFrame.screenSize.height/3 );
        setIcon(new ImageIcon(spriteSheet.crop(0,0,400,spriteSheet.getSheet().getRaster().getHeight()).getScaledInstance(getWidth(),getHeight(),4)));
        setVisible(false);


    }


    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public JLabel getMap() {
        return map;
    }

    public ArrayList<JLabel> getEnemyLabel() {
        return enemyLabel;
    }



    public void setLocation(JLabel location) {
        this.location = location;
    }

    public ArrayList<JLabel> getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(ArrayList<JLabel> userLabel) {
        this.userLabel = userLabel;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setMap(JLabel map) {
        this.map = map;
    }


}
