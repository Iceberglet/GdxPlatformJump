package com.minstudio.core.yoshi.statetrigger;

import com.minstudio.core.Context;
import com.minstudio.core.audio.AudioManager;
import com.minstudio.core.objects.Eatable;
import com.minstudio.core.Logger;
import com.minstudio.core.yoshi.Yoshi;

public class EatTrigger extends AbstractStateTrigger {

    public EatTrigger() {
        super(Yoshi.State.EAT);
    }

    @Override
    public void resetTrigger(Yoshi yoshi, Context context) {

    }

    @Override
    public boolean isTriggered(Yoshi yoshi, Context context) {
        return yoshi.previousCollided instanceof Eatable;
    }

    @Override
    public void doTrigger(Yoshi yoshi, Context context) {
        super.doTrigger(yoshi, context);
    }
}
