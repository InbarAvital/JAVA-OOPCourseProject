package setting;

import items.Block;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;

/**
 * this class can contain information of collision points.
 * @author Inbar Avital.
 */
public class CollisionInfo {
    //members
    // The collision point.
    private Point point;
    // The collision object.
    private Collidable object;
    // constructors
    /**
     * Constructor #1.
     * Gets a point and a object and creates a CollisionInfo.
     * @param point - the point of the collision.
     * @param newObject - the object to collide with.
     */
    public CollisionInfo(Point point, Collidable newObject) {
        this.point = point;
        this.object = newObject;
    }
    /**
     * constructor #2.
     * default constructor. Needed to use it many times during the code.
     */
    public CollisionInfo() {
        this.point = new Point(0, 0);
        Rectangle newObject = new Rectangle(point, 1, 1);
        this.object = new Block(newObject);
    }
    /**
     * returns the point at which the collision occurs.
     * @return Point
     */
    public Point collisionPoint() {
        return point;
    }
    /**
     * returns the collidable object involved in the collision.
     * @return Collidable
     */
    public Collidable collisionObject() {
        return object;
    }
}