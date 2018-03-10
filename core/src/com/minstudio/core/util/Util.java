package com.minstudio.core.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Util {

    public static float toFloatDeltaTime(long from, long to){
        return (to - from) / 1000f;
    }

    public static long toLongStamp(long from, float to){
        return from + (long)(to * 1000);
    }

    @SafeVarargs
    public static <T> Set<T> asSet(T... items){
        Set<T> res = new HashSet<>();
        Collections.addAll(res, items);
        return res;
    }
}
