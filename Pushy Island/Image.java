import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Image here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Image extends Actor
{
    boolean worldcreate = true;

    public void act(String customimage) {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage(customimage));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize/2, MyWorld.BlockSize/2);
            setImage(image);
            worldcreate = false;
        }
    } 
}
