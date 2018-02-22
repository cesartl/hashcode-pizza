package com.hashcode;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Slice {
    private Pair<Integer, Integer> topLeft;
    private Pair<Integer, Integer> bottomRight;

    public Slice(Pair<Integer, Integer> topLeft, Pair<Integer, Integer> bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public int area() {
        return width() * height();
    }

    public int width() {
        return bottomRight.getRight() - topLeft.getRight() + 1;
    }

    public int height() {
        return bottomRight.getLeft() - topLeft.getLeft() + 1;
    }

    public Stream<Pair<Integer, Integer>> coordinates() {
        final List<Pair<Integer, Integer>> l = new ArrayList<>();
        for (int row = topLeft.getLeft(); row <= bottomRight.getLeft(); row++) {
            for (int col = topLeft.getRight(); col <= bottomRight.getRight(); col++) {
                l.add(Pair.of(row, col));
            }
        }
        return l.stream();
    }

    public static Slice slice(int r1, int c1, int r2, int c2) {
        return new Slice(Pair.of(r1, c1), Pair.of(r2, c2));
    }

    public Slice translate(int row, int col){
        return new Slice(Pair.of(row + topLeft.getLeft(), topLeft.getRight() + col), Pair.of(bottomRight.getLeft() + row, bottomRight.getRight() + col));
    }

    @Override
    public String toString() {
        return String.format("%d:%d %d:%d", topLeft.getLeft(), topLeft.getRight(), bottomRight.getLeft(), bottomRight.getRight());
    }
}
