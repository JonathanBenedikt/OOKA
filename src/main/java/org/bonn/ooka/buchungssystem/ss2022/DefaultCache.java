package org.bonn.ooka.buchungssystem.ss2022;

import java.util.HashMap;
import java.util.List;

public class DefaultCache implements Caching<Hotel>{

    @Override
    public void cache(HashMap<String, List<Hotel>> data) {
        System.out.println("Cache ubergeben um Caching freizuschalten");
    }

    @Override
    public void clear() {
        System.out.println("Cache ubergeben um Caching freizuschalten");
    }

    @Override
    public HashMap<String, List<Hotel>> load() {
        System.out.println("Cache ubergeben um Caching freizuschalten");
        return null;
    }
}
