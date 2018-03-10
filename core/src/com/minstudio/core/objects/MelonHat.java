package com.minstudio.core.objects;

import java.util.Set;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.minstudio.core.animation.BaseAnimation;
import com.minstudio.core.animation.DisappearingAnimation;
import com.minstudio.core.animation.DrawableTexture;
import com.minstudio.core.util.Util;

public class MelonHat extends Eatable {
    public MelonHat(float x, float y) {
        super(new Rectangle(x, y, 32, 32), false);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(TextureLoader.getMelonHead(), this.getRectangle().x, this.getRectangle().y);
    }



    @Override
    public Set<Animation<DrawableTexture>> getExitAnimation() {
        return Util.asSet(DisappearingAnimation.getSequence(0.05f, this.getPosition(), TextureLoader.getApple(), 10));
    }
}
