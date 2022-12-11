package edu.vanier.ufo.game;

import edu.vanier.ufo.engine.Sprite;
import javafx.scene.image.Image;
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
        ImageView newAtom = new ImageView();
        Image shipImage = new Image(imagePath, true); 
        newAtom.setImage(shipImage);
        this.setImage(shipImage);
        
        this.node = newAtom;
        this.collidingNode = newAtom;
    }

    /**
     * Change the velocity of the current atom particle.
     */
    @Override
    public void update() {
        getNode().setTranslateX(getNode().getTranslateX() + this.vX);
        getNode().setTranslateY(getNode().getTranslateY() + this.vY);
    }

    /**
     * Returns a node casted as a JavaFX Circle shape.
     *
     * @return Circle shape representing JavaFX node for convenience.
     */
    public ImageView getImageViewNode() {
        return (ImageView) getNode();
    }  
    
}
