package ObjectPackege;




import GameCore.MainFrame;
import GameCore.UnitAttackLabel;
import GameCore.World;
import ImageHandel.SpriteSheet;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


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
    private boolean targetIsEnemy=false;


    public Unit() {
        super();
        pointToMove=new Point();
        setLayout(new GridLayout(6,1));



    }




    public void setTheUnitMethod() {

        final GameObject gameObject = this;
        new Thread(new Runnable() {
            public void run() {
                while (checkIfObjectIsAlive(gameObject)) {


                    if (!objectIsAttacking)
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    moveTheUnit();

                    checkIfUnitInRangeOfEnemy();

                }
                addExplotionLabel();
                if (getGroup().contains("not"))
                    removeTheObject( World.allEnemyObjects);
                else
                    removeTheObject( World.allObjects);

            }
        }).start();
    }

    private void checkIfUnitInRangeOfEnemy() {

        if(getGroup().equals("friendly"))
        for (int i = 0; i < World.allEnemyObjects.size(); i++) {
            checkWhatUnitCanShotAt(i,World.allEnemyObjects);
            attackTheEnemy(i,World.allEnemyObjects);

        }
        else
            for (int i = 0; i < World.allObjects.size(); i++) {
                checkWhatUnitCanShotAt(i, World.allObjects);
                attackTheEnemy(i,World.allObjects);

            }

    }

    public void addLifeToUnit(){
GameObject gameObject=this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (checkIfObjectIsAlive(gameObject))
                {
                    checkUnitToHeal();
                    try {
                        Thread.sleep(speedOfAttack);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();



    }

    private void checkUnitToHeal() {


        for (int i = 0; i < World.allUnit.size(); i++) {
            try {
                if (World.allUnit.get(i).calculateTheDistanceBetweenUnits(this) <= rangeOfAttack &&
                        World.allUnit.get(i) != this &&
                        World.allUnit.get(i).getLife() < World.allUnit.get(i).getLifeBar().getMaximum()
                        && World.allUnit.get(i).isObjectIsLive()) {
                    World.allUnit.get(i).setLife(World.allUnit.get(i).getLife() + damageToEnemy);
                    World.allUnit.get(i).getLifeBar().setValue(World.allUnit.get(i).getLife() + damageToEnemy);
                    World.allUnit.get(i).getLifeBar().setString("" + World.allUnit.get(i).getLife());


                }
            } catch (Exception e) {
                break;
            }

        }
    }

    private void attackTheEnemy(int i,ArrayList<GameObject>arrayList) {
        try
        {

            if(calculateTheDistanceBetweenUnits(arrayList.get(i))<=rangeOfAttack&&canAttackThisType)
            {
                objectIsStanding=false;
                objectIsMoving=false;
                objectIsAttacking=true;

                try
                {

                    MainFrame.world.getBackGroundImage().add(unitAttackLabel=new UnitAttackLabel(this,arrayList.get(i)),0);

                }catch (Exception e)
                {
                    e.printStackTrace();

                }
                changeTheImage();
                try
                {
                    while (i<arrayList.size()&&calculateTheDistanceBetweenUnits(arrayList.get(i))<=rangeOfAttack&&objectIsAttacking&&!objectIsMoving&&!objectIsStanding&&i<arrayList.size()&&arrayList.get(i).isObjectIsLive()&&isObjectIsLive())
                    {






                        try {
                            Thread.sleep(speedOfAttack);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(arrayList.size()>0&&i<arrayList.size()&&!arrayList.get(i).isObjectIsLive())
                        {
                            break;
                        }else if(arrayList.size()>0&&i<arrayList.size()&&arrayList.get(i).isObjectIsLive())
                            try
                            {

                                synchronized (arrayList.get(i))
                                {
                                    decreaseLifeOfUnit(arrayList.get(i));
                                }





                            }catch (Exception e)
                            {
                                e.printStackTrace();
                                break;
                            }




                    }
                }catch (Exception e)
                {
                    e.printStackTrace();

                }


                objectIsAttacking=false;
                objectIsStanding=true;


            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private void decreaseLifeOfUnit(GameObject unit) {
        try
        {
            if( checkIfObjectIsAlive(unit))
            {

                unit.setLife(unit.getLife()-damageToEnemy);
                unit.getLifeBar().setString(""+unit.getLife());
                unit.getLifeBar().setValue(unit.getLife());
                if(Integer.parseInt(unit.getLifeBar().getString())<0)
                    unit.getLifeBar().setString(""+0);



            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }



    private void checkWhatUnitCanShotAt(int index, ArrayList<GameObject >arrayList) {
        if(canShotAir&&!canShotGround&&arrayList.get(index).getClass().getPackage().getName().contains("Air"))
        {
            canAttackThisType=true;
//            only attack air
        }
        else if (!canShotAir&&canShotGround&&!arrayList.get(index).getClass().getPackage().getName().contains("Air"))
        {
//            only ground
            canAttackThisType=true;
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


            if(targetIsEnemy&&distance<=rangeOfAttack)
            {

                objectIsMoving=false;
                objectIsStanding=true;
            }
            else if (distance < 50)
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

    public void setTargetIsEnemy(boolean targetIsEnemy) {
        this.targetIsEnemy = targetIsEnemy;
    }

    public boolean getTargetIsEnemy() {
        return targetIsEnemy;
    }
}

