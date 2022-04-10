package org.bonn.ooka.buchungssystem.ss2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HotelRetrieval {

    private static DBAccess access;
    private Caching cache;

    public HotelRetrieval(Caching cache){
        this.cache = cache;
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

    public Hotel[] getHotelByName(String name) {
        openSession();
        // Liste für das Abfragen aller Hotels, die String im Namen besitzen
        List<String> searchResult = access.getObjects(DBAccess.HOTEL, name);
        ArrayList<Hotel> hotelResult = new ArrayList<>();

        try {
            Iterator<String> it = searchResult.stream().iterator();
            while(it.hasNext())
            {
                Hotel newHotel = new Hotel();

                newHotel.id = Integer.parseInt(it.next());
                if(it.hasNext())
                    newHotel.hotelname = it.next();
                if(it.hasNext())
                    newHotel.ort = it.next();
                hotelResult.add(newHotel);
            }
            it.remove();
        } catch (Exception ex)
        {
            System.out.println(ex);
        }

        Hotel[] hotelResultArray = new Hotel[hotelResult.size()];
        access.closeConnection();

        return hotelResult.toArray(hotelResultArray);
    }

    public void openSession()
    {
        if(access == null)
            access = new DBAccess();

        System.out.println("Es wird versucht eine Verbindung zur Datenbank zu öffnen.");
        access.openConnection();
        System.out.println("Verbindungsaufbau zur Datenbank konnte erfolgreich durchgeführt werden.");
    }
}
