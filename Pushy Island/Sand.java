import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Sand extends Actor {

    boolean worldcreate = true;

    public void act() {
        // Choose Icon based on surrounding area
        if (worldcreate == true) {
            List waterright = getWorld().getObjectsAt(getX() + MyWorld.SquareSize, getY(), Water.class);
            List waterbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.SquareSize, Water.class);
            List waterabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.SquareSize, Water.class);
            List waterleft = getWorld().getObjectsAt(getX() - MyWorld.SquareSize, getY(), Water.class);
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
            if (((getX() > (MyWorld.WorldWidth - 1) * MyWorld.SquareSize) & !waterabove.isEmpty())
                    | (getY() < MyWorld.SquareSize & !waterright.isEmpty())) {
                // above no world and right water or right no world and above water
                setImage(new GreenfootImage("sand_ro.png"));
            } else if (((getX() > (MyWorld.WorldWidth - 1) * MyWorld.SquareSize) & !waterbelow.isEmpty())
                    | (getY() > (MyWorld.WorldHeight - 1) * MyWorld.SquareSize & !waterright.isEmpty())) {
                // below no world and right water or right no world and below water
                setImage(new GreenfootImage("sand_ru.png"));
            } else if (((getX() < MyWorld.SquareSize) & !waterabove.isEmpty())
                    | (getY() < MyWorld.SquareSize & !waterleft.isEmpty())) {
                // above no world and left water or left no world and above water
                setImage(new GreenfootImage("sand_lo.png"));
            } else if (((getX() < MyWorld.SquareSize) & !waterbelow.isEmpty())
                    | (getY() > (MyWorld.WorldHeight - 1) * MyWorld.SquareSize & !waterleft.isEmpty())) {
                // below no world and left water or left no world and below water
                setImage(new GreenfootImage("sand_lu.png"));
            }
            if (getX() > (MyWorld.WorldWidth - 1) * MyWorld.SquareSize & getY() < MyWorld.SquareSize) {
                // corner top right
                setImage(new GreenfootImage("sand_ro.png"));
            } else if (getX() > (MyWorld.WorldWidth - 1) * MyWorld.SquareSize
                    & getY() > (MyWorld.WorldHeight - 1) * MyWorld.SquareSize) {
                // corner bottom right
                setImage(new GreenfootImage("sand_ru.png"));
            } else if (getX() < MyWorld.SquareSize & getY() < MyWorld.SquareSize) {
                // corner top left
                setImage(new GreenfootImage("sand_lo.png"));
            } else if (getX() < MyWorld.SquareSize & getY() > (MyWorld.WorldHeight - 1) * MyWorld.SquareSize) {
                // corner bottom left
                setImage(new GreenfootImage("sand_lu.png"));
            }
            // Resize Image to fit Grid
            GreenfootImage image = getImage();
            image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
            setImage(image);
            worldcreate = false;
        }

    }
}
