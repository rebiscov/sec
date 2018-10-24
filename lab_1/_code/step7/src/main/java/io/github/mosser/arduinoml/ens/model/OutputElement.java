package io.github.mosser.arduinoml.ens.model;


import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

import java.util.List;
import java.util.ArrayList;

public class OutputElement implements NamedElement, Visitable {

    protected String name;
    protected List<State> states = new ArrayList<State>();

    public OutputElement(String name) {
        this.name = name;
    }

    public OutputElement() {}

    @Override
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

    public void setListOfStates(List<State> states) {
        this.states = states;
    }

    public List<State> getListOfStates() {
        return states;
    }

    public void addState(State state) {
        this.states.add(state);
    }

}