package org.bonn.ooka.buchungssystem.ss2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HotelRetrieval {

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
        if (mode.equals("einfach")){
            search = new HotelsucheEinfach();
        } else if (mode.equals("erweitert")) {
            search = new HotelsucheErweitert();
        }
    }
    public void openSession() {
        search.openSession();
    }
    public Hotel[] getHotelByName(String name) {
        return search.getHotelByName(name);
    }
}
