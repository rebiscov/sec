package io.github.mosser.arduinoml.ens.lib;

import io.github.mosser.arduinoml.ens.model.*;

import java.util.List;

public class Led {


    private Actuator led;

    public Led(String name) {
        this.led = new Actuator(name);
    }

    public Led(String name, Integer pin) {
        Integer tabpin[] = { pin }; 
        this.led = new Actuator(name, tabpin);
    }

    public Integer getPin() {
        return led.getPin()[0];
    }

    public void setPin(Integer pin) {
        Integer tabpin[] = { pin }; 
        led.setPin(tabpin);
    }

    public List<State> getListOfStates() {
        return led.getListOfStates();
    }

    public void setStates(List<State> states) {
        led.setStates(states);
    }

    public void addState(State state) {
        led.getListOfStates().add(state);
    }


    public void fromActuator(Actuator led) {
        this.led = led;
    }

    public Actuator toActuator() {
        return led;
    }

}