package edu.vanier.ufo.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Sprite manager is responsible for holding all sprite objects, and cleaning up
 * sprite objects to be removed. All collections are used by the JavaFX
 * application thread. During each cycle (animation frame) sprite management
 * occurs. This assists the user of the API to not have to create lists to later
 * be garbage collected. Should provide some performance gain.
 *
 * @author cdea
 */
public class SpriteManager {

    /**
     * All the sprite objects currently in play
     */
    private final List<Sprite> sprites;

    /**
     * A global single threaded set used to cleanup or remove sprite objects in
     * play.
     */
    private final Set<Sprite> spritesToBeRemoved;

    public SpriteManager() {
        this.sprites = new ArrayList<>();
        this.spritesToBeRemoved = new HashSet<>();
    }

    /**
     * Get the list of sprites.
     * @return a list of sprites.
     */
    public List<Sprite> getAllSprites() {
        return this.sprites;
    }

    /**
     * VarArgs of sprite objects to be added to the game.
     *
     * @param inSprites
     */
    public void addSprites(Sprite... inSprites) {        
        this.sprites.addAll(Arrays.asList(inSprites));
    }

    /**
     * VarArgs of sprite objects to be removed from the game.
     *
     * @param inSprites
     */
    public void removeSprites(Sprite... inSprites) {
        this.sprites.removeAll(Arrays.asList(inSprites));
    }

    /**
     * Adds sprite objects to be removed
     *
     * @param sprites varargs of sprite objects.
     */
    public void addSpritesToBeRemoved(Sprite... sprites) {
        if (sprites.length > 1) {
            this.spritesToBeRemoved.addAll(Arrays.asList(sprites));
        } else {
            this.spritesToBeRemoved.add(sprites[0]);
        }
    }

    /**
     * Removes sprite objects and nodes from all temporary collections such as:
     * CLEAN_UP_SPRITES. The sprite to be removed will also be removed from the
     * list of all sprite objects called (GAME_ACTORS).
     */
    public void cleanupSprites() {

        // remove from actors list
        this.sprites.removeAll(this.spritesToBeRemoved);

        // reset the clean up sprites
        this.spritesToBeRemoved.clear();
    }
}
