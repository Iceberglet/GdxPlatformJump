package com.minstudio.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.minstudio.GameInput;

public class DesktopGameInput implements GameInput {

    @Override
    public boolean isUpDown() {
        return Gdx.input.isKeyJustPressed(Input.Keys.UP);
    }

    @Override
    public boolean isUpPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    @Override
    public boolean isDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    @Override
    public boolean isLeftPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    @Override
    public boolean isRightPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    @Override
    public boolean isKeyPressed(int keyCode){
        return Gdx.input.isKeyPressed(keyCode);
    }

    @Override
    public boolean isSpaceDown() {
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }
}
