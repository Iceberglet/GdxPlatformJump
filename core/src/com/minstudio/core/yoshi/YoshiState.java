package com.minstudio.core.yoshi;

public abstract class YoshiState {

    private final Yoshi.State state;

    public YoshiState(Yoshi.State state){
        this.state = state;
    }

    public abstract void onStateStart(Yoshi yoshi);

    public abstract void onStateEnd(Yoshi yoshi);

}
