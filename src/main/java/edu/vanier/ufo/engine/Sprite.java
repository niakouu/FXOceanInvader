package edu.vanier.ufo.engine;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

/**
 * A class used to represent a sprite of any type on the scene.  
 */
public abstract class Sprite {
    // The JavaFX node that holds the sprite graphic.
    protected Node node;
    private Image image;
    protected ImageView imageView;
    protected double centerX;
    protected double centerY;
    protected double vX;
    protected double vY;
    private double width;
    private double height;
    public boolean isDead;

    protected Node collidingNode;

    public Sprite() {
        this.vX = 0d;
        this.vY = 0d;
        this.isDead = false;
    }
    
    public abstract void update();

    public void setImage(Image inImage) {
        this.image = inImage;
        this.width = inImage.getWidth();
        this.height = inImage.getHeight();
        this.imageView = new ImageView(this.image);
    }

    public void setImage(String filename) {
        setImage(new Image(filename));
    }

    public void setVelocity(double x, double y) {
        this.vX = x;
        this.vY = y;
    }

    public void addVelocity(double x, double y) {
        this.vX += x;
        this.vY += y;
    }

    /**
     * Did this sprite collide into the other sprite?
     *
     * @param other - The other sprite.
     * @return boolean - Whether this or the other sprite collided, otherwise
     * false.
     */
    public boolean collide(Sprite other) {
        return this.collidingNode.getBoundsInParent().intersects(other.node.getBoundsInParent());
    }

    public boolean intersects(Sprite s) {       
        Bounds sBounds = s.getNode().localToScene(s.getNode().getBoundsInLocal());
        return this.node.intersects(sBounds);
    }
    
    /**
     * The center X coordinate of the current visible image. See
     * <code>getCurrentShipImage()</code> method.
     *
     * @return The scene or screen X coordinate.
     */
    public double getCenterX() {
        return getNode().getTranslateX() + (this.imageView.getBoundsInLocal().getWidth() / 2);
    }

    /**
     * The center Y coordinate of the current visible image. See
     * <code>getCurrentShipImage()</code> method.
     *
     * @return The scene or screen Y coordinate.
     */
    public double getCenterY() {
        return getNode().getTranslateY() + (this.imageView.getBoundsInLocal().getHeight() / 2);
    }
    
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }
    
    public void setCenterY(double centerY) {
        this.centerY = centerY;
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

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Node getNode() {
        return this.node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getCollisionBounds() {
        return this.collidingNode;
    }

    public void setCollisionBounds(Node collisionBounds) {
        this.collidingNode = collisionBounds;
    }

    public void handleDeath(GameEngine gameWorld) {
        gameWorld.getSpriteManager().addSpritesToBeRemoved(this);
    }
}
