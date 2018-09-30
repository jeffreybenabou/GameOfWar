package Units.MechanicUnits;


import ImageHandel.SpriteSheet;
import ObjectPackege.MechanicUnit;

import javax.swing.*;

public class SuperTank extends MechanicUnit {


    public static SpriteSheet moveSpriteSheetSuperTank =new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/supertank/supertank.png"));
    public static SpriteSheet standSpriteSheetSuperTank =new SpriteSheet(imageLoader.loadImage("image/mechanic/user unit/supertank/supertank.png"));

    public SuperTank(boolean onWorld) {
        super();
        type=19;
        moveSpriteSheet = moveSpriteSheetSuperTank;
        standSpriteSheet = standSpriteSheetSuperTank;
        init(onWorld);
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0, xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
