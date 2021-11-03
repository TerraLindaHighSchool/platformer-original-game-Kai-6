import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BrickWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends World
{
    private final float GRAVITY = 0.0667f;
    private final int SPEED = 3;
    private final float JUMP_FORCE = 5.6f;
    private final int MAX_HEALTH = 3;
    private final int MAX_POWERUP = 3;
    private final Class NEXT_LEVEL = WinSplash.class;
    private final GreenfootSound MUSIC = new GreenfootSound("May.mp3");
    private static final String bgImageFile= "space1.jpg";
    private static final float scrollSpeed = 0.45f;
    private static final int bgWidth = (new GreenfootImage(bgImageFile)).getWidth();
    private GreenfootImage bgImage, bgBase;
    private float scrollPos = 0;
    public Level3()
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
        SmBrickWall smBrickWall = new SmBrickWall();
        addObject(smBrickWall,60,661);
        SmBrickWall smBrickWall2 = new SmBrickWall();
        addObject(smBrickWall2,495,572);
        SmBrickWall smBrickWall3 = new SmBrickWall();
        addObject(smBrickWall3,59,455);
        SmBrickWall smBrickWall4 = new SmBrickWall();
        addObject(smBrickWall4,497,360);
        SmBrickWall smBrickWall5 = new SmBrickWall();
        addObject(smBrickWall5,63,239);
        SmBrickWall smBrickWall6 = new SmBrickWall();
        addObject(smBrickWall6,491,133);
        BrickWall brickWall = new BrickWall();
        addObject(brickWall,957,782);
        TrapDoor smBrickWall7 = new TrapDoor(GRAVITY);
        addObject(smBrickWall7,857,254);
        Gem gem = new Gem();
        addObject(gem,857,200);
        Gem gem2 = new Gem();
        addObject(gem2,963,739);
        Bomb gem3 = new Bomb(GRAVITY);
        addObject(gem3,1059,741);
        Bomb gem4 = new Bomb(GRAVITY);
        addObject(gem4,854,742);
        Gem gem5 = new Gem();
        addObject(gem5,1159,741);
        SmBrickWall smBrickWall10 = new SmBrickWall();
        addObject(smBrickWall10,1140,290);
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
