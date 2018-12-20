package ens.smartcity.model.sensor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * 
 */
public class Mesurement {

    /**
     * Default constructor
     */
    public Mesurement(Object value, Date time, Sensor s) {
        this.Value = value;
        this.Time = time;
        this.owner = s;
    }

    /**
     * Value est de type Object pour pouvoir avoir des données de n'importe quel type
     */
    private Object Value;

    /**
     * 
     */
    private Date Time;

    private Sensor owner;

    /**
     * @return the sensor owner of this mesurement
     */
    public Sensor getOwner() {
        return owner;
    }

    /**
     * @return the time when the mesurement was taken
     */
    public Date getTime() {
        return Time;
    }

    /**
     * @return the value of the mesurement
     */
    public Object getValue() {
        return Value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        Value = value;
    }


    /**
     * Code pour envoyer dans la base de données la mesure donnée
     */
    public void sendMeasurement() {

        String unixTime = Time.toString() + "000000";

        String urlParameters = owner.getName() + " value=" + Value.toString() + " " + unixTime;

        HttpPost.executePost("http://localhost:8086/write?db=sec", urlParameters);
    }
}

class HttpPost {

    public static String executePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length",
                    Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream (
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}