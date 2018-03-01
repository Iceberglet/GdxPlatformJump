package com.minstudio.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.minstudio.GameInput;

public class DesktopGameInput implements GameInput {

    @Override
    public boolean isUp() {
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    @Override
    public boolean isDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    @Override
    public boolean isLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    @Override
    public boolean isRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    @Override
    public boolean isKeyPressed(int keyCode){
        return Gdx.input.isKeyPressed(keyCode);
    }
}
