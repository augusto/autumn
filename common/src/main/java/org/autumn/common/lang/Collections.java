package org.autumn.common.lang;

import java.util.HashMap;
import java.util.Map;

public class Collections {
    public static final Map EMPTY_MAP = java.util.Collections.EMPTY_MAP;

    private Collections() {
    }

    public static <T> Map<T,T> asMap(T ... keysAndValues) {
        if( keysAndValues.length % 2 == 1) {
            throw new IllegalArgumentException("The array must have an even number of elements");
        }

        Map<T,T> map = new HashMap<T,T>();
        for( int i=0 ; i < keysAndValues.length ; i+=2) {
            map.put(keysAndValues[i], keysAndValues[i+1]);
        }

        return map;
    }
}
