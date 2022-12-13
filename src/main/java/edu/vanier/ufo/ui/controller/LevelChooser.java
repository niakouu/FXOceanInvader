/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.controller;

import edu.vanier.ufo.engine.SoundManager;
import edu.vanier.ufo.ui.GameWorld;
import edu.vanier.ufo.helpers.Level;
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
    
    private final Scene mainMenu;
    
    private final Parent initialMenuRoot;
    
    private final Runnable gameWorldEnd;
    
    private final Runnable gameWorldStart;
    
    private final SoundManager soundManager;
    
    private GameWorld gameWorld;

    public LevelChooser(Stage primaryStage) throws IOException {
        this.gameWorld = new GameWorld(ResourcesManager.FRAMES_PER_SECOND);
        this.mainMenu = new Scene(new Group(), 1000, 600);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ResourcesManager.FXML_MAIN_MENU));
        loader.setController(this);
        this.mainMenu.setRoot((Pane) loader.load()); 
        this.initialMenuRoot = this.mainMenu.getRoot();
        
        this.soundManager = new SoundManager(1);
        
        this.soundManager.loadSoundEffects("menu", getClass().getClassLoader().getResource(ResourcesManager.MUSIC_MENU));
        this.soundManager.playSound("menu");
        
        primaryStage.setScene(this.mainMenu);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        
        this.gameWorldStart = () -> {
            this.soundManager.stopSound("menu");
            this.gameWorld.initialize(primaryStage);
            this.gameWorld.beginGameLoop();
        };
        
        this.gameWorldEnd = () -> {
            this.gameWorld.shutdown();
            this.mainMenu.setRoot(this.initialMenuRoot);
            this.soundManager.playSound("menu");
        };
    }
    
    @FXML
    public void initialize() {
        
    }
    
    public void stop() {
        this.soundManager.shutdown();
        this.gameWorld.shutdown();
    }
    
    @FXML
    private void clickOnEasy() {
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                Level.LEVEL1, 
                this.gameWorldEnd);

        this.gameWorldStart.run();
    }
    
    @FXML
    private void clickOnMedium() {
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                Level.LEVEL2, 
                this.gameWorldEnd);
        this.gameWorldStart.run();
    }
    
    @FXML
    private void clickOnHard(){
        this.gameWorld = new GameWorld(
                ResourcesManager.FRAMES_PER_SECOND,
                ResourcesManager.APPLICATION_TITLE,
                Level.LEVEL3, 
                this.gameWorldEnd);
        this.gameWorldStart.run();
    }
}
