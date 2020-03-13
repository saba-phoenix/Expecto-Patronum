/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HarryPotter;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.scene.ImageCursor;
import javafx.scene.layout.StackPane;

public class LevelMenu  {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 700;
    Scene single=new Scene(new StackPane(),WIDTH,800);
    Stage window;

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Level One", () -> {
                System.out.println("Go to Game");
                //try{
                new ExpectoPatronum().goGame(window,300);
                //}
                //catch(FileNotFoundException e){
                 //   System.out.println("yesh");
                //}
            }),
           
            new Pair<String, Runnable>("Level Two", () -> {
                 new ExpectoPatronum().goGame(window,200);
            }),
            
            new Pair<String, Runnable>("Level Three", () -> {
                 new ExpectoPatronum().goGame(window,150);
            }),
           
            new Pair<String, Runnable>("Main Menu", () -> {
                try {
                new MainM().heyMainM(window);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GameFinishedLabel.class.getName()).log(Level.SEVERE, null, ex);
            }
            }),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );

    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;
/***
 * adding everything to the menu
 * @return the layout the created menu
 * @throws FileNotFoundException 
 */
   Parent createContent() throws FileNotFoundException {
        addBackground();
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addingMenu(lineX + 5, lineY + 5);

        beginAnimation();

        return root;
    }
/**
 * adding background of the menu page
 * @throws FileNotFoundException 
 */
    private void addBackground() throws FileNotFoundException {
        ImageView imageView = new ImageView(new Image(MainM.class.getResourceAsStream("res/hog.jpg")));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }
/***
 * Title of the game
 */
    private void addTitle() {
        Title title = new Title("Expecto Patronum");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }
/***
 * adds line to the menu page
 * @param x Co ordinate of line
 * @param y Co ordinate of line
 */
    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 240);
        line.setStrokeWidth(5);
        line.setStroke(Color.color(1,1,1,.65));
        line.setEffect(new DropShadow(5, Color.PINK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }
/***
 * Animation of menu item
 */
    private void beginAnimation (){
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }
/**
 * adds the menu item
 * @param x Co ordinate of menu
 * @param y Co ordinate of menu
 */
    private void addingMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }

  
   /***
    * creates the scene for the level menu
    * @param primaryStage on which menu level scene  is shown
    * @throws FileNotFoundException 
    */
    void heyLevelM(Stage primaryStage) throws FileNotFoundException{
        window=primaryStage;
        Scene scene = new Scene(createContent());
        Image mg=new Image(new FileInputStream("C:\\Users\\SMART\\Documents\\NetBeansProjects\\game sat\\res\\oie_transparent.png"));
        ImageCursor ff=new ImageCursor(mg);
        scene.setCursor(ff);
       /*
        String musicFile = "C:\\Users\\SMART\\Documents\\NetBeansProjects\\game sat\\res\\Harry Potter Theme Song.mp3"; 
        // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        */
        
        
        primaryStage.setTitle("Expecto Patronum");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    
}
