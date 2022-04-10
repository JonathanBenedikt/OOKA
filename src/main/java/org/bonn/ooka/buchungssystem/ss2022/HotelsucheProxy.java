package org.bonn.ooka.buchungssystem.ss2022;

public class HotelsucheProxy implements Hotelsuche{

    private HotelRetrieval retrieval;

    @Override
    public String getHotelByName(String name) {

        return retrieval.getHotelByName(name);
    }

    @Override
    public void openSession() {

    setRetrieval(new HotelRetrieval());
    HotelRetrieval retrieval = getRetrieval();

    retrieval.openSession();
    }

    public HotelRetrieval getRetrieval() {
        return retrieval;
    }

    public void setRetrieval(HotelRetrieval retrieval) {
        this.retrieval = retrieval;
    }
}
