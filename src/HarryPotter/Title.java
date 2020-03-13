/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HarryPotter;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * @author Satabdi
 */
public class Title extends Pane {
    private Text text;
/**
 * setting title name
 * @param name title name
 */
    public Title(String name) {
        String spread = "";
        for (char c : name.toCharArray()) {
            spread += c + " ";
        }

        text = new Text(spread);
        text.setFont(Font.loadFont("file:res/hp.ttf", 50));
        text.setFill(Color.SILVER   );
        text.setEffect(new DropShadow(30, Color.BLACK));

        getChildren().addAll(text);
       
    }
/**
 * Title width
 * @return width of title
 */
   public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }
/**
 * Title height
 * @return height of title
 */
    public double getTitleHeight() {
        return text.getLayoutBounds().getHeight();
    }

}