package com.minstudio.core;

public class Constants {

    //animation related
    public static final long RUNNING_DURATION = 600L;
    public static final long EAT_DURATION = 1600L;
    public static final long HIT_DURATION = 400L;
    public static final long TONGUE_DURATION = 600L;
    //physics and speed
    public static float G = -0.0013f; //downwards
    public static float MAX_DOWNWARD_SPEED = -0.25f;
    public static float YOSHI_HORIZONTAL_SPEED = 0.095f;
    public static float YOSHI_JUMP_SPEED = 0.46f;
    public static float CAMERA_ASCEND = 0.02f;

    //Camera
    public static final int CAMERA_WIDTH = 256 + 128;
    public static final int CAMERA_HEIGHT = 512;

    //For Object creation
    public static final int PLATFORM_GAP = 80;
}
