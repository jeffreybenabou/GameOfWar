package GameCore;

import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;
import ObjectPackege.GameObject;
import ObjectPackege.Unit;

import javax.swing.*;
import java.awt.*;

public class UnitAttackLabel extends JLabel {

    public static ImageLoader imageLoader= new ImageLoader();;
    public static SpriteSheet spriteSheet = new SpriteSheet(imageLoader.loadImage("image/attack/infantry sprite.png"));
    private final Unit unit;
    private GameObject gameObject;
    public UnitAttackLabel(Unit unit, GameObject gameObject) {
this.gameObject=gameObject;
this.unit=unit;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    airUnit();
                    groundUnit();


                }catch (Exception e)
                {
                    e.printStackTrace();
                }


                removeFromWorld();
            }
        }).start();



    }

    private void groundUnit() {
        if(!unit.getClass().getPackage().getName().contains("Air"))
        {
            setTheImage(spriteSheet);
        }
    }

    private void airUnit() {
        if(unit.getClass().getPackage().getName().contains("Air"))
        {
            setTheImage(spriteSheet);
        }
    }

    public void setTheImage(SpriteSheet spriteSheet) {
        try
        {
            while (gameObject.isObjectIsLive()&&unit.isObjectIsAttacking() ) {
                setVerticalAlignment(JLabel.TOP);

                try
                {
                    if (unit.getDirOfUnit() == 0) {
                        setBounds(unit.getX() + unit.getWidth() / 3, unit.getY() + unit.getHeight(), unit.getWidth() / 3, unit.getHeight() / 2);
                        setIcon(new ImageIcon(spriteSheet.crop(280, 0, 115, 285).getScaledInstance(getWidth(), getHeight(), 4)));


                        //                        down
                    } else if (unit.getDirOfUnit() == 1) {

//            up
                        setBounds(unit.getX() + unit.getWidth() / 3, unit.getY() - unit.getHeight() / 2, unit.getWidth() / 3, unit.getHeight() / 2);
                        setIcon(new ImageIcon(spriteSheet.crop(400, 0, 115, 285).getScaledInstance(getWidth(), getHeight(), 4)));
                    } else if (unit.getDirOfUnit() == 2) {

                        setBounds(unit.getX() - unit.getWidth() + unit.getWidth() / 10, unit.getY() + unit.getHeight() / 3, unit.getWidth() / 2, unit.getHeight() / 3);
                        setIcon(new ImageIcon(spriteSheet.crop(0, 115, 280, 115).getScaledInstance(getWidth(), getHeight(), 4)));
//            left
                    } else if (unit.getDirOfUnit() == 3) {

                        setBounds(unit.getX() + unit.getWidth() - unit.getWidth() / 12, unit.getY() + unit.getHeight() / 3, unit.getWidth() / 2, unit.getHeight() / 3);
                        setIcon(new ImageIcon(spriteSheet.crop(0, 0, 280, 115).getScaledInstance(getWidth(), getHeight(), 4)));
//            right
                    } else if (unit.getDirOfUnit() == 4) {

                        setBounds(unit.getX() - unit.getWidth() + unit.getWidth() / 7, unit.getY() + unit.getHeight() / 2 + unit.getHeight() / 10, unit.getWidth() / 2 + unit.getWidth() / 10, unit.getHeight() / 3);
                        setIcon(new ImageIcon(spriteSheet.crop(455, 285, 220, 215).getScaledInstance(getWidth(), getHeight(), 4)));
//            left down
                    } else if (unit.getDirOfUnit() == 5) {

                        setBounds(unit.getX() + unit.getWidth() - unit.getWidth() / 8, unit.getY() + unit.getHeight() / 2, unit.getWidth() / 2 + unit.getWidth() / 10, unit.getHeight() / 3);
                        setIcon(new ImageIcon(spriteSheet.crop(680, 285, 220, 215).getScaledInstance(getWidth(), getHeight(), 4)));
//            right down
                    } else if (unit.getDirOfUnit() == 6) {

                        setBounds(unit.getX() - unit.getWidth() + unit.getWidth() / 10, unit.getY() - unit.getHeight() / 2, unit.getWidth() / 2 + unit.getWidth() / 10, unit.getHeight() / 3);
                        setIcon(new ImageIcon(spriteSheet.crop(220, 280, 225, 215).getScaledInstance(getWidth(), getHeight(), 4)));
//            left up
                    } else if (unit.getDirOfUnit() == 7) {

                        setBounds(unit.getX() + unit.getWidth() - unit.getWidth() / 10, unit.getY(), unit.getWidth() / 2 + unit.getWidth() / 10, unit.getHeight() / 3);
                        setIcon(new ImageIcon(spriteSheet.crop(0, 280, 225, 215).getScaledInstance(getWidth(), getHeight(), 4)));
//            right up
                    }

                    try {
                        Thread.sleep(unit.getSpeedOfAttack() / 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setVisible(false);
                    try {
                        Thread.sleep(unit.getSpeedOfAttack() / 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    setVisible(true);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }



            }
            unit.setObjectIsStanding(true);
            unit.setObjectIsAttacking(false);
        }catch (Exception e)
        {
            e.printStackTrace();
        }



    }

    private void removeFromWorld() {

        try
        {

            MainFrame.world.getBackGroundImage().remove(this);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
