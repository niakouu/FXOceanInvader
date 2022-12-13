package edu.vanier.ufo.engine;

import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Responsible for loading sound media to be played using an id or key. Contains
 * all sounds for use later.
 * <p/>
 * User: cdea
 */
public class SoundManager {

    private final ExecutorService soundPool;
    private final Map<String, AudioClip> soundEffectsMap;

    /**
     * Constructor to create a simple thread pool.
     *
     * @param numberOfThreads - number of threads to use media players in the
     * map.
     */
    public SoundManager(int numberOfThreads) {
        this.soundPool = Executors.newFixedThreadPool(numberOfThreads);
        this.soundEffectsMap = new HashMap<>();
    }
    
    /**
     * Stop all threads and media players.
     */
    public void shutdown() {
        this.soundPool.shutdown();
    }
    
    /**
     * Load a sound into a map to later be played based on the id.
     *
     * @param id - The identifier for a sound.
     * @param url - The url location of the media or audio resource. Usually in
     * src/main/resources directory.
     */
    public void loadSoundEffects(String id, URL url) {
        AudioClip sound = new AudioClip(url.toExternalForm());
        sound.setVolume(0.4);
        this.soundEffectsMap.put(id, sound);
    }

    /**
     * Lookup a name resource to play sound based on the id.
     *
     * @param id identifier for a sound to be played.
     */
    public void playSound(final String id) {
        Runnable soundPlay = () -> {
            this.soundEffectsMap.get(id).play();
        };
        this.soundPool.execute(soundPlay);
    }
    
    public void stopSound(final String id) {
        Runnable stopSound = () -> {
            this.soundEffectsMap.get(id).stop();
        };
        this.soundPool.execute(stopSound);
    }

}
