package org.bonn.ooka.buchungssystem.ss2022;

import java.util.HashMap;
import java.util.List;

public interface Caching<C> {
    void cache(HashMap<String, List<C>> data);
    void clear();
    HashMap<String, List<C>> load();
}