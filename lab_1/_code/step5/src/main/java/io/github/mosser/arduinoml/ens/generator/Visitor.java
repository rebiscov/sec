package io.github.mosser.arduinoml.ens.generator;

import io.github.mosser.arduinoml.ens.model.*;

public abstract class Visitor<T> {

	public abstract void visit(App app);
	public abstract void visit(Actuator actuator);
	public abstract void visit(SetActuator setActuator);
	public abstract void visit(SevenSeg sevenseg);
	public abstract void visit(Register register);
	public abstract void visit(Producer producer);
	public abstract void visit(Consumer consumer);
	public abstract void visit(SetRegister setRegister);
	public abstract void visit(SetSevenSeg setSevenSeg);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	T code;
	T headers;

	public T getCode() {
		return code;
	}
	public T getHeaders() {
		return headers;
	}

}

