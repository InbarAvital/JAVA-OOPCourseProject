package setting;

import geometry.Point;
/**
 * this class represent the velocity object
 * Velocity specifies the change in position on
 * the `x` and the `y` axes.
 * @author Inbar Avital.
 */
public class Velocity {
    // members
    private double dx;
    private double dy;
    /**
     * constructor.
     * @param dx - the x axe of the velocity change
     * @param dy - the y axe of the velocity change
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Take a point with position (x,y) and return
     * a new point with position (x+dx, y+dy).
     * @param p - the point to apply the change to
     * @return Point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * gets an angle and a speed and sets the
     * velocity according to that.
     * @param angle - the angle of the movement
     * @param speed - the speed of the movement
     * @return Velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle - 90)) * speed;
        double dy = Math.sin(Math.toRadians(angle - 90)) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * @return double - the x value of the velocity
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * @return double - the y value of the velocity
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * Receives a dx double and set it as this velocity's dx value.
     * @param newDx - the new dx value to set.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }
    /**
     * Receives a dy double and set it as this velocity's dy value.
     * @param newDy - the new dy value to set.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }
}