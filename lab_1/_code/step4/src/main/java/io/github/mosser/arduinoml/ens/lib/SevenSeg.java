package io.github.mosser.arduinoml.ens.lib;


import io.github.mosser.arduinoml.ens.model.*;

import java.util.List;

public class SevenSeg {


    private Actuator sevenSeg;

    public SevenSeg(String name) {
        this.sevenSeg = new Actuator(name);
    }

    public SevenSeg(String name, Integer[] pins) {
        this.sevenSeg = new Actuator(name, pins);
    }


    public void fromActuator(Actuator sevenSeg) {
        this.sevenSeg = sevenSeg;
    }

    public Actuator toActuator() {
        return sevenSeg;
    }



    public List<State> getListOfStates() {
        return sevenSeg.getListOfStates();
    }

    public void setStates(List<State> states) {
        sevenSeg.setStates(states);
    }

    public void addState(State state) {
        sevenSeg.getListOfStates().add(state);
    }
}