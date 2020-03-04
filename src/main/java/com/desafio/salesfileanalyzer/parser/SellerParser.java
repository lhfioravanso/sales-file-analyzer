package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Seller;

public class SellerParser {

    private static final int CPF = 1;
    private static final int NAME = 2;
    private static final int SALARY = 3;

    private static final int LINE_LENGTH = 4;

    public static Seller parse(String[] lineData) throws InvalidLineDataSizeException {

        if (lineData.length != LINE_LENGTH)
            throw new InvalidLineDataSizeException();

        return new Seller(lineData[CPF], lineData[NAME], Double.parseDouble(lineData[SALARY]));
    }


}
