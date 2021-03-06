package io.github.mosser.arduinoml.ens.compiler;

import io.github.mosser.arduinoml.external.*;
import io.github.mosser.arduinoml.ens.model.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ModelBuilder extends ArduinoMLBaseListener {

    /********************
     ** Business Logic **
     ********************/

    private App theApp = null;
    private boolean built = false;

    public App retrieve() {
        if (built) { return theApp; }
        throw new RuntimeException("Cannot retrieve a model that was not created!");
    }

    /*******************
     ** Symbol tables **
     *******************/

    private Map<String, OutputElement> outputElements = new HashMap<>();
    private Map<String, State>    states  = new HashMap<>();
    private Map<State, String>  transitions  = new HashMap<>();
    private Map<State, String>  transitionsIfHigh  = new HashMap<>();
    private Map<String, Sensor> sensors = new HashMap<>();
    private List<State> initialStates = new ArrayList<>();

    private State currentState = null;

    /**************************
     ** Listening mechanisms **
     **************************/


    @Override
    public void enterApp(ArduinoMLParser.AppContext ctx) {
        this.built = false;
        this.theApp = new App();
        this.theApp.setName(ctx.name.getText());
    }

    @Override
    public void exitApp(ArduinoMLParser.AppContext ctx) {
        // Resolving transitions
        this.transitions.forEach( (from, nextId) -> from.setNext(this.states.get(nextId)) );
        this.transitionsIfHigh.forEach( (from, nextId) -> from.setNextIfHigh(this.states.get(nextId)) );
        this.built = true;
        this.theApp.setStates(new ArrayList(states.values()));
        this.theApp.setInitial(initialStates);
    }

    @Override
    public void enterActuator(ArduinoMLParser.ActuatorContext ctx) {
        Actuator act = new Actuator(ctx.location().id.getText(), Integer.parseInt(ctx.location().port.getText()));
        this.theApp.getOutputElements().add(act);
        this.outputElements.put(act.getName(), act); // Symbol table for actuators
    }

    @Override
    public void enterSevenSeg(ArduinoMLParser.SevenSegContext ctx) {
        int[] pins = new int[7];

        for (int i = 0; i < 7; i++)
            pins[i] = Integer.parseInt(ctx.PORT_NUMBER(i).getText());

        SevenSeg ss = new SevenSeg(ctx.id.getText(), pins);

        this.theApp.getOutputElements().add(ss);
        this.outputElements.put(ss.getName(), ss); // Symbol table for actuators
    }

    @Override
    public void enterSensor(ArduinoMLParser.SensorContext ctx) {
        Sensor sens = new Sensor(ctx.location().id.getText(), Integer.parseInt(ctx.location().port.getText()));
        this.theApp.getSensors().add(sens);
        this.sensors.put(sens.getName(), sens);
    }


    @Override
    public void enterState(ArduinoMLParser.StateContext ctx) {
        State local = new State();
        local.setName(ctx.name.getText());
        this.currentState = local;

        try {
            local.setSensor(sensors.get(ctx.sensorName.getText()));
        } catch (Exception e) {}


        this.states.put(local.getName(), local); // Symbol table for states
    }

    @Override
    public void exitState(ArduinoMLParser.StateContext ctx) {
        this.theApp.getStates().add(this.currentState);
        this.currentState = null;
    }

    @Override
    public void enterActuatorAction(ArduinoMLParser.ActuatorActionContext ctx) {
        Actuator act = (Actuator) outputElements.get(ctx.receiver.getText());
        ActuatorAction action = new ActuatorAction(act, SIGNAL.valueOf(ctx.value.getText()));
        act.addState(currentState);
        currentState.getActions().add(action);
    }

    @Override
    public void enterSevenSegAction(ArduinoMLParser.SevenSegActionContext ctx) {
        SevenSeg ss = (SevenSeg) outputElements.get(ctx.receiver.getText());
        SevenSegAction action = new SevenSegAction(ss, SEVENSEGNUMBER.valueOf(ctx.value.getText()));
        ss.addState(currentState);
        currentState.getActions().add(action);
    }

    @Override
    public void enterNext(ArduinoMLParser.NextContext ctx) {
        this.transitions.put(this.currentState, ctx.target.getText());
    }

    @Override
    public void enterNextIfHigh(ArduinoMLParser.NextIfHighContext ctx) {
        this.transitionsIfHigh.put(this.currentState, ctx.target.getText());
    }

    @Override
    public void enterInitial(ArduinoMLParser.InitialContext ctx) {
        this.initialStates.add(this.currentState);
    }

}
