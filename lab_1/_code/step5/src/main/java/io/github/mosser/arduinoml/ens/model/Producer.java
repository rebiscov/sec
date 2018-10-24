package io.github.mosser.arduinoml.ens.model;


import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Producer implements Visitable {

    private String name;
    private List<Consumer> consumers;
    private Integer pin;
    private Boolean value;

    public Producer(String name) {
        this.name = name;
    }

    public Producer() {}


    public Producer(String name, int pin) {
        this.name = name;
        this.pin = pin;
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

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Integer getPin() {
        return pin;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }
    
    public void setConsumers(List<Consumer> consumers) {
        this.consumers = consumers;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    
}