package com.tzapps.book.fp.closure;

import java.util.Optional;
import java.util.function.Function;

public class App2 {

    public static void main(String[] args) {

        Function<Integer, Optional<String>> numToText = num -> {
            final String[] weeks = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
            return (num > 0 && num <= weeks.length) ? Optional.of(weeks[num - 1]) : Optional.empty();
        };

        Optional<String> okStr = numToText.apply(3);
        System.out.println(okStr.get());
        Optional<String> emptyStr = numToText.apply(30);
        System.out.println(emptyStr.isEmpty());

        /*
            Wed
            true
        */

        // decouple a piece of code from the flow execution

    }
}
