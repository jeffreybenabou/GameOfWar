package GameCore;

import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;
import ObjectPackege.GameObject;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;


public class MiniMap extends JLabel {

    private ImageLoader imageLoader;
    private SpriteSheet spriteSheet;
    private JLabel map,location;
    private ArrayList<JLabel>lables;

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
                while (true)
                {

                    for (int i = 0; i < lables.size(); i++) {
                        try
                        {
                            lables.get(i).setBounds(getWidth()/15+World.allObjects.get(i).getX()/73,getHeight()/5+World.getAllUnit().get(i).getY()/100,3,3);

                        }catch (Exception e)
                        {
                            e.printStackTrace();
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
            }
        }).start();
    }

    public void setTheMiniMapObjects (){
        for (GameObject g:World.allObjects) {
            if (!g.isAddedToMiniMap()) {
                addAllObjectFromArrayListToTheMap(g);
                g.setAddedToMiniMap(true);
            }

        }
    }

    public  void addAllObjectFromArrayListToTheMap(GameObject g) {
        JLabel k=new JLabel();
        k.setBounds(getWidth()/15+g.getX()/73,getHeight()/5+g.getY()/100,3,3);
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

    public void setMap(JLabel map) {
        this.map = map;
    }


}
