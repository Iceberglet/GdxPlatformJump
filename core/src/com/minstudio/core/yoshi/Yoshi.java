package com.minstudio.core.yoshi;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.minstudio.core.objects.BoxCollider;

public class Yoshi extends BoxCollider {

    /**
     * Each Yoshi.State has a corresponding Animation
     */
    public enum State {
        IDLE, JUMP, DJUMP, RUN, HIT, TONGUE, EAT
    }

    private boolean isFacingRight;

    private State currentState;
    private float currentStateDuration;
    private Vector2 currentSpeed;

    public Yoshi(Rectangle rectangle) {
        super(rectangle);
        this.isFacingRight = true;
        this.currentState = State.IDLE;
        this.currentStateDuration = 0f;
        this.currentSpeed = new Vector2(0, 0);  //cannot use Vector2.Zero
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public float getCurrentStateDuration() {
        return currentStateDuration;
    }

    public Vector2 getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Ending condition:
     * 1. Animation time expired
     * 2. User action (input)
     * 3. Environment Change (hit by something)
     */
    public void checkTransition(){
        //1. yoshi is hit

            //1.1 yoshi is invincible (hit recently / has a buff / has a hat) -> ignore, check further

            //1.2 yoshi is not invincible -> becomes hit

        //2. yoshi is not hit

            //2.1 user pressed left / right? -> play running animation
    }
}
