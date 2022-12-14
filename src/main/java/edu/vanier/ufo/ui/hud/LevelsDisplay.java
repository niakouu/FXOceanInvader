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
 * The levels display of my app
 * @author edeli
 */
public class LevelsDisplay extends VBox{
    
    private final Label levelLabel;

    public LevelsDisplay(int level) {
        this.levelLabel = new Label();
        this.levelLabel.setTextFill(Color.WHITE);
        this.setAlignment(Pos.CENTER);
        this.levelLabel.setText("Level " + level);
        this.getChildren().add(this.levelLabel);
    }
}
