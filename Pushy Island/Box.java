import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Box here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Box extends Actor
{
    /**
     * Act - do whatever the Box wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Resize Image to fit Grid
        setImage(new GreenfootImage("box.png"));
        GreenfootImage image = getImage();
        image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
        setImage(image);
    }    
}
