import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class LevelButton extends Actor {
    String gamemode = "PushyIsland";
    String level;
    String gotomode;
    

    // automatically centered text
    public LevelButton(String lvl, int lvlcolor, String go) {
        level = lvl;
        gotomode = go;
        int size = 50;
        GreenfootImage img = new GreenfootImage(2, 1);
        img.scale(level.length() * (int) Math.round(size / 2), (int) Math.round(size * 1.5));
        img.setFont(new Font("Calibri", false, false, size));
        if (lvlcolor==1) {
            img.setColor(new Color(0, 0, 0));
        }
        else if (lvlcolor==2) {
            img.setColor(new Color(255, 0, 0));
        }
        else {
            img.setColor(new Color(128, 128, 128));
        }
        img.drawString((level), (int) Math.round(0), (int) Math.round(size * 1.05));
        setImage(img);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            try {
                loadlevel();
            } catch (Exception e) {
            }
        }
    }

    public void loadlevel() throws Exception {
            BufferedWriter mode = new BufferedWriter(new FileWriter(
                System.getProperty("user.home") + "/Documents/Pushy/mode.pushy"));
            mode.write(gotomode);
            mode.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                System.getProperty("user.home") + "/Documents/Pushy/levels/" + gotomode + "/current.lvl"));
            bw.write(level);
            bw.close();
            Greenfoot.setWorld(new MyWorld());
            Greenfoot.start();
            return;
    }

}
