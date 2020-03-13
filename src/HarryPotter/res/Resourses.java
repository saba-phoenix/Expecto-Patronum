/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HarryPotter.res;

import javafx.scene.image.Image;

public class Resourses {

        public Image birdImgs;

    public Resourses() {
        try {
               birdImgs= new Image(getClass().getResourceAsStream("birdFrame0" + ".png"));

           

        } catch (Exception e) {
            System.out.println("Problem in loading resourses");
        }
    }

}