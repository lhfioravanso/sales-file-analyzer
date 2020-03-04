package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Customer;

public class CustomerParser {

    private static final int CNPJ = 1;
    private static final int NAME = 2;
    private static final int BUSINESS_AREA = 3;

    private static final int LINE_LENGTH = 4;

    public static Customer parse(String[] lineData) throws InvalidLineDataSizeException {

        if (lineData.length != LINE_LENGTH)
            throw new InvalidLineDataSizeException();

        return new Customer(lineData[CNPJ], lineData[NAME], lineData[BUSINESS_AREA]);
    }


}
