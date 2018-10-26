package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

import java.util.List;
import java.util.ArrayList;

public class Actuator implements NamedElement, Visitable {

    protected Integer[] pin;
    protected String name;
    protected List<State> states = new ArrayList<State>();

    public Actuator() {}

    public Actuator(String name) {
        this.name = name;
    }

    public Actuator(String name, Integer[] pin) {
        this.pin = pin;
        this.name = name;
    }

    public Integer[] getPin() {
        return pin;
    }
    public void setPin(Integer[] pin) {
        this.pin = pin;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<State> getListOfStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
