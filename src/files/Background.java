package files;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;
import interfaces.Sprite;
import runners.GameLevel;

/**
 * in charge of the background.
 * @author Inbar
 *
 */
public class Background implements Sprite {
    // members
    private String background;
    /**
     * Constructor.
     * @param background - the background string.
     */
    public Background(String background) {
        this.background = background;
    }
    @Override
    public void drawOn(DrawSurface d) {
        switch(this.background.split("\\(")[0]) {
        case "image":
            Image image = null;
            try {
                image = ImageIO.read(ClassLoader.getSystemClassLoader().
                        getResourceAsStream(this.background.split("\\(")[1].split("\\)")[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            d.drawImage(0, 0, image);
            break;
        case "color":
            d.setColor(ColorsParser.colorFromString(this.background));
            d.fillRectangle(0, 0, 800, 600);
            break;
        default:
            d.setColor(java.awt.Color.WHITE);
            d.fillRectangle(0, 0, 800, 600);
            break;
        }
    }
    @Override
    public void timePassed() {
    }
    @Override
    public void addToGame(GameLevel g) {
    }
}
