package com.minstudio.core.objectFactories;

import java.util.Collection;
import java.util.HashSet;

import com.badlogic.gdx.math.MathUtils;
import com.minstudio.core.Constants;
import com.minstudio.core.objects.AppleGolden;
import com.minstudio.core.objects.AppleNormal;
import com.minstudio.core.objects.GameObject;
import com.minstudio.core.objects.MelonHat;
import com.minstudio.core.objects.Platform;

public class GameObjectFactory extends AbstractFactory {

    private int nextPlatformStart = 0;
    private int target;
    private int lastPosition = 0;

    @Override
    public Collection<GameObject> create() {
        initLevels();
        Collection<GameObject> res = new HashSet<>();

        while(nextPlatformStart < target){
            //add platform
            int platformPos = -100;
            while(Math.abs(platformPos - lastPosition) > 1){
                platformPos = randomX();
            }
            float randomizableOffset = 64f;
            float xPos = platformPos * Constants.CAMERA_WIDTH / 4 + randomizableOffset * MathUtils.random();
            res.add(new Platform(xPos, nextPlatformStart));


            //position for an eatable
            float eatableRandom = MathUtils.random();
            if(eatableRandom < 0.5f){
                float posX = MathUtils.random(xPos-12f, xPos + 72f);
                float posY = MathUtils.random(nextPlatformStart + 12f, nextPlatformStart + 30f);
                if(eatableRandom < 0.035f){
                    res.add(new MelonHat(posX, posY));
                }
                else if(eatableRandom < 0.07f){
                    res.add(new AppleGolden(posX, posY));
                }
                else {
                    res.add(new AppleNormal(posX, posY));
                }
            }

            //double down!
            int another = randomX();
            if(nextPlatformStart % 3 == 0 && another != platformPos){
                xPos = another * Constants.CAMERA_WIDTH / 4 + randomizableOffset * MathUtils.random();
                res.add(new Platform(xPos, nextPlatformStart));
            }

            nextPlatformStart += Constants.PLATFORM_GAP;
            lastPosition = platformPos;
        }
        return res;
    }

    /**
     * returns a random position out of 4 broader categories:
     * leftmost, left, right, rightmost
     * @return the x pos: 0-leftmost, 1-left, 2-right, 3-rightmost
     */
    private int randomX(){
        return (int)Math.floor(Math.random() * 4);
    }

    private void initLevels(){
        int baseLevel = getCurrentProductionLevel();
        target = baseLevel + Constants.CAMERA_HEIGHT;
        nextPlatformStart = ( baseLevel / Constants.PLATFORM_GAP + 1 ) * Constants.PLATFORM_GAP;
    }
}
