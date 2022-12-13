package edu.vanier.ufo.engine;

import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * A class used to represent a sprite of any type on the scene.  
 */
public abstract class Sprite extends Group {
    // The JavaFX node that holds the sprite graphic.
    protected Image image;
    protected ImageView imageView;
    protected Ellipse collsionBond;
    protected double centerX;
    protected double centerY;
    protected double vX;
    protected double vY;
    public boolean isDead;

    public Sprite(String imagePath) {
        this.vX = 0d;
        this.vY = 0d;
        this.isDead = false;
        
        this.image = new Image(imagePath, false);
        this.imageView = new ImageView(this.image);
        setupImage();
        
        this.collsionBond = new Ellipse(this.image.getWidth() / 2, this.image.getHeight() / 2);
        this.collsionBond.setTranslateX(this.imageView.getTranslateX() + this.image.getWidth() / 2);
        this.collsionBond.setTranslateY(this.imageView.getTranslateY() + this.image.getHeight() / 2);
        this.collsionBond.setOpacity(0d);
        
        setUpGrid();
        this.getChildren().addAll(this.imageView, this.collsionBond);

    }
    
    public abstract void update();

    public void setVelocity(double x, double y) {
        this.vX = x;
        this.vY = y;
    }

    public void addVelocity(double x, double y) {
        this.vX += x;
        this.vY += y;
    }
    
    protected void setupImage() {
        this.imageView.setCache(true);
        this.imageView.setCacheHint(CacheHint.SPEED);
        this.imageView.setManaged(false);
        this.imageView.setVisible(true);
    }
    
    protected void setUpGrid() {
        this.setTranslateX(350);
        this.setTranslateY(450);
        this.setCache(true);
        this.setCacheHint(CacheHint.SPEED);
        this.setManaged(false);
        this.setAutoSizeChildren(false);
    }
    
    /**
     * Did this sprite collide into the other sprite?
     *
     * @param other - The other sprite.
     * @return boolean - Whether this or the other sprite collided, otherwise
     * false.
     */
    public boolean collide(Sprite other) {
        return this.collsionBond.getBoundsInLocal().intersects(this.collsionBond.sceneToLocal(other.collsionBond.localToScene(other.collsionBond.getBoundsInLocal())));
    }
    
    /**
     * The center X coordinate of the current visible image. See
     * <code>getCurrentShipImage()</code> method.
     *
     * @return The scene or screen X coordinate.
     */
    public double getCenterX() {
        return this.getTranslateX() + (this.imageView.getBoundsInLocal().getWidth() / 2);
    }

    /**
     * The center Y coordinate of the current visible image. See
     * <code>getCurrentShipImage()</code> method.
     *
     * @return The scene or screen Y coordinate.
     */
    public double getCenterY() {
        return this.getTranslateY() + (this.imageView.getBoundsInLocal().getHeight() / 2);
    }

    public Image getImage() {
        return this.image;
    }

    public double getVelocityX() {
        return this.vX;
    }

    public void setVelocityX(double velocityX) {
        this.vX = velocityX;
    }

    public double getVelocityY() {
        return vY;
    }

    public void setVelocityY(double velocityY) {
        this.vY = velocityY;
    }
    
    public Shape getCollisionBounds() {
        return this.collsionBond;
    }

    public void setCollisionBounds(Ellipse collisionBounds) {
        this.collsionBond = collisionBounds;
    }
}
