package Units.InfantryUnit;



import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

import java.awt.*;

public class Sniper extends HumanUnit {


    public Sniper() {
        super();
        moveSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/sniper/sniper.png"));
        standSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/sniper/sniperSteale.png"));
        setTheUnitMethod(moveSpriteSheet, standSpriteSheet, StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH, StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT);

    }
}
