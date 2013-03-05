package com.BombingGames.Game.Gameobjects;

/**
 *
 * @author Benedikt
 */
public class AnimatedBlock extends Block implements IAnimation{
    private int[] animationsduration;
    private int counter = 0;
    private boolean running;
    private boolean loop;
    
        /**
     * Create this Block with an array wich has the time of every animation step in ms in it.
     * @param animationinformation array wich has time in ms of each value. Example: new int[]{600,200,1000}
     * @param  autostart True when it should automatically start.
     * @param loop Set to true when it should loop, when false it stops after one time. 
     */
    protected AnimatedBlock(int[] animationsinformation, boolean autostart, boolean loop){
        this.animationsduration = animationsinformation;
        this.running = autostart;
        this.loop = loop;
    }
    
   /**
     * updates the block and the animation.
     * @param delta the time wich has passed since last update
     */
    @Override
    public void update(int delta) {
        if (running) counter = Animator.updateAnimation(this, animationsduration, delta, counter, loop);
    }

    /**
     * Starts the animation.
     */
    @Override
    public void start() {
        running = true;
    }

    /**
     * Stops the animation.
     */
    @Override
    public void stop() {
        running = false;
    }
    
}
