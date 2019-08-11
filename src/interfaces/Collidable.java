package interfaces;

import geometry.Point;
import geometry.Rectangle;
import items.Ball;
import setting.Velocity;

/**
 * interface of all of the collidables objects.
 * @author Inbar Avital
 */
public interface Collidable {
    /**
     * @return Rectangle - the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter - the hitter ball.
     * @param collisionPoint - a collision point with the coliidable shape.
     * @param currentVelocity - the current velocity of the ball
     * before the hit.
     * @return Velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}