package examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Challenge {
    public static void main(String[] args) {
        // 3,44  -> "o3,e44"   even or odd
        System.out.println(getString(Arrays.asList(3, 44)));
        System.out.println(getString(Arrays.asList(3)));
    }

    public static String getString(List<Integer> list) {
        return list.stream()
                .map(i -> {
                    String s = "";
                    if (i % 2 == 0) {
                        s += "e" + i;
                    } else {
                        s += "o" + i;
                    }
                    return s;
                }).collect(Collectors.joining(","));
    }
}
