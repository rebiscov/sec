package  ens.smartcity.model.iofile;

import java.net.URL;

import ens.smartcity.model.data.*;

/**
 * 
 */
public abstract class ReplayMeasurement {

    public ReplayMeasurement(String filePath) {
        FilePath = filePath;
        FileName = filePath.substring(filePath.lastIndexOf("/"), filePath.lastIndexOf("."));
    }

    public ReplayMeasurement(URL url) {
        // Not implemented
    }

    protected String FilePath;

    protected String FileName;


    /**
     * @return
     */
    // public abstract Data OpenFile(Integer t, Integer v, Integer s, Boolean firstLineDifferent);

}