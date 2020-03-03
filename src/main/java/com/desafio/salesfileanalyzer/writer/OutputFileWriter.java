package com.desafio.salesfileanalyzer.writer;

import com.desafio.salesfileanalyzer.model.SalesInputFile;
import com.desafio.salesfileanalyzer.util.Constants;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OutputFileWriter {

    public void write(Path outputPath, SalesInputFile salesInputFile) throws Exception {
        List<String> outputData = this.createOutputData(salesInputFile);

        String fileName = generateFileName(salesInputFile);
        try (FileWriter writer = new FileWriter(outputPath.resolve(fileName).toString());
             BufferedWriter bw = new BufferedWriter(writer)) {

            for (String data: outputData) {
                bw.write(data);
                bw.write(System.getProperty("line.separator"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateFileName(SalesInputFile salesInputFile){
        return salesInputFile.getFileName().replace(Constants.FILE_EXTENSION_INPUT, Constants.FILE_EXTENSION_OUPUT);
    }

    public List<String> createOutputData(SalesInputFile salesInputFile) throws Exception {
        List<String> outputData = new ArrayList<>();

        outputData.add("Quantidade de clientes no arquivo de entrada: " + salesInputFile.getCountCustomers());
        outputData.add("Quantidade de vendedor no arquivo de entrada: " + salesInputFile.getCountSellers());
        outputData.add("ID da venda mais cara: " + salesInputFile.getMostExpensiveSaleId());
        outputData.add("O pior vendedor: " + salesInputFile.getWorstSeller());

        return outputData;
    }


}
