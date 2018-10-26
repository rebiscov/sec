package io.github.mosser.arduinoml.ens.lib;

import io.github.mosser.arduinoml.ens.lib.*;

import io.github.mosser.arduinoml.ens.model.Action;
import io.github.mosser.arduinoml.ens.model.SIGNAL;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class SevenSegAction {

	private Action action;
	
	public SevenSegAction() { action = new Action() ;}

	public SevenSegAction(SevenSeg sevenSeg, SEVENSEGNUMBER value) {
		action = new Action(sevenSeg.toActuator(), numbertosignal(value));
	}

	public SevenSegAction(SevenSeg sevenSeg, Integer value) {
		action = new Action(sevenSeg.toActuator(), numbertosignal(fromint(value)));
	}

	public void fromAction(Action action) {
		this.action = action;
	}

	public Action toAction() {
		return action;
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

	private int toint(SEVENSEGNUMBER nb) {
		switch (nb) {
			case ZERO: return 0;
			case ONE: return 1;
			case TWO: return 2;
			case THREE: return 3;
			case FOUR: return 4;
			case FIVE: return 5;
			case SIX: return 6;
			case SEVEN: return 7;
			case EIGHT: return 8;
			case NINE: return 9;
		}
		return -1;
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