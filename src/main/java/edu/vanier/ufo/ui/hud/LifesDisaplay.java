/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.hud;

import edu.vanier.ufo.helpers.ResourcesManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author edeli
 */
public class LifesDisaplay extends HBox{
    
    private final static int LIFES_MAX = 3;
    
    private final ImageView heartsImage;
    
    private int lifesCount;
    
    public LifesDisaplay() {
        this.lifesCount = LIFES_MAX;
        this.heartsImage = new ImageView();
        updateHeartsImage();
        this.getChildren().add(this.heartsImage);
    }
    
    public boolean updateLifesCount() {
        this.lifesCount--;
        updateHeartsImage();
        return this.lifesCount < 1;
    }
    
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
