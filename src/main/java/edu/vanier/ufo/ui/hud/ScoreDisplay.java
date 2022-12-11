/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.hud;


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
        this.scoreLabel.setTextFill(Color.WHITE);
        this.score = -1;
        updateScore();
        this.getChildren().add(this.scoreLabel);
    }
    
    public void updateScore() {
        this.score++;
        this.scoreLabel.setText("Score: " + this.score);
    }
    
}
