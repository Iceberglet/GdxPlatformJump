package com.minstudio.core.animation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.minstudio.core.util.Util;
import com.minstudio.core.Logger;

public class AnimationManagerImpl implements AnimationManager {

    private Set<AnimationWrapper> list = new HashSet<>();

    @Override
    public void submit(Animation<DrawableTexture> animation, long timeStamp) {
        list.add(new AnimationWrapper(timeStamp, animation));
    }

    @Override
    public void draw(long timeStamp, SpriteBatch batch) {
        for(Iterator<AnimationWrapper> iter = list.iterator(); iter.hasNext();){
            AnimationWrapper animation = iter.next();
            float timeDelta = Util.toFloatDeltaTime(animation.getStartTime(), timeStamp);
            if(animation.getAnimation().getPlayMode() == Animation.PlayMode.NORMAL && !animation.getAnimation().isAnimationFinished(timeDelta)){
                animation.getAnimation().getKeyFrame(timeDelta).draw(batch);
            } else {
                iter.remove();
            }
        }
    }
}
