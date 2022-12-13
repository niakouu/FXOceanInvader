package edu.vanier.ufo.engine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This application demonstrates a JavaFX Game Loop. Shown below are the methods
 * which comprise of the fundamentals to a simple game loop in JavaFX:
 * <pre>
 *  <b>initialize()</b> - Initialize the game world.
 *  <b>beginGameLoop()</b> - Creates a JavaFX Timeline object containing the game life cycle.
 *  <b>updateSprites()</b> - Updates the sprite objects each period (per frame)
 *  <b>checkCollisions()</b> - Method will determine objects that collide with each other.
 *  <b>cleanupSprites()</b> - Any sprite objects needing to be removed from play.
 * </pre>
 *
 * @author cdea
 */
public abstract class GameEngine extends Pane{
    
    private final Pane sceneNodes;
    
    private static Timeline gameLoop;
    
    private final int framesPerSecond;

    private final String windowTitle;

    private final SpriteManager spriteManager;
    
    private final SoundManager soundManager;

    /**
     * Constructor that is called by the derived class. This will set the frames
     * per second, title, and setup the game loop.
     *
     * @param fps - Frames per second.
     * @param title - Title of the application window.
     */
    public GameEngine(final int fps, final String title) {
        this.framesPerSecond = fps;
        this.windowTitle = title;
        this.spriteManager = new SpriteManager();
        this.soundManager = new SoundManager(3);
        this.sceneNodes = new Pane();
        
        // create and set timeline for the game loop
        buildAndSetGameLoop();
    }
    
    /**
     * Initialize the game world by update the JavaFX Stage.
     *
     * @param primaryStage The main window containing the JavaFX Scene.
     */
    public abstract void initialize(final Stage primaryStage);
    
    /**
     * Updates the sprite object's information to position on the game surface.
     *
     * @param sprite - The sprite to update.
     */
    protected abstract void handleUpdate(Sprite sprite);
    
    /**
     * When two objects collide this method can handle the passed in sprite
     * objects. By default it returns false, meaning the objects do not collide.
     *
     * @param spriteA - called from checkCollision() method to be compared.
     * @param spriteB - called from checkCollision() method to be compared.
     * @return boolean True if the objects collided, otherwise false.
     */
    protected abstract boolean handleCollision(Sprite spriteA, Sprite spriteB);


    /**
     * Builds and sets the game loop ready to be started.
     */
    protected final void buildAndSetGameLoop() {

        final Duration frameDuration = Duration.millis(1000 / (float) getFramesPerSecond());
        EventHandler<ActionEvent> onFinished = (event) -> {
            // update actors
            updateSprites();
            // check for collision.
            checkCollisions();
            // removed dead sprites.
            cleanupSprites();
        };
        final KeyFrame gameFrame = new KeyFrame(frameDuration, onFinished);
        // sets the game world's game loop (Timeline)
        Timeline gameEngineLoop = new Timeline(gameFrame);
        gameEngineLoop.setCycleCount(Animation.INDEFINITE);
        setGameLoop(gameEngineLoop);
    }

    /**
     * Kicks off (plays) the Timeline objects containing one key frame that
     * simply runs indefinitely with each frame invoking a method to update
     * sprite objects, check for collisions, and cleanup sprite objects.
     */
    public void beginGameLoop() {
        getGameLoop().play();
    }

    /**
     * Updates each game sprite in the game world. This method will loop through
     * each sprite and passing it to the handleUpdate() method. The derived
     * class should override handleUpdate() method.
     */
    protected void updateSprites() {
        for (Sprite sprite : spriteManager.getAllSprites()) {
            handleUpdate(sprite);
        }
    }

    /**
     * Checks each game sprite in the game world to determine a collision
     * occurred. The method will loop through each sprite and passing it to the
     * handleCollision() method. The derived class should override
     * handleCollision() method.
     */
    protected void checkCollisions() {
        // check other sprite's collisions
        spriteManager.getAllSprites();
        // check each sprite against other sprite objects.
        for (Sprite spriteA : spriteManager.getAllSprites()) {
            for (Sprite spriteB : spriteManager.getAllSprites()) {
                if (handleCollision(spriteA, spriteB)) {
                    // The break statement means one object only hits another
                    // object as opposed to one hitting many objects
                    break;
                }
            }
        }
    }
    
    /**
     * Sprites to be cleaned up.
     */
    protected void cleanupSprites() {
        this.spriteManager.cleanupSprites();
    }

    /**
     * Returns the frames per second.
     *
     * @return int The frames per second.
     */
    protected int getFramesPerSecond() {
        return this.framesPerSecond;
    }

    /**
     * Returns the game's window title.
     *
     * @return String The game's window title.
     */
    public String getWindowTitle() {
        return this.windowTitle;
    }

    /**
     * The game loop (Timeline) which is used to update, check collisions, and
     * cleanup sprite objects at every interval (fps).
     *
     * @return Timeline An animation running indefinitely representing the game
     * loop.
     */
    protected static Timeline getGameLoop() {
        return GameEngine.gameLoop;
    }

    /**
     * The sets the current game loop for this game world.
     *
     * @param gameLoop Timeline object of an animation running indefinitely
     * representing the game loop.
     */
    protected static void setGameLoop(Timeline gameLoop) {
        GameEngine.gameLoop = gameLoop;
    }

    /**
     * Returns the sprite manager containing the sprite objects to manipulate in
     * the game.
     *
     * @return SpriteManager The sprite manager.
     */
    public SpriteManager getSpriteManager() {
        return this.spriteManager;
    }

    /**
     * All JavaFX nodes which are rendered onto the game surface(Scene) is a
     * JavaFX Group object.
     *
     * @return Group The root containing many child nodes to be displayed into
     * the Scene area.
     */
    public Pane getSceneNodes() {
        return this.sceneNodes;
    }

    protected SoundManager getSoundManager() {
        return this.soundManager;
    }

    /**
     * Stop threads and stop media from playing.
     */
    public void shutdown() {
        // Stop the game's animation.
        getGameLoop().stop();
        getSoundManager().shutdown();
    }
}
