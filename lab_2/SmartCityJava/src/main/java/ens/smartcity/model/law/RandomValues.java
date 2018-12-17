package ens.smartcity.model.law;


import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomValues extends ModelingSensor {


    private Integer inf;
    private Integer sup;


    public RandomValues(Date beginTime, Date endTime, Integer interval, Sensor owner, Integer inf, Integer sup) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.interval = interval;
        this.inf = inf;
        this.sup = sup;
    }

    public List<Mesurement> generateValue(Sensor owner) {


        ArrayList<Mesurement> measurements = new ArrayList<>();
        Date currentTime, nextTime = beginTime;
        Random randomizer = new Random();

        do {

            currentTime = nextTime;

            Double value = randomizer.nextDouble() * (sup - inf) + inf;
            measurements.add(new Mesurement(value, currentTime, owner));

            nextTime = DateUtils.addSeconds(currentTime, interval);

        } while (nextTime.before(beginTime));

        return measurements;
    }
}