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
    private int powerupCount;
    private int speed;
    private int walkIndex;
    private int standIndex;
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
        health = new Health[maxHealth];
        powerup = new Powerup[maxPowerup];
        healthCount = maxHealth;
        powerupCount = 0;
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
        if(!MUSIC.isPlaying())
        {
            MUSIC.playLoop();
        }
        walk();
        jump();
        fall();
        onCollision();
        gameOver();
    }

    public void addedToWorld(World world) 
    {
        for(int i = 0; i < health.length; i++)
        {
            health[i] = new Health();
            world.addObject(health[i], 30 + 42 * i, 36);
        }
    }

    private void walk() 
    {
        if(isWalking)
        {
            animator();
        }
        else
        {
            setImage(STANDING_IMAGE);
            walkIndex = 0;
        }
        if(Greenfoot.isKeyDown("right"))
        {
            if(isFacingLeft)
            {
                mirrorImages(WALK_ANIMATION);
            }
            isWalking = true;
            isFacingLeft = false;
            if(!(getX() + speed > getWorld().getWidth()))
            {
                move(speed);
            } else {
                setLocation(getWorld().getWidth(), getY());
            }
        }

        if(Greenfoot.isKeyDown("left"))
        {
            if(!isFacingLeft)
            {
                mirrorImages(WALK_ANIMATION);
            }
            isWalking = true;
            isFacingLeft = true;
            if(!(getX() - speed < 0))
            {
                move(-speed);
            }
            else
            {
                setLocation(0, getY());
            }
        }
        if(!(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left")))
        {
            isWalking = false;
        }
    }

    private void jump() 
    {
        if(Greenfoot.isKeyDown("space") && isOnGround())
        {
            yVelocity = JUMP_FORCE;
            isJumping = true;
            Greenfoot.playSound("jump.wav");
        }
        if(isJumping && yVelocity > 0)
        {
            setLocation(getX(), getY() - (int) yVelocity);
            yVelocity -= GRAVITY;
        }
        else 
        {
            isJumping = false;
        }
    }

    private void fall() 
    {
        if(!isJumping && !isOnGround())
        {
            setLocation(getX(), getY() - (int) yVelocity);
            yVelocity -= GRAVITY;
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

    private void onCollision() 
    {
        if(isTouching(Door.class))
        {
            World world = null;
            try 
            {
                world = (World) NEXT_LEVEL.newInstance();
            }   
            catch (InstantiationException e) 
            {
                System.out.println("Class cannot be instantiated");
            } catch (IllegalAccessException e) {
                System.out.println("Cannot access class constructor");
            } 
            Greenfoot.playSound("door_open.wav");
            MUSIC.stop();
            Greenfoot.setWorld(world);
        }
        if(isTouching(Obstacle.class))
        {
            if(!isTouching(TrapDoor.class))
            {
                getWorld().removeObject(health[healthCount - 1]);
                healthCount--;
            }
            Greenfoot.playSound("explosionSmall.wav");
            removeTouching(Obstacle.class);
        }
        if(isTouching(Platform.class) && !isOnGround)
        {
            yVelocity = -1;
            fall();
        }
        if(isTouching(Gem.class))
        {
            removeTouching(Gem.class);
            Greenfoot.playSound("collectable.wav");
        }
    }

    private void mirrorImages(GreenfootImage[] anim) 
    {
        for(int i = 0; i < anim.length; i++)
        {
            anim[i].mirrorHorizontally();
        }
    }

    private void gameOver() 
    {
        if(healthCount == 0)
        {
                MUSIC.stop();
                Greenfoot.setWorld(new Level1());
        }
    }

    private boolean isOnGround() 
    {
        Actor ground = getOneObjectAtOffset(0, getImage().getHeight() /
                2, Platform.class);
        return ground != null;
    }
}