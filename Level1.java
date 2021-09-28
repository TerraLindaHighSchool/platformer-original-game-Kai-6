import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BrickWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{
    private final float GRAVITY = 0.0667f;
    private final GreenfootSound MUSIC = new GreenfootSound("zapsplat_024.mp3");
    private GreenfootImage background = new GreenfootImage(1200, 800);
    private int scrollCount;
    public Level1()
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1, false); 
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
        Player player = new Player(3, 5.6f, GRAVITY, 3, 3, Level2.class, MUSIC);
        addObject(player,25,755);
        addObject(new Door(),1175,45);
        addObject(new Floor(), 600, 800);
        addObject(new BrickWall(),500,194);
        addObject(new BrickWall(),400,436);
        addObject(new BrickWall(),958,554);
        addObject(new SmBrickWall(),1140,106);
        addObject(new SmBrickWall(),920,140);
        addObject(new SmBrickWall(),70,274);
        addObject(new SmBrickWall(),1140,402);
        addObject(new SmBrickWall(),1138,696);
        addObject(new SmBrickWall(),580,624);
        addObject(new SmBrickWall(),380,702);
        addObject(new SmBrickWall(),228,344);
        addObject(new Bomb(GRAVITY),606,148);
        addObject(new Bomb(GRAVITY),398,148);
        addObject(new Bomb(GRAVITY),1052,764);
        addObject(new Bomb(GRAVITY),814,508);
        addObject(new Bomb(GRAVITY),1052,508);
        addObject(new Bomb(GRAVITY),460,390);
        addObject(new Gem(),1140,362);
        addObject(new Gem(),1138,655);
        addObject(new Gem(),810,768);
    }
    
    private void spawn()
    {
        if(Math.random() < 0.0025)
        {
            addObject(new Rock(GRAVITY), Greenfoot.getRandomNumber(1200), -30);
        }
        if(Math.random() < 0.0001)
        {
            addObject(new AcidRain(GRAVITY), Greenfoot.getRandomNumber(1200), -30);
        }
    }
    
    private void scroll()
    {
        background.clear();
        background.setTransparency(255);
        background.drawImage(, 200 + scrollCount, 400);
        scrollCount += 1;
    }
}
