package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Constants;
import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public class JumpTrigger extends AbstractStateTrigger {

    public JumpTrigger() {
        super(Yoshi.State.JUMP);
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {

    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        return context.getGameInput().isUpDown();
    }

    @Override
    public void doTrigger(Yoshi yoshi, Context context) {
        super.doTrigger(yoshi, context);
        yoshi.getCurrentSpeed().y = Constants.YOSHI_JUMP_SPEED;
    }
}
