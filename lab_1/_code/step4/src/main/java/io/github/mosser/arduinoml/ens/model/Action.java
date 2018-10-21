package io.github.mosser.arduinoml.ens.model;

import io.github.mosser.arduinoml.ens.generator.Visitable;
import io.github.mosser.arduinoml.ens.generator.Visitor;

public class Action implements Visitable {
	
	public Action() {}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
