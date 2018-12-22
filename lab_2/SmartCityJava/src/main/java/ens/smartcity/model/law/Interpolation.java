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

public class Interpolation implements Law {
    private Polynomial f;

    public Interpolation(ArrayList<Double> x, ArrayList<Double> y) {

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

    public Interpolation(String filePath) {
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
}
