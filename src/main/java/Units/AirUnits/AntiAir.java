package Units.AirUnits;

import GameCore.MainFrame;
import GameCore.StaticVariables;
import GameCore.World;
import ImageHandel.SpriteSheet;
import ObjectPackege.AirUnit;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class AntiAir extends AirUnit {


    public static SpriteSheet moveSpriteSheetAntiAir=new SpriteSheet(imageLoader.loadImage("image/air/user/airUnit.png"));
    public static SpriteSheet standSpriteSheetAntiAir=new SpriteSheet(imageLoader.loadImage("image/air/user/anirAir/move.png"));

    public AntiAir() {
        super();
        xWitdhToCrop= StaticVariables.ANTI_AIR_AIR_CRAFT_WIDTH_SPRITE_SHEET_SIZE;
        yHeightToCrop=StaticVariables.ANTI_AIR_AIR_CRAFT_HEIGHT_SPRITE_SHEET_SIZE;
        standSpriteSheet=standSpriteSheetAntiAir;
        moveSpriteSheet=moveSpriteSheetAntiAir;
        type=20;
        bound = new Rectangle(500, 500, MainFrame.world.getBackGroundImage().getWidth() / 100, MainFrame.world.getBackGroundImage().getWidth() / 100);
        init();
        timeToTrain=1;
        setIcon(new ImageIcon(moveSpriteSheetAntiAir.crop(0,0, xWitdhToCrop,yHeightToCrop).getScaledInstance(getWidth(),getHeight(),4)));
        setImage();
        World.allUnit.add(this);
    }

   
}

