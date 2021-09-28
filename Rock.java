import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rock extends Obstacle
{
    private float yVelocity;
    private final float GRAVITY;
    private int counter;
    public Rock(float gravity)
    {
        GRAVITY = gravity;
        counter = 450;
    }
    
    public void act()
    {
        fall();
        if(counter <= 0) {
            getWorld().removeObject(this);
        }
        else
        {
            counter -= 1;
        }
    }

    protected void fall()
    { 
        if(!isOnGround())
        {
            yVelocity += GRAVITY;
            setLocation(getX(), getY() + (int) yVelocity);
        }
    }
}
