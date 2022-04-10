package org.bonn.ooka.buchungssystem.ss2022;

public class HotelsucheProxy implements Hotelsuche{
    private HotelRetrieval retrieval;

    public HotelsucheProxy(Caching cache){
        this.retrieval = new HotelRetrieval(cache);
    }
    public HotelsucheProxy(){
        this.retrieval = new HotelRetrieval();
    }

    @Override
    public Hotel[] getHotelByName(String name) {
        if(retrieval == null)
            setRetrieval(new HotelRetrieval());

        return retrieval.getHotelByName(name);
    }

    @Override
    public void openSession() {
        if(retrieval == null)
            setRetrieval(new HotelRetrieval());

    retrieval.openSession();
    }

    public HotelRetrieval getRetrieval() {
        return retrieval;
    }

    public void setRetrieval(HotelRetrieval retrieval) {
        this.retrieval = retrieval;
    }
}
