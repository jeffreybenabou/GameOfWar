package Units.MechanicUnits;

import ImageHandel.SpriteSheet;
import ObjectPackege.MechanicUnit;

import javax.swing.*;


public class BigBoss extends MechanicUnit {



    public static SpriteSheet moveSpriteSheetBigBoss =new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/bigboss/bigbossstand.png"));
    public static SpriteSheet standSpriteSheetBigBoss =new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/bigboss/bigbossstand.png"));

    public BigBoss() {
        super();
        type=18;
        moveSpriteSheet = moveSpriteSheetBigBoss;
        standSpriteSheet = standSpriteSheetBigBoss;
        init();
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0, xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}