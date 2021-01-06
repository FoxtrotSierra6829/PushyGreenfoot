import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class Back extends Actor {
    int levelnr = 1;
    String gamemode = "Menu";

    // automatically centered text
    public Back() {
        String text = "<  ";
        int size = 50;
        GreenfootImage img = new GreenfootImage(2, 1);
        img.scale(text.length() * (int) Math.round(size / 2), (int) Math.round(size * 1.5));
        img.setFont(new Font("Calibri", false, false, size));
        img.setColor(new Color(255, 255, 255));
        img.drawString(text, (int) Math.round(size * 0.8), (int) Math.round(size * 1.05));
        setImage(img);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            try {
                previouslevel();
            } catch (Exception e) {
            }
        }
    }

    public void previouslevel() throws Exception {
        try {
            BufferedReader mode = new BufferedReader(new InputStreamReader(
                    new FileInputStream(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy")));
            gamemode = mode.readLine();
            mode.close();
        } catch (Exception e) {
        }
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
        BufferedWriter bw = new BufferedWriter(new FileWriter(
                System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/current.lvl"));

        if (levelnr - 1 < 1) {
            bw.write(String.valueOf(1));
        } else {
            bw.write(String.valueOf(levelnr - 1));
        }
        bw.close();
        Greenfoot.setWorld(new MyWorld());
        Greenfoot.start();
        return;
    }

}
