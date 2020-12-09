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
                if (movableabove()) {
                    if (cangoup(2)) {
                        setLocation(getX(), getY() - MyWorld.BlockSize);
                    }
                }
                // If Pushy can go up, move up by 1
                else if (cangoup(1)) {
                    setLocation(getX(), getY() - MyWorld.BlockSize);
                }
            }
            if (Greenfoot.isKeyDown("A") | Greenfoot.isKeyDown("left")) {
                // turn left
                setRotation(270);

                if (movableleft()) {
                    if (cangoleft(2)) {
                        setLocation(getX() - MyWorld.BlockSize, getY());
                    }
                }
                // If Pushy can go left, move left by 1
                else if (cangoleft(1)) {
                    setLocation(getX() - MyWorld.BlockSize, getY());
                }
            }
            if (Greenfoot.isKeyDown("S") | Greenfoot.isKeyDown("down")) {
                // turn down
                setRotation(180);

                if (movablebelow()) {
                    if (cangodown(2)) {
                        setLocation(getX(), getY() + MyWorld.BlockSize);
                    }
                }
                // If Pushy can go down, move down by 1
                else if (cangodown(1)) {
                    setLocation(getX(), getY() + MyWorld.BlockSize);
                }
            }
            if (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right")) {
                // turn right
                setRotation(90);
                if (movableright()) {
                    if (cangoright(2)) {
                        setLocation(getX() + MyWorld.BlockSize, getY());
                    }
                }
                // If Pushy can go right, move right by 1
                else if (cangoright(1)) {
                    setLocation(getX() + MyWorld.BlockSize, getY());
                }
            }
        }
    }

    public boolean cangoleft(int step) {
        // check for Water
        List waterleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * step, getY(), Water.class);
        // check for House, Stone, Palms
        List staticleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * step, getY(), Static.class);
        // check for Grass
        List grassleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * step, getY(), Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        if (step == 2) {
            List sandstep2 = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * (step - 1), getY(), Sand.class);
            if (getX() > MyWorld.BlockSize * step & waterleft.isEmpty() & staticleft.isEmpty()
                    & ((grassleft.isEmpty() & !sandstep2.isEmpty())) | sandstep2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
        // check if Pushy is further away from world end than one block and there is no
        // water & house left
        else if (getX() > MyWorld.BlockSize * step & waterleft.isEmpty() & staticleft.isEmpty()
                & ((grassleft.isEmpty() & !sand.isEmpty())) | sand.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cangoright(int step) {
        // check for Water
        List waterright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize * step, getY(), Water.class);
        // check for House, Stone, Palms
        List staticright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize * step, getY(), Static.class);
        // check for Grass
        List grassright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize * step, getY(), Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        if (step == 2) {
            List sandstep2 = getWorld().getObjectsAt(getX() + MyWorld.BlockSize * (step - 1), getY(), Sand.class);
            if (getX() < MyWorld.BlockSize * step * (MyWorld.WorldWidth - 1) & waterright.isEmpty()
                    & staticright.isEmpty() & ((grassright.isEmpty() & !sandstep2.isEmpty())) | sandstep2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
        // check if Pushy is further away from world end than one block and there is no
        // water & house right
        if (getX() < MyWorld.BlockSize * step * (MyWorld.WorldWidth - 1) & waterright.isEmpty() & staticright.isEmpty()
                & ((grassright.isEmpty() & !sand.isEmpty())) | sand.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cangoup(int step) {
        // check for Water
        List waterabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * step, Water.class);
        // check for Stone
        List stoneabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * step, Stone.class);
        // check for Palms
        List palmabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * step, Palm.class);
        // check for Grass
        List grassabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * step, Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        if (step == 2) {
            List sandstep2 = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * (step - 1), Sand.class);
            if (getY() > MyWorld.BlockSize * step & waterabove.isEmpty() & stoneabove.isEmpty() & palmabove.isEmpty()
                    & ((grassabove.isEmpty() & !sandstep2.isEmpty())) | sandstep2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
        // check if Pushy is further away from world end than one block and there is no
        // water above
        if (getY() > MyWorld.BlockSize * step & waterabove.isEmpty() & stoneabove.isEmpty() & palmabove.isEmpty()
                & ((grassabove.isEmpty() & !sand.isEmpty())) | sand.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cangodown(int step) {
        // check for Water
        List waterbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize * step, Water.class);
        // check for House, Stone, Palms
        List staticbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize * step, Static.class);
        // check for Grass
        List grassbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize * step, Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        if (step == 2) {
            List sandstep2 = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize * (step - 1), Sand.class);
            if (getY() < MyWorld.BlockSize * step * (MyWorld.WorldHeight - 1) & waterbelow.isEmpty()
                    & staticbelow.isEmpty() & ((grassbelow.isEmpty() & !sandstep2.isEmpty())) | sandstep2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
        // check if Pushy is further away from world end than one block and there is no
        // water & house right
        if (getY() < MyWorld.BlockSize * step * (MyWorld.WorldHeight - 1) & waterbelow.isEmpty() & staticbelow.isEmpty()
                & ((grassbelow.isEmpty() & !sand.isEmpty())) | sand.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean movableleft() {
        List movable = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Movable.class);
        if (!movable.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean movableright() {
        List movable = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Movable.class);
        if (!movable.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean movableabove() {
        List movable = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Movable.class);
        if (!movable.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean movablebelow() {
        List movable = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Movable.class);
        if (!movable.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void turn() {
        turn(15);
    }
}

