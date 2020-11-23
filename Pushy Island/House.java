import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class House extends Actor {

    boolean worldcreate = true;
    int levelnr = 1;
    int highscorelevel = 1;
    String gamemode = "Menu";

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("images/house.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.SquareSize, MyWorld.SquareSize);
            setImage(image);
            try {
                BufferedReader mode = new BufferedReader(new InputStreamReader(
                        new FileInputStream(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy")));
                gamemode = mode.readLine();
                mode.close();
            } catch (Exception e) {
            }
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
            BufferedReader lvl = new BufferedReader(new InputStreamReader(new FileInputStream(
                    System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/current.lvl")));
            String levelStr = lvl.readLine();
            lvl.close();
            levelnr = Integer.parseInt(levelStr);
        } catch (Exception e) {
            levelnr = 1;
        }
        try {
            BufferedReader lvl = new BufferedReader(new InputStreamReader(new FileInputStream(
                    System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/highscore.lvl")));
            String levelStr = lvl.readLine();
            lvl.close();
            highscorelevel = Integer.parseInt(levelStr);
        } catch (Exception e) {
            highscorelevel = 0;
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(
                System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/current.lvl"));

        if (levelnr + 1 > MyWorld.maxlevel) {
            bw.write(String.valueOf(MyWorld.maxlevel));
        } else {
            bw.write(String.valueOf(levelnr + 1));
        }
        bw.close();
        if (levelnr > highscorelevel) {
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(
                    System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/highscore.lvl"));
            if (levelnr >= MyWorld.maxlevel) {
                bw2.write(String.valueOf(MyWorld.maxlevel));
            } else {
                bw2.write(String.valueOf(levelnr));
            }
            bw2.close();
        }
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
