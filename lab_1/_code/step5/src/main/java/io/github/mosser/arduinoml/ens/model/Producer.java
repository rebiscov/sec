package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

import java.util.ArrayList;

public class Producer implements Visitable {

    private String name;
    private Boolean value;
    private ArrayList<Consumer> targets = new ArrayList<Consumer>();
    private Sensor sensor;

    public Producer(String name, Boolean value) {
        this.name = name;
        this.value = value;
    }

    public Producer(String name, Sensor sensor) {
        this.name = name;
        this.sensor = sensor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Consumer> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Consumer> targets) {
        this.targets = targets;
    }

    public Boolean getValue() {
        return value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    @Override 
    public void accept(Visitor visitor) { 
        visitor.visit(this); 
    }

}