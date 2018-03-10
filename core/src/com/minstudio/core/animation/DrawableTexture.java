package com.minstudio.core.animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class DrawableTexture {

    private TextureRegion textureRegion;
    private Vector2 origin;
    private Vector2 scale;
    private float rotation;

    DrawableTexture(TextureRegion textureRegion, Vector2 origin, Vector2 scale, float rotation) {
        this.textureRegion = textureRegion;
        this.origin = origin;
        this.scale = scale;
        this.rotation = rotation;
    }

    protected abstract Vector2 getPos();

    public void draw(SpriteBatch batch){
        Vector2 pos = getPos();
        batch.draw(textureRegion, pos.x, pos.y, origin.x, origin.y, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), scale.x, scale.y, rotation);
    }
}
