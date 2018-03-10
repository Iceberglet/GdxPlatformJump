package com.minstudio.core.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A submit-and-forget contract
 * users of this class simply submit the animations they wish to be drawn
 * and every frame they will call draw method supplying SpriteBatch
 */
public interface AnimationManager {

    void submit(Animation<DrawableTexture> animation, long timeStamp);

    void draw(long timeStamp, SpriteBatch batch);
}
