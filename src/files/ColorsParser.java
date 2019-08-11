package files;

import java.awt.Color;

/**
 * makes string a color.
 * @author Inbar
 *
 */
public class ColorsParser {
    /**
     * Parse color definition and return the specified color.
     * @param s - the string color
     * @return the color.
     */
    public static java.awt.Color colorFromString(String s) {
        String m = s;
        s = s.split("\\(")[1].split("\\)")[0];
        Color color = null;
        switch (s) {
        case "black":
            color = Color.BLACK;
            break;
        case "blue":
            color = Color.BLUE;
            break;
        case "cyan":
            color = Color.CYAN;
            break;
        case "lightGray":
            color = Color.LIGHT_GRAY;
            break;
        case "gray":
            color = Color.GRAY;
            break;
        case "darkGray":
            color = Color.DARK_GRAY;
            break;
        case "green":
            color = Color.GREEN;
            break;
        case "pink":
            color = Color.PINK;
            break;
        case "yellow":
            color = Color.YELLOW;
            break;
        case "magneta":
            color = Color.MAGENTA;
            break;
        case "red":
            color = Color.RED;
            break;
        case "white":
            color = Color.WHITE;
            break;
        case "orange":
            color = Color.ORANGE;
            break;
        case "RGB":
            m = m.split("\\(")[2];
            String[] rgb = new String[3];
            rgb[0] = m.split(",")[0];
            rgb[1] = m.split(",")[1].split(",")[0];
            rgb[2] = m.split(",")[2].split("\\)")[0];
            color = new Color(Integer.parseInt(rgb[0]),
                    Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
            break;
        default:
            color = Color.WHITE;
            break;
            }
        return color;
    }
}
