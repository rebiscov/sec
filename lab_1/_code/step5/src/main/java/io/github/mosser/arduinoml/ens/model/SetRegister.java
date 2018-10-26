package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;


public class SetRegister extends Action {

    private Register target;
    private REGISTERACTION action;

    public SetRegister(REGISTERACTION action, Register target) {
        this.target = target;
        this.action = action;
    }

    public SetRegister(Register guard, REGISTERACTION action, Register target) {
        this.target = target;
        this.action = action;
        this.guard = guard;
    }

    public Register getTarget() {
        return target;
    }

    public void setTarget(Register target) {
        this.target = target;
    }
    

    @Override 
    public void accept(Visitor visitor) { 
        visitor.visit(this);
    }

    public void setAction(REGISTERACTION action) {
        this.action = action;
    }

    public REGISTERACTION getAction() {
        return action;
    }

}

