package edu.vanier.ufo.game;

import edu.vanier.ufo.engine.GameEngine;
import edu.vanier.ufo.engine.Sprite;
import edu.vanier.ufo.helpers.ResourcesManager;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

    /**
     * Animate an implosion. Once done remove from the game world
     *
     * @param gameWorld - game world
     */
    public void implode(final GameEngine gameWorld) {
        this.vX = this.vY = 0;
        Node currentNode = getNode();
        
        Sprite explosion = new Atom(ResourcesManager.EXPLOSION);
        if (!(this instanceof Missile)) {
            explosion.getNode().setTranslateX(this.getNode().getTranslateX());
            explosion.getNode().setTranslateY(this.getNode().getTranslateY());
            gameWorld.getSceneNodes().getChildren().add(explosion.getNode());
        }
        
        FadeTransition ft = new FadeTransition(Duration.millis(300), currentNode);
        ft.setFromValue(35);
        ft.setToValue(0);
        ft.setOnFinished((ActionEvent event) -> {
            this.isDead = true;
            gameWorld.getSceneNodes().getChildren().remove(currentNode);
            gameWorld.getSceneNodes().getChildren().remove(explosion.getNode());
        });
        ft.play();
    }

    @Override
    public void handleDeath(GameEngine gameWorld) {
        implode(gameWorld);
        super.handleDeath(gameWorld);
    }
}
