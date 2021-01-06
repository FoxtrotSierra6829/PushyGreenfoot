import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class House extends Static {

    boolean worldcreate = true;
    int levelnr = 1;
    int highscorelevel = 1;
    int maxlevel = 1;
    String gamemode = "Menu";
    int homerun = 0;
    Pushy pushy1;
    static int pushyinhouse = 0;

    public House(Pushy Pushy1) {
        pushy1 = Pushy1;
        pushyinhouse = 0;

    }

    public void act() {
        if (worldcreate == true) {
            // Resize Image to fit Grid
            setImage(new GreenfootImage("images/house.png"));
            GreenfootImage image = getImage();
            image.scale(MyWorld.BlockSize, MyWorld.BlockSize);
            setImage(image);
            try {
                BufferedReader mode = new BufferedReader(new InputStreamReader(
                        new FileInputStream(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy")));
                gamemode = mode.readLine();
                mode.close();
            } catch (Exception e) {
            }
            try {
                BufferedReader max = new BufferedReader(new InputStreamReader(new FileInputStream(
                        System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/max.lvl")));
                maxlevel = Integer.parseInt(max.readLine());
                max.close();
            } catch (Exception e) {
            }
            worldcreate = false;
        }
        if (checkhome() == true) {
            pushyinhouse = 1;
            if (homerun < 50) {
                Greenfoot.setSpeed(50);
                homerun++;
                pushy1.turn();
            } else {
                try {
                    nextlevel();
                } catch (Exception e) {
                }
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

        if (levelnr + 1 > maxlevel) {
            bw.write(String.valueOf(maxlevel));
        } else {
            bw.write(String.valueOf(levelnr + 1));
        }
        bw.close();
        if (levelnr > highscorelevel) {
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(
                    System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/highscore.lvl"));
            if (levelnr >= maxlevel) {
                bw2.write(String.valueOf(maxlevel));
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
        // check if pushy is in house and missions are completed
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
        if (MyWorld.seastar == 0){
          return true;
        }else {
             return false; 
          }
        }
    

    public static boolean pushyinhouse() { // used by Pushy.class
        if (pushyinhouse == 1) {
            return true;
        } else {
            return false;
        }
    }
}
