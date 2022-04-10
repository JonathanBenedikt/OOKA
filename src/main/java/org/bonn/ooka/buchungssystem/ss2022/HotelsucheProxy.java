package org.bonn.ooka.buchungssystem.ss2022;

import java.util.HashMap;
import java.util.List;

import java.text.SimpleDateFormat;

public class HotelsucheProxy{

    private HotelRetrieval retrieval;
    private SimpleDateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd 'um' HH:mm:ss z");

    public HotelsucheProxy(Caching cache){
        this.retrieval = new HotelRetrieval(cache);
    }
    public HotelsucheProxy(){
        this.retrieval = new HotelRetrieval(new DefaultCache());

    }

    public void setSearch(String mode){
        System.out.println(dateformatter.format(System.currentTimeMillis())+": Die Methode setSearch der HotelRetrieval-Klasse wurde auf den Modus "+mode+" gesetzt.");
        retrieval.setSearch(mode);

    }

    public Hotel[] getHotelByName(String name) {
        System.out.println(dateformatter.format(System.currentTimeMillis())+": Zugriff auf das Buchungssystem über die Methode getHotelByName. Suchwort: "+name);
        return this.retrieval.getHotelByName(name);
    }
    public void openSession() {
        System.out.println(dateformatter.format(System.currentTimeMillis())+": Eröffnung einer neuen Datenbankverbindung über die Methode openSession");
        this.retrieval.openSession();
    }


    public void cache(HashMap<String, List<Hotel>> data){
        System.out.println(dateformatter.format(System.currentTimeMillis())+": Caching der referenzierte Liste von Hotels über die Methode cache.");
        retrieval.cache(data);}

    public void clearCache(){
        System.out.println(dateformatter.format(System.currentTimeMillis())+": Löschen der Caching-Daten über die Methode clearCache.");
        retrieval.clearCache();}

    public HashMap<String, List<Hotel>> loadCache(){
        System.out.println(dateformatter.format(System.currentTimeMillis())+": Laden der gespeicherten Daten über die Methode loadCache.");
        return retrieval.loadCache();}
}
