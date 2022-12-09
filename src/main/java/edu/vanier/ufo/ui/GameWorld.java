package edu.vanier.ufo.ui;

import edu.vanier.ufo.helpers.ResourcesManager;
import edu.vanier.ufo.engine.*;
import edu.vanier.ufo.game.*;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * This is a simple game world simulating a bunch of spheres looking like atomic
 * particles colliding with each other. When the game loop begins the user will
 * notice random spheres (atomic particles) floating and colliding. The user
 * will navigate his/her ship by right clicking the mouse to thrust forward and
 * left click to fire weapon to atoms.
 *
 * @author cdea
 */
public class GameWorld extends GameEngine {

    // mouse pt label
    private final Label mousePtLabel;
    // mouse press pt label
    private final Label mousePressPtLabel;
    
    private final Shipp spaceShip;
    
    private double mousePositionX;
    private double mousePositionY;
    
    // Key pressed
    private final BooleanProperty wPressed;
    private final BooleanProperty aPressed;
    private final BooleanProperty sPressed;
    private final BooleanProperty dPressed;
    
    // Animations
    private final AnimationTimer move;
    
    public GameWorld(int fps, String title) {
        super(fps, title);
        this.mousePtLabel = new Label();
        this.mousePressPtLabel = new Label();
        this.spaceShip = new Shipp();
        this.wPressed = new SimpleBooleanProperty();
        this.aPressed = new SimpleBooleanProperty();
        this.sPressed = new SimpleBooleanProperty();
        this.dPressed = new SimpleBooleanProperty();
        
        this.move = new AnimationTimer() {
            @Override
            public void handle(long timestamp) {
                spaceShip.move(wPressed.get(), aPressed.get(), sPressed.get(), dPressed.get());
            }
        };
        
        this.mousePositionX = 0d;
        this.mousePositionY = 0d;
        
    }

    /**
     * Initialize the game world by adding sprite objects.
     *
     * @param primaryStage The game window or primary stage.
     */
    @Override
    public void initialize(final Stage primaryStage) {
        this.move.start();
        
        // Sets the window title
        primaryStage.setTitle(getWindowTitle());

        // Create the scene
        setSceneNodes(new Group());
        setGameSurface(new Scene(getSceneNodes(), 1000, 600));

        // Change the background of the main scene.
        getGameSurface().setFill(Color.BLACK);
        primaryStage.setScene(getGameSurface());

        // Setup Game input
        setupInput(primaryStage);

        // Create spheres
        generateManySpheres(5);

        getSpriteManager().addSprites(this.spaceShip);
        getSceneNodes().getChildren().add(0, this.spaceShip.getNode());
        
        // mouse point
        VBox stats = new VBox();
        HBox row1 = new HBox();
        this.mousePtLabel.setTextFill(Color.WHITE);
        row1.getChildren().add(this.mousePtLabel);
        HBox row2 = new HBox();
        this.mousePressPtLabel.setTextFill(Color.WHITE);
        row2.getChildren().add(this.mousePressPtLabel);
        stats.getChildren().add(row1);
        stats.getChildren().add(row2);
        
        //TODO: Add the HUD here.
        getSceneNodes().getChildren().add(0, stats);


        // load sound files
        getSoundManager().loadSoundEffects("laser", getClass().getClassLoader().getResource(ResourcesManager.SOUND_LASER));
    }

    /**
     * Sets up the mouse input.
     *
     * @param primaryStage The primary stage (app window).
     */
    private void setupInput(Stage primaryStage) {
        System.out.println("Ship's center is (" + this.spaceShip.getCenterX() + ", " + this.spaceShip.getCenterY() + ")");
        
        primaryStage.getScene().setOnKeyPressed((KeyEvent event) -> {
            checkKeyIfKeyPressed(event, true);
            if (event.getCode() == KeyCode.E) this.spaceShip.shieldToggle();
            
            if (event.getCode() == KeyCode.SPACE) {
                Missile missile = this.spaceShip.fire(this.mousePositionX, this.mousePositionY);
                getSpriteManager().addSprites(missile);

                getSoundManager().playSound("laser");
                getSceneNodes().getChildren().add(0, missile.getNode());
            }
            
            if (event.getCode() == KeyCode.C) {
                this.spaceShip.changeWeapon();
            }
        });
        
        primaryStage.getScene().setOnKeyReleased((event) -> {
            checkKeyIfKeyPressed(event, false);
        });
        
        primaryStage.getScene().setOnMouseMoved((MouseEvent event) -> {
            this.mousePtLabel.setText("Mouse PT = (" + event.getX() + ", " + event.getY() + ")");
            this.mousePositionX = event.getSceneX();
            this.mousePositionY = event.getSceneY();
        });
    }

