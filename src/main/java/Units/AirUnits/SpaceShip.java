package Units.AirUnits;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import GameCore.World;
import ImageHandel.SpriteSheet;
import ObjectPackege.AirUnit;

import javax.swing.*;
import java.awt.*;

public class SpaceShip extends AirUnit {

    public static SpriteSheet moveSpriteSheetSpaceShip =new SpriteSheet(imageLoader.loadImage("image/air/user/airUnit.png"));

    public SpaceShip() {
        super();
        xWitdhToCrop = StaticVariables.SPACE_SHIP_WIDTH_SPRITE_SHEET_SIZE;
        yHeightToCrop = StaticVariables.SPACE_SHIP_HEIGHT_SPRITE_SHEET_SIZE;

        standSpriteSheet = moveSpriteSheetSpaceShip;
        moveSpriteSheet = moveSpriteSheetSpaceShip;
        type = 22;
        bound = new Rectangle(500, 500, MainFrame.world.getBackGroundImage().getWidth() / 100, MainFrame.world.getBackGroundImage().getWidth() / 100);
        init();
        timeToTrain = 1;
        setIcon(new ImageIcon(moveSpriteSheetSpaceShip.crop(StaticVariables.SPACE_SHIP_X_SPRITE_SHEET_SIZE, 0, xWitdhToCrop, yHeightToCrop).getScaledInstance(getWidth(), getHeight(), 4)));
        setImage();
        World.allUnit.add(this);
    }
}
