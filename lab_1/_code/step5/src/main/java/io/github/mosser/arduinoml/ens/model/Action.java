package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;

public abstract class Action implements Visitable {

    protected Register guard = null;
    protected Boolean onGuardValue = true;

    public Register getGuard() { 
        return guard; 
    }

    public void setGuard(Register guard) {
        this.guard = guard;
    }

    public void setGuard(Register guard, Boolean onGuardValue) {
        this.guard = guard;
        this.onGuardValue = onGuardValue;
    }

    /**
     * @param onGuardValue the onGuardValue to set
     */
    public void setOnGuardValue(Boolean onGuardValue) {
        this.onGuardValue = onGuardValue;
    }

    /**
     * @return the onGuardValue
     */
    public Boolean getOnGuardValue() {
        return onGuardValue;
    }
}
