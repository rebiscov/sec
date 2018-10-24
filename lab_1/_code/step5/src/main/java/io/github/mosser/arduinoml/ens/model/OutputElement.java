package io.github.mosser.arduinoml.ens.model;


public class OutputElement {

    protected String name;

    public OutputElement(String name) {
        this.name = name;
    }

    public OutputElement() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}