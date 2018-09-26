package GameCore;

import ImageHandel.ImageLoader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends JLabel {

    private ImageLoader imageLoader;
    private JLabel power,money,place;
    private JLabel moneyHas,powerHas,placeHas;
    private JButton showTheMap;
    private boolean isMapIsVisible=false;

    public GamePanel() {

        setBounds(0, 0, MainFrame.screenSize.width, MainFrame.screenSize.height / 10);
        imageLoader = new ImageLoader();
        setIcon(new ImageIcon(imageLoader.loadImage("image/panel/gamePanel.png").getScaledInstance(getWidth(),getHeight(),4)));

        setBackground(Color.black);

        setThePower();
        setTheMoney();
        setThePlace();
        setTheString();
        setTheMapButton();
        setLayout(null);
setOpaque(true);




    }



    private void setTheString() {
        moneyHas= new JLabel(""+StaticVariables.sumOfMoney);
        moneyHas.setBounds(money.getX()+money.getWidth(),money.getY()+getHeight()/4,getWidth()/10,getHeight()/3);
        moneyHas.setForeground(Color.yellow);
        add(moneyHas);

        powerHas= new JLabel(""+StaticVariables.powerNeed+"/"+StaticVariables.powerHas);
        powerHas.setBounds(power.getX()+power.getWidth(),power.getY()+getHeight()/4,getWidth()/10,getHeight()/3);
        powerHas.setForeground(Color.yellow);
        add(powerHas);

        placeHas= new JLabel(""+StaticVariables.unitHas+"/"+StaticVariables.UNIT_LIMIT);
        placeHas.setBounds(place.getX()+place.getWidth(),place.getY()+getHeight()/4,getWidth()/10,getHeight()/3);
        placeHas.setForeground(Color.yellow);
        add(placeHas);

        new Thread(new Runnable() {
            public void run() {
                while (true)
                {
                    getMoneyHas().setText(""+StaticVariables.sumOfMoney);

                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private void setTheMapButton() {
        showTheMap=new JButton("show the Map");
        showTheMap.setVerticalTextPosition(JButton.CENTER);
        showTheMap.setHorizontalAlignment(JButton.CENTER);
        showTheMap.setHorizontalTextPosition(JButton.CENTER);
        showTheMap.setBounds(getWidth()/20,getHeight()/6,getWidth()/10,getHeight()/2+getHeight()/5);
        showTheMap.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/mapButton.png").getScaledInstance(showTheMap.getWidth(),showTheMap.getHeight(),4)));
        showTheMap.setBackground(new Color(0,0,0,0));
        showTheMap.setContentAreaFilled(false);
        showTheMap.setBorder(null);
        showTheMap.addMouseListener(MainFrame.mainFrame);
        showTheMap.setPressedIcon(new ImageIcon(imageLoader.loadImage("image/panel/mapButton.png").getScaledInstance(showTheMap.getWidth()+showTheMap.getWidth()/10,showTheMap.getHeight()+showTheMap.getHeight()/10,4)));

        add(showTheMap);

    }

    private void setThePlace() {
        place=new JLabel();
        place.setBounds(getWidth()-getWidth()/3+getWidth()/40,5,getWidth()/25,getHeight()-getHeight()/5);
        place.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/unit.png").getScaledInstance(place.getWidth(),place.getHeight(),4)));

        place.setVerticalAlignment(JLabel.CENTER);
        place.setHorizontalAlignment(JLabel.CENTER);
        add(place);
    }

    private void setTheMoney() {
        money=new JLabel();

        money.setBounds(getWidth()-getWidth()/8,5,getWidth()/30,getHeight()-getHeight()/4);
        money.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/money.png").getScaledInstance(money.getWidth(),money.getHeight(),4)));
        money.setVerticalAlignment(JLabel.CENTER);
        money.setHorizontalAlignment(JLabel.CENTER);
        add(money);
    }

    private void setThePower() {
        power=new JLabel();

        power.setBounds(getWidth()-getWidth()/3+getWidth()/8,5,getWidth()/25,getHeight()-getHeight()/5);
        power.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/power.png").getScaledInstance(power.getWidth(),power.getHeight(),4)));

        power.setVerticalAlignment(JLabel.CENTER);
        power.setHorizontalAlignment(JLabel.CENTER);
        add(power);
    }


    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public JLabel getPower() {
        return power;
    }

    public void setPower(JLabel power) {
        this.power = power;
    }

    public JLabel getMoney() {
        return money;
    }

    public void setMoney(JLabel money) {
        this.money = money;
    }

    public JLabel getPlace() {
        return place;
    }

    public void setPlace(JLabel place) {
        this.place = place;
    }


    public JLabel getMoneyHas() {
        return moneyHas;
    }

    public void setMoneyHas(JLabel moneyHas) {
        this.moneyHas = moneyHas;
    }

    public JLabel getPowerHas() {
        return powerHas;
    }

    public void setPowerHas(JLabel powerHas) {
        this.powerHas = powerHas;
    }

    public JLabel getPlaceHas() {
        return placeHas;
    }

    public void setPlaceHas(JLabel placeHas) {
        this.placeHas = placeHas;
    }

    public JButton getShowTheMap() {
        return showTheMap;
    }

    public boolean isMapIsVisible() {
        return isMapIsVisible;
    }

    public void setMapIsVisible(boolean mapIsVisible) {
        isMapIsVisible = mapIsVisible;
    }

    public void setShowTheMap(JButton showTheMap) {
        this.showTheMap = showTheMap;
    }
}
