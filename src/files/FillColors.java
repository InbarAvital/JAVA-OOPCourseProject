package files;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *
 * @author Inbar
 *
 */
public class FillColors {
    //members
    private String fill;
    private Image image = null;
    private Color colorFill = null;
    /**
     * Constructor.
     * @param fill - the fill color.
     */
    public FillColors(String fill) {
        this.fill = fill;
    }
    /**
     * sets the fill to image or color.
     */
    public void setFill() {
        if (fill != null) {
            String colorOrImage = fill.split("\\(")[0];
            if (colorOrImage.equals("image")) {
                try {
                    this.image = ImageIO.read(ClassLoader.getSystemClassLoader().
                            getResourceAsStream(fill.split("\\(")[1].substring(
                                    0, fill.split("\\(")[1].length() - 1)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                this.colorFill = ColorsParser.colorFromString(fill);
            }
        }
    }
    /**
     * get image.
     * @return the image
     */
    public Image getImage() {
        return this.image;
    }
    /**
     * get color.
     * @return the color
     */
    public Color getColor() {
    return this.colorFill;
    }
}
