package com.tzapps.book.fp.builder;


import lombok.Builder;
import lombok.Singular;
import lombok.val;

import java.util.Set;

@Builder
class BuilderExample {
    @Builder.Default private long created = System.currentTimeMillis();
    private String name;
    private int age;
    @Singular
    private Set<String> occupations;
}

public class App {

    public static void main(String[] args) {

        val example = BuilderExample.builder()
                .age(12)
                .name("bubu")
                .occupation("job 1")
                .occupation("job 2")
                .build();

        /*
        example = {BuilderExample@666}
                created = 1560974302663
                name = "bubu"
                age = 12
                occupations = {Collections$UnmodifiableSet@668}  size = 2
                     0 = "job 1"
                     1 = "job 2"
        * */
    }
}


