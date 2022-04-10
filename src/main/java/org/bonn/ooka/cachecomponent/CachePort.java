package org.bonn.ooka.cachecomponent;
import org.bonn.ooka.buchungssystem.ss2022.Caching;

import java.util.HashMap;
import java.util.List;

public class CachePort<C> implements Caching<C> {
    private Cache<C> cache;

    public CachePort() {
        this.cache = new Cache<>();
    }

    @Override
    public void cache(HashMap<String, List<C>> data) {
        this.cache.cache(data);
    }

    @Override
    public void clear() {
        this.cache.clear();
    }

    @Override
    public HashMap<String, List<C>> load() {
        return this.cache.load();
    }
}
