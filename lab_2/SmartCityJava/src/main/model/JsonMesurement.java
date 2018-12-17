package ens.smartcity.model.iofile;


import java.util.*;

import ens.smartcity.model.model.*;

/**
 * 
 */
public class JsonMesurement extends ReplayMesurement {

    /**
     * Default constructor
     */
    public JsonMesurement(String filePath) {
        super(filePath);
    }

    /**
     * @return
     */
    public Data OpenFile(Integer t, Integer v, Integer s, Boolean firstLineDifferent){

        return new Data();
    }

}