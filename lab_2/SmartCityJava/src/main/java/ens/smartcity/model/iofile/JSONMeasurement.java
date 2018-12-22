package ens.smartcity.model.iofile;

import ens.smartcity.model.data.Data;

import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ens.smartcity.model.sensor.Mesurement;
import ens.smartcity.model.sensor.Sensor;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONMeasurement extends ReplayMeasurement {

    public JSONMeasurement(String filePath) {
        super(filePath);
    }

    public JSONMeasurement(URL url) { super(url); }

    public Data OpenFile() {

        Data d = new Data(FileName + System.nanoTime(), "Data from " + FileName);
        ArrayList<Mesurement> mesurements = new ArrayList<>();
        try{
            Reader reader = Files.newBufferedReader(Paths.get(this.FilePath));
            char c[] = new char[100000];
            reader.read(c);

            String jsonFile = new String(c);

            reader.close();

            JSONArray jsonarray = new JSONArray(jsonFile);



            for(int i = 0; i < jsonarray.length(); i++) {
                JSONObject obj = jsonarray.getJSONObject(i);

                String value = obj.getString("v");
                Sensor sensor = new Sensor(0, obj.getString("s"), "");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sdf.parse(obj.getString("t"));

                Mesurement m = new Mesurement(value, date, sensor);
                mesurements.add(m);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        d.setMesurements(mesurements);



        return d;
    }
}
