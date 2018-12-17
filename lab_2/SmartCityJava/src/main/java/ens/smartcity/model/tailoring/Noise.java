package ens.smartcity.model.tailoring;

import java.util.*;

/**
 * 
 */
public class Noise implements TailorMesurement {

    public Noise(Double inf, Double sup) {
        assert (sup - inf > 0);
        this.inf = inf;
        this.sup = sup;
    }

    /**
     * 
     */
    private Double inf;

    /**
     * 
     */
    private Double sup;

    @Override
    public Double modifyValue(Double value) {
        Double n = (new Random().nextDouble()) * (sup - inf) + inf;
        return value + n;
    }

}