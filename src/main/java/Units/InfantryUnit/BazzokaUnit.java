package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

import javax.swing.*;

public class BazzokaUnit extends HumanUnit {


    public BazzokaUnit() {
        super();
        type=12;
        moveSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/bazzoka/bazzokaMove.png"));
        standSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/bazzoka/bazzokaStanding.png"));

        init();
        setTheUnitMethod(moveSpriteSheet, standSpriteSheet, StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH, StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT);
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0,StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH,StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
