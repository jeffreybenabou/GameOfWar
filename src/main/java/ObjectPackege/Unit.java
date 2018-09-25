package ObjectPackege;




import GameCore.MainFrame;
import GameCore.UnitAttackLabel;
import GameCore.World;
import ImageHandel.SpriteSheet;
import javax.swing.*;
import java.awt.*;



public class Unit extends GameObject {



    protected SpriteSheet standSpriteSheet, moveSpriteSheet;

    protected UnitAttackLabel unitAttackLabel;
    protected int xToMove=0,yToMove=0;
    protected Point pointToMove;
    protected boolean unitHasBeenPick=false;
    protected double distance;
    protected Double directionY,directionX;
    protected double angle;
    protected int xWitdhToCrop,yHeightToCrop;
    protected boolean unitHasBeenCheckForIntersect=false;
    protected boolean canAttackThisType=false;
    private int dirOfUnit=0;


    public Unit() {
        super();
        pointToMove=new Point();
        setLayout(new GridLayout(6,1));



    }




    public void setTheUnitMethod() {


        new Thread(new Runnable() {
            public void run() {
                while (objectIsLive)
                {


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                        moveTheUnit();
                    checkIfUnitInRangeOfEnemy();



                }
            }
        }).start();
    }

    private void checkIfUnitInRangeOfEnemy() {
        // TODO: 25/09/2018 keep continue on this
        for (int i = 0; i < World.allEnemyObjects.size(); i++) {
            checkWhatUnitCanShotAt(i);
            attackTheEnemy(i);

        }

    }

