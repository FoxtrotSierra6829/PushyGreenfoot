import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sand here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Sand extends Actor {
    /**
     * Act - do whatever the Sand wants to do. This method is called whenever the
     * 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Resize Image to fit Grid
        setImage(new GreenfootImage("sand.png"));
        GreenfootImage image = getImage();
        image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
        setImage(image);
    }
}
