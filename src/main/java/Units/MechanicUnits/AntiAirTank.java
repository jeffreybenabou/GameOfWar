package Units.MechanicUnits;


import ImageHandel.SpriteSheet;
import ObjectPackege.MechanicUnit;

import javax.swing.*;

public class AntiAirTank extends MechanicUnit {


    public static SpriteSheet moveSpriteSheetAntiAir =new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/anti air/stand.png"));
    public static SpriteSheet standSpriteSheetAntiAir =new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/anti air/stand.png"));

    public AntiAirTank(boolean onWorld) {
        super();
        type=17;
        moveSpriteSheet = moveSpriteSheetAntiAir;
        standSpriteSheet = standSpriteSheetAntiAir;
        init(onWorld);
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0, xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
