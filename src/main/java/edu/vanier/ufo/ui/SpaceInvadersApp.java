package edu.vanier.ufo.ui;

import edu.vanier.ufo.engine.GameEngine;
import edu.vanier.ufo.helpers.ResourcesManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * The main driver of the game.
 *
 * @author cdea
 */
public class SpaceInvadersApp extends Application {

    private GameEngine gameWorld;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.gameWorld = new GameWorld(ResourcesManager.FRAMES_PER_SECOND, "JavaFX Space Invaders");
        // Setup title, scene, stats, controls, and actors.
        this.gameWorld.initialize(primaryStage);
        // kick off the game loop
        this.gameWorld.beginGameLoop();
        // display window
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
        this.gameWorld.shutdown();
    }

}
