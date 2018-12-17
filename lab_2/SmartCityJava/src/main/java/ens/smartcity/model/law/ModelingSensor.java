package ens.smartcity.model.law;


import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;

import java.util.Date;
import java.util.List;

public abstract class ModelingSensor {

    protected Date beginTime, endTime;
    protected Integer interval;


    public abstract List<Mesurement> generateValue(Sensor s);



}