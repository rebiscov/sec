package ens.smartcity.model.data;

import java.util.*;

import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.tailoring.TailorMesurement;

/**
 * 
 */
public class Data {


    private String Description;
    private List<Mesurement> mesurements = new ArrayList<>();
    private String Name;


    /**
     * Default constructor
     */
    public Data(String name, String description) {
        this.Name = name; 
        this.Description  = description;
    }



    /**
     * @param mesurements the mesurements to set
     */
    public void setMesurements(List<Mesurement> mesurements) {
        this.mesurements = mesurements;
    }

    /**
     * @return the mesurements
     */
    public List<Mesurement> getMesurements() {
        return mesurements;
    }


    public String getDescription() {
        return Description;
    }

    public void printData() {
        for (Mesurement m : mesurements){
            System.out.println(m.getValue() + " " + m.getTime() + " " + m.getOwner().getName());
        }
    }

    /**
     * 
     */
    public void AddTailoring(TailorMesurement tailoring) {
        for (Mesurement m : mesurements) {
            try {
                m.setValue(tailoring.modifyValue(Double.valueOf(m.getValue().toString())));
            } catch (Exception e) {
                System.err.println("pas le bon type");
            }
        }

    }

    public String getName() {
        return Name;
    }

    public void sendData() {
        for (Mesurement m: mesurements) {
            m.sendMeasurement();
        }
    }
}