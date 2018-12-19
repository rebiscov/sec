package ens.smartcity.model.law;

import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;
import java.util.ArrayList;
import java.util.Date;

public class Function extends ModelingSensor {
  protected Date beginTime, endTime;
  protected Integer interval;
  private Sensor s;
  private Polynomial f;

  public Function(
      Sensor s,
      Date beginTime,
      Date endTime,
      Integer interval,
      ArrayList<ArrayList<Double>> p,
      ArrayList<Double> points) {
    this.s = s;
    this.beginTime = beginTime;
    this.endTime = endTime;
    this.interval = interval;

    f = new Polynomial(p, points);
  }

  public double evaluate(Double x) {
    return this.f.evaluate(x);
  }

  public ArrayList<Mesurement> generateValue(Sensor s) {
    ArrayList<Mesurement> mesurements = new ArrayList<>();

    Date pointer = new Date(beginTime.getTime());

    while (pointer.before(endTime)) {
      System.out.println(new Double(pointer.getTime()));
      Double v = f.evaluate(new Double(pointer.getTime()));
      mesurements.add(new Mesurement(v, new Date(pointer.getTime()), this.s));

      pointer = new Date(pointer.getTime() + interval);
    }

    return mesurements;
  }
}
