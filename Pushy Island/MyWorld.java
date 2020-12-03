
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class MyWorld extends World {

    static int WorldWidth = 20;
    static int WorldHeight = 12;
    static int BlockSize = 75;
    static int offset = BlockSize / 2;
    int levelnr = 1;
    int highscorelevel = 1;
    int maxlevel = 1;
    String gamemode = "Menu";
    String menu = "Menu";
    String pushyisland = "PushyIsland";
    String levelground = "";
    String levelobjects = "";

    public MyWorld() throws Exception {
        // Create a new world with 1500x900 cells with a cell size of 1x1 pixels.
        super(1500, 900, 1);
        Greenfoot.setSpeed(30);
        new File((System.getProperty("user.home") + "/Documents/Pushy")).mkdirs();
        try {
            BufferedReader mode = new BufferedReader(new InputStreamReader(
                    new FileInputStream(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy")));
            gamemode = mode.readLine();
            mode.close();
        } catch (Exception e) {
        }
        if (gamemode.equalsIgnoreCase(pushyisland)) {
            // get maximum level
            int imax = 1;
            while (imax != 0) {
                try {
                    BufferedReader maxlvl = new BufferedReader(new InputStreamReader(
                            new FileInputStream("levels/" + gamemode + "/level" + imax + "_ground.txt")));
                    String levelStr = maxlvl.readLine();
                    maxlvl.close();
                    maxlevel = imax;
                    imax++;
                } catch (Exception e) {
                    imax = 0;
                    new File((System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode)).mkdirs();
                    BufferedWriter max = new BufferedWriter(new FileWriter(
                            System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/max.lvl"));
                    max.write(String.valueOf(maxlevel));
                    max.close();
                }
            }
            // get highscore level
            try {
                BufferedReader lvl = new BufferedReader(new InputStreamReader(new FileInputStream(
                        System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode + "/highscore.lvl")));
                String levelStr = lvl.readLine();
                lvl.close();
                highscorelevel = Integer.parseInt(levelStr);
            } catch (Exception e) {
                new File((System.getProperty("user.home") + "/Documents/Pushy/levels/" + gamemode)).mkdirs();
                highscorelevel = 1;
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
            if (levelnr > maxlevel | levelnr < 1) { // Prevent errors, not being able to load a level
                levelnr = 1;
            }
            if (levelnr > highscorelevel + 1) { // Cannot skip levels further than highscorelevel
                levelnr = highscorelevel + 1;
            }

            levelground = "levels/" + gamemode + "/level" + levelnr + "_ground.txt"; // set reference to level ground
                                                                                     // file
            levelobjects = "levels/" + gamemode + "/level" + levelnr + "_objects.txt"; // set reference to level objects
                                                                                       // file
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
                    int Xcoord = (x * BlockSize), Ycoord = ((y - 1) * BlockSize);

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
                    int Xcoord = (x * BlockSize), Ycoord = ((y - 1) * BlockSize);

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
            setPaintOrder(Text.class, Back.class, Forward.class, Pushy.class, House.class, Box.class, Stone.class,
                    Palm_left.class, Palm_right.class, Grass.class, Sand.class, Water.class);
            Back Back = new Back("<  ", 50, "center"); // new Text(Text, Fontsize, alignment relative to x)
            addObject(Back, BlockSize / 4, (WorldHeight - 1) * BlockSize + offset); // Add Text(center x, center y)
            Forward Forward = new Forward(">  ", 50, "center"); // new Text(Text, Fontsize, alignment relative to x)
            addObject(Forward, (WorldWidth - 1) * BlockSize + BlockSize / 4, (WorldHeight - 1) * BlockSize + offset); // Add
                                                                                                                      // Text(center
                                                                                                                      // x,
                                                                                                                      // center
                                                                                                                      // y)
        }
    }

    /// ACT METHOD
    public void act() {
        if (gamemode.equalsIgnoreCase(menu)) {
            menu();
        }
        if (gamemode.equalsIgnoreCase(pushyisland)) {
            PushyIsland();
        }
    }

    public void menu() {
        Text MenuText = new Text("Press ENTER to continue", 30, "center"); // new Text(Text, Fontsize, alignment
                                                                           // relative to x)
        addObject(MenuText, WorldWidth * BlockSize / 2, WorldHeight * BlockSize / 2); // Add Text(center x, center y)
        if (Greenfoot.isKeyDown("enter")) {
            changemode(pushyisland);
        }
        if (Greenfoot.isKeyDown("escape")) {
            changemode(menu);
        }
    }

    public void PushyIsland() {
        if (Greenfoot.isKeyDown("escape")) {
            changemode(menu);
        }
    }

    public void changemode(String modechange) {
        try {
            new File(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy").delete();
            BufferedWriter mode = new BufferedWriter(
                    new FileWriter(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy"));
            mode.write(modechange);
            mode.close();
            Greenfoot.setWorld(new MyWorld());
            Greenfoot.start();
            return;
        } catch (Exception e) {
        }
    }
}
