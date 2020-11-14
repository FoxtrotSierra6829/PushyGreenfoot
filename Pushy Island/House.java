
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

/**
 * Write a description of class House here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class House extends Actor {
    /**
     * Act - do whatever the House wants to do. This method is called whenever the
     * 'Act' or 'Run' button gets pressed in the environment.
     */

    int level = 1;

    public void act() {
        // Resize Image to fit Grid
        setImage(new GreenfootImage("images/haus.jpg"));
        GreenfootImage image = getImage();
        image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
        setImage(image);
        if (checklvl() == true) {
            try {
                nextlevel();
            } catch (Exception e) {
            }
        }

    }

    public void nextlevel() throws Exception {
        // get current level
        try {
            BufferedReader lvl = new BufferedReader(new FileReader("levels/currentlvl.txt"));
            String levelStr = lvl.readLine();
            lvl.close();
            level = Integer.parseInt(levelStr);
        } catch (Exception e) {
            level = 1;
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("levels/currentlvl.txt"));
        if (level > MyWorld.maxlevel) {
            bw.write(String.valueOf(MyWorld.maxlevel));
        } else {
            bw.write(String.valueOf(level + 1));
        }
        bw.close();
        Greenfoot.setWorld(new MyWorld());
        Greenfoot.start();
        return;
    }

    public boolean checklvl() {
        Actor Pushy = getOneIntersectingObject(Pushy.class);
        if (Pushy != null) {
            return true;
        } else {
            return false;
        }
    }
}
