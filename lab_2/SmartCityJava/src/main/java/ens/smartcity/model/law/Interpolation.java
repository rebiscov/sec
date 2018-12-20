package ens.smartcity.model.law;

import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;
import java.util.ArrayList;
import java.util.Date;

public class Interpolation extends ModelingSensor {
  protected Date beginTime, endTime;
  protected Integer interval;
  private Sensor s;
  private Polynomial f;

  public Interpolation(
      Sensor s,
      Date beginTime,
      Date endTime,
      Integer interval,
      ArrayList<Double> x,
      ArrayList<Double> y) {
    this.s = s;
    this.beginTime = beginTime;
    this.endTime = endTime;
    this.interval = interval;

    ArrayList<ArrayList<Double>> p = new ArrayList<>();

    for (int i = 0; i < y.size() - 1; i++) {
      ArrayList<Double> pi = p.get(i);
      Double slope = y.get(i + 1) - y.get(i);
      Double intercept = y.get(i) - x.get(i) * slope;

      pi.add(intercept);
      pi.add(slope);
    }

    f = new Polynomial(p, x);
  }

  public Double evaluate(Double x) {
    return this.f.evaluate(x);
  }

  public ArrayList<Mesurement> generateValue(Sensor s) {
    ArrayList<Mesurement> mesurements = new ArrayList<>();

    return mesurements;
  }
}
