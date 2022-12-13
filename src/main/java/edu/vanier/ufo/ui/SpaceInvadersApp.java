package edu.vanier.ufo.ui;

import edu.vanier.ufo.ui.controller.LevelChooser;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * The main driver of the game.
 *
 * @author cdea
 */
public class SpaceInvadersApp extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        LevelChooser main_menu = new LevelChooser(primaryStage);
    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
    }
}
