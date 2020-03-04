package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.exception.InvalidLayoutException;
import com.desafio.salesfileanalyzer.model.SalesInputFile;

import java.util.List;

public class SalesFileParser implements IFileParser {

    private SalesInputFile salesInputFile;

    private final String SPLIT_CHAR_LINES = "ç";

    private static final String ID_SELLER = "001";
    private static final String ID_CUSTOMER = "002";
    private static final String ID_SALE = "003";

    @Override
    public SalesInputFile parseFile(String fileName, List<String> lines) throws Exception {
        salesInputFile = new SalesInputFile(fileName);

        String[] splittedLine;
        for (String line: lines) {
            splittedLine = line.split(SPLIT_CHAR_LINES);

            switch (splittedLine[0]){
                case ID_SELLER:
                    salesInputFile.addSeller(SellerParser.parse(splittedLine));
                    break;
                case ID_CUSTOMER:
                    salesInputFile.addCustomer(CustomerParser.parse(splittedLine));
                    break;
                case ID_SALE:
                    salesInputFile.addSale(SaleParser.parse(splittedLine));
                    break;
                default:
                    throw new InvalidLayoutException();
            }
        }


        return salesInputFile;
    }
}
