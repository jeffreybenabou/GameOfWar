package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

import java.awt.*;

public class Medic extends HumanUnit {


    public Medic() {
        super();
        moveSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/medic/medicmove.png"));
        standSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/medic/medicstand.png"));
        setTheUnitMethod(moveSpriteSheet, standSpriteSheet, StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH, StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT);

    }
}
