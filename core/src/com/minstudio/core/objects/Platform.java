package com.minstudio.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends GameObject {

    public Platform(float x, float y) {
        super(new Rectangle(x, y, 64, 7), true);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(TextureLoader.getHardPlatform(), this.getRectangle().x, this.getRectangle().y);
        batch.draw(TextureLoader.getHardPlatform(), this.getRectangle().x + 31.95f, this.getRectangle().y);
    }
}
