import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
public class Bottle extends Movable {

    boolean worldcreate = true;
    boolean inwaterhole = false;
    
    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("bottle.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
        }
        if (inwaterhole == true){
            setImage(new GreenfootImage("bottle_water.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            MyWorld.filled = true;
        }
        if ((!((Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up") | Greenfoot.isKeyDown("S")
                | Greenfoot.isKeyDown("down"))
                & (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right") | Greenfoot.isKeyDown("A")
                        | Greenfoot.isKeyDown("left"))))) {
            if (Greenfoot.isKeyDown("A") | Greenfoot.isKeyDown("left")) {
                if (canmoveleft()) {
                    setLocation(getX() - MyWorld.BlockSize, getY());
                    // Check if now in water
                    List bottleinwaterhole = getWorld().getObjectsAt(getX(), getY(), WaterHole.class);
                    if (!bottleinwaterhole.isEmpty()) {
                        inwaterhole = true;
                    }
                }
            }
            if (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right")) {
                if (canmoveright()) {
                    setLocation(getX() + MyWorld.BlockSize, getY());
                    // Check if now in water
                    List bottleinwaterhole = getWorld().getObjectsAt(getX(), getY(), WaterHole.class);
                    if (!bottleinwaterhole.isEmpty()) {
                        inwaterhole = true;
                    }
                }
            }
            if (Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up")) {
                if (canmoveup()) {
                    setLocation(getX(), getY() - MyWorld.BlockSize);
                    // Check if now in water
                    List bottleinwaterhole = getWorld().getObjectsAt(getX(), getY(), WaterHole.class);
                    if (!bottleinwaterhole.isEmpty()) {
                        inwaterhole = true;
                    }
                }
            }
            if (Greenfoot.isKeyDown("S") | Greenfoot.isKeyDown("down")) {
                if (canmovedown()) {
                    setLocation(getX(), getY() + MyWorld.BlockSize);
                    // Check if now in water
                    List bottleinwaterhole = getWorld().getObjectsAt(getX(), getY(), WaterHole.class);
                    if (!bottleinwaterhole.isEmpty()) {
                        inwaterhole = true;
                    }
                }
            }
           
        }
        checkbean();
    }

    public boolean canmoveleft() {
        List pushyright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Pushy.class);
        if (!pushyright.isEmpty()) {
            List staticleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Static.class);
            List movableleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Movable.class);
            List grassleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Grass.class);
            List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
            List water = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Water.class);
            List boxinwater = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Box_in_water.class);
            if (getX() > MyWorld.BlockSize & staticleft.isEmpty() & movableleft.isEmpty()
                   & (water.isEmpty() | !water.isEmpty() & !boxinwater.isEmpty()) & (((grassleft.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
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
            List movableright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Movable.class);
            List grassright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Grass.class);
            List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
            List water = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Water.class);
            List boxinwater = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Box_in_water.class);
            if (getX() < MyWorld.BlockSize * (MyWorld.WorldWidth - 1) & staticright.isEmpty() & movableright.isEmpty()
                   & (water.isEmpty() | !water.isEmpty() & !boxinwater.isEmpty()) & (((grassright.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
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
            List movableabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Movable.class);
            List grassabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Grass.class);
            List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
            List water = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Water.class);
            List boxinwater = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Box_in_water.class);
            if (getY() > MyWorld.BlockSize & staticabove.isEmpty() & movableabove.isEmpty()
                    & (water.isEmpty() | !water.isEmpty() & !boxinwater.isEmpty()) & (((grassabove.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
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
            List movablebelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Movable.class);
            List grassbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Grass.class);
            List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
            List water = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Water.class);
            List boxinwater = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Box_in_water.class);
            if (getY() < MyWorld.BlockSize * (MyWorld.WorldHeight - 1) & staticbelow.isEmpty() & movablebelow.isEmpty()
                   & (water.isEmpty() | !water.isEmpty() & !boxinwater.isEmpty()) & (((grassbelow.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void checkbean(){
        //check bean in hole and water on bean
        if (MyWorld.spring){
            getWorld().removeObject(this);
        }
    }
}

