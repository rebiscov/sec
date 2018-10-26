package io.github.mosser.arduinoml.ens.generator;

import io.github.mosser.arduinoml.ens.model.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ToC extends Visitor<StringBuffer> {

	private final static String CURRENT_STATE = "current_state";

	public ToC() {
		this.code = new StringBuffer();
		this.headers = new StringBuffer();
	}

	private void c(String s) {
		code.append(String.format("%s\n",s));
	}

	private void h(String s) {
		headers.append(String.format("%s\n",s));
	}

	private void pdtAutom(List<State> states1, List<State> states2) {
		for(State s1: states1) {
			for(State s2: states2) {
				h(String.format("void state_%s_%s();", s1.getName(), s2.getName()));
				c(String.format("void state_%s_%s() {", s1.getName(), s2.getName()));
				
				for(Action action: s1.getActions()) {
					action.accept(this);
				}
				for(Action action: s2.getActions()) {
					action.accept(this);
				}

				c("  _delay_ms(1000);");

				if (s1.hasSensor || s2.hasSensor) {
					if (s1.hasSensor) {
						c(String.format("  if (digitalRead(%d) == HIGH)", s1.getSensor().getPin()));
						if (s2.hasSensor && s2.getSensor().getName() == s1.getSensor().getName()) {
							c(String.format("    state_%s_%s();", s1.getNextIfHigh().getName(), s2.getNextIfHigh().getName()));
							c("  else");
							System.out.println(s2.getNext().getName());
							c(String.format("    state_%s_%s();", s1.getNext().getName(), s2.getNext().getName()));
						}
						else if (!s2.hasSensor) {
							c(String.format("    state_%s_%s();", s1.getNextIfHigh().getName(), s2.getNext().getName()));
							c("  else");
							c(String.format("    state_%s_%s();", s1.getNext().getName(), s2.getNext().getName()));
						}
						else {
							c(String.format("    if (digitalRead(%d) == HIGH)", s2.getSensor().getPin()));
							c(String.format("      state_%s_%s();", s1.getNextIfHigh().getName(), s2.getNextIfHigh().getName()));
							c("    else");
							c(String.format("      state_%s_%s();", s1.getNextIfHigh().getName(), s2.getNext().getName()));
							c("  else");
							c(String.format("    if (digitalRead(%d) == HIGH)", s2.getSensor().getPin()));
							c(String.format("      state_%s_%s();", s1.getNext().getName(), s2.getNextIfHigh().getName()));
							c("    else");
							c(String.format("      state_%s_%s();", s1.getNext().getName(), s2.getNext().getName()));
						}
					 }
					 else {
						c(String.format("  if (digitalRead(%d) == HIGH)", s2.getSensor().getPin()));
						c(String.format("    state_%s_%s();", s1.getNext().getName(), s2.getNextIfHigh().getName()));
						c("  else");
						c(String.format("    state_%s_%s();", s1.getNext().getName(), s2.getNext().getName()));
					 }
				}
				else {
					c(String.format("  state_%s_%s();", s1.getNext().getName(), s2.getNext().getName()));
				}
				c("}");
			}
		}
	}

	private String concatList(List<State> list) {
		String result = "";
		for (State e: list) {
			result += String.format("_%s", e.getName());
		}
		return result;
	}

	@Override
	public void visit(App app) {
		c("// C code generated from an object model");
		c(String.format("// Application name: %s\n", app.getName()));
		c("#include <avr/io.h>");
		c("#include <util/delay.h>");
		c("#include <Arduino.h>");
		c("#include <fsm.h>");
		c("");
		c("void setup(){");
		// Initialisation des actuators
		for(Actuator a: app.getActuators()){
			a.accept(this);
		}
		// Initialisation des Sensors
		for(Sensor s: app.getSensors()){
			s.accept(this);
		}
		c("}\n");

		if (app.getActuators().size() == 1) {
			for(State state: app.getStates()){
				h(String.format("void state_%s();", state.getName()));
				state.accept(this);
			}
			
		}
		else {
			pdtAutom(app.getActuators().get(0).getListOfStates(), app.getActuators().get(1).getListOfStates());
		}
		
		if (app.getInitial() != null) {
			c("int main(void) {");
			c("  setup();");
			c(String.format("  state%s();", concatList(app.getInitial())));
			c("  return 0;");
			c("}");
		}
	}

	@Override
	public void visit(Actuator actuator) {
		for (int i: actuator.getPin()) {
			 c(String.format("  pinMode(%d, OUTPUT); // %s [Actuator]", i, actuator.getName()));
		}
	}
	
	@Override
	public void visit(Sensor sensor) {
		c(String.format("  pinMode(%d, INPUT); // %s [Sensor]", sensor.getPin(), sensor.getName()));
	}

	@Override
	public void visit(State state) {
		c(String.format("void state_%s() {",state.getName()));
		for(Action action: state.getActions()) {
			action.accept(this);
		}
		c("  _delay_ms(1000);");
        if (state.hasSensor) {
            c(String.format("  if (digitalRead(%d) == HIGH)", state.getSensor().getPin()));
		    c(String.format("     state_%s();", state.getNextIfHigh().getName()));
		                  c("  else");
		    c(String.format("     state_%s();", state.getNext().getName()));
        } else {
		c(String.format("  state_%s();", state.getNext().getName()));
        }
		c("}");
	}


	@Override
	public void visit(Action action) {
		for(int i = 0; i < action.getValue().length; i++) {
			c(String.format("  digitalWrite(%d,%s);",action.getActuator().getPin()[i],action.getValue()[i]));
		}
	}

}