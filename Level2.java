import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BrickWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends World
{
    private final float GRAVITY = 0.0667f;
    private final int SPEED = 3;
    private final float JUMP_FORCE = 5.6f;
    private final int MAX_HEALTH = 3;
    private final int MAX_POWERUP = 3;
    private final Class NEXT_LEVEL = WinSplash.class;
    private final GreenfootSound MUSIC = new GreenfootSound("incompetech_tribal.mp3");
    private static final String bgImageFile= "space1.jpg";
    private static final float scrollSpeed = 0.45f;
    private static final int bgWidth = (new GreenfootImage(bgImageFile)).getWidth();
    private GreenfootImage bgImage, bgBase;
    private float scrollPos = 0;
    public Level2()
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1, false);
        bgImage = new GreenfootImage(getBackground());
        bgBase = new GreenfootImage(bgWidth, getHeight());
        bgBase.drawImage(bgImage, 0, 0);
        prepare();
    }
    
    public void act()
    {
        spawn();
        scroll();
    }
    
    private void prepare()
    {
        setPaintOrder(Player.class, Platform.class, Obstacle.class, Collectable.class,
        Door.class, HUD.class);
        Player player = new Player(SPEED, JUMP_FORCE, GRAVITY, MAX_HEALTH, MAX_POWERUP, NEXT_LEVEL, MUSIC);
        addObject(player,25,755);
        addObject(new Door(),1175,45);
        addObject(new Floor(), 600, 800);
        addObject(new BrickWall(), 800, 650);
        addObject(new BrickWall(), 200, 400);
        addObject(new SmBrickWall(), 1000, 200);
        addObject(new SmBrickWall(), 600, 450);
        addObject(new SmBrickWall(), 600, 200);
        addObject(new SmBrickWall(), 800, 500);
        addObject(new SmBrickWall(), 50, 600);
        addObject(new TrapDoor(GRAVITY), 1000, 400);
        addObject(new TrapDoor(GRAVITY), 50, 200);
        addObject(new Gem(), 50, 150);
        addObject(new Gem(), 50, 550);
        addObject(new Gem(), 1000, 350);
    }
    
    private void spawn()
    {
        if(Math.random() < 0.005)
        {
            addObject(new Rock(GRAVITY), Greenfoot.getRandomNumber(1200), -30);
        }
        if(Math.random() < 0.001)
        {
            addObject(new AcidRain(GRAVITY), Greenfoot.getRandomNumber(1200), -30);
        }
    }
    
    private void scroll()
    {
        scrollPos -= scrollSpeed;
        while(scrollSpeed > 0 && scrollPos < -bgWidth) 
        {
          scrollPos += bgWidth;  
        }
        while(scrollSpeed < 0 && scrollPos > 0) 
        {
          scrollPos -= bgWidth;  
        }
        GreenfootImage bg = getBackground();
        bg.drawImage(bgBase, Math.round(scrollPos), 0);
        bg.drawImage(bgImage, Math.round(scrollPos) + bgWidth, 0);
    }
}
