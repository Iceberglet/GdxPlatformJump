package com.minstudio.core.yoshi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.minstudio.core.Resources;
import com.minstudio.core.objects.BoxCollider;
import com.minstudio.core.objects.GameObject;
import com.minstudio.core.yoshi.statetrigger.AbstractStateTrigger;
import com.minstudio.core.yoshi.statetrigger.AnimationStopTrigger;
import com.minstudio.core.yoshi.statetrigger.DJumpTrigger;
import com.minstudio.core.yoshi.statetrigger.EatTrigger;
import com.minstudio.core.yoshi.statetrigger.JumpTrigger;
import com.minstudio.core.yoshi.statetrigger.RunStartTrigger;
import com.minstudio.core.yoshi.statetrigger.RunStopTrigger;
import com.minstudio.core.yoshi.statetrigger.SteppedOnGroundTrigger;
import com.minstudio.core.yoshi.statetrigger.ToBeHitTrigger;
import com.minstudio.core.yoshi.statetrigger.TongueTrigger;

public class Yoshi extends GameObject {

    /**
     * Each Yoshi.State has a corresponding Animation
     */
    public enum State {
        IDLE(0, YoshiAnimation.AnimationType.NORMAL),
        JUMP(7, YoshiAnimation.AnimationType.JUMP),
        DJUMP(7, YoshiAnimation.AnimationType.JUMP),
        RUN(6, YoshiAnimation.AnimationType.RUN),
        HIT(10, YoshiAnimation.AnimationType.HIT),
        TONGUE(8, YoshiAnimation.AnimationType.TONGUE),
        EAT(9, YoshiAnimation.AnimationType.EAT);

        public final int priority;
        public final YoshiAnimation.AnimationType animationType;
        Set<AbstractStateTrigger> triggers = new HashSet<>();

        State(int priority, YoshiAnimation.AnimationType animationType) {
            this.priority = priority;
            this.animationType = animationType;
        }

        private void setTriggers(AbstractStateTrigger... triggers) {
            this.triggers.addAll(Arrays.asList(triggers));
        }

        public Set<AbstractStateTrigger> getTriggers() {
            return triggers;
        }
    }

    static {
        //triggers initialization
        EatTrigger eatTrigger = new EatTrigger();
        AnimationStopTrigger stopEatTrigger = new AnimationStopTrigger(YoshiAnimation.EAT_DURATION);
        DJumpTrigger dJumpTrigger = new DJumpTrigger();
        JumpTrigger jumpTrigger = new JumpTrigger();
        AnimationStopTrigger recoverFromHitTrigger = new AnimationStopTrigger(YoshiAnimation.HIT_DURATION);
        RunStartTrigger runStartTrigger = new RunStartTrigger();
        RunStopTrigger runStopTrigger = new RunStopTrigger();
        ToBeHitTrigger toBeHitTrigger = new ToBeHitTrigger();
        TongueTrigger tongueTrigger = new TongueTrigger();
        SteppedOnGroundTrigger steppedOnGroundTrigger = new SteppedOnGroundTrigger();
        AnimationStopTrigger tongueStopTrigger = new AnimationStopTrigger(YoshiAnimation.TONGUE_DURATION);

        State.IDLE.setTriggers(eatTrigger, jumpTrigger, toBeHitTrigger, runStartTrigger, tongueTrigger);
        State.JUMP.setTriggers(steppedOnGroundTrigger, dJumpTrigger, toBeHitTrigger, tongueTrigger);
        State.DJUMP.setTriggers(steppedOnGroundTrigger, toBeHitTrigger, tongueTrigger);
        State.RUN.setTriggers(runStopTrigger, toBeHitTrigger, tongueTrigger, eatTrigger, jumpTrigger);
        State.HIT.setTriggers(recoverFromHitTrigger);
        State.TONGUE.setTriggers(toBeHitTrigger, tongueStopTrigger, eatTrigger);
        State.EAT.setTriggers(toBeHitTrigger, stopEatTrigger);
    }

