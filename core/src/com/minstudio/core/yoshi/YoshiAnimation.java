package com.minstudio.core.yoshi;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class YoshiAnimation {

    public enum AnimationType {
        RUN, NORMAL, HIT, EAT, TONGUE, JUMP
    }

    private static int FRAME_SIZE = 48;
    private static int FRAME_LENGTH = 12;

    public static final long RUNNING_DURATION = 600L;
    public static final long EAT_DURATION = 400L;
    public static final long HIT_DURATION = 400L;
    public static final long TONGUE_DURATION = 600L;

    private Vector2 offset;

    private Map<AnimationType, Animation<TextureRegion>> animations = new HashMap<>();

    public YoshiAnimation(Texture img, boolean horizontalFlip) {
        TextureRegion[] frames = new TextureRegion[FRAME_LENGTH];
        TextureRegion[][] tempFrames = TextureRegion.split(img, FRAME_SIZE, FRAME_SIZE);
        System.arraycopy(tempFrames[0], 0, frames, 0, FRAME_LENGTH);

        if (horizontalFlip) {
            for (TextureRegion f : frames) {
                f.flip(true, false);
            }
            offset = new Vector2(-FRAME_SIZE / 2f, 0);
        } else {
            offset = new Vector2(0, 0);
        }

        animations.put(AnimationType.RUN, new Animation<>(RUNNING_DURATION / 6000f, Arrays.copyOfRange(frames, 0, 6)));
        animations.put(AnimationType.NORMAL, new Animation<>(1f, frames[0]));
        animations.put(AnimationType.HIT, new Animation<>(HIT_DURATION, frames[10]));
        animations.put(AnimationType.TONGUE, new Animation<>(TONGUE_DURATION, frames[6]));
        animations.put(AnimationType.EAT, new Animation<>(EAT_DURATION / 4000f, Arrays.copyOfRange(frames, 7, 10)));
        animations.put(AnimationType.JUMP, new Animation<>(1f, frames[11]));
    }

    public void draw(SpriteBatch batch, Vector2 pos, AnimationType type, float deltaTime) {
//        Logger.info(this, pos + " " + type + " " + deltaTime);
        batch.draw(animations.get(type).getKeyFrame(deltaTime, true), pos.x + offset.x, pos.y + offset.y);
    }

}
