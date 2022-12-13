package edu.vanier.ufo.ui;

import edu.vanier.ufo.ui.hud.HeadsUpDisplay;
import edu.vanier.ufo.engine.*;
import edu.vanier.ufo.game.*;
import edu.vanier.ufo.helpers.ResourcesManager;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;
import static javafx.scene.layout.BackgroundSize.DEFAULT;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
    
    private final static int LEVELS_NUMBER = 3;
    
    private final Level[] levels;
    
    private final Ship spaceShip;
    
    private final HeadsUpDisplay hud;
    
    private boolean gameEnd;
    
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
        this.mousePositionX = 0d;
        this.mousePositionY = 0d;
        this.gameEnd = false;
        this.levels = new Level[LEVELS_NUMBER];
        this.spaceShip = new Ship(ResourcesManager.SPACE_SHIP_2);
        this.hud = new HeadsUpDisplay();
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
    }

    /**
     * Initialize the game world by adding sprite objects.
     *
     * @param primaryStage The game window or primary stage.
     */
    @Override
    public void initialize(final Stage primaryStage) {
        this.move.start();
        
        
        getSceneNodes().setBackground(
            new Background(
                new BackgroundImage(
                    new Image(ResourcesManager.RED_WATER_TILE),
                    REPEAT,
                    REPEAT,
                    CENTER,
                    DEFAULT
                )
            )
        );
        
        // Sets the window title
        primaryStage.setTitle(getWindowTitle());
        
        // Create the scene
        setGameSurface(new Scene(getSceneNodes(), 1000, 600));
        
        primaryStage.setScene(getGameSurface());

        // Setup Game input
        setupInput(primaryStage);
        
        // Hud margin
        VBox margins = new VBox(this.hud);
        VBox.setMargin(this.hud, new Insets(10d));

        // Create spheres
        generateManySpheres(5);
        
        getSpriteManager().addSprites(this.spaceShip);
        getSceneNodes().getChildren().add(0, margins);
        getSceneNodes().getChildren().add(1, this.spaceShip);
        
        // load sound files
        getSoundManager().loadSoundEffects("laser", getClass().getClassLoader().getResource(ResourcesManager.SOUND_LASER));
        getSoundManager().loadSoundEffects("explosion", getClass().getClassLoader().getResource(ResourcesManager.SOUND_EXPLOSION));
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
        if (sprite instanceof Missile missile) removeMissiles(missile);
        else if (sprite instanceof Ship) stopShipAtBounds();
        else bounceOffWalls(sprite);
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
        
        // Missile hits missile will do nothing
        if (spriteA instanceof Missile && spriteB instanceof Missile)
            return false;
        
        // Atom hits Ship -> loss of heart
        if (spriteA instanceof Atom && !(spriteA instanceof Missile) && spriteB instanceof Ship && spriteA.collide(spriteB) && !this.spaceShip.getShieldOn()) {
            handleDeath((Atom) spriteA);
            this.gameEnd = this.hud.updateLifesDisplay();
            if (this.gameEnd) {
                stopShip();
            }
        }
        
        // Missile hitting sprite
        if (spriteA instanceof Missile) {
            if (spriteA.collide(spriteB) && spriteB instanceof Atom) {
                    handleDeath((Atom)spriteA);
                    handleDeath((Atom)spriteB);
                    getSoundManager().playSound("explosion");
                    this.hud.updateScore();
            }
        }
        return false;
    }

    /**
     * Sets up the inputs.
     *
     * @param primaryStage The primary stage (app window).
     */
    private void setupInput(Stage primaryStage) {
        
        primaryStage.getScene().setOnKeyPressed((KeyEvent event) -> {
            if (!this.gameEnd) {
                checkKeyIfKeyPressed(event, true);

                // Shield
                if (event.getCode() == KeyCode.E) this.spaceShip.shieldToggle();

                // Fire
                if (event.getCode() == KeyCode.SPACE) {
                    Missile missile = this.spaceShip.fire(this.mousePositionX, this.mousePositionY);
                    getSpriteManager().addSprites(missile);

                    getSoundManager().playSound("laser");
                    getSceneNodes().getChildren().add(0, missile);
                }

                // Change weapon
                if (event.getCode() == KeyCode.C) {
                    this.spaceShip.changeWeapon();
                }
            }
            
        });

        primaryStage.getScene().setOnKeyReleased((event) -> {
            if (!this.gameEnd) checkKeyIfKeyPressed(event, false);
        });

        primaryStage.getScene().setOnMouseMoved((MouseEvent event) -> {
            if (!this.gameEnd) {
                this.mousePositionX = event.getSceneX();
                this.mousePositionY = event.getSceneY();
            }
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
        Scene gameSurface = this.getGameSurface();
        for (int i = 0; i < numSpheres; i++) {
            // Randomize the invaders images
            String invaderPath = ResourcesManager.INVADERS[rnd.nextInt(ResourcesManager.INVADERS.length - 1)];
            
            Atom atom = new Atom(invaderPath);
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

            atom.setTranslateX(newX);
            atom.setTranslateY(newY);
            atom.setVisible(true);
            atom.setId("invader");
            atomImage.setCache(true);
            atomImage.setCacheHint(CacheHint.SPEED);
            atomImage.setManaged(false);

            // add to actors in play (sprite objects)
            getSpriteManager().addSprites(atom);

            // add sprite's 
            getSceneNodes().getChildren().add(atom);
        }
    }

    /**
     * Change the direction of the moving object when it encounters the walls. 
     *
     * @param sprite The sprite to update based on the wall boundaries.
     */
    private void bounceOffWalls(Sprite sprite) {
        if (sprite != null) {
            // Get the group node's X and Y but use the ImageView to obtain the width.
            if (sprite.getTranslateX() > (getGameSurface().getWidth() - sprite.getBoundsInParent().getWidth())
                    || sprite.getTranslateX() < 0) {
                // bounce the opposite direction
                sprite.setVelocityX(sprite.getVelocityX() * -1);
            }
            // Get the group node's X and Y but use the ImageView to obtain the height.
            if (sprite.getTranslateY() > getGameSurface().getHeight() - sprite.getBoundsInParent().getHeight()
                    || sprite.getTranslateY() < 0) {
                sprite.setVelocityY(sprite.getVelocityY() * -1);
            }
        }
    }

    /**
     * Remove missiles when they reach the wall boundaries.
     *
     * @param missile The missile to remove based on the wall boundaries.
     */
    private void removeMissiles(Missile missile) {
        if (missile != null) {
            if (missile.getTranslateX() > (getGameSurface().getWidth()
                    - missile.getBoundsInParent().getWidth())
                    || missile.getTranslateX() < 0) {

                getSpriteManager().addSpritesToBeRemoved(missile);
                getSceneNodes().getChildren().remove(missile);
            }
            
            if (missile.getTranslateY() > getGameSurface().getHeight()
                    - missile.getBoundsInParent().getHeight()
                    || missile.getTranslateY() < 0) {

                getSpriteManager().addSpritesToBeRemoved(missile);
                getSceneNodes().getChildren().remove(missile);
            }
        }
    }
    
    private void stopShipAtBounds() {
        if (this.spaceShip != null) {
            if (this.spaceShip.getTranslateX() > (getGameSurface().getWidth()
                    - this.spaceShip.getBoundsInParent().getWidth()))
                this.dPressed.setValue(false);
            
            if (this.spaceShip.getTranslateX() < 0)
                this.aPressed.setValue(false);
            
            if (this.spaceShip.getTranslateY() > getGameSurface().getHeight()
                    - this.spaceShip.getBoundsInParent().getHeight())
                this.sPressed.setValue(false);
            
            if (this.spaceShip.getTranslateY() < 0)
                this.wPressed.setValue(false);
        }
    }
    
    private void handleDeath(Atom atom) {
        implode(atom);
        removeAtom(atom);
    }
    
    /**
     * Animate an implosion. Once done remove from the game world
     *
     * @param gameWorld - game world
     */
    private void implode(Atom atom) {
        atom.setVelocityX(0);
        atom.setVelocityY(0);
        
        Sprite explosion = new Atom(ResourcesManager.EXPLOSION);
        if (!(atom instanceof Missile)) {
            explosion.setTranslateX(atom.getTranslateX());
            explosion.setTranslateY(atom.getTranslateY() - atom.getImage().getHeight());
            getSceneNodes().getChildren().add(explosion);
        }
        
        FadeTransition ft = new FadeTransition(Duration.millis(700), atom);
        ft.setFromValue(35);
        ft.setToValue(0);
        ft.setOnFinished((ActionEvent event) -> {
            atom.isDead = true;
            getSceneNodes().getChildren().remove(atom);
            getSceneNodes().getChildren().remove(explosion);
        });
        ft.play();
    }
    
    private void removeAtom(Atom atom) {
        getSpriteManager().addSpritesToBeRemoved(atom);
    }
    
    private void checkKeyIfKeyPressed (KeyEvent event, boolean keyPressed) {
        if (event.getCode() == KeyCode.W) wPressed.set(keyPressed);
        if (event.getCode() == KeyCode.A) aPressed.set(keyPressed);
        if (event.getCode() == KeyCode.S) sPressed.set(keyPressed);
        if (event.getCode() == KeyCode.D) dPressed.set(keyPressed);
    }
    
    private void stopShip() {
        this.wPressed.setValue(false);
        this.aPressed.setValue(false);
        this.sPressed.setValue(false);
        this.dPressed.setValue(false);
    }
}
