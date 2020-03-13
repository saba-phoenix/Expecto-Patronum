/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HarryPotter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

/***
 * 
 * @author Saba The Great
 */

public class Gem {
     private ImageView graphics = new ImageView();
    private Image frame;
    private int frameCounter = 0;
    public boolean jumping = false;
    public boolean falling = false;
    Circle bounds;
/**
 * for getting image view
 * @return image view of the gem pic
 */
    public ImageView getGraphics() {
        return graphics;
    }
/**
 * gem's base shape
 * @return shape
 */
    public Circle getBounds() {
        return bounds;
    }
/**
 * for creating gem
 * @param frame gem's photo
 */
    public Gem(Image frame) {
        this.frame = frame;
                
        this.bounds = new Circle();
        bounds.setRadius(frame.getWidth()/2);
        
        
        System.out.println(frame.getHeight()/3);
        graphics.setImage(frame);
       graphics.setRotate(25);
       /*
        graphics.setFitHeight(120);
        graphics.setFitWidth(120);
        graphics.setPreserveRatio(true);
        */
        bounds.setFill(Color.TRANSPARENT);
        bounds.setStroke(Color.TRANSPARENT);
        bounds.centerXProperty().bind(graphics.translateXProperty().add((frame.getWidth()/2 )));
        bounds.centerYProperty().bind(graphics.translateYProperty().add(150));
        bounds.rotateProperty().bind(graphics.rotateProperty());
        boolean w=bounds.isVisible();
        bounds.setVisible(true);
        System.out.println(w);
    }

  

    
}
