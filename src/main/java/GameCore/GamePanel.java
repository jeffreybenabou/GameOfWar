package GameCore;

import ImageHandel.ImageLoader;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static GameCore.World.initTheArrayList;

public class GamePanel extends JLabel {

    private ImageLoader imageLoader;
    private JLabel power,money,place;
    private JLabel moneyHas,powerHas,placeHas;
    private JButton showTheMap;
    private boolean isMapIsVisible=false;

    private MiniMap miniMap;
    private BuildingMenu buildingMenu;
    private UnitTrainMenu unitTrainMenu;
    private MechanicMenu mechanicMenu;
    private AirUnitsMenu airUnitMenu;
    private JLabel bar;


    public GamePanel() {

        setBounds(0, 0, MainFrame.screenSize.width, MainFrame.screenSize.height);
        imageLoader = new ImageLoader();

        setLayout(null );


        setTheBar();
        setThePower();
        setTheMoney();
        setThePlace();
        setTheString();
        setTheMapButton();
        addMiniMapAndBuildingMenu();






    }

    private void setTheBar() {
        bar=new JLabel();

        bar.setBounds(0,0,getWidth(),getHeight()/4-getHeight()/7);
        bar.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/gamePanel.png").getScaledInstance(getWidth(),bar.getHeight(),4)));
        bar.setOpaque(true);
        add(bar,0);
    }


    private void setTheString() {
        moneyHas= new JLabel(""+StaticVariables.sumOfMoney);
        moneyHas.setBounds(money.getX()+money.getWidth(),money.getY()+bar.getHeight()/4,bar.getWidth()/10,bar.getHeight()/3);
        moneyHas.setForeground(Color.yellow);
        bar.add(moneyHas);

        powerHas= new JLabel(""+StaticVariables.powerNeed+"/"+StaticVariables.powerHas);
        powerHas.setBounds(power.getX()+power.getWidth(),power.getY()+bar.getHeight()/4,bar.getWidth()/10,bar.getHeight()/3);
        powerHas.setForeground(Color.yellow);
        bar.add(powerHas);

        placeHas= new JLabel(""+StaticVariables.unitHas+"/"+StaticVariables.UNIT_LIMIT);
        placeHas.setBounds(place.getX()+place.getWidth(),place.getY()+bar.getHeight()/4,bar.getWidth()/10,bar.getHeight()/3);
        placeHas.setForeground(Color.yellow);
        bar.add(placeHas);

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
        showTheMap.setBounds(bar.getWidth()/20,bar.getHeight()/6,bar.getWidth()/10,bar.getHeight()/2+bar.getHeight()/5);
        showTheMap.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/mapButton.png").getScaledInstance(showTheMap.getWidth(),showTheMap.getHeight(),4)));
        showTheMap.setBackground(new Color(0,0,0,0));
        showTheMap.setContentAreaFilled(false);
        showTheMap.setBorder(null);
        showTheMap.addMouseListener(MainFrame.mainFrame);
        showTheMap.setPressedIcon(new ImageIcon(imageLoader.loadImage("image/panel/mapButton.png").getScaledInstance(showTheMap.getWidth()+showTheMap.getWidth()/10,showTheMap.getHeight()+showTheMap.getHeight()/10,4)));

        bar.add(showTheMap);

    }

    private void setThePlace() {
        place=new JLabel();
        place.setBounds(bar.getWidth()-bar.getWidth()/3+bar.getWidth()/40,5,bar.getWidth()/25,bar.getHeight()-bar.getHeight()/5);
        place.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/unit.png").getScaledInstance(place.getWidth(),place.getHeight(),4)));

        place.setVerticalAlignment(JLabel.CENTER);
        place.setHorizontalAlignment(JLabel.CENTER);
        bar.add(place);
    }

    private void setTheMoney() {
        money=new JLabel();

        money.setBounds(bar.getWidth()-bar.getWidth()/8,5,bar.getWidth()/30,bar.getHeight()-bar.getHeight()/4);
        money.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/money.png").getScaledInstance(money.getWidth(),money.getHeight(),4)));
        money.setVerticalAlignment(JLabel.CENTER);
        money.setHorizontalAlignment(JLabel.CENTER);
        bar.add(money);
    }

    private void setThePower() {
        power=new JLabel();

        power.setBounds(bar.getWidth()-bar.getWidth()/3+bar.getWidth()/8,5,bar.getWidth()/25,bar.getHeight()-bar.getHeight()/5);
        power.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/power.png").getScaledInstance(power.getWidth(),power.getHeight(),4)));

        power.setVerticalAlignment(JLabel.CENTER);
        power.setHorizontalAlignment(JLabel.CENTER);
        bar.add(power);
    }

    public void addMiniMapAndBuildingMenu()
    {
        initTheArrayList();
        miniMap =new MiniMap();

        buildingMenu =new BuildingMenu();
        unitTrainMenu=new UnitTrainMenu();
        mechanicMenu=new MechanicMenu();
        airUnitMenu=new AirUnitsMenu();

        add(buildingMenu,0);
        add(unitTrainMenu,0);
        add(mechanicMenu,0);
        add(airUnitMenu,0);
        add(miniMap,0);

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

    public JLabel getBar() {
        return bar;
    }

    public void setBar(JLabel bar) {
        this.bar = bar;
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

    public void changeTheText() {
        MainFrame.gamePanel.getPlaceHas().setText(""+StaticVariables.unitHas+"/"+StaticVariables.UNIT_LIMIT);

    }

    public MiniMap getMiniMap() {
        return miniMap;
    }

    public void setMiniMap(MiniMap miniMap) {
        this.miniMap = miniMap;
    }

    public BuildingMenu getBuildingMenu() {
        return buildingMenu;
    }

    public void setBuildingMenu(BuildingMenu buildingMenu) {
        this.buildingMenu = buildingMenu;
    }

    public UnitTrainMenu getUnitTrainMenu() {
        return unitTrainMenu;
    }

    public void setUnitTrainMenu(UnitTrainMenu unitTrainMenu) {
        this.unitTrainMenu = unitTrainMenu;
    }

    public MechanicMenu getMechanicMenu() {
        return mechanicMenu;
    }

    public void setMechanicMenu(MechanicMenu mechanicMenu) {
        this.mechanicMenu = mechanicMenu;
    }

    public AirUnitsMenu getAirUnitMenu() {
        return airUnitMenu;
    }

    public void setAirUnitMenu(AirUnitsMenu airUnitMenu) {
        this.airUnitMenu = airUnitMenu;
    }
}
