package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

import javax.swing.*;
import java.awt.*;

public class ArmoredInfentry extends HumanUnit {

    public static SpriteSheet moveSpriteSheetArmored=new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/armored/movmentArmored.png"));
    public static SpriteSheet standSpriteSheetArmored=new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/armored/armored.png"));

    public ArmoredInfentry() {
        super();
        type=11;
        moveSpriteSheet =moveSpriteSheetArmored;
        standSpriteSheet =standSpriteSheetArmored;
        init();
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0,StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH,StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
