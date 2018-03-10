package com.minstudio.core.objects;

import java.util.Set;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.minstudio.core.animation.DrawableTexture;

public abstract class GameObject extends BoxCollider {

    private Vector2 pos = new Vector2();
    private final boolean isHardObject;

    public GameObject(Rectangle rectangle, boolean isHardObject) {
        super(rectangle);
        this.isHardObject = isHardObject;
    }

    public boolean isHardObject() {
        return isHardObject;
    }

    public abstract void draw(SpriteBatch batch);

    public Vector2 getPosition() {
        pos.x = getRectangle().x;
        pos.y = getRectangle().y;
        return pos;
    }

    public void setPosition(float x, float y) {
        this.getRectangle().x = x;
        this.getRectangle().y = y;
    }

    public Set<Animation<DrawableTexture>> getExitAnimation(){
        return null;
    }
}
