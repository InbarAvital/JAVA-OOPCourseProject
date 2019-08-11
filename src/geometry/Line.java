package geometry;
/**
 * This class represent a line object.
 * @author Inbar Avital.
 */
public class Line {
    //members
    // the point that starts the line
    private Point start;
    // the point that ends the line
    private Point end;
    // constructors
   /**
    * constructor #1.
    * @param start - the beginning line of the line
    * @param end - the ending point of the line
    */
    public Line(Point start, Point end) {
        // sets start point
        this.start = new Point(start.getX(), start.getY());
        // sets ending point
        this.end = new Point(end.getX(), end.getY());
    }
   /**
    * constructor #2.
    * @param x1 - x value of start point
    * @param y1 - y value of start point
    * @param x2 - x value of ending point
    * @param y2 - y value of ending point
    */
    public Line(double x1, double y1, double x2, double y2) {
        // sets start point
        this.start = new Point(x1, y1);
        // sets ending point
        this.end = new Point(x2, y2);
    }
   /**
    * @return double - the length of the line
    */
    public double length() {
        return start.distance(end);
    }
   /**
    * @return Point -  the middle point of the line
    */
    public Point middle() {
        Point middle = new Point((start.getX() + end.getX()) / 2,
                (start.getY() + end.getY()) / 2);
        return middle;
    }
    /**
     * @return Point - the start point of the line
     */
    public Point start() {
        return start;
    }
    /**
     * @return Point - the end point of the line
     */
    public Point end() {
        return end;
    }
    /**
     * Checks wether the lines intersect or otherwise.
     * @param other - the line to check the intersection with.
     * @return boolean - true if the lines intersect,
     * false otherwise
     */
    public boolean isIntersecting(Line other) {
        // will later represent the incline of the lines
        double m1, m2, x, y;
        // these "if"s sets the incline of the lines
        // in case the y values are the same in line 1
        if (this.start.getX() == this.end.getX()) {
            // in case the x values are the same in line 2
            if (other.start().getX() == other.end().getX()) {
                return false;
            // in case the x values are different
            } else {
                m2 = (other.start().getY() - other.end().getY())
                        / (other.start().getX() - other.end().getX());
                x = this.start.getX();
            }
            y = m2 * (x - other.start().getX()) + other.start().getY();
            if (y > this.start.getY() && y > this.end.getY()) {
                return false;
            }
            if (y < this.start.getY() && y < this.end.getY()) {
                return false;
            }
        // in case the x values of line 2 are the same
        } else if (other.start().getX() == other.end().getX()) {
            m1 = (this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX());
            x = other.start().getX();
            y = m1 * (x - this.start.getX()) + this.start.getY();
            if (y > other.start().getY() && y > other.end().getY()) {
                return false;
            }
            if (y < other.start().getY() && y < other.end().getY()) {
                return false;
            }
        // in case the x values of all lines are different
        } else {
            m1 = (this.start.getY() - this.end.getY())
                    / (this.start.getX() - this.end.getX());
            m2 = (other.start().getY() - other.end().getY())
                    / (other.start().getX() - other.end().getX());
            // if the inclines are different
            if (m1 == m2) {
                return false;
            }
            x = (m1 * this.start.getX() - this.start.getY()
                   - m2 * other.start().getX() + other.start().getY())
                   / (m1 - m2);
        }
        // if the collision point is only in the "endless"
        // line but not on these lines.
        if (x > this.start.getX() && x > this.end.getX()) {
            return false;
        }
        if (x < this.start.getX() && x < this.end.getX()) {
            return false;
        }
        if (x > other.start().getX() && x > other.end().getX()) {
            return false;
        }
        if (x < other.start().getX() && x < other.end().getX()) {
            return false;
        }
        return true;
    }
    /**
     * returns the intersection point, if there is any, between the
     * two lines (this and the param).
     * @param other - the line to send the intersection point with.
     * @return Point - the intersection point if the
     * lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // if the lines intersect
        if (this.isIntersecting(other)) {
            // will later represent the incline of the lines
            double m1, m2;
            // will later represent the x and y values
            // of the intersection point
            double x, y;
            // these "if"s sets the incline of the lines
            // in case the x values are the same in line 1
            if (this.start.getX() == this.end.getX()) {
                m2 = (other.start().getY() - other.end().getY())
                        / (other.start().getX() - other.end().getX());
                x = this.start.getX();
                // sets y value of intersection point
                y = m2 * (x - other.start().getX()) + other.start().getY();
            // in case the x values of line 2 is the same
            } else if (other.start().getX() == other.end().getX()) {
                m1 = (this.start.getY() - this.end.getY())
                        / (this.start.getX() - this.end.getX());
                x = other.start().getX();
                // sets y value of intersection point
                y = m1 * (x - this.start.getX()) + this.start.getY();
            // in case the x values of all lines are different
            } else {
                m1 = (this.start.getY() - this.end.getY())
                        / (this.start.getX() - this.end.getX());
                m2 = (other.start().getY() - other.end().getY())
                        / (other.start().getX() - other.end().getX());
                x = (m1 * this.start.getX() - this.start.getY()
                       - m2 * other.start().getX() + other.start().getY())
                       / (m1 - m2);
                // sets y value of intersection point
                y = m2 * (x - other.start().getX()) + other.start().getY();
            }
            // returns intersection point
            Point interPoint = new Point(x, y);
            return interPoint;
        }
        // if the lines doesn't intersect
        return null;
    }
    /**
     * checks if the this line and "other" are equal.
     * @param other - the line to check if equals to.
     * @return boolean - true is the lines are equal
     * false otherwise
     */
    public boolean equals(Line other) {
        // if both start and end are equal
        if ((this.start.equals(other.start())
                && (this.end.equals(other.end())))) {
            return true;
        }
        // if end equal start on both
        if ((this.end.equals(other.start())
                && (this.start.equals(other.end())))) {
            return true;
        }
        // if they are not equal
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect - the rectangle to check the closest intersection with.
     * @return Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line current = new Line(this.start, this.end);
        java.util.List<Point> interPoints = rect.intersectionPoints(current);
        Point closest;
        // if there are no intersection points, return null.
        if (interPoints.size() == 0) {
            return null;
        }
        closest = interPoints.get(0);
        // if there are intersection points. keep the closest.
        for (Point p: interPoints) {
            if (p.distance(current.start) < closest.distance(current.start)) {
                closest = p;
            }
        }
        // return the closest intersection points.
        return closest;
    }
    /**
     * gets a point and returns true if the point is in line
     * and false otherwise.
     * @param point - the point to check if it in line.
     * @return boolean
     */
    public boolean isPointInLine(Point point) {
        // checks if the point exceeds the limits of the line.
        if (point.getX() - 0.001 > this.start.getX()
                && point.getX() - 0.001 > this.end.getX()) {
            return false;
        }
        if (point.getX() + 0.001 < this.start.getX()
                && point.getX() + 0.001 < this.end.getX()) {
            return false;
        }
        if (point.getY() - 0.001 > this.start.getY()
                && point.getY() - 0.001 > this.end.getY()) {
            return false;
        }
        if (point.getY() + 0.001 < this.start.getY()
                && point.getY() + 0.001 < this.end.getY()) {
            return false;
        }
        // if it passed all of the
        return true;
    }
}