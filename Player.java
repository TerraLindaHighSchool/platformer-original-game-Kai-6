import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private Health[] health;
    private Powerup[] powerup;
    private int healthCount;
    private int speed;
    private int walkIndex;
    private int frame;
    private float yVelocity;
    private boolean isWalking;
    private boolean isJumping;
    private boolean isFacingLeft;
    private boolean isOnGround;
    private final GreenfootImage[] WALK_ANIMATION;
    private final GreenfootImage STANDING_IMAGE;
    private final float JUMP_FORCE;
    private final float GRAVITY;
    private final Class NEXT_LEVEL;
    private final GreenfootSound MUSIC;

    Player(int speed, float jumpForce, float gravity, int maxHealth,
    int maxPowerup, Class nextLevel, GreenfootSound music)
    {
        this.speed = speed;
        JUMP_FORCE = jumpForce;
        GRAVITY = gravity;
        NEXT_LEVEL = nextLevel;
        MUSIC = music;
        
        STANDING_IMAGE = getImage();
        WALK_ANIMATION = new GreenfootImage[]
        { 
            new GreenfootImage("walk0.png"),
            new GreenfootImage("walk1.png"),
            new GreenfootImage("walk2.png"),
            new GreenfootImage("walk3.png"),
            new GreenfootImage("walk4.png"),
            new GreenfootImage("walk5.png")
        };
        
    }

    public void act() {
        walk();
        jump();
        fall();
        onCollision();
        gameOver();
}
    public void addedToWorld(World world) {}
    private void walk() 
    {
        if (isWalking)
        {
            animator();
        }
        else
        {
            setImage(STANDING_IMAGE);
            walkIndex = 0;
        }
        if (Greenfoot.isKeyDown("right"))
        {
            if (isFacingLeft)
            {
                mirrorImages();
            }
            isWalking = true;
            isFacingLeft = false;
            setLocation(getX() + speed, getY());
        }
        if (Greenfoot.isKeyDown("left"))
        {
            if (!isFacingLeft)
            {
                mirrorImages();
            }
            isWalking = true;
            isFacingLeft = true;
            setLocation(getX() - speed, getY());
        }
        if (!(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left")))
        {
            isWalking = false;
        }
    }
    private void jump() 
    {
        if (Greenfoot.isKeyDown("space") && isOnGround)
        {
            yVelocity = -JUMP_FORCE;
            isJumping = true;
        }
        if (isJumping && yVelocity < 0)
        {
            setLocation(getX(), getY() + Math.round(yVelocity));
            yVelocity += GRAVITY;
        }
        else 
        {
            isJumping = false;
        }
    }
    private void fall() 
    {
        if (!isJumping && !isOnGround)
        {
            
        }
    }
    private void animator()
    {
        if(frame % (15 - 2 * speed) == 0)
        {
            if(walkIndex < WALK_ANIMATION.length)
            {
                setImage(WALK_ANIMATION[walkIndex]);
                walkIndex++;
            }
            else
            {
                walkIndex = 0;
            }
        }
        frame++;
    }
    private void onCollision() {}
    private void mirrorImages() {}
    private void gameOver() {}
    private boolean isOnGround() {return false;}
}