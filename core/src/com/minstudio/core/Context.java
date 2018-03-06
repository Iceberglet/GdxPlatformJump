package com.minstudio.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.minstudio.GameInput;
import com.minstudio.GameMain;
import com.minstudio.core.objectFactories.AbstractFactory;
import com.minstudio.core.objects.GameObject;
import com.minstudio.core.objects.Platform;
import com.minstudio.core.yoshi.Logger;
import com.minstudio.core.yoshi.Yoshi;
import com.minstudio.core.yoshi.statetrigger.AbstractStateTrigger;

public class Context {

    public static float G = -0.0013f; //downwards

    public static float MAX_DOWNWARD_SPEED = -0.25f;

    public static float YOSHI_HORIZONTAL_SPEED = 0.075f;

    public static float YOSHI_JUMP_SPEED = 0.46f;

    public static float CAMERA_ASCEND = 0.01f;

    private Collection<GameObject> gameObjects; //eateables

    private Collection<GameObject> hardObjects = new ArrayList<>(); //platforms

    private Yoshi yoshi;

    private GameInput gameInput;

    private Camera camera;

    private long currentTimestamp;

    public Context(Camera camera, Yoshi yoshi, GameInput gameInput) {
        this.yoshi = yoshi;
        this.gameInput = gameInput;
        this.camera = camera;
        this.currentTimestamp = 0L;
        //TODO: remove test
        hardObjects.add(new Platform(128, 256));
        hardObjects.add(new Platform(128, 360));
    }

    /**
     * gets time in milliseconds
     *
     * @return
     */
    public long getTimestamp() {
        return currentTimestamp;
    }

    private void updateYoshiState(Yoshi.State state, boolean facingRight) {
        if (state != yoshi.getCurrentState() || facingRight != yoshi.isFacingRight()) {
            yoshi.setCurrentState(state);
            yoshi.setFacingRight(facingRight);
        }
    }

    public void update() {
        long deltaTime = Math.round(Gdx.graphics.getDeltaTime() * 1000);
        this.currentTimestamp += deltaTime;
        this.camera.position.y += deltaTime * CAMERA_ASCEND;

        //update horizontal and vertical speed
        Vector2 speed = yoshi.getCurrentSpeed();
        speed.y = Math.max(MAX_DOWNWARD_SPEED, speed.y + G * deltaTime);   //update gravity with maximum falling speed.

        if (gameInput.isLeftPressed()) {
            speed.x = -YOSHI_HORIZONTAL_SPEED;
            yoshi.setFacingRight(false);
        } else if (gameInput.isRightPressed()) {
            speed.x = YOSHI_HORIZONTAL_SPEED;
            yoshi.setFacingRight(true);
        } else {
            speed.x = 0;
        }

        yoshi.update(deltaTime);

        //collision checks for other objects (e.g. yoshi eats apple)

        //collision checks for movement-impeding objects (e.g. yoshi stops falling / stops going left or right)
        this.hardObjects.forEach(ho -> yoshi.collidesWith(ho, true));

        //register all collided objects

        //yoshi state trigger tests
        Optional<AbstractStateTrigger> theTriggerOp = yoshi.getCurrentState().getTriggers().stream()
                .filter(t -> t.isTriggered(yoshi, this))
                .max(Comparator.comparingInt(a -> a.toState.priority));
        if (theTriggerOp.isPresent()) {
            AbstractStateTrigger theTrigger = theTriggerOp.get();
            theTrigger.doTrigger(yoshi, this);
            yoshi.setCurrentState(theTrigger.toState);
            theTrigger.toState.getTriggers().forEach(t -> t.resetTrigger(yoshi, this));
        }


        //clamp Yoshi within view port

        float posX = MathUtils.clamp(yoshi.getPosition().x, 0f, GameMain.CAMERA_WIDTH - 32);
        float posY = Math.min(GameMain.CAMERA_HEIGHT / 2 + camera.position.y - 32, yoshi.getPosition().y);
        yoshi.setPosition(posX, posY);

//        Logger.info(this, yoshi.getCurrentState() + " " + yoshi.getCurrentSpeed() + " " + yoshi.getPosition());
    }

    public GameInput getGameInput() {
        return gameInput;
    }

    public void render(SpriteBatch batch) {
        //render the scene
        hardObjects.forEach(ho -> ho.draw(batch));
        yoshi.draw(batch);
    }
}
