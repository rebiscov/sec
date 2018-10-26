package io.github.mosser.arduinoml.ens.lib;

import io.github.mosser.arduinoml.ens.model.*;

public class Led {


    private Actuator led;

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

    public void fromActuator(Actuator led) {
        this.led = led;
    }

    public Actuator toActuator() {
        return led;
    }

}