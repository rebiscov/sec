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

		// Declaring 7-seg
		int[] pins = {1,2,3,4,5,6,7};
		SevenSeg sevenSeg = new SevenSeg("7SEG", pins);

		// Declaring button
		Sensor button = new Sensor("button", 10);

		// Declaring states
		State on = new State("on", button);
		State off = new State("off", button);

		// Creating actions
		Action switchTheLightOn = new ActuatorAction(led, SIGNAL.HIGH);
		Action switchTheLightOff = new ActuatorAction(led, SIGNAL.LOW);

		// Binding actions to states
		on.setActions(Arrays.asList(switchTheLightOn));
		off.setActions(Arrays.asList(switchTheLightOff));

		// Binding transitions to states
		on.setNext(on);
		on.setNextIfHigh(off);
		off.setNext(off);
		off.setNextIfHigh(on);

		led.addState(on);
		led.addState(off);


		State[] number = new State[11];
		for (int i = 0; i < 10; i++) {
			number[i] = new State(String.format("nb%d", i), button);
		}
		for (int i = 0; i < 10; i++) {
			number[i].setNext(number[(i + 1) % 10]);
			number[i].setNextIfHigh(number[0]);
			Action action = new SevenSegAction(sevenSeg, i);
			number[i].setActions(Arrays.asList(action));
			sevenSeg.addState(number[i]);
		}

		ArrayList<State> state = new ArrayList<State>();
		state.add(on); state.add(off);
		for (int i = 0 ; i < 10; i++) {
			state.add(number[i]);
		}

		// Building the App
		App theSwitch = new App("Arduino");
		theSwitch.setBricks(Arrays.asList(led, sevenSeg));
		theSwitch.setStates(state); 
		theSwitch.setSensors(Arrays.asList(button));
		theSwitch.setInitial(Arrays.asList(on, number[0])); 

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
