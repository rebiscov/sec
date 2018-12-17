package ens.smartcity.model.sensor;


/**
 * 
 */
public class Location {

    /**
     * Default constructor
     */
    public Location(String name, Double lat, Double longitude) {
        Name = name;
        Lat = lat;
        Long = longitude;
    }

    /**
     * 
     */
    private String Name;

    /**
     * 
     */
    private Double Lat;

    /**
     * 
     */
    private Double Long;

}