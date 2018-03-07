package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.yoshi.Yoshi;

public interface StateTrigger {
    /**
     * function called when the state this trigger belongs to becomes active.
     *
     * @param yoshi
     * @param context
     */
    void resetTrigger(Yoshi yoshi, Context context);

    boolean isTriggered(Yoshi yoshi, Context context);

    void doTrigger(Yoshi yoshi, Context context);

}
