package io.github.mosser.arduinoml.ens.lib;


import io.github.mosser.arduinoml.ens.model.*;

public class SevenSeg {


    private Actuator sevenSeg;

    public SevenSeg(String name, Integer[] pins) {
        this.sevenSeg = new Actuator(name, pins);
    }


    public void fromActuator(Actuator sevenSeg) {
        this.sevenSeg = sevenSeg;
    }

    public Actuator toActuator() {
        return sevenSeg;
    }

}