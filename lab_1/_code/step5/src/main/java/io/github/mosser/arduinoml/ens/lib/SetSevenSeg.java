package io.github.mosser.arduinoml.ens.lib;

import io.github.mosser.arduinoml.ens.model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SetSevenSeg {

    private SetActuator sa;

    public SetSevenSeg(SEVENSEGNUMBER value, SevenSeg target) {
        sa = new SetActuator(numbertosignal(value), target.toActuator());
    }

    public SetSevenSeg(SEVENSEGNUMBER value, SevenSeg target, Register guard) {
        sa = new SetActuator(numbertosignal(value), target.toActuator(), guard);
    }

    public SetSevenSeg(SEVENSEGNUMBER value, SevenSeg target, Register guard, Boolean onGuardValue) {
        sa = new SetActuator(numbertosignal(value), target.toActuator(), guard, onGuardValue);
    }

    public SetSevenSeg(Integer value, SevenSeg target) {
        sa = new SetActuator(numbertosignal(fromint(value)), target.toActuator());
    }

    public SetSevenSeg(Integer value, SevenSeg target, Register guard) {
        sa = new SetActuator(numbertosignal(fromint(value)), target.toActuator(), guard);
    }

    public SetSevenSeg(Integer value, SevenSeg target, Register guard, Boolean onGuardValue) {
        sa = new SetActuator(numbertosignal(fromint(value)), target.toActuator(), guard, onGuardValue);
    }

    public SetActuator toSa() {
        return sa;
    }

    public void fromSa(SetActuator sa) {
        this.sa = sa;
    }

    private SIGNAL[] numbertosignal(SEVENSEGNUMBER nb) {
		List<Integer> pinOn = new ArrayList<Integer>();

		switch (nb) {
			case ZERO: pinOn = Arrays.asList(0,1,2,3,4,5); break;
			case ONE: pinOn = Arrays.asList(1,2); break;
			case TWO: pinOn = Arrays.asList(0,1,3,4,6); break;
			case THREE: pinOn = Arrays.asList(0,1,2,3,6); break;
			case FOUR: pinOn = Arrays.asList(1,2,5,6); break;
			case FIVE: pinOn = Arrays.asList(0,2,3,5,6); break;
			case SIX: pinOn = Arrays.asList(0,2,3,4,5,6); break;
			case SEVEN: pinOn = Arrays.asList(0,1,2); break;
			case EIGHT: pinOn = Arrays.asList(0,1,2,3,4,5,6); break;
			case NINE: pinOn = Arrays.asList(0,1,2,3,5,6); break;
		}

		SIGNAL[] ret = new SIGNAL[7];
		for(int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				ret[i] = SIGNAL.LOW;
			else
				ret[i] = SIGNAL.HIGH;
		}

		return ret;
	}

	public SEVENSEGNUMBER fromint(int nb) {
		switch (nb) {
			case 1: return SEVENSEGNUMBER.ONE;
			case 2: return SEVENSEGNUMBER.TWO;
			case 3: return SEVENSEGNUMBER.THREE;
			case 4: return SEVENSEGNUMBER.FOUR;
			case 5: return SEVENSEGNUMBER.FIVE;
			case 6: return SEVENSEGNUMBER.SIX;
			case 7: return SEVENSEGNUMBER.SEVEN;
			case 8: return SEVENSEGNUMBER.EIGHT;
			case 9: return SEVENSEGNUMBER.NINE;
			default: return SEVENSEGNUMBER.ZERO;
		}
	}

}