package ens.smartcity.model.sensor;

import ens.smartcity.model.law.ModelingSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * Un service comprend un ensemble de sensors correspondant à un certain service (place disponible sur un parking par exemple : 1 sensor par place)
 */
public class Service {

    private String description;
    private List<Sensor> sensors;
    private String name;

    /**
     * Default constructor
     */
    public Service(String name, String description, List<Sensor> sensors) {
        this.sensors = sensors;
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * It's used to create a service with a given number of sensors. All the sensors are created with the same law.
     * @param name name of the service
     * @param description description of the service
     * @param nbSensors number of sensors that the service will contain
     */
    public Service(String name, String description, Integer nbSensors, ModelingSensor law){
        this.name = name;
        this.description = description;
        ArrayList<Sensor> sensors = new ArrayList<>();
        for(Integer i = 0; i < nbSensors; i++) {
            Sensor sensor = new Sensor(i,"sensor_" + name + "_" + i.toString(), "Sensor for service " + name);
            sensor.setLaw(law);
            sensors.add(sensor);
        }
        this.sensors = sensors;
    }
}