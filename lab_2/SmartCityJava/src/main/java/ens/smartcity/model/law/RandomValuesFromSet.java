package ens.smartcity.model.law;

import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;
import org.apache.commons.lang3.time.DateUtils;

import java.util.*;

public class RandomValuesFromSet extends ModelingSensor {

    private ArrayList<Object> values;

    public RandomValuesFromSet(Date beginTime, Date endTime, Integer interval, Sensor owner, ArrayList<Object> values) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.interval = interval;
        this.values = values;
    }

    @Override
    public List<Mesurement> generateValue(Sensor owner) {
        ArrayList<Mesurement> measurements = new ArrayList<>();
        Date currentTime, nextTime = beginTime;
        Random randomizer = new Random();

        do {

            currentTime = nextTime;

            Object value = values.get(randomizer.nextInt(values.size()));
            measurements.add(new Mesurement(value, currentTime, owner));

            nextTime = DateUtils.addSeconds(currentTime, interval);

        } while (nextTime.before(beginTime));

        return measurements;



    }
}
