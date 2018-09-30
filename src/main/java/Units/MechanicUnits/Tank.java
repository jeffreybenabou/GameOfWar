package Units.MechanicUnits;


import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.MechanicUnit;

import javax.swing.*;
import java.awt.*;

public class Tank extends MechanicUnit {

    public static SpriteSheet moveSpriteSheetTank=new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/tank/tank.png"));
    public static SpriteSheet standSpriteSheetTank=new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/tank/tank.png"));


    public Tank(boolean onWorld) {
        super();

        type=15;
        moveSpriteSheet =moveSpriteSheetTank;
        standSpriteSheet =standSpriteSheetTank;
        init(onWorld);
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0, xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();

    }
}
