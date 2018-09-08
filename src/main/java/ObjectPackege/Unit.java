package ObjectPackege;



import GameCore.StaticVariables;
import ImageHandel.ImageLoader;
import ImageHandel.SpriteSheet;

import javax.swing.*;
import java.awt.*;


public class Unit extends GameObject {

    protected SpriteSheet standSpriteSheet, moveSpriteSheet;

    protected int xToMove=0,yToMove=0;
    protected Point pointToMove;
    protected boolean unitHasBeenPick=false;
    protected double distance;
    protected Double directionY,directionX;
    protected double angle;
    protected int xWitdhToCrop,yHeightToCrop;


    public Unit() {
        super();
        initTheSpriteSheet();
        pointToMove=new Point();

    }




    protected void setTheUnitMethod(final SpriteSheet move, final SpriteSheet walk, final int x, final int y) {
        xWitdhToCrop=x;
        yHeightToCrop=y;
        new Thread(new Runnable() {
            public void run() {
                while (objectIsLive)
                {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(objectIsMoving)
                    {
                        moveTheUnit(move,x,y);

                    }else if (objectIsStanding)
                    {
                        changeTheImage(walk, x, y);
                    }

                }
            }
        }).start();
    }
    private void calculateTheDistance(){
        float xDistance = pointToMove.x - getX();
        float yDistance = pointToMove.y - getY();
        distance   = Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));

    }
    private void moveTheUnit(SpriteSheet move,int x,int y) {



        while (objectIsMoving)
        {


            try {
                Thread.sleep(150);
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

            changeTheImage(move,x,y);

        }



    }

    public void changeTheImage(SpriteSheet spriteSheet, int x, int y){


        if(isObjectIsMoving())
        {



            yToMove+=y;
            if(yToMove>spriteSheet.getSheet().getRaster().getHeight()-y)
                yToMove=0;
        }else if (objectIsStanding)
        {
            yToMove=0;
            xToMove+=x;
            if(xToMove>spriteSheet.getSheet().getRaster().getWidth()-x)
                xToMove=0;

        }


        setIcon(new ImageIcon(spriteSheet.crop(xToMove,yToMove,x,y).getScaledInstance(getWidth(),getHeight(),4)));


    }

    public void setTheRightIcon() {
        angle=calculateTheAngle();
        if(angle>=170&&angle<190)
        xToMove=2;
        else if(angle>=190&&angle<250)
            xToMove=6;

        else if(angle>=250&&angle<290)
            xToMove=1;

        else if(angle>=290&&angle<340)
            xToMove=7;

        else if(angle>=340||angle<20)
            xToMove=3;
        else if(angle>=20&&angle<70)
            xToMove=5;

        else if(angle>=70&&angle<110)
            xToMove=0;

        else if(angle>=110&&angle<170)
            xToMove=4;

        xToMove*=xWitdhToCrop;
    }

    private double calculateTheAngle() {
        float xDistance = pointToMove.x - getX();
        float yDistance = pointToMove.y - getY();

        return (360 + Math.toDegrees(Math.atan2(yDistance, xDistance))) % 360;


    }

    private void initTheSpriteSheet() {
        imageLoader=new ImageLoader();
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

    public void setUnitHasBeenPick(boolean unitHasBeenPick) {
        this.unitHasBeenPick = unitHasBeenPick;
    }
}

