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

  public Function(Sensor s, Date beginTime, Date endTime, Integer interval, String filePath) {
    try {
      Reader reader = Files.newBufferedReader(Paths.get(filePath));
      CSVReader funFile = new CSVReader(reader);

      ArrayList<ArrayList<Double>> p = new ArrayList<>();
      ArrayList<Double> points = new ArrayList<>();

      String[] buff = funFile.readNext();

      for (int i = 0; i < buff.length; i++) points.add(Double.parseDouble(buff[i]));

      buff = funFile.readNext();
      while (buff != null) {
        ArrayList<Double> pi = new ArrayList<>();

        for (int i = 0; i < buff.length; i++) pi.add(Double.parseDouble(buff[i]));
        p.add(pi);

        buff = funFile.readNext();
      }

      f = new Polynomial(p, points);

    } catch (IOException e) {
      System.err.println("Unexpected error: ");
      System.err.println(e.toString());
      System.exit(1);
    }
  }

  public double evaluate(Double x) {
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
