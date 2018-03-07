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
import com.badlogic.gdx.math.Vector2;
import com.minstudio.GameInput;
import com.minstudio.core.objectFactories.GameObjectFactory;
import com.minstudio.core.objects.Eatable;
import com.minstudio.core.objects.GameObject;
import com.minstudio.core.yoshi.Logger;
import com.minstudio.core.yoshi.Yoshi;
import com.minstudio.core.yoshi.statetrigger.AbstractStateTrigger;

public class Context {

    private Yoshi yoshi;

    private GameInput gameInput;

    private Camera camera;

    private long currentTimestamp;

    private Collection<GameObject> objects = new ArrayList<>();

    private GameObjectFactory gameObjectFactory = new GameObjectFactory();

    public Context(Camera camera, Yoshi yoshi, GameInput gameInput) {
        this.yoshi = yoshi;
        this.gameInput = gameInput;
        this.camera = camera;
        this.currentTimestamp = 0L;
        createObjects();
    }

    private void createObjects(){
        while(gameObjectFactory.shouldCreateObjects(camera.position.y)){
            objects.addAll(gameObjectFactory.create());
        }
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
        this.camera.position.y += deltaTime * Constants.CAMERA_ASCEND;

        //update horizontal and vertical speed
        Vector2 speed = yoshi.getCurrentSpeed();
        speed.y = Math.max(Constants.MAX_DOWNWARD_SPEED, speed.y + Constants.G * deltaTime);   //update gravity with maximum falling speed.

        if (gameInput.isLeftPressed()) {
            speed.x = -Constants.YOSHI_HORIZONTAL_SPEED;
            yoshi.setFacingRight(false);
        } else if (gameInput.isRightPressed()) {
            speed.x = Constants.YOSHI_HORIZONTAL_SPEED;
            yoshi.setFacingRight(true);
        } else {
            speed.x = 0;
        }

        yoshi.update(deltaTime);

        //collision checks for other objects (e.g. yoshi eats apple)

        //collision checks for movement-impeding objects (e.g. yoshi stops falling / stops going left or right)
        List<GameObject> eaten = this.objects.stream().filter(ho -> yoshi.collidesWith(ho, ho.isHardObject()) && ho instanceof Eatable).collect(Collectors.toList());

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
        float posX = MathUtils.clamp(yoshi.getPosition().x, 0f, Constants.CAMERA_WIDTH - 32);
        float posY = Math.min(Constants.CAMERA_HEIGHT / 2 + camera.position.y - 32, yoshi.getPosition().y);
        yoshi.setPosition(posX, posY);

        createObjects();
        //remove out of scope objects (
        this.objects.removeAll(eaten);
        this.objects.removeAll(
                this.objects.stream()
                    .filter(this::isObjectOufOfScope)
                    .collect(Collectors.toList())
        );

//        Logger.info(this, yoshi.getCurrentState() + " " + yoshi.getCurrentSpeed() + " " + yoshi.getPosition());
    }

    public GameInput getGameInput() {
        return gameInput;
    }

    public boolean isObjectOufOfScope(GameObject object){
        //if yoshi falls too much
        if (object.getPosition().y < this.camera.position.y - Constants.CAMERA_HEIGHT / 2 - 40f) {
            Logger.info(object, "becomes out of scope! " + object.getPosition() + " Camera at: " + this.camera.position);
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch) {
        //render the scene
        this.objects.forEach(ho -> ho.draw(batch));
        yoshi.draw(batch);
    }
}
