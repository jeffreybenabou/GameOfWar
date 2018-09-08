package ObjectPackege;


import GameCore.MainFrame;
import ImageHandel.ImageLoader;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class GameObject extends JLabel {



    protected ImageLoader imageLoader;

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
    protected boolean objectIsMoving=true;
    protected boolean objectIsStanding=false;
    protected boolean enoughPower = true, enoughMoney = true, enoughUnitPlace=true;

    protected boolean canShotAir;
    protected boolean isFloatOnPanel;
    protected boolean objectIsPressed = false;
    protected boolean objectIsLive=true;
    private MainFrame mainFrame;


    public GameObject(Rectangle bound,boolean objectIsOnWorld){
        init(bound,objectIsOnWorld);







    }



    public void init(Rectangle bound, boolean objectIsOnWorld){

        this.objectIsOnWorld=objectIsOnWorld;
        setBounds(bound);
        setTheUnitProperties();


    }





    public void setTheUnitProperties() {
    /*
     * this method will update the object field by their type of class
     *
     *
     * */

//    addMouseListener(mainFrame);
    /*switch (getClass().getSimpleName()) {
        case "Infantry": {

            canShotAir = true;
            objectIsLive = false;
            objectCanMove = true;

            rangeOfAttack=200;
            speedOfMove = 3;
            speedOfAttack = 2000;
            damageToEnemy = 15;
            timeToTrain = 2000;
            powerNeedToBuild = 0;
            costToBuild = 300;
            life = 150;

            break;
        }
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




        case "MainFactory": {

            life = 3000;

            if (objectIsOnWorld) {



            }

            break;
        }
        case "PowerFactory": {
            life = 500;
            powerNeedToBuild = -20;

            break;
        }
        case "InfentryFactory": {

            powerNeedToBuild = 40;
            costToBuild = 1000;
            life = 1500;

            if (objectIsOnWorld) {


            }


            break;
        }
        case "AirForceFactory": {

            life = 500;
            powerNeedToBuild = 0;
            costToBuild = 500;
            if (objectIsOnWorld) {


            }

            break;
        }
        case "CloneFactory": {
            life = 500;
            powerNeedToBuild = 0;
            costToBuild = 500;

            break;
        }
        case "SpacielOpsFactory": {
            life = 500;
            powerNeedToBuild = 0;
            costToBuild = 500;

            break;
        }
        case "SuperWeponeFactory": {
            life = 500;
            powerNeedToBuild = 0;
            costToBuild = 500;

            break;
        }
        case "MoneyFactory": {
            powerNeedToBuild = 20;
            costToBuild = 2000;
            life = 1500;

            break;
        }
        case "TankFactory": {
            powerNeedToBuild = 20;
            costToBuild = 2000;
            life = 1500;

            if (objectIsOnWorld) {


            }

            break;
        }
        case "SateliteFactory": {
            powerNeedToBuild = 20;
            costToBuild = 2000;
            life = 1500;

            break;
        }



    }*/

}




}
