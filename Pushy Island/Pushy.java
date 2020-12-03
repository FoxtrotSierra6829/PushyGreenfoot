import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Pushy extends Actor {

    boolean worldcreate = true;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("pushy.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
        }
        // no diagonal moves
        if (!((Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up") | Greenfoot.isKeyDown("S")
                | Greenfoot.isKeyDown("down"))
                & (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right") | Greenfoot.isKeyDown("A")
                        | Greenfoot.isKeyDown("left")))) {
            /// move
            if (Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up")) {
                // turn up
                setRotation(0);
                // If Pushy can go up, move up by 1
                if (cangoup()) {
                    setLocation(getX(), getY() - MyWorld.BlockSize);
                }
            }
            if (Greenfoot.isKeyDown("A") | Greenfoot.isKeyDown("left")) {
                // turn left
                setRotation(270);
                // If Pushy can go left, move left by 1
                if (cangoleft()) {
                    setLocation(getX() - MyWorld.BlockSize, getY());
                }
            }
            if (Greenfoot.isKeyDown("S") | Greenfoot.isKeyDown("down")) {
                // turn down
                setRotation(180);
                // If Pushy can go down, move down by 1
                if (cangodown()) {
                    setLocation(getX(), getY() + MyWorld.BlockSize);
                }
            }
            if (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right")) {
                // turn right
                setRotation(90);
                // If Pushy can go right, move right by 1
                if (cangoright()) {
                    setLocation(getX() + MyWorld.BlockSize, getY());
                }
            }
        }
    }

    public boolean cangoleft() {
        // check for Water
        List waterleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Water.class);
        // check for House
        List houseleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), House.class);
        // check for Grass
        List grassleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        // check if Pushy is further away from world end than one block and there is no
        // water & house left
        if (getX() > MyWorld.BlockSize & waterleft.isEmpty() & houseleft.isEmpty()
                & (grassleft.isEmpty() & !sand.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cangoright() {
        // check for Water
        List waterright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Water.class);
        // check for House
        List houseright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), House.class);
        // check for Grass
        List grassright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        // check if Pushy is further away from world end than one block and there is no
        // water & house right
        if (getX() < MyWorld.BlockSize * (MyWorld.WorldWidth - 1) & waterright.isEmpty() & houseright.isEmpty()
                & (grassright.isEmpty() & !sand.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cangoup() {
        // check for Water
        List waterabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Water.class);
        // check if Pushy is further away from world end than one block and there is no
        // water above
        if (getY() > MyWorld.BlockSize & waterabove.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cangodown() {
        // check for Water
        List waterbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Water.class);
        // check for House
        List housebelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, House.class);
        // check if Pushy is further away from world end than one block and there is no
        // water & house right
        if (getY() < MyWorld.BlockSize * (MyWorld.WorldHeight - 1) & waterbelow.isEmpty() & housebelow.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
