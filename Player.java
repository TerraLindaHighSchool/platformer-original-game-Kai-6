import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private Health health[];
    private Powerup powerup[];
    private int healthCount;
    private int speed;
    private int walkIndex;
    private float yVelocity;
    private boolean isWalking;
    private boolean isJumping;
    private boolean isFacingLeft;
    private final GreenfootImage WALK_ANIMATION[];
    private final GreenfootImage STANDING_IMAGE;
    private final float JUMP_FORCE;
    
    public void act()
    {
        // Add your action code here.
    }
}
