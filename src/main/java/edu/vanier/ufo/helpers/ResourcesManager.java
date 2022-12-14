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
    public static final String APPLICATION_TITLE = "JavaFX Space Invaders";
    private static final String RESOURCES_FOLDER = "";
    private static final String IMAGES_FOLDER = RESOURCES_FOLDER + "images/";
    private static final String SOUNDS_FOLDER = RESOURCES_FOLDER + "sounds/";
    private static final String FXML_FOLDER = "/fxml/";
    
    // Background fxml tiles
    public static final String GREEN_WATER_TILE = IMAGES_FOLDER + "ocean_tile.gif";
    public static final String RED_WATER_TILE = IMAGES_FOLDER + "ocean_tile_red.gif";
    public static final String PINK_WATER_TILE = IMAGES_FOLDER + "ocean_tile_pink.gif";
    
    // Fxml files
    public static final String FXML_MAIN_MENU = FXML_FOLDER + "main_menu.fxml";
    
    // Ship images
    public static final String SPACE_SHIP_1 = IMAGES_FOLDER + "ship_level1.png";
    public static final String SPACE_SHIP_2 = IMAGES_FOLDER + "ship_level2.png";
    public static final String SPACE_SHIP_3 = IMAGES_FOLDER + "ship_level3.png";
    
    // Shield images
    public static final String SHIELD = IMAGES_FOLDER + "shield.png";
    
    // Rocket images
    public static final String MISSILE_RED = IMAGES_FOLDER + "missile_red.png";
    public static final String MISSILE_THREE_COLOR = IMAGES_FOLDER + "missile_three_color.png";
    public static final String ROCKET = IMAGES_FOLDER + "rocket.png";
    
    // Explosion gif
    public static final String EXPLOSION = IMAGES_FOLDER + "ketchup_splash.gif";

    // Invader sprites.
    public static final String INVADER_DRAGONFISH = IMAGES_FOLDER + "invader_dragonfish.gif";
    public static final String INVADER_HATCHEFISH = IMAGES_FOLDER + "invader_hatchetfish.gif";
    public static final String INVADER_JELLYFISH = IMAGES_FOLDER + "invader_jellyfish.gif";
    public static final String INVADER_SHARK = IMAGES_FOLDER + "invader_shark.png";
    public static final String INVADER_SHARK_MOVING = IMAGES_FOLDER + "invader_shark_moving.gif";
    public static final String INVADER_VAPERFISH = IMAGES_FOLDER + "invader_vaperfish.gif";
    public static final String INVADER_MONSTER_SEA_HORSE = IMAGES_FOLDER + "monster_seahorse.gif";

    // Sound effect files
    public static final String SOUND_LASER = SOUNDS_FOLDER + "laser.wav";    
    public static final String SOUND_EXPLOSION = SOUNDS_FOLDER + "explosion.wav";
    public static final String MUSIC_LEVEL1 = SOUNDS_FOLDER + "level_1.mp3";
    public static final String MUSIC_LEVEL2 = SOUNDS_FOLDER + "level_2.wav";
    public static final String MUSIC_LEVEL3 = SOUNDS_FOLDER + "level_3.mp3";
    public static final String MUSIC_MENU = SOUNDS_FOLDER + "menu.mp3";
    
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
    
    public static final String[] WEAPONS_1 =  {
        MISSILE_RED,
        MISSILE_THREE_COLOR,
        ROCKET
    };
    
    public static final String[] INVADERS = {			
	INVADER_DRAGONFISH,
        INVADER_HATCHEFISH,
        INVADER_JELLYFISH,
        INVADER_SHARK,
        INVADER_SHARK_MOVING,
        INVADER_VAPERFISH, 
        INVADER_MONSTER_SEA_HORSE
    };
    
}
