package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.*;

import java.util.ArrayList;
import java.util.List;

public class App implements NamedElement, Visitable {

	private String name;
	private List<OutputElement> outputElements = new ArrayList<OutputElement>();
	private List<State> states = new ArrayList<State>();
	private List<State> initial = new ArrayList<State>();
	private List<Sensor> sensors = new ArrayList<Sensor>();

	public App(String name) {
		this.name = name;
	}

	public App() {}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public List<OutputElement> getOutputElements() {
		return outputElements;
	}

	public void setBricks(List<OutputElement> outputElements) {
		this.outputElements = outputElements;
	}

	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	public List<Sensor> getSensors() {
		return sensors;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}


	public List<State> getInitial() {
		return initial;
	}

	public void setInitial(List<State> initial) {
		this.initial = initial;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
