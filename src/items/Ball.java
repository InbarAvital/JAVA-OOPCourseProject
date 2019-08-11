package items;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import interfaces.Sprite;
import runners.GameLevel;
import setting.CollisionInfo;
import setting.GameEnvironment;
import setting.Velocity;
/**
 * This class represent a ball object.
 * @author Inbar Avital
 */
public class Ball implements Sprite {
   // members
   // the size of the ball's radius
   private int size;
   // the center point of the ball
   private Point center;
   // the color of the ball
   private java.awt.Color color;
   // the velocity of the ball
   private Velocity velo;
   // the game environment of the ball
   private GameEnvironment game;
   /**
    * constructor #1.
    * @param center - a center point for the ball
    * @param r - the size of the ball's radius
    * @param color - the color of the ball
    */
   public Ball(Point center, int r, java.awt.Color color) {
       // sets the object's variables
       this.center = center;
       this.size = r;
       this.color = color;
   }
   /**
    * constructor #2.
    * @param x - x value of the center point
    * @param y - y value of the center point
    * @param r - the size of the ball's radius
    * @param color - the color of the ball
    */
   public Ball(int x, int y, int r, java.awt.Color color) {
       // sets the object's variables
       Point newCenter = new Point(x, y);
       this.center = newCenter;
       this.size = r;
       this.color = color;
   }
   // accessors
   /**
    * @return int - the x value of the center point
    */
   public int getX() {
       return (int) this.center.getX();
   }
   /**
    * @return int - the y value of the center point
    */
   public int getY() {
       return (int) this.center.getY();
   }
   /**
    * @return int - the size of the point's radius
    */
   public int getSize() {
       return this.size;
   }
   /**
    * @return java.awt.Color - the color of the ball
    */
   public java.awt.Color getColor() {
       return this.color;
   }
   /**
    * draw the ball on the given DrawSurface.
    * @param surface - the surface of the ball
    */
   public void drawOn(DrawSurface surface) {
       // filling the circle and painting it
       surface.setColor(color);
       surface.fillCircle((int) center.getX(), (int) center.getY(), size);
       surface.setColor(java.awt.Color.BLACK);
       surface.drawCircle((int) center.getX(), (int) center.getY(), size);
       surface.setColor(java.awt.Color.RED);
       surface.drawCircle((int) center.getX(), (int) center.getY(), 1);
   }
   /**
    * set the velocity #1.
    * @param v - the velocity to set
    */
   public void setVelocity(Velocity v) {
       this.velo = v;
   }
   /**
    * set the velocity #2.
    * @param dx - the x value of the velocity(speed)
    * @param dy - the y value of the velocity(speed)
    */
   public void setVelocity(double dx, double dy) {
       this.velo = new Velocity(dx, dy);
   }
   /**
    * @return Velocity - the velocity of the ball(speed set)
    */
   public Velocity getVelocity() {
       return this.velo;
   }
   /**
    * moves the ball one step.
    */
   public void moveOneStep() {
       Line movement = new Line(this.center.getX(),
               this.center.getY(),
               this.center.getX() + this.velo.getDx(),
               this.center.getY() + this.velo.getDy());
       // sets the center point to be the updated
       // point after one step.
       // if the ball collides with a block
       if (game.getClosestCollision(movement) != null) {
           // set the collision point information
           CollisionInfo collision = this.game.getClosestCollision(movement);
           // sets the new center after the step
           if (this.velo.getDx() == 0) {
               this.center = new Point(collision.collisionPoint().getX(),
                       collision.collisionPoint().getY()
                       - 2 * (this.velo.getDy() / (Math.abs(this.velo.getDy()))));
           } else if (this.velo.getDy() == 0) {
               this.center = new Point(collision.collisionPoint().getX()
                       - 2 * (this.velo.getDx() / (Math.abs(this.velo.getDx()))),
                       collision.collisionPoint().getY());
           } else {
               this.center = new Point(collision.collisionPoint().getX()
                       - 2 * (this.velo.getDx() / (Math.abs(this.velo.getDx()))),
                       collision.collisionPoint().getY()
                       - 2 * (this.velo.getDy() / (Math.abs(this.velo.getDy()))));
           }
           // sets the updated velocity "after" the collision.
           this.velo = collision.collisionObject().
                   hit(this, collision.collisionPoint(), this.velo);
       // if the ball does not collide with a block
       } else {
           this.center = this.velo.applyToPoint(this.center);
       }
   }
   /**
    * Gets a game and sets it as this current ball's game.
    * @param newGame - the game to set as the ball's game.
    */
   public void setGame(GameEnvironment newGame) {
       this.game = newGame;
   }
   /**
    * removes the ball from the game.
    * @param gameA - the game to remove the ball from.
    */
   public void removeFromGame(GameLevel gameA) {
       gameA.removeSprite(this);
   }
   /**
    * gets the game of the current ball.
    * @return GameEnvironment
    */
   public GameEnvironment getGame() {
       return this.game;
   }
   /**
    * Moves one step.
    */
   public void timePassed() {
       moveOneStep();
   }
   /**
    * @param g - Adds the ball to the game "g".
    */
   public void addToGame(GameLevel g) {
       g.addSprite(this);
   }
}