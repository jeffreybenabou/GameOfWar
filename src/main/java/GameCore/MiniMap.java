package GameCore;

import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;
import ObjectPackege.GameObject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class MiniMap extends JLabel {

    private ImageLoader imageLoader;
    private SpriteSheet spriteSheet;
    private JLabel map,location;
    private ArrayList<JLabel>lables;
    private volatile boolean running = true;

    public MiniMap(){
        imageLoader = new ImageLoader();
        spriteSheet=new SpriteSheet(imageLoader.loadImage("image/panel/grid.png"));
        lables=new ArrayList<JLabel>();
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

                    for (int i = 0; i < getLables().size()-1; i++) {
                        try
                        {

                            lables.get(i).setBounds(getWidth()/15+World.allObjects.get(i).getX()/73,getHeight()/5+World.allObjects.get(i).getY()/100,3,3);

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                            running=false;


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
            for (GameObject g:World.allObjects) {
                if (!g.isAddedToMiniMap()) {
                    addAllObjectFromArrayListToTheMap(g);
                    g.setAddedToMiniMap(true);
                }

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public  void addAllObjectFromArrayListToTheMap(GameObject g) {
        JLabel k=new JLabel();
        k.setBounds(getWidth()/15+g.getX()/73,getHeight()/5+g.getY()/100,3,3);
        // TODO: 15/09/2018 add here if team != team colour blue

        k.setBackground(Color.red);


        k.setOpaque(true);
        add(k);
        lables.add(k);
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



    public void setLocation(JLabel location) {
        this.location = location;
    }

    public ArrayList<JLabel> getLables() {
        return lables;
    }

    public void setLables(ArrayList<JLabel> lables) {
        this.lables = lables;
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
