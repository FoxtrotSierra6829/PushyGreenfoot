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
    static int seastar;
    static int bean;
    static boolean filled = false;
    static boolean spring = false;
    int levelnr = 1;
    int highscorelevel = 1;
    int maxlevel = 1;
    String gamemode = "Menu";
    String gotomode;
    String menu = "Menu";
    String pushyisland = "PushyIsland";
    String win = "win";
    String levelground = "";
    String levelobjects = "";
    String levelname = "";
    Back Back = new Back(); // new Back

    public MyWorld() throws Exception {
        // Create a new world with 1500x900 cells with a cell size of 1x1 pixels.
        super(1500, 900, 1);
        Greenfoot.setSpeed(34);
        filled = false;
        spring = false;
        bean = 0;
        seastar = 0;
        new File((System.getProperty("user.home") + "/Documents/Pushy")).mkdirs(); // Create Pushy User folder if not
                                                                                   // existing
        // get current game mode
        try {
            BufferedReader mode = new BufferedReader(new InputStreamReader(
                    new FileInputStream(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy")));
            gamemode = mode.readLine();
            mode.close();
        } catch (Exception e) {
        }

        ///// MENU (Pushy Island)
        if (gamemode.equalsIgnoreCase(menu)) {
            gotomode = pushyisland;
            setBackground("pushy_island.jpg");
            // get maximum level
            int imax = 1;
            while (imax != 0) {
                try {
                    BufferedReader maxlvl = new BufferedReader(new InputStreamReader(getClass().getClassLoader()
                            .getResourceAsStream("levels/" + pushyisland + "/level" + imax + "_ground.txt")));
                    String levelStr = maxlvl.readLine();
                    maxlvl.close();
                    maxlevel = imax;
                    imax++;
                } catch (Exception e) {
                    imax = 0;
                    new File((System.getProperty("user.home") + "/Documents/Pushy/levels/" + gotomode)).mkdirs();
                    BufferedWriter max = new BufferedWriter(new FileWriter(
                            System.getProperty("user.home") + "/Documents/Pushy/levels/" + gotomode + "/max.lvl"));
                    max.write(String.valueOf(maxlevel));
                    max.close();
                }
            }
            // get highscore level
            try {
                BufferedReader lvl = new BufferedReader(new InputStreamReader(new FileInputStream(
                        System.getProperty("user.home") + "/Documents/Pushy/levels/" + gotomode + "/highscore.lvl")));
                String levelStr = lvl.readLine();
                lvl.close();
                highscorelevel = Integer.parseInt(levelStr);
            } catch (Exception e) {
                new File((System.getProperty("user.home") + "/Documents/Pushy/levels/" + gotomode)).mkdirs();
                highscorelevel = 0;
            }
            // get current level
            try {
                BufferedReader lvl = new BufferedReader(new InputStreamReader(new FileInputStream(
                        System.getProperty("user.home") + "/Documents/Pushy/levels/" + gotomode + "/current.lvl")));
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

            // add available level numbers to choose what level to enter
            int i = 1;
            int y = 5;
            while (y < WorldHeight - 2) {
                int x = 1;
                while (x < WorldWidth) {
                    if (i <= highscorelevel + 1 & i <= maxlevel) {
                        int Xcoord = (x * BlockSize);
                        int Ycoord = ((y - 1) * BlockSize);
                        int lvlcolor;
                        if (i == levelnr) {
                            lvlcolor = 2;

                        } else {
                            if (i <= highscorelevel) {
                                lvlcolor = 1;
                            } else {
                                lvlcolor = 0;
                            }
                        }
                        addObject(new LevelButton(String.valueOf(i), lvlcolor, gotomode), Xcoord + offset,
                                Ycoord + offset);
                        i++;
                    }
                    x++;
                }
                y++;
            }
        }

        ///// PUSHY ISLAND
        else if (gamemode.equalsIgnoreCase(pushyisland)) {
            // get maximum level
            setBackground("pushy_island_loading.jpg");
            int imax = 1;
            while (imax != 0) {
                try {
                    BufferedReader maxlvl = new BufferedReader(new InputStreamReader(getClass().getClassLoader()
                            .getResourceAsStream("levels/" + gamemode + "/level" + imax + "_ground.txt")));
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
                highscorelevel = 0;
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

            /// OBJECTS
            BufferedReader br2 = new BufferedReader(
                    new InputStreamReader(getClass().getClassLoader().getResourceAsStream(levelobjects))); // reading
                                                                                                           // levelX_ground
                                                                                                           // file
            int[] LevelConstructionObjects = new int[WorldWidth];
            y = 0;
            Pushy Pushy1 = new Pushy();
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
                        this.addObject(Pushy1, Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 2) {
                        this.addObject(new House(Pushy1), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 3) {
                        this.addObject(new Box(), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 4) {
                        this.addObject(new Stone(), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 5) {
                        this.addObject(new Palm("left"), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 6) {
                        this.addObject(new Palm("right"), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 7) {
                        this.addObject(new Seastar(), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 8) {
                        this.addObject(new WaterHole(), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 9) {
                        this.addObject(new SandHole(), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 10) {
                        this.addObject(new Bottle(), Xcoord + offset, Ycoord + offset);
                    }
                    if (LevelConstructionObjects[x] == 11) {
                        this.addObject(new Bean(), Xcoord + offset, Ycoord + offset);
                    }
                }
            }
            br2.close();
            // change display order of actors (top -> bottom)
            setPaintOrder(Text.class, Image.class, Back.class, Forward.class, Reset.class, Pushy.class, House.class,
                    Box.class, Seastar.class, Bottle.class, Bean.class, Stone.class, Palm.class, Box_in_water.class,
                    WaterHole.class, SandHole.class, Grass.class, Sand.class, Water.class);

            // add back, forward and reset button
            addObject(new Back(), BlockSize / 4, (WorldHeight - 1) * BlockSize + offset); // new Back
            addObject(new Forward(), (WorldWidth - 1) * BlockSize + BlockSize / 4,
                    (WorldHeight - 1) * BlockSize + offset); // new Forward
            addObject(new Reset(), (WorldWidth - 1) * BlockSize + offset + BlockSize / 4, BlockSize / 4); // new Reset

            // show current level + name
            try {
                BufferedReader lvlname = new BufferedReader(new InputStreamReader(getClass().getClassLoader()
                        .getResourceAsStream("levels/" + gamemode + "/level" + levelnr + "_name.txt")));
                levelname = lvlname.readLine();
                lvlname.close();
            } catch (Exception e) {
            }
            Text Levelname = new Text(levelname, 30, "left", "white"); // new Text(Text, Fontsize, alignment relative to
                                                                       // x, color)
            addObject(Levelname, BlockSize / 4, BlockSize / 4); // Add Text(center x, center y)
        } else if (gamemode.equalsIgnoreCase(win)) {
            setBackground("pushy_island_win.jpg");
        }

    }

    /// ACT METHOD
    public void act() {
        // execute menu funcions when in menu
        if (gamemode.equalsIgnoreCase(menu)) {
            menu();
        }
        // play Pushy Island
        if (gamemode.equalsIgnoreCase(pushyisland)) {
            PushyIsland();
        }
        // win
        if (gamemode.equalsIgnoreCase(win)) {
            win();
            try {
                BufferedWriter mode = new BufferedWriter(
                        new FileWriter(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy"));
                mode.write(menu);
                mode.close();
            } catch (Exception e) {
            }
        }
    }

    public void menu() {
        // enter changes to Pushy Island/..
        if (Greenfoot.isKeyDown("enter")) {
            changemode(gotomode);
        }
        // escape changes to menu (currently: == reload)
        if (Greenfoot.isKeyDown("escape")) {
            changemode(menu);
        }
    }

    public void PushyIsland() {
        // escape changes to menu
        if (Greenfoot.isKeyDown("escape")) {
            changemode(menu);
        }
    }

    public void win() {
        // enter changes to menu
        if (Greenfoot.isKeyDown("enter")) {
            changemode(menu);
        }
        // escape changes to menu
        if (Greenfoot.isKeyDown("escape")) {
            changemode(menu);
        }
    }

    public static void changemode(String modechange) {
        try {
            // change mode file
            new File(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy").delete();
            BufferedWriter mode = new BufferedWriter(
                    new FileWriter(System.getProperty("user.home") + "/Documents/Pushy/mode.pushy"));
            mode.write(modechange);
            mode.close();
            // reload
            Greenfoot.setWorld(new MyWorld());
            Greenfoot.start();
            return;
        } catch (Exception e) {
        }
    }
}