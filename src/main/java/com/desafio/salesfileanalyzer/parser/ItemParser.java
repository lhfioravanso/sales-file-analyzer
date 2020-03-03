package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.model.Item;

public class ItemParser {

    private static final int ID = 0;
    private static final int QUANTITY = 1;
    private static final int PRICE = 2;

    public static Item parse(String[] line) {
        return new Item(Integer.parseInt(line[ID]), Integer.parseInt(line[QUANTITY]), Double.parseDouble(line[PRICE]));
    }


}
