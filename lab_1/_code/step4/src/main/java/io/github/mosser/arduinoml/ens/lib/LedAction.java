package io.github.mosser.arduinoml.ens.lib;


import io.github.mosser.arduinoml.ens.model.*;

import io.github.mosser.arduinoml.ens.lib.Led;

public class LedAction {

	private Action action;

	public LedAction() { action = new Action(); }


	public LedAction(Led led, SIGNAL value) {
		SIGNAL[] val = {value}; 
		this.action = new Action(led.toActuator(), val);
	}

	
	public SIGNAL getValue() {
		return action.getValue()[0];
	}

	public void setValue(SIGNAL value) {
		SIGNAL[] val = {value}; 
		this.action.setValue(val);
	}

	public Action toAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void fromAction(Action action) {
		this.action = action;
	}

}
