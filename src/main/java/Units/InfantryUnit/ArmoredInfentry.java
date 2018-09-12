package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;

import javax.swing.*;
import java.awt.*;

public class ArmoredInfentry extends HumanUnit {


    public ArmoredInfentry() {
        super();
        type=11;
        moveSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/armored/movmentArmored.png"));
        standSpriteSheet =new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/armored/armored.png"));
        init();
        setTheUnitMethod(moveSpriteSheet, standSpriteSheet, StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH, StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT);
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0,StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH,StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }
}
