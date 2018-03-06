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
                this.restoreFromCollision(deltaX, deltaY, collider);
            }
            collider.onCollision(this);
        }
        return collides;
    }

    protected void restoreFromCollision(float deltaX, float deltaY, BoxCollider collider){
        //do nothing
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
