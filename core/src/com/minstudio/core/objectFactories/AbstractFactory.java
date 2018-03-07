package com.minstudio.core.objectFactories;

import java.util.Collection;

import com.minstudio.core.Constants;
import com.minstudio.core.objects.GameObject;

public abstract class AbstractFactory {

    private int currentProductionLevel = -Constants.CAMERA_HEIGHT;

    /**
     * called every frame to determine whether this factory should produce objects
     * @return yes or no
     */
    public final boolean shouldCreateObjects(float cameraY){
        if(currentProductionLevel < cameraY + Constants.CAMERA_HEIGHT){
            currentProductionLevel += Constants.CAMERA_HEIGHT;
            return true;
        }
        return false;
    }

    public int getCurrentProductionLevel() {
        return currentProductionLevel;
    }

    /**
     * Returns the set of integers
     *
     * @return new object. null to indicate no object created.
     */
    public abstract Collection<GameObject> create();

}
