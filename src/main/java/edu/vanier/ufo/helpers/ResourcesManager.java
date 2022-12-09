package edu.vanier.ufo.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * A resource manager providing useful resource definitions used in this game.
 *
 * @author Sleiman
 */
public class ResourcesManager {

    /**
     * Used to control the speed of the game.
     */
    public static final int FRAMES_PER_SECOND = 85;
    private static final String RESOURCES_FOLDER = "";
    private static final String IMAGES_FOLDER = RESOURCES_FOLDER + "images/";
    private static final String SOUNDS_FOLDER = RESOURCES_FOLDER + "sounds/";
    
    // Ship images.
    public static final String SPACE_SHIP = IMAGES_FOLDER + "spiked ship.png";
    public static final String SPACE_STAR_SHIP = IMAGES_FOLDER + "starship.png";
    public static final String SPACE_TANK = IMAGES_FOLDER + "tank.png";
    
    // Rocket images
    public static final String ROCKET_SMALL = IMAGES_FOLDER + "rocket.png";
    public static final String ROCKET_FIRE = IMAGES_FOLDER + "missile.png";
    
    // Explosion gif
    public static final String EXPLOSION = IMAGES_FOLDER + "explosion.gif";

    // Invader sprites.
    public static final String INVADER_LARGE_SHIP = IMAGES_FOLDER + "large_invader_b.png";
    public static final String INVADER_SMALL_SHIP = IMAGES_FOLDER + "small_invader_b.png";
    public static final String INVADER_UFO = IMAGES_FOLDER + "ufo.png";
    public static final String INVADER_CHICKEN = IMAGES_FOLDER + "rounded-chicken.png";
    public static final String INVADER_BEE = IMAGES_FOLDER + "small-bee.png";
    public static final String INVADER_SCI_FI = IMAGES_FOLDER + "sci-fi.png";

    // Sound effect files
    public static final String SOUND_LASER = SOUNDS_FOLDER + "laser_2.mp3";    
    //public static final String SOUND_LASER = SOUNDS_FOLDER + "alienMove2.wav";    
    
    public static final List<String> weapons = new ArrayList<>() {{
        add(ROCKET_FIRE);
        add(ROCKET_SMALL);
    }};
    
    public static final String[] INVADERS = {			
			INVADER_UFO, INVADER_CHICKEN, INVADER_BEE,INVADER_SCI_FI,
                        INVADER_SMALL_SHIP, INVADER_LARGE_SHIP
	};
    
    public static HashMap<Integer, String> getInvaderSprites() {
        HashMap<Integer, String> invaders = new HashMap<>();
        invaders.put(1, INVADER_BEE);
        invaders.put(2, INVADER_LARGE_SHIP);
        invaders.put(3, INVADER_SMALL_SHIP);
        invaders.put(4, INVADER_CHICKEN);
        invaders.put(5, INVADER_SCI_FI);
        return invaders;
    }
}