    /**
     * Make some more space spheres (Atomic particles)
     *
     * @param numSpheres The number of random sized, color, and velocity atoms
     * to generate.
     */
    private void generateManySpheres(int numSpheres) {
        Random rnd = new Random();
        Scene gameSurface = getGameSurface();
        for (int i = 0; i < numSpheres; i++) {
            Atom atom = new Atom(ResourcesManager.INVADER_SCI_FI);
            ImageView atomImage = atom.getImageViewNode();
            
            // Randomize the location of each newly generated atom.
            atom.setVelocityX((rnd.nextInt(2) + rnd.nextDouble()) * (rnd.nextBoolean() ? 1 : -1));
            atom.setVelocityY((rnd.nextInt(2) + rnd.nextDouble()) * (rnd.nextBoolean() ? 1 : -1));

            // random x between 0 to width of scene
            double newX = rnd.nextInt((int) gameSurface.getWidth() - 100);

            if (newX > (gameSurface.getWidth() - (rnd.nextInt(15) + 5 * 2))) {
                newX = gameSurface.getWidth() - (rnd.nextInt(15) + 5 * 2);
            }

            double newY = rnd.nextInt((int) (gameSurface.getHeight() - 300));
            if (newY > (gameSurface.getHeight() - (rnd.nextInt(15) + 5 * 2))) {
                newY = gameSurface.getHeight() - (rnd.nextInt(15) + 5 * 2);
            }

            atomImage.setTranslateX(newX);
            atomImage.setTranslateY(newY);
            atomImage.setVisible(true);
            atomImage.setId("invader");
            atomImage.setCache(true);
            atomImage.setCacheHint(CacheHint.SPEED);
            atomImage.setManaged(false);

            // add to actors in play (sprite objects)
            getSpriteManager().addSprites(atom);

            // add sprite's 
            getSceneNodes().getChildren().add(atom.getNode());
        }
    }

    /**
     * Each sprite will update it's velocity and bounce off wall borders.
     *
     * @param sprite - An atomic particle (a sphere).
     */
    @Override
    protected void handleUpdate(Sprite sprite) {
        // advance object
        sprite.update();
        if (sprite instanceof Missile missile) {
            removeMissiles(missile);
        } else {
            bounceOffWalls(sprite);
        }
    }

    /**
     * Change the direction of the moving object when it encounters the walls.
     *
     * @param sprite The sprite to update based on the wall boundaries. TODO The
     * ship has got issues.
     */
    private void bounceOffWalls(Sprite sprite) {
        // bounce off the walls when outside of boundaries

        Node displayNode;
        if (sprite instanceof Shipp) {
            displayNode = sprite.getNode();
        } else {
            displayNode = sprite.getNode();
        }
        // Get the group node's X and Y but use the ImageView to obtain the width.
        if (sprite.getNode().getTranslateX() > (getGameSurface().getWidth() - displayNode.getBoundsInParent().getWidth())
                || displayNode.getTranslateX() < 0) {

            // bounce the opposite direction
            sprite.setVelocityX(sprite.getVelocityX() * -1);
        }
        // Get the group node's X and Y but use the ImageView to obtain the height.
        if (sprite.getNode().getTranslateY() > getGameSurface().getHeight() - displayNode.getBoundsInParent().getHeight()
                || sprite.getNode().getTranslateY() < 0) {
            sprite.setVelocityY(sprite.getVelocityY() * -1);
        }
    }

    /**
     * Remove missiles when they reach the wall boundaries.
     *
     * @param missile The missile to remove based on the wall boundaries.
     */
    private void removeMissiles(Missile missile) {
        // bounce off the walls when outside of boundaries
        if (missile.getNode().getTranslateX() > (getGameSurface().getWidth()
                - missile.getNode().getBoundsInParent().getWidth())
                || missile.getNode().getTranslateX() < 0) {

            getSpriteManager().addSpritesToBeRemoved(missile);
            getSceneNodes().getChildren().remove(missile.getNode());

        }
        if (missile.getNode().getTranslateY() > getGameSurface().getHeight()
                - missile.getNode().getBoundsInParent().getHeight()
                || missile.getNode().getTranslateY() < 0) {

            getSpriteManager().addSpritesToBeRemoved(missile);
            getSceneNodes().getChildren().remove(missile.getNode());
        }
    }

    /**
     * How to handle the collision of two sprite objects. Stops the particle by
     * zeroing out the velocity if a collision occurred. /** How to handle the
     * collision of two sprite objects. Stops the particle by
     *
     *
     * @param spriteA Sprite from the first list.
     * @param spriteB Sprite from the second list.
     * @return boolean returns a true if the two sprites have collided otherwise
     * false.
     */
    @Override
    protected boolean handleCollision(Sprite spriteA, Sprite spriteB) {
        //TODO: implement collision detection here.
        if (spriteA != spriteB && spriteA instanceof Missile) {
            if (spriteA.collide(spriteB) && spriteB instanceof Atom) {

                if (spriteA != spaceShip) {
                    spriteA.handleDeath(this);
                }
                if (spriteB != spaceShip) {
                    spriteB.handleDeath(this);
                }
            }
        }
        return false;
    }
    
    private void checkKeyIfKeyPressed (KeyEvent event, boolean keyPressed) {
        if (event.getCode() == KeyCode.W) wPressed.set(keyPressed);
        if (event.getCode() == KeyCode.A) aPressed.set(keyPressed);
        if (event.getCode() == KeyCode.S) sPressed.set(keyPressed);
        if (event.getCode() == KeyCode.D) dPressed.set(keyPressed);
    }
}