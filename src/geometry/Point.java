package geometry;
/**
 * This class represent a point object.
 * @author Inbar Avital.
 */
public class Point {
    // members
    private double x;
    private double y;
    // constructor
    /**
     * sets the point.
     * @param x - double x value of the point
     * @param y - double y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Return the distance between two points.
     * @param other - the point that will be
     * checked the distance with.
     * @return double - the distance of this
     * point to the other point
     */
    public double distance(Point other) {
        return (Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY()))));
    }
    /**
     * Checks if the points are equal.
     * @param other - the point that will be
     * checked if is equal
     * @return boolean - true if the points are
     * equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }
    /**
     * @return double - the x value of this point
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return double - the y value of this point
     */
    public double getY() {
        return this.y;
    }
}
