package com.hashcode;

import java.util.List;

public class DummySlicer implements Slicer {
    @Override
    public List<Slice> slice(Pizza pizza) {
        final int width = (int) Math.floor(Math.sqrt(pizza.getMax()));
        final int height = width / pizza.getMax();

        return null;
    }
}
