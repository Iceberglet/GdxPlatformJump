package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public abstract class AbstractStateTrigger implements StateTrigger {

    public final Yoshi.State toState;

    public AbstractStateTrigger(Yoshi.State toState) {
        this.toState = toState;
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {
//        Logger.info(this, "reset");
    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        return false;
    }

    @Override
    public void doTrigger(Yoshi yoshi, Context context) {
//        Logger.info(this, "triggered");
    }
}
