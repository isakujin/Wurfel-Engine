package com.BombingGames.Game.Gameobjects;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

/**
 *An entity is a game object wich is selfAware that means it knows it's position.
 * @author Benedikt
 */
public abstract class AbstractEntity extends Object implements ISelfAware {
   
    /**
     * Create an abstractEntity. You should use Block.getInstance(int) 
     * @see com.BombingGames.Game.Gameobjects.Block#getInstance(int) 
     */
    protected AbstractEntity(int id){
        super(id);
        setVisible(true);
    }
    
    public static AbstractEntity getInstance(int id, int value, int[] coords){
        AbstractEntity entity = null;
        //define the default SideSprites
        switch (id){
            case 40: 
                    try {
                        entity = new Player(id);
                        entity.setAbsCoords(coords);
                        entity.setTransparent(true);
                        entity.setObstacle(true);
                        entity.setDimensionY(2);
                        if (value == 0)
                            entity.setHidden(true);
                    } catch (SlickException ex) {
                        Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
           case 50:entity = new AnimatedEntity(id, new int[]{2000,200},true, false);//explosion
                    break;
        }
        entity.setValue(value);
        return entity;
    }
}
