package Units.Factory;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import ObjectPackege.Factory;

import javax.swing.*;
import java.awt.*;

public class MoneyFactory extends Factory {
    public static final int MONEY_FACTORY_WIDTH = MainFrame.world.getBackGroundImage().getWidth()/60, MONEY_FACTORY_HEIGHT = MainFrame.world.getBackGroundImage().getWidth()/70;


    public MoneyFactory(boolean onWorld) {
        super();
        type=8;
        bound=new Rectangle(0,0, MONEY_FACTORY_WIDTH,MONEY_FACTORY_HEIGHT);
        saveTheWidthAndHeight(bound.width,bound.height);
        init(onWorld);
        setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.FACTORY_SHEET_WIDTH*2,0, StaticVariables.FACTORY_SHEET_WIDTH,StaticVariables.FACTORY_SHEET_HEIGHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();

    }


    public void gainMoney()
    {
        new Thread(new Runnable() {
            public void run() {
                while (isObjectIsLive())
                {

                        StaticVariables.sumOfMoney++;


                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }




}
