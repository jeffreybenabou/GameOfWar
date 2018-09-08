package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

public class BazzokaUnit extends HumanUnit {


    public BazzokaUnit() {
        super();
        moveSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/bazzoka/bazzokaMove.png"));
        standSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/bazzoka/bazzokaStanding.png"));
        setTheUnitMethod(moveSpriteSheet, standSpriteSheet, StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH, StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT);

    }
}
