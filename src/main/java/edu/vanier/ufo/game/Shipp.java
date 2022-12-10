package edu.vanier.ufo.game;

import edu.vanier.ufo.helpers.ResourcesManager;
import edu.vanier.ufo.engine.Sprite;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * A spaceship with 32 directions When two atoms collide each will fade and
 * become removed from the scene. The method called implode() implements a fade
 * transition effect.
 *
 * @author cdea
 */
public class Shipp extends Sprite {
    
    private final static double CONSTANT_VELOCITY = 3d;

    private boolean shieldOn;

    private final Circle stopArea;
    
    private final Circle shield;

    private final Circle hitBounds;
    
    private final Group flipBook;
    
    private String rocketName;
    
    private final FadeTransition shieldFade;
    
    private int rocketNameIterationCounter;

    public Shipp() {
        loadImage();
        
        this.rocketNameIterationCounter = 0;
        this.rocketName = ResourcesManager.ROCKET_FIRE;
        
        this.flipBook = getFlipBook();
        setNode(this.flipBook);
        
        this.stopArea = getStopArea();
        this.hitBounds = getHitBounds();
        this.shield = getShield();
        this.shieldFade = getShieldFadeTransition();
        this.flipBook.getChildren().add(this.hitBounds);
    }

    /**
     * Change the velocity of the atom particle.
     */
    @Override
    public void update() {
        this.flipBook.setTranslateX(this.flipBook.getTranslateX() + this.vX);
        this.flipBook.setTranslateY(this.flipBook.getTranslateY() + vY);

        if (this.stopArea.contains(getCenterX(), getCenterY())) {
            this.vX = 0;
            this.vY = 0;
        }
    }
    
    /**
     * Update the velocity of atom particles.
     * 
     * @param wPressed
     * @param aPressed
     * @param sPressed
     * @param dPressed 
     */
    public void move(boolean wPressed, boolean aPressed, boolean sPressed, boolean dPressed) {
        if (wPressed) this.vY = -CONSTANT_VELOCITY;
        if (sPressed) this.vY = CONSTANT_VELOCITY;
        if (!sPressed && !wPressed) this.vY = 0;
        if (aPressed) this.vX = -CONSTANT_VELOCITY;
        if (dPressed) this.vX = CONSTANT_VELOCITY;
        if (!aPressed && !dPressed) this.vX = 0;
        if (this.vX == 0 && this.vY == 0) return;
        
        double angle = Math.atan2(this.vY, this.vX);
        this.vX = Math.cos(angle) * CONSTANT_VELOCITY;
        this.vY = Math.sin(angle) * CONSTANT_VELOCITY;
        
        this.imageView.rotateProperty().setValue(Math.toDegrees(angle));
    }
  
    public Missile fire(double mousePositionX, double mousePositionY) {
        Missile fireMissile;
        
        Point2D centerScene = this.getNode().localToScene(
            this.hitBounds.getCenterX(),
            this.hitBounds.getCenterY()
        );
        
        double angleShip = Math.atan2(mousePositionY - centerScene.getY(), mousePositionX - centerScene.getX());
        this.imageView.rotateProperty().setValue(Math.toDegrees(angleShip));
        
        fireMissile = getMissle(this.rocketName, angleShip);
        fireMissile.getImageViewNode().rotateProperty().setValue(Math.toDegrees(angleShip) + 90);
        
        return fireMissile;
    }

    public void shieldToggle() {
        double x = this.imageView.getBoundsInLocal().getWidth() / 2;
        double y = this.imageView.getBoundsInLocal().getHeight() / 2;

        this.shield.setCenterX(x);
        this.shield.setCenterY(y);
        setCollisionBounds(this.shield);
        
        this.shieldFade.playFromStart();

        this.shieldOn = !shieldOn;
        
        if (this.shieldOn) {
            setCollisionBounds(this.shield);
            this.flipBook.getChildren().add(0, this.shield);
            this.shieldFade.playFromStart();
        } else {
            this.flipBook.getChildren().remove(this.shield);
            this.shieldFade.stop();
            setCollisionBounds(this.hitBounds);
        }
    }
    