    private Texture img;

    private boolean isFacingRight;

    private State currentState;
    private Vector2 currentSpeed;
    private long currentStateDuration;
    private YoshiAnimation yoshiAnimationLeft;
    private YoshiAnimation yoshiAnimationRight;

    public Yoshi(Rectangle rectangle) {
        super(rectangle);
        img = Resources.getTexture("sprites/yoshi.png");
        this.isFacingRight = true;
        this.currentState = State.IDLE;
        this.currentSpeed = new Vector2(0, 0);  //cannot use Vector2.Zero
        yoshiAnimationLeft = new YoshiAnimation(img, true);
        yoshiAnimationRight = new YoshiAnimation(img, false);
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

    public void setCurrentState(State newState) {
        Logger.info(this, "State Updated to " + newState);
        this.currentState = newState;
        this.currentStateDuration = 0L;
    }

    public Vector2 getCurrentSpeed() {
        return currentSpeed;
    }

    public void update(long deltaTime) {
        this.currentStateDuration += deltaTime;
        Vector2 pos = this.getPosition();
        this.setPosition(deltaTime * this.currentSpeed.x + pos.x, deltaTime * this.currentSpeed.y + pos.y);
        this.previousCollisionDirection = -1;
    }

    @Override
    public void draw(SpriteBatch batch) {
        YoshiAnimation ani = this.isFacingRight ? yoshiAnimationRight : yoshiAnimationLeft;
        ani.draw(batch, this.getPosition(), currentState.animationType, currentStateDuration / 1000f);
    }

    @Override
    protected void onCollision(BoxCollider collider) {
        //
    }

    public void dispose() {
        img.dispose();
    }

    //cleared every update
    //-1: no collision
    // 1: top
    // 2: right
    // 3: btm
    // 4: left
    public int previousCollisionDirection = -1;

    @Override
    protected void restoreFromCollision(float deltaX, float deltaY, BoxCollider collider) {
        float l = Math.abs(collider.getRectangle().x - this.getRectangle().width - this.getRectangle().x);
        float t = Math.abs(collider.getRectangle().y + collider.getRectangle().height - this.getRectangle().y);
        float r = Math.abs(collider.getRectangle().x + collider.getRectangle().width - this.getRectangle().x);
        float b = Math.abs(collider.getRectangle().y - this.getRectangle().width - this.getRectangle().y);

        if(deltaX >= 0 && deltaY >= 0){
            //we move either left or btm
            if(l < b){
                this.getRectangle().x -= l;
                this.currentSpeed.x = 0f;
                this.previousCollisionDirection = 2;
            } else {
                this.getRectangle().y -= b;
                this.currentSpeed.y = 0f;
                this.previousCollisionDirection = 1;
            }
        }
        else if(deltaX < 0 && deltaY >= 0){
            //we move either right or btm
            if(r < b){
                this.getRectangle().x += r;
                this.currentSpeed.x = 0f;
                this.previousCollisionDirection = 4;
            } else {
                this.getRectangle().y -= b;
                this.currentSpeed.y = 0f;
                this.previousCollisionDirection = 1;
            }
        }
        else if(deltaX < 0 && deltaY < 0){
            //we move either right or top
            if(r < t){
                this.getRectangle().x += r;
                this.currentSpeed.x = 0f;
                this.previousCollisionDirection = 4;
            } else {
                this.getRectangle().y += t;
                this.currentSpeed.y = 0f;
                this.previousCollisionDirection = 3;
            }
        }
        else if(deltaX >= 0 && deltaY < 0){
            //we move either left or top
            if(l < t){
                this.getRectangle().x -= l;
                this.currentSpeed.x = 0f;
                this.previousCollisionDirection = 2;
            } else {
                this.getRectangle().y += t;
                this.currentSpeed.y = 0f;
                this.previousCollisionDirection = 3;
            }
        }
    }
}
