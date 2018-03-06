package com.minstudio.core.objects;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.minstudio.core.Resources;

public class TextureLoader {

    private static TextureRegion[] frames;

    public static void init() {
        Texture texture = Resources.getTexture("sprites/items.png");

        frames = new TextureRegion[9];
        TextureRegion[][] tempFrames = TextureRegion.split(texture, 32, 32);
        System.arraycopy(tempFrames[0], 0, frames, 0, 9);
    }

    public static TextureRegion getApple(){
        return frames[0];
    }

    public static TextureRegion getAppleGolden(){
        return frames[1];
    }

    public static TextureRegion getMelonHead(){
        return frames[2];
    }

    public static TextureRegion[] getGoombas() {
        return Arrays.copyOfRange(frames, 3, 5);
    }

    public static TextureRegion getHardPlatform(){
        return frames[5];
    }
}
