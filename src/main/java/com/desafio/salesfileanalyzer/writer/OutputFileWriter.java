package com.desafio.salesfileanalyzer.writer;

import com.desafio.salesfileanalyzer.model.InputData;
import com.desafio.salesfileanalyzer.util.Constants;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OutputFileWriter {

    public void write(Path outputPath, InputData inputData) throws Exception {
        List<String> outputData = this.createOutputData(inputData);
        String outputFileName = inputData.getFileName().replace(Constants.FILE_EXTENSION_INPUT, Constants.FILE_EXTENSION_OUPUT);

        try (FileWriter writer = new FileWriter(outputPath.resolve(outputFileName).toString());
             BufferedWriter bw = new BufferedWriter(writer)) {

            for (String data: outputData) {
                bw.write(data);
                bw.write(System.getProperty("line.separator"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> createOutputData(InputData inputData) throws Exception {
        List<String> outputData = new ArrayList<>();

        outputData.add("Quantidade de clientes no arquivo de entrada: " + inputData.getCountCustomers());
        outputData.add("Quantidade de vendedor no arquivo de entrada: " + inputData.getCountSellers());
        outputData.add("ID da venda mais cara: " + inputData.getMostExpensiveSaleId());
        outputData.add("O pior vendedor: " + inputData.getWorstSeller());

        return outputData;
    }


}
