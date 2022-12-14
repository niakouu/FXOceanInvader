/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.hud;

import edu.vanier.ufo.helpers.ResourcesManager;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Lives displaying the images of hearts that changes based on the number of 
 * times the ship was hit. 
 * @author edeli
 */
public class LivesDisaplay extends HBox{
    
    private final static int LIFES_MAX = 3;
    
    private final ImageView heartsImage;
    
    private int lifesCount;
    
    public LivesDisaplay() {
        this.lifesCount = LIFES_MAX;
        this.heartsImage = new ImageView();
        this.setAlignment(Pos.CENTER);
        updateHeartsImage();
        this.getChildren().add(this.heartsImage);
    }
    
    /**
     * Update the 
     * @return 
     */
    public boolean updateLivesCount() {
        this.lifesCount--;
        updateHeartsImage();
        return this.lifesCount < 1;
    }
    
    /**
     * Get the right image for the heart.
     */
    private void updateHeartsImage() {
        switch (this.lifesCount) {
            case 3 -> this.heartsImage.setImage(new Image(ResourcesManager.LifesDisplay.THREE_LIFE_LEFT.toString()));
            case 2 -> this.heartsImage.setImage(new Image(ResourcesManager.LifesDisplay.TWO_LIFE_LEFT.toString()));
            case 1 -> this.heartsImage.setImage(new Image(ResourcesManager.LifesDisplay.ONE_LIFE_LEFT.toString()));
            case 0 -> this.heartsImage.setImage(new Image(ResourcesManager.LifesDisplay.ZERO_LIFE_LEFT.toString()));
            default -> throw new AssertionError();
        }
    }
}
