import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Image extends Actor
{
    boolean worldcreate = true;
    String customimage;
    public Image(String custom) {
        customimage = custom;
    }
    
    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage(customimage));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            worldcreate = false;
        }
        else if (customimage.equalsIgnoreCase("1xbean.png")) {
            if (MyWorld.bean == 0) {
                getWorld().removeObject(this);
            }
        }
    } 
}
