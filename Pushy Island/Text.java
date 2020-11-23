import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Text extends Actor {
    // automatically centered text
    public Text(String text, int size, String alignment) {
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

    // change text
    public void setText(String text, int size) {
        GreenfootImage img = getImage();
        img.clear();
        img.scale(text.length() * (int) Math.round(size / 2), (int) Math.round(size * 1.5));
        img.setFont(new Font("Calibri", false, false, size));
        img.drawString(text, (int) Math.round(size * 0.8), (int) Math.round(size * 1.05));
    }

}
