package com.minstudio.core.objects;

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

    public Vector2 getCenter(){
        center.x = rectangle.x + rectangle.width / 2;
        center.y = rectangle.y + rectangle.height / 2;
        return center;
    }

    public CollisionDirection collidesWith(BoxCollider collider){
        Rectangle a = this.rectangle;
        Rectangle b = collider.getRectangle();
        //if my left is in the other
        boolean myBtmLeftIsInHim = a.x >= b.x && a.x <= b.x + b.width && a.y >= b.y && a.y <= b.y + b.height;
        boolean myTopRightIsInHim = a.x + a.width >= b.x && a.x + a.width <= b.x + b.width &&
                a.y + a.height >= b.y && a.y + a.height <= b.y + b.height;
        if(myBtmLeftIsInHim || myTopRightIsInHim || collider.collidesWith(this) != CollisionDirection.NONE){
            return computeRelativeDirection(collider);
        }
        return CollisionDirection.NONE;
    }

    private CollisionDirection computeRelativeDirection(BoxCollider collider){
        Vector2 myCenter = collider.getCenter();
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
