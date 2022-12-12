package edu.vanier.ufo.helpers;

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
    private static final String IMAGES_FOLDER = RESOURCES_FOLDER + "images/found/";
    private static final String SOUNDS_FOLDER = RESOURCES_FOLDER + "sounds/";
    
    // Background fxml tiles
    public static final String COLD_WATER = IMAGES_FOLDER + "coldwater.gif";
    public static final String COLD_WATER_DEEP = IMAGES_FOLDER + "coldwaterdeepwater.gif";
    public static final String COLD_WATER_ICE = IMAGES_FOLDER + "coldwaterice.gif";
    public static final String OCEAN_WATER = IMAGES_FOLDER + "ocean_tile.gif";
    
    // Ship images
    public static final String SPACE_SHIP_1 = IMAGES_FOLDER + "ship_level1.png";
    public static final String SPACE_SHIP_2 = IMAGES_FOLDER + "ship_level2.png";
    public static final String SPACE_SHIP_3 = IMAGES_FOLDER + "ship_level3.png";
    
    // Rocket images
    public static final String MISSILE_RED = IMAGES_FOLDER + "missile_red.png";
    public static final String MISSILE_THREE_COLOR = IMAGES_FOLDER + "missile_three_color.png";
    
    // Explosion gif
    public static final String EXPLOSION = IMAGES_FOLDER + "ketchup_splash.gif";

    // Invader sprites.
    public static final String INVADER_DRAGONFISH = IMAGES_FOLDER + "invader_dragonfish.gif";
    public static final String INVADER_HATCHEFISH = IMAGES_FOLDER + "invader_hatchefish.gif";
    public static final String INVADER_JELLYFISH = IMAGES_FOLDER + "invader_jellyfish.gif";
    public static final String INVADER_SHARK = IMAGES_FOLDER + "invader_shark.png";
    public static final String INVADER_SHARK_MOVING = IMAGES_FOLDER + "invader_shark_moving.gif";
    public static final String INVADER_VAPERFISH = IMAGES_FOLDER + "invader_vaperfish.png";

    // Sound effect files
    public static final String SOUND_LASER = SOUNDS_FOLDER + "laser_2.mp3";    
    public static final String SOUND_EXPLOSION = SOUNDS_FOLDER + "explosion.wav";
    
    // Hearts
    public enum LifesDisplay {
        ZERO_LIFE_LEFT {
            @Override
            public String toString() {
                return IMAGES_FOLDER + "heartbar/HUD/0.png";
            }
        },
        ONE_LIFE_LEFT {
            @Override
            public String toString() {
                return IMAGES_FOLDER + "heartbar/HUD/1.png";
            }
        },
        TWO_LIFE_LEFT {
            @Override
            public String toString() {
                return IMAGES_FOLDER + "heartbar/HUD/2.png";
            }
        },
        THREE_LIFE_LEFT {
            @Override
            public String toString() {
                return IMAGES_FOLDER + "heartbar/HUD/3.png";
            }
        };
    }
    
    public static final String[] weapons =  {
        MISSILE_RED,
        MISSILE_THREE_COLOR
    };
    
    public static final String[] INVADERS = {			
	INVADER_DRAGONFISH,
        INVADER_HATCHEFISH,
        INVADER_JELLYFISH,
        INVADER_SHARK,
        INVADER_SHARK_MOVING,
        INVADER_VAPERFISH
    };
    
}
