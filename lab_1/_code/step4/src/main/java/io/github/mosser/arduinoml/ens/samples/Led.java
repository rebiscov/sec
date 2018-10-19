package io.github.mosser.arduinoml.ens.samples;

import io.github.mosser.arduinoml.ens.model.*;
import io.github.mosser.arduinoml.ens.generator.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Led {

	public static void main(String[] args) {

		// Declaring LED
		Actuator led = new Actuator();
		led.setName("LED");
		led.setPin(13);

		// Declaring 7-seg
		/*Actuator sevenSeg[] = new Actuator[7];
		for(int i = 0; i <= 6; i++) {
			sevenSeg[i] = new Actuator();
			sevenSeg[i].setName(String.format("Segment%d", i));
			sevenSeg[i].setPin(i+1);
		}*/

		// Declaring button
		Sensor button = new Sensor();
		button.setName("button");
		button.setPin(10);





		// Declaring states
		State on = new State();
		on.setName("on");
		on.setSensor(button);

		State off = new State();
		off.setName("off");
		off.setSensor(button);


		// Declaring states of 7-seg
		/*State numbers[] = new State[10];
		for(int i = 0; i < 10; i++) {
			numbers[i] = new State();
			numbers[i].setName(String.format("number%d", i));
		}

		// Action for each segment of the 7-seg
		Action switchSegOn[] = new Action[7], 
			switchSegOff[] = new Action[7];
		for(int i = 0; i < 7; i++) {
			switchSegOff[i] = new Action();
			switchSegOn[i] = new Action();
			switchSegOn[i].setActuator(sevenSeg[i]);
			switchSegOff[i].setActuator(sevenSeg[i]);
			switchSegOff[i].setValue(SIGNAL.HIGH);
			switchSegOn[i].setValue(SIGNAL.LOW);
		}*/

		// Creating actions
		Action switchTheLightOn = new Action();
		switchTheLightOn.setActuator(led);
		switchTheLightOn.setValue(SIGNAL.HIGH);

		Action switchTheLightOff = new Action();
		switchTheLightOff.setActuator(led);
		switchTheLightOff.setValue(SIGNAL.LOW);

		// Creating SensorTransition for the 7-seg
		/*SensorTransition changeNumber[] = new SensorTransition[10];
		for(int i = 0 ; i < 10; i++) {
			changeNumber[i] = new SensorTransition();
			changeNumber[i].setName(String.format("change%d", i));
			changeNumber[i].setSensor(button);
		}*/

		// Binding actions to states
		on.setActions(Arrays.asList(switchTheLightOn));
		off.setActions(Arrays.asList(switchTheLightOff));

		// Binding transitions to states
		on.setNext(on);
		on.setNextIfHigh(off);
		off.setNext(off);
		off.setNextIfHigh(on);


		/*numbers[0].setActions(Arrays.asList(switchSegOn[0],switchSegOn[1],switchSegOn[2],switchSegOn[3],switchSegOn[4],switchSegOn[5],switchSegOff[6]));
		numbers[1].setActions(Arrays.asList(switchSegOff[0],switchSegOn[1],switchSegOn[2],switchSegOff[3],switchSegOff[4],switchSegOff[5],switchSegOff[6]));
		numbers[2].setActions(Arrays.asList(switchSegOn[0],switchSegOn[1],switchSegOff[2],switchSegOn[3],switchSegOn[4],switchSegOn[5],switchSegOff[6]));
		numbers[3].setActions(Arrays.asList(switchSegOn[0],switchSegOn[1],switchSegOn[2],switchSegOn[3],switchSegOn[4],switchSegOff[5],switchSegOff[6]));
		numbers[4].setActions(Arrays.asList(switchSegOff[0],switchSegOn[1],switchSegOn[2],switchSegOff[3],switchSegOff[4],switchSegOn[5],switchSegOn[6]));
		numbers[5].setActions(Arrays.asList(switchSegOn[0],switchSegOff[1],switchSegOn[2],switchSegOn[3],switchSegOff[4],switchSegOn[5],switchSegOn[6]));
		numbers[6].setActions(Arrays.asList(switchSegOn[0],switchSegOff[1],switchSegOn[2],switchSegOn[3],switchSegOn[4],switchSegOn[5],switchSegOn[6]));
		numbers[7].setActions(Arrays.asList(switchSegOn[0],switchSegOn[1],switchSegOn[2],switchSegOff[3],switchSegOff[4],switchSegOff[5],switchSegOff[6]));
		numbers[8].setActions(Arrays.asList(switchSegOn[0],switchSegOn[1],switchSegOn[2],switchSegOn[3],switchSegOn[4],switchSegOn[5],switchSegOn[6]));
		numbers[9].setActions(Arrays.asList(switchSegOn[0],switchSegOn[1],switchSegOn[2],switchSegOn[3],switchSegOff[4],switchSegOn[5],switchSegOn[6]));


		for(int i = 0; i < 10; i++) {
			numbers[i].setNext(changeNumber[i]);
			changeNumber[i].setNextIfHigh(numbers[0]);
			changeNumber[i].setNextIfLow(numbers[(i+1)%10]);
		}
		*/



		// Building the App
		App theSwitch = new App();
		theSwitch.setName("Led!");
		theSwitch.setBricks(Arrays.asList(led ));//, sevenSeg[0],sevenSeg[1],sevenSeg[2],sevenSeg[6],sevenSeg[3],sevenSeg[4],sevenSeg[5]));
		theSwitch.setStates(Arrays.asList(on, off)); //,numbers[0], numbers[1],numbers[2], numbers[3],numbers[4], numbers[5],numbers[6], numbers[7], numbers[8], numbers[9]));
		theSwitch.setSensors(Arrays.asList(button));
		//theSwitch.setSensorTransitions(Arrays.asList(sensorTransitionLedOff, sensorTransitionLedOn)); //,changeNumber[1], changeNumber[7],changeNumber[2], changeNumber[8],changeNumber[3], changeNumber[9],changeNumber[4], changeNumber[5], changeNumber[6], changeNumber[0]));
		theSwitch.setInitial(Arrays.asList(on)); //, numbers[0]));

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
