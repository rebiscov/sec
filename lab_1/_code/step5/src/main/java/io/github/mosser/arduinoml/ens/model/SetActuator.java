package io.github.mosser.arduinoml.ens.model;


import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class SetActuator implements Action, Visitable {

    private SIGNAL value;
    private Actuator target;
    private Register guard;

    public SetActuator() {}

    public SetActuator(Actuator target) {
        this.target = target;
    }

    public SetActuator(Actuator target, SIGNAL value) {
        this.target = target;
        this.value = value;
    }

    public SetActuator(Actuator target, SIGNAL value, Register guard) {
        this.target = target;
        this.value = value;
        this.guard = guard;
    }

    public void setValue(SIGNAL value) {
        this.value = value;
    }

    public SIGNAL getValue() {
        return value;
    }

    public Actuator getTarget() {
        return target;
    }

    public void setTarget(Actuator target) {
        this.target = target;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Register getGuard() {
		return guard;
	}

    @Override
	public void setGuard(Register guard) {
		this.guard = guard;
	}

}