import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pushy extends Actor {

    boolean worldcreate = true;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("pushy.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
            setImage(image);
            worldcreate = false;
        }
        // no diagonal moves
        if (!((Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up") | Greenfoot.isKeyDown("S")
                | Greenfoot.isKeyDown("down"))
                & (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right") | Greenfoot.isKeyDown("A")
                        | Greenfoot.isKeyDown("left")))) {
            // move
            if (Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up")) {
                // turn up
                setRotation(0);
                // If Pushy is further away than one square, move up by 1
                if (getY() > MyWorld.SquareSize) {
                    setLocation(getX(), getY() - MyWorld.SquareSize);
                }
            }
            if (Greenfoot.isKeyDown("A") | Greenfoot.isKeyDown("left")) {
                // turn left
                setRotation(270);
                // If Pushy is further away than one square, move left by 1
                if (getX() > MyWorld.SquareSize) {
                    setLocation(getX() - MyWorld.SquareSize, getY());
                }
            }
            if (Greenfoot.isKeyDown("S") | Greenfoot.isKeyDown("down")) {
                // turn down
                setRotation(180);
                // If Pushy is further away than one square, move down by 1
                if (getY() < MyWorld.SquareSize * (MyWorld.WorldHeight - 1)) {
                    setLocation(getX(), getY() + MyWorld.SquareSize);
                }
            }
            if (Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right")) {
                // turn right
                setRotation(90);
                // If Pushy is further away than one square, move right by 1
                if (getX() < MyWorld.SquareSize * (MyWorld.WorldWidth - 1)) {
                    setLocation(getX() + MyWorld.SquareSize, getY());
                }
            }
        }
    }
}
