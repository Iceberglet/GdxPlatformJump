package com.minstudio.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject extends BoxCollider {

    public GameObject(Rectangle rectangle) {
        super(rectangle);
    }

    public abstract void draw(SpriteBatch batch);

    private Vector2 pos = new Vector2();

    public Vector2 getPosition(){
        pos.x = getRectangle().x;
        pos.y = getRectangle().y;
        return pos;
    }

    public void setPosition(float x, float y){
        this.getRectangle().x = x;
        this.getRectangle().y = y;
    }
}
