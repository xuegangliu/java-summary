package com.lxg.core.util;

import java.util.List;
import java.util.Map;

/**
 * Spring-Core MultiValueMap
 * @param <K>
 * @param <V>
 */
public interface MultiValueMap<K, V> extends Map<K, List<V>> {
    V getFirst(K var1);

    void add(K var1, V var2);

    void set(K var1, V var2);

    void setAll(Map<K, V> var1);

    Map<K, V> toSingleValueMap();
}
