/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HarryPotter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Saba 
 */
public class Harry {
    private ImageView graphics = new ImageView();
    private Image frame;
    private int frameCounter = 0;
    public boolean jumping = false;
    public boolean falling = false;
    Ellipse shape;
/***
 * for getting image view
 * @return image view of the character
 */
    public ImageView getGraphics() {
        return graphics;
    }
/**
 * for getting base shape of character
 * @return shape
 */
    public Ellipse getShape() {
        return shape;
    }
/**
 * for creating character
 * @param frame photo of harry
 */
    public Harry(Image frame) {
        this.frame = frame;
                
        this.shape = new Ellipse(frame.getWidth()/2.1 , frame.getHeight()/2.3);
        System.out.println(frame.getHeight()/3);
        graphics.setImage(frame);
       graphics.setRotate(25);
      
        shape.setFill(Color.ORANGE);
        shape.setStroke(Color.ORANGE);
        shape.centerXProperty().bind(graphics.translateXProperty().add((frame.getWidth() / 1.9)));
        shape.centerYProperty().bind(graphics.translateYProperty().add(80));
        shape.rotateProperty().bind(graphics.rotateProperty());
        boolean w=shape.isVisible();
        shape.setVisible(true);
        System.out.println(w);
    }

  

    
}
