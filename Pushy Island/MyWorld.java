import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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

    // if you change the size here, remember to adapt the super constructor and the
    // arrays
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() {
        // Create a new world with 1500x900 cells with a cell size of 1x1 pixels.
        super(1500, 900, 1);
        Greenfoot.setSpeed(30);
        int level = 1;
        if (level == 1) {
            // 0 = None
            // 1 = Sand
            int[] LevelConstructionGround = { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                                            0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
                                            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                                            0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
                                            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                                            0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
                                            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                                            0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
                                            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                                            0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
                                            1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
                                            0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1,
                                        };

            for (int i = 0; i < 240; i++) {
                int j = i + 1; // j starts at 1, goes to 240
                int x = i % WorldWidth, y = (j - i % WorldWidth) / WorldWidth; // remainder of i/WorldWidth is X-Coordinate
                                                                               // (ex. j=1: x=0%20->0 (1. position)///
                                                                               // j=20: x=19%20->19 (20. positon)///
                                                                               // j=21: x=20%20->0 (New Row)
                int Xcoord = (x * 75), Ycoord = (y * 75); // (j-remainder)/20 is Y-Coordinate (ex. j=1: y=(1-0%20)/20->0
                                                          // j=21: y=(1-20%20)/20->1.05->1 (Second Row, remainder is cut by integer

                if (LevelConstructionGround[i] == 1) {
                    this.addObject(new Sand(), Xcoord + offset, Ycoord + offset);
                }

            }
            // 0 = None
            // 1 = Pushy
            int[] LevelConstructionObjects = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                         0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                        };

            for (int i = 0; i < 240; i++) {
                int j = i + 1; // j starts at 1, goes to 240
                int x = i % WorldWidth, y = (j - i % WorldWidth) / WorldWidth; // remainder of i/WorldWidth is X-Coordinate
                                                                               // (ex. j=1: x=0%20->0 (1. position)///
                                                                               // j=20: x=19%20->19 (20. positon)///
                                                                               // j=21: x=20%20->0 (New Row)
                int Xcoord = (x * 75), Ycoord = (y * 75); // (j-remainder)/20 is Y-Coordinate (ex. j=1: y=(1-0%20)/20->0
                                                          // j=21: y=(1-20%20)/20->1.05->1 (Second Row, remainder is cut by integer

                if (LevelConstructionObjects[i] == 1) {
                    this.addObject(new Pushy(), Xcoord + offset, Ycoord + offset);
                }

            }
        }
    }

    public void act() {

    }
}
