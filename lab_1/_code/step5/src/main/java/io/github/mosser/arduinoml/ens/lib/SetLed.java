package io.github.mosser.arduinoml.ens.lib;

import io.github.mosser.arduinoml.ens.model.*;

public class SetLed {

    private SetActuator sa;

    public SetLed(SIGNAL value, Led target) {
        SIGNAL[] val = {value};
        sa = new SetActuator(val, target.toActuator());
    }

    public SetLed(SIGNAL value, Led target, Register guard) {
        SIGNAL[] val = {value};
        sa = new SetActuator(val, target.toActuator(), guard);
    }

    public SetLed(SIGNAL value, Led target, Register guard, Boolean onGuardValue) {
        SIGNAL[] val = {value};
        sa = new SetActuator(val, target.toActuator(), guard, onGuardValue);
    }

    public SetActuator toSa() {
        return sa;
    }

    public void fromSa(SetActuator sa) {
        this.sa = sa;
    }

}