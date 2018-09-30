package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

import javax.swing.*;

public class BazzokaUnit extends HumanUnit {


    public static SpriteSheet moveSpriteSheetBazzoka=new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/bazzoka/bazzokaMove.png"));
    public static SpriteSheet standSpriteSheetBazzoka=new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/bazzoka/bazzokaStanding.png"));

    public BazzokaUnit(boolean onWorld) {
        super();
        type=12;
        moveSpriteSheet =moveSpriteSheetBazzoka;
        standSpriteSheet =standSpriteSheetBazzoka;
        init(onWorld);
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0,StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH,StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
