package HarryPotter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Saba & Satabdi
 */


public class Rain extends ImageView {
    public static Image k=new Image(Rain.class.getResourceAsStream("res/cloud5.gif"));
    
   /**
    * Creates Rain
    */
    public Rain() {
        setImage(k);
        //setScaleX(Math.random() / 2.0 + 0.5);
        //setScaleY(2);
        //setOpacity(0.9);
    }

}