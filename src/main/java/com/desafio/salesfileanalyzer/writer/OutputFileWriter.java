package com.desafio.salesfileanalyzer.writer;

import com.desafio.salesfileanalyzer.model.OutputFile;
import com.desafio.salesfileanalyzer.util.Constants;

import java.io.*;
import java.nio.file.Path;

public class OutputFileWriter {

    private Path outputPath;

    public OutputFileWriter(Path outputPath){
        this.outputPath = outputPath;
    }

    public void write(OutputFile outputFile) {

        final String fileName = generateFileName(outputFile.getFileName());

        try (FileWriter writer = new FileWriter(fileName);
             BufferedWriter bw = new BufferedWriter(writer)) {
                bw.write(outputFile.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String generateFileName(String fileName){
        fileName = fileName.replace(Constants.FILE_EXTENSION_INPUT, Constants.FILE_EXTENSION_OUPUT);
        return outputPath.resolve(fileName).toString();
    }


}
