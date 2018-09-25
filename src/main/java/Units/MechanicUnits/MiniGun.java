package Units.MechanicUnits;

import ImageHandel.SpriteSheet;
import ObjectPackege.MechanicUnit;

import javax.swing.*;
import java.awt.*;


public class MiniGun extends MechanicUnit {
    public static SpriteSheet moveSpriteSheetMiniGun =new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/minigun/minigun.png"));
    public static SpriteSheet standSpriteSheetMiniGun =new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/minigun/minigun.png"));

    public MiniGun() {
        super();
        type=16;
        moveSpriteSheet = moveSpriteSheetMiniGun;
        standSpriteSheet = standSpriteSheetMiniGun;
        init();
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0, xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
