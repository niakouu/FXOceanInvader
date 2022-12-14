/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package edu.vanier.ufo.helpers;

/**
 *  All the levels implemented for my game.
 * @author edeli
 */
public enum Level {
    LEVEL1 (
            ResourcesManager.GREEN_WATER_TILE,
            ResourcesManager.SPACE_SHIP_1,
            1,
            7,
            0,
            0.7,
            ResourcesManager.MUSIC_LEVEL1
    ),
    LEVEL2 (
            ResourcesManager.PINK_WATER_TILE,
            ResourcesManager.SPACE_SHIP_2,
            2,
            15,
            1,
            1.0,
            ResourcesManager.MUSIC_LEVEL2
            ),
    LEVEL3 (
            ResourcesManager.RED_WATER_TILE,
            ResourcesManager.SPACE_SHIP_3,
            3,
            25,
            2,
            1.3,
            ResourcesManager.MUSIC_LEVEL3
            );

    private final String backgroundTile;
    private final String shipImagePath;
    private final int levelId;
    private final int invadersNumber;
    private final int missileId;
    private final double velocityMultiplier;
    private final String backgroundSound;

    private Level(String backgroundTile, String spaceShipPath, int levelId, int invadersNumber, int missileId, double velocityMultiplier, String backgroundSound) {
        this.backgroundTile = backgroundTile;
        this.shipImagePath = spaceShipPath;
        this.levelId = levelId;
        this.invadersNumber = invadersNumber;
        this.missileId = missileId;
        this.velocityMultiplier = velocityMultiplier;
        this.backgroundSound = backgroundSound;
    }

    public String getBackgroundTile() {
        return this.backgroundTile;
    }

    public String getShipImagePath() {
        return this.shipImagePath;
    }

    public int getLevelId() {
        return this.levelId;
    }

    public int getInvadersNumber() {
        return this.invadersNumber;
    }

    public double getVelocityMultiplier() {
        return this.velocityMultiplier;
    }

    public String getBackgroundSound() {
        return this.backgroundSound;
    }
    
    public int getMissileId() {
        return this.missileId;
    }
}
