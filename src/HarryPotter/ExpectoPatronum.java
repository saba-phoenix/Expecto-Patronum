/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HarryPotter;

//import copyproject.res.Resourses;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Saba &sat
 */
public class ExpectoPatronum implements EventHandler<KeyEvent> {

    private final double width = 1240, height = 750; // set screen size
    int level;
    //Resourses res = new Resourses();
    
    public Image harryImgs = new Image(ExpectoPatronum.class.getResourceAsStream("res/harry_resize.gif"));
    String ops = "C:\\Users\\SMART\\Documents\\NetBeansProjects\\Hairypotter\\src\\HarryPotter\\res\\harry.mp3";
    String gemMusic = "C:\\Users\\SMART\\Documents\\NetBeansProjects\\Hairypotter\\src\\HarryPotter\\res\\gem.mp3";
    Media sound1, sound2;
    MediaPlayer mediaPlayer2, mediaPlayer1;

    Pane root;
    boolean gameOver = false;
    private boolean increaseOne = true;
    private boolean gIncreaseOnce = true;
    int score = 0;
    int highScore = 0;
    int gemScore = 0;
    int gemHighScore = 0;
    double FPS_30 = 30;

    Stage window;

    Harry harry;
    TranslateTransition up;
    TranslateTransition down;
    RotateTransition rotator;
    ArrayList<Obstacles> listOfBlocks = new ArrayList<>();
    ArrayList<Rain> RainList = new ArrayList<>();
    Scoreboard scoreLabel = new Scoreboard(width - 100, 0);
    Timeline gameLoop;
    Image bg = new Image(ExpectoPatronum.class.getResourceAsStream("res/forest.png"));
    Image bgk = new Image(ExpectoPatronum.class.getResourceAsStream("res/forest2.png"));
    
    private ParallelTransition parallelTransition;
    //Image img=new Image(ExpectoPatronum.class.getResourceAsStream("res/hog.jpg"));
    ImageView bg1 = new ImageView(bg);
    ImageView bg2 = new ImageView(bgk);
    /**
     * for scrolling background
     */
    void moveBackGround(){
         TranslateTransition translateTransition
                = new TranslateTransition(Duration.millis(6000), bg2);
         System.out.println(bg2.getFitWidth());
        translateTransition.setFromX(1240);
        translateTransition.setToX(0);
        translateTransition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition translateTransition2
                = new TranslateTransition(Duration.millis(6000), bg1);
        translateTransition2.setFromX(0);
        translateTransition2.setToX(-1240);
        translateTransition2.setInterpolator(Interpolator.LINEAR);

        parallelTransition
                = new ParallelTransition(translateTransition, translateTransition2);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        
    }
/**
 * It creates the Scene for main game
 * @param primaryStage Stage on which new scene will be shown
 * @param lvl level of the game/speed of the game
 */
    void goGame(Stage primaryStage, int lvl) {
        level = lvl;
        window = primaryStage;
        root = new Pane();
        

        //bg1.setImage(bg);
        bg1.setFitHeight(height);
        bg1.setFitWidth(width);
        bg2.setFitHeight(height);
        bg2.setFitWidth(width);
        moveBackGround();
        parallelTransition.play();
        

        Scene scene = new Scene(root, width, height);
        scene.setOnKeyPressed(this);

        primaryStage.setTitle("Expecto Patronam");
        primaryStage.setScene(scene);
        
        initGame();
        
        root.setPrefSize(width, height);
        root.setOnMouseClicked(e -> {
            if (!gameOver) {
                upHarry();
            } else {
                initializeGame();
            }
        });

    }
/***
 * for upward movement
 */
    private void upHarry() {
        rotator.setDuration(Duration.millis(20));
        rotator.setToAngle(-5);
        rotator.stop();
        rotator.play();
        up.setByY(-100);
        up.setCycleCount(1);
        harry.jumping = true;
        
        up.stop();
        up.play();
        up.setOnFinished((finishedEvent) -> {
            rotator.setDuration(Duration.millis(300));
            rotator.setToAngle(4);
            rotator.stop();
            rotator.play();
            harry.jumping = false;
            
        });
    }
/**
 * for downward movement
 */
    private void downHarry() {
        rotator.setDuration(Duration.millis(20));
        rotator.setToAngle(5);
        rotator.stop();
        rotator.play();
        if (gameOver) {
            down.setByY(height);
        } else {
            down.setByY(160);
        }

        down.setCycleCount(1);
        harry.falling = true;
        
        down.stop();
        down.play();

        
        down.setOnFinished((finishedEvent) -> {
            rotator.setDuration(Duration.millis(500));
            rotator.setToAngle(4);
            rotator.stop();
            rotator.play();
            harry.falling = false;
            down.stop();;
            // fall.play();
        });
    }
    /**
     * Checks Collision
     */

