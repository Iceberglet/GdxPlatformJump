package com.minstudio.core.objects;

import java.util.Set;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.minstudio.core.animation.BaseAnimation;
import com.minstudio.core.animation.DisappearingAnimation;
import com.minstudio.core.animation.DrawableTexture;
import com.minstudio.core.util.Util;

public class AppleGolden extends Eatable {
    public AppleGolden(float x, float y) {
        super(new Rectangle(x, y, 32, 32), false);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(TextureLoader.getAppleGolden(), this.getRectangle().x, this.getRectangle().y);
    }

    @Override
    public Set<Animation<DrawableTexture>> getExitAnimation() {
        return Util.asSet(DisappearingAnimation.getSequence(0.05f, this.getPosition(), TextureLoader.getAppleGolden(), 10),
                          BaseAnimation.getSequence(0.08f, this.getPosition(), TextureLoader.getPopout())
                );
    }
}
