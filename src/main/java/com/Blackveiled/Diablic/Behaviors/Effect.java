package com.Blackveiled.Diablic.Behaviors;

public class Effect {

    public enum EffectType  {

        DAMAGE(0),
        PERSISTENT(1),
        MODIFICATION(2);

        private int effectType;

        EffectType(int i)   {
            this.effectType = i;
        }
    }
}
