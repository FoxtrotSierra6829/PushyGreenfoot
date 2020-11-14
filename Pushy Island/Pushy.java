import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pushy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pushy extends Actor
{
    /**
     * Act - do whatever the Pushy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Resize Image to fit 75x75 Grid
        GreenfootImage image = getImage();
        image.scale(75, 75);
        setImage(image);
        //Keine Schrägen bewegungen
        if((Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up") | Greenfoot.isKeyDown("S")| Greenfoot.isKeyDown("down"))  &(Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right") | Greenfoot.isKeyDown("A") | Greenfoot.isKeyDown("left"))) {return;}
        //Bewegungen
        else{
        if(Greenfoot.isKeyDown("W") | Greenfoot.isKeyDown("up")) {
            setLocation(getX(),getY()-75);
        }
        if(Greenfoot.isKeyDown("A") | Greenfoot.isKeyDown("left")) {
            setLocation(getX()-75,getY());
        }
        if(Greenfoot.isKeyDown("S") | Greenfoot.isKeyDown("down")) {
            setLocation(getX(),getY()+75);
        }
        if(Greenfoot.isKeyDown("D") | Greenfoot.isKeyDown("right")) {
            setLocation(getX()+75,getY());
        }
    }
}
}
