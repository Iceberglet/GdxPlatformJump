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

public class Yoshi extends BoxCollider {

    /**
     * Each Yoshi.State has a corresponding Animation
     */
    public enum State {
        IDLE(0, YoshiAnimation.AnimationType.NORMAL),
        JUMP(7, YoshiAnimation.AnimationType.NORMAL),
        DJUMP(7, YoshiAnimation.AnimationType.NORMAL),
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
        State.RUN.setTriggers(runStopTrigger, toBeHitTrigger, tongueTrigger, eatTrigger);
        State.HIT.setTriggers(recoverFromHitTrigger);
        State.TONGUE.setTriggers(toBeHitTrigger, tongueStopTrigger, eatTrigger);
        State.EAT.setTriggers(toBeHitTrigger, stopEatTrigger);
    }

    private Texture img;

    private boolean isFacingRight;

    private State currentState;
    private Vector2 currentSpeed;
    private Vector2 currentPos;
    private long currentStateDuration;
    private YoshiAnimation yoshiAnimationLeft;
    private YoshiAnimation yoshiAnimationRight;

    public Yoshi(Rectangle rectangle) {
        super(rectangle);
        img = Resources.getTexture("sprites/yoshi.png");
        this.isFacingRight = true;
        this.currentState = State.IDLE;
        this.currentSpeed = new Vector2(0, 0);  //cannot use Vector2.Zero
        this.currentPos = new Vector2(0, 0);
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
        this.currentPos.x += deltaTime * this.currentSpeed.x;
        this.currentPos.y += deltaTime * this.currentSpeed.y;
    }

    public void render(SpriteBatch batch) {
        YoshiAnimation ani = this.isFacingRight ? yoshiAnimationRight : yoshiAnimationLeft;
        ani.draw(batch, currentPos, currentState.animationType, currentStateDuration / 1000f);
    }

    @Override
    protected void onCollision(BoxCollider collider) {
        //
    }

    public void dispose() {
        img.dispose();
    }
}
