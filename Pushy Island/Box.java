import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Box extends Movable {
    boolean worldcreate = true;
    boolean inwater;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
            if (water.isEmpty()) {
                setImage(new GreenfootImage("box.png"));
                inwater = false;
            } else {
                setImage(new GreenfootImage("box_water.png"));
                inwater = true;
            }
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
        }
        if (!inwater) {
            if (Greenfoot.isKeyDown("A") | Greenfoot.isKeyDown("left")) {
                if (canmoveleft()) {
                    setLocation(getX() - MyWorld.BlockSize, getY());
                    List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
                    if (!water.isEmpty()) {
                        worldcreate = true;
                        inwater = true;
                    }
                }
            }
            if (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right")) {
                if (canmoveright()) {
                    setLocation(getX() + MyWorld.BlockSize, getY());
                    List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
                    if (!water.isEmpty()) {
                        worldcreate = true;
                        inwater = true;
                    }
                }
            }
            if (Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up")) {
                if (canmoveup()) {
                    setLocation(getX(), getY() - MyWorld.BlockSize);
                    List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
                    if (!water.isEmpty()) {
                        worldcreate = true;
                        inwater = true;
                    }
                }
            }
            if (Greenfoot.isKeyDown("S") | Greenfoot.isKeyDown("down")) {
                if (canmovedown()) {
                    setLocation(getX(), getY() + MyWorld.BlockSize);
                    List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
                    if (!water.isEmpty()) {
                        worldcreate = true;
                        inwater = true;
                    }
                }
            }
        }
    }

    public boolean canmoveleft() {
        List pushyright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Pushy.class);
        if (!pushyright.isEmpty()) {
            List staticleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Static.class);
            if (staticleft.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean canmoveright() {
        List pushyleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Pushy.class);
        if (!pushyleft.isEmpty()) {
            List staticright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Static.class);
            if (staticright.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean canmoveup() {
        List pushybelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Pushy.class);
        if (!pushybelow.isEmpty()) {
            List staticabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Static.class);
            if (staticabove.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean canmovedown() {
        List pushyabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Pushy.class);
        if (!pushyabove.isEmpty()) {
            List staticbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Static.class);
            if (staticbelow.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
