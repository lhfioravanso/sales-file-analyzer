package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLineDataSizeException;
import com.desafio.salesfileanalyzer.model.Item;
import com.desafio.salesfileanalyzer.model.Sale;
import com.desafio.salesfileanalyzer.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class SaleParser {

    private static final int ID = 1;
    private static final int ITENS = 2;
    private static final int SELLER_NAME = 3;

    private static final int LINE_LENGTH = 4;

    public static Sale parse(String[] lineData) throws InvalidLineDataSizeException {

        if (lineData.length != LINE_LENGTH)
            throw new InvalidLineDataSizeException();

        return new Sale(Integer.parseInt(lineData[ID]), getItemsFromLine(lineData[ITENS]), lineData[SELLER_NAME]);
    }

    public static List<Item> getItemsFromLine(String itemLine) throws InvalidLineDataSizeException {
        List<Item> itens = new ArrayList<>();

        String[] splittedItens = itemLine.replace("[", "").replace("]", "").split(Constants.SPLIT_CHAR_ITENS);
        String[] currItem;

        for (String splitItem: splittedItens) {
            currItem = splitItem.split(Constants.SPLIT_CHAR_ITENS_LINE);
            itens.add(ItemParser.parse(currItem));
        }

        return itens;
    }

}
