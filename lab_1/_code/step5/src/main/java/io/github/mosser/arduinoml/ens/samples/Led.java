package io.github.mosser.arduinoml.ens.samples;

import io.github.mosser.arduinoml.ens.model.*;
import io.github.mosser.arduinoml.ens.generator.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Led {

	public static void main(String[] args) {

		// Declaring LED
		Actuator led = new Actuator("LED", 13);

		Producer button = new Producer("Button", 10);
		button.setValue(true);

		int[] pins = {1,2,3,4,5,6,7};
		SevenSeg sevenSeg = new SevenSeg("7seg", pins);

		Register led_flag = new Register("LED_FLAG");
		Register sevenseg_flag = new Register("SEVENSEG_FLAG");

		SetActuator change_led = new SetActuator(led);
		change_led.setGuard(led_flag);

		SetSevenSeg changeseg = new SetSevenSeg(sevenseg_flag, sevenSeg);

		Consumer consumer1 = new Consumer("Consumer1", Arrays.asList(led_flag, sevenseg_flag), Arrays.asList(change_led, changeseg));
		
		button.setConsumers(Arrays.asList(consumer1));




		// Building the App
		App theSwitch = new App("Arduino");
		theSwitch.setActuators(Arrays.asList(led));
		theSwitch.setProducers(Arrays.asList(button));
		theSwitch.setConsumers(Arrays.asList(consumer1));
		theSwitch.setSevenSegs(Arrays.asList(sevenSeg));
		
		
		//theSwitch.setSensors(Arrays.asList(button));

		// Generating Code
		Visitor codeGenerator = new ToC();
		theSwitch.accept(codeGenerator);

		// Writing C files
        try {
            System.out.println("Generating C code: ./output/fsm.h");
            Files.write(Paths.get("./output/fsm.h"), codeGenerator.getHeaders().toString().getBytes());
            System.out.println("Generating C code: ./output/main.c");
            Files.write(Paths.get("./output/main.c"), codeGenerator.getCode().toString().getBytes());
            System.out.println("Code generation: done");
            System.out.println("Board upload : cd output && make upload && cd ..;");
        } catch (IOException e) {
            System.err.println(e);
        }
	}

}
