package com.minstudio.core;

import com.badlogic.gdx.Gdx;

public class Logger {

    public static void info(Object caller, String s) {
        Gdx.app.log(caller.getClass().getSimpleName(), s);
    }

}
