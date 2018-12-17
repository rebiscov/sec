package ens.smartcity.model.tailoring;


public class Offset implements TailorMesurement {

    /**
     * Default constructor
     */
    public Offset(Double value) {
        Value = value;
    }

    public Double Value;

    @Override
    public Double modifyValue(Double value) {
        return value + this.Value;
    }

}