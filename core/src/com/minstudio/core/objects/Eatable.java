package com.minstudio.core.objects;

import com.badlogic.gdx.math.Rectangle;

public abstract class Eatable extends GameObject {

    public Eatable(Rectangle rectangle, boolean isHardObject) {
        super(rectangle, isHardObject);
    }
}
