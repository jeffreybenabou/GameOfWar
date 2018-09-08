package GameCore;

import ImageHandel.ImageLoader;
import ObjectPackege.Unit;
import Units.InfantryUnit.ArmoredInfentry;
import Units.InfantryUnit.BazzokaUnit;
import Units.InfantryUnit.Infantry;

import javax.swing.*;
import java.awt.*;

public class World extends JPanel {

    private JLabel backGroundImage;
    private boolean isTesting;


    private Unit tempUnit;

    public World(boolean isTesting){
        this.isTesting=isTesting;
        if(isTesting)
        {
            setTheTestScreen();
        }

        setBounds(0,MainFrame.gamePanel.getHeight(),MainFrame.screenSize.width,MainFrame.screenSize.height);
            setTheBackGroundWorld();
            setBackground(Color.gray);

setLayout(null);








    }

    private void setTheTestScreen() {

    }

    private void setTheBackGroundWorld() {
        new Thread(new Runnable() {
            public void run() {
                backGroundImage=new JLabel();
                backGroundImage.setBounds(0,0,MainFrame.screenSize.width*10,MainFrame.screenSize.height*20);
                backGroundImage.setBackground(Color.black);
                backGroundImage.setOpaque(true);
                tempUnit=new Infantry();
                backGroundImage.add(tempUnit);
                ArmoredInfentry bazzokaUnit=new ArmoredInfentry();
                backGroundImage.add(bazzokaUnit);
                bazzokaUnit.setLocation(300,300);
                add(backGroundImage);
            }
        }).start();




// TODO: 07/09/2018 fix the unit not appiring and world change layout when add the unit
    }

    public JLabel getBackGroundImage() {
        return backGroundImage;
    }

    public void setBackGroundImage(JLabel backGroundImage) {
        this.backGroundImage = backGroundImage;
    }

    public boolean isTesting() {
        return isTesting;
    }

    public void setTesting(boolean testing) {
        isTesting = testing;
    }

    public Unit getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(Unit tempUnit) {
        this.tempUnit = tempUnit;
    }
}
