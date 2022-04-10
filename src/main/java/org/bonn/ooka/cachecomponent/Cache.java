package org.bonn.ooka.cachecomponent;

import java.util.List;

public class Cache<C> implements Caching<C>{

    private List<C> cache;

    @Override
    public void cache(List<C> data) {
        this.cache = data;
    }

    @Override
    public void clear() {
        this.cache = null;
    }

    @Override
    public List<C> load() {
        return this.cache;
    }
}