    private void attackTheEnemy(int i) {
        if(calculateTheDistanceBetweenUnits(World.allEnemyObjects.get(i))<=rangeOfAttack&&canAttackThisType)
        {
            objectIsStanding=false;
            objectIsMoving=false;
            objectIsAttacking=true;

            MainFrame.world.getBackGroundImage().add(unitAttackLabel=new UnitAttackLabel(this));
            changeTheImage();
            while (objectIsAttacking&&!objectIsMoving&&!objectIsStanding)
            {






                try {
                    Thread.sleep(speedOfAttack);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if(!World.allEnemyObjects.get(i).isObjectIsLive())
                    break;

                World.allEnemyObjects.get(i).setLife( World.allEnemyObjects.get(i).getLife()-damageToEnemy);

                World.allEnemyObjects.get(i).getLifeBar().setString(""+World.allEnemyObjects.get(i).getLife());
                World.allEnemyObjects.get(i).getLifeBar().setValue(World.allEnemyObjects.get(i).getLife());
            }
            objectIsAttacking=false;
        }

    }

    private void checkWhatUnitCanShotAt(int index) {
        if(canShotAir&&!canShotGround&&World.allEnemyObjects.get(index).getClass().getPackage().getName().contains("Air"))
        {
            canAttackThisType=true;
//            only attack air
        }
        else if (!canShotAir&&canShotGround&&!World.allEnemyObjects.get(index).getClass().getPackage().getName().contains("Air"))
        {
//            only ground
            canAttackThisType=true;
        }

        else if (type==13)
        {
//            medic
        }
        else //            all the rest can attack normal
            canAttackThisType = canShotAir&&canShotGround;

    }

    private void calculateTheDistance(){
        float xDistance = pointToMove.x - getX();
        float yDistance = pointToMove.y - getY();
        distance   = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));

    }
    public void moveTheUnit() {



        while (objectIsMoving)
        {
            setUnitHasBeenCheckForIntersect(false);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            calculateTheDistance();
            directionY = ((pointToMove.getY() - getY()) / distance) * speedOfMove;
            directionX = ((pointToMove.getX() - getX()) / distance) * speedOfMove;

            if (distance < 50)
            {
                objectIsMoving=false;
                objectIsStanding=true;
            }

            setLocation((int) ((getX() + directionX)), (int) ((getY() + directionY)));
            if(!getClass().getPackage().toString().contains("Air"))
            changeTheImage();


        }
        if(!getClass().getPackage().toString().contains("Air"))

            changeTheImage();


    }








    public void changeTheImage(){





        if(isObjectIsMoving())
        {

            if(yToMove>moveSpriteSheet.getSheet().getRaster().getHeight()-yHeightToCrop)
                yToMove=0;
            setIcon(new ImageIcon(moveSpriteSheet.crop(xToMove,yToMove,xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
            yToMove+=yHeightToCrop;
        }else if (objectIsStanding)
        {
            yToMove=0;
            if(xToMove>standSpriteSheet.getSheet().getRaster().getWidth()-xWitdhToCrop)
            {
                dirOfUnit=0;
                        xToMove=0;
            }

            setIcon(new ImageIcon(standSpriteSheet.crop(xToMove,yToMove,xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
            xToMove+=xWitdhToCrop;



        }else if (objectIsAttacking)
        {
            xToMove=dirOfUnit*xWitdhToCrop;
            if(getClass().getPackage().getName().contains("Air"))
            setIcon(new ImageIcon(standSpriteSheet.crop(0,0,xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
            else
                setIcon(new ImageIcon(moveSpriteSheet.crop(xToMove,yToMove,xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));

        }






    }



    public void setTheRightIcon() {
        angle=calculateTheAngle();
        if(angle>=170&&angle<190)
        {
            xToMove=2;
            dirOfUnit=2;
        }

        else if(angle>=190&&angle<250)
        {
            xToMove=6;
            dirOfUnit=6;
        }


        else if(angle>=250&&angle<290)
        {
            dirOfUnit=1;
            xToMove=1;
        }


        else if(angle>=290&&angle<340)
        {
            dirOfUnit=7;
            xToMove=7;
        }


        else if(angle>=340||angle<20)
        {
            dirOfUnit=3;
            xToMove=3;
        }

        else if(angle>=20&&angle<70)
        {
            dirOfUnit=5;
            xToMove=5;
        }


        else if(angle>=70&&angle<110)
        {
            dirOfUnit=0;
            xToMove=0;
        }


        else if(angle>=110&&angle<170)
        {
            dirOfUnit=4;
            xToMove=4;
        }


        xToMove*=xWitdhToCrop;
    }

    private double calculateTheAngle() {
        float xDistance = pointToMove.x - getX();
        float yDistance = pointToMove.y - getY();

        return (360 + Math.toDegrees(Math.atan2(yDistance, xDistance))) % 360;


    }



    public SpriteSheet getStandSpriteSheet() {
        return standSpriteSheet;
    }

    public void setStandSpriteSheet(SpriteSheet standSpriteSheet) {
        this.standSpriteSheet = standSpriteSheet;
    }

    public SpriteSheet getMoveSpriteSheet() {
        return moveSpriteSheet;
    }

    public void setMoveSpriteSheet(SpriteSheet moveSpriteSheet) {
        this.moveSpriteSheet = moveSpriteSheet;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Double getDirectionY() {
        return directionY;
    }

    public void setDirectionY(Double directionY) {
        this.directionY = directionY;
    }

    public Double getDirectionX() {
        return directionX;
    }

    public void setDirectionX(Double directionX) {
        this.directionX = directionX;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getxWitdhToCrop() {
        return xWitdhToCrop;
    }

    public void setxWitdhToCrop(int xWitdhToCrop) {
        this.xWitdhToCrop = xWitdhToCrop;
    }

    public int getyHeightToCrop() {
        return yHeightToCrop;
    }

    public void setyHeightToCrop(int yHeightToCrop) {
        this.yHeightToCrop = yHeightToCrop;
    }

    public int getxToMove() {
        return xToMove;
    }

    public void setxToMove(int xToMove) {
        this.xToMove = xToMove;
    }

    public int getyToMove() {
        return yToMove;
    }

    public void setyToMove(int yToMove) {
        this.yToMove = yToMove;
    }

    public Point getPointToMove() {
        return pointToMove;
    }

    public void setPointToMove(Point pointToMove) {
        this.pointToMove = pointToMove;
    }

    public boolean isUnitHasBeenPick() {
        return unitHasBeenPick;
    }

    public boolean isUnitHasBeenCheckForIntersect() {
        return unitHasBeenCheckForIntersect;
    }

    public void setUnitHasBeenCheckForIntersect(boolean unitHasBeenCheckForIntersect) {
        this.unitHasBeenCheckForIntersect = unitHasBeenCheckForIntersect;
    }

    public void setUnitHasBeenPick(boolean unitHasBeenPick) {
        this.unitHasBeenPick = unitHasBeenPick;
    }

    public boolean isCanAttackThisType() {
        return canAttackThisType;
    }

    public void setCanAttackThisType(boolean canAttackThisType) {
        this.canAttackThisType = canAttackThisType;
    }

    public int getDirOfUnit() {
        return dirOfUnit;
    }

    public void setDirOfUnit(int dirOfUnit) {
        this.dirOfUnit = dirOfUnit;
    }
}

