package com.minstudio.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class BoxCollider {

    public enum CollisionDirection {
        TOP, RIGHT, LEFT, BOTTOM, NONE
    }

    private final Rectangle rectangle;
    private final Vector2 center = new Vector2(0, 0);

    public BoxCollider(Rectangle rectangle) {
        this.rectangle = rectangle;
        getCenter();
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean collidesWith(BoxCollider collider, boolean restore){
        Vector2 myCenter = this.getCenter();
        Vector2 otherCenter = collider.getCenter();
        float deltaX = otherCenter.x - myCenter.x;
        float deltaY = otherCenter.y - myCenter.y;
        boolean hor = Math.abs(deltaX) < (this.rectangle.width + collider.rectangle.width) / 2;
        boolean ver = Math.abs(deltaY) < (this.rectangle.height + collider.rectangle.height) / 2;
        boolean collides = hor && ver;

        if(collides){
            if(restore){
                float l = Math.abs(collider.rectangle.x - this.rectangle.width - this.rectangle.x);
                float t = Math.abs(collider.rectangle.y + collider.rectangle.height - this.rectangle.y);
                float r = Math.abs(collider.rectangle.x + collider.rectangle.width - this.rectangle.x);
                float b = Math.abs(collider.rectangle.y - this.rectangle.width - this.rectangle.y);

                if(deltaX >= 0 && deltaY >= 0){
                    //we move either left or btm
                    if(l < b){
                        this.rectangle.x -= l;
                    } else {
                        this.rectangle.y -= b;
                    }
                }
                else if(deltaX < 0 && deltaY >= 0){
                    //we move either right or btm
                    if(r < b){
                        this.rectangle.x += r;
                    } else {
                        this.rectangle.y -= b;
                    }
                }
                else if(deltaX < 0 && deltaY < 0){
                    //we move either right or top
                    if(t < b){
                        this.rectangle.x += r;
                    } else {
                        this.rectangle.y += t;
                    }
                }
                else if(deltaX >= 0 && deltaY < 0){
                    //we move either left or top
                    if(l < b){
                        this.rectangle.x -= l;
                    } else {
                        this.rectangle.y += t;
                    }
                }
            }
            collider.onCollision(this);
        }
        return collides;
    }

    protected void onCollision(BoxCollider collider){
        Gdx.app.debug("Collision", "No action taken for Collision Happened to " + collider);
    }

    private Vector2 getCenter(){
        center.x = rectangle.x + rectangle.width / 2;
        center.y = rectangle.y + rectangle.height / 2;
        return center;
    }

    private CollisionDirection computeRelativeDirection(BoxCollider collider){
        Vector2 myCenter = this.getCenter();
        Vector2 otherCenter = collider.getCenter();
        float deltaX = otherCenter.x - myCenter.x;
        float deltaY = otherCenter.y - myCenter.y;
        if(Math.abs(deltaY / deltaX) < 1){
            //y is smaller than x
            return deltaX >= 0 ? CollisionDirection.RIGHT : CollisionDirection.LEFT;
        }
        return deltaY >= 0 ? CollisionDirection.TOP : CollisionDirection.BOTTOM;
    }
}
