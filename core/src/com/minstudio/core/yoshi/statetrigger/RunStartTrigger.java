package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public class RunStartTrigger extends AbstractStateTrigger {

    public RunStartTrigger() {
        super(Yoshi.State.RUN);
    }


    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {

    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        return context.getGameInput().isRightPressed() || context.getGameInput().isLeftPressed();
    }

    @Override
    public void doTrigger(Yoshi yoshi, Context context) {
        super.doTrigger(yoshi, context);
    }
}
