package io.github.mosser.arduinoml.ens.generator;

import io.github.mosser.arduinoml.ens.model.*;

import java.util.List;
import java.nio.channels.AcceptPendingException;
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


	@Override
	public void visit(App app) {
		c("// C code generated from an object model");
		c(String.format("// Application name: %s\n", app.getName()));
		c("#include <avr/io.h>");
		c("#include <util/delay.h>");
		c("#include <Arduino.h>");
		c("#include <fsm.h>");
		c("\n");



		c("// ########## INITIALIZATION #############\n");

		c("void setup(){");
		for(Actuator actuator: app.getActuators()){
			c(String.format("  pinMode(%d, OUTPUT);", actuator.getPin()));
		}
		for(Producer producer: app.getProducers()){
			c(String.format("  pinMode(%d, INPUT);", producer.getPin()));
		}
		for(SevenSeg sevenSeg: app.getSevenSegs()) {
			for(int i = 0; i < sevenSeg.getPins().length ; i++) {
				c(String.format("  pinMode(%d, OUTPUT);", sevenSeg.getPins()[i]));
			}
		}
		c("}\n");
		c("");




		c("// ##### ELECTRONICS AND LOGICAL COMPONENTS\n");

		for (Actuator actuator: app.getActuators()) {
			actuator.accept(this);
		}

		for (SevenSeg sevenSeg: app.getSevenSegs()) {
			sevenSeg.accept(this);
		}
		
		for (Producer producer: app.getProducers()){
			producer.accept(this);
		}

		for (Consumer consumer: app.getConsumers()) {
			consumer.accept(this);
		}

		
		c("int main(void) {");
		c("  setup();");
		c("  while(1) {");
		// First producers which set flags
		c("    // message producer");
		for (Producer producer: app.getProducers()){
			c(String.format("    %s_push();", producer.getName())); 
		}
		// Then consumer which use these flags
		c("    // message consumer");
		for (Consumer consumer: app.getConsumers()) {
			c(String.format("    %s_pull();", consumer.getName()));
		}
		
		c("    // Frequency");
		c("    _delay_ms(1000);");
		c("  }");
		c("  return 0;");
		c("}");
	}


	@Override
	public void visit(Actuator actuator) {
		h(String.format("void change_state_%s();", actuator.getName()));
		c(String.format("boolean %s_on = false;", actuator.getName()));
		c(String.format("void change_state_%s(){", actuator.getName()));
		c(String.format("  if (%s_on){ digitalWrite(13, LOW); }", actuator.getName()));
		c("  else { digitalWrite(13, HIGH); }");
		c(String.format("  %s_on = !%s_on;", actuator.getName(), actuator.getName()));
		c("}");
	}

	@Override
	public void visit(SevenSeg sevenSeg) {
		c(String.format("int sevenseg_nb_%s = %d;", sevenSeg.getName(), 0));
		h(String.format("void change_7seg_%s();", sevenSeg.getName()));
		c(String.format("void change_7seg_%s() {", sevenSeg.getName()));

		c(String.format("  if(sevenseg_nb_%s == 0) {", sevenSeg.getName()));
		List<Integer> pinOn = Arrays.asList(0,1,2,3,4,5);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 1) {", sevenSeg.getName()));
		pinOn = Arrays.asList(1,2);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 2) {", sevenSeg.getName()));
		pinOn = Arrays.asList(0,1,3,4,6);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 3) {", sevenSeg.getName()));
		pinOn = Arrays.asList(0,1,2,3,6);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 4) {", sevenSeg.getName()));
		pinOn = Arrays.asList(1,2,5,6);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 5) {", sevenSeg.getName()));
		pinOn = Arrays.asList(0,2,3,5,6);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 6) {", sevenSeg.getName()));
		pinOn = Arrays.asList(0,2,3,4,5,6);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 7) {", sevenSeg.getName()));
		pinOn = Arrays.asList(0,1,2);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 8) {", sevenSeg.getName()));
		pinOn = Arrays.asList(0,1,2,3,4,5,6);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c(String.format("  else if(sevenseg_nb_%s == 9) {", sevenSeg.getName()));
		pinOn = Arrays.asList(0,1,2,3,5,6);
		for (int i = 0; i < 7; i++) {
			if (pinOn.contains(i)) 
				c(String.format("    digitalWrite(%d,LOW);", sevenSeg.getPins()[i]));
			else
				c(String.format("    digitalWrite(%d,HIGH);", sevenSeg.getPins()[i]));
		}
		c("  }");
		c("  sevenseg_nb_" + sevenSeg.getName() + " = (sevenseg_nb_" + sevenSeg.getName() + "+ 1) % 10;");
		c("}\n");
	}

	@Override
	public void visit(Consumer consumer) {
		h(String.format("void %s_pull();", consumer.getName()));
		c(String.format("void %s_pull() {", consumer.getName()));
		for (Action a: consumer.getBehavior()){
			if(a.getGuard() != null) {
				c(String.format("  if(%s) { ", a.getGuard().getName()));
			}
			if(a instanceof SetActuator){
				c(String.format("    change_state_%s();", ((SetActuator) a).getTarget().getName()));
			}
			else if (a instanceof SetRegister) {
				c(String.format("    set_register_%s();", ((SetRegister) a).getTarget().getName()));
			}
			else if(a instanceof SetSevenSeg) {
				c(String.format("    sevenseg_nb_%s = 0;", ((SetSevenSeg) a).getTarget().getName()));
				c(String.format("    change_7seg_%s();", ((SetSevenSeg) a).getTarget().getName()));
				c("  } else {");
				c(String.format("    change_7seg_%s(); ", ((SetSevenSeg) a).getTarget().getName()));
				
			}
			c("  }");
		}
		for (Action a: consumer.getBehavior()) {
			if(a.getGuard() != null) {
				c(String.format("    %s = false;", a.getGuard().getName()));
			}
		}
		c("}\n");
	}

	@Override
	public void visit(Producer producer) {
		for(Consumer consumer: producer.getConsumers()){
			for(Register r: consumer.getMemory()){
				c(String.format("boolean %s;", r.getName()));
			}
		}
		h(String.format("void %s_push();", producer.getName()));
		c(String.format("void %s_push() {", producer.getName()));
		c(String.format("  if (digitalRead(%d) == HIGH) {", producer.getPin()));
		for(Consumer consumer: producer.getConsumers()){
			for(Register r: consumer.getMemory()) {
				c(String.format("    %s = %s;", r.getName(), producer.getValue()));
			}
		}
		c("  }\n}\n");
	}

	@Override
	public void visit(SetSevenSeg setSevenSeg) {
		
	}


	@Override
	public void visit(Register register) {
		
	}

	@Override
	public void visit(SetActuator setActuator) {
		c(String.format("digitalWrite(%d, %s);", setActuator.getTarget().getPin(), setActuator.getValue()));
	}


	@Override
	public void visit(SetRegister setRegister) {
		// Sert à rien pour l'instant
	}
}