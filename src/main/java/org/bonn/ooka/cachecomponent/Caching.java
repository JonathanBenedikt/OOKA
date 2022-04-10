package org.bonn.ooka.cachecomponent;

import java.util.List;

public interface Caching<C> {
    void cache(List<C> data);
    void clear();
    List<C> load();
}
