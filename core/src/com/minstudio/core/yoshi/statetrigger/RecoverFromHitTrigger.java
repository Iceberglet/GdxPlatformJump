package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public class RecoverFromHitTrigger extends StateTrigger{

    private static final long HIT_DURATION = 2000L; //TODO: Take this from HIT Animation time

    private long timeFrom;

    public RecoverFromHitTrigger() {
        super(Yoshi.State.HIT, Yoshi.State.IDLE);
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {
        timeFrom = context.getTimestamp();
    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        return context.getTimestamp() - timeFrom > HIT_DURATION;
    }
}
