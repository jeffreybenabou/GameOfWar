package Units.AirUnits;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import GameCore.World;
import ImageHandel.SpriteSheet;
import ObjectPackege.AirUnit;

import javax.swing.*;
import java.awt.*;

public class Choper extends AirUnit {

    public static SpriteSheet moveSpriteSheetChoper=new SpriteSheet(imageLoader.loadImage("image/air/user/airUnit.png"));
    public static SpriteSheet standSpriteSheetAntiAir=new SpriteSheet(imageLoader.loadImage("image/air/user/chopper/move.png"));

    public Choper(boolean onWorld) {
        super();
        xWitdhToCrop= StaticVariables.CHOPPER_WIDTH_SPRITE_SHEET_SIZE;
        yHeightToCrop=StaticVariables.CHOPPER_HEIGHT_SPRITE_SHEET_SIZE;

        standSpriteSheet=standSpriteSheetAntiAir;
        moveSpriteSheet=moveSpriteSheetChoper;
        type=21;
        bound = new Rectangle(500, 500, MainFrame.world.getBackGroundImage().getWidth() / 100, MainFrame.world.getBackGroundImage().getWidth() / 100);
        saveTheWidthAndHeight(100,100);
        init(onWorld);
        timeToTrain=1;
        setIcon(new ImageIcon(moveSpriteSheetChoper.crop(StaticVariables.CHOPPER_X_SPRITE_SHEET_SIZE,0, xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
        World.allUnit.add(this);
    }

}
