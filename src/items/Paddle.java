package items;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
import runners.GameLevel;
import setting.Velocity;
/**
 * This paddle class is in charge of the paddle of the game.
 * @author Inbar Avital.
 */
public class Paddle implements Sprite, Collidable {
    //members
    // Screen Size
    private static final int WIDTH = 800;
    // Keyboard
    private biuoop.KeyboardSensor keyboard;
    // this is for the painting.
    private Block paddle;
    // this is for the intersections.
    private Block linePaddle;
    private Point upperLeft;
    // The speed of the paddle
    private double speed = 6;
    // The size of the paddle
    private int sizeX;
    private int sizeY;
    // The borders of the paddle
    private static final int BORDER_LEFT = 25;
    private static final int BORDER_RIGHT = 775;
    // The angles of the velocity of the
    // different regions on the paddle.
    private static final int ANGLE_1 = -60;
    private static final int ANGLE_2 = -30;
    private static final int ANGLE_4 = 30;
    private static final int ANGLE_5 = 60;
    //constructors
    /**
     * Gets the keyboard of the paddle.
     * Sets everything else as default.
     * @param keyboard - the keyboard of the paddle.
     */
    public Paddle(KeyboardSensor keyboard) {
        this.sizeX = 90;
        this.sizeY = 20;
        this.upperLeft = new Point((WIDTH - this.sizeX) / 2, 570);
        this.paddle = new Block(this.upperLeft, sizeX, sizeY);
        this.keyboard = keyboard;
        this.linePaddle = new Block(this.upperLeft, sizeX, 0);
    }
    /**
     * Gets the keyboard of the paddle.
     * Sets everything else as default.
     * @param keyboard - the keyboard of the paddle.
     * @param sizeX - the width of the paddle.
     * @param speed - the speed of the paddle.
     */
    public Paddle(KeyboardSensor keyboard, int sizeX, double speed) {
        this.upperLeft = new Point((WIDTH - sizeX) / 2, 570);
        this.speed = speed;
        this.sizeX = sizeX;
        this.sizeY = 20;
        this.paddle = new Block(this.upperLeft, sizeX, sizeY);
        this.keyboard = keyboard;
        this.linePaddle = new Block(this.upperLeft, sizeX, 0);
    }
    /**
     * This function resets the puddle's location.
     */
    public void resetLocation() {
        this.upperLeft = new Point(350, 570);
    }
    /**
     * Moves the paddle left.
     */
    public void moveLeft() {
        // if it leaves the left border, don't move.
        if (this.upperLeft.getX() - speed < BORDER_LEFT) {
            this.upperLeft = new Point(BORDER_LEFT, this.upperLeft.getY());
        // if it does not, move.
        } else {
            this.upperLeft = new Point(this.upperLeft.getX() - speed,
                    this.upperLeft.getY());
        }
        this.linePaddle = new Block(this.upperLeft, sizeX, 0);
        this.paddle = new Block(this.upperLeft, sizeX, sizeY);
    }
    /**
     * Moves the paddle right.
     */
    public void moveRight() {
        // if it leaves the right border, don't move.
        if (this.upperLeft.getX() + speed > BORDER_RIGHT
                - this.paddle.getCollisionRectangle().getWidth()) {
            this.upperLeft = new Point(BORDER_RIGHT
                - this.paddle.getCollisionRectangle().getWidth(),
                  this.upperLeft.getY());
        // if it does not
        } else {
            this.upperLeft = new Point(this.upperLeft.getX() + speed,
                    this.upperLeft.getY());
        }
        this.linePaddle = new Block(this.upperLeft, sizeX, 0);
        this.paddle = new Block(this.upperLeft, sizeX, sizeY);
    }
    /**
     * if the user clicks the right arrow, it goes to "moveRight".
     * if the user clicks the left arrow, it goes to "moveLeft".
     * otherwise, it stays.
     */
    public void timePassed() {
        // if the user click the left arrow
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        // if the user click the right arrow.
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draws the paddle.
     * @param d - the draw surface we use to draw the paddle.
     */
    public void drawOn(DrawSurface d) {
        // fill the paddle in orange.
        d.setColor(java.awt.Color.ORANGE);
        d.fillRectangle((int) this.paddle.getCollisionRectangle().
                getUpperLeft().getX(),
                (int) this.paddle.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.paddle.getCollisionRectangle().getWidth(),
                (int) this.paddle.getCollisionRectangle().getHeight());
        // draw the edges in black.
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.paddle.getCollisionRectangle().
                getUpperLeft().getX(),
                (int) this.paddle.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.paddle.getCollisionRectangle().getWidth(),
                (int) this.paddle.getCollisionRectangle().getHeight());
    }
    /**
     * @return Rectangle - returns the rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.linePaddle.getCollisionRectangle();
    }
    /**
     * This is the "hit" function. Just like any hit function,
     * it reacts to the hit with the paddle. When a ball hit the paddle,
     * it moves to the correct direction depending on where it hit it.
     * @param hitter - the hitter ball.
     * @param collisionPoint - the collision point with the paddle.
     * @param currentVelocity - the velocity of the ball before the hit.
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelo = new Velocity(20, 20);
        // Divides the width to 5 equal parts - regions.
        double onePartOfLine = this.paddle.getCollisionRectangle().
                getWidth() / 5.0;
        // Sets the speed not consider differences of X/Y.
        this.speed = (Math.sqrt((currentVelocity.getDx()
                * currentVelocity.getDx())
                + (currentVelocity.getDy()
                * currentVelocity.getDy())));
        // If the ball is inside the paddle.
        if (collisionPoint.getY() - currentVelocity.getDy() > this.linePaddle.
                getCollisionRectangle().getUpperLeft().getY()) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        // If the ball hits the left corner or the left line.
        } else if (collisionPoint.getX() == this.linePaddle.
                getCollisionRectangle().getUpperLeft().getX()) {
            return new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
        // If the ball hits the first region
        } else if (this.linePaddle.getCollisionRectangle().
                getUpperLeft().getX() + onePartOfLine
                > collisionPoint.getX()) {
            return newVelo.fromAngleAndSpeed(ANGLE_1, speed);
        // If the ball hits the second region
        } else if (this.linePaddle.getCollisionRectangle().
                getUpperLeft().getX() + 2 * onePartOfLine
                > collisionPoint.getX()) {
            return newVelo.fromAngleAndSpeed(ANGLE_2, speed);
        // If the ball hits the third region
        } else if (this.linePaddle.getCollisionRectangle().
                getUpperLeft().getX() + 3 * onePartOfLine
                > collisionPoint.getX()) {
            return new Velocity(currentVelocity.getDx(),
                    -currentVelocity.getDy());
        // If the ball hits the forth region
        } else if (this.linePaddle.getCollisionRectangle().
                getUpperLeft().getX() + 4 * onePartOfLine
                > collisionPoint.getX()) {
            return newVelo.fromAngleAndSpeed(ANGLE_4, speed);
        // If the ball hits the fifth region
        } else if (this.linePaddle.getCollisionRectangle().
                getUpperLeft().getX() + 5 * onePartOfLine
                >= collisionPoint.getX()) {
            return newVelo.fromAngleAndSpeed(ANGLE_5, speed);
        // if it hits the right corner or the right line.
        } else {
            return new Velocity(-currentVelocity.getDx(),
                    currentVelocity.getDy());
        }
    }
    /**
     * Add this paddle to the game.
     * @param g - the game to add the paddle to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Add this paddle from the game.
     * @param g - the game to add the paddle to.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
    /**
     * @return the speed of the paddle
     */
    public double getSpeed() {
        return speed;
    }
    /**
     * @return the width of the paddle
     */
    public int getWidth() {
        return sizeX;
    }
}