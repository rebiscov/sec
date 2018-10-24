package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class SetSevenSeg implements Action, Visitable {

    private Register guard;
    private SevenSeg target;
    private Integer number;


    public SetSevenSeg() {}

    public SetSevenSeg(Register guard) {
        this.guard = guard;
    }

    public SetSevenSeg(Register guard, SevenSeg target) {
        this.guard = guard;
        this.target = target;
    }


    public void setTarget(SevenSeg target) {
        this.target = target;
    }

    public SevenSeg getTarget() {
        return target;
    }

    @Override
    public void setGuard(Register guard) {
        this.guard = guard;
    }

    @Override
    public Register getGuard() {
        return guard;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }


}