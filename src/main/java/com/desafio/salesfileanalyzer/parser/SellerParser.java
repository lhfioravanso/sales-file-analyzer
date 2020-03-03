package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.model.Seller;

public class SellerParser {

    private static final int CPF = 1;
    private static final int NAME = 2;
    private static final int SALARY = 3;

    public static Seller parse(String[] line) {
        return new Seller(line[CPF], line[NAME], Double.parseDouble(line[SALARY]));
    }


}
