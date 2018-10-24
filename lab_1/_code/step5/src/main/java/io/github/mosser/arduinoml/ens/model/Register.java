package io.github.mosser.arduinoml.ens.model;


import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class Register implements Visitable {

    private String name;
    private Boolean value;

    public Register() {}

    public Register(String name) {
        this.name = name;
    }

    public Register(String name, Boolean value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Boolean getValue() {
        return value;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}