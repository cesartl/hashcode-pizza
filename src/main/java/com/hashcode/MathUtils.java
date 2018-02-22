package com.hashcode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MathUtils {
    public static List<Integer> factors(int n) {
        return Stream.iterate(1, i -> i + 1)
                .limit(n + 1)
                .filter(i -> n % i == 0)
                .collect(Collectors.toList());
    }
}
