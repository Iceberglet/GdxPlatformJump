package com.minstudio.core.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationWrapper {

    private long startTime;

    private Animation<DrawableTexture> animation;

    public AnimationWrapper(long startTime, Animation<DrawableTexture> animation) {
        this.startTime = startTime;
        this.animation = animation;
    }

    public long getStartTime() {
        return startTime;
    }

    public Animation<DrawableTexture> getAnimation() {
        return animation;
    }
}
