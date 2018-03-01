package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public class ToBeHitTrigger extends StateTrigger {

    public ToBeHitTrigger(Yoshi.State from) {
        super(from, Yoshi.State.HIT);
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {
        //TODO
    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        //TODO: Implement
        // check if yoshi is hit by any boxes.
        // computation should be done here.
        return false;
    }

}
