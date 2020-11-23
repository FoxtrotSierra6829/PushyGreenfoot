import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Grass here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Grass extends Actor {
    boolean worldcreate = true;

    public void act() {
        if (worldcreate == true) {
            List sandright = getWorld().getObjectsAt(getX() + MyWorld.SquareSize, getY(), Sand.class);
            List sandbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.SquareSize, Sand.class);
            List sandabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.SquareSize, Sand.class);
            List sandleft = getWorld().getObjectsAt(getX() - MyWorld.SquareSize, getY(), Sand.class);

            if (!sandbelow.isEmpty()) {
                if (!sandright.isEmpty()) { // right and below no sand
                    setImage(new GreenfootImage("grass_ru.png"));
                } else if (!sandleft.isEmpty()) { // right and above no sand
                    setImage(new GreenfootImage("grass_lu.png"));
                } else {
                    setImage(new GreenfootImage("grass_down.png"));
                }
            } else if (!sandabove.isEmpty()) {
                if (!sandright.isEmpty()) { // left and below no sand
                    setImage(new GreenfootImage("grass_ro.png"));
                } else if (!sandleft.isEmpty()) { // left and above no sand
                    setImage(new GreenfootImage("grass_lo.png"));
                } else {
                    setImage(new GreenfootImage("grass.png"));
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
