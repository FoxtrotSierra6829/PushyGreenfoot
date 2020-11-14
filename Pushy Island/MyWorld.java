import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
int offset=75/2;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1500, 900, 1);
        Greenfoot.setSpeed(32);
        int level=1;
    if (level==1) {
        // 0 = Nichts
        // 1 = Sand
        int[] LevelAufbauUntergrund={
            1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,
            0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,
            1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,
            0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,
            1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,
            0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,
            1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,
            0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,
            1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,
            0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,
            1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,
            0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,
           };
            
           
           
           for(int i=0;i<240;i++){
            int j=i+1; //j fängt bei 1 an, und geht bis 240
            int x=i%20,y=(j-i%20)/20;       //Rest von i/20 ist X-Koordinate (bsp. j=1: x=0%20->0 (1. Position)/// j=20: x=19%20->19 (20. Positon)/// j=21: x=20%20->0 (Neue Zeile)
            int xkoord=(x*75),ykoord=(y*75); // (j-Rest)/20 ist Y-Koordinate (bsp. j=1: y=(1-0%20)/20->0 /// j=21: y=(1-20%20)/20->1.05->1 (Zweite Zeile, Rest wird von int Abgeschnitten
            
            if(LevelAufbauUntergrund[i]==1){
               this.addObject(new Sand(),xkoord+offset ,ykoord+offset);
            }
            
           }
           // 0 = Nichts
           // 1 = Pushy
           int[] LevelAufbauObjekte={
            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
           };
            
           
           
           for(int i=0;i<240;i++){
            int j=i+1; //j fängt bei 1 an, und geht bis 240
            int x=i%20,y=(j-i%20)/20;       //Rest von i/20 ist X-Koordinate (bsp. j=1: x=0%20->0 (1. Position)/// j=20: x=19%20->19 (20. Positon)/// j=21: x=20%20->0 (Neue Zeile)
            int xkoord=(x*75),ykoord=(y*75); // (j-Rest)/20 ist Y-Koordinate (bsp. j=1: y=(1-0%20)/20->0 /// j=21: y=(1-20%20)/20->1.05->1 (Zweite Zeile, Rest wird von int Abgeschnitten
            
            if(LevelAufbauObjekte[i]==1){
               this.addObject(new Pushy(),xkoord+offset ,ykoord+offset);
            }
            
           }
    }
    }
    public void act()
    {
       
    }
}
