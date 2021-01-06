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
        if ((!((Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up") | Greenfoot.isKeyDown("S")
                | Greenfoot.isKeyDown("down"))
                & (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right") | Greenfoot.isKeyDown("A")
                        | Greenfoot.isKeyDown("left"))))
                & House.pushyinhouse() == false) {
            /// move
            if (Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up")) {
                // turn up
                setRotation(0);
                if (movableabove()) {
                    List bottleabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * 1, Bottle.class);
                    if (!bottleabove.isEmpty()){
                        List waterabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * 2, Water.class);
                        if (waterabove.isEmpty() & cangoup(2)){
                            setLocation(getX(), getY() - MyWorld.BlockSize);
                        }
                    }else if (cangoup(2)) {
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
            
            if (Greenfoot.isKeyDown("Space")){
                if (MyWorld.bean > 0){
                    MyWorld.bean --;
                    getWorld().addObject(new Bean(),getX(), getY());
                }
            }
            
            
        }
        checkspring();
    }

    public boolean cangoleft(int step) {
        // check for Water
        List waterleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * step, getY(), Water.class);
        // check for House, Stone, Palms
        List staticleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * step, getY(), Static.class);
        // check for Box_in_water
        List boxinwaterleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * step, getY(), Box_in_water.class);
        // check for Grass
        List grassleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * step, getY(), Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        if (step == 2) {
            List sandstep2 = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * (step - 1), getY(), Sand.class);
            List movableleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize * step, getY(), Movable.class);
            if (getX() > MyWorld.BlockSize * step & (waterleft.isEmpty() | (!boxinwaterleft.isEmpty()))
                    & staticleft.isEmpty() & movableleft.isEmpty()
                    & (((grassleft.isEmpty() & !sandstep2.isEmpty())) | sandstep2.isEmpty())) {
                return true;
            } else {
                return false;
            }
        }
        // check if Pushy is further away from world end than one block and there is no
        // water & house left
        else if (getX() > MyWorld.BlockSize * step & (waterleft.isEmpty() | (!boxinwaterleft.isEmpty()))
                & staticleft.isEmpty() & (((grassleft.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
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
        // check for Box_in_water
        List boxinwaterright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize * step, getY(), Box_in_water.class);
        // check for Grass
        List grassright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize * step, getY(), Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        if (step == 2) {
            List sandstep2 = getWorld().getObjectsAt(getX() + MyWorld.BlockSize * (step - 1), getY(), Sand.class);
            List movableright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize * step, getY(), Movable.class);
            if (getX() < MyWorld.BlockSize * (MyWorld.WorldWidth - step)
                    & (waterright.isEmpty() | (!boxinwaterright.isEmpty())) & staticright.isEmpty()
                    & movableright.isEmpty()
                    & (((grassright.isEmpty() & !sandstep2.isEmpty())) | sandstep2.isEmpty())) {
                return true;
            } else {
                return false;
            }
        }
        // check if Pushy is further away from world end than one block and there is no
        // water & house right
        if (getX() < MyWorld.BlockSize * (MyWorld.WorldWidth - step)
                & (waterright.isEmpty() | (!boxinwaterright.isEmpty())) & staticright.isEmpty()
                & (((grassright.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
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
        // check for Box_in_water
        List boxinwaterabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * step, Box_in_water.class);
        // check for Grass
        List grassabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * step, Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        if (step == 2) {
            List sandstep2 = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * (step - 1), Sand.class);
            List movableabove = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize * (step - 1), Movable.class);
            if (getY() > MyWorld.BlockSize * step & (waterabove.isEmpty() | (!boxinwaterabove.isEmpty()))
                    & stoneabove.isEmpty() & palmabove.isEmpty() & movableabove.isEmpty()
                    & (((grassabove.isEmpty() & !sandstep2.isEmpty())) | sandstep2.isEmpty())) {
                return true;
            } else {
                return false;
            }
        }
        // check if Pushy is further away from world end than one block and there is no
        // water above
        if (getY() > MyWorld.BlockSize * step & (waterabove.isEmpty() | (!boxinwaterabove.isEmpty()))
                & stoneabove.isEmpty() & palmabove.isEmpty()
                & (((grassabove.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
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
        // check for Box_in_water
        List boxinwaterbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize * step, Box_in_water.class);
        // check for Grass
        List grassbelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize * step, Grass.class);
        // check for Sand
        List sand = getWorld().getObjectsAt(getX(), getY(), Sand.class);
        if (step == 2) {
            List sandstep2 = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize * (step - 1), Sand.class);
            List movablebelow = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize * (step - 1), Movable.class);
            if (getY() < MyWorld.BlockSize * (MyWorld.WorldHeight - step)
                    & (waterbelow.isEmpty() | (!boxinwaterbelow.isEmpty())) & staticbelow.isEmpty()
                    & movablebelow.isEmpty()
                    & (((grassbelow.isEmpty() & !sandstep2.isEmpty())) | sandstep2.isEmpty())) {
                return true;
            } else {
                return false;
            }
        }
        // check if Pushy is further away from world end than one block and there is no
        // water & house right
        if (getY() < MyWorld.BlockSize * (MyWorld.WorldHeight - step)
                & (waterbelow.isEmpty() | (!boxinwaterbelow.isEmpty())) & staticbelow.isEmpty()
                & (((grassbelow.isEmpty() & !sand.isEmpty())) | sand.isEmpty())) {
            return true;
        } else {
            return false;
        }
    }

    // Check for Movable next to Pushy
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

    // Turn pushy (used in House)
    public void turn() {
        turn(15);
    }
    
    public void checkspring(){
        Actor SandHole = getOneIntersectingObject(SandHole.class);
        if (MyWorld.spring == true & SandHole != null){
            int rotation = getRotation();
            if (rotation == 0){
                // check for Water
                List waterup = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Water.class);
                // check for House, Stone, Palms
                List staticup = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Static.class);
                // check for Box_in_water
                List movableup = getWorld().getObjectsAt(getX(), getY() - MyWorld.BlockSize, Movable.class);
                
                // check if Pushy is further away from world end than one block and there is no
                // water & house up
                if (getY() > MyWorld.BlockSize & waterup.isEmpty() & movableup.isEmpty() & staticup.isEmpty()) {
                    turn(-90);
                    move(75);
                    turn(90);
                }
            }
            else if (rotation == 90){
                // check for Water
                List waterright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Water.class);
                // check for House, Stone, Palms
                List staticright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Static.class);
                // check for Box_in_water
                List movableright = getWorld().getObjectsAt(getX() + MyWorld.BlockSize, getY(), Movable.class);
                
                // check if Pushy is further away from world end than one block and there is no
                // water & house right
                if (getX() < MyWorld.BlockSize * (MyWorld.WorldWidth -1) & waterright.isEmpty() & movableright.isEmpty() & staticright.isEmpty()) {
                    turn(-90);
                    move(75);
                    turn(90);
                }
            }
            else if (rotation == 180){
                // check for Water
                List waterdown = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Water.class);
                // check for House, Stone, Palms
                List staticdown = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Static.class);
                // check for Box_in_water
                List movabledown = getWorld().getObjectsAt(getX(), getY() + MyWorld.BlockSize, Movable.class);
                
                // check if Pushy is further away from world end than one block and there is no
                // water & house down
                if (getY() < MyWorld.BlockSize * (MyWorld.WorldHeight -1) & waterdown.isEmpty() & movabledown.isEmpty() & staticdown.isEmpty()) {
                    turn(-90);
                    move(75);
                    turn(90);
                }
            }
            else if (rotation == 270){
                // check for Water
                List waterleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Water.class);
                // check for House, Stone, Palms
                List staticleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Static.class);
                // check for Box_in_water
                List movableleft = getWorld().getObjectsAt(getX() - MyWorld.BlockSize, getY(), Movable.class);
                
                // check if Pushy is further away from world end than one block and there is no
                // water & house left
                if (getX() > MyWorld.BlockSize & waterleft.isEmpty() & movableleft.isEmpty() & staticleft.isEmpty()) {
                    turn(-90);
                    move(75);
                    turn(90);
                }
            }
            
        }
    }
}
