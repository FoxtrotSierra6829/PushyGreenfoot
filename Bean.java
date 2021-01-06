import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
public class Bean extends Actor {

    boolean worldcreate = true;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("bean.png"));
            GreenfootImage image = getImage();
            Actor SandHole = getOneIntersectingObject(SandHole.class); 
            if (SandHole != null){
                image.scale(MyWorld.BlockSize/2, MyWorld.BlockSize/2);
            } else {
                image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            }
            setImage(image);
            worldcreate = false;
        }
        if (checkpushy()){
            MyWorld.bean ++;
            getWorld().removeObject(this);
        }else { 
            checkwater();
        }
    }
    
    public boolean checkpushy() {
        // check if pushy is on bean and missions are completed
        Actor Pushy = getOneIntersectingObject(Pushy.class);
        Actor SandHole = getOneIntersectingObject(SandHole.class); 
        if (Pushy != null & SandHole == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void checkwater(){
        //check bean in hole and water on bean
        Actor SandHole2 = getOneIntersectingObject(SandHole.class); 
        Actor Bottle = getOneIntersectingObject(Bottle.class);
        if (SandHole2 != null & Bottle != null & MyWorld.filled == true){
            getWorld().removeObject(this);
            MyWorld.spring = true;
        }
    }
}
