package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

public class Infantry extends HumanUnit {



    public Infantry() {
        super();
        type=0;
        moveSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/infentry/movmentInfentry.png"));
        standSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/infentry/steale.png"));
        init();
        setTheUnitMethod(moveSpriteSheet, standSpriteSheet, StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH, StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT);

    }
}
