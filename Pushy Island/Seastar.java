import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Seastar extends Movable {

    boolean worldcreate = true;
    boolean inwater = false;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("seastar.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
            MyWorld.seastar++;
        }
        if (inwater == true) {
            getWorld().removeObject(this);
            MyWorld.seastar--;
        } else {
            try {
                if ("a".equals(MyWorld.key) | "left".equals(MyWorld.key)) {
                    if (canmoveleft()) {
                        setLocation(getX() - MyWorld.BlockSize, getY());
                        // Check if now in water
                        List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
                        List boxinwater = getWorld().getObjectsAt(getX(), getY(), Box_in_water.class);
                        if (!water.isEmpty() & boxinwater.isEmpty()) {
                            inwater = true;
                        }
                    }
                }
                if ("d".equals(MyWorld.key) | "right".equals(MyWorld.key)) {
                    if (canmoveright()) {
                        setLocation(getX() + MyWorld.BlockSize, getY());
                        // Check if now in water
                        List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
                        List boxinwater = getWorld().getObjectsAt(getX(), getY(), Box_in_water.class);
                        if (!water.isEmpty() & boxinwater.isEmpty()) {
                            inwater = true;
                        }
                    }
                }
                if ("w".equals(MyWorld.key) | "up".equals(MyWorld.key)) {
                    if (canmoveup()) {
                        setLocation(getX(), getY() - MyWorld.BlockSize);
                        // Check if now in water
                        List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
                        List boxinwater = getWorld().getObjectsAt(getX(), getY(), Box_in_water.class);
                        if (!water.isEmpty() & boxinwater.isEmpty()) {
                            inwater = true;
                        }
                    }
                }
                if ("s".equals(MyWorld.key) | "down".equals(MyWorld.key)) {
                    if (canmovedown()) {
                        setLocation(getX(), getY() + MyWorld.BlockSize);
                        // Check if now in water
                        List water = getWorld().getObjectsAt(getX(), getY(), Water.class);
                        List boxinwater = getWorld().getObjectsAt(getX(), getY(), Box_in_water.class);
                        if (!water.isEmpty() & boxinwater.isEmpty()) {
                            inwater = true;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean canmoveleft() {
        List pushyright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Pushy.class);
        if (!pushyright.isEmpty()) {

            List sandright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Sand.class);
            List grass = getWorld().getObjectsAt(getX(), getY(), Grass.class);
            List grassright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Grass.class);
            List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
            if (!(!sandright.isEmpty() & !grass.isEmpty()) & !(!grassright.isEmpty() & !sand.isEmpty())) {
                List staticleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Static.class);
                List movableleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Movable.class);
                List grassleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Grass.class);
                List springleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), SandHole.class);
                if (getX() < MyWorld.BlockSize * (MyWorld.WorldWidth - 1) & staticleft.isEmpty() & movableleft.isEmpty()
                        & !(!springleft.isEmpty() & MyWorld.spring == true)
                        & (((grassleft.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
                    return true;
                } else {
                    return false;
                }
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

            List sandleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Sand.class);
            List grass = getWorld().getObjectsAt(getX(), getY(), Grass.class);
            List grassleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Grass.class);
            List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
            if (!(!sandleft.isEmpty() & !grass.isEmpty()) & !(!grassleft.isEmpty() & !sand.isEmpty())) {
                List staticright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Static.class);
                List movableright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Movable.class);
                List grassright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Grass.class);
                List springright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), SandHole.class);
                if (getX() < MyWorld.BlockSize * (MyWorld.WorldWidth - 1) & staticright.isEmpty()
                        & !(!springright.isEmpty() & MyWorld.spring == true) & movableright.isEmpty()
                        & (((grassright.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
                    return true;
                } else {
                    return false;
                }
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

            List sandbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Sand.class);
            List grass = getWorld().getObjectsAt(getX(), getY(), Grass.class);
            List grassbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Grass.class);
            List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
            if (!(!sandbelow.isEmpty() & !grass.isEmpty()) & !(!grassbelow.isEmpty() & !sand.isEmpty())) {
                List staticabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Static.class);
                List movableabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Movable.class);
                List grassabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Grass.class);
                List springabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, SandHole.class);
                if (getY() > MyWorld.BlockSize & staticabove.isEmpty() & movableabove.isEmpty()
                        & !(!springabove.isEmpty() & MyWorld.spring == true)
                        & (((grassabove.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
                    return true;
                } else {
                    return false;
                }
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

            List sandabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Sand.class);
            List grass = getWorld().getObjectsAt(getX(), getY(), Grass.class);
            List grassabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Grass.class);
            List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
            if (!(!sandabove.isEmpty() & !grass.isEmpty()) & !(!grassabove.isEmpty() & !sand.isEmpty())) {
                List staticbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Static.class);
                List movablebelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Movable.class);
                List grassbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Grass.class);
                List springbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, SandHole.class);
                if (getY() < MyWorld.BlockSize * (MyWorld.WorldHeight - 1) & staticbelow.isEmpty()
                        & !(!springbelow.isEmpty() & MyWorld.spring == true) & movablebelow.isEmpty()
                        & (((grassbelow.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
