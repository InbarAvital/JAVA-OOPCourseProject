package items;

import biuoop.DrawSurface;
import files.FillColors;
import files.HashMapsSet;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import runners.GameLevel;
import setting.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the block class. It is in charge of every block in the
 * game(currently, rectangles).
 *
 * @author Inbar Avital
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // members
    // The rectangle of the block.
    private Rectangle shape;
    // The number of hits left on the block.
    private int hitNumber;
    // The color of the block.
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private FillColors fill;
    private Color stroke;
    private Color color = null;
    private HashMapsSet maps;
    // // hash maps
    private HashMap<Integer, String> hash;

    // constructors
    /**
     * constructor #1. Gets a rectangle and creates a block based on the shape.
     *
     * @param shape - The shape of the block.
     */
    public Block(Rectangle shape) {
        this.shape = shape;
        this.color = java.awt.Color.BLACK;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * constructor #2. Gets a upper left point of the rectangle, height and width,
     * and creates with it the rectangle to be the block.
     *
     * @param upperLeft - the upper left point of the shape.
     * @param width     - the width of the shape.
     * @param height    - the height of the shape.
     */
    public Block(Point upperLeft, double width, double height) {
        Rectangle a = new Rectangle(upperLeft, width, height);
        this.shape = a;
        this.color = java.awt.Color.BLACK;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor #3. This constructor is like the second constructor, but unlike
     * others that chooses black as the default color, it receives a color and sets
     * the block's color to be that color.
     *
     * @param upperLeft - the upper left point of the shape.
     * @param width     - the width of the shape.
     * @param height    - the height of the shape.
     * @param color     - the color of the block.
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        Rectangle a = new Rectangle(upperLeft, width, height);
        this.shape = a;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * constructor #4.
     *
     * @param x      - x value of left point.
     * @param y      - y value of left point.
     * @param width  - width of block.
     * @param height - height of block.
     * @param color  - color of block
     */
    public Block(double x, double y, int width, int height, java.awt.Color color) {
        Rectangle a = new Rectangle(x, y, width, height);
        this.shape = a;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor.
     *
     * @param rec       - the rectangle.
     * @param fill      - the color/image path of the rec.
     * @param hitNumber - number of hits.
     * @param stroke    - borders.
     * @param hash      - The HashMap.
     */
    public Block(Rectangle rec, String fill, int hitNumber, Color stroke, HashMap<Integer, String> hash) {
        this.shape = rec;
        this.fill = new FillColors(fill);
        this.fill.setFill();
        this.hitNumber = hitNumber;
        this.stroke = stroke;
        this.hash = hash;
        this.maps = new HashMapsSet(this.hash);
        this.maps.setImagesColors();
    }

    // methods
    /**
     * @return Rectangle - the "collision shape" of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Sets the number of hits.
     *
     * @param newHitNumber - the number of hits of the block.
     */
    public void setHitNumber(int newHitNumber) {
        this.hitNumber = newHitNumber;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given
     * velocity. The return is the new velocity expected after the hit (based on the
     * force the object inflicted on us).
     *
     * @param hitter          - the hitter ball.
     * @param collisionPoint  - the coliision point with the block.
     * @param currentVelocity - the currenct velocity of the ball.
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Velocity hitVelocity = new Velocity(dx, dy);
        // if the collision point is the upper left point.
        if (this.shape.getLines()[0].start().equals(collisionPoint)) {
            if (dy > 0) {
                hitVelocity.setDy(-dy);
            }
            if (dx > 0) {
                hitVelocity.setDx(-dx);
            }
            // if the collision point is the upper right point.
        } else if (this.shape.getLines()[0].end().equals(collisionPoint)) {
            if (dy > 0) {
                hitVelocity.setDy(-dy);
            }
            if (dx < 0) {
                hitVelocity.setDx(-dx);
            }
            // if the collision point is the down left point.
        } else if (this.shape.getLines()[1].start().equals(collisionPoint)) {
            if (dy < 0) {
                hitVelocity.setDy(-dy);
            }
            if (dx > 0) {
                hitVelocity.setDx(-dx);
            }
            // if the collision point is the down right point.
        } else if (this.shape.getLines()[1].end().equals(collisionPoint)) {
            if (dy < 0) {
                hitVelocity.setDy(-dy);
            }
            if (dx < 0) {
                hitVelocity.setDx(-dx);
            }
            // if the collision point is with the upper line.
        } else if (this.shape.getLines()[0].isPointInLine(collisionPoint)) {
            hitVelocity.setDy(-dy);
            // if the collision point is with the down line.
        } else if (this.shape.getLines()[1].isPointInLine(collisionPoint)) {
            hitVelocity.setDy(-dy);
            // if the collision point is with the right line.
        } else if (this.shape.getLines()[2].isPointInLine(collisionPoint)) {
            hitVelocity.setDx(-dx);
            // if the collision point is with the left line.
        } else if (this.shape.getLines()[3].isPointInLine(collisionPoint)) {
            hitVelocity.setDx(-dx);
        }
        // if the hit number is bigger than 0, lower it after this hit.
        if (hitNumber > 0) {
            hitNumber--;
        }
        // notify the hit
        this.notifyHit(hitter);
        // return the updated velocity
        return hitVelocity;
    }

    /**
     * Drawing the block.
     *
     * @param d - a draw surface variable to use while drawing.
     */
    public void drawOn(DrawSurface d) {
        // fill
        if (this.color != null) {
            // sets the color to be the color of the block.
            d.setColor(this.color);
            // fill the block
            // if the counter is not 1 or 2
            d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                    (int) this.shape.getWidth(), (int) this.shape.getHeight());
            // draw a black border to the block
            d.setColor(java.awt.Color.BLACK);
            d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                    (int) this.shape.getWidth(), (int) this.shape.getHeight());
        } else if (hash != null) {
            if (this.maps.getImages().containsKey(this.hitNumber)) {
                drawImages(d);
            } else if (this.hash.containsKey(this.hitNumber)) {
                drawColors(d);
            } else {
                drawNormal(d);
            }
        } else {
            drawNormal(d);
        }
        // borders
        if (stroke != null) {
            d.setColor(stroke);
            d.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * Drawing the block if it is by images.
     *
     * @param d - a draw surface variable to use while drawing.
     */
    public void drawImages(DrawSurface d) {
        d.drawImage((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                this.maps.getImages().get(this.hitNumber));
    }

    /**
     * Drawing the block if it is by color.
     *
     * @param d - a draw surface variable to use while drawing.
     */
    public void drawColors(DrawSurface d) {
        d.setColor(this.maps.getColors().get(this.hitNumber));
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(), (int) this.getCollisionRectangle().getWidth(),
                (int) this.getCollisionRectangle().getHeight());
    }

    /**
     * Drawing the block if it is normal(not by hit points).
     *
     * @param d - a draw surface variable to use while drawing.
     */
    public void drawNormal(DrawSurface d) {
        if (this.fill.getImage() != null) {
            d.drawImage((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                    this.fill.getImage());
        } else {
            d.setColor(this.fill.getColor());
            d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * Passed time function. Currently empty, when we will need to add moving
     * blocks, we will change this.
     */
    public void timePassed() {
    }

    /**
     * adds the block to the game.
     *
     * @param g - the game to add the block to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removes the block from the game.
     *
     * @param game - the game to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * adds a hit listener to the block.
     *
     * @param hl - the hit listener to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * removes a hit listener from the block.
     *
     * @param hl - the hit listener to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This function goes through every single listener and performs the hitEvent
     * function.
     *
     * @param hitter - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * @return int - the amount of hit points left to the block.
     */
    public int getHitPoints() {
        return this.hitNumber;
    }
}
