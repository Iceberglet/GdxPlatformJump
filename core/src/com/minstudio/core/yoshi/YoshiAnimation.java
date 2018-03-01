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
        RUN, NORMAL, HIT, EAT
    }

    private static int FRAME_SIZE = 48;
    private static int FRAME_LENGTH = 11;

    private static float RUNNING_FPS = 0.1f;
    private static float EAT_FPS = 0.1f;

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
            offset = new Vector2(-FRAME_SIZE / 1.5f, 0);
        } else {
            offset = new Vector2(-FRAME_SIZE / 3, 0);
        }

        animations.put(AnimationType.RUN, new Animation<>(RUNNING_FPS, Arrays.copyOfRange(frames, 0, 6)));
        animations.put(AnimationType.NORMAL, new Animation<>(1, frames[0]));
        animations.put(AnimationType.HIT, new Animation<>(1, frames[10]));
        animations.put(AnimationType.EAT, new Animation<>(EAT_FPS, Arrays.copyOfRange(frames, 6, 10)));
    }

    public void draw(SpriteBatch batch, Vector2 pos, AnimationType type, float deltaTime) {
        batch.draw(animations.get(type).getKeyFrame(deltaTime, true), pos.x + offset.x, pos.y + offset.y);
    }

}
