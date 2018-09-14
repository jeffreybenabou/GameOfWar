package ObjectPackege;


import GameCore.LifeBar;
import GameCore.MainFrame;
import GameCore.World;
import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject extends JLabel {



    protected static ImageLoader imageLoader=new ImageLoader();
    protected BufferedImage image;

    protected LifeBar lifeBar;
    protected int life;
    protected int speedOfMove;
    protected int speedOfAttack;
    protected int damageToEnemy;
    protected int timeToTrain;
    protected int powerNeedToBuild;
    protected int costToBuild;
    protected int rangeOfAttack;



    protected boolean objectCanMove;
    protected boolean objectIsOnWorld;
    protected boolean objectIsMoving=false;
    protected boolean objectIsStanding=true;
    protected boolean enoughPower = true, enoughMoney = true, enoughUnitPlace=true;

    protected boolean canShotAir;
    protected boolean isFloatOnPanel;
    protected boolean objectIsPressed = false;
    protected boolean objectIsLive=true;
    protected int type;
    protected String nameOfObject,discription,buildingNeed;
    protected MainFrame mainFrame;
    protected Rectangle bound;


    protected GameObject(){
        mainFrame=MainFrame.mainFrame;
        imageLoader=new ImageLoader();

        addLife();

            World.allObjects.add(this);



    }

    protected void addLife() {
        new Thread(new Runnable() {
            public void run() {
                while (getWidth()==0)
                {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                setTheLifeBar();
            }
        }).start();

    }

    private void setTheLifeBar() {
        lifeBar=new LifeBar(life);
        add(lifeBar);
        repaint();
    }


    protected void init(){


        setBounds(bound);
        addMouseListener(mainFrame);
        setTheUnitProperties();



    }





    protected void setTheUnitProperties() {
        /*
         * this method will update the object field by their type of class
         *
         *
         * */
        switch (type) {
            case 0:
//Infantry
                buildingNeed="<html>none<html>";
                nameOfObject = "Infantry";
                discription="<html>the infentry unit is great against  air and land units.<br>its cheep and fast to create<html>";
                canShotAir = true;
                objectIsLive = true;
                objectCanMove = true;

                rangeOfAttack = 200;
                speedOfMove = 10;
                speedOfAttack = 2000;
                damageToEnemy = 15;
                timeToTrain = 1;
                powerNeedToBuild = 0;
                costToBuild = 300;
                life = 150;

                break;
            case 1:
//main factory
                buildingNeed="none";
                discription="<html>this is a main factory. <br>with this building you can build any other building you want. </html>";
                nameOfObject = "Main Factory";

                powerNeedToBuild = 0;
                costToBuild = 3000;
                life = 3000;

                break;
            case 2:
//power factory
                buildingNeed="none";
                discription="<html>this is a power factory. <br>you need this building in order to get enough power to build building .</html> ";

                nameOfObject = "Power Factory";

                powerNeedToBuild = 0;
                costToBuild = 300;
                life = 1000;

                break;
            case 3:
//InfentryFactory
                buildingNeed="power factory.";
                discription="<html>this is a Infentry factory. <br>you need this building in order to create infantry units.</html> ";
                World.infentryFactory.add((Factory) this);
                nameOfObject = "Infentry Factory";

                powerNeedToBuild = 40;
                costToBuild = 1000;
                life = 1500;

                break;

            case 4:
//AirForceFactory
                buildingNeed="<html>power factory<br>staelite factory<html>";

                discription="<html>this is a Air Force factory.<br>you need this building in order to create air units.</html> ";

                nameOfObject = "Air Force Factory";

                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;

            case 5:
//CloneFactory
                buildingNeed="<html>Spaciel Ops Factory <html>";

                discription="<html>this is a clone factory.<br>the clone factory give you extra 2 units on each one you build.</html> ";

                nameOfObject = "Clone Factory";

                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 6:
                //SpacielOpsFactory
                buildingNeed="<html>staelite factory<html>";

                discription="<html>this is a Spacial Ops factory.<br>it give you the access to very strong units.</html> ";

                nameOfObject = "Spaciel Ops Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 7:
                //SuperWeponeFactory
                buildingNeed="<html>Spaciel Ops Factory <html>";

                discription="<html>this is a Super Weapon factory.<br>lets make the enemy blow away.</html> ";

                nameOfObject = "Super Wepone Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 8:
                buildingNeed="power factory";
                //MoneyFactory
                discription="<html>this is a Money factory.<br>give us a strong economy.</html> ";

                nameOfObject = "Money Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 9:
                buildingNeed="<html>power factory<br>infentry factory,<br>money factory<html>";
                //TankFactory
                discription="<html>this is a tank factory.<br>lets shot the big guns and roll over the enemy .</html> ";

                nameOfObject = "Tank Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 10:
                //SateliteFactory
                buildingNeed="<html>power factory<br>tank factory,<br>money factory<html>";
                discription="<html>this is a Satellite factory.<br>see the enemy in the map.</html> ";

                nameOfObject = "Satellite Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 11:
//ArmoredInfentry
                nameOfObject = "Armored Infentry";
                buildingNeed="<html>none<html>";
                discription="<html>the Armored Infantry unit is great against land units .<br>its little bit expansive and medium speed but last <br>long and make massive damage to enemy<html>";

                canShotAir = false;
                objectIsLive = true;
                objectCanMove = true;
                rangeOfAttack=300;
                speedOfMove = 2;
                speedOfAttack = 1500;
                damageToEnemy = 35;
                timeToTrain = 10;
                powerNeedToBuild = 0;
                costToBuild = 650;
                life = 400;

                break;
            case 12:
//BazzokaUnit
                nameOfObject = "Bazzoka Unit";
                buildingNeed="<html>none<html>";
                discription="<html>the Bazooka unit is powerful against  air and land units.<br>its not cheep and also slow move but when hit it deal a great damage<html>";
                canShotAir = true;
                objectIsLive = true;
                objectCanMove = true;
                rangeOfAttack=300;
                speedOfMove = 2;
                speedOfAttack = 2000;
                damageToEnemy = 150;
                timeToTrain = 25;
                powerNeedToBuild = 0;
                costToBuild = 850;
                life = 200;

                break;
            case 13:
                //medic
                nameOfObject="Medic";
                buildingNeed="<html>Tank Factory<html>";
                discription="<html>this unit is unique with is power -its heal only solider that are injured.<br>its take time to train but worth the life <br>of your units<html>";

                canShotAir = false;
                objectIsLive = true;
                objectCanMove = true;
                rangeOfAttack=150;
                speedOfMove = 3;
                speedOfAttack = 500;
                damageToEnemy = 15;
                timeToTrain = 45;
                powerNeedToBuild = 0;
                costToBuild = 1500;
                life = 1000;

                break;
            case 14:
//                sniper
                nameOfObject="sniper";
                buildingNeed="<html>spaciel ops factory<html>";
                discription="<html>'fast dead'-this unit deal a massive damage to enemy .<br>worth the effort<html>";

                canShotAir = false;
                objectIsLive = true;
                objectCanMove = true;
                rangeOfAttack=500;
                speedOfMove = 3;
                speedOfAttack = 1500;
                damageToEnemy = 50;
                timeToTrain = 60;
                powerNeedToBuild = 0;
                costToBuild = 1000;
                life = 200;

                break;


        }


    }

    /*switch (getClass().getSimpleName()) {




        case "Sniper": {

            canShotAir = false;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=500;
            speedOfMove = 3;
            speedOfAttack = 1500;
            damageToEnemy = 50;
            timeToTrain = 18000;
            powerNeedToBuild = 0;
            costToBuild = 1000;
            life = 200;

            break;
        }

        case "Tank": {

            canShotAir = false;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=500;
            speedOfMove = 2;
            speedOfAttack = 2000;
            damageToEnemy = 50;
            timeToTrain = 20000;
            powerNeedToBuild = 0;
            costToBuild = 1000;
            life = 800;

            break;
        }

        case "MiniGun": {

            canShotAir = false;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=600;
            speedOfMove = 4;
            speedOfAttack = 500;
            damageToEnemy = 25;
            timeToTrain = 25000;
            powerNeedToBuild = 0;
            costToBuild = 700;
            life = 450;

            break;
        }
        case "AntiAirTank": {

            canShotAir = true;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=500;
            speedOfMove = 4;
            speedOfAttack = 800;
            damageToEnemy = 20;
            timeToTrain = 15000;
            powerNeedToBuild = 0;
            costToBuild = 800;
            life = 300;

            break;
        }
        case "SuperTank": {

            canShotAir = false;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=700;
            speedOfMove = 2;
            speedOfAttack = 1000;
            damageToEnemy = 50;
            timeToTrain = 60000;
            powerNeedToBuild = 0;
            costToBuild = 2000;
            life = 2000;

            break;
        }
        case "BigBoss": {

            canShotAir = true;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=700;
            speedOfMove = 3;
            speedOfAttack = 500;
            damageToEnemy = 45;
            timeToTrain = 75000;
            powerNeedToBuild = 0;
            costToBuild = 3000;
            life = 2500;

            break;
        }








        }




    }*/



    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSpeedOfMove() {
        return speedOfMove;
    }

    public void setSpeedOfMove(int speedOfMove) {
        this.speedOfMove = speedOfMove;
    }

    public int getSpeedOfAttack() {
        return speedOfAttack;
    }

    public void setSpeedOfAttack(int speedOfAttack) {
        this.speedOfAttack = speedOfAttack;
    }

    public int getDamageToEnemy() {
        return damageToEnemy;
    }

    public void setDamageToEnemy(int damageToEnemy) {
        this.damageToEnemy = damageToEnemy;
    }

    public int getTimeToTrain() {
        return timeToTrain;
    }

    public void setTimeToTrain(int timeToTrain) {
        this.timeToTrain = timeToTrain;
    }

    public int getPowerNeedToBuild() {
        return powerNeedToBuild;
    }

    public void setPowerNeedToBuild(int powerNeedToBuild) {
        this.powerNeedToBuild = powerNeedToBuild;
    }

    public int getCostToBuild() {
        return costToBuild;
    }

    public void setCostToBuild(int costToBuild) {
        this.costToBuild = costToBuild;
    }

    public int getRangeOfAttack() {
        return rangeOfAttack;
    }

    public void setRangeOfAttack(int rangeOfAttack) {
        this.rangeOfAttack = rangeOfAttack;
    }

    public boolean isObjectCanMove() {
        return objectCanMove;
    }

    public void setObjectCanMove(boolean objectCanMove) {
        this.objectCanMove = objectCanMove;
    }

    public boolean isObjectIsOnWorld() {
        return objectIsOnWorld;
    }

    public void setObjectIsOnWorld(boolean objectIsOnWorld) {
        this.objectIsOnWorld = objectIsOnWorld;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage() {
        image = new BufferedImage(
                getIcon().getIconWidth(),
                getIcon().getIconHeight(),
                BufferedImage.TYPE_INT_ARGB );
        Graphics g = image.createGraphics();
// paint the Icon to the BufferedImage.
        getIcon().paintIcon(null, g, 0,0);
        g.dispose();
    }

    public boolean isObjectIsMoving() {
        return objectIsMoving;
    }

    public void setObjectIsMoving(boolean objectIsMoving) {
        this.objectIsMoving = objectIsMoving;
    }

    public boolean isObjectIsStanding() {
        return objectIsStanding;
    }

    public void setObjectIsStanding(boolean objectIsStanding) {
        this.objectIsStanding = objectIsStanding;
    }

    public boolean isEnoughPower() {
        return enoughPower;
    }

    public void setEnoughPower(boolean enoughPower) {
        this.enoughPower = enoughPower;
    }

    public boolean isEnoughMoney() {
        return enoughMoney;
    }

    public void setEnoughMoney(boolean enoughMoney) {
        this.enoughMoney = enoughMoney;
    }

    public boolean isEnoughUnitPlace() {
        return enoughUnitPlace;
    }

    public void setEnoughUnitPlace(boolean enoughUnitPlace) {
        this.enoughUnitPlace = enoughUnitPlace;
    }

    public boolean isCanShotAir() {
        return canShotAir;
    }

    public void setCanShotAir(boolean canShotAir) {
        this.canShotAir = canShotAir;
    }

    public boolean isFloatOnPanel() {
        return isFloatOnPanel;
    }

    public void setFloatOnPanel(boolean floatOnPanel) {
        isFloatOnPanel = floatOnPanel;
    }

    public boolean isObjectIsPressed() {
        return objectIsPressed;
    }

    public void setObjectIsPressed(boolean objectIsPressed) {
        this.objectIsPressed = objectIsPressed;
    }

    public boolean isObjectIsLive() {
        return objectIsLive;
    }

    public void setObjectIsLive(boolean objectIsLive) {
        this.objectIsLive = objectIsLive;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public LifeBar getLifeBar() {
        return lifeBar;
    }

    public void setLifeBar(LifeBar lifeBar) {
        this.lifeBar = lifeBar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNameOfObject() {
        return nameOfObject;
    }

    public void setNameOfObject(String nameOfObject) {
        this.nameOfObject = nameOfObject;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    public String getDiscription() {
        return discription;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getBuildingNeed() {
        return buildingNeed;
    }

    public void setBuildingNeed(String buildingNeed) {
        this.buildingNeed = buildingNeed;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
