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
public class LevelsDisplay extends VBox{
    
    private final Label levelLabel;

    public LevelsDisplay() {
        this.levelLabel = new Label();
        this.levelLabel.setTextFill(Color.WHITE);
        this.setLevel(1);
        this.getChildren().add(this.levelLabel);
    }

    public void setLevel(int level) {
        this.levelLabel.setText("Level " + level);
    }
}
