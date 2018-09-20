package GameCore;

import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;
import ObjectPackege.GameObject;
import ObjectPackege.Unit;
import Units.InfantryUnit.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UnitTrainMenu extends JLabel {


    private Font fontOfButtons=new Font("SERIF", Font.PLAIN, (MainFrame.screenSize.width + MainFrame.screenSize.height) / 120);

    private ImageLoader imageLoader;
    private SpriteSheet spriteSheet;
    private ArrayList<Unit>queqeOfUnit;
    private  ArrayList<JLabel> unitLabel;
    private JLabel boxBackGround;
    private JLabel buildingBackGround;
    private int j=0;
    private JLabel objectName, objectDamage, objectCost, objectProperties, unitQueue, timeToTrain;
    private   JLabel gatherTheInformation;
    private JLabel pictureOfObject;
    private int time=0,  spendTime=0;

    public UnitTrainMenu() {
        imageLoader = new ImageLoader();
        spriteSheet = new SpriteSheet(imageLoader.loadImage("image/panel/grid.png"));
        init();

        spriteSheet = new SpriteSheet(imageLoader.loadImage("image/infentry/factoryPanel/list of human unit.png"));
        setVisible(false);
        addComponentToBuild();

    }




    private void addComponentToBuild() {
        unitLabel =new ArrayList<JLabel>();



        for (int i = 0; i <3 ; i++) {
            setTheBoxBackGround(i);
            setTheUnitIcon(i);
            boxBackGround.setName(""+i);
            unitLabel. add(boxBackGround);
            add(boxBackGround);
            boxBackGround.addMouseListener(MainFrame.mainFrame);
        }
        defineTheQueue();

    }



    private void defineTheQueue() {
        unitQueue =new JLabel();
        unitQueue.setBounds(getWidth()/6,getHeight()/5,getWidth()/12,getHeight()/2);
        unitQueue.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/box.png").getScaledInstance(boxBackGround.getWidth(),boxBackGround.getHeight(),4)));
        add(unitQueue);
    }


    private void setTheUnitIcon(int i) {
        buildingBackGround=new JLabel();
        buildingBackGround.setBounds(boxBackGround.getWidth()/8,boxBackGround.getHeight()/6,boxBackGround.getWidth()-boxBackGround.getWidth()/4,boxBackGround.getHeight()-boxBackGround.getHeight()/3);

        switch (i)
        {
            case 0:
                buildingBackGround.setIcon(new ImageIcon(spriteSheet.crop(0,0,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE).getScaledInstance(buildingBackGround.getWidth(),buildingBackGround.getHeight(),4)));
                break;
            case 1:
                buildingBackGround.setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.INFENTRY_FACTORY_SHEET_SIZE,0,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE).getScaledInstance(buildingBackGround.getWidth(),buildingBackGround.getHeight(),4)));
                break;
            case 2:
                buildingBackGround.setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.INFENTRY_FACTORY_SHEET_SIZE*2,0,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE).getScaledInstance(buildingBackGround.getWidth(),buildingBackGround.getHeight(),4)));
                break;
            case 3:
                buildingBackGround.setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.INFENTRY_FACTORY_SHEET_SIZE*3,0,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE).getScaledInstance(buildingBackGround.getWidth(),buildingBackGround.getHeight(),4)));
                break;
            case 4:
                buildingBackGround.setIcon(new ImageIcon(spriteSheet.crop(StaticVariables.INFENTRY_FACTORY_SHEET_SIZE*4,0,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE,StaticVariables.INFENTRY_FACTORY_SHEET_SIZE).getScaledInstance(buildingBackGround.getWidth(),buildingBackGround.getHeight(),4)));
                break;



        }

        boxBackGround.add(buildingBackGround);

    }

    private void setTheBoxBackGround(int i) {
        boxBackGround =new JLabel();
        boxBackGround.setSize(MainFrame.screenSize.width/15,MainFrame.screenSize.height/8);
        if(i>=3)
        {
            boxBackGround.setLocation(j*boxBackGround.getWidth()+MainFrame.screenSize.width/2+MainFrame.screenSize.width/7,getHeight()/2);
            j++;
        }
        else
            boxBackGround.setLocation(i*boxBackGround.getWidth()+MainFrame.screenSize.width/2+MainFrame.screenSize.width/7,0);
        boxBackGround.setIcon(new ImageIcon(imageLoader.loadImage("image/panel/box.png").getScaledInstance(boxBackGround.getWidth(),boxBackGround.getHeight(),4)));

    }
    public void init(){
        setBounds(MainFrame.screenSize.width/2,MainFrame.screenSize.height-MainFrame.screenSize.height/5,MainFrame.screenSize.width,MainFrame.screenSize.height/4);
        setIcon(new ImageIcon(spriteSheet.crop(390,180,spriteSheet.getSheet().getRaster().getWidth()-390,230).getScaledInstance(getWidth(),getHeight(),4)));
        addMouseListener(MainFrame.mainFrame);
        queqeOfUnit=new ArrayList<Unit>();
    }

    public void setTheInformation(GameObject gameObject){
        gatherTheInformation=new JLabel();

        gatherTheInformation.setBounds(0,getHeight()/2-getHeight()/3,getWidth()/2+getWidth()/10,getHeight()/2+getHeight()/4);

        add(gatherTheInformation);

        setTheNameOfObject(gameObject);
        setTheDamageThatObjectDo(gameObject);
        setTheObjectCost(gameObject);
        setTheObjectProperties(gameObject);
        setTheImageOfObject(gameObject);
        gatherTheInformation.setVisible(false);
    }

    private void setTheImageOfObject(GameObject gameObject) {
        try
        {
            pictureOfObject=new JLabel();
            pictureOfObject.setBounds(getWidth()/30,0,getWidth()/10,gatherTheInformation.getHeight());
            pictureOfObject.setIcon(new ImageIcon(gameObject.getImage().getScaledInstance(pictureOfObject.getWidth(),pictureOfObject.getHeight(),4)));
            pictureOfObject.setBackground(new Color(0,0,0,0));
            gatherTheInformation.add(pictureOfObject);
        }catch (Exception e)
        {

        }

    }

    private void setTheObjectProperties(GameObject gameObject)
    {
        objectProperties=new JLabel(gameObject.getDiscription());
        objectProperties.setBounds(gatherTheInformation.getWidth()/2,gatherTheInformation.getHeight()/2,gatherTheInformation.getWidth(),gatherTheInformation.getHeight()/2);
        objectProperties.setFont(fontOfButtons);
        objectProperties.setVerticalTextPosition(JLabel.TOP);




        objectProperties.setForeground(Color.red);

        gatherTheInformation.add(objectProperties);
    }
    private void setTheObjectCost(GameObject gameObject) {
        objectCost=new JLabel("cost: "+gameObject.getCostToBuild());
        objectCost.setBounds(gatherTheInformation.getWidth()/2,gatherTheInformation.getY()/2,gatherTheInformation.getWidth()/3,gatherTheInformation.getHeight()/3);
        objectCost.setFont(fontOfButtons);
        objectCost.setVerticalTextPosition(JLabel.TOP);
        objectCost.setForeground(Color.red);
        gatherTheInformation.add(objectCost);
    }

    private void setTheDamageThatObjectDo(GameObject gameObject) {

        objectDamage =new JLabel("<html>damage:<br> <html>"+gameObject.getDamageToEnemy());
        objectDamage.setBounds(gatherTheInformation.getWidth()-getGatherTheInformation().getWidth()/8,gatherTheInformation.getY()/2,gatherTheInformation.getWidth()/3,gatherTheInformation.getHeight()/3);
        objectDamage.setFont(fontOfButtons);
        objectDamage.setVerticalTextPosition(JLabel.TOP);
        objectDamage.setForeground(Color.red);

        gatherTheInformation.add(objectDamage);
    }


    private void setTheNameOfObject(GameObject gameObject) {
        objectName=new JLabel(gameObject.getNameOfObject());
        objectName.setBounds(gatherTheInformation.getWidth()-gatherTheInformation.getWidth()/3,0,gatherTheInformation.getWidth()/3,gatherTheInformation.getHeight()/3);
        objectName.setVerticalTextPosition(JLabel.TOP);
        objectName.setFont(fontOfButtons);
        objectName.setForeground(Color.red);

        gatherTheInformation.add(objectName);

    }


    public Font getFontOfButtons() {
        return fontOfButtons;
    }

    public void setFontOfButtons(Font fontOfButtons) {
        this.fontOfButtons = fontOfButtons;
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

    public ArrayList<Unit> getQueqeOfUnit() {
        return queqeOfUnit;
    }

    public void setQueqeOfUnit(ArrayList<Unit> queqeOfUnit) {
        this.queqeOfUnit = queqeOfUnit;
    }

    public JLabel getUnitQueue() {
        return unitQueue;
    }

    public void setUnitQueue(JLabel unitQueue) {
        this.unitQueue = unitQueue;
    }

    public ArrayList<JLabel> getUnitLabel() {
        return unitLabel;
    }

    public void setUnitLabel(ArrayList<JLabel> unitLabel) {
        this.unitLabel = unitLabel;
    }

    public JLabel getBoxBackGround() {
        return boxBackGround;
    }

    public void setBoxBackGround(JLabel boxBackGround) {
        this.boxBackGround = boxBackGround;
    }

    public JLabel getBuildingBackGround() {
        return buildingBackGround;
    }

    public void setBuildingBackGround(JLabel buildingBackGround) {
        this.buildingBackGround = buildingBackGround;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public JLabel getObjectName() {
        return objectName;
    }

    public void setObjectName(JLabel objectName) {
        this.objectName = objectName;
    }

    public JLabel getObjectDamage() {
        return objectDamage;
    }

    public void setObjectDamage(JLabel objectDamage) {
        this.objectDamage = objectDamage;
    }

    public JLabel getObjectCost() {
        return objectCost;
    }

    public void setObjectCost(JLabel objectCost) {
        this.objectCost = objectCost;
    }

    public JLabel getObjectProperties() {
        return objectProperties;
    }

    public void setObjectProperties(JLabel objectProperties) {
        this.objectProperties = objectProperties;
    }

    public JLabel getGatherTheInformation() {
        return gatherTheInformation;
    }

    public void setGatherTheInformation(JLabel gatherTheInformation) {
        this.gatherTheInformation = gatherTheInformation;
    }

    public JLabel getPictureOfObject() {
        return pictureOfObject;
    }

    public void setPictureOfObject(JLabel pictureOfObject) {
        this.pictureOfObject = pictureOfObject;
    }

    public JLabel getTimeToTrain() {
        return timeToTrain;
    }

    public void setTimeToTrain(JLabel timeToTrain) {
        this.timeToTrain = timeToTrain;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(int spendTime) {
        this.spendTime = spendTime;
    }
}
