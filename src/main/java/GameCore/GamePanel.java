package GameCore;

import ImageHandel.ImageLoader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GamePanel extends JPanel {

    private ImageLoader imageLoader;
    private JLabel power,money,place;
    private Border border = BorderFactory.createLineBorder(Color.black, 3);

    public GamePanel(){
setBounds(0,0,MainFrame.screenSize.width,MainFrame.screenSize.height/15);
        setBackground(Color.yellow);
        imageLoader=new ImageLoader();
        setThePower();
        setTheMoney();
        setThePlace();
        setOpaque(true);
        setBorder(border);
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
}
