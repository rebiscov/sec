package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.*;

import java.util.ArrayList;
import java.util.List;

public class App implements Visitable {

	private String name;
	private List<Actuator> actuators = new ArrayList<Actuator>();
	private List<Producer> producers = new ArrayList<Producer>();
	private List<Consumer> consumers = new ArrayList<Consumer>();
	private List<SevenSeg> sevenSegs = new ArrayList<SevenSeg>();

	public App(String name) {
		this.name = name;
	}

	public App() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActuators(List<Actuator> actuators) {
		this.actuators = actuators;
	}

	public List<Actuator> getActuators() {
		return actuators;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public List<Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(List<Consumer> consumers) {
		this.consumers = consumers;
	}

	public List<Producer> getProducers() {
		return producers;
	}

	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}

	public void setSevenSegs(List<SevenSeg> sevenSegs) {
		this.sevenSegs = sevenSegs;
	}

	public List<SevenSeg> getSevenSegs() {
		return sevenSegs;
	}


}
