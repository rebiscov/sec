package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class Actuator extends OutputElement implements Visitable {

    private int pin;

    public Actuator(String name, int pin) {
        this.pin = pin;
        this.name = name;
    }

    public int getPin() {
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
