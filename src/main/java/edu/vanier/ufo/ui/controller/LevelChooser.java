/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.controller;

import edu.vanier.ufo.helpers.ResourcesManager;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author edeli
 */
public class LevelChooser{
    
    private final static int LEVELS_NUMBER = 3;
    
    private final Level[] levels;
    
    private final Scene mainMenu;
    
    private final Parent initialMenuRoot;
    
    private final Runnable gameWorldEnd;
    
    private final Runnable gameWorldStart;
    
    private GameWorld gameWorld;

    public LevelChooser(Stage primaryStage) throws IOException {
        this.gameWorld = null;
        this.mainMenu = new Scene(new Group(), 1000, 600);
        this.levels = new Level[LEVELS_NUMBER];
        addLevels();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourcesManager.FXML_MAIN_MENU));
        loader.setController(this);
        this.mainMenu.setRoot((Pane) loader.load()); 
        this.initialMenuRoot = this.mainMenu.getRoot();
        
        primaryStage.setScene(this.mainMenu);
        primaryStage.show();
        
        this.gameWorldStart = () -> {
            this.gameWorld.initialize(primaryStage);
            this.gameWorld.beginGameLoop();
        };
        
        this.gameWorldEnd = () -> {
            this.gameWorld.shutdown();
            this.mainMenu.setRoot(this.initialMenuRoot);
        };
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
    
    @FXML
    public void initialize() {
        
    }
    
    @FXML
    public void stop() {
        this.gameWorld.shutdown();
    }
    
    @FXML
    private void clickOnEasy() {
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                this.levels[0], 
                this.gameWorldEnd);

        this.gameWorldStart.run();
    }
    
    @FXML
    private void clickOnMedium() {
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                this.levels[1], 
                this.gameWorldEnd);
        this.gameWorldStart.run();
    }
    
    @FXML
    private void clickOnHard(){
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                this.levels[2], 
                this.gameWorldEnd);
        this.gameWorldStart.run();
    }
}
