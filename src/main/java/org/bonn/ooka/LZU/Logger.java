package org.bonn.ooka.LZU;

import java.sql.Timestamp;
import java.util.Date;

public class Logger {

    public void sendLog(String str){
        System.out.println("+++++ LOG: "+str+" "+new Timestamp(System.currentTimeMillis()));
    }


}
