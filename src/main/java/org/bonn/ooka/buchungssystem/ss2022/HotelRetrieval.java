package org.bonn.ooka.buchungssystem.ss2022;

import org.bonn.ooka.LZU.FactoryProvider;
import org.bonn.ooka.LZU.Logger;
import org.bonn.ooka.LZU.StandardLoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HotelRetrieval {

    private Logger logger;

    private static DBAccess access;
    private Hotelsuche search;
    private Caching cache;

    public HotelRetrieval(Caching cache){
        this.cache = cache;
        this.search = new HotelsucheEinfach();
    }
    public HotelRetrieval(){
    }


    public void cache(HashMap<String, List<Hotel>> data){
        cache.cache(data);
    }
    public void clearCache(){
        cache.clear();
    }
    public HashMap<String, List<Hotel>> loadCache(){
        return cache.load();
    }

    public void setSearch(String mode){
        FactoryProvider provider = new FactoryProvider();
        StandardLoggerFactory factory = (StandardLoggerFactory)provider.getFactory("StandardLoggerFactory");
        logger = factory.createLogger();

        if (mode.equals("einfach")){
            search = new HotelsucheEinfach();
            logger.sendLog("Die Hotelsuche wurde auf einfach gesetzt.");
        } else if (mode.equals("erweitert")) {
            search = new HotelsucheErweitert();
            logger.sendLog("Die Hotelsuche wurde auf erweitert gesetzt.");
        }
    }
    public void openSession() {
        search.openSession();
    }
    public Hotel[] getHotelByName(String name) {
        return search.getHotelByName(name);
    }
}
