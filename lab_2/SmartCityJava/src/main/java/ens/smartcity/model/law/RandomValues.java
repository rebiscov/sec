package ens.smartcity.model.law;


import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomValues implements Law {


    private Integer inf;
    private Integer sup;

    private Random randomizer;

    public RandomValues(Integer inf, Integer sup) {
        this.inf = inf;
        this.sup = sup;
        randomizer = new Random();
    }



    @Override
    public Object evaluate(Double d) {
        return randomizer.nextDouble() * (sup - inf) + inf;
    }
}