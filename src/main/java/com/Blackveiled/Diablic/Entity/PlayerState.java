package com.Blackveiled.Diablic.Entity;

public enum PlayerState {

    IDLE(0), COMBAT(1), STUNNED(2), FROZEN(3), CASTING(4), INTERACTING(5);

    private int state;

    PlayerState(int state)  {
        this.state = state;
    }
}
