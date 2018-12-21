package ens.smartcity.model.iofile;


import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URL;

import ens.smartcity.model.sensor.*;
import ens.smartcity.model.data.*;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 */
public class CSVMeasurement extends ReplayMeasurement {


    public CSVMeasurement(String filePath) {
        super(filePath);
    }

    public CSVMeasurement(URL url) { super(url); }


    /**
     * @param t : number of column for t
     * @param v : number of column for v
     * @param s : number of column for s
     */
    public Data OpenFile(Integer t, Integer v, Integer s, Boolean firstLineDifferent) {

        Data d = new Data(FileName + System.nanoTime(), "Data from " + FileName);

        try {
            Reader reader = Files.newBufferedReader(Paths.get(this.FilePath));
            CSVReader csvReader = new CSVReader(reader);
            
            String[] lines;

            if(firstLineDifferent){
                csvReader.readNext();
            }

            
            lines = csvReader.readNext();
            Sensor sensor = new Sensor(-1, lines[s], "");

            

            do  {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Mesurement mesurement = new Mesurement(lines[v], sdf.parse(lines[t]), sensor);

                d.getMesurements().add(mesurement);
                
                

            } while ((lines = csvReader.readNext()) != null);
            csvReader.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return d;
    }

}