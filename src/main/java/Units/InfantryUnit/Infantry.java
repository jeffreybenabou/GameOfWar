package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

import javax.swing.*;

public class Infantry extends HumanUnit {


    public static SpriteSheet moveSpriteSheetInfentry=new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/infentry/movmentInfentry.png"));
    public static SpriteSheet standSpriteSheetInfentry=new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/infentry/steale.png"));

    public Infantry() {
        super();
        type=0;
        moveSpriteSheet =moveSpriteSheetInfentry;
        standSpriteSheet =standSpriteSheetInfentry;
        init();
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0,StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH,StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
