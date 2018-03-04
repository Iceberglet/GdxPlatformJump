package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public class AnimationStopTrigger extends AbstractStateTrigger {

    private final long duration;
    private long start;

    public AnimationStopTrigger(long duration) {
        super(Yoshi.State.IDLE);
        this.duration = duration;
    }

    public AnimationStopTrigger(long duration, Yoshi.State toState) {
        super(toState);
        this.duration = duration;
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {
        start = context.getTimestamp();
    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        return context.getTimestamp() - start > duration;
    }

    @Override
    public void doTrigger(Yoshi yoshi, Context context) {
        super.doTrigger(yoshi, context);
    }
}
