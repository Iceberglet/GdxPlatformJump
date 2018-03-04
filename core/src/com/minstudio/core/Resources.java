package com.minstudio.core;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;

public class Resources {

    private static Map<String, Texture> textureResources = new HashMap<>();

    public static synchronized Texture getTexture(String path){
        return textureResources.computeIfAbsent(path, (p)-> new Texture(path));
    }

    public static void dispose(){
        textureResources.values().forEach(Texture::dispose);
    }
}
