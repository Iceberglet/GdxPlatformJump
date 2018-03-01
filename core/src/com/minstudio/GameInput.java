package com.minstudio;

public interface GameInput {

    boolean isUp();

    boolean isDown();

    boolean isLeft();

    boolean isRight();

    boolean isKeyPressed(int keyCode);
}
