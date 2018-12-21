package ens.smartcity.model.sensor;


import ens.smartcity.model.law.Law;
import ens.smartcity.model.law.ModelingSensor;

/**
 * 
 */
public class Sensor {



    private Integer id;
    private String name;
    private String description;
    private Law law = null;
    private Location location = null;
    /**
     * Create new Sensor
     */
    public Sensor(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public void setLaw(Law law) {
        this.law = law;
    }

    public Law getLaw() {
        return law;
    }

    /**
     * @param location the location of the sensor
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the location of the sensor
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}