package com.minstudio.core.objects;

import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject extends BoxCollider {
    public GameObject(Rectangle rectangle) {
        super(rectangle);
    }
}
