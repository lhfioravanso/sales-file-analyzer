package com.desafio.salesfileanalyzer.parser;

import com.desafio.salesfileanalyzer.model.SalesInputFile;
import com.desafio.salesfileanalyzer.util.Constants;

import java.util.List;

public class SalesFileParser implements IFileParser {

    private SalesInputFile salesInputFile;

    @Override
    public SalesInputFile parseFile(String fileName, List<String> lines) throws Exception {
        salesInputFile = new SalesInputFile(fileName);

        String[] splittedLine;
        for (String line: lines) {
            splittedLine = line.split(Constants.SPLIT_CHAR_LINES);

            switch (splittedLine[0]){
                case "001":
                    salesInputFile.addSeller(SellerParser.parse(splittedLine));
                    break;
                case "002":
                    salesInputFile.addCustomer(CustomerParser.parse(splittedLine));
                    break;
                case "003":
                    salesInputFile.addSale(SaleParser.parse(splittedLine));
                    break;
                default:
                    throw new Exception("Layout do arquivo não esperado.");
            }
        }


        return salesInputFile;
    }
}
