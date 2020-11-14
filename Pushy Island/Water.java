import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Water here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Water extends Actor {
    /**
     * Act - do whatever the Water wants to do. This method is called whenever the
     * 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Resize Image to fit Grid
        setImage(new GreenfootImage("wasser.jpg"));
        GreenfootImage image = getImage();
        image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
        setImage(image);
    }
}
