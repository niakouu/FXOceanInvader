/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.hud;

import javafx.scene.layout.VBox;

/**
 *
 * @author edeli
 */
public class HeadsUpDisplay extends VBox{
    
    private final ScoreDisplay scoreDisplay;
    
    private final LevelsDisplay levelDisplay;
    
    private final LifesDisaplay lifesDisplay;

    public HeadsUpDisplay() {
        this.scoreDisplay = new ScoreDisplay();
        this.levelDisplay = new LevelsDisplay();
        this.lifesDisplay = new LifesDisaplay();
        this.getChildren().addAll(this.scoreDisplay, this.levelDisplay, this.lifesDisplay);
    }
    
    public void updateScore() {
        this.scoreDisplay.updateScore();
    }
    
    public void updateLevel(int level) {
        this.levelDisplay.setLevel(level);
    }
    
    /**
     * 
     * @return true if the games end or false if it continues
     */
    public boolean updateLifesDisplay() {
        return this.lifesDisplay.updateLifesCount();
    }
    
}
