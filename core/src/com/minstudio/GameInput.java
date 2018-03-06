package com.minstudio;

public interface GameInput {

    boolean isUpDown();

    boolean isUpPressed();

    boolean isDown();

    boolean isLeftPressed();

    boolean isRightPressed();

    boolean isKeyPressed(int keyCode);
}
