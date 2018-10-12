package io.github.mosser.arduinoml.ens.model;


import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class SensorTransition implements Visitable, NamedElement{

    private State nextIfLow, nextIfHigh;
    private Sensor sensor;
    private String name;


    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    public Sensor getSensor() {
        return sensor;
    }

    public void setNextIfHigh(State state){ 
        this.nextIfHigh = state; 
    }
    public State getNextIfHigh(){ 
        return this.nextIfHigh; 
    }

    public void setNextIfLow(State state){ 
        this.nextIfLow = state; 
    }
    public State getNextIfLow(){ 
        return this.nextIfLow; 
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void setName(String name) {
        this.name = name;
    }
	public String getName() { return name; }

}