    public void changeWeapon() {
        List<String> weapons = ResourcesManager.weapons;
        if(++this.rocketNameIterationCounter < weapons.size()) this.rocketName = weapons.get(this.rocketNameIterationCounter);
        else {
            this.rocketNameIterationCounter = 0;
            this.rocketName = weapons.get(this.rocketNameIterationCounter);
        }
    }
    
    private FadeTransition getShieldFadeTransition() {
        FadeTransition shieldTransition = new FadeTransition();
        shieldTransition.setFromValue(1);
        shieldTransition.setToValue(.40);
        shieldTransition.setDuration(Duration.millis(1000));
        shieldTransition.setCycleCount(12);
        shieldTransition.setAutoReverse(true);
        shieldTransition.setNode(this.shield);
        shieldTransition.setOnFinished((ActionEvent actionEvent) -> {
            this.shieldOn = false;
            this.flipBook.getChildren().remove(this.shield);
            shieldTransition.stop();
            setCollisionBounds(this.hitBounds);
        });
        return shieldTransition;
    }
    
    private Circle getShield() {
        Circle newShield = new Circle();
        newShield.setRadius(60);
        newShield.setStrokeWidth(5);
        newShield.setStroke(Color.LIMEGREEN);
        newShield.setOpacity(.70);
        return newShield;
    }
    
    private Group getFlipBook() {
        Group group = new Group();
        group.getChildren().add(this.imageView);
        group.setTranslateX(350);
        group.setTranslateY(450);
        group.setCache(true);
        group.setCacheHint(CacheHint.SPEED);
        group.setManaged(false);
        group.setAutoSizeChildren(false);
        return group;
    }
    
    private void loadImage() {
        setImage(new Image(ResourcesManager.SPACE_STAR_SHIP, false));
        this.imageView.setCache(true);
        this.imageView.setCacheHint(CacheHint.SPEED);
        this.imageView.setManaged(false);
        this.imageView.setVisible(true);
    }
    
    private Circle getHitBounds() {
        Circle hitbound = new Circle();
        double imageHeight = this.imageView.getImage().getHeight();
        double imageWidth = this.imageView.getImage().getWidth();
        hitbound.setStroke(Color.PINK);
        hitbound.setFill(Color.RED);
        hitbound.setRadius(imageHeight > imageWidth ? imageHeight/2 : imageWidth/2);
        hitbound.setOpacity(0);
        hitbound.setCenterX(imageHeight/2);
        hitbound.setCenterY(imageWidth/2);
        setCollisionBounds(this.hitBounds);
        return hitbound;
    }
    
    private Circle getStopArea() {
        Circle c = new Circle();
        c.setRadius(40);
        c.setStroke(Color.ORANGE);
        return c;
    }
    
    private Missile getMissle(String filepath, double angle) {
        Missile fireMissile = new Missile(filepath);
        double offsetX = (this.imageView.getBoundsInLocal().getWidth() - fireMissile.getNode().getBoundsInLocal().getWidth()) / 2;
        double offsetY = (this.imageView.getBoundsInLocal().getHeight() - fireMissile.getNode().getBoundsInLocal().getHeight()) / 2;
        
        fireMissile.setVelocityX(Math.cos(angle) * CONSTANT_VELOCITY);
        fireMissile.setVelocityY(Math.sin(angle) * CONSTANT_VELOCITY);
        fireMissile.getNode().setTranslateX(getNode().getTranslateX() + (offsetX + fireMissile.getVelocityX()));
        fireMissile.getNode().setTranslateY(getNode().getTranslateY() + (offsetY + fireMissile.getVelocityY()));
        
        return fireMissile;
    }
}