import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SandHole extends Actor {

    boolean worldcreate = true;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("sand_hole.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
        }
        checkbean();
    }

    public void checkbean() {
        // check bean in hole and water on bean
        if (MyWorld.spring) {
            setImage(new GreenfootImage("spring.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
        }
    }

}
