package ens.smartcity.model.law;

import java.util.ArrayList;

public class Polynomial {
  ArrayList<ArrayList<Double>> p;
  ArrayList<Double> points;

  public Polynomial(ArrayList<ArrayList<Double>> p, ArrayList<Double> points) {
    this.p = p;
    this.points = points;
  }

  public double evaluate(Double x) {
    int i;
    double v = 0.;

    if (x.compareTo(points.get(0)) < 0 || x.compareTo(points.get(points.size() - 1)) >= 0) return v;

    for (i = 0; i < points.size() && x.compareTo(points.get(i)) > 0; i++) ;

    ArrayList<Double> pi = p.get(i - 1);

    for (int k = 0; k < pi.size(); k++) v += pi.get(k) * Math.pow(x, k);

    return v;
  }
}
