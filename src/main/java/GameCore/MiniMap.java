package GameCore;

import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;
import ObjectPackege.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MiniMap extends JLabel {

    private ImageLoader imageLoader;
    private SpriteSheet spriteSheet;
    private JLabel map,middel,options;
    private ArrayList<JLabel>lables;

    public MiniMap(){
        imageLoader = new ImageLoader();
        spriteSheet=new SpriteSheet(imageLoader.loadImage("image/panel/grid.png"));
        setTheMap();




    }

    public void setTheObjectsOnMiniMap() {
        lables=new ArrayList<JLabel>();
        for (GameObject g:World.allObjects) {
            JLabel k=new JLabel();
            k.setBounds(getWidth()/15+g.getX()/73,getHeight()/5+g.getY()/100,3,3);
            k.setBackground(Color.red);
            k.setOpaque(true);
            add(k);
            lables.add(k);
        }
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

                        }

                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
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

    public JLabel getMiddel() {
        return middel;
    }

    public void setMiddel(JLabel middel) {
        this.middel = middel;
    }

    public JLabel getOptions() {
        return options;
    }

    public void setOptions(JLabel options) {
        this.options = options;
    }
}
