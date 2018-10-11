package GameCore;

import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;
import ObjectPackege.GameObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;


public class MiniMap extends JLabel {

    private ImageLoader imageLoader;
    private SpriteSheet spriteSheet;
    private JLabel map, locationOfPlayer;
    private volatile boolean running = true;
    public static int width,height;

    public MiniMap(){
        imageLoader = new ImageLoader();
        spriteSheet=new SpriteSheet(imageLoader.loadImage("image/panel/grid.png"));
        width=getWidth();
        height=getHeight();


        setTheMap();

        addLocationOfUserOnMiniMap();




    }

    public void moveTheLocationOnMiniMap(int x,int y)
    {


        try
        {

                locationOfPlayer.setLocation(  getWidth()/15-x/70,getHeight()/5-y/105);

        }catch (ArithmeticException e)
        {
            e.printStackTrace();

        }






    }


    private void addLocationOfUserOnMiniMap() {
        locationOfPlayer =new JLabel();
        locationOfPlayer.setBounds(getWidth()/15,getHeight()/5,getWidth()/10,getHeight()/10);
        locationOfPlayer.setBackground(new Color(100,100,100,150));
         Border border = BorderFactory.createLineBorder(Color.yellow, 1);

        locationOfPlayer.setBorder(border);
        locationOfPlayer.setOpaque(true);
        add(locationOfPlayer);
    }



    public static   JLabel addAllObjectFromArrayListToTheMap(boolean enemy) {
        JLabel k=new JLabel();
        k.setSize(3,3);



        if(!enemy)

            k.setBackground(Color.blue);
        else
            k.setBackground(Color.red);

        k.setOpaque(true);
        return k;

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



    public JLabel getLocationOfPlayer() {
        return locationOfPlayer;
    }

    public void setLocationOfPlayer(JLabel locationOfPlayer) {
        this.locationOfPlayer = locationOfPlayer;
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
