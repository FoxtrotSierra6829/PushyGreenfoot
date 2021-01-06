import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Sand extends Actor {

    boolean worldcreate = true;

    public void act() {
        // Choose Icon based on surrounding area
        if (worldcreate == true) {
            List waterright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Water.class);
            List waterbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Water.class);
            List waterabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Water.class);
            List waterleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Water.class);
            if (!waterright.isEmpty()) {

                if (!waterbelow.isEmpty()) { // right and below no water
                    setImage(new GreenfootImage("sand_ru.png"));
                } else if (!waterabove.isEmpty()) { // right and above no water
                    setImage(new GreenfootImage("sand_ro.png"));
                } else {
                    setImage(new GreenfootImage("sand.png"));
                }
            } else if (!waterleft.isEmpty()) {
                if (!waterbelow.isEmpty()) { // left and below no water
                    setImage(new GreenfootImage("sand_lu.png"));
                } else if (!waterabove.isEmpty()) { // left and above no water
                    setImage(new GreenfootImage("sand_lo.png"));
                } else {
                    setImage(new GreenfootImage("sand.png"));
                }
            } else { // if normal
                setImage(new GreenfootImage("sand.png"));
            }
            if (((getX() > (MyWorld.WorldWidth - 1) * MyWorld.BlockSize) & !waterabove.isEmpty())
                    | (getY() < MyWorld.BlockSize & !waterright.isEmpty())) {
                // above no world and right water or right no world and above water
                setImage(new GreenfootImage("sand_ro.png"));
            } else if (((getX() > (MyWorld.WorldWidth - 1) * MyWorld.BlockSize) & !waterbelow.isEmpty())
                    | (getY() > (MyWorld.WorldHeight - 1) * MyWorld.BlockSize & !waterright.isEmpty())) {
                // below no world and right water or right no world and below water
                setImage(new GreenfootImage("sand_ru.png"));
            } else if (((getX() < MyWorld.BlockSize) & !waterabove.isEmpty())
                    | (getY() < MyWorld.BlockSize & !waterleft.isEmpty())) {
                // above no world and left water or left no world and above water
                setImage(new GreenfootImage("sand_lo.png"));
            } else if (((getX() < MyWorld.BlockSize) & !waterbelow.isEmpty())
                    | (getY() > (MyWorld.WorldHeight - 1) * MyWorld.BlockSize & !waterleft.isEmpty())) {
                // below no world and left water or left no world and below water
                setImage(new GreenfootImage("sand_lu.png"));
            }
            if (getX() > (MyWorld.WorldWidth - 1) * MyWorld.BlockSize & getY() < MyWorld.BlockSize) {
                // corner top right
                setImage(new GreenfootImage("sand_ro.png"));
            } else if (getX() > (MyWorld.WorldWidth - 1) * MyWorld.BlockSize
                    & getY() > (MyWorld.WorldHeight - 1) * MyWorld.BlockSize) {
                // corner bottom right
                setImage(new GreenfootImage("sand_ru.png"));
            } else if (getX() < MyWorld.BlockSize & getY() < MyWorld.BlockSize) {
                // corner top left
                setImage(new GreenfootImage("sand_lo.png"));
            } else if (getX() < MyWorld.BlockSize & getY() > (MyWorld.WorldHeight - 1) * MyWorld.BlockSize) {
                // corner bottom left
                setImage(new GreenfootImage("sand_lu.png"));
            }
            // Resize Image to fit Grid
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
        }

    }
}
