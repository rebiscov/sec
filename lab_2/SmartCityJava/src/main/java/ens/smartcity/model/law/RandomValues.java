package ens.smartcity.model.law;


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