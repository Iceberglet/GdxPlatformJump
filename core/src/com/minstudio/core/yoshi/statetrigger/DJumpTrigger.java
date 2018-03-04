package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public class DJumpTrigger extends AbstractStateTrigger {

    public DJumpTrigger() {
        super(Yoshi.State.DJUMP);
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {

    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        return false;
    }

    @Override
    public void doTrigger(Yoshi yoshi, Context context) {
        super.doTrigger(yoshi, context);
        yoshi.getCurrentSpeed().y = Context.YOSHI_JUMP_SPEED;
    }
}
