package files;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
/**
 *
 * @author Inbar
 *
 */
public class HashMapsSet {
    // members
    private HashMap<Integer, Image> images = new HashMap<Integer, Image>();
    private HashMap<Integer, Color> colors = new HashMap<Integer, Color>();
    private HashMap<Integer, String> fill;

    /**
     * constructor.
     * @param fill - the fill hash map.
     */
    public HashMapsSet(HashMap<Integer, String> fill) {
        this.fill = fill;
    }

    /**
     * Sets the hash maps.
     */
    public void setImagesColors() {
        if (fill != null) {
            for (int i = 1; i < 20; i++) {
                if (fill.containsKey(i)) {
                    if (fill.get(i).split("\\(")[0].equals("image")) {
                        try {
                            images.put(i, ImageIO.read(ClassLoader.getSystemClassLoader().
                                    getResourceAsStream(fill.get(i).split("\\(")[1].substring(0,
                                            fill.get(i).split("\\(")[1].length() - 1))));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (fill.get(i).split("\\(")[0].equals("color")) {
                        colors.put(i, ColorsParser.colorFromString(fill.get(i)));
                    }
                }
            }
        }
    }
    /**
     * @return the color's hash map.
     */
    public HashMap<Integer, Color> getColors() {
        return this.colors;
    }
    /**
     * @return the images' hash map.
     */
    public HashMap<Integer, Image> getImages() {
        return this.images;
    }
}
