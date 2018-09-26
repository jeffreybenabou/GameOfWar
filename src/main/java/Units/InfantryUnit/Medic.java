package Units.InfantryUnit;

import GameCore.StaticVariables;
import ImageHandel.SpriteSheet;
import ObjectPackege.HumanUnit;
import ObjectPackege.Unit;

import javax.swing.*;
import java.awt.*;

public class Medic extends HumanUnit {



    public static SpriteSheet moveSpriteSheetMedic=new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/medic/medicmove.png"));
    public static SpriteSheet standSpriteSheetMedic=new SpriteSheet(imageLoader.loadImage("image/infentry/userUnits/medic/medicstand.png"));

    public Medic() {
        super();
        type=13;
        moveSpriteSheet =moveSpriteSheetMedic;
        standSpriteSheet =standSpriteSheetMedic;
        init();
        timeToTrain=1;
        setIcon(new ImageIcon(standSpriteSheet.crop(0,0,StaticVariables.HUMAN_UNIT_SHEET_MOVE_WIDTH,StaticVariables.HUMAN_UNIT_SHEET_MOVE_HEGIHT).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
    }


}
