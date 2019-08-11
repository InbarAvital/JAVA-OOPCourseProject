package setting;
import java.awt.Color;
import java.util.ArrayList;
import biuoop.DrawSurface;
import geometry.Line;
import interfaces.Collidable;

/**
 * This class contains the game's environment.
 * All of the collidables in a game are held and kept here.
 * @author Inbar Avital.
 */
public class GameEnvironment {
    //members
    private java.util.List<Collidable> collid;
    //constructor
    /**
     * Default constructor.
     */
    public GameEnvironment() {
         this.collid = new ArrayList<>();
    }
    //methods
    /**
     * add the given collidable to the environment.
     * @param c - the collidable item.
     */
    public void addCollidable(Collidable c) {
        collid.add(c);
    }
    /**
     * removes the given collidable from the environment.
     * @param c - the collidable item.
     */
    public void removeCollidable(Collidable c) {
        collid.remove(c);
    }
    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory - the movement line.
     * @return CollisionInfo
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        java.util.List<CollisionInfo> intersectInfo = new ArrayList<>();
        CollisionInfo current = new CollisionInfo();
        // sets the collision info of all of the upcoming collisions.
        for (Collidable i: this.collid) {
            if (i.getCollisionRectangle().
                    intersectionPoints(trajectory).size() != 0) {
                current = new CollisionInfo(trajectory.
                        closestIntersectionToStartOfLine(i.
                                getCollisionRectangle()), i);
                intersectInfo.add(current);
            }
        }
        // if there arn't any, return null
        if (intersectInfo.size() == 0) {
            return null;
        // if there are collisions
        } else {
            current = intersectInfo.get(0);
            // put into current the closest collision.
            for (CollisionInfo i: intersectInfo) {
                if (i.collisionPoint().distance(trajectory.start())
                        < current.collisionPoint().
                        distance(trajectory.start())) {
                        current = i;
                }
            }
            return current;
        }

    }
    /**
     * Gets a draw surface and draw the game's collidables.
     * @param d - the draw surface.
     */
    public void drawCollidables(DrawSurface d) {
        d.setColor(Color.BLACK);
        for (Collidable i: collid) {
            d.fillRectangle((int) i.getCollisionRectangle().
                            getUpperLeft().getX(),
                    (int) i.getCollisionRectangle().getUpperLeft().getY(),
                    (int) i.getCollisionRectangle().getWidth(),
                    (int) i.getCollisionRectangle().getHeight());
        }
    }



}