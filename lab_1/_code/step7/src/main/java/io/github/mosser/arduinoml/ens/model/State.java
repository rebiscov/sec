package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

import java.util.ArrayList;
import java.util.List;

public class State implements NamedElement, Visitable {

	private String name;
	private List<Action> actions = new ArrayList<Action>();
	private State next, nextIfHigh;
	public  boolean hasSensor = false;
	private Sensor sensor;

	public State(String name) {
		this.name = name;
	}

	public State(String name, Sensor sensor) {
		this.name = name;
		this.sensor = sensor;
		this.hasSensor = true;
	}

	public State() {}

	@Override public String getName() {
		return name;
	}
	@Override public void setName(String name) {
		this.name = name;
	}

	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public void setSensor(Sensor sensor){
		this.sensor = sensor;
		hasSensor = true;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public State getNext() {
		return next;
	}
	public void setNext(State next) {
		this.next = next;
	}

	public void setNextIfHigh(State state){ 
        this.nextIfHigh = state; 
    }

    public State getNextIfHigh(){ 
        return this.nextIfHigh; 
    }
	

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
