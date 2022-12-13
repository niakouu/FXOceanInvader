package edu.vanier.ufo.game;

import edu.vanier.ufo.engine.Sprite;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
/**
 * A spherical looking object (Atom) with a random radius, color, and velocity.
 * When two atoms collide each will fade and become removed from the scene. The
 * method called implode() implements a fade transition effect.
 *
 * @author cdea
 */
public class Atom extends Sprite {
    /**
     * Constructor will create a optionally create a gradient fill circle shape.
     * This sprite will contain a JavaFX Circle node.
     *
     * @param imagePath the path of the image to be embedded in the node object.
     */
    public Atom(String imagePath) {
        super(imagePath);
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        
        this.imageView.setEffect(reflection);
    }

    /**
     * Change the velocity of the current atom particle.
     */
    @Override
    public void update() {
        this.setTranslateX(this.getTranslateX() + this.vX);
        this.setTranslateY(this.getTranslateY() + this.vY);
        
        if (!(this instanceof Missile))
            this.setScaleX(this.vX < 0 ? -1 : 1);
    }

    /**
     * Returns a node casted as a JavaFX Circle shape.
     *
     * @return Circle shape representing JavaFX node for convenience.
     */
    public ImageView getImageViewNode() {
        return this.imageView;
    }  
    
}
