package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

import java.awt.*;

public class ArmoredInfentry extends HumanUnit {


    public ArmoredInfentry() {
        super();
        moveSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/armored/movmentArmored.png"));
        standSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/armored/armored.png"));
        setTheUnitMethod(moveSpriteSheet, standSpriteSheet, StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH, StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT);

    }
}
