package io.github.mosser.arduinoml.ens.model;


import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Visitable {

    private String name;
    private List<Register> memory;
    private List<Action> behavior;

    public Consumer(String name) {
        this.name = name;
    }

    public Consumer() {}

    public Consumer(String name, List<Register> memory) {
        this.name = name;
        this.memory = memory;
    }

    public Consumer(String name, List<Register> memory, List<Action> behavior) {
        this.name = name;
        this.memory = memory;
        this.behavior = behavior;
    }

    public List<Register> getMemory() {
        return memory;
    }

    public void setMemory(List<Register> memory) {
        this.memory = memory;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBehavior(List<Action> behavior) {
        this.behavior = behavior;
    }

    public List<Action> getBehavior() {
        return behavior;
    }

}