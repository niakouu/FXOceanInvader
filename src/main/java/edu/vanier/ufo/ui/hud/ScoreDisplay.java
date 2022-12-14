/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.hud;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author edeli
 */
public class ScoreDisplay extends VBox{
    
    private final Label scoreLabel; 
    private int score;

    public ScoreDisplay() {
        this.scoreLabel = new Label();
        this.score = 0;
        
        // Set up the scoreLabel 
        this.scoreLabel.setTextFill(Color.WHITE);
        this.scoreLabel.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
        this.scoreLabel.setText("Score: " + this.score);
        
        
        this.setAlignment(Pos.CENTER);
        
        // Add to the scoreLabel to the VBox. 
        this.getChildren().add(this.scoreLabel);
    }
    
    public void updateScore() {
        this.score++;
        this.scoreLabel.setText("Score: " + this.score);
    }
    
}
