package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public abstract class StateTrigger {
    public final Yoshi.State fromState;
    public final Yoshi.State toState;

    public StateTrigger(Yoshi.State fromState, Yoshi.State toState) {
        this.fromState = fromState;
        this.toState = toState;
    }

    /**
     * function called when the state this trigger belongs to becomes active.
     * @param yoshi
     * @param context
     */
    public abstract void resetTrigger(Yoshi yoshi, Context context);

    public abstract boolean isTriggered(Yoshi yoshi, Context context);
}
