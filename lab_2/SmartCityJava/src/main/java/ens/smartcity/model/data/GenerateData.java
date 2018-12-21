package ens.smartcity.model.data;

import ens.smartcity.model.law.Law;
import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;

public class GenerateData {

    public static Data generate(Sensor s, Date beginTime, Date endTime, Integer interval){
        Law l = s.getLaw();

        ArrayList<Mesurement> measurements = new ArrayList<>();
        Date currentTime, nextTime = beginTime;

        do {

            currentTime = nextTime;

            measurements.add(
                    new Mesurement(
                            l.evaluate(new Double(currentTime.getMinutes() * 60 + currentTime.getHours())),
                            currentTime,
                            s)
            );

            nextTime = DateUtils.addSeconds(currentTime, interval);

        } while (currentTime.before(endTime));

        Data d = new Data(s.getName() + System.nanoTime(), "Data for the sensor " + s.getName());
        d.setMesurements(measurements);

        return d;
    }

    public static void generateRealTime(Sensor s, Integer interval, Integer duration) {
        Law l = s.getLaw();

        Date beginTime = new Date();
        Date endTime = DateUtils.addSeconds(new Date(), duration);
        Date currentTime, nextTime = beginTime;


        do {

            currentTime = nextTime;

            Mesurement msr = new Mesurement(
                            l.evaluate(new Double(currentTime.getMinutes() * 60 + currentTime.getHours())),
                            currentTime,
                            s);

            msr.sendMeasurement();

            try{
                Thread.sleep(interval * 1000);
            }
            catch (Exception e) {
                System.err.println("Interrupted");
            }

            nextTime = DateUtils.addSeconds(currentTime, interval);

        } while (nextTime.before(endTime));
    }

}
