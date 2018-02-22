package com.hashcode;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class MathUtilsTest {

    @Test
    public void factors() {
        assertThat(MathUtils.factors(14)).containsExactlyInAnyOrder(1, 2, 7, 14);

        System.out.println(MathUtils.factors(5));
    }
}