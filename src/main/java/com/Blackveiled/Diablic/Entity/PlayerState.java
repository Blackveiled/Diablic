package com.Blackveiled.Diablic.Entity;

import java.util.ArrayList;
import java.util.List;

public class PlayerState {

    public enum State   {

        IDLE, COMBAT, STUNNED, FROZEN, CASTING, INTERACTING;

        private boolean active = false;
        private long timeStarted = 0;
        private long duration = 0;

        State() {
            timeStarted = System.currentTimeMillis();
        }

        public long getTimeStarted()    {
            return timeStarted;
        }

        public long getDuration()   {
            return duration;
        }

        public void setTimeStarted(long d)  {
            timeStarted = d;
        }

        public void setDuration(long d) {
            duration = d;
        }

        @Override
        public String toString()    {
            switch(ordinal())   {
                case 0:
                    return "Idle";
                case 1:
                    return "Combat";
                case 2:
                    return "Stunned";
                case 3:
                    return "Frozen";
                case 4:
                    return "Casting";
                case 5:
                    return "Interacting";
            }
            return null;
        }

    }

    private List<State> states = new ArrayList();

    public PlayerState()    {

    }

    public List<State> states() {
        return states;
    }


}
