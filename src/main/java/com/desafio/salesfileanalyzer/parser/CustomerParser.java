package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.model.Customer;

public class CustomerParser {

    private static final int CNPJ = 1;
    private static final int NAME = 2;
    private static final int BUSINESS_AREA = 3;

    public static Customer parse(String[] line) {
        return new Customer(line[CNPJ], line[NAME], line[BUSINESS_AREA]);
    }


}
