/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HarryPotter;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Saba The Great
 */
public class Tutorial {
    Image img=new Image(Tutorial.class.getResourceAsStream("res/tutorial.jpg"));
    private ImageView graphics = new ImageView(img);
    /***
     * for creating tutorial scene
     * @param primaryStage on which stage tutorial scene will be shown
     */
    Tutorial(Stage primaryStage){
        graphics.setFitHeight(700);
        graphics.setFitWidth(1000);
        Pane root=new Pane();
        root.getChildren().add(graphics);
        Scene scene = new Scene (root,1000,700);
        primaryStage.setScene(scene);
        
        
    }
    
    
    
}
