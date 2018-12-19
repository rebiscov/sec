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
import java.util.List;
import java.util.Random;

enum MesurementType {
  STRING,
  LONG,
  DOUBLE
}

public class MarkovChain extends ModelingSensor {
  protected Date beginTime, endTime;
  protected Integer interval;
  public ArrayList<String> stateName;
  public double[][] markovChain;
  private int n;
  private Sensor s;
  private MesurementType type;

  public MarkovChain(Sensor s, Date beginTime, Date endTime, Integer interval, String filePath) {
    this.s = s;
    this.beginTime = beginTime;
    this.endTime = endTime;
    this.interval = interval;
    this.stateName = new ArrayList<String>();

    try {
      Reader reader = Files.newBufferedReader(Paths.get(filePath));
      CSVReader markovFile = new CSVReader(reader);

      String[] buff = markovFile.readNext();

      // Get the number of states
      n = Integer.decode(buff[0]);
      markovChain = new double[n][n];

      // Get the datatype of the mesurements
      if (buff[1].equals("String")) type = MesurementType.STRING;
      else if (buff[1].equals("Long")) type = MesurementType.LONG;
      else if (buff[1].equals("Double")) type = MesurementType.DOUBLE;

      buff = markovFile.readNext();

      int i = 0;
      while (buff != null) {
        stateName.add(new String(buff[0]));

        for (int j = 1; j <= n; j++)
          markovChain[i][j - 1] =
              Double.parseDouble(buff[j]) + (j > 1 ? markovChain[i][j - 2] : 0.0);

        buff = markovFile.readNext();
        i++;
      }
    } catch (IOException e) {
      System.err.println("Unexpected error: ");
      System.err.println(e.toString());
      System.exit(1);
    }
  }

  public List<Mesurement> generateValue(Sensor s) {
    ArrayList<Mesurement> mesurements = new ArrayList<>();
    Date pointer = new Date(beginTime.getTime());
    Random rnd = new Random();
    int currentState = 0;

    while (pointer.before(endTime)) {
      double v = rnd.nextDouble();
      int j;

      for (j = 0; markovChain[currentState][j] <= v; j++) ;

      currentState = j;
      Object value;
      if (type == MesurementType.STRING) value = new String(stateName.get(j));
      else if (type == MesurementType.LONG) value = Long.parseLong(stateName.get(j));
      else if (type == MesurementType.DOUBLE) value = Double.parseDouble(stateName.get(j));
      else value = null;

      mesurements.add(new Mesurement(value, new Date(pointer.getTime()), this.s));
      pointer = new Date(pointer.getTime() + interval);
    }

    return mesurements;
  }
}
