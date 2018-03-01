package com.minstudio.core;

import java.util.Collection;
import java.util.List;

import com.minstudio.core.objectFactories.AbstractFactory;
import com.minstudio.core.objects.GameObject;
import com.minstudio.core.yoshi.Yoshi;

public class Context {

    private Collection<GameObject> gameObjects;

    private List<AbstractFactory> factories;

    private Yoshi yoshi;

    private long currentTimestamp;

    public long getTimestamp(){
        return currentTimestamp;
    }

    public void update(){
        //1. create all new objects

        //2. check for collisions

        //3. perform input on Yoshi

        //4. render the scene
    }

}
