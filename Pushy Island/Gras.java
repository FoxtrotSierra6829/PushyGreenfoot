import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Gras here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gras extends Actor
{
    boolean worldcreate = true;
    
    public void act() 
    {
        if (worldcreate == true) {
            List sandright = getWorld().getObjectsAt(getX() + MyWorld.SquareSize, getY(), Sand.class);
            List sandbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.SquareSize, Sand.class);
            List sandabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.SquareSize, Sand.class);
            List sandleft = getWorld().getObjectsAt(getX() - MyWorld.SquareSize, getY(), Sand.class);
            
            if (!sandbelow.isEmpty()) {
                if (!sandright.isEmpty()) { // right and below no sand
                    setImage(new GreenfootImage("grün_ru.jpg"));
                } else if (!sandleft.isEmpty()) { // right and above no sand
                    setImage(new GreenfootImage("grün_lu.jpg"));
                } else {
                    setImage(new GreenfootImage("grün_unten.jpg"));
                }
            } else if (!sandabove.isEmpty()) {
                if (!sandright.isEmpty()) { // left and below no sand
                    setImage(new GreenfootImage("grün_ro.jpg"));
                } else if (!sandleft.isEmpty()) { // left and above no sand
                    setImage(new GreenfootImage("grün_lo.jpg"));
                } else {
                    setImage(new GreenfootImage("grün.jpg"));
                }
            } else { // if normal
                setImage(new GreenfootImage("grass.png"));
            }
            
                                  
            // Resize Image to fit Grid
            GreenfootImage image = getImage();
            image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
            setImage(image);
            worldcreate = false;
        }
        
    }    
}
