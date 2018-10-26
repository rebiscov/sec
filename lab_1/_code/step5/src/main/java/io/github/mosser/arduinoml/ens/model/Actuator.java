package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class Actuator implements Visitable {
   

    private String name;
    private Integer[] pin;

    public Actuator(String name, Integer[] pin) {
        this.name = name;
        this.pin = pin;
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

    /**
     * @return the pin
     */
    public Integer[] getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(Integer[] pin) {
        this.pin = pin;
    }

    @Override 
    public void accept(Visitor visitor) { 
        visitor.visit(this); 
    }
}
