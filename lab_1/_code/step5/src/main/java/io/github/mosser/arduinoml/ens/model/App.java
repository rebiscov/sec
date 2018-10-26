package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

import java.util.ArrayList;

public class App implements Visitable{

    private ArrayList<Producer> producers = new ArrayList<>();
    private ArrayList<Consumer> consumers = new ArrayList<>();
    private ArrayList<Actuator> actuators = new ArrayList<>();
    private ArrayList<Sensor> sensors = new ArrayList<>();
    private String name;

    public App(String name) {
        this.name = name;
    }

    public ArrayList<Producer> getProducers() { 
        return producers;
    }

    public void setConsumers(ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setActuators(ArrayList<Actuator> actuators) {
        this.actuators = actuators;
    }

    public ArrayList<Actuator> getActuators() {
        return actuators;
    }

    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    public String getName() {
        return name;
    }
    
    @Override 
    public void accept(Visitor visitor) {
         visitor.visit(this);
    }

    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(ArrayList<Sensor> sensors) {
        this.sensors = sensors;
    }
}
