import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class House extends Actor {

    boolean worldcreate = true;
    int level = 1;

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("images/house.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
            setImage(image);
            worldcreate = false;
        }
        if (checkhome() == true) {
            try {
                nextlevel();
            } catch (Exception e) {
            }
        }

    }

    public void nextlevel() throws Exception {
        // get current level
        try {
            BufferedReader lvl = new BufferedReader(new InputStreamReader(
                    new FileInputStream(System.getProperty("user.home") + "/Documents/Pushy/levels/currentlvl.txt")));
            String levelStr = lvl.readLine();
            lvl.close();
            level = Integer.parseInt(levelStr);
        } catch (Exception e) {
            level = 1;
        }
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(System.getProperty("user.home") + "/Documents/Pushy/levels/currentlvl.txt"));

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

    public boolean checkhome() {
        Actor Pushy = getOneIntersectingObject(Pushy.class);
        if (Pushy != null) {
            if (missioncomplete() == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean missioncomplete() {
        return true;
    }
}
