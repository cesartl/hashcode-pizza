package com.hashcode;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class PizzaTest {

    @Test
    public void testParse() throws Exception{
        final String name = "small.in";
        final Pizza small = parsePizza(name);
        System.out.println(small);

        final Slice slice = Slice.slice(1, 1, 2, 2);

        assertThat(slice.area()).isEqualTo(4);

        assertThat(small.count(slice, Ingredient.T)).isEqualTo(1);
        assertThat(small.count(slice, Ingredient.M)).isEqualTo(3);

        System.out.println(small.possibleSlices());

        System.out.println(parsePizza("big.in").possibleSlices());
    }

    private Pizza parsePizza(String name) throws IOException {
        final InputStream in = this.getClass().getClassLoader().getResourceAsStream(name);
        return Pizza.fromStream(in);
    }
}