package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class SetRegister implements Action, Visitable {

    private Register guard;
    private Register target;

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
    
    public void setTarget(Register target) {
        this.target = target;
    }

    public Register getTarget() {
        return target;
    }

}