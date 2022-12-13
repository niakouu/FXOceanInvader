/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.ufo.ui.controller;

/**
 *
 * @author edeli
 */
public class Level {
    
        private final String backgroundTile;
    private final String spaceShipPath;
    private final int levelId;
    private final int invadersNumber;
    private double velocityMultiplier;
    private String weapons[];
    private String sounds[];

    public Level(String backgroundTile, String spaceShipPath, int levelId, int invadersNumber) {
        this.backgroundTile = backgroundTile;
        this.spaceShipPath = spaceShipPath;
        this.levelId = levelId;
        this.invadersNumber = invadersNumber;
    }

    public String getBackgroundTile() {
        return this.backgroundTile;
    }

    public String getSpaceShipPath() {
        return this.spaceShipPath;
    }

    public int getLevelId() {
        return this.levelId;
    }

    public int getInvadersNumber() {
        return this.invadersNumber;
    }
    
}
