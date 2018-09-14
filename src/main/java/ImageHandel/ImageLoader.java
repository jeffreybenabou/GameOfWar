package ImageHandel;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageLoader {
    private ClassLoader classLoader = ImageLoader.class.getClassLoader();

    private File file;

    public BufferedImage loadImage(String path) {
        try {

            file = new File(classLoader.getResource(path).toURI());

            return             ImageIO.read(file);

        } catch (IOException e) {


            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return null;
    }

    public  BufferedImage scale(BufferedImage sbi,  int dWidth, int dHeight) {
        BufferedImage dbi = null;

        if(sbi != null) {
            dbi = new BufferedImage(dWidth, dHeight, 2);
            Graphics2D g = dbi.createGraphics();
            AffineTransform at = AffineTransform.getScaleInstance(dWidth, dHeight);
            g.drawRenderedImage(sbi, at);

        }
        return dbi;
    }





}
