package org.bonn.ooka.buchungssystem.ss2022;


public class FassadeBuchungssystem implements Hotelsuche{
    private HotelsucheProxy proxy;

    public FassadeBuchungssystem(){
        Caching<Hotel> cache = new DefaultCache();
        this.proxy = new HotelsucheProxy(cache); //Koennte man mit Cachingcomponent austauschen
    }

    public void setCurrentSearchmode(String mode){
        this.proxy.setSearch(mode);
    }

    public Hotel[] getHotelByName(String name) {
        return proxy.getHotelByName(name);
    }

    public void openSession() {
        proxy.openSession();
    }
}
