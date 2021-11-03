import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BrickWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WinSplash extends World
{
    /**
     * Constructor for objects of class BrickWorld.
     * 
     */
    private final float GRAVITY = 0.0667f;
    private final int SPEED = 3;
    private final float JUMP_FORCE = 5.6f;
    private final int MAX_HEALTH = 3;
    private final int MAX_POWERUP = 3;
    private final Class NEXT_LEVEL = WinSplash.class;
    private final GreenfootSound MUSIC = new GreenfootSound("BackAgain.mp3");
    private static final String bgImageFile= "skyBox.png";
    private static final float scrollSpeed = 0.45f;
    private static final int bgWidth = (new GreenfootImage(bgImageFile)).getWidth();
    private GreenfootImage bgImage, bgBase;
    private float scrollPos = 0;
    public WinSplash()
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1, false); 
         GreenfootImage image = new GreenfootImage("Nice job. You beat my game. hooraaaaaaaay...", 60, Color.RED, null);
        getBackground().drawImage(image, 600-image.getWidth()/2, 400-image.getHeight()/2);
        prepare();
    }
    private void prepare()
    {
        addObject(new Floor(), 600, 800);
        addObject(new Player(SPEED, JUMP_FORCE, GRAVITY, MAX_HEALTH, MAX_POWERUP, NEXT_LEVEL, MUSIC),600,700);
    }
     public void act()
    {
        spawn();
    }
    private void spawn()
    {
        if(Math.random() < 0.0025)
        {
            addObject(new Rock(GRAVITY), Greenfoot.getRandomNumber(1200), -30);
        }
        if(Math.random() < 0.05)
        {
            addObject(new AcidRain(GRAVITY), Greenfoot.getRandomNumber(1200), -30);
        }
    }
}
