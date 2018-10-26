package io.github.mosser.arduinoml.ens.generator;

import io.github.mosser.arduinoml.ens.model.*;


public class ToC extends Visitor {

    public ToC() { this.code = new StringBuffer(); }

	private void c(String s) {
		code.append(String.format("%s\n",s));
	}

	@Override public void visit(App app) {
		c("// C code generated from an object model");
		c(String.format("// Application name: %s\n", app.getName()));
		c("#include <avr/io.h>");
		c("#include <util/delay.h>");
		c("#include <Arduino.h>");
		c("");

		c("void setup(){");
		for(Actuator a: app.getActuators()){
			a.accept(this);
        }
        for(Sensor s: app.getSensors()){
            s.accept(this);
        }
		c("}\n");

        // Visit producers
        for(Producer p: app.getProducers())
            p.accept(this);

		// Visit consumers
        for(Consumer c: app.getConsumers())
            c.accept(this);

        // Generating the Main loop
        c("int main(void) {");
        c("  setup();");
        c("  while(1) {");
        for(Producer p: app.getProducers())
            c(String.format("    producer_%s();", p.getName()));
        for(Consumer c: app.getConsumers())
            c(String.format("    consumer_%s();", c.getName()));
        c("    _delay_ms(1000);");
        c("  }");
        c("  return 0;");
        c("}");
	}


	@Override public void visit(Actuator actuator) {
        for (Integer i: actuator.getPin())
		    c(String.format("  pinMode(%d, OUTPUT); // %s [Actuator]", i, actuator.getName()));
	}


    @Override public void visit(Producer producer) {
        for(Consumer c: producer.getTargets())
        {
            c(String.format("boolean C_FLAG_%s;", c.getName()));
        }
        
        c(String.format("void producer_%s() {", producer.getName()));

        if (producer.getSensor() != null){
            for(Consumer c: producer.getTargets())
            {
                c(String.format("  if (digitalRead(%d))", producer.getSensor().getPin()));
                c(String.format("    C_FLAG_%s = true;", c.getName(), producer.getValue()));
                c("  else");
                c(String.format("    C_FLAG_%s = false;", c.getName(), producer.getValue()));
            }
        }
        else {
            for(Consumer c: producer.getTargets())
            {
                c(String.format("  C_FLAG_%s = %s;", c.getName(), producer.getValue()));
            }
        }
        
        
        c("}\n");
    }

	@Override
	public void visit(Consumer consumer) {
        for(Register r: consumer.getMemory())
            c(String.format("boolean %s;", r.getName()));
        c(String.format("void consumer_%s() {", consumer.getName()));
        c(String.format("  if (!C_FLAG_%s) { return ; }", consumer.getName()));
        for(Action a: consumer.getBehavior())
            a.accept(this);
        c("}\n");
	}

	@Override public void visit(SetActuator setActuator) {
        if(setActuator.getGuard() != null) {
            if(setActuator.getOnGuardValue())
                c(String.format("  if(%s) {", setActuator.getGuard().getName()));
            else
                c(String.format("  if(!%s) {", setActuator.getGuard().getName()));
        }
        for (Integer i = 0 ; i < setActuator.getTarget().getPin().length ; i++) {
            c(String.format("    digitalWrite(%d,%s);", setActuator.getTarget().getPin()[i], setActuator.getValue()[i]));
        }
        if (setActuator.getGuard() != null) {
            c("  }");
        }
	}

	@Override public void visit(SetRegister setRegister) {
        if(setRegister.getGuard() != null) {
            if(setRegister.getOnGuardValue())
                c(String.format("  if(%s)", setRegister.getGuard().getName()));
            else
                c(String.format("  if(!%s)", setRegister.getGuard().getName()));
        }
        switch (setRegister.getAction()) {
            case TRUE:
                c(String.format("    %s = true;", setRegister.getTarget().getName()));
                break;
            case FALSE:
                c(String.format("    %s = false;", setRegister.getTarget().getName()));
                break;
            case SWAP:
                c(String.format("    %s = !%s;", setRegister.getTarget().getName(), setRegister.getTarget().getName()));
                break;
        }
    }
    
    @Override
    public void visit(Sensor sensor) {
        c(String.format("  pinMode(%d, INPUT); // %s [Sensor]", sensor.getPin(), sensor.getName()));
    }

}
