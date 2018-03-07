package com.minstudio.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class AppleNormal extends Eatable {
    public AppleNormal(float x, float y) {
        super(new Rectangle(x, y, 32, 32), false);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(TextureLoader.getApple(), this.getRectangle().x, this.getRectangle().y);
    }
}
