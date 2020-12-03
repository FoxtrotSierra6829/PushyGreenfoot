import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Box extends Actor {
    boolean worldcreate = true;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("box.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
        }
    }
}
