package com.BombingGames.Game.Lighting;

import com.BombingGames.EngineCore.Map;
import com.badlogic.gdx.graphics.Color;

/**
 * The sun circles the world. The sun is only virtual. You can never see it inisometric perspective.
 * @author Benedikt Vogler
 */
public class Sun extends GlobalLightSource {

    /**
     *
     */
    public Sun() {
        super(-Map.getWorldSpinDirection(), 0, new Color(255, 255, 255, 1), 70);
    }
}