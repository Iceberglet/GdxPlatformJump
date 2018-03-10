package com.minstudio.core.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class BaseAnimation extends FixedPositionAnimation {


    public static Animation<DrawableTexture> getSequence(float frameDuration, Vector2 pos, TextureRegion... textures){
        BaseAnimation[] animations = new BaseAnimation[textures.length];
        for(int idx = 0; idx < textures.length; idx++){
            animations[idx] = new BaseAnimation(textures[idx], pos);
        }
        return new Animation<>(frameDuration, animations);
    }


    private static final Vector2 UNIT = new Vector2(1, 1);

    public BaseAnimation(TextureRegion textureRegion, Vector2 pos) {
        super(textureRegion, Vector2.Zero, UNIT, 0f, pos);
    }
}