    private void checkCollisions() {
        Obstacles obstacle = listOfBlocks.get(0);
        if (obstacle.getTranslateX() < 45 && increaseOne) {
            score++;
            scoreLabel.setText("Score: " + score);
            increaseOne = false;

        }

        Path p2 = (Path) Shape.intersect(harry.getShape(), obstacle.topHead);

        Path p4 = (Path) Shape.intersect(harry.getShape(), obstacle.lowerHead);
        Path p = (Path) Shape.intersect(harry.getShape(), obstacle.gem.getBounds());
        boolean intersection = !(p2.getElements().isEmpty()
                && p4.getElements().isEmpty());
        boolean GemInt = !(p.getElements().isEmpty());

        if (harry.getShape().getCenterY() + harry.getShape().getRadiusY() > height || harry.getShape().getCenterY() - harry.getShape().getRadiusY() < 0) {
            intersection = true;
        }
        if (GemInt && gIncreaseOnce) {
            gemScore++;
            score++;
            scoreLabel.setGText("Gem: " + gemScore);
            mediaPlayer2.play();
            mediaPlayer2 = new MediaPlayer(sound2);
            gIncreaseOnce = false;
            obstacle.gem.getGraphics().setOpacity(0);
        }
        if (intersection) {
            mediaPlayer1.play();
            GameFinishedLabel gameOverLabel = new GameFinishedLabel(width / 2, height / 2, window);
            highScore = highScore < score ? score : highScore;
            gemHighScore = gemHighScore < gemScore ? gemScore : gemHighScore;
            gameOverLabel.setText("Tap to retry. Score: " + score + "\nGem: " + gemScore + "\nHighScore: " + highScore + "\nHighest Gem: " + gemHighScore);
            saveHighScore();
            root.getChildren().add(gameOverLabel);
            root.getChildren().get(2).setOpacity(0);
            gameOver = true;
            gameLoop.stop();
            down.setByY(height);
            down.setDuration(Duration.millis(1000));
            rotator.setToAngle(270);
            rotator.setDuration(Duration.millis(200));
            rotator.stop();
            rotator.play();
            down.play();

        }
    }
/***
 * To initialize game each time refreshes content
 */
    void initializeGame() {
        listOfBlocks.clear();
        RainList.clear();
        root.getChildren().clear();
        root.getChildren().addAll(bg1,bg2);

        harry.getGraphics().setTranslateX(100);
        harry.getGraphics().setTranslateY(100);
        scoreLabel.setOpacity(0.8);
        scoreLabel.setText("Score: 0");
        scoreLabel.setGText("Gem: 0");
        root.getChildren().addAll(  scoreLabel);
        root.getChildren().add(harry.getGraphics());
        

        for (int i = 0; i < 5; i++) {
            Rain rain = new Rain();
            rain.setX(Math.random() * width);
            rain.setY(0);
            RainList.add(rain);
            root.getChildren().add(rain);
        }
       for (int i = 0; i < 3; i++) {
            SimpleDoubleProperty y = new SimpleDoubleProperty(0);
            y.set(root.getHeight() * Math.random() / 2);
            Obstacles block;
            if (i != 2) {
                block = new Obstacles(y, root, false, false);
            } else {
                block = new Obstacles(y, root, true, false);
            }
            block.setTranslateX(i * (width / 4 + 300) + 1400);
           listOfBlocks.add(block);
           root.getChildren().add(block);
        }

        score = 0;
        gemScore = 0;
        increaseOne = true;
        gIncreaseOnce = true;

        gameOver = false;
        harry.jumping = false;

        gameLoop.play();

        sound1 = new Media(new File(ops).toURI().toString());
        mediaPlayer1 = new MediaPlayer(sound1);

        sound2 = new Media(new File(gemMusic).toURI().toString());
        mediaPlayer2 = new MediaPlayer(sound2);
    }
   /***
    * To primarily initialize the game on which gameloop is declared
    */
    void initGame() {
        harry = new Harry(harryImgs);
        rotator = new RotateTransition(Duration.millis(500), harry.getGraphics());
        up = new TranslateTransition(Duration.millis(200), harry.getGraphics());
        down = new TranslateTransition(Duration.millis(150), harry.getGraphics());
        up.setInterpolator(Interpolator.LINEAR);
        down.setInterpolator(Interpolator.LINEAR);

        rotator.setCycleCount(1);
        harry.getGraphics().setRotationAxis(Rotate.Z_AXIS);

        gameLoop = new Timeline(new KeyFrame(Duration.millis(level / FPS_30), new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                // updateCounters();
                checkCollisions();
                gameLoop.setRate(1);
                if (listOfBlocks.get(0).getTranslateX() <= -width / 12.3) {
                    listOfBlocks.remove(0);
                    SimpleDoubleProperty y = new SimpleDoubleProperty(0);
                    y.set(root.getHeight() * Math.random() / 2.0);
                    Obstacles block;
                    if (Math.random() < 0.4) {
                        block = new Obstacles(y, root, true, false);
                    } else if (Math.random() > 0.85) {
                        block = new Obstacles(y, root, true, true);
                    } else {
                        block = new Obstacles(y, root, false, false);
                    }
                    block.setTranslateX(listOfBlocks.get(listOfBlocks.size() - 1).getTranslateX() + (width / 3+20));
                    listOfBlocks.add(block);
                    increaseOne = true;
                    gIncreaseOnce = true;
                    //root.getChildren().remove(7);
                    root.getChildren().add(block);
                }
                for (int i = 0; i < listOfBlocks.size(); i++) {
                    if (RainList.get(i).getX() < -RainList.get(i).getImage().getWidth() * RainList.get(i).getScaleX()) {
                        RainList.get(i).setX(RainList.get(i).getX() + width + RainList.get(i).getImage().getWidth() * RainList.get(i).getScaleX());
                    }
                    RainList.get(i).setX(RainList.get(i).getX() - 1);
                    listOfBlocks.get(i).setTranslateX(listOfBlocks.get(i).getTranslateX()-2);
                }
            }
        }));
        gameLoop.setCycleCount(-1);
        initializeGame();
        loadHighScore();
        loadGHighScore();
    }
/**
 * for loading high score
 */
    void loadHighScore() {
        try {
            highScore = new DataInputStream(new FileInputStream("highScore.score")).readInt();
        } catch (Exception e) {
        }
    }
/**
 * for saving high score
 */
    void saveHighScore() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("highScore.score"));
            out.writeInt(score);
            out.flush();
        } catch (Exception e) {
        }
    }
/***
 * for loading gem highScore
 */
    void loadGHighScore() {
        try {
            gemHighScore = new DataInputStream(new FileInputStream("gemHighScore.gemScore")).readInt();
        } catch (Exception e) {
        }
    }
    /***
     * For Saving Gem high Score
     */

    void saveGHighScore() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("gemHighScore.gemScore"));
            out.writeInt(gemScore);
            out.flush();
        } catch (Exception e) {
        }
    }

    @Override
    public void handle(KeyEvent event) {
        if (!gameOver) {
            if (event.getCode() == KeyCode.UP) {
                up.stop();
                down.stop();
                upHarry();
                System.out.println("up");
            }

            if (event.getCode() == KeyCode.DOWN) {

                up.stop();
                down.stop();
                downHarry();
                System.out.println("down");
            }
        }

        System.out.println("ssss");

    }

}
