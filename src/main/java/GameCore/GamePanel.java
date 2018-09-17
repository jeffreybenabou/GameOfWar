package GameCore;

import ImageHandel.ImageLoader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends JPanel {

    private ImageLoader imageLoader;
    private JLabel power,money,place;
    private JLabel moneyHas,powerHas,placeHas;
    private Border border = BorderFactory.createLineBorder(Color.yellow, 3);
    private JButton showTheMap;
    private boolean isMapIsVisible=false;

    public GamePanel() {

        setBounds(0, 0, MainFrame.screenSize.width, MainFrame.screenSize.height / 12);
        setBackground(Color.black);
        imageLoader = new ImageLoader();
        setThePower();
        setTheMoney();
        setThePlace();
        setTheString();
        setTheMapButton();

        setBorder(border);
        setLayout(null);





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
    }


    private void setTheMapButton() {
        showTheMap=new JButton("show the Map");
        showTheMap.setBounds(getWidth()/20,5,getWidth()/10,getHeight()/2+getHeight()/5);
        showTheMap.addMouseListener(MainFrame.mainFrame);
        add(showTheMap);

    }

    private void setThePlace() {
        place=new JLabel();
        place.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/unit.png")));
        place.setBounds(getWidth()-getWidth()/3,5,power.getIcon().getIconWidth(),power.getIcon().getIconHeight());
        place.setVerticalAlignment(JLabel.CENTER);
        place.setHorizontalAlignment(JLabel.CENTER);
        add(place);
    }

    private void setTheMoney() {
        money=new JLabel();
        money.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/money.png")));
        money.setBounds(getWidth()-getWidth()/10,0,power.getIcon().getIconWidth(),power.getIcon().getIconHeight());
        money.setVerticalAlignment(JLabel.CENTER);
        money.setHorizontalAlignment(JLabel.CENTER);
        add(money);
    }

    private void setThePower() {
        power=new JLabel();
        power.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/power.png")));
        power.setBounds(getWidth()-getWidth()/5,5,power.getIcon().getIconWidth(),power.getIcon().getIconHeight());
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

    @Override
    public Border getBorder() {
        return border;
    }

    @Override
    public void setBorder(Border border) {
        this.border = border;
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
