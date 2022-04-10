package org.bonn.ooka.cachecomponent;

import org.bonn.ooka.buchungssystem.ss2022.Caching;

import java.util.HashMap;
import java.util.List;

public class Cache<C> implements Caching<C>{

    private HashMap<String, List<C>> storage;

    public void cache(HashMap<String, List<C>> data) {
        this.storage = data;
    }

    @Override
    public void clear() {
        this.storage.clear();
    }

    @Override
    public HashMap<String, List<C>> load() {
        return this.storage;
    }
}