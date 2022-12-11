/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui;

import javafx.scene.control.Label;

/**
 *
 * @author edeli
 */
public class HeadsUpDisplay {
    
    private final Label score;
    private final Label level;
    private final Label livesCounter;

    public HeadsUpDisplay() {
        this.score = new Label();
        this.level = new Label();
        this.livesCounter = new Label();
    }
    
    
}
