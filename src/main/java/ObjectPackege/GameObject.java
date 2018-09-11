package ObjectPackege;


import GameCore.LifeBar;
import GameCore.MainFrame;
import GameCore.World;
import ImageHandel.ImageLoader;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class GameObject extends JLabel {



    protected ImageLoader imageLoader;
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
    protected String nameOfObject,discription;
    protected MainFrame mainFrame;
    protected Rectangle bound;


    protected GameObject(){
        mainFrame=MainFrame.mainFrame;
        imageLoader=new ImageLoader();
        addLife();
        synchronized ( World.allObjects)
        {
            World.allObjects.add(this);
        }


    }

    protected void addLife() {
        new Thread(new Runnable() {
            public void run() {
                while (getWidth()==0)
                {
                    try {
                        Thread.sleep(10);
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
                nameOfObject = "Infantry";
                canShotAir = true;
                objectIsLive = true;
                objectCanMove = true;

                rangeOfAttack = 200;
                speedOfMove = 10;
                speedOfAttack = 2000;
                damageToEnemy = 15;
                timeToTrain = 2000;
                powerNeedToBuild = 0;
                costToBuild = 300;
                life = 150;

                break;
            case 1:
//main factory
                discription="<html>this is a main factory. <br>with this building you can build any other building you want. </html>";
                nameOfObject = "Main Factory";
                World.factoryWhoCanBuild.add((Factory) this);
                powerNeedToBuild = 0;
                costToBuild = 3000;
                life = 3000;

                break;
            case 2:
//power factory
                discription="<html>this is a power factory. <br>you need this building in order to get enough power to build building .</html> ";

                nameOfObject = "Power Factory";

                powerNeedToBuild = 0;
                costToBuild = 300;
                life = 1000;

                break;
            case 3:
//InfentryFactory

                nameOfObject = "Infentry Factory";

                powerNeedToBuild = 40;
                costToBuild = 1000;
                life = 1500;

                break;

            case 4:
//AirForceFactory

                nameOfObject = "Air Force Factory";

                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;

            case 5:
//CloneFactory

                nameOfObject = "Clone Factory";

                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 6:
                //SpacielOpsFactory

                nameOfObject = "Spaciel Ops Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 7:
                //SuperWeponeFactory

                nameOfObject = "Super Wepone Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 8:
                //MoneyFactory

                nameOfObject = "Money Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 9:
                //TankFactory

                nameOfObject = "Tank Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;
            case 10:
                //SateliteFactory

                nameOfObject = "Satelite Factory";
                life = 500;
                powerNeedToBuild = 0;
                costToBuild = 500;

                break;


        }


    }

    /*switch (getClass().getSimpleName()) {

        case "ArmoredInfentry": {
            canShotAir = false;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=300;
            speedOfMove = 2;
            speedOfAttack = 1500;
            damageToEnemy = 35;
            timeToTrain = 2000;
            powerNeedToBuild = 0;
            costToBuild = 650;
            life = 400;


            break;
        }
        case "BazzokaUnit": {

            canShotAir = true;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=300;
            speedOfMove = 2;
            speedOfAttack = 2000;
            damageToEnemy = 150;
            timeToTrain = 2000;
            powerNeedToBuild = 0;
            costToBuild = 850;
            life = 200;

            break;
        }
        case "Medic": {

            canShotAir = false;
            objectIsLive = false;
            objectCanMove = true;
            rangeOfAttack=150;
            speedOfMove = 3;
            speedOfAttack = 500;
            damageToEnemy = 15;
            timeToTrain = 2000;
            powerNeedToBuild = 0;
            costToBuild = 1500;
            life = 1000;

            break;
        }
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

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
