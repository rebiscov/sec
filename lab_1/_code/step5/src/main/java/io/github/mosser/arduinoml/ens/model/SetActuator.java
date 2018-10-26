package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;
import io.github.mosser.arduinoml.ens.model.SIGNAL;

public class SetActuator extends Action {

    private SIGNAL[] value;
    private Actuator target;

    public SetActuator(SIGNAL[] value, Actuator target) {
        this.value = value;
        this.target = target;
    }

    public SetActuator(SIGNAL[] value, Actuator target, Register guard) {
        this.value = value;
        this.target = target;
        this.guard = guard;
    }

    public SetActuator(SIGNAL[] value, Actuator target, Register guard, Boolean onGuardValue) {
        this.value = value;
        this.target = target;
        this.guard = guard;
        this.onGuardValue = onGuardValue;
    }

    /**
     * @return the target
     */
    public Actuator getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Actuator target) {
        this.target = target;
    }

    /**
     * @return the value
     */
    public SIGNAL[] getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(SIGNAL[] value) {
        this.value = value;
    }

    @Override 
    public void accept(Visitor visitor) { 
        visitor.visit(this); 
    }
}
