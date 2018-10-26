package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;
import io.github.mosser.arduinoml.ens.model.Action;

import java.util.*;

public class Consumer implements Visitable {

    private String name;
    private ArrayList<Action> behavior = new ArrayList<Action>();
    private ArrayList<Register> memory = new ArrayList<Register>();

    public Consumer(String name) {
        this.name = name;
    }

    /**
     * @param behavior the behavior to set
     */
    public void setBehavior(ArrayList<Action> behavior) {
        this.behavior = behavior;
    }

    /**
     * @return the behavior
     */
    public ArrayList<Action> getBehavior() {
        return behavior;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(ArrayList<Register> memory) {
        this.memory = memory;
    }

    /**
     * @return the memory
     */
    public ArrayList<Register> getMemory() {
        return memory;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }



    @Override 
    public void accept(Visitor visitor) { 
        visitor.visit(this); 
    }
}
