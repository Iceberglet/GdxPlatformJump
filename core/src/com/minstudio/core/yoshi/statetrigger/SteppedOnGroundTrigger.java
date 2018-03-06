package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public class SteppedOnGroundTrigger extends AbstractStateTrigger {

    public SteppedOnGroundTrigger() {
        super(Yoshi.State.IDLE);
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {

    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        return yoshi.previousCollisionDirection == 3;
    }

    @Override
    public void doTrigger(Yoshi yoshi, Context context) {
        super.doTrigger(yoshi, context);
    }
}
