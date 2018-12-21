package ens.smartcity.model.law;

import com.opencsv.CSVReader;
import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    ArrayList<ArrayList<Double>> p = new ArrayList<ArrayList<Double>>();

    for (int i = 0; i < y.size() - 1; i++) {
      ArrayList<Double> pi = new ArrayList<Double>();
      Double slope = y.get(i + 1) - y.get(i);
      Double intercept = y.get(i) - x.get(i) * slope;

      pi.add(intercept);
      pi.add(slope);
      p.add(pi);
    }

    f = new Polynomial(p, x);
  }

  public Interpolation(Sensor s, Date beginTime, Date endTime, Integer interval, String filePath) {
    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();

    try {
      Reader reader = Files.newBufferedReader(Paths.get(filePath));
      CSVReader pointsFile = new CSVReader(reader);

      String[] buff = pointsFile.readNext();
      for (int i = 0; i < buff.length; i++) x.add(Double.parseDouble(buff[i]));

      buff = pointsFile.readNext();
      for (int i = 0; i < buff.length; i++) y.add(Double.parseDouble(buff[i]));

    } catch (IOException e) {
      System.err.println("Unexpected error: ");
      System.err.println(e.toString());
      System.exit(1);
    }

    this.s = s;
    this.beginTime = beginTime;
    this.endTime = endTime;
    this.interval = interval;

    ArrayList<ArrayList<Double>> p = new ArrayList<ArrayList<Double>>();

    for (int i = 0; i < y.size() - 1; i++) {
      ArrayList<Double> pi = new ArrayList<Double>();
      Double slope = y.get(i + 1) - y.get(i);
      Double intercept = y.get(i) - x.get(i) * slope;

      pi.add(intercept);
      pi.add(slope);
      p.add(pi);
    }

    f = new Polynomial(p, x);
  }

  public Double evaluate(Double x) {
    return this.f.evaluate(x);
  }

  public ArrayList<Mesurement> generateValue(Sensor s) {
    ArrayList<Mesurement> mesurements = new ArrayList<>();

    Date pointer = new Date(beginTime.getTime());

    while (pointer.before(endTime)) {
      Double v = f.evaluate(Double.valueOf((Long.valueOf(pointer.getTime())).doubleValue()));
      mesurements.add(new Mesurement(v, new Date(pointer.getTime()), this.s));

      pointer = new Date(pointer.getTime() + interval);
    }

    return mesurements;
  }
}
