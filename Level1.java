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
    public Level1()
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1, false); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        setPaintOrder(Player.class, Platform.class, Obstacle.class, Collectable.class,
            Door.class, HUD.class);
        addObject(new Player(),25,755);
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
        addObject(new AcidRain(GRAVITY),886,584);
        addObject(new AcidRain(GRAVITY),728,584);
        addObject(new AcidRain(GRAVITY),923,172);
        addObject(new AcidRain(GRAVITY),37,12);
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
}
