package org.bonn.ooka.cachecomponent;

import java.util.List;

public class CachePort<C> implements Caching<C> {
    private Cache<C> storage;

    @Override
    public void cache(List<C> data) {
        this.storage.cache(data);
    }

    @Override
    public void clear() {
        this.storage.clear();
    }

    @Override
    public List<C> load() {
        return this.storage.load();
    }
}
