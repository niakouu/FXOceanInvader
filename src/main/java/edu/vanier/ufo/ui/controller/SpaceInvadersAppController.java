/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.controller;

import edu.vanier.ufo.helpers.ResourcesManager;
import edu.vanier.ufo.ui.GameWorld;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 *
 * @author edeli
 */
public class SpaceInvadersAppController {

    private final static int LEVELS_NUMBER = 3;
    
    private final Level[] levels;
    
    private GameWorld gameWorld;
    
    private final Stage primaryStage;

    public SpaceInvadersAppController(Stage primaryStage) {
        this.levels = new Level[LEVELS_NUMBER];
        this.primaryStage = primaryStage;
        
        addLevels();
    }
    
    @FXML
    public void initialize() {
        
    }
    
    @FXML
    public void stop() {
        Platform.exit();
        this.gameWorld.shutdown();
    }
    
    @FXML
    private void clickOnEasy() {
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                this.levels[0]);

        this.gameWorld.initialize(primaryStage);
        this.gameWorld.beginGameLoop();
        this.primaryStage.show();
    }
    
    @FXML
    private void clickOnMedium() {
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                this.levels[1]);
        this.gameWorld.initialize(primaryStage);
        this.gameWorld.beginGameLoop();
        this.primaryStage.show();
    }
    
    @FXML
    private void clickOnHard(){
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                this.levels[2]);
        this.gameWorld.initialize(primaryStage);
        this.gameWorld.beginGameLoop();
        this.primaryStage.show();
    }
    
    private void addLevels() {
        this.levels[0] = new Level(
                ResourcesManager.BLUE_WATER_TILE, 
                ResourcesManager.SPACE_SHIP_1,
                1,
                5);
        
        this.levels[1] = new Level(
                ResourcesManager.PINK_WATER_TILE, 
                ResourcesManager.SPACE_SHIP_2,
                2,
                15);
        
        this.levels[2] = new Level(
                ResourcesManager.RED_WATER_TILE,
                ResourcesManager.SPACE_SHIP_3,
                3,
                25);
    }
    
}
