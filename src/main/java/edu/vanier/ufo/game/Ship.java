package edu.vanier.ufo.game;

import edu.vanier.ufo.helpers.ResourcesManager;
import edu.vanier.ufo.engine.Sprite;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * A spaceship with 32 directions When two atoms collide each will fade and
 * become removed from the scene. The method called implode() implements a fade
 * transition effect.
 *
 * @author cdea
 */
public class Ship  {

//    /**
//     * 360 degree turn
//     */
//    private final static int TWO_PI_DEGREES = 360;
//
//    /**
//     * Number of ship frames and directions the ship is pointing nose
//     */
//    private final static int NUM_DIRECTIONS = 32;
//
//    /**
//     * The angle of one direction (adjacent directions) (11.25 degrees)
//     */
//    private final static float UNIT_ANGLE_PER_FRAME = ((float) TWO_PI_DEGREES / NUM_DIRECTIONS);
//
//    /**
//     * Amount of time it takes the ship to move 180 degrees in milliseconds.
//     */
//    private final static int MILLIS_TURN_SHIP_180_DEGREES = 300;
//
//    /**
//     * When the ship turns on each direction one amount of time for one frame or
//     * turn of the ship. (18.75 milliseconds)
//     */
//    private final static float MILLIS_PER_FRAME = (float) MILLIS_TURN_SHIP_180_DEGREES / (NUM_DIRECTIONS / 2);
//
//    /**
//     * All possible turn directions Clockwise, Counter Clockwise, or Neither
//     * when the user clicks mouse around ship
//     */
//    private enum DIRECTION {
//        CLOCKWISE, COUNTER_CLOCKWISE, NEITHER
//    }
//
//    /**
//     * Velocity amount used vector when ship moves forward. scale vector of
//     * ship. See flipBook translateX and Y.
//     */
//    private final static float THRUST_AMOUNT = 4.3f;
//
//    /**
//     *
//     */
//    private final static float MISSILE_THRUST_AMOUNT = 6.3F;
//
//    /**
//     * Angle in degrees to rotate ship.
//     */
//    /**
//     * Current turning direction. default is NEITHER. Clockwise and Counter
//     * Clockwise.
//     */
//    private DIRECTION turnDirection = DIRECTION.NEITHER;
//
//    /**
//     * The current starting position of the vector or coordinate where the nose
//     * of the ship is pointing towards.
//     */
//    private CustomVector u; // current or start vector
//
//    /**
//     * All ImageViews of all the possible image frames for each direction the
//     * ship is pointing. ie: 32 directions.
//     */
//    private final List<RotatedShipImage> directionalShips = new ArrayList<>();
//
//    /**
//     * The Timeline instance to animate the ship rotating using images. This is
//     * an optical illusion similar to page flipping as each frame is displayed
//     * the previous visible attribute is set to false. No rotation is happening.
//     */
//    private Timeline rotateShipTimeline;
//
//    /**
//     * The current index into the list of ImageViews representing each direction
//     * of the ship. Zero is the ship pointing to the right or zero degrees.
//     */
//    private int uIndex = 0;
//
//    /**
//     * The end index into the list of ImageViews representing each direction of
//     * the ship. Zero is the ship pointing to the right or zero degrees.
//     */
//    private int vIndex = 0;
//
//    /**
//     * The spot where the user has right clicked letting the engine check the
//     * ship's center is in this area.
//     */
//    private final Circle stopArea = new Circle();
//
//    /**
//     * A group contain all of the ship image view nodes.
//     */
//    private final Group flipBook = new Group();
//
//    /**
//     * A key code will be used for weapon selection.
//     */
//    private KeyCode keyCode;
//
//    /**
//     * Turn shields on
//     */
//    private boolean shieldOn;
//
//    /**
//     * Green shield to be used as collision bounds.
//     */
//    private Circle shield;
//
//    /**
//     * A fade effect while the shields are up momentarily
//     */
//    private FadeTransition shieldFade;
//
//    /**
//     * The collision bounding region for the ship
//     */
//    private Circle hitBounds;
//
//    public Ship() {
//        // Load one image.
//        Image shipImage;
//        shipImage = new Image(ResourcesManager.SPACE_STAR_SHIP, true);
//        this.stopArea.setRadius(40);
//        this.stopArea.setStroke(Color.ORANGE);
//        RotatedShipImage prev = null;
//
//        // create all the number of directions based on a unit angle. 360 divided by NUM_DIRECTIONS
//        for (int i = 0; i < NUM_DIRECTIONS; i++) {
//            RotatedShipImage imageView = new RotatedShipImage();
//            imageView.setImage(shipImage);
//            imageView.setRotate(-1 * i * UNIT_ANGLE_PER_FRAME);
//            imageView.setCache(true);
//            imageView.setCacheHint(CacheHint.SPEED);
//            imageView.setManaged(false);
//
//            imageView.setPrevRotatedImage(prev);
//            imageView.setVisible(false);
//            this.directionalShips.add(imageView);
//            if (prev != null) {
//                prev.setNextRotatedImage(imageView);
//            }
//            prev = imageView;
//            this.flipBook.getChildren().add(imageView);
//        }
//
//        RotatedShipImage firstShip = this.directionalShips.get(0);
//        firstShip.setPrevRotatedImage(prev);
//        prev.setNextRotatedImage(firstShip);
//        // set javafx node to an image
//        firstShip.setVisible(true);
//        setNode(this.flipBook);
//        this.flipBook.setTranslateX(350);
//        this.flipBook.setTranslateY(450);
//        this.flipBook.setCache(true);
//        this.flipBook.setCacheHint(CacheHint.SPEED);
//        this.flipBook.setManaged(false);
//        this.flipBook.setAutoSizeChildren(false);
//        
//        this.u = new CustomVector(1, 0);
//        initHitZone();
//    }
//
//    /**
//     * Change the ship imageView
//     *
//     * @param newShip new image of ship
//     */
//    public void changeShip(String newShip) {
//        double currentX = this.flipBook.getTranslateX();
//        double currentY = this.flipBook.getTranslateY();
//
//        this.directionalShips.clear();
//        this.flipBook.getChildren().clear();
//
//        Image shipImage;
//        shipImage = new Image(newShip, true);
//        this.stopArea.setRadius(40);
//        this.stopArea.setStroke(Color.ORANGE);
//        RotatedShipImage prev = null;
//        // create all the number of directions based on a unit angle. 360 divided by NUM_DIRECTIONS
//        for (int i = 0; i < NUM_DIRECTIONS; i++) {
//            RotatedShipImage imageView = new RotatedShipImage();
//            imageView.setImage(shipImage);
//            imageView.setRotate(-1 * i * UNIT_ANGLE_PER_FRAME);
//            imageView.setCache(true);
//            imageView.setCacheHint(CacheHint.SPEED);
//            imageView.setManaged(false);
//
//            imageView.setPrevRotatedImage(prev);
//            imageView.setVisible(false);
//
//            this.directionalShips.add(imageView);
//            if (prev != null) {
//                prev.setNextRotatedImage(imageView);
//            }
//            prev = imageView;
//            this.flipBook.getChildren().add(imageView);
//        }
//
//        RotatedShipImage firstShip = this.directionalShips.get(0);
//        firstShip.setPrevRotatedImage(prev);
//        prev.setNextRotatedImage(firstShip);
//
//        // set javafx node to an image
//        firstShip.setVisible(true);
//        setNode(this.flipBook);
//        this.flipBook.setTranslateX(currentX);
//        this.flipBook.setTranslateY(currentY);
//        this.flipBook.setCache(true);
//        this.flipBook.setCacheHint(CacheHint.SPEED);
//        this.flipBook.setManaged(false);
//        this.flipBook.setAutoSizeChildren(false);
//        
//        this.u = new CustomVector(1, 0);
//        initHitZone();
//    }
//
//    /**
//     * Initialize the collision region for the space ship. It's just an
//     * inscribed circle.
//     */
//    public void initHitZone() {
//        // build hit zone
//        if (this.hitBounds == null) {
//            //RotatedShipImage firstShip = directionalShips.get(0);
//            double hZoneCenterX = 55;
//            double hZoneCenterY = 34;
//            this.hitBounds = new Circle();
//            this.hitBounds.setCenterX(hZoneCenterX);
//            this.hitBounds.setCenterY(hZoneCenterY);
//            this.hitBounds.setStroke(Color.PINK);
//            this.hitBounds.setFill(Color.RED);
//            this.hitBounds.setRadius(15);
//            this.hitBounds.setOpacity(0);
//            this.flipBook.getChildren().add(this.hitBounds);
//            setCollisionBounds(this.hitBounds);
//        }
//    }
//
//    /**
//     * Change the velocity of the atom particle.
//     */
//    @Override
//    public void update() {
//        this.flipBook.setTranslateX(this.flipBook.getTranslateX() + this.vX);
//        this.flipBook.setTranslateY(this.flipBook.getTranslateY() + vY);
//
//        if (this.stopArea.contains(getCenterX(), getCenterY())) {
//            this.vX = 0;
//            this.vY = 0;
//        }
//    }
//
//    private RotatedShipImage getCurrentShipImage() {
//        return this.directionalShips.get(this.uIndex);
//    }
//    
//     /**
//     * Determines the angle between it's starting position and ending position
//     * (Similar to a clock's second hand). When the user is shooting the ship
//     * nose will point in the direction of the mouse press using the primary
//     * button. When the user is thrusting to a location on the screen the right
//     * click mouse will pass true to the thrust parameter.
//     *
//     * @param screenX The mouse press' screen x coordinate.
//     * @param screenY The mouse press' screen ycoordinate.
//     * @param thrust Thrust ship forward or not. True move forward otherwise
//     * false.
//     */
//    public void plotCourse(double screenX, double screenY, boolean thrust) {
//        // get center of ship
//        double sx = getCenterX();
//        double sy = getCenterY();
//
//        // get user's new turn position based on mouse click
//        CustomVector v = new CustomVector(screenX, screenY, sx, sy);
//
//        double atan2RadiansU = Math.atan2(this.u.y, this.u.x);
//        double atan2DegreesU = Math.toDegrees(atan2RadiansU);
//
//        double atan2RadiansV = Math.atan2(v.y, v.x);
//        double atan2DegreesV = Math.toDegrees(atan2RadiansV);
//
//        double angleBetweenUAndV = atan2DegreesV - atan2DegreesU;
//
//        // if abs value is greater than 180 move counter clockwise
//        //(or opposite of what is determined)
//        double absAngleBetweenUAndV = Math.abs(angleBetweenUAndV);
//        boolean goOtherWay = false;
//        if (absAngleBetweenUAndV > 180) {
//            if (angleBetweenUAndV < 0) {
//                this.turnDirection = Ship.DIRECTION.COUNTER_CLOCKWISE;
//                goOtherWay = true;
//            } else if (angleBetweenUAndV > 0) {
//                this.turnDirection = Ship.DIRECTION.CLOCKWISE;
//                goOtherWay = true;
//            } else {
//                this.turnDirection = Ship.DIRECTION.NEITHER;
//            }
//        } else {
//            if (angleBetweenUAndV < 0) {
//                this.turnDirection = Ship.DIRECTION.CLOCKWISE;
//            } else if (angleBetweenUAndV > 0) {
//                this.turnDirection = Ship.DIRECTION.COUNTER_CLOCKWISE;
//            } else {
//                this.turnDirection = Ship.DIRECTION.NEITHER;
//            }
//        }
//
//        double degreesToMove = absAngleBetweenUAndV;
//        
//        if (goOtherWay) {
//            degreesToMove = TWO_PI_DEGREES - absAngleBetweenUAndV;
//        }
//
//        //int q = v.quadrant();
//        this.uIndex = Math.round((float) (atan2DegreesU / UNIT_ANGLE_PER_FRAME));
//        if (this.uIndex < 0) {
//            this.uIndex = NUM_DIRECTIONS + uIndex;
//        }
//        this.vIndex = Math.round((float) (atan2DegreesV / UNIT_ANGLE_PER_FRAME));
//        if (this.vIndex < 0) {
//            this.vIndex = NUM_DIRECTIONS + this.vIndex;
//        }
//        if (thrust) {
//            this.vX = Math.cos(atan2RadiansV) * THRUST_AMOUNT;
//            this.vY = -Math.sin(atan2RadiansV) * THRUST_AMOUNT;
//        }
//        turnShip();
//
//        this.u = v;
//    }
//
//    /**
//     * Determines the angle between it's starting position and ending position
//     * (Similar to a clock's second hand).When the user is shooting the ship
// nose will point in the direction of the mouse press using the primary
// button. When the user is thrusting to a location on the screen the right
// click mouse will pass true to the thrust parameter.
//     *
//     * @param globalVector end point in the vector space
//     * @param thrust Thrust ship forward or not. True move forward otherwise
//     * false.
//     * @param isMouseEvent if the path course we are making is a mouse click or not
//     */
//    public void plotCourse(CustomVector globalVector, boolean thrust, boolean isMouseEvent) {
//        if (isMouseEvent) {
//            globalVector.setScreenX(getCenterX());
//            globalVector.setScreenY(getCenterY());
//        }
//
//        double atan2RadiansU = Math.atan2(this.u.y, this.u.x);
//        double atan2DegreesU = Math.toDegrees(atan2RadiansU);
//
//        double atan2RadiansV = Math.atan2(globalVector.y, globalVector.x);
//        double atan2DegreesV = Math.toDegrees(atan2RadiansV);
//
//        double angleBetweenUAndV = atan2DegreesV - atan2DegreesU;
//
//        // if abs value is greater than 180 move counter clockwise
//        //(or opposite of what is determined)
//        double absAngleBetweenUAndV = Math.abs(angleBetweenUAndV);
//        boolean goOtherWay = false;
//        if (absAngleBetweenUAndV > 180) {
//            if (angleBetweenUAndV < 0) {
//                this.turnDirection = Ship.DIRECTION.COUNTER_CLOCKWISE;
//                goOtherWay = true;
//            } else if (angleBetweenUAndV > 0) {
//                this.turnDirection = Ship.DIRECTION.CLOCKWISE;
//                goOtherWay = true;
//            } else {
//                this.turnDirection = Ship.DIRECTION.NEITHER;
//            }
//        } else {
//            if (angleBetweenUAndV < 0) {
//                this.turnDirection = Ship.DIRECTION.CLOCKWISE;
//            } else if (angleBetweenUAndV > 0) {
//                this.turnDirection = Ship.DIRECTION.COUNTER_CLOCKWISE;
//            } else {
//                this.turnDirection = Ship.DIRECTION.NEITHER;
//            }
//        }
//
//        double degreesToMove = absAngleBetweenUAndV;
//        
//        if (goOtherWay) {
//            degreesToMove = TWO_PI_DEGREES - absAngleBetweenUAndV;
//        }
//
//        //int q = v.quadrant();
//        this.uIndex = Math.round((float) (atan2DegreesU / UNIT_ANGLE_PER_FRAME));
//        if (this.uIndex < 0) {
//            this.uIndex = NUM_DIRECTIONS + uIndex;
//        }
//        this.vIndex = Math.round((float) (atan2DegreesV / UNIT_ANGLE_PER_FRAME));
//        if (this.vIndex < 0) {
//            this.vIndex = NUM_DIRECTIONS + this.vIndex;
//        }
//        if (thrust) {
//            this.vX = Math.cos(atan2RadiansV) * THRUST_AMOUNT;
//            this.vY = -Math.sin(atan2RadiansV) * THRUST_AMOUNT;
//        }
//        turnShip();
//
//        this.u = globalVector;
//    }
//
//    private void turnShip() {
//
//        final Duration oneFrameAmt = Duration.millis(MILLIS_PER_FRAME);
//        RotatedShipImage startImage = this.directionalShips.get(this.uIndex);
//        RotatedShipImage endImage = this.directionalShips.get(this.vIndex);
//        List<KeyFrame> frames = new ArrayList<>();
//
//        RotatedShipImage currImage = startImage;
//
//        int i = 1;
//        while (true) {
//
//            final Node displayNode = currImage;
//
//            KeyFrame oneFrame = new KeyFrame(oneFrameAmt.multiply(i), (javafx.event.ActionEvent event) -> {
//                // make all ship images invisible
//                for (RotatedShipImage shipImg : this.directionalShips) {
//                    shipImg.setVisible(false);
//                }
//                // make current ship image visible
//                displayNode.setVisible(true);
//
//                // update the current index
//                // uIndex = directionalShips.indexOf(displayNode);
//            }); // oneFrame
//
//            frames.add(oneFrame);
//
//            if (currImage == endImage) {
//                break;
//            }
//            if (this.turnDirection == DIRECTION.CLOCKWISE) {
//                currImage = currImage.getPrevRotatedImage();
//            }
//            if (this.turnDirection == DIRECTION.COUNTER_CLOCKWISE) {
//                currImage = currImage.getNextRotatedImage();
//            }
//            i++;
//        }
//
//        if (this.rotateShipTimeline != null) {
//            this.rotateShipTimeline.stop();
//            this.rotateShipTimeline.getKeyFrames().clear();
//            this.rotateShipTimeline.getKeyFrames().addAll(frames);
//        } else {
//            // sets the game world's game loop (Timeline)
//            Timeline timeline = new Timeline();
//            timeline.getKeyFrames().addAll(frames);
//            this.rotateShipTimeline = timeline;
//        }
//        this.rotateShipTimeline.playFromStart();
//
//    }
//
//    /**
//     * Stops the ship from thrusting forward.
//     *
//     * @param screenX the screen's X coordinate to stop the ship.
//     * @param screenY the screen's Y coordinate to stop the ship.
//     */
//    public void applyTheBrakes(double screenX, double screenY) {
//        this.stopArea.setCenterX(screenX);
//        this.stopArea.setCenterY(screenY);
//    }
//
//    public Missile fire() {
//        Missile fireMissile;
//        float slowDownAmt = 0;
//        int scaleBeginningMissle;
//        if (KeyCode.DIGIT2 == this.keyCode) {
//            fireMissile = new Missile(ResourcesManager.ROCKET_FIRE);
//            slowDownAmt = 1.3f;
//            scaleBeginningMissle = 11;
//        } else {
//            fireMissile = new Missile(ResourcesManager.ROCKET_SMALL);
//            scaleBeginningMissle = 8;
//        }
//
//        //fireMissile.setPosition(getNode().getLayoutX()+ 10, getNode().getLayoutY() - 20);
//        // velocity vector of the missile
//        fireMissile.setVelocityX(Math.cos(Math.toRadians(this.uIndex * UNIT_ANGLE_PER_FRAME)) * (MISSILE_THRUST_AMOUNT - slowDownAmt));
//        fireMissile.setVelocityY(Math.sin(Math.toRadians(-this.vIndex * UNIT_ANGLE_PER_FRAME)) * (MISSILE_THRUST_AMOUNT - slowDownAmt));
//
//        // make the missile launch in the direction of the current direction of the ship nose. based on the
//        // current frame (uIndex) into the list of image view nodes.
//        RotatedShipImage shipImage = this.directionalShips.get(this.uIndex);
//
//        // start to appear in the center of the ship to come out the direction of the nose of the ship.
//        double offsetX = (shipImage.getBoundsInLocal().getWidth() - fireMissile.getNode().getBoundsInLocal().getWidth()) / 2;
//        double offsetY = (shipImage.getBoundsInLocal().getHeight() - fireMissile.getNode().getBoundsInLocal().getHeight()) / 2;
//
//        // initial launch of the missile   (multiply vector by 4 makes it appear at the nose of the ship)
//        fireMissile.getNode().setTranslateX(getNode().getTranslateX() + (offsetX + (fireMissile.getVelocityX() * scaleBeginningMissle)));
//        fireMissile.getNode().setTranslateY(getNode().getTranslateY() + (offsetY + (fireMissile.getVelocityY() * scaleBeginningMissle)));
//        return fireMissile;
//    }
//
//    public void changeWeapon(KeyCode keyCode) {
//        this.keyCode = keyCode;
//    }
//
//    public void shieldToggle() {
//        if (this.shield == null) {
//            RotatedShipImage shipImage = getCurrentShipImage();
//            double x = shipImage.getBoundsInLocal().getWidth() / 2;
//            double y = shipImage.getBoundsInLocal().getHeight() / 2;
//
//            // add shield
//            this.shield = new Circle();
//            this.shield.setRadius(60);
//            this.shield.setStrokeWidth(5);
//            this.shield.setStroke(Color.LIMEGREEN);
//            this.shield.setCenterX(x);
//            this.shield.setCenterY(y);
//            this.shield.setOpacity(.70);
//            setCollisionBounds(this.shield);
//            //--
//            this.shieldFade = new FadeTransition();
//            this.shieldFade.setFromValue(1);
//            this.shieldFade.setToValue(.40);
//            this.shieldFade.setDuration(Duration.millis(1000));
//            this.shieldFade.setCycleCount(12);
//            this.shieldFade.setAutoReverse(true);
//            this.shieldFade.setNode(this.shield);
//            this.shieldFade.setOnFinished((ActionEvent actionEvent) -> {
//                this.shieldOn = false;
//                this.flipBook.getChildren().remove(this.shield);
//                this.shieldFade.stop();
//                setCollisionBounds(this.hitBounds);
//            });
//            this.shieldFade.playFromStart();
//
//        }
//        this.shieldOn = !shieldOn;
//        if (this.shieldOn) {
//            setCollisionBounds(this.shield);
//            this.flipBook.getChildren().add(0, this.shield);
//            this.shieldFade.playFromStart();
//        } else {
//            this.flipBook.getChildren().remove(this.shield);
//            this.shieldFade.stop();
//            setCollisionBounds(this.hitBounds);
//        }
//    }
}
