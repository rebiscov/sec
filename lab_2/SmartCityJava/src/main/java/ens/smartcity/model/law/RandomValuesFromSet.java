package ens.smartcity.model.law;


import java.util.*;

public class RandomValuesFromSet implements Law {

    private ArrayList<Object> values;

    private Random randomizer;

    public RandomValuesFromSet(ArrayList<Object> values) {
        this.values = values;
        randomizer = new Random();
    }


    @Override
    public Object evaluate(Double d) {
        return values.get(randomizer.nextInt(values.size()));
    }

}
