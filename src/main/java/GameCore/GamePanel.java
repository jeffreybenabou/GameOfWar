package GameCore;

import ImageHandel.ImageLoader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends JPanel {

    private ImageLoader imageLoader;
    private JLabel power,money,place;
    private Border border = BorderFactory.createLineBorder(Color.black, 3);
    private JButton showTheMap;
    private boolean isMapIsVisible=false;

    public GamePanel() {

        setBounds(0, 50, MainFrame.screenSize.width, MainFrame.screenSize.height / 12);
        setBackground(Color.yellow);
        imageLoader = new ImageLoader();
        setThePower();
        setTheMoney();
        setThePlace();
        setTheMapButton();
        setBorder(border);

        setLayout(null);





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
