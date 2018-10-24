package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitor;

public class SevenSeg extends OutputElement {

    private int[] pins;

    public SevenSeg(String name, int[] pins) {
        this.name = name;
        this.pins = pins;
    }
    
    public int[] getPins() {
        return pins;
    }

    public void setPins(int[] pins) {
        this.pins = pins;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

enum SevenSegState {

}