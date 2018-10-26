package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class Sensor implements Visitable {

    private Integer pin;
    private String name;

    public Sensor (String name, Integer pin) {
        this.pin = pin;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getPin() {
        return pin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    @Override 
    public void accept(Visitor visitor) { 
        visitor.visit(this);
    }

}