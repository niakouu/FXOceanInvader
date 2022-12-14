package edu.vanier.ufo.engine;

import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;

/**
 * A class used to represent a sprite of any type on the scene.  
 */
public abstract class Sprite extends Group {
    // The JavaFX node that holds the sprite graphic.
    protected Image image;
    protected ImageView imageView;
    protected Ellipse collisionBond;
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
        
        this.collisionBond = new Ellipse(this.image.getWidth() / 2, this.image.getHeight() / 2);
        this.collisionBond.setTranslateX(this.imageView.getTranslateX() + this.image.getWidth() / 2);
        this.collisionBond.setTranslateY(this.imageView.getTranslateY() + this.image.getHeight() / 2);
        this.collisionBond.setOpacity(0d);
        
        setUpGrid();
        this.getChildren().addAll(this.imageView, this.collisionBond);

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
    
    protected final void setupImage() {
        this.imageView.setCache(true);
        this.imageView.setCacheHint(CacheHint.SPEED);
        this.imageView.setManaged(false);
        this.imageView.setVisible(true);
    }
    
    protected final void setUpGrid() {
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
        return this.collisionBond.getBoundsInLocal().intersects(this.collisionBond.sceneToLocal(other.collisionBond.localToScene(other.collisionBond.getBoundsInLocal())));
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
}
