package com.minstudio.core.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class DisappearingAnimation extends FixedPositionAnimation {

    /**
     * disappear by shrinking
     */
    public static Animation<DrawableTexture> getSequence(float frameDuration,  Vector2 pos, TextureRegion texture, int length){
        DisappearingAnimation[] animations = new DisappearingAnimation[length];
        for(int idx = 0; idx < length; idx++){
            float scale = (length - idx) * 1f / length;
            animations[idx] = new DisappearingAnimation(texture,
                    new Vector2(texture.getRegionWidth() / 2, texture.getRegionHeight() / 2),
                    new Vector2(scale, scale),
                    0f, pos);
        }
        return new Animation<>(frameDuration, animations);
    }

    private DisappearingAnimation(TextureRegion textureRegion, Vector2 origin, Vector2 scale, float rotation, Vector2 pos) {
        super(textureRegion, origin, scale, rotation, pos);
    }
}
