package geometry;
import java.util.ArrayList;
/**
 * This class represent a rectangle and the functions in it.
 * @author Inbar Avital.
 *
 */
public class Rectangle {
    //members
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] recLines;
    /**
     *  Constructor.
     *  Create a new rectangle with location and width/height.
     * @param upperLeft - the upper left point in the rectangle.
     * @param width - the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        double x = this.upperLeft.getX();
        double y = this.upperLeft.getY();
        this.recLines = new Line[4];
        //up
        this.recLines[0] = new Line(x, y, x + this.width, y);
        //down
        this.recLines[1] = new Line(x, y + this.height
                , x + this.width, y + this.height);
        //right
        this.recLines[2] = new Line(x + this.width, y
                , x + this.width, y + this.height);
        //left
        this.recLines[3] = new Line(x, y, x, y + this.height);
    }
    /**
     * Constructor #2.
     * @param x - the x value of left point.
     * @param y - the y value of let point.
     * @param width - the width of the rec.
     * @param height - the height of the rec.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.recLines = new Line[4];
        //up
        this.recLines[0] = new Line(x, y, x + this.width, y);
        //down
        this.recLines[1] = new Line(x, y + this.height
                , x + this.width, y + this.height);
        //right
        this.recLines[2] = new Line(x + this.width, y
                , x + this.width, y + this.height);
        //left
        this.recLines[3] = new Line(x, y, x, y + this.height);
    }
    /**
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     * @param line - the line that will be checked
     * @return java.util.List<Point> - a list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> interPoints = new ArrayList<>();
        for (Line i : recLines) {
            if (i.isIntersecting(line)) {
                interPoints.add(i.intersectionWith(line));
            }
        }
        return interPoints;
    }
    /**
     * @return double - the width and height of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * @return double - the width and height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * @return Point - the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * @return Line[] - the lines the creates the rectangle.
     */
    public Line[] getLines() {
        return recLines;
    }
}