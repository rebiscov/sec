package io.github.mosser.arduinoml.ens.generator;

import io.github.mosser.arduinoml.ens.model.*;


public abstract class Visitor {

	public abstract void visit(Actuator actuator);
	public abstract void visit(App app);
	public abstract void visit(Consumer consumer);
    public abstract void visit(Producer producer);
    public abstract void visit(Sensor sensor);
    public abstract void visit(SetActuator setActuator);
    public abstract void visit(SetRegister setRegistry);


	/***********************
	 ** Helper mechanisms **
	 ***********************/

	StringBuffer code;

	public StringBuffer getCode() {
		return code;
	}

}

