package org.bonn.ooka.buchungssystem.ss2022;

public class HotelsucheErweitert implements Hotelsuche{

    private HotelRetrieval retrieval;

    public HotelsucheErweitert(HotelRetrieval retrieval){
        this.retrieval = retrieval;
    }
    public Hotel[] getHotelByName(String name) {
        return retrieval.getHotelByName(name);
    }

    public void openSession() {
        retrieval.openSession();
    }
}
