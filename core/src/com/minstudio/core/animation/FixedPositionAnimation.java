package com.minstudio.core.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class FixedPositionAnimation extends DrawableTexture {

    private Vector2 pos;

    public FixedPositionAnimation(TextureRegion textureRegion, Vector2 origin, Vector2 scale, float rotation, Vector2 pos) {
        super(textureRegion, origin, scale, rotation);
        this.pos = pos;
    }

    @Override
    protected Vector2 getPos() {
        return pos;
    }
}
