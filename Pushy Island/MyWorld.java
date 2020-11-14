import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class MyWorld extends World {
    static int WorldWidth = 20;
    static int WorldHeight = 12;
    static int SquareSize = 75;
    static int offset = SquareSize / 2;
    int level = 1;
    static int maxlevel = 2;

    // if you change the size here, remember to adapt the super constructor and the
    // arrays
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() throws Exception {
        // Create a new world with 1500x900 cells with a cell size of 1x1 pixels.
        super(1500, 900, 1);
        Greenfoot.setSpeed(30);
        // get current level
        try {
        BufferedReader lvl = new BufferedReader(new FileReader("levels/currentlvl.txt"));
        String levelStr = lvl.readLine();
        lvl.close();
        level = Integer.parseInt(levelStr);}
        catch(Exception e) {
        level = 1;}
        if (level > maxlevel | level < 1) { //Prevent errors, not being able to load a level
            level = 1;
        }
        String levelground = "";
        String levelobjects = "";
        if (level == 1) {
            levelground = "levels/level1_ground.txt";
            levelobjects = "levels/level1_objects.txt";
        } else if (level == 2) {
            levelground = "levels/level2_ground.txt";
            levelobjects = "levels/level2_objects.txt";
        }
        //// DRAWING WORLD
        /// GROUND
        BufferedReader br = new BufferedReader(new FileReader(levelground)); // reading levelX_ground file
        int[] LevelConstructionGround = new int[WorldWidth];
        int y = 0;
        while (y < WorldHeight) { // scan line per line
            y++;
            String line = br.readLine(); // read one line of levels file
            String[] strs = line.trim().split("\\s+"); // split every character to a string
            for (int i = 0; i < WorldWidth; i++) {
                LevelConstructionGround[i] = Integer.parseInt(strs[i]); // String to Integer Array
            }
            for (int x = 0; x < WorldWidth; x++) {
                int Xcoord = (x * SquareSize), Ycoord = ((y-1) * SquareSize);

                if (LevelConstructionGround[x] == 0) {
                    this.addObject(new Water(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionGround[x] == 1) {
                    this.addObject(new Sand(), Xcoord + offset, Ycoord + offset);
                }
            }
        }
        br.close();
        /// GROUND
        BufferedReader br2 = new BufferedReader(new FileReader(levelobjects)); // reading levelX_ground file
        int[] LevelConstructionObjects = new int[WorldWidth];
        y = 0;
        while (y < WorldHeight) { // scan line per line
            y++;
            String line = br2.readLine(); // read one line of levels file
            String[] strs = line.trim().split("\\s+"); // split every character to a string
            for (int i = 0; i < WorldWidth; i++) {
                LevelConstructionObjects[i] = Integer.parseInt(strs[i]); // String to Integer Array
            }
            for (int x = 0; x < WorldWidth; x++) {
                int Xcoord = (x * SquareSize), Ycoord = ((y-1) * SquareSize);

                if (LevelConstructionObjects[x] == 1) {
                    this.addObject(new Pushy(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionObjects[x] == 2) {
                    this.addObject(new House(), Xcoord + offset, Ycoord + offset);
                }
            }
        }
        br2.close();
    }
}
