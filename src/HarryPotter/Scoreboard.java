package HarryPotter;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Scoreboard extends Pane {

    Text Lines = new Text("Score: 0");
    Text gstatus = new Text("");
    
/**
 * Score label
 * @param x Co ordinate of the label
 * @param y Co ordinate of the label
 */
    public Scoreboard(double x, double y) {
        setPrefHeight(150);
        setPrefWidth(300);
        setTranslateX(x - 310);
        setTranslateY(y + 10);
        setStyle("-fx-background-color: #CCC;"
                + "-fx-background-radius:20px");
        setOpacity(0.8);
        Lines.setTranslateY(50);
        Lines.setTranslateX(100);
        gstatus.setTranslateY(100);
        gstatus.setTranslateX(100);
        getChildren().addAll(Lines,gstatus);
        Lines.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 22));
        Lines.setFill(/*new Color(107 / 255.0, 162 / 255.0, 252 / 255.0, 1.0*/Color.RED);
        gstatus.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 22));
        gstatus.setFill(Color.BLACK);
    }
/**
 *  Setting text for score count
 * @param message Showed score
 */
    public void setText(String message) {
        Lines.setText(message);

    }
    /**
     * Setting text for gem count
     * @param message Showed Gem
     */
    public void setGText(String message) {
        gstatus.setText(message);

    }
}

class GameFinishedLabel extends Scoreboard {
    /**
     * When game is over ,this label is shown
     * @param x Co ordinate of the label
     * @param y Co ordinate of the label
     
     */

    public GameFinishedLabel(double x, double y,Stage primaryStage) {
        super(x, y);
        Label t=new Label("Go to Main Menu\n");
        getChildren().add(t);
        t.setLayoutX(x-490);
        t.setLayoutY((y/3)+30);
        
        t.setScaleX(1.5);
        t.setScaleY(1.5);
        setOpacity(0.8);
        t.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 20));
        t.setTextFill(Color.BLACK);
        t.setOnMouseClicked(e -> {
            System.out.println("MAIN MENU");
            try {
                new MainM().heyMainM(primaryStage);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameFinishedLabel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        setPrefWidth(500);
        setPrefHeight(250);
        
        setTranslateX(x - 180);
        setTranslateY(y-180);
        
        Lines.setTranslateX(100);
        gstatus.setTranslateX(150);

    }

}