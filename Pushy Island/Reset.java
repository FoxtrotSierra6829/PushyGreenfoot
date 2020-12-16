import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class Reset extends Actor {

    // automatically centered text
    public Reset() {
            // Set image
            setImage(new GreenfootImage("reset.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize/3, MyWorld.BlockSize/3);
            setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            try {
                Greenfoot.setWorld(new MyWorld());
                Greenfoot.start();
                return;
            } catch (Exception e) {
            }
        }
    }
}