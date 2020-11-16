import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Water extends Actor {

    boolean worldcreate = true;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("water.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
            setImage(image);
            worldcreate = false;
        }
    }
}
