package com.minstudio.core.audio;

import java.util.EnumMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {

    public enum SoundEffect {
        BOING,
        POP
    }

    private static EnumMap<SoundEffect, Sound> soundMap = new EnumMap<>(SoundEffect.class);


    public static void init(){
        soundMap.put(SoundEffect.BOING, Gdx.audio.newSound(Gdx.files.internal("sounds/borrr.mp3")));
        soundMap.put(SoundEffect.POP, Gdx.audio.newSound(Gdx.files.internal("sounds/pop.mp3")));
    }

    public static void play(SoundEffect effect){
        soundMap.get(effect).play();
    }

    public static void dispose(){
        soundMap.values().forEach(Sound::dispose);
        soundMap.clear();
    }

}
