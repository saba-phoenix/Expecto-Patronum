/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HarryPotter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Saba & Satabdi
 */
public class Obstacles extends Group {

    public Rectangle topHead, lowerHead;
    public Rectangle topBody, lowerBody;
    double GAP = 270;
    public Image gemImgs = new Image(ExpectoPatronum.class.getResourceAsStream("res/Gem2.png"));
    Gem gem = new Gem(gemImgs);
    Image wall1 = new Image(ExpectoPatronum.class.getResourceAsStream("res/wall.jpg"));
    Image wall2 = new Image(ExpectoPatronum.class.getResourceAsStream("res/wall.jpg"));
    private ImageView graphics1 = new ImageView();
    private ImageView graphics2 = new ImageView();

    Stop[] stops = new Stop[]{new Stop(0, Color.CHOCOLATE), new Stop(1, Color.BROWN)};
    LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
    Color c2 = new Color(88 / 255.0, 56 / 255.0, 71 / 255.0, 1.0);
    double oscillationCenter;
    Timeline animateTube;
    int frames = 0;
/***
 * Creating Obstacles
 * @param gapLocation height of the obstacles
 * @param root layout of the obstacles
 * @param animate vertical movement
 * @param rotate angular movement
 */
    public Obstacles(SimpleDoubleProperty gapLocation, Pane root, boolean animate, boolean rotate) {
        graphics1.setImage(wall1);
        graphics2.setImage(wall2);
        if (rotate) {
            setRotationAxis(Rotate.Z_AXIS);
            setRotate(-25 + 50 * Math.random());

            setTranslateY(-40);
        }
        topBody = new Rectangle();
        oscillationCenter = gapLocation.get();
        //System.out.println(oscillationCenter);
        if (animate) {
            animateTube = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                gapLocation.set(25 * Math.cos(Math.PI / 60 * frames) + oscillationCenter);
                frames = (frames + 1) % 120;
            }));
            animateTube.setCycleCount(-1);
            animateTube.play();
        }

        topHead = new Rectangle();
        topHead.widthProperty().bind(root.widthProperty().divide(14.1));
        topHead.heightProperty().bind(root.heightProperty().divide(11.2));

        topHead.yProperty().bind(gapLocation);
        graphics2.yProperty().bind(gapLocation);

        lowerHead = new Rectangle();
        lowerHead.widthProperty().bind(root.widthProperty().divide(14.4));
        lowerHead.heightProperty().bind(root.heightProperty().divide(12));

        graphics1.yProperty().bind(gapLocation.add(GAP).add(root.heightProperty().divide(12)));;

        lowerHead.yProperty().bind(gapLocation.add(GAP).add(root.heightProperty().divide(12)));

        gem.getGraphics().yProperty().bind(gapLocation.add(GAP - 150).add(root.heightProperty().divide(12)));
        gem.getBounds().centerYProperty().bind(gapLocation.add(GAP - 150).add(root.heightProperty().divide(12)));

        lowerHead.setFill(lg1);
        topHead.setFill(lg1);
        lowerHead.setStroke(c2);

        topHead.setStroke(c2);

        Circle d = new Circle();
        getChildren().addAll(topHead, graphics2, gem.getBounds(), gem.getGraphics(), lowerHead, graphics1);
        getChildren().addAll();

    }

}
