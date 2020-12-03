import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;

public class Forward extends Actor {
    int levelnr = 1;
    int highscorelevel = 1;
    int maxlevel = 1;
    String gamemode = "Menu";

    // automatically centered text
    public Forward(String text, int size, String alignment) {
        if (alignment.equalsIgnoreCase("center")) {
            GreenfootImage img = new GreenfootImage(2, 1);
            img.scale(text.length() * (int) Math.round(size / 2), (int) Math.round(size * 1.5));
            img.setFont(new Font("Calibri", false, false, size));
            img.drawString(text, (int) Math.round(size * 0.8), (int) Math.round(size * 1.05));
            setImage(img);
        }
        if (alignment.equalsIgnoreCase("left")) {
            GreenfootImage img = new GreenfootImage(2, 1);
            img.scale(text.length() * size, (int) Math.round(size * 1.5));
            img.setFont(new Font("Calibri", false, false, size));
            img.drawString(text, (int) Math.round(text.length() * size / 2), (int) Math.round(size * 1.05));
            setImage(img);
        }
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            try {
                nextlevel();
            } catch (Exception e) {
            }
        }
    }

    public void nextlevel() throws Exception {
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
            BufferedReader lvl2 = new BufferedReader(new InputStreamReader(new FileInputStream(
                    System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/highscore.lvl")));
            String levelStr = lvl2.readLine();
            lvl2.close();
            highscorelevel = Integer.parseInt(levelStr);
        } catch (Exception e) {
            highscorelevel = 0;
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(
                System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/current.lvl"));

        if (levelnr + 1 <= maxlevel & levelnr + 1 <= highscorelevel + 1) {
            bw.write(String.valueOf(levelnr + 1));
        } else {
            bw.write(String.valueOf(levelnr));
        }
        bw.close();
        Greenfoot.setWorld(new MyWorld());
        Greenfoot.start();
        return;
    }

}
