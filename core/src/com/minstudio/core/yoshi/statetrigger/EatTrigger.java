package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public class EatTrigger extends AbstractStateTrigger {

    public EatTrigger() {
        super(Yoshi.State.EAT);
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {
        super.resetTrigger(yoshi, context);
    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        //TODO: When food hits you
        return super.isTriggered(yoshi, context);
    }

    @Override
    public void doTrigger(Yoshi yoshi, Context context) {
        super.doTrigger(yoshi, context);
    }
}
