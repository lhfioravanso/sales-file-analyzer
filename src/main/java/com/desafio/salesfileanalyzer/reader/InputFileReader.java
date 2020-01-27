package com.desafio.salesfileanalyzer.reader;

import com.desafio.salesfileanalyzer.model.InputData;
import com.desafio.salesfileanalyzer.model.Item;
import com.desafio.salesfileanalyzer.util.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {

    public InputData read(Path filePath, String fileName) {
        InputData inputData = new InputData(fileName);

        try (FileReader reader = new FileReader(filePath.resolve(fileName).toString(), StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(reader)) {

            String line;
            String[] splittedLine;
            while ((line = br.readLine()) != null) {
                splittedLine = line.split(Constants.SPLIT_CHAR_LINES);

                switch (splittedLine[0]){
                    case "001":
                        inputData.addSeller(splittedLine[1], splittedLine[2], Double.parseDouble(splittedLine[3]));
                        break;
                    case "002":
                        inputData.addCustomer(splittedLine[1], splittedLine[2], splittedLine[3]);
                        break;
                    case "003":
                        inputData.addSale(Integer.parseInt(splittedLine[1]), readItemLine(splittedLine[2]), splittedLine[3]);
                        break;
                    default:
                        throw new Exception("Layout do arquivo não esperado.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            inputData.getMostExpensiveSaleId();
            inputData.getWorstSeller();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputData;
    }

    public List<Item> readItemLine(String itemLine) {
        List<Item> itens = new ArrayList<>();

        String[] splittedItens = itemLine.replace("[", "").replace("]", "").split(Constants.SPLIT_CHAR_ITENS);
        String[] currItem;

        for (String splitItem: splittedItens) {
            currItem = splitItem.split(Constants.SPLIT_CHAR_ITENS_LINE);
            itens.add(new Item(
                    Integer.parseInt(currItem[0]),
                    Integer.parseInt(currItem[1]),
                    Double.parseDouble(currItem[2])
            ));
        }

        return itens;
    }

}
