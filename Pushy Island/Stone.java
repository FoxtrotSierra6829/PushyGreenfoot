import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stone extends Actor
{
    boolean worldcreate = true;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("stein_grün.jpg"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
            setImage(image);
            worldcreate = false;
        }
    }   
}
