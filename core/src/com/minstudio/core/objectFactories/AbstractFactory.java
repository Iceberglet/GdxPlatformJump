package com.minstudio.core.objectFactories;

import com.minstudio.core.Context;
import com.minstudio.core.objects.GameObject;

public interface AbstractFactory<T extends GameObject> {

    /**
     * If no object is to be created in this frame, simply return null.
     *
     * @param context the game context
     * @return new object. null to indicate no object created.
     * TODO: Maybe return more than one object?
     */
    T create(Context context);

}
