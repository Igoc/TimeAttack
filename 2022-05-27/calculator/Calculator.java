package calculator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Calculator {
    public int intersection(int[] a, int[] b) {
        Set<Integer> setA = Arrays.stream(a).boxed().collect(Collectors.toSet());
        Set<Integer> setB = Arrays.stream(b).boxed().collect(Collectors.toSet());

        setA.retainAll(setB);

        return setA.stream().mapToInt(Integer::intValue).sum();
    }

    public int differenceOfSet(int[] a, int[] b) {
        Set<Integer> setA = Arrays.stream(a).boxed().collect(Collectors.toSet());
        Set<Integer> setB = Arrays.stream(b).boxed().collect(Collectors.toSet());

        setA.removeAll(setB);

        return setA.stream().mapToInt(Integer::intValue).sum();
    }
}