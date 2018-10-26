package io.github.mosser.arduinoml.ens.samples;

import io.github.mosser.arduinoml.ens.generator.*;
import io.github.mosser.arduinoml.ens.model.*;
import io.github.mosser.arduinoml.ens.lib.*;
//import static io.github.mosser.arduinoml.ens.model.SIGNAL.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class Example {

	public static void main(String[] args) {
		App theApp = new App("Blinking led!");

        Led led = new Led("Led", 10);
        
        Integer[] pins = {1,2,3,4,5,6,7};
        SevenSeg sevenSeg = new SevenSeg("7SEG", pins);
        
        Sensor button = new Sensor("Button", 13);


        Producer producer = new Producer("producer", button);

        Consumer ledconsumer = new Consumer("ledConsumer");

        Register on = new Register("on", true);
        ledconsumer.getMemory().add(on);
        ledconsumer.getBehavior().add(new SetLed(SIGNAL.HIGH, led, on, true).toSa());
        ledconsumer.getBehavior().add(new SetLed(SIGNAL.LOW, led, on, false).toSa());
        ledconsumer.getBehavior().add(new SetRegister(REGISTERACTION.SWAP, on));
        theApp.getConsumers().add(ledconsumer);
        producer.getTargets().add(ledconsumer);

        Consumer sevensegconsumer = new Consumer("sevenSegConsumer");
        Register[] count = new Register[10];
        for (Integer i = 0; i < 10; i++) {
            count[i] = new Register(String.format("count%s", i), i == 0 ? true : false);
            sevensegconsumer.getMemory().add(count[i]);
        }

        for(Integer i = 0; i < 10; i++) {
            sevensegconsumer.getBehavior().add(new SetSevenSeg(i, sevenSeg, count[i], true).toSa());
            sevensegconsumer.getBehavior().add(new SetRegister(count[i], REGISTERACTION.SWAP, count[(i+1)%10]));
            sevensegconsumer.getBehavior().add(new SetRegister(count[i], REGISTERACTION.SWAP, count[i]));
        }
        theApp.getConsumers().add(sevensegconsumer);
        producer.getTargets().add(sevensegconsumer);

        Visitor codeGenerator = new ToC();
        
        theApp.setSensors(new ArrayList<>(Arrays.asList(button)));
        theApp.setActuators(new ArrayList<>(Arrays.asList(led.toActuator(), sevenSeg.toActuator())));
        theApp.getProducers().add(producer);
        theApp.accept(codeGenerator);


		// Writing C files
        try {
            System.out.println("Generating C code: ./output/main.c");
            String code =  codeGenerator.getCode().toString();
            System.out.println(code);
            Files.write(Paths.get("./output/main.c"), code.getBytes());
            System.out.println("Code generation: done");
            System.out.println("Board upload : cd output && make upload && cd ..;");
        } catch (IOException e) {
            System.err.println(e);
        }
	}

}
