import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class MyWorld extends World {

    static int WorldWidth = 20;
    static int WorldHeight = 12;
    static int SquareSize = 75;
    static int offset = SquareSize / 2;
    int level = 1;
    static int maxlevel = 2;

    public MyWorld() throws Exception {
        // Create a new world with 1500x900 cells with a cell size of 1x1 pixels.
        super(1500, 900, 1);
        Greenfoot.setSpeed(30);
        // get current level
        try {
            BufferedReader lvl = new BufferedReader(new InputStreamReader(
                    new FileInputStream(System.getProperty("user.home") + "/Documents/Pushy/levels/currentlvl.txt")));
            String levelStr = lvl.readLine();
            lvl.close();
            level = Integer.parseInt(levelStr);
        } catch (Exception e) {
            new File((System.getProperty("user.home") + "/Documents/Pushy/levels")).mkdirs();
            level = 1;
        }
        if (level > maxlevel | level < 1) { // Prevent errors, not being able to load a level
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
        BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(levelground))); // reading
                                                                                                      // levelX_ground
                                                                                                      // file
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
                int Xcoord = (x * SquareSize), Ycoord = ((y - 1) * SquareSize);

                if (LevelConstructionGround[x] == 0) {
                    this.addObject(new Water(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionGround[x] == 1) {
                    this.addObject(new Sand(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionGround[x] == 2) {
                    this.addObject(new Grass(), Xcoord + offset, Ycoord + offset);
                }
            }
        }
        br.close();
        /// GROUND
        BufferedReader br2 = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(levelobjects))); // reading
                                                                                                       // levelX_ground
                                                                                                       // file
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
                int Xcoord = (x * SquareSize), Ycoord = ((y - 1) * SquareSize);

                if (LevelConstructionObjects[x] == 1) {
                    this.addObject(new Pushy(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionObjects[x] == 2) {
                    this.addObject(new House(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionObjects[x] == 3) {
                    this.addObject(new Box(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionObjects[x] == 4) {
                    this.addObject(new Stone(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionObjects[x] == 5) {
                    this.addObject(new Palm_left(), Xcoord + offset, Ycoord + offset);
                }
                if (LevelConstructionObjects[x] == 6) {
                    this.addObject(new Palm_right(), Xcoord + offset, Ycoord + offset);
                }
            }
        }
        br2.close();
        // change display order of actors (top -> bottom)
        setPaintOrder(Pushy.class, House.class, Box.class, Stone.class, Palm_left.class, Palm_right.class, Grass.class,
                Sand.class, Water.class);
    }
}
