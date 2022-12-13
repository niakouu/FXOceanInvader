/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.hud;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author edeli
 */
public class HeadsUpDisplay extends VBox{
    
    private final ScoreDisplay scoreDisplay;
    
    private final LevelsDisplay levelDisplay;
    
    private final LifesDisaplay lifesDisplay;
    
    public HeadsUpDisplay(int level) {
        this.scoreDisplay = new ScoreDisplay();
        this.levelDisplay = new LevelsDisplay(level);
        this.lifesDisplay = new LifesDisaplay();
        this.getChildren().addAll(this.scoreDisplay, this.levelDisplay, this.lifesDisplay);
        this.setPrefWidth(200d);
        this.setPadding(new Insets(40d));
        this.setOpacity(0.8);
        this.setSpacing(10d);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10d), Insets.EMPTY)));
    }
    
    public void updateScore() {
        this.scoreDisplay.updateScore();
    }
    
    /**
     * 
     * @return true if the games end or false if it continues
     */
    public boolean updateLifesDisplay() {
        return this.lifesDisplay.updateLifesCount();
    }
}
