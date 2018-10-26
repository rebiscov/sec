package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class Action implements Visitable {
	
	public Action() {}

	protected SIGNAL[] value;
	protected Actuator actuator;

	public Action(Actuator actuator, SIGNAL[] value) {
		this.actuator = actuator;
		this.value = value;
	}

	
	public SIGNAL[] getValue() {
		return value;
	}

	public void setValue(SIGNAL[] value) {
		this.value = value;
	}

	public Actuator getActuator() {
		return actuator;
	}

	public void setActuator(Actuator actuator) {
		this.actuator = actuator;
	}

	public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
