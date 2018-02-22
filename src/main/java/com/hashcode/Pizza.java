package com.hashcode;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Pizza {
    private final int rows;
    private final int columns;
    private final int min;
    private final int max;
    private final Table<Integer, Integer, Ingredient> table;

    public Pizza(int rows, int columns, int min, int max, Table<Integer, Integer, Ingredient> table) {
        this.rows = rows;
        this.columns = columns;
        this.min = min;
        this.max = max;
        this.table = ArrayTable.create(table);
    }

    public static Pizza fromStream(InputStream in) throws IOException {
        final List<String> lines = IOUtils.readLines(new InputStreamReader(in));
        final String[] split = lines.get(0).split(" ");
        final int rows = Integer.parseInt(split[0]);
        final int columns = Integer.parseInt(split[1]);
        final int min = Integer.parseInt(split[2]);
        final int max = Integer.parseInt(split[3]);
        final List<List<Ingredient>> cells = lines.stream().skip(1)
                .map(line -> Arrays.stream(line.split("")).map(Ingredient::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());

        Table<Integer, Integer, Ingredient> table = HashBasedTable.create();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                table.put(row, column, cells.get(row).get(column));
            }
        }
        return new Pizza(rows, columns, min, max, table);
    }

    private String printTable() {
        return table.rowMap().values().stream().map(row -> row.values().stream().map(Enum::name).collect(Collectors.joining("")))
                .collect(Collectors.joining("\n"));
    }

    public long count(Slice slice, Ingredient ingredient) {
        return slice.coordinates().map(p -> table.get(p.getLeft(), p.getRight()))
                .filter(c -> c == ingredient)
                .count();
    }

    public List<Slice> possibleSlices() {
        return Stream.iterate(2 * min, i -> i + 1).limit(max - 2 * min + 1)
                .flatMap(area -> allPairs(area).stream())
                .map(this::pairToSlice).collect(Collectors.toList());
    }

    private Set<Pair<Integer, Integer>> allPairs(int area) {
        final List<Integer> factors = MathUtils.factors(area);
        final List<Pair<Integer, Integer>> pairs = factors.stream().map(f -> Pair.of(f, area / f)).collect(Collectors.toList());
        return Stream.concat(pairs.stream(), pairs.stream().map(p -> Pair.of(p.getRight(), p.getLeft()))).collect(Collectors.toSet());
    }

    private Slice pairToSlice(Pair<Integer, Integer> pair) {
        return new Slice(Pair.of(0, 0), pair);
    }

    @Override
    public String toString() {
        return printTable();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public Table<Integer, Integer, Ingredient> getTable() {
        return table;
    }
}
