import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Palm extends Static {

    boolean worldcreate = true;
    String richtung;

    public Palm(String r) {
        richtung = r;
    }

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            if (richtung == "left") {
                setImage(new GreenfootImage("palm_left.png"));
            } else if (richtung == "right") {
                setImage(new GreenfootImage("palm_right.png"));
            }
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
        }
    }
}
