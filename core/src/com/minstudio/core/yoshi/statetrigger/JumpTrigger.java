package com.minstudio.core.yoshi.statetrigger;

import com.badlogic.gdx.math.Vector2;
import com.minstudio.core.Constants;
import com.minstudio.core.Context;
import com.minstudio.core.animation.BaseAnimation;
import com.minstudio.core.audio.AudioManager;
import com.minstudio.core.objects.TextureLoader;
import com.minstudio.core.yoshi.Yoshi;

public class JumpTrigger extends AbstractStateTrigger {

    public JumpTrigger() {
        super(Yoshi.State.JUMP);
    }

    public JumpTrigger(Yoshi.State toState){
        super(toState);
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
        AudioManager.play(AudioManager.SoundEffect.BOING);
        Vector2 yoshiPos = yoshi.getPosition();
        context.getAnimationManager().submit(BaseAnimation.getSequence(0.08f,
                new Vector2(yoshiPos.x, yoshiPos.y),
                TextureLoader.getJumpBoom()), context.getTimestamp());
    }
}